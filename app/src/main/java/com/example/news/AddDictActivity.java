package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.news.data.DictionaryDB;
import com.example.news.data.dao.DictionaryDao;
import com.example.news.data.entity.DictionaryEntity;

public class AddDictActivity extends AppCompatActivity {

    private EditText edtWord, edtSpeech, edtMeaning;
    private Button btnSave;

    private DictionaryDao dictionaryDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dict);
        edtWord = findViewById(R.id.word);
        edtSpeech = findViewById(R.id.partofspeech);
        edtMeaning = findViewById(R.id.description);
        btnSave = findViewById(R.id.btn_save);

        dictionaryDao = DictionaryDB.getInstance(this).dictionaryDao();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DictionaryEntity dictionaryEntity = new DictionaryEntity();
                dictionaryEntity.word = edtWord.getText().toString();
                dictionaryEntity.partofspeech = edtSpeech.getText().toString();
                dictionaryEntity.meaning = edtMeaning.getText().toString();
                dictionaryDao.save((dictionaryEntity));

                Toast.makeText(AddDictActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();

    }

}
