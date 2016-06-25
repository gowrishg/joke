package in.kudu.jokerlib;

import android.text.TextUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest  {

    @Test
    public void joke_cant_be_empty() {
        JokeGenerator jokeGenerator = new JokeGenerator();
        String joke = jokeGenerator.tellAJoke();
        assertTrue("Joke can't be empty", joke.length() > 0);
    }

    @Test
    public void consecutive_jokes_cant_be_same() {
        for (int i = 0; i < 1000; i++) {
            JokeGenerator jokeGenerator = new JokeGenerator();
            String joke1 = jokeGenerator.tellAJoke();
            jokeGenerator = new JokeGenerator();
            String joke2 = jokeGenerator.tellAJoke();
            assertTrue("Consecutive Jokes can't be same", !joke1.equalsIgnoreCase(joke2));
        }
    }
}