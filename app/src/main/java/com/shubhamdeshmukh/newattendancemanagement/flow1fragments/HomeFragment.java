package com.shubhamdeshmukh.newattendancemanagement.flow1fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shubhamdeshmukh.newattendancemanagement.R;
import com.shubhamdeshmukh.newattendancemanagement.adaptermodelclass.Summary;
import com.shubhamdeshmukh.newattendancemanagement.recycleradapters.SummaryAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    RecyclerView recyclerView;
    ArrayList<Summary> summaryArrayList;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.summaryRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        summaryArrayList = new ArrayList<>();

        summaryArrayList.add(new Summary("Computer Network","6S402","Lecture","Date : 1-1-2025","9:40 AM"));
        summaryArrayList.add(new Summary("Computer Network","6S402","Lecture","Date : 1-1-2025","9:40 AM"));
        summaryArrayList.add(new Summary("Computer Network","6S402","Lecture","Date : 1-1-2025","9:40 AM"));
        summaryArrayList.add(new Summary("Computer Network","6S402","Lecture","Date : 1-1-2025","9:40 AM"));
        summaryArrayList.add(new Summary("Computer Network","6S402","Lecture","Date : 1-1-2025","9:40 AM"));
        summaryArrayList.add(new Summary("Computer Network","6S402","Lecture","Date : 1-1-2025","9:40 AM"));
        summaryArrayList.add(new Summary("Computer Network","6S402","Lecture","Date : 1-1-2025","9:40 AM"));
        summaryArrayList.add(new Summary("Computer Network","6S402","Lecture","Date : 1-1-2025","9:40 AM"));

        SummaryAdapter summaryAdapter = new SummaryAdapter(getContext(),summaryArrayList);

        recyclerView.setAdapter(summaryAdapter);


    }
}