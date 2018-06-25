package com.t3.android.jokeviewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeViewerActivity extends AppCompatActivity {

    String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_viewer);

        joke = getIntent().getStringExtra("EXTRA_JOKE");
        if (savedInstanceState != null){
            joke = savedInstanceState.getString("JOKE");
        }

        TextView jokeTextView = findViewById(R.id.joke_text_view);
        jokeTextView.setText(joke);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("JOKE", joke);
    }
}
