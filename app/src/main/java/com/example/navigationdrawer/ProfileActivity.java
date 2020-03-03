package com.example.navigationdrawer;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.widget.ImageView;

public class ProfileActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences("IMAGE", MODE_PRIVATE);
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_profile );
        imageView=findViewById ( R.id.activity_profile_circular_image );
        getSupportActionBar ().setTitle ( "Profile" );

    }
}
