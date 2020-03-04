package com.example.navigationdrawer;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_detail );

        Bundle bundle=this.getIntent ().getExtras ();
        if (bundle != null) {
            addFragment ( new DetailFragment (), (MovieModel) bundle.getSerializable ( "position" ) );
        }
        imageView=findViewById ( R.id.activity_detail_fragment_image_view );

    }

    public void addFragment(Fragment fragment, MovieModel movieModel) {

        Bundle bundle=new Bundle ();
        bundle.putSerializable ( "position", movieModel );
        fragment.setArguments ( bundle );

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
