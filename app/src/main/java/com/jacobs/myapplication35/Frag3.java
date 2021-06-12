package com.jacobs.myapplication35;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public  class Frag3 extends  Fragment{


    EditText name_input, text_input;
    Button add_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag3, container, false);


        name_input = view.findViewById(R.id.name_input);
        text_input = view.findViewById(R.id.text_input);

        add_button = view.findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(getContext());
                myDB.addLyric(name_input.getText().toString().trim(),
                        text_input.getText().toString().trim());
                getFragmentManager().popBackStack();
            }

        });

        return view;
    }



}