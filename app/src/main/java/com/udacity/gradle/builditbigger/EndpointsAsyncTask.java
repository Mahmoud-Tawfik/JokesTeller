package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<AsyncTaskHandler, Void, String> {
    private static JokeApi myApiService = null;
    private AsyncTaskHandler handler;
    private Boolean localhost = false;

    @Override
    protected String doInBackground(AsyncTaskHandler... params) {
        if(myApiService == null) {
            String url = localhost ? "http://localhost:8080/_ah/api/" : "http://10.0.2.2:8080/_ah/api/";

            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(url)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        handler = params[0];

        try {
            return myApiService.tellJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        handler.OnResultReceivedListener(result);
    }

    public void setLocalhost (Boolean localhost){
        this.localhost = localhost;
    }
}