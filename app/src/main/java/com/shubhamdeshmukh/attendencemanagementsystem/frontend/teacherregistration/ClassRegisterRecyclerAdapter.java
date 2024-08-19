package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Class;

import java.util.ArrayList;

public class ClassRegisterRecyclerAdapter extends RecyclerView.Adapter<ClassRegisterRecyclerAdapter.ViewHolder> {

    ArrayList<Class> classes;
    Context context;

    ClassRegisterRecyclerAdapter(Context context, ArrayList<Class> classes){

        this.context = context;
        this.classes = classes;

    }



    @NonNull
    @Override
    public ClassRegisterRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_class_and_subject_category_register_recycler, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ClassRegisterRecyclerAdapter.ViewHolder holder, int position) {

        holder.textView.setText(classes.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name);

        }
    }
}
