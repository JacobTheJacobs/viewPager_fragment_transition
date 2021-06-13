package com.jacobs.myapplication35;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Switch simpleToggleButton = (Switch) findViewById(R.id.simpleToggleButton); // initiate a toggle button
        Boolean ToggleButtonState = simpleToggleButton.isChecked();


        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES && !ToggleButtonState) {
            setTheme(R.style.DARK);
            simpleToggleButton.setChecked(true);

        } else {
            setTheme(R.style.LIGHT);
            simpleToggleButton.setChecked(false);
        }

        Button contact = (Button)findViewById(R.id.contactButton);

        contact.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                sendMessage();
            }
        });


        simpleToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                }else{

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();

                }
            }

        });



    }


public void sendMessage(){
    try {
        Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/bajaLemon"));
        telegram.setPackage("org.telegram.messenger");
        startActivity(telegram);
    } catch (Exception e) {
        Toast.makeText(this, "Telegram app is not installed", Toast.LENGTH_LONG).show();
    }
}

    public void restartApp(){
        Intent i = new Intent(this.getApplicationContext(),ContactActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

    }



}
