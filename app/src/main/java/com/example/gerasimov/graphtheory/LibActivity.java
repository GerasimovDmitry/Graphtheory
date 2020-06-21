package com.example.gerasimov.graphtheory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class LibActivity extends AppCompatActivity {

    private ListView termRepositoryList;
    ArrayAdapter<TermRepository> arrayAdapter;
    DatabaseHelper sqlHelper;
    Button buttonAdd;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lib_actvity);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        View.OnClickListener oclbuttonGraph = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibActivity.this, TermActivity.class);
                startActivity(intent);
            }
        };
        buttonAdd.setOnClickListener(oclbuttonGraph);

        sqlHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        sqlHelper.create_db();

        termRepositoryList = (ListView) findViewById(R.id.list);

        termRepositoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TermRepository termRepository = arrayAdapter.getItem(position);
                if (termRepository != null) {
                    Intent intent = new Intent(getApplicationContext(), TermActivity.class);
                    intent.putExtra("id", termRepository.getId());
                    intent.putExtra("click", 25);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseAdapter adapter = new DatabaseAdapter(this);
        adapter.open();

        List<TermRepository> terms = adapter.getTerms();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, terms);
        termRepositoryList.setAdapter(arrayAdapter);
        adapter.close();
        // sqlHelper.close();
    }
    // по нажатию на кнопку запускаем TermActivity для добавления данных
}