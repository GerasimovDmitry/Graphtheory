package com.example.gerasimov.graphtheory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TermActivity extends AppCompatActivity {

    private EditText termBox;
    private EditText detBox;
    private Button delButton;
    private Button saveButton;

    private DatabaseAdapter adapter;
    private long termId =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_activity);

        termBox = (EditText) findViewById(R.id.term);
        detBox = (EditText) findViewById(R.id.det);
        delButton = (Button) findViewById(R.id.deleteButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        adapter = new DatabaseAdapter(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            termId = extras.getLong("id");
        }
        // если 0, то добавление
        if (termId > 0) {
            // получаем элемент по id из бд
            adapter.open();
            TermRepository termRepository = adapter.getTerm(termId);
            termBox.setText(termRepository.getTerm());
            detBox.setText(String.valueOf(termRepository.getDet()));
            adapter.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
    }

    public void save(View view){


        String term = termBox.getText().toString();
        String det = (detBox.getText().toString());
        TermRepository termRepository = new TermRepository(termId, term, det);

        adapter.open();
        if (termId > 0) {
            adapter.update(termRepository);
        } else {
            adapter.insert(termRepository);
        }
        adapter.close();
        goHome();
    }
    public void delete(View view){

        adapter.open();
        adapter.delete(termId);
        adapter.close();
        goHome();
    }
    private void goHome(){
        // переход к главной activity
        Intent intent = new Intent(this, LibActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}
