package com.example.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RetreiveDataSearchActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Students> studentsList;
    SearchView studentSearch;
    ListAdapterRecyclerView listAdapter;

    DatabaseReference studentDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_data_search);

        recyclerView = findViewById(R.id.myListView);
        studentSearch = findViewById(R.id.student_search);
        studentsList = new ArrayList<>();

        listAdapter = new ListAdapterRecyclerView(this, studentsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

        studentDbRef = FirebaseDatabase.getInstance().getReference("Students");


        studentDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentsList.clear();

                for (DataSnapshot studentDatasnap : dataSnapshot.getChildren()){
                    Students students = studentDatasnap.getValue(Students.class);
                    studentsList.add(students);
                }

                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        studentSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileteredList(newText);
                return true;
            }
        });


        registerForContextMenu(recyclerView);


        //set itemLong listener on listview item

    }

    private void fileteredList(String newText) {
        ArrayList<Students> searchstudents = new ArrayList<>();

        for(Students students : studentsList){
            if (students.getName().toLowerCase().contains(newText.toLowerCase())){
                searchstudents.add(students);
            }
        }
        if (searchstudents.isEmpty()){
            listAdapter.setFilteredList(searchstudents);
            Toast.makeText(this, "Student not found!", Toast.LENGTH_LONG).show();
        }else{
            listAdapter.setFilteredList(searchstudents);
        }
    }
}