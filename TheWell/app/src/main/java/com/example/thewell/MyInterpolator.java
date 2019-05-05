package com.example.thewell;

/**
 Jacob Bamonte, Mike Moran
 CIT382
 Final Project
 */

public class MyInterpolator  implements android.view.animation.Interpolator{

    private double mAmplitude;
    private double mFrequency;

    MyInterpolator(double amplitude, double frequency){

        mAmplitude = amplitude;
        mFrequency = frequency;

    }

    @Override
    public float getInterpolation(float time) {

        return (float) (-1 * Math.pow(Math.E, -time/mAmplitude) *

                Math.cos(mFrequency * time) + 1);

    }

}
