package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacher;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Batch;

import java.util.ArrayList;

public class BatchSelectionRecyclerAdapter extends RecyclerView.Adapter<BatchSelectionRecyclerAdapter.ViewHolder> {

    ArrayList<Batch>batchArrayList;
    Context context;

    public BatchSelectionRecyclerAdapter(ArrayList<Batch>batchArrayList,Context context){

        this.batchArrayList = batchArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public BatchSelectionRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v =  LayoutInflater.from(context).inflate(R.layout.layout_batch_list_recycler,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BatchSelectionRecyclerAdapter.ViewHolder holder, int position) {


        holder.batchname.setText(batchArrayList.get(position).getName());
        holder.batch_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,AttendanceViewActivity.class);
                context.startActivity(intent);


            }
        });



    }

    @Override
    public int getItemCount() {
        return batchArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout batch_layout ;
        TextView batchname ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            batch_layout =  itemView.findViewById(R.id.batch_card);
            batchname = itemView.findViewById(R.id.batchname);
        }
    }
}
