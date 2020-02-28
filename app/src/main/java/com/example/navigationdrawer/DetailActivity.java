package com.example.navigationdrawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_detail );

        addFragment ( new DetailFragment () );
        Bundle bundle=this.getIntent ().getExtras ();
        if (bundle != null) {
            bundle.getSerializable ( "position" );
        }


    }

    public void addFragment(Fragment fragment) {
        FragmentTransaction transaction=getSupportFragmentManager ().beginTransaction ();
        transaction.add ( R.id.activity_detail_frame, fragment ).addToBackStack ( "freg_back" );                        // replace a Fragment with Frame Layout && for Backstack
        transaction.commit ();             // commit the changes
    }
    @Override
    public void onBackPressed() {             //for killing the activity when go back
        // do something on back.
        finish ();

    }
}
