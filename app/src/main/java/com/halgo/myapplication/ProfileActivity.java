package com.halgo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Halgo on 19/02/2018.
 */

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private boolean isOpen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isOpen=false;
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.profile);
        mDrawerlayout=(DrawerLayout)findViewById(R.id.draw);
        menu_icon=(ImageView) findViewById(R.id.menu_app);
        notification_icon=(ImageView)findViewById(R.id.notification_icon);
        help_icon=(ImageView)findViewById(R.id.help_icon);
        logout_icon=(ImageView)findViewById(R.id.logout_icon);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mToggle=new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        notification_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProfileActivity.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProfileActivity.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });



        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isOpen) {
                    mDrawerlayout.openDrawer(Gravity.START);
                    isOpen = true;
                }
                else {
                    mDrawerlayout.closeDrawer(Gravity.START);
                    isOpen = false;
                }
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.tournee1 :
                startActivity(new Intent(ProfileActivity.this, TourneesActivity.class));
                break;
            case R.id.trajectoire1:
                startActivity(new Intent(ProfileActivity.this, TrajectoireActivity.class));
                break;
            case R.id.planing1:
                startActivity(new Intent(ProfileActivity.this, PlanningActivity.class));
                break;
            default:
                break;
        }

        this.mDrawerlayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
