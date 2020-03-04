package com.example.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class DetailFragment extends Fragment implements View.OnClickListener {

    private MovieModel movieModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate ( R.layout.activity_detail_fragment, container, false );

        movieModel =(MovieModel) getArguments ().getSerializable ( "position" );

        ImageView fragmentImage=rootView.findViewById ( R.id.activity_detail_fragment_image_view );
        fragmentImage.setImageResource ( movieModel.imageId );

        TextView fragmentText=rootView.findViewById ( R.id.activity_detail_fragment_text_view );
        fragmentText.setText ( movieModel.MovieNames);

        Button fragmentTrailer=rootView.findViewById ( R.id.activity_detail_fragment_btn_trailer );

        fragmentTrailer.setOnClickListener (this);

        return rootView;
    }


    @Override
    public void onClick(View v) {

        ((DetailActivity)getActivity ()).addFragment ( new ShowTrailerFragment(), movieModel);
//        Intent intent=new Intent (getActivity (),);
//        startActivity ( intent );
    }
}
