package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {
    final CountDownLatch signal = new CountDownLatch(1);

    @Test
    public void AsyncTaskReturn() throws Exception {
        EndpointsAsyncTask task = new EndpointsAsyncTask();
        task.setLocalhost(true);
        task.execute(new AsyncTaskHandler() {
            @Override
            public void OnResultReceivedListener(String result) {
                assertFalse("Joke is empty!", result.isEmpty());
                assertFalse("Failed to connect to server! make sure to run the appengine",result.contains("failed to connect"));
                assertFalse("Connection refused! make sure to run the appengine",result.contains("Connection refused"));
                signal.countDown();
            }
        });
        signal.await();
    }
}
