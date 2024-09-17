package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.models.CategorySelection;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;

import java.util.ArrayList;

public class CategorySelectionRecyclerAdapter extends RecyclerView.Adapter<CategorySelectionRecyclerAdapter.ViewHolder> {

    Context context;
    private ArrayList<CategorySelection> categorySelectionArrayList;

    RegisterSubjectInfoAndCategoryActivity parent;

    CategorySelectionRecyclerAdapter(Context context, ArrayList<CategorySelection> categorySelectionArrayList, RegisterSubjectInfoAndCategoryActivity parent){

        this.context = context;
        this.categorySelectionArrayList = categorySelectionArrayList;
        Log.d(MainActivity.TAG, String.valueOf(categorySelectionArrayList.size()));
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

        holder.category_name.setText(categorySelectionArrayList.get(position).getCategory().getName());

        holder.category_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.showCategoryInfoDialog(holder.getBindingAdapterPosition());
            }
        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categorySelectionArrayList.get(holder.getBindingAdapterPosition()).setSelected(holder.checkBox.isChecked());
            }
        });
    }

    public ArrayList<CategorySelection> getCategorySelectionArrayList() {
        return categorySelectionArrayList;
    }

    @Override
    public int getItemCount() {
        return categorySelectionArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView category_name;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            category_name  = itemView.findViewById(R.id.name);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
