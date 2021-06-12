package com.jacobs.myapplication35;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


public  class Frag4 extends  Fragment{


    EditText name_input, text_input;
    Button update_button, delete_button;

    String id, name, lyric, updated_on;

    public Frag4(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag4, container, false);


        name_input = view.findViewById(R.id.name_input2);
        text_input = view.findViewById(R.id.text_input2);

        update_button = view.findViewById(R.id.update_button);
        delete_button = view.findViewById(R.id.delete_button);

        //First we call this
        savedInstanceState=this.getArguments();
        getAndSetIntentData(savedInstanceState);


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(getContext());
                name = name_input.getText().toString().trim();
                lyric = text_input.getText().toString().trim();
                myDB.updateData(id, name, lyric);
                getFragmentManager().popBackStack();
            }

        });


        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
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
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(getContext());
                myDB.deleteOneRow(id);
                getFragmentManager().popBackStack();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

}