package com.jacobs.myapplication35;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MyGridAdapter extends RecyclerView.Adapter<MyGridAdapter.ViewHolder> {

        private ArrayList<Rhyme> rhmList;
        private LayoutInflater mInflater;
        private ItemClickListener mClickListener;

        // data is passed into the constructor
        MyGridAdapter(Context context, ArrayList<Rhyme> rhmList) {
            this.mInflater = LayoutInflater.from(context);
            this.rhmList = rhmList;
        }


        // inflates the cell layout from xml when needed
        @Override
        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.layout_grid_item, parent, false);
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
            draw.setCornerRadius( 20 );

            view.setBackground(draw);
            view.getBackground().setAlpha(70);

            return new ViewHolder(view);
        }



    // binds the data to the TextView in each cell
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.myTextView.setText(rhmList.get(position).get_name());
        }

        // total number of cells
        @Override
        public int getItemCount() {
                return (rhmList == null) ? 0 : rhmList.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView myTextView;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.tv_rhm_name);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        // convenience method for getting data at click position
        Rhyme getItem(int id) {
            return rhmList.get(id);
        }

        // allows clicks events to be caught
        void setClickListener(ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }
    }

