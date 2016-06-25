package in.kudu.joke;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    Activity mActivity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    class TestCallBack implements AsyncHandler {
        @Override
        public void endOfAsync(String joke) {
            latch.countDown();
        }
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    final CountDownLatch latch = new CountDownLatch(1);

    @Test(timeout = 5000)
    public void async_task_check() {

        //onView(withId(R.id.tell_a_joke)).perform(click());

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.execute(new TestCallBack());
        try {
            latch.await();
            String nonEmptyString = endpointsAsyncTask.get();
            assertFalse("The output of the async task is empty", nonEmptyString.isEmpty());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}