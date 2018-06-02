package com.halgo.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TrajectoireActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerlayout;
    FloatingActionButton fab;
    private GoogleMap googleMap;
    SupportMapFragment mapFragment;
    private ActionBarDrawerToggle mToggle;
    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private  ImageView logout_icon;
    private boolean isOpen;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trajectoire);
        menu_icon=(ImageView) findViewById(R.id.menu_traj);
        //mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);
        notification_icon=(ImageView)findViewById(R.id.notification_traj);


        help_icon=(ImageView)findViewById(R.id.help_traj);
        logout_icon=(ImageView)findViewById(R.id.logout_traj);
        mDrawerlayout=(DrawerLayout)findViewById(R.id.draw_traj);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_traj);
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
                startActivity(new Intent(TrajectoireActivity.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(TrajectoireActivity.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(TrajectoireActivity.this, MainActivity.class));
            }
        });


       /* mapFragment.getMapAsync(new

                                   OnMapReadyCallback() {
                                       @Override
                                       public void onMapReady (GoogleMap googleMap){
                                           googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                                           googleMap.addMarker(new MarkerOptions()
                                                   .position(new LatLng(37.4233438, -122.0728817))
                                                   .title("LinkedIn")
                                                   .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                                           googleMap.addMarker(new MarkerOptions()
                                                   .position(new LatLng(37.4629101, -122.2449094))
                                                   .title("Facebook")
                                                   .snippet("Facebook HQ: Menlo Park"));

                                           googleMap.addMarker(new MarkerOptions()
                                                   .position(new LatLng(37.3092293, -122.1136845))
                                                   .title("Apple"));

                                           googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.4233438, -122.0728817), 10));
                                       }
                                   });
*/



        menu_icon.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View view){
                if (!isOpen) {
                    mDrawerlayout.openDrawer(Gravity.START);
                    isOpen = true;
                } else {
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
                startActivity(new Intent(TrajectoireActivity.this, TourneesActivity.class));
                break;
            case R.id.trajectoire:
                startActivity(new Intent(TrajectoireActivity.this, TrajectoireActivity.class));
                break;
            case R.id.planing:
                startActivity(new Intent(TrajectoireActivity.this, PlanningActivity.class));
                break;
            default:
                break;
        }

        this.mDrawerlayout.closeDrawer(GravityCompat.START);

        return true;
    }


}
