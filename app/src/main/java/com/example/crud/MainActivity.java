package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnInsertData;
    Button  btnRetreiveData;
    Button  btnSearchStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertData = findViewById(R.id.btnInsertData);
        btnRetreiveData = findViewById(R.id.btnRetreiveData);
        btnSearchStudent = findViewById(R.id.btnSearchStudent);

        btnInsertData.setOnClickListener(this);
        btnRetreiveData.setOnClickListener(this);
        btnSearchStudent.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnInsertData:
                startActivity(new Intent(MainActivity.this, InsertingDataActivity.class));
                break;

            case R.id.btnRetreiveData:
                startActivity(new Intent(MainActivity.this, RetreiveDataActivity.class));
                break;

            case R.id.btnSearchStudent:
                startActivity(new Intent(MainActivity.this, RetreiveDataSearchActivity.class));
                break;
             }
        }
    }
