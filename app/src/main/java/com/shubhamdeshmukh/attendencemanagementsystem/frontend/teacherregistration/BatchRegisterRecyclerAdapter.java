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
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Batch;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;

import java.util.ArrayList;

public class BatchRegisterRecyclerAdapter extends RecyclerView.Adapter<BatchRegisterRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<Batch> batchArrayList;

    RegisterClassInfoAndBatchesActivity parent;

    BatchRegisterRecyclerAdapter(Context context, ArrayList<Batch> batchArrayList, RegisterClassInfoAndBatchesActivity parent){

        this.context = context;
        this.batchArrayList = batchArrayList;
        Log.d(MainActivity.TAG, String.valueOf(BatchRegisterRecyclerAdapter.this.batchArrayList.size()));
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

        holder.batch_name.setText(batchArrayList.get(position).getName());

        holder.batch_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.showBatchInfoDialog(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return batchArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView batch_name ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            batch_name  = itemView.findViewById(R.id.name);
        }
    }
}
