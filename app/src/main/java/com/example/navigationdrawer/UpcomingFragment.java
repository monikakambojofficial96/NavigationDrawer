package com.example.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class UpcomingFragment extends Fragment {
    RecyclerView recyclerView;
    private List<MovieModel> moviesList = new ArrayList<> ();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView=inflater.inflate ( R.layout.activity_upcoming_fragment,container,false );

        recyclerView=rootView.findViewById( R.id.activity_upcoming_recycler_view );
        movieData ();
        PopularMovieAdapter popularMovieAdapter=new PopularMovieAdapter ( (AppCompatActivity) getContext (),moviesList);
        LinearLayoutManager layoutManager=new LinearLayoutManager ( getActivity () );
        recyclerView.setLayoutManager (layoutManager);
        recyclerView.setAdapter(popularMovieAdapter);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Upcoming Movies");

        //Recycler View End
        return rootView;
    }

    private void movieData() {

        moviesList.add(new MovieModel("Angrezi Medium",R.raw.angrezi_medium));
        moviesList.add(new MovieModel("Haathi Mere Saathi",R.raw.haathi_mere_saathi));
        moviesList.add(new MovieModel("Coolie No.1",R.raw.coolie_no_one));
        moviesList.add(new MovieModel("Dil Bechara",R.raw.dil_bechara));
        moviesList.add(new MovieModel("Shakuntala Devi",R.raw.shakuntala_devi));
        moviesList.add(new MovieModel("Sher Shaah",R.raw.shershaah));
        moviesList.add(new MovieModel("Chehre",R.raw.chehre));
        moviesList.add(new MovieModel("The Winds Hawayein",R.raw.the_winds_hawayein));
        moviesList.add(new MovieModel("Baagi-3",R.raw.baagi_three));
        moviesList.add(new MovieModel("Kaamyaab",R.raw.kaamyaab));

    }
}
