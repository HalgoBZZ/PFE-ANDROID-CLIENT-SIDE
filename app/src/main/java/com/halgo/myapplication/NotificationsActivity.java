package com.halgo.myapplication;

import android.app.Notification;
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
import android.widget.ImageView;

public class NotificationsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private  ImageView logout_icon;
    private boolean isOpen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);
        menu_icon=(ImageView) findViewById(R.id.menu_not);
        notification_icon=(ImageView)findViewById(R.id.notification_not);
        help_icon=(ImageView)findViewById(R.id.help_not);
        logout_icon=(ImageView)findViewById(R.id.logout_not);
        mDrawerlayout=(DrawerLayout)findViewById(R.id.draw_not);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_not);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle=new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();


        notification_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(NotificationsActivity.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(NotificationsActivity.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(NotificationsActivity.this, MainActivity.class));
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
            case R.id.tournee :
                startActivity(new Intent(NotificationsActivity.this, TourneesActivity.class));
                break;
            case R.id.trajectoire:
                startActivity(new Intent(NotificationsActivity.this, TrajectoireActivity.class));
                break;
            case R.id.planing:
                startActivity(new Intent(NotificationsActivity.this, PlanningActivity.class));
                break;
            default:
                break;
        }

        this.mDrawerlayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
