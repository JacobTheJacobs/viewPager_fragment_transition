package com.jacobs.myapplication35;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Random;


public  class Frag4 extends  Fragment  implements IOnBackPressed{


    EditText name_input, text_input;
    LinearLayout delete_button,sharebutton,randomButton;

    String id, name, lyric, updated_on;


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

    public Frag4(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag4, container, false);


        NestedScrollView frag4View = view.findViewById(R.id.frag4View);
        name_input = view.findViewById(R.id.name_input2);
        text_input = view.findViewById(R.id.text_input2);

        delete_button = view.findViewById(R.id.deleteButton);
        sharebutton=view.findViewById(R.id.shareButton);
        randomButton=view.findViewById(R.id.randomButton);

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
            frag4View.setBackground(draw);
            frag4View.getBackground().setAlpha(0);
            delete_button.getBackground().setAlpha(255);
            delete_button.setBackground(draw);

            sharebutton.getBackground().setAlpha(255);
            sharebutton.setBackground(draw);

            randomButton.getBackground().setAlpha(255);
            randomButton.setBackground(draw);

        }else{

            name_input.setBackground(draw);
            name_input.getBackground().setAlpha(70);
            text_input.setBackground(draw);
            text_input.getBackground().setAlpha(70);
            frag4View.setBackground(draw);
            frag4View.getBackground().setAlpha(50);


        }


        delete_button.setAlpha(0f);
        delete_button.setTranslationY(5);
        delete_button.animate().alpha(1f).translationYBy(-5).setDuration(300);

        randomButton.setAlpha(0f);
        randomButton.setTranslationY(5);
        randomButton.animate().alpha(1f).translationYBy(-5).setDuration(300);

        sharebutton.setAlpha(0f);
        sharebutton.setTranslationY(5);
        sharebutton.animate().alpha(1f).translationYBy(-5).setDuration(300);


        //First we call this
        savedInstanceState=this.getArguments();
        getAndSetIntentData(savedInstanceState);


        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmDialog();
            }

        });

        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,name_input.getText().toString());
                String app_url = " https://play.google.com/store/apps/details?id=my.example.javatpoint";
                if(text_input.getText().toString().length()>0){
                    app_url=text_input.getText().toString();
                }
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });


        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text_input.setText(text_input.getText().toString()+"!!!!");
            }

        });




    requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (text_input.length() > 0 || name_input.length()>0) {
                    if (text_input.length() > 0 || name_input.length()>0) {
                        MyDatabaseHelper myDB = new MyDatabaseHelper(mContext);
                        name = name_input.getText().toString().trim();
                        lyric = text_input.getText().toString().trim();
                        myDB.updateData(id, name, lyric);
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

    void getAndSetIntentData(Bundle  bundle ){
        if (bundle != null) {


            id = bundle.getString("id");
            name = bundle.getString("name");
            lyric = bundle.getString("text");

            System.out.println(id);
            System.out.println(name);
            System.out.println(lyric);

            //Setting Intent Data
            name_input.setText(name);
            text_input.setText(lyric);
            Log.d("stev", name+" "+lyric);
        }else{
            Toast.makeText(getContext(), "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("למחוק " + name + " ?");
        builder.setMessage("בטוח למחוק את " + name + " ?");
        builder.setPositiveButton("כן", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(getContext());
                myDB.deleteOneRow(id);
                changefragment();
            }
        });
        builder.setNegativeButton("לא", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    public void changefragment(){
        fm.popBackStack();

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}