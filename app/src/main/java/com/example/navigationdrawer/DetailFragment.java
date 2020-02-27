package com.example.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Objects;

public class DetailFragment extends Fragment implements Serializable {


//    TextView tvDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

     View layout= inflater.inflate ( R.layout.activity_detail_fragment, container, false );

     ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        Bundle bundle=this.getArguments ();
        if(bundle!=null){
            bundle.getSerializable ( "position" );
        }
     return layout;

    }
}
