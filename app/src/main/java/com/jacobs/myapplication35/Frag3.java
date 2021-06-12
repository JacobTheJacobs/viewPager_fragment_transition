package com.jacobs.myapplication35;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Random;


public  class Frag3 extends  Fragment{


    EditText name_input, text_input;
    Button add_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag3, container, false);


        ConstraintLayout frag1View = view.findViewById(R.id.frag3View);

        Random mRandom = new Random();
        int baseColor = Color.WHITE;

        int baseRed = Color.red(baseColor);
        int baseGreen = Color.green(baseColor);
        int baseBlue = Color.blue(baseColor);

        int red = (baseRed + mRandom.nextInt(256)) / 2;
        int green = (baseGreen + mRandom.nextInt(256)) / 2;
        int blue = (baseBlue + mRandom.nextInt(256)) / 2;

        GradientDrawable draw = new GradientDrawable();

        draw.setColor(Color.rgb(red,green,blue));

        frag1View.setBackground(draw);

        name_input = view.findViewById(R.id.name_input);
        text_input = view.findViewById(R.id.text_input);

        add_button = view.findViewById(R.id.add_button);

        add_button.setAlpha(0f);
        add_button.setTranslationY(50);
        add_button.animate().alpha(1f).translationYBy(-50).setDuration(1500);


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