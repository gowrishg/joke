package in.kudu.joke;

import android.content.Context;

/**
 * Created by gowrishg on 26/6/16.
 */
public class IAd {

    public interface AdCallback {
        void onAdClosed();

        void onAdNotLoaded();
    }

    public interface AdController {
        void showAd();

        void setCallback(AdCallback adCallback);

        void instantiateOnce(Context context);
    }
}
