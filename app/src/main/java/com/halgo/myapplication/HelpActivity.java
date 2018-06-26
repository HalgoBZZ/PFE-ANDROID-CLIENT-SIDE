package com.halgo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class HelpActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private  ImageView logout_icon;
    private boolean isOpen;
    private String token;
    private String login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        final Intent intent=getIntent();
        token= intent.getStringExtra("token");
        login=intent.getStringExtra("login");
        menu_icon=(ImageView) findViewById(R.id.menu_help);
        notification_icon=(ImageView)findViewById(R.id.notification_help);
        help_icon=(ImageView)findViewById(R.id.help_help);
        logout_icon=(ImageView)findViewById(R.id.logout_help);
        mDrawerlayout=(DrawerLayout)findViewById(R.id.draw_help);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_help);
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
                Intent intent=new Intent(HelpActivity.this, NotificationsActivity.class);
                intent.putExtra("login",login);
                startActivity(intent);
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent =new Intent(HelpActivity.this, HelpActivity.class);
                intent.putExtra("login",login);
                startActivity(intent);
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               Intent intent1=new Intent(HelpActivity.this, MainActivity.class);
               intent.putExtra("login","");
               intent.putExtra("token","");
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       /* MenuItem tournee=findViewById(R.id.tournee);
        MenuItem trajectoire=findViewById(R.id.trajectoire);
        MenuItem planing =findViewById(R.id.planing);
        switch (item.getItemId()) {
            case R.id.tournee: {
                startActivity(new Intent(HelpActivity.this, TourneesActivity.class));
                this.mDrawerlayout.closeDrawer(GravityCompat.START);
                Log.i("you are cliking","tournee");
                break;
            }
            case R.id.trajectoire: {
                startActivity(new Intent(HelpActivity.this, TrajectoireActivity.class));
                this.mDrawerlayout.closeDrawer(GravityCompat.START);
                Log.i("you are cliking","Trajectoire");
                break;
            }
            case R.id.planing: {
                startActivity(new Intent(HelpActivity.this, PlanningActivity.class));
                this.mDrawerlayout.closeDrawer(GravityCompat.START);
                Log.i("you are cliking","Planning");
                break;
            }
            default:{
            this.mDrawerlayout.closeDrawer(GravityCompat.START);
            return true;
            }
        }*/

        switch (item.getItemId()) {
            case R.id.tournee: {
                startActivity(new Intent(HelpActivity.this, TourneesActivity.class));
                this.mDrawerlayout.closeDrawer(GravityCompat.START);
                break;
            }
            case R.id.trajectoire: {
                startActivity(new Intent(HelpActivity.this, TrajectoireActivity.class));
                this.mDrawerlayout.closeDrawer(GravityCompat.START);
                break;
            }
            case R.id.planing: {
                startActivity(new Intent(HelpActivity.this, PlanningActivity.class));
                this.mDrawerlayout.closeDrawer(GravityCompat.START);
                break;
            }
            default:{
                this.mDrawerlayout.closeDrawer(GravityCompat.START);
                return true;
            }
        }

       /* if(item.getTitle()=="Tournées"){
            startActivity(new Intent(HelpActivity.this, TourneesActivity.class));
           // this.mDrawerlayout.closeDrawer(GravityCompat.START);
            return true;
        }else if(item.getTitle()=="Trajectoire"){
            startActivity(new Intent(HelpActivity.this, TrajectoireActivity.class));
            this.mDrawerlayout.closeDrawer(GravityCompat.START);
            return true;
        }else if(item.getTitle()=="planing"){
            startActivity(new Intent(HelpActivity.this, PlanningActivity.class));
            this.mDrawerlayout.closeDrawer(GravityCompat.START);
            return true;
        }*/
        this.mDrawerlayout.closeDrawer(GravityCompat.START);

        return true;

    }
    @Override
    public void onBackPressed() {
        if(mDrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerlayout.closeDrawer(GravityCompat.START);
        }
    }

}

