package com.example.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetreiveDataActivity extends AppCompatActivity {

    ListView myListview;
    List<Students> studentsList;

    DatabaseReference studentDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_data);

        myListview = findViewById(R.id.myListView);
        studentsList = new ArrayList<>();

        studentDbRef = FirebaseDatabase.getInstance().getReference("Students");

        studentDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentsList.clear();

                for (DataSnapshot studentDatasnap : dataSnapshot.getChildren()){
                    Students students = studentDatasnap.getValue(Students.class);
                    studentsList.add(students);
                }

                ListAdapter adapter = new ListAdapter(RetreiveDataActivity.this,studentsList);
                myListview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //set itemLong listener on listview item

        myListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Students students = studentsList.get(position);
                showUpdateDialog(students.getId(), students.getName());

                return false;
            }
        });
    }

    private void showUpdateDialog(final String id, String name){

        final AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.update_dialog, null);

        mDialog.setView(mDialogView);

        //create views refernces
        final EditText etUpdateName = mDialogView.findViewById(R.id.etUpdateName);
        final EditText etUpdateCourse = mDialogView.findViewById(R.id.etUpdateCourse);
        final EditText etUpdateYear = mDialogView.findViewById(R.id.etUpdateYear);
        final EditText etUpdateAge = mDialogView.findViewById(R.id.etUpdateAge);



        Button btnUpdate = mDialogView.findViewById(R.id.btnUpdate);
        Button btnDelete = mDialogView.findViewById(R.id.btnDelete);

        mDialog.setTitle("Updating " + name +" record");

        final AlertDialog alertDialog = mDialog.create();
        alertDialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here we will update data in database
                //now get values from view

                String newName = etUpdateName.getText().toString();
                String newCourse = etUpdateCourse.getText().toString();
                String newYear = etUpdateYear.getText().toString();
                String newAge = etUpdateAge.getText().toString();



                updateData(id,newName,newCourse,newYear,newAge);

                Toast.makeText(RetreiveDataActivity.this, "Record Updated", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord(id);

                alertDialog.dismiss();
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void deleteRecord(String id){
        //create reference to database
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("Students").child(id);
        //we referencing child here because we will be delete one record not whole data data in database
        //we will use generic Task here so lets do it..

        Task<Void> mTask = DbRef.removeValue();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showToast("Deleted");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showToast("Error deleting record");
            }
        });
    }

    private void updateData(String id, String name, String course, String year, String age){

        //creating database reference
        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("Students").child(id);
        Students students = new Students(id, name, course, year, age);
        DbRef.setValue(students);
    }

}