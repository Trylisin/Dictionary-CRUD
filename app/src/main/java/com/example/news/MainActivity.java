package com.example.news;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.adapter.DictionaryAdapter;
import com.example.news.data.DictionaryDB;
import com.example.news.data.dao.DictionaryDao;
import com.example.news.data.entity.DictionaryEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String EDIT_KEY_DICTIONARY ="2000" ;
    private static final String VIEW_KEY_DICTIONARY ="3000" ;
    private static final int REQUEST_EDIT_DICT_CODE = 101;
    private static final int REQUEST_VIEW_DICT_CODE = 102;
    private FloatingActionButton btnAdd;

    private DictionaryDao dictionaryDao;
    private static int position;
    private RecyclerView recyclerView;
    private DictionaryAdapter adapter;

    private static final int REQUEST_ADD_DICT_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.floatingActionButton);
        dictionaryDao = DictionaryDB.getInstance(this).dictionaryDao();
        recyclerView = findViewById(R.id.rvDictionary);
        setupRecyclerView();
        loadDictList();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(MainActivity.this,
                                AddDictActivity.class), REQUEST_ADD_DICT_CODE
                );
            }
        });

    }

    private void setupRecyclerView() {
        adapter = new DictionaryAdapter(new ArrayList<DictionaryEntity>(), new DictionaryAdapter.AdapterListener() {
            @Override
            public void onButtonClickMore(View view, DictionaryEntity dictionaryEntity, int position) {
                openPopupMenu(view,dictionaryEntity,position);
            }

        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void openPopupMenu(View view,final DictionaryEntity dictionaryEntity, int pos) {
        position = pos;
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.edit:
                        editDictionary(dictionaryEntity);
                        return true;
                    case R.id.delete:
                        deleteDictionary(dictionaryEntity);
                        return true;
                    case R.id.view:
                        viewDictionary(dictionaryEntity);
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }

    private void deleteDictionary(DictionaryEntity dictionaryEntity) {
        if (position>=0){
            dictionaryDao.delete(dictionaryEntity);
            adapter.remove(position);
        }
    }

    private void editDictionary(DictionaryEntity dictionaryEntity) {
        Intent intent = new Intent(this,EditActivity.class);
        intent.putExtra(EDIT_KEY_DICTIONARY, dictionaryEntity);
        startActivityForResult(intent,REQUEST_EDIT_DICT_CODE);
    }
    private void viewDictionary(DictionaryEntity dictionaryEntity) {
        Intent intent = new Intent(this,ViewActivity.class);
        intent.putExtra(VIEW_KEY_DICTIONARY, dictionaryEntity);
        startActivityForResult(intent,REQUEST_VIEW_DICT_CODE);
    }
    private void loadDictList() {
        adapter.addMoreItem(dictionaryDao.getDictionary());
    }

    private void reload() {
        adapter.reloadItems(dictionaryDao.getDictionary());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK &&
                (requestCode == REQUEST_ADD_DICT_CODE || requestCode == REQUEST_EDIT_DICT_CODE ||requestCode == REQUEST_VIEW_DICT_CODE )) {
            reload();
        }
    }
}
