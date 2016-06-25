package in.kudu.joke;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import in.kudu.joke.backend.joke.Joke;
import in.kudu.joke_viewer.JokeActivity;

public class MainActivity extends AppCompatActivity {

    Button mTellAJokeButton;
    FrameLayout mAdFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdFragment = (FrameLayout) findViewById(R.id.ad_fragment);
        mTellAJokeButton = (Button) findViewById(R.id.tell_a_joke);
        mTellAJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kudu.in.joke_provider.JokeGenerator jokeGenerator = new kudu.in.joke_provider.JokeGenerator();
                //String joke = jokeGenerator.tellAJoke();

                Toast.makeText(MainActivity.this, R.string.joke_message, Toast.LENGTH_SHORT).show();

                new EndpointsAsyncTask().execute(MainActivity.this);
            }
        });

        boolean isAdEnabled = getResources().getBoolean(R.bool.enable_advertisement);
        if (isAdEnabled) {
            //! using reflection as the Class wouldn't be compilable in other flavour
            Class c = null;
            Method method = null;
            try {
                c = Class.forName("in.kudu.joke.AdFragment");
                method = c.getMethod("newInstance");
                Fragment adFragment = (Fragment) method.invoke(null);
                getSupportFragmentManager().beginTransaction().add(R.id.ad_fragment, adFragment).commit();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            mAdFragment.setVisibility(View.VISIBLE);
            mTellAJokeButton.setText(R.string.free_joke);
        } else {
            mAdFragment.setVisibility(View.GONE);
            mTellAJokeButton.setText(R.string.tell_a_joke_button);
        }

    }
}
