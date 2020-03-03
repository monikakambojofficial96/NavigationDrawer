package com.example.navigationdrawer;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {
    private static final int GALLERY=1, CAMERA=2;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private static final String IMAGE_DIRECTORY="/demonuts";
    private CircleImageView imageView;
//    private String imagePreferance="image";
//    public  SharedPreferences myPrefrence;
//    public String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
       requestMultiplePermissions();
        NavigationView navigationView= findViewById ( R.id.activity_main_navigation_drawer );
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header);
        imageView=headerView.findViewById ( R.id.nav_header_image );
        TextView tvName=headerView.findViewById ( R.id.nav_header_name );
        drawerLayout=findViewById ( R.id.activity_main_drawer_layout );
        actionBarDrawerToggle=new ActionBarDrawerToggle ( this, drawerLayout, R.string.Open, R.string.Close );
        drawerLayout.addDrawerListener ( actionBarDrawerToggle );
        actionBarDrawerToggle.syncState ();


//
//        SharedPreferences.Editor editor = myPrefrence.edit();
//        editor.putString("imagePreferance", encodeTobase64(yourbitmap));
//        editor.commit();

        //for getting the action bar

        ActionBar bar=getSupportActionBar ();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled ( true );
            bar.setTitle ( "MovieMesh" );
        }
        ((NavigationView) findViewById ( R.id.activity_main_navigation_drawer )).setNavigationItemSelectedListener ( new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId ();
                Fragment fragment=null;             // create a Fragment Object

                if (id == R.id.navigation_menu_profile) {
                    fragment=new HomeFragment ();
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

        imageView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                showPictureDialog(v);
            }
        } );

        tvName.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent profile=new Intent ( MainActivity.this,ProfileActivity.class );
                startActivity ( profile );
            }
        } );


    }
//
//    public static String encodeTobase64(Bitmap bitmap){
//
//        Bitmap imageStored=bitmap;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        imageStored.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] b = baos.toByteArray();
//        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
//        Log.d("Image Log:", imageEncoded);
//        return imageEncoded;
//    }


    public void showPictureDialog(View v) {
        AlertDialog.Builder pictureDialog=new AlertDialog.Builder ( this );
        pictureDialog.setTitle ( "Select Action" );
        String[] pictureDialogItems={
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems ( pictureDialogItems,
                new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary ();
                                break;
                            case 1:
                                takePhotoFromCamera ();
                                break;
                        }
                    }
                } );
        pictureDialog.show ();
    }

    public void choosePhotoFromGallary() {
                Intent galleryIntent=new Intent ( Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                startActivityForResult ( galleryIntent, GALLERY );
    }

    private void takePhotoFromCamera() {
        Intent intent=new Intent ( android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
        if (intent.resolveActivity ( getPackageManager () ) != null)
            startActivityForResult ( intent, CAMERA );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult ( requestCode, resultCode, data );
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI=data.getData ();
                try {
                    Bitmap bitmap=MediaStore.Images.Media.getBitmap ( this.getContentResolver (), contentURI );
                    if (bitmap != null) {
                        saveImage ( bitmap );
                        Toast.makeText ( MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT ).show ();

                        imageView.setImageBitmap ( bitmap );
                    }
                } catch (IOException e) {
                    e.printStackTrace ();
                    Toast.makeText ( MainActivity.this, "Failed!", Toast.LENGTH_SHORT ).show ();
                }
            }
            }else if (requestCode == CAMERA) {
            Bitmap bitmap=(Bitmap) data.getExtras ().get ( "data" );
            saveImage ( bitmap );
            Toast.makeText ( MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT ).show ();
            imageView.setImageBitmap ( bitmap );

        }
    }

    public void saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes=new ByteArrayOutputStream ();
        myBitmap.compress ( Bitmap.CompressFormat.JPEG, 90, bytes );
        File wallpaperDirectory=new File ( Environment.getExternalStorageDirectory () + IMAGE_DIRECTORY );

        // have the object build the directory structure, if needed.

        if (!wallpaperDirectory.exists ()) {
            wallpaperDirectory.mkdirs ();
        }

        try {
            File f=new File ( wallpaperDirectory, Calendar.getInstance ()
                    .getTimeInMillis () + ".jpg" );
            f.createNewFile ();
            FileOutputStream fo=new FileOutputStream ( f );
            fo.write ( bytes.toByteArray () );
            MediaScannerConnection.scanFile ( this,
                    new String[]{f.getPath ()},
                    new String[]{"image/jpeg"}, null );
            fo.close ();
            Log.d ( "TAG", "File Saved::---&gt;" + f.getAbsolutePath () );

            f.getAbsolutePath ();
        } catch (IOException e1) {
            e1.printStackTrace ();
        }
    }

    private void  requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener () {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<com.karumi.dexter.listener.PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }


                }).withErrorListener(new PermissionRequestErrorListener () {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                }).onSameThread().check();
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