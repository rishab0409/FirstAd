package com.example.firstad;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class mystatsfrag extends Fragment {

    RecyclerView recyclerView2;
    private statsAdapter adapter2;
    String uid;
    public mystatsfrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mystatsfrag, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Stats");

        recyclerView2=(RecyclerView)view.findViewById(R.id.recycler2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseRecyclerOptions<stats> options2 =
                new FirebaseRecyclerOptions.Builder<stats>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users").child(uid), stats.class)
                        .build();
        adapter2=new statsAdapter(options2);
        recyclerView2.setAdapter(adapter2);




    return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter2.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter2.stopListening();
    }

}
