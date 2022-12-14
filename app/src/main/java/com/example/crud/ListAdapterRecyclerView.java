package com.example.crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class ListAdapterRecyclerView extends RecyclerView.Adapter<ListAdapterRecyclerView.MyViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private RecyclerItemClickListener clickListener;
    ArrayList<Students> studentModelArrayList;

    CardView cardView;


    Context context;

    public ListAdapterRecyclerView(Context context, ArrayList<Students> studentModelArrayList) {
        this.studentModelArrayList = studentModelArrayList;
        this.context = context;
    }

    public void setFilteredList(ArrayList<Students> filteredList) {
        this.studentModelArrayList = filteredList;
        notifyDataSetChanged();
    }


    public ListAdapterRecyclerView(ArrayList<Students> studentModelArrayList, RecyclerItemClickListener clickListener) {
        this.studentModelArrayList = studentModelArrayList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_card_view, parent, false);
        cardView = v.findViewById(R.id.card_view);


//        cardView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Students students = studentModelArrayList.get(positionclick);
//                String studId = students.getId();
//                Toast.makeText(context, "gumana", Toast.LENGTH_SHORT).show();
//                AlertDialog.Builder builderTransaction = new AlertDialog.Builder(context);
//                builderTransaction.setMessage("" + studId);
//                builderTransaction.setCancelable(false);
//                builderTransaction.setPositiveButton("Update",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog,
//                                                int which) {
//                                Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show();
//                                dialog.dismiss();
//                            }
//                        });
//                builderTransaction.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
//                        dialogInterface.dismiss();
//                    }
//                });
//                AlertDialog alertTransaction = builderTransaction.create();
//                alertTransaction.show();
//
//
//                return true;
//            }
//        });
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Students studentModel = studentModelArrayList.get(position);


        holder.name.setText(studentModel.getName());
        holder.course.setText(studentModel.getCourse());
        holder.year.setText(studentModel.getYear());
        holder.age.setText(studentModel.getAge());

    }

    @Override
    public int getItemCount() {
        return studentModelArrayList.size();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, course, year, age;


        MyViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.tvName);
            course = itemView.findViewById(R.id.tvCourse);
            year = itemView.findViewById(R.id.tvYear);
            age = itemView.findViewById(R.id.tvAge);

            ButterKnife.bind(this, itemView);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickListener.onLongItemClick(v, getAdapterPosition());
                    return true;
                }
            });
        }
    }

}