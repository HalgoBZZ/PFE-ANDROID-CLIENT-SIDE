package com.halgo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class TourneesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private  ImageView logout_icon;
    private boolean isOpen;
    private List<Tournee> tourneeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TourneeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tournees);
        tourneeList.add(new Tournee(1L,"Tournee1","Secteur1"));
        tourneeList.add(new Tournee(2L,"Tournee2","Secteur1"));
        tourneeList.add(new Tournee(3L,"Tournee3","Secteur1"));
        tourneeList.add(new Tournee(4L,"Tournee4","Secteur1"));
        tourneeList.add(new Tournee(5L,"Tournee5","Secteur1"));
        tourneeList.add(new Tournee(6L,"Tournee6","Secteur2"));
        tourneeList.add(new Tournee(7L,"Tournee7","Secteur2"));
        tourneeList.add(new Tournee(8L,"Tournee8","Secteur2"));
        tourneeList.add(new Tournee(9L,"Tournee9","Secteur3"));
        tourneeList.add(new Tournee(10L,"Tournee10","Secteur4"));
        tourneeList.add(new Tournee(11L,"Tournee11","Secteur5"));
        tourneeList.add(new Tournee(12L,"Tournee12","Secteur6"));
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new TourneeAdapter(tourneeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        menu_icon=(ImageView) findViewById(R.id.menu_tour);
        notification_icon=(ImageView)findViewById(R.id.notification_tour);
        help_icon=(ImageView)findViewById(R.id.help_tour);
        logout_icon=(ImageView)findViewById(R.id.logout_tour);
        mDrawerlayout=(DrawerLayout)findViewById(R.id.draw_tour);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_tour);
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
                startActivity(new Intent(TourneesActivity.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(TourneesActivity.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(TourneesActivity.this, MainActivity.class));
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
                startActivity(new Intent(TourneesActivity.this, TourneesActivity.class));
                break;
            case R.id.trajectoire:
                startActivity(new Intent(TourneesActivity.this, TrajectoireActivity.class));
                break;
            case R.id.planing:
                startActivity(new Intent(TourneesActivity.this, PlanningActivity.class));
                break;
            default:
                break;
        }

        this.mDrawerlayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
