package com.jacobs.myapplication35;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public  class Frag2 extends  Fragment implements View.OnClickListener {


    private List<Rhyme> msProductList;
    private MyDataBaseHelperRhymes mDBHelper;
    private EditText searchBar;
    private Button retrieve;
    private Context mContext;
    private MyGridAdapter adapter;
    RecyclerView recyclerView;


    // Initialise it from onAttach()
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;


    }

    public  Frag2 () {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.frag2, container, false);


        mDBHelper = new MyDataBaseHelperRhymes(mContext);
        searchBar =  view.findViewById(R.id.RhymeSearch);
        retrieve =  view.findViewById(R.id.SearchRhm);


       copyDatabase(mContext);


         recyclerView = view.findViewById(R.id.gv_rhm);

        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, numberOfColumns));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));



        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res = searchBar.getText().toString().length();
                System.out.println(searchBar.getText().toString());
                if(res>0){
                   showList(searchBar.getText().toString());

              }
            }
        });

        return view;
    }
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 1){
            return false;
        }else{
            etText.setError("2 אותיות או יותר");
            Toast.makeText(mContext, "2 אותיות או יותר", Toast.LENGTH_SHORT).show();
            return true;
        }

    }

    private void showList(String toString) {

        System.out.println(toString);

        msProductList = mDBHelper.getRhymes(toString);

        if(msProductList.size()==0){
            Toast.makeText(mContext,
                    "לא נמצא חרוזים מתאימים, נסו שוב !",
                    Toast.LENGTH_SHORT).show();

        }

        for(Rhyme t:msProductList){
            System.out.println(t);
        }


        if(msProductList.size()>0){
            // recyclerView.setLayoutManager(new GridLayoutManager(mContext, numberOfColumns));
            adapter = new MyGridAdapter(mContext, (ArrayList<Rhyme>) msProductList);
            // adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);

        }

    }


    private boolean copyDatabase(Context context) {


        try {

            InputStream inputStream = context.getAssets().open(MyDataBaseHelperRhymes.DBNAME);
            String outFileName = MyDataBaseHelperRhymes.DBLOCATION + MyDataBaseHelperRhymes.DBNAME;

            String outFileName1 = mContext.getDatabasePath(MyDataBaseHelperRhymes.DBNAME).getAbsolutePath();


            OutputStream outputStream = new FileOutputStream(outFileName);

            MyDataBaseHelperRhymes helper = new MyDataBaseHelperRhymes(getContext());
            SQLiteDatabase database = helper.getReadableDatabase();
            String filePath = database.getPath();
            database.close();


            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            inputStream.close();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void onClick(View view) {

    }
}