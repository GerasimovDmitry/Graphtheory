package com.example.gerasimov.graphtheory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class AlgActivity extends AppCompatActivity {
    Spinner spinnerAlg, spinnerS, spinnerF;
    Button buttonAlg, buttonGraph, buttonLibrary, buttonMatrix, buttonStart;
    TextView textResult;
    String[] dataNodes = {"1","2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
    String[] dataAlg = {"DFS", "Floyd", "Dijkstra", "etc."};
    String choice;
    int startNode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alg_acttvity);

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataAlg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAlg = (Spinner) findViewById(R.id.spinnerAlg);
        spinnerAlg.setAdapter(adapter);
        // заголовок
        spinnerAlg.setPrompt("BFS");
        // выделяем элемент
        spinnerAlg.setSelection(0);
        // устанавливаем обработчик нажатия
        spinnerAlg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                choice = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        // адаптер
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataNodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerS = (Spinner) findViewById(R.id.spinnerS);
        spinnerS.setAdapter(adapter2);
        // заголовок
        spinnerS.setPrompt("DFS");
        // выделяем элемент
        spinnerS.setSelection(0);
        // устанавливаем обработчик нажатия
        spinnerS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                startNode = Integer.parseInt(parent.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        // адаптер
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataNodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerF = (Spinner) findViewById(R.id.spinnerF);
        spinnerF.setAdapter(adapter3);
        // заголовок
        spinnerF.setPrompt("1");
        // выделяем элемент
        spinnerF.setSelection(0);
        // устанавливаем обработчик нажатия
        spinnerF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        //Работа с кнопками
        buttonMatrix = (Button) findViewById(R.id.buttonMatrix);
        buttonGraph = (Button) findViewById(R.id.buttonGraph);
        buttonLibrary = (Button) findViewById(R.id.buttonLibrary);
        buttonStart = (Button) findViewById(R.id.buttonStart);

        View.OnClickListener oclbuttonStart = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Аlgorithms.setMatrix();
                textResult = (TextView) findViewById(R.id.textViewResult);
                Аlgorithms.n = Main.countNodes;
                if(choice.equals("DFS")){
                    int k = Аlgorithms.components();
                    String result =  new StringBuilder().append("Components = ").append(k).append('\n').toString();
                    for(int i = 0; i < k; i++){
                        result = new StringBuilder().append(Аlgorithms.components[i]).append(result).toString();
                    }
                }
                if(choice.equals("Floyd")) {
                    int [][] a = Аlgorithms.floyd();
                    String result = "";
                    for(int i = 0; i < Main.countNodes; i++){
                        for(int j = 0; j < Main.countNodes; j++ )
                            result =  new StringBuilder().append(result).append(" ").append(a[i][j]).toString();
                        result = new StringBuilder().append(result).append('\n').toString();
                    }
                    textResult.setText(result);
                }
                if(choice.equals("Dijkstra")) {
                    int [] a = Аlgorithms.dijkstra(startNode);
                    String result = "";
                    for(int i = 0; i < Main.countNodes; i++){
                            result =  new StringBuilder().append(result).append(" ").append(a[i]).toString();
                    }
                    textResult.setText(result);
                }
                }
        };
        buttonStart.setOnClickListener(oclbuttonStart);

        //activity GRAPH
        View.OnClickListener oclbuttonGraph = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlgActivity.this, GraphActivity.class);
                startActivity(intent);
            }
        };
        buttonGraph.setOnClickListener(oclbuttonGraph);

        //activity MAIN
        View.OnClickListener oclbuttonMatrix = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent2 = new Intent(AlgActivity.this, Main.class);
                //startActivity(intent2);
                Intent intent = new Intent(AlgActivity.this, Main.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        };
        buttonMatrix.setOnClickListener(oclbuttonMatrix);

        // Activity LIBRARY
        View.OnClickListener oclbuttonLibrary = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(AlgActivity.this, LibActivity.class);
                startActivity(intent3);
            }
        };
        buttonLibrary.setOnClickListener(oclbuttonLibrary);
    }
}
