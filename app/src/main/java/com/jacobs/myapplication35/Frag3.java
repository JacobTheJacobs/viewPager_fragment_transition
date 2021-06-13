package com.jacobs.myapplication35;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Random;


public  class Frag3 extends  Fragment implements IOnBackPressed{


    // Declare Context variable at class level in Fragment
    private Context mContext;
    private FragmentManager fm;





    // Initialise it from onAttach()
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        this.fm = getFragmentManager();

    }

    @Override
    public boolean onBackPressed() {

        if (text_input.length() > 0 || name_input.length()>0 ) {


            return true;
        } else {
            return false;
        }
    }

    EditText name_input, text_input;
    Button add_button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag3, container, false);


        NestedScrollView frag1View = (NestedScrollView )view.findViewById(R.id.frag3View);

        LinearLayout cardView = (LinearLayout )view.findViewById(R.id.cardViewcreate);

        name_input = view.findViewById(R.id.name_input);
        text_input = view.findViewById(R.id.text_input);





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

        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){


            name_input.setBackground(draw);
            name_input.getBackground().setAlpha(0);
            text_input.setBackground(draw);
            text_input.getBackground().setAlpha(0);
            frag1View.setBackground(draw);
            frag1View.getBackground().setAlpha(0);

        }else{

            name_input.setBackground(draw);
            name_input.getBackground().setAlpha(70);
            text_input.setBackground(draw);
            text_input.getBackground().setAlpha(70);
            frag1View.setBackground(draw);
            frag1View.getBackground().setAlpha(50);
        }



        LinearLayout randomLayotButton = (LinearLayout )view.findViewById(R.id.randomLyric);

        randomLayotButton.setAlpha(0f);
        randomLayotButton.setTranslationY(5);
        randomLayotButton.animate().alpha(1f).translationYBy(-5).setDuration(300);


        randomLayotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text_input.setText(text_input.getText().toString()+"!!!!");
            }

        });

/*
        add_button = view.findViewById(R.id.add_button);
randomLyric
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

*/
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (text_input.length() > 0 || name_input.length()>0) {
                    if (text_input.length() > 0 || name_input.length()>0) {
                        MyDatabaseHelper myDB = new MyDatabaseHelper(mContext);
                        myDB.addLyric(name_input.getText().toString().trim(),
                                text_input.getText().toString().trim());
                        changefragment();


                    }
                } else {
                    // If you want to get default implementation of onBackPressed, use this
                    this.remove();
                    requireActivity().onBackPressed();
                }
            }
        });

        return view;
    }

    public void changefragment(){

        fm.popBackStack();

    }

}