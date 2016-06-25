package in.kudu.joke_viewer;

import android.app.Activity;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import static android.support.test.espresso.Espresso.onView;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
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
    public void display_joke_test() {
        Intent intent = new Intent();
        intent.putExtra(JokeActivity.JOKE_KEY, "Test Joke");
        activityRule.launchActivity(intent);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.joke)).check(matches(withText("Test Joke")));
    }




}