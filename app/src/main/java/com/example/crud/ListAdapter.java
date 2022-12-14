package com.example.crud;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.annotations.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<Students> studentsList;

    public ListAdapter(Activity mContext, List<Students> studentsList){
        super(mContext,R.layout.list_item,studentsList);
        this.mContext = mContext;
        this.studentsList = studentsList;
    }

    @NonNull
   // @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item,null,true);

        TextView tvName = listItemView.findViewById(R.id.tvName);
        TextView tvCourse = listItemView.findViewById(R.id.tvCourse);
        TextView tvYear = listItemView.findViewById(R.id.tvYear);
        TextView tvAge = listItemView.findViewById(R.id.tvAge);


        Students students = studentsList.get(position);

        tvName.setText(students.getName());
        tvCourse.setText(students.getCourse());
        tvYear.setText(students.getYear());
        tvAge.setText(students.getAge());


        return listItemView;
    }
}
