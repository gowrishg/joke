package in.kudu.joke;

import android.content.Intent;

/**
 * Created by gowrishg on 25/6/16.
 */
public interface AsyncHandler {
    /**
     * Callback to retrieve end of async
     * @param joke
     */
    void endOfAsync(String joke);
}
