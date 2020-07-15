package com.example.firstad;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class account extends Fragment {


    public account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_account, container, false);
    //  getActivity().getActionBar().setTitle("My Account");

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Account");
        return  view;
    }

}
