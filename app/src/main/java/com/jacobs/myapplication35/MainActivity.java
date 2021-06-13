package com.jacobs.myapplication35;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;



import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Switch;


import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Switch mySwitch;




    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        boolean handled = false;
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frag3View);
        Fragment fragment4 = getSupportFragmentManager().findFragmentById(R.id.frag4View);
        if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
            getSupportFragmentManager().popBackStack();
        }else if(!(fragment4 instanceof IOnBackPressed) || !((IOnBackPressed) fragment4).onBackPressed()){
            super.onBackPressed();
            getSupportFragmentManager().popBackStack();
        }else{

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
             setTheme(R.style.DARK);
        } else{
             setTheme(R.style.LIGHT);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // AppBarLayout toolbar = (AppBarLayout) findViewById(R.id.appbarid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);





        //VIEWPAGER
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final ViewPagerAdapter adapter = new ViewPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });







    }




    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }

        /*
        MenuItem checkable = menu.findItem(R.id.myswitch);
        Switch actionView=(Switch) MenuItemCompat.getActionView(checkable);

        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){

         actionView.setChecked(true);
        }
        actionView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(isChecked){

                  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

              }else{

                  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

              }
            }
        });

*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.contact) {
            Intent intent1 = new Intent(this,ContactActivity.class);
            this.startActivity(intent1);
            return true;
        }


        if (id == R.id.share) {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            this.startActivity(sendIntent);
            return true;
        }






/*
        if (id == R.id.settings) {
            Toast.makeText(this, "Setting", Toast.LENGTH_LONG).show();
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }
}