package com.example.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.MovieViewHolder> {

    private List<MovieModel> moviesList;
    private AppCompatActivity activity;

    public PopularMovieAdapter(AppCompatActivity popularFragment, List<MovieModel> moviesList) {
        this.moviesList=moviesList;
        this.activity=popularFragment;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from ( parent.getContext () );
        View view=layoutInflater.inflate ( R.layout.item_list_movie, parent, false );
        return new MovieViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final MovieModel movie=moviesList.get ( position );
        holder.tvTextView.setText ( movie.MovieNames );
        holder.ivImageView.setImageResource ( movie.imageId );

    }


    @Override
    public int getItemCount() {
        return moviesList.size ();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivImageView;
        TextView tvTextView;

        public MovieViewHolder(@NonNull View itemView) {
            super ( itemView );
            ivImageView=itemView.findViewById ( R.id.activity_popular_fragment_layout_image_view );
            tvTextView=itemView.findViewById ( R.id.activity_popular_fragment_layout_tvmovie_name );
            itemView.setOnClickListener ( this );
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent ( activity,DetailActivity.class );
            intent.putExtra ( "position" , moviesList.get ( getAdapterPosition ()));         //for Serializable
            activity.startActivity ( intent );
        }
    }
}
