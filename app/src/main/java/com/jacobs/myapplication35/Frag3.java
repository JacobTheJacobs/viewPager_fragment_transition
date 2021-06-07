package com.jacobs.myapplication35;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public  class Frag3 extends  Fragment implements View.OnClickListener {

    private static final String TAG = "SecondFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag3, container, false);

        Button btn = (Button) view.findViewById(R.id.btn);

        btn.setOnClickListener(this);



        return view;
    }


    @Override
    public void onClick(View view) {
        FragmentTransaction trans = getFragmentManager()
                .beginTransaction();
        trans.replace(R.id.root_frame, new Frag1());
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);
        trans.commit();
    }
}