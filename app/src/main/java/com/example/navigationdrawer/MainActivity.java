package com.example.navigationdrawer;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        drawerLayout=findViewById ( R.id.activity_main_drawer_layout );
        actionBarDrawerToggle=new ActionBarDrawerToggle ( this, drawerLayout, R.string.Open, R.string.Close );
        drawerLayout.addDrawerListener ( actionBarDrawerToggle );
        actionBarDrawerToggle.syncState ();

        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        this.getSupportActionBar().setTitle("MovieMesh");             //for title of action bar

        navigationView=findViewById ( R.id.activity_main_navigation_drawer );
        navigationView.setNavigationItemSelectedListener ( new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId ();
                Fragment fragment=null;             // create a Fragment Object

                if (id == R.id.navigation_menu_profile) {
                    fragment=new ProfileFragment ();
                } else if (id == R.id.navigation_menu_popular) {
                    fragment=new PopularFragment ();
                } else if (id == R.id.navigation_menu_top_rated) {
                    fragment=new TopRatedFragment ();
                } else if (id == R.id.navigation_menu_upcoming) {
                    fragment=new UpcomingFragment ();
                }
                if (fragment != null) {
                    addFragment ( fragment );
                    return true;
                }
                return false;
            }
        } );
    }

    public void addFragment(Fragment fragment) {
        FragmentTransaction transaction=getSupportFragmentManager ().beginTransaction ();
        transaction.add ( R.id.frame, fragment ).addToBackStack ( "freg_back" );                        // replace a Fragment with Frame Layout && for Backstack
        transaction.commit ();                                            // commit the changes
        drawerLayout.closeDrawers ();                                      // close the all open Drawer Views
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            if (actionBarDrawerToggle.onOptionsItemSelected ( item )) {
                return true;
            }
            return super.onOptionsItemSelected ( item );

    }

}
