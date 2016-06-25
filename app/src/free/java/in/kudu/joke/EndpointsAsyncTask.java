package in.kudu.joke;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import in.kudu.joke.backend.joke.Joke;
import in.kudu.joke_viewer.JokeActivity;

/**
 * Created by gowrishg on 25/6/16.
 */

class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private in.kudu.joke.backend.joke.Joke myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
            Joke.Builder builder = new Joke.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://udacity-1297.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];

        try {
            return myApiService.tellAJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        Intent intent = new Intent(context, in.kudu.joke_viewer.JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        context.startActivity(intent);
    }
}