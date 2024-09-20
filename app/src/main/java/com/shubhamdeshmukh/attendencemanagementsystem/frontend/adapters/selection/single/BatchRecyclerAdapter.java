package com.shubhamdeshmukh.attendencemanagementsystem.frontend.adapters.selection.single;

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

public class BatchRecyclerAdapter extends RecyclerView.Adapter<BatchRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<BatchSelection> batchSelectionArrayList;

//    RegisterClassInfoAndBatchesActivity parent;

    public BatchRecyclerAdapter(Context context, ArrayList<BatchSelection> batchSelectionArrayList){

        this.context = context;
        this.batchSelectionArrayList = batchSelectionArrayList;
        Log.d(MainActivity.TAG, String.valueOf(this.batchSelectionArrayList.size()));
//        this.parent = parent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View v  =  LayoutInflater.from(context).inflate(R.layout.layout_batch_list_recycler,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return  viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.batch_name.setText(batchSelectionArrayList.get(holder.getBindingAdapterPosition()).getBatch().getName());
        holder.checkBox.setChecked(batchSelectionArrayList.get(holder.getBindingAdapterPosition()).isSelected());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                batchSelectionArrayList.get(holder.getBindingAdapterPosition()).setSelected(holder.checkBox.isChecked());
            }
        });

        holder.batch_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                parent.showBatchInfoDialog(holder.getBindingAdapterPosition());
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
