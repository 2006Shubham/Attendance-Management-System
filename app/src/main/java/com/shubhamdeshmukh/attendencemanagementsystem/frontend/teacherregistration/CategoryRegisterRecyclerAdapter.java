package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Category;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;

import java.util.ArrayList;

public class CategoryRegisterRecyclerAdapter extends RecyclerView.Adapter<CategoryRegisterRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<Category> categoryArrayList;

    RegisterSubjectInfoAndCategoryActivity parent;

    CategoryRegisterRecyclerAdapter(Context context, ArrayList<Category> categoryArrayList, RegisterSubjectInfoAndCategoryActivity parent){

        this.context = context;
        this.categoryArrayList = categoryArrayList;
        Log.d(MainActivity.TAG, String.valueOf(categoryArrayList.size()));
        this.parent = parent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View v  =  LayoutInflater.from(context).inflate(R.layout.layout_class_and_subject_category_register_recycler,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return  viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.category_name.setText(categoryArrayList.get(position).getName());

        holder.category_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.showCategoryInfoDialog(holder.getBindingAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView category_name ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            category_name  = itemView.findViewById(R.id.name);
        }
    }
}
