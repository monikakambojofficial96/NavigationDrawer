package com.example.navigationdrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PopularFragment extends Fragment {

    public RecyclerView recyclerView;

    public List<MovieModel> moviesList=new ArrayList<> ();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //RecycclerView  Started

        View rootView=inflater.inflate ( R.layout.activity_popular_fragment, container, false );

        recyclerView=rootView.findViewById ( R.id.recycler_view );
        movieData ();
        PopularMovieAdapter popularMovieAdapter=new PopularMovieAdapter ( ((AppCompatActivity) getActivity ()), moviesList );
        LinearLayoutManager layoutManager=new LinearLayoutManager ( getActivity () );
        recyclerView.setLayoutManager ( layoutManager );
        recyclerView.setAdapter ( popularMovieAdapter );

        //Recycler View End
        return rootView;
    }

  public void movieData() {

        moviesList.add ( new MovieModel ( "3 Idiots", R.raw.three ) );
        moviesList.add ( new MovieModel ( "Swades", R.raw.swades ) );
        moviesList.add ( new MovieModel ( "Dil Chahta Hai", R.raw.swades ) );
        moviesList.add ( new MovieModel ( "Rang de Basanti", R.raw.rang ) );
        moviesList.add ( new MovieModel ( "Jab We Met", R.raw.jbwemet ) );
        moviesList.add ( new MovieModel ( "Chek de India", R.raw.chakdeindia ) );
        moviesList.add ( new MovieModel ( "Sanju", R.raw.sanju ) );
        moviesList.add ( new MovieModel ( "Lagaan", R.raw.lagaan ) );
        moviesList.add ( new MovieModel ( "Sholay", R.raw.sholay ) );
        moviesList.add ( new MovieModel ( "Zindgi Na Milegi Dubara", R.raw.zindgi_na ) );
        moviesList.add ( new MovieModel ( "A Wednesday", R.raw.wednesday ) );
        moviesList.add ( new MovieModel ( "Andaz Apna Apna", R.raw.andaz ) );
        moviesList.add ( new MovieModel ( "Dangal", R.raw.dangal ) );
        moviesList.add ( new MovieModel ( "Gangster", R.raw.gangster ) );
        moviesList.add ( new MovieModel ( "The LunchBox", R.raw.lunchbox ) );
        moviesList.add ( new MovieModel ( "Bhaag Milkha Bhaag", R.raw.milkha ) );
        moviesList.add ( new MovieModel ( "Sultan", R.raw.sultan ) );
        moviesList.add ( new MovieModel ( "Barfi", R.raw.barfi ) );
        moviesList.add ( new MovieModel ( "Mother india", R.raw.mother_india ) );
        moviesList.add ( new MovieModel ( "Black Friday", R.raw.black_friday ) );

    }
}
