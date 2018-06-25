package com.udacity.gradle.builditbigger.backend;

import com.t3.android.jokeslib.JokesTeller;

public class MyBean {

    public String getJoke() {
        return new JokesTeller().getRandom();
    }

}