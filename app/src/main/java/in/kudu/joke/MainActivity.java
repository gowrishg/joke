package in.kudu.joke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import in.kudu.joke_viewer.JokeActivity;

public class MainActivity extends AppCompatActivity implements AsyncHandler, IAd.AdCallback {

    Button mTellAJokeButton;
    FrameLayout mAdFragment;
    String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createInterstitialAdObj();
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
        showAd();
    }

    private void showAd() {
        boolean isAdEnabled = getResources().getBoolean(R.bool.enable_advertisement);
        if (isAdEnabled) {
            mAdFragment.setVisibility(View.VISIBLE);
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
            mTellAJokeButton.setText(R.string.free_joke);
        } else {
            mAdFragment.setVisibility(View.GONE);
            mTellAJokeButton.setText(R.string.tell_a_joke_button);
        }
    }

    IAd.AdController mAdController = null;

    private void createInterstitialAdObj() {
        boolean isAdEnabled = getResources().getBoolean(R.bool.enable_advertisement);
        if (!isAdEnabled) {
            return;
        }

        //! using reflection as the Class wouldn't be compilable in other flavour
        Class c = null;
        Method method = null;
        try {
            c = Class.forName("in.kudu.joke.InterstitialAdFragment");
            method = c.getMethod("newInstance");
            mAdController = (IAd.AdController) method.invoke(null);
            mAdController.setCallback(this);
            mAdController.instantiateOnce(this);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endOfAsync(String joke) {
        mJoke = joke;
        if (TextUtils.isEmpty(joke)) {
            Toast.makeText(this, R.string.unable_get_joke_message, Toast.LENGTH_LONG).show();
            return;
        }

        boolean isAdEnabled = getResources().getBoolean(R.bool.enable_advertisement);

        if (isAdEnabled && mAdController != null) {
            mAdController.showAd();
        } else {
            loadJokeActivity(mJoke);
        }
    }

    @Override
    public void onAdClosed() {
        loadJokeActivity(mJoke);
    }

    @Override
    public void onAdNotLoaded() {
        loadJokeActivity(mJoke);
    }

    private void loadJokeActivity(String joke) {
        Intent intent = new Intent(this, in.kudu.joke_viewer.JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        startActivity(intent);
    }
}
