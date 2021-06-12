package com.jacobs.myapplication35;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList lyric_id, lyric_name, lyric_text, lyric_date,short_lyric_text;


    RecyclerViewAdapter(Activity activity, Context context,
                        ArrayList book_id, ArrayList lyric_name,
                        ArrayList lyric_text, ArrayList lyric_date,ArrayList short_lyric_text){
        this.activity = activity;
        this.context = context;
        this.lyric_id = book_id;
        this.lyric_name = lyric_name;
        this.lyric_text = lyric_text;
        this.lyric_date = lyric_date;
        this.short_lyric_text = short_lyric_text;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);




        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.lyric_id_txt.setText(String.valueOf(lyric_id.get(position)));
        holder.lyric_name_txt.setText(String.valueOf(lyric_name.get(position)));
        holder.lyric_text_txt.setText(String.valueOf(short_lyric_text.get(position)));
        holder.lyric_date_txt.setText(String.valueOf(lyric_date.get(position)));
        setAnimation(holder.itemView, position);
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(lyric_id.get(position)));
                bundle.putString("name", String.valueOf(lyric_name.get(position)));
                bundle.putString("text", String.valueOf(lyric_text.get(position)));
                // set Fragmentclass Arguments
                Frag4 fragmentobj = new Frag4();
                fragmentobj.setArguments(bundle);

                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();

                manager.beginTransaction().replace(R.id.root_frame, fragmentobj)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null).commit();

              //  Intent intent = new Intent(context, UpdateActivity.class);
              //  intent.putExtra("id", String.valueOf(lyric_id.get(position)));
              //  intent.putExtra("title", String.valueOf(lyric_name.get(position)));
               // intent.putExtra("author", String.valueOf(lyric_text.get(position)));
               // intent.putExtra("pages", String.valueOf(lyric_date.get(position)));
              //  activity.startActivityForResult(intent, 1);
            }
        });




    }

    @Override
    public int getItemCount() {
        return lyric_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lyric_id_txt, lyric_name_txt, lyric_text_txt, lyric_date_txt;
        LinearLayout mainLayout;
        CardView cardView;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lyric_id_txt = itemView.findViewById(R.id.lyric_id_txt);
            lyric_name_txt = itemView.findViewById(R.id.lyric_name_txt);
            lyric_text_txt = itemView.findViewById(R.id.lyric_text);
            lyric_date_txt = itemView.findViewById(R.id.lyric_date);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            cardView = itemView.findViewById(R.id.cardView);

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

            cardView.setBackground(draw);


            //Animate Recyclerview
            //Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
           // mainLayout.setAnimation(translate_anim);
        }

    }


    private void setAnimation(View itemView, int i) {
        boolean isNotFirstItem = i == -1;
        i++;
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.f, 0.5f, 1.0f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animator.setStartDelay(isNotFirstItem ? 500 / 2 : (i * 500 / 3));
        animator.setDuration(500);
        animatorSet.play(animator);
        animator.start();

        /**/
    }


}