package com.example.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class ShowTrailerFragment extends Fragment {

    private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate ( R.layout.activity_show_trailer, container, false );
        MediaController mc= new MediaController (getActivity());
        videoView=rootView.findViewById(R.id.activity_detail_fragment_video_view );
        String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.video;
        videoView.setVideoURI( Uri.parse(path));
        videoView.setMediaController(mc);
        return rootView;
    }

}
