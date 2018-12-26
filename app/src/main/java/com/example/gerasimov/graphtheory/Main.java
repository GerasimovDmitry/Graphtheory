package com.example.gerasimov.graphtheory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Main extends AppCompatActivity {

    String[] dataNodes = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
    String[] dataType = {"Digraph", "Undigraph"};
    static String s;
    GridLayout matrix;
    Spinner spinnerNodes;
    Button buttonClear, buttonAlg, buttonGraph, buttonLibrary;
    static int countNodes = 2;
    static EditText[][] nums;
    static int [][] matrixNums;

    public static int[][] getMatrix()
    {
        GraphActivity.flag = true;
        matrixNums = new int[countNodes][countNodes];
        for(int i = 0; i < countNodes; i++ )
        {
            for(int j = 0; j < countNodes; j++){
                if(i == j){
                    matrixNums[i][j] = 0;
                }
                else{
                    matrixNums[i][j] = Integer.valueOf(nums[i][j].getText().toString());
                }
            }
        }
        return matrixNums;
    }
    public void printMatrix(int count)
    {
        nums = new EditText[count][count];
        count++;
        matrix.removeAllViews();
        matrix.setColumnCount(count);
        matrix.setRowCount(count);
        for(int i = 0; i < count; i++)
        {
            for(int j = 0; j < count; j++)
            {   if(i==j && i != 0) {
                EditText a = new EditText(this);
                a.setText("x");
                a.setEnabled(false);
                matrix.addView(a);
                if(i >= 1 && j >= 1){
                    nums[i-1][j-1] = a;
                }
                continue;
                }
                if(i == 0 && j == 0)
                {
                EditText a = new EditText(this);
                a.setText("_|");
                a.setEnabled(false);
                matrix.addView(a);
                continue;
                }
                if(i == 0)
                {
                    EditText a = new EditText(this);
                    a.setText(Integer.toString(j));
                    a.setEnabled(false);
                    matrix.addView(a);
                } else if(j == 0){
                                EditText a = new EditText(this);
                                a.setText(Integer.toString(i));
                                a.setEnabled(false);
                                matrix.addView(a);
                }else{

                EditText a = new EditText(this);
                a.setText(Integer.toString(0));
                matrix.addView(a);
                    if(i >= 1 && j >= 1){
                        nums[i-1][j-1] = a;
                    }
                }
            }
        }
        matrix.setRight(20);
        matrix.setLeft(20);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataNodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerNodes = (Spinner) findViewById(R.id.spinnerNodes);
        spinnerNodes.setAdapter(adapter);
        // заголовок
        spinnerNodes.setPrompt("2");
        // выделяем элемент
        spinnerNodes.setSelection(0);
        // устанавливаем обработчик нажатия
        spinnerNodes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                countNodes = Integer.parseInt(parent.getItemAtPosition(position).toString());
                printMatrix(countNodes);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        // адаптер
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataType);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerMatrix);
        spinner.setAdapter(adapter2);
        // заголовок
        spinner.setPrompt("Digraph");
        // выделяем элемент
        spinner.setSelection(0);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        // работа с GridLayout

            matrix = (GridLayout) findViewById(R.id.gridLayout);
            printMatrix(countNodes);

        //Работа с кнопками
        buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonAlg = (Button) findViewById(R.id.buttonAlg);
        buttonGraph = (Button) findViewById(R.id.buttonGraph);
        buttonLibrary = (Button) findViewById(R.id.buttonLibrary);

        View.OnClickListener oclbuttonClear = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printMatrix(3);
                spinnerNodes.setSelection(0);
            }
        };
        buttonClear.setOnClickListener(oclbuttonClear);

        //activity GRAPH
        View.OnClickListener oclbuttonGraph = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Main.this, GraphActivity.class);
                    startActivity(intent);
                }
        };
        buttonGraph.setOnClickListener(oclbuttonGraph);

        //activity ALG
        View.OnClickListener oclbuttonAlg = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Main.this, AlgActivity.class);
                startActivity(intent2);
            }
        };
        buttonAlg.setOnClickListener(oclbuttonAlg);

        // Activity LIBRARY
        View.OnClickListener oclbuttonLibrary = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Main.this, LibActivity.class);
                startActivity(intent3);
            }
        };
        buttonLibrary.setOnClickListener(oclbuttonLibrary);
    }
}
