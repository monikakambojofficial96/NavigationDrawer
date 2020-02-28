package com.example.navigationdrawer;

import java.io.Serializable;

public class MovieModel implements Serializable {

        String MovieNames;
        Integer imageId;

        MovieModel(String MovieNames, Integer imageId){

            this.MovieNames=MovieNames;
            this.imageId=imageId;
        }

    }

