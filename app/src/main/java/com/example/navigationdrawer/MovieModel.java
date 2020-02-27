package com.example.navigationdrawer;

import java.io.Serializable;

public class MovieModel implements Serializable {

        String MovieNames;
        Integer imageId;

        boolean isSelected;



        public void setGetSelected(boolean getSelected) {
            this.isSelected = getSelected;
        }


        MovieModel(String MovieNames, Integer imageId){

            this.MovieNames=MovieNames;
            this.imageId=imageId;
        }

    }

