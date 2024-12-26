package com.shubhamdeshmukh.newattendancemanagement.recycleradapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Batch;

import java.util.ArrayList;

public class BatchAdapter extends RecyclerView.Adapter<BatchAdapter.ViewHolder> {

    ArrayList<Batch> batchArrayList;
    Context context;

    Boolean showCheckBox ;

    public BatchAdapter(ArrayList<Batch> batchArrayList, Context context, boolean showCheckBox) {
        this.batchArrayList = batchArrayList;
        this.context = context;
        this.showCheckBox = showCheckBox;
    }

    @NonNull
    @Override
    public BatchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_batch_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BatchAdapter.ViewHolder holder, int position) {

        Batch batch = batchArrayList.get(position);
        holder.batchName.setText(batch.getBatchName());

        if (showCheckBox){
            holder.checkBox.setVisibility(View.VISIBLE);
        }else {
            holder.checkBox.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return batchArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView batchName;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            batchName = itemView.findViewById(R.id.batchName);
            checkBox = itemView.findViewById(R.id.check);

        }
    }
}
