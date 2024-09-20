package com.shubhamdeshmukh.attendencemanagementsystem.frontend.adapters.entry.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubhamdeshmukh.attendencemanagementsystem.R;
import com.shubhamdeshmukh.attendencemanagementsystem.backend.database_entities.Class;

import java.util.ArrayList;

public class ClassRecyclerAdapter extends RecyclerView.Adapter<ClassRecyclerAdapter.ViewHolder> {

    ArrayList<Class> classes;
    Context context;

    ClassRecyclerAdapter(Context context, ArrayList<Class> classes){

        this.context = context;
        this.classes = classes;

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

        holder.textView.setText(classes.get(position).getName());

//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, RegisterClassInfoAndBatchesActivity.class);
//                intent.putExtra("classFetchedDataIndex", holder.getBindingAdapterPosition());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name);

        }
    }
}
