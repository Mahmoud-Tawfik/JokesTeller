package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.t3.android.jokeviewer.JokeViewerActivity;


public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.joke_progressbar);
        progressBar.setVisibility(View.GONE);
    }

    public void tellJoke(View view) {
        progressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(new AsyncTaskHandler() {
            @Override
            public void OnResultReceivedListener(String result) {
                progressBar.setVisibility(View.GONE);
                showJoke(result);
            }
        });
    }

    public void showJoke(String joke) {
        if (!joke.isEmpty()){
            Intent intent = new Intent(this, JokeViewerActivity.class);
            intent.putExtra("EXTRA_JOKE", joke);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.no_jokes_available, Toast.LENGTH_SHORT).show();
        }
    }

}
