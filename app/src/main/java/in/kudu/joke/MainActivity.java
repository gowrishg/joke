package in.kudu.joke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import in.kudu.joke_viewer.JokeActivity;

public class MainActivity extends AppCompatActivity {

    Button mTellAJokeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTellAJokeButton = (Button) findViewById(R.id.tell_a_joke);
        mTellAJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kudu.in.joke_provider.JokeGenerator jokeGenerator = new kudu.in.joke_provider.JokeGenerator();
                String joke = jokeGenerator.tellAJoke();

                Toast.makeText(MainActivity.this, R.string.joke_message, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, in.kudu.joke_viewer.JokeActivity.class);
                intent.putExtra(JokeActivity.JOKE_KEY, joke);
                startActivity(intent);
            }
        });
    }
}
