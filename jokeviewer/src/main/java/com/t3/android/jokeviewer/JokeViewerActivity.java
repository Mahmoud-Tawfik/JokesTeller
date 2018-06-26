package com.t3.android.jokeviewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeViewerActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_JOKE ="EXTRA_JOKE";

    String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_viewer);

        if(getIntent().getStringExtra(EXTRA_KEY_JOKE) != null) {
            joke = getIntent().getStringExtra(EXTRA_KEY_JOKE);

            TextView jokeTextView = findViewById(R.id.joke_text_view);
            jokeTextView.setText(joke);

        }
    }
}
