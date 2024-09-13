package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Batch;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.models.BatchSelection;

import java.util.ArrayList;

public class BatchRegisterRecyclerAdapter extends RecyclerView.Adapter<BatchRegisterRecyclerAdapter.ViewHolder> {

    public ArrayList<Batch> getBatchArrayList() {
        return batchArrayList;
    }

    ArrayList<Batch> batchArrayList;
    Context context;

    public BatchRegisterRecyclerAdapter(ArrayList<Batch> batchArrayList, Context context){

        this.batchArrayList = batchArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v =  LayoutInflater.from(context).inflate(R.layout.layout_register_batch_recycler,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.batchname.setText(batchArrayList.get(position).getName());
        holder.batch_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(context, AttendanceViewActivity.class);
//                context.startActivity(intent);
//                ClassSelectionRecyclerAdapter.callAttendanceViewActivityWithBatch(holder.getAdapterPosition()); // Sending Current Batch Index

                if (context instanceof RegisterClassInfoAndBatchesActivity)
                {
                    ((RegisterClassInfoAndBatchesActivity) context).showBatchInfoDialog(holder.getAdapterPosition());
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return batchArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView batch_layout ;
        TextView batchname ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            batch_layout =  itemView.findViewById(R.id.register_batch);
            batchname = itemView.findViewById(R.id.batchname);
        }
    }
}
