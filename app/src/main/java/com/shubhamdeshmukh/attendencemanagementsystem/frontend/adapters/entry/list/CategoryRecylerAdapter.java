package com.shubhamdeshmukh.attendencemanagementsystem.frontend.adapters.entry.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Category;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Class;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.models.ClassSelection;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.adapters.selection.list.ClassRecyclerAdapter;
import com.shubhamdeshmukh.attendencemanagementsystem.frontend.teacher.CategorySelectionActivity;

import java.util.ArrayList;

public class CategoryRecylerAdapter extends RecyclerView.Adapter<CategoryRecylerAdapter.ViewHolder> {

    Context context;
    ArrayList<Category>categoryarrayList;

    static int currentExpandedPosition = -1;

    public CategoryRecylerAdapter(Context context, ArrayList<Category> categoryarrayList){

        this.categoryarrayList = categoryarrayList;
        this.context = context;

    }

    public static void callAttendanceViewActivityWithClass(int classIndex, int batchIndex) {
        CategorySelectionActivity.callAttendanceViewActivityWithCategory(currentExpandedPosition, classIndex, batchIndex); // Sending Current Category Index, Class Index & Batch Index
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v =  LayoutInflater.from(context).inflate(R.layout.layout_category_recycler,parent,false);
       ViewHolder viewHolder = new ViewHolder(v);
       return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.categoryName.setText(categoryarrayList.get(position).getName());

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));

        ArrayList<ClassSelection> classSelectionArrayList = new ArrayList<>();

        for (Class _class:
                categoryarrayList.get(position).getClassList()) {
            classSelectionArrayList.add(new ClassSelection(_class, false));
        }
        holder.recyclerView.setAdapter(new ClassRecyclerAdapter(context, classSelectionArrayList));

        if (CategoryRecylerAdapter.currentExpandedPosition == holder.getAdapterPosition())
            holder.innerclassLayout.setVisibility(View.VISIBLE);
        else
            holder.innerclassLayout.setVisibility(View.GONE);

        holder.categoryCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (CategoryRecylerAdapter.currentExpandedPosition == holder.getAdapterPosition())
                {
                    CategoryRecylerAdapter.currentExpandedPosition = -1;
                }
                else
                {
                    CategoryRecylerAdapter.currentExpandedPosition = holder.getAdapterPosition();
                }
                notifyDataSetChanged();
            }
        });

    }



    @Override
    public int getItemCount() {
        return categoryarrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;
        CardView categoryCard;
        LinearLayout innerclassLayout;
        RecyclerView recyclerView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.category);
            categoryCard = itemView.findViewById(R.id.categorycard);
            innerclassLayout = itemView.findViewById(R.id.class_list);
            recyclerView = itemView.findViewById(R.id.class_list_recycler);

        }
    }

}
