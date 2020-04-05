package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.news.data.DictionaryDB;
import com.example.news.data.dao.DictionaryDao;
import com.example.news.data.entity.DictionaryEntity;

public class ViewActivity extends AppCompatActivity {

    private static final String VIEW_KEY_DICTIONARY ="3000" ;
    TextView tvword,tvpartofspeech,tvmeaning;
    Button btnback;
    DictionaryEntity dictionaryEntity;
    DictionaryDao dictionaryDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        tvword = findViewById(R.id.view_word);
        tvpartofspeech = findViewById(R.id.view_partofspeech);
        tvmeaning = findViewById(R.id.view_meaning);
        btnback = findViewById(R.id.btn_back);
        dictionaryDao = DictionaryDB.getInstance(this).dictionaryDao();
        view();

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void view() {
        Intent intent = getIntent();
        if (intent != null){
            dictionaryEntity = intent.getParcelableExtra(VIEW_KEY_DICTIONARY);
            tvword.setText(dictionaryEntity.word);
            tvpartofspeech.setText(dictionaryEntity.partofspeech);
            tvmeaning.setText(dictionaryEntity.meaning);
        }
    }
    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}
