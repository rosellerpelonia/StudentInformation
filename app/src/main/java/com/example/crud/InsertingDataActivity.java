package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertingDataActivity extends AppCompatActivity {

    EditText etName;
    EditText etCourse;
    EditText etYear;
    EditText etAge;

    Button btnInsertData;
    DatabaseReference studentDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserting_data);

        etName = findViewById(R.id.etName);
        etCourse = findViewById(R.id.etCourse);
        etYear = findViewById(R.id.etYear);
        etAge = findViewById(R.id.etAge);
        btnInsertData = findViewById(R.id.btnInsertData);
        studentDbRef = FirebaseDatabase.getInstance().getReference("Students");
        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertStudentData();
                etName.getText().clear();
                etCourse.getText().clear();
                etYear.getText().clear();
                etAge.getText().clear();
            }
        });
    }

    private void insertStudentData(){
        String name = etName.getText().toString();
        String course = etCourse.getText().toString();
        String year = etYear.getText().toString();
        String age = etAge.getText().toString();

        String id = studentDbRef.push().getKey();

        Students students = new Students(id,name,course,year,age);
        assert id != null;
        studentDbRef.child(id).setValue(students);
        Toast.makeText(InsertingDataActivity.this,"Data inserted!",Toast.LENGTH_LONG).show();
    }
}