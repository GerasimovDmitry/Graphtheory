package com.example.gerasimov.graphtheory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GraphActivity extends AppCompatActivity {
    Button  buttonAlg, buttonLibrary, buttonMatrix;
    private GraphView graphView;
    public static boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);
        graphView = (GraphView) findViewById(R.id.ImageGraph);
        graphView.setDrawCustomCanvas(true);
        graphView.invalidate();
        //Работа с кнопками
        buttonAlg = (Button) findViewById(R.id.buttonAlg);
        buttonMatrix = (Button) findViewById(R.id.buttonMatrix);
        buttonLibrary = (Button) findViewById(R.id.buttonLibrary);

        //activity MAIN
        View.OnClickListener oclbuttonMatrix = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent2 = new Intent(GraphActivity.this, Main.class);
                //startActivity(intent2);
                Intent intent = new Intent(GraphActivity.this, Main.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        };
        buttonMatrix.setOnClickListener(oclbuttonMatrix);

        // Activity LIBRARY
        View.OnClickListener oclbuttonLibrary = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(GraphActivity.this, LibActivity.class);
                startActivity(intent3);
            }
        };
        buttonLibrary.setOnClickListener(oclbuttonLibrary);

        //activity ALG
        View.OnClickListener oclbuttonAlg = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(GraphActivity.this, AlgActivity.class);
                startActivity(intent2);
            }
        };
        buttonAlg.setOnClickListener(oclbuttonAlg);
    }
}
