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
import com.shubhamdeshmukh.attendencemanagementsystem.backend.models.BatchSelection;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;

import java.util.ArrayList;

public class BatchRegisterRecyclerAdapter extends RecyclerView.Adapter<BatchRegisterRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<BatchSelection> batchSelectionArrayList;

    RegisterClassInfoAndBatchesActivity parent;

    BatchRegisterRecyclerAdapter(Context context, ArrayList<BatchSelection> batchSelectionArrayList, RegisterClassInfoAndBatchesActivity parent){

        this.context = context;
        this.batchSelectionArrayList = batchSelectionArrayList;
        Log.d(MainActivity.TAG, String.valueOf(BatchRegisterRecyclerAdapter.this.batchSelectionArrayList.size()));
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

        holder.batch_name.setText(batchSelectionArrayList.get(holder.getAdapterPosition()).getBatch().getName());
        holder.checkBox.setChecked(batchSelectionArrayList.get(holder.getAdapterPosition()).isSelected());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                batchSelectionArrayList.get(holder.getAdapterPosition()).setSelected(holder.checkBox.isChecked());
            }
        });

        holder.batch_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.showBatchInfoDialog(holder.getAdapterPosition());
            }
        });
    }

    public ArrayList<BatchSelection> getBatchSelectionArrayList() {
        return batchSelectionArrayList;
    }

    @Override
    public int getItemCount() {
        return batchSelectionArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView batch_name;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            batch_name  = itemView.findViewById(R.id.name);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
