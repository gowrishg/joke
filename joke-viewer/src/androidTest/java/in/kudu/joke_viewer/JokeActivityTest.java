package in.kudu.joke_viewer;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(AndroidJUnit4.class)
public class JokeActivityTest {

    @Rule
    public ActivityTestRule<JokeActivity> activityRule
            = new ActivityTestRule<>(
            JokeActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False to customize the intent

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void display_joke_test() {
        Intent intent = new Intent();
        intent.putExtra(JokeActivity.JOKE_KEY, "Test Joke");
        activityRule.launchActivity(intent);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}