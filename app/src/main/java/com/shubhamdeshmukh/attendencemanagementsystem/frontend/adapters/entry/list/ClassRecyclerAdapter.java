package com.shubhamdeshmukh.attendencemanagementsystem.frontend.adapters.entry.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Batch;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Class;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.models.BatchSelection;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.adapters.entry.single.BatchRecyclerAdapter;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration.RegisterAddClassesAndSubjectsActivity;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration.RegisterClassInfoAndBatchesActivity;

import java.util.ArrayList;

public class ClassRecyclerAdapter extends RecyclerView.Adapter<ClassRecyclerAdapter.ViewHolder> {

    ArrayList<Class> classes;
    Context context;
    static int currentExpandedPosition = -1;

    ClassRecyclerAdapter(Context context, ArrayList<Class> classes){

        this.context = context;
        this.classes = classes;

    }


    public static void callAttendanceViewActivityWithBatch(int batchIndex) {
        CategoryRecylerAdapter.callAttendanceViewActivityWithClass(currentExpandedPosition, batchIndex); // Sending Current Class Index & Batch Index
    }

    @NonNull
    @Override
    public ClassRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_class_list_recycler, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ClassRecyclerAdapter.ViewHolder holder, int position) {

        holder.className.setText(classes.get(position).getName());

        if (currentExpandedPosition == position)
        {
            holder.batch_card.setVisibility(View.VISIBLE);

        }
        else {
            holder.batch_card.setVisibility(View.GONE);
        }

        ArrayList<Batch> batchArrayList =  classes.get(position).getBatchList();


        holder.batchlist_recycler.setLayoutManager(new LinearLayoutManager(context));
        holder.batchlist_recycler.setAdapter(new BatchRecyclerAdapter(batchArrayList, context));


        holder.className.setOnClickListener(new View.OnClickListener() {

//            final Intent intent = new Intent(context, AttendanceViewActivity.class);

            @Override
            public void onClick(View view) {

                if (classes.get(holder.getBindingAdapterPosition()).getBatchList().isEmpty())
                {
                    currentExpandedPosition = holder.getBindingAdapterPosition();

                     callAttendanceViewActivityWithBatch(-1);
                }
                else
                {
                    if (currentExpandedPosition == holder.getBindingAdapterPosition())
                    {
                        currentExpandedPosition = -1;
                    }
                    else {
                        currentExpandedPosition = holder.getBindingAdapterPosition();
                    }
                    notifyDataSetChanged();
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView className;
        RecyclerView batchlist_recycler;
        LinearLayout batch_card;

        LinearLayout class_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.classname);
            batchlist_recycler = itemView.findViewById(R.id.batch_list_recycler);
            batch_card = itemView.findViewById(R.id.batch_list);

        }
    }
}
