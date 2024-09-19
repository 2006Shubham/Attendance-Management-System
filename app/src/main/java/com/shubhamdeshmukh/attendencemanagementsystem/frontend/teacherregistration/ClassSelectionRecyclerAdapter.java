package com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacherregistration;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.shubhamdeshmukh.attendencemanagementsystem.backend.models.ClassSelection;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.MainActivity;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacher.AttendanceViewActivity;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacher.CategorySelectionRecylerAdapter;

import java.util.ArrayList;

public class ClassSelectionRecyclerAdapter extends RecyclerView.Adapter<ClassSelectionRecyclerAdapter.ViewHolder> {

    ArrayList<ClassSelection> classSelectionArrayList;

    Context context;

    static int currentExpandedPosition = -1;

    public ClassSelectionRecyclerAdapter(Context context, ArrayList<ClassSelection> classSelectionArrayList){

        this.classSelectionArrayList = classSelectionArrayList;
        this.context = context;


    }

    public static void callAttendanceViewActivityWithBatch(int batchIndex) {
        CategorySelectionRecylerAdapter.callAttendanceViewActivityWithClass(currentExpandedPosition, batchIndex); // Sending Current Class Index & Batch Index
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(context).inflate(R.layout.layout_category_info_class_selection_recycler,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return  viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.className.setText(classSelectionArrayList.get(position).getThisClass().getName());

        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classSelectionArrayList.get(holder.getBindingAdapterPosition()).setSelected(holder.checkbox.isChecked());
                Log.d(MainActivity.TAG, "onClick: SET" + holder.checkbox.isChecked());
            }
        });

//        Intent intent = new Intent(context, AttendanceViewActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        ArrayList<Batch> batchArrayList =  classSelectionArrayList.get(position).getThisClass().getBatchList();

        classSelectionArrayList.get(position).setBatchSelectionArrayList(new ArrayList<>());
        for (Batch batch:
             batchArrayList) {
            classSelectionArrayList.get(position).getBatchSelectionArrayList().add(new BatchSelection(batch, false));
        }

        holder.batchlist_recycler.setLayoutManager(new LinearLayoutManager(context));
        holder.batchlist_recycler.setAdapter(new BatchSelectionRecyclerAdapter(context, classSelectionArrayList.get(position).getBatchSelectionArrayList()));

        Log.d(MainActivity.TAG, "onBindViewHolder: Batch: " + classSelectionArrayList.get(position).getThisClass().getBatchList());


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
                if (context instanceof RegisterAddClassesAndSubjectsActivity)
                {
                    Intent intent = new Intent(context, RegisterClassInfoAndBatchesActivity.class);
                    intent.putExtra("classFetchedDataIndex", holder.getBindingAdapterPosition());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                else
                {
                    if (classSelectionArrayList.get(holder.getBindingAdapterPosition()).getThisClass().getBatchList().isEmpty())
                    {
                        currentExpandedPosition = holder.getBindingAdapterPosition();
//                    context.startActivity(intent);
                        // ClassSelectionRecyclerAdapter.callAttendanceViewActivityWithBatch(-1);
                    }
                    else
                    {
                        if (ClassSelectionRecyclerAdapter.currentExpandedPosition == holder.getBindingAdapterPosition())
                        {
                            ClassSelectionRecyclerAdapter.currentExpandedPosition = -1;
                        }
                        else {
                            ClassSelectionRecyclerAdapter.currentExpandedPosition = holder.getBindingAdapterPosition();
                        }
                        notifyDataSetChanged();
                    }
                }
            }
        });



    }

    public ArrayList<ClassSelection> getClassSelectionArrayList() {
        return classSelectionArrayList;
    }

    @Override
    public int getItemCount() {
        return classSelectionArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView className;
        CheckBox checkbox;
        RecyclerView batchlist_recycler;
        LinearLayout classLinearLayout,batch_card;

        LinearLayout class_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            classLinearLayout = itemView.findViewById(R.id.class_card);
            className = itemView.findViewById(R.id.classname);
            checkbox = itemView.findViewById(R.id.checkbox);
            batchlist_recycler = itemView.findViewById(R.id.batch_list_recycler);
            batch_card = itemView.findViewById(R.id.batch_list);

        }
    }
}
