package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacher;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.entities.Class;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;

import java.util.ArrayList;

public class ClassSelectionRecyclerAdapter extends RecyclerView.Adapter<ClassSelectionRecyclerAdapter.ViewHolder> {

    ArrayList<Class> classArrayList;

    Context context;

    static int currentExpandedPosition = -1;

    ClassSelectionRecyclerAdapter(Context context,ArrayList<Class>classArrayList){

        this.classArrayList = classArrayList;
        this.context = context;


    }

    public static void callAttendanceViewActivityWithBatch(int batchIndex) {
        CategorySelectionRecylerAdapter.callAttendanceViewActivityWithClass(currentExpandedPosition, batchIndex); // Sending Current Class Index & Batch Index
    }

    @NonNull
    @Override
    public ClassSelectionRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(context).inflate(R.layout.layout_class_list_recycler,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return  viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ClassSelectionRecyclerAdapter.ViewHolder holder, int position) {


        holder.className.setText(classArrayList.get(position).getName());

        Intent intent = new Intent(context,AttendanceViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        holder.batchlist_recycler.setLayoutManager(new LinearLayoutManager(context));
        holder.batchlist_recycler.setAdapter(new BatchSelectionRecyclerAdapter(classArrayList.get(position).getBatchList(),context));

        Log.d(MainActivity.TAG, "onBindViewHolder: Batch: " + classArrayList.get(position).getBatchList());


        holder.classLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                context.startActivity(intent);

            }
        });

        if (ClassSelectionRecyclerAdapter.currentExpandedPosition == position)
        {
            holder.batch_card.setVisibility(View.VISIBLE);

        }
        else {
            holder.batch_card.setVisibility(View.GONE);
        }

        holder.classLinearLayout.setOnClickListener(new View.OnClickListener() {

//            final Intent intent = new Intent(context, AttendanceViewActivity.class);

            @Override
            public void onClick(View view) {
                if (classArrayList.get(holder.getAdapterPosition()).getBatchList().isEmpty())
                {
                    currentExpandedPosition = holder.getAdapterPosition();
//                    context.startActivity(intent);
                    ClassSelectionRecyclerAdapter.callAttendanceViewActivityWithBatch(-1);
                }
                else
                {
                    if (ClassSelectionRecyclerAdapter.currentExpandedPosition == holder.getAdapterPosition())
                    {
                        ClassSelectionRecyclerAdapter.currentExpandedPosition = -1;
                    }
                    else {
                        ClassSelectionRecyclerAdapter.currentExpandedPosition = holder.getAdapterPosition();
                    }
                    notifyDataSetChanged();
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return classArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView className;
        RecyclerView batchlist_recycler;
        LinearLayout classLinearLayout,batch_card;

        LinearLayout class_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            classLinearLayout = itemView.findViewById(R.id.class_card);
            className = itemView.findViewById(R.id.classname);
            batchlist_recycler = itemView.findViewById(R.id.batch_list_recycler);
            batch_card = itemView.findViewById(R.id.batch_list);

        }
    }
}
