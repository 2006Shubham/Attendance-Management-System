package com.shubhamdeshmukh.newattendancemanagement.recycleradapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    private final ArrayList<Category> categoryArrayList;
    private final Context context;
    private final OnItemClickListener listener; // Listener for click events

    public CategoryAdapter(ArrayList<Category> categoryArrayList, Context context, OnItemClickListener listener) {
        this.categoryArrayList = categoryArrayList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_category_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryArrayList.get(position);
        holder.categoryName.setText(category.getCategoryName());

        // Set the click listener if provided
        if (listener != null) {
            holder.itemView.setOnClickListener(v -> listener.onItemClicked(position));
        } else {
            holder.itemView.setOnClickListener(null); // Disable click
        }
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
        }
    }
}
