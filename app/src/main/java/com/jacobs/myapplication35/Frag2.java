package com.jacobs.myapplication35;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public  class Frag2 extends  Fragment {


    public  Frag2 () {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag2, container, false);

        FloatingActionButton fab = view.findViewById(R.id.fab);

        return inflater.inflate(R.layout.frag2, container, false);
    }
}