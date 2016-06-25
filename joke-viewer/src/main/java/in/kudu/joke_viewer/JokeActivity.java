package in.kudu.joke_viewer;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;


public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "JOKE_KEY";
    TextView mJokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_joke);
        mJokeTextView = (TextView) findViewById(R.id.joke);

        String joke = getIntent().getStringExtra(JOKE_KEY);
        if (!TextUtils.isEmpty(joke)) {
            mJokeTextView.setText(joke);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
