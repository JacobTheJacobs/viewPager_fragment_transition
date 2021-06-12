package com.jacobs.myapplication35;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public  class  Frag1  extends  Fragment {

    RecyclerView recyclerView;
    ImageView empty_imageview;
    TextView no_data;

    MyDatabaseHelper myDB;
    ArrayList<String> lyric_id, lyric_name, lyric_text, lyric_date;
    RecyclerViewAdapter customAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag1, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();

                trans.replace(R.id.root_frame, new Frag3());

                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);
        empty_imageview = view.findViewById(R.id.empty_imageview);
        no_data = view.findViewById(R.id.no_data);

        myDB = new MyDatabaseHelper(getContext());
        lyric_id = new ArrayList<>();
        lyric_name = new ArrayList<>();
        lyric_text = new ArrayList<>();
        lyric_date = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new RecyclerViewAdapter(getActivity(),getContext(),
                lyric_id, lyric_name, lyric_text,
                lyric_date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }



    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                lyric_id.add(cursor.getString(0));
                lyric_name.add(cursor.getString(1));

                if(cursor.getString(2).length()>10){

                    lyric_text.add(cursor.getString(2).substring(0,10)+"...");
                }else{
                    lyric_text.add(cursor.getString(2));
                }

                System.out.println(lyric_text);
                String temp = cursor.getString(3);
                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                try {
                    Date dateObj= dateFormat.parse(temp);
                    String timeAgo = TimeAgo.getTimeAgo(dateObj.getTime());
                    lyric_date.add(timeAgo);

                } catch (ParseException e) {
                    e.printStackTrace();
                }




            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

}
