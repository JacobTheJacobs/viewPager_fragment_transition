package com.jacobs.myapplication35;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<Rhyme> rhmList;
    private static LayoutInflater inflater = null;

    public MyAdapter(Context context, ArrayList<Rhyme> rhmList) {
        this.context = context;
        this.rhmList = rhmList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return rhmList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null)
            convertView = inflater.inflate(R.layout.layout_grid_item, null);

        TextView nameTextView = (TextView) convertView.findViewById(R.id.tv_rhm_name);

        Rhyme e = new Rhyme();
        e = rhmList.get(position);

        nameTextView.setText(e.get_name());


        return convertView;
    }

}