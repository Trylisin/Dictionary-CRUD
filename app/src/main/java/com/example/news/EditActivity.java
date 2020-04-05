package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.news.data.DictionaryDB;
import com.example.news.data.dao.DictionaryDao;
import com.example.news.data.entity.DictionaryEntity;

public class EditActivity extends AppCompatActivity {

    private static final String EDIT_KEY_DICTIONARY ="2000" ;
    EditText edtword,edtspeech,edtmeaning;
    Button btnUpdate;
    DictionaryEntity dictionaryEntity;
    DictionaryDao dictionaryDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtmeaning = findViewById(R.id.edit_description);
        edtspeech = findViewById(R.id.editpartofspeech);
        edtword = findViewById(R.id.editword);
        btnUpdate = findViewById(R.id.btn_update);
        dictionaryDao = DictionaryDB.getInstance(this).dictionaryDao();
        bind();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDictionary();
            }
        });
    }
    private void bind() {
        Intent intent = getIntent();
        if (intent != null){
            dictionaryEntity = intent.getParcelableExtra(EDIT_KEY_DICTIONARY);
            edtword.setText(dictionaryEntity.word);
            edtspeech.setText(dictionaryEntity.partofspeech);
            edtmeaning.setText(dictionaryEntity.meaning );
        }
    }
    private void updateDictionary() {

        if (dictionaryEntity != null){
            dictionaryEntity.word = edtword.getText().toString();
            dictionaryEntity.partofspeech = edtspeech.getText().toString();
            dictionaryEntity.meaning = edtmeaning.getText().toString();

            dictionaryDao.update(dictionaryEntity);

            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }

}
