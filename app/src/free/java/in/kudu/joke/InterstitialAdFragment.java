package in.kudu.joke;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Use the {@link InterstitialAdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InterstitialAdFragment implements IAd.AdController {

    public InterstitialAdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AdFragment.
     */
    public static InterstitialAdFragment newInstance() {
        InterstitialAdFragment fragment = new InterstitialAdFragment();
        return fragment;
    }

    InterstitialAd mInterstitialAd = null;
    IAd.AdCallback mAdCallback = null;

    @Override
    public void showAd() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            return;
        }
        mAdCallback.onAdNotLoaded();
    }

    @Override
    public void setCallback(IAd.AdCallback adCallback) {
        mAdCallback = adCallback;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("D83716E5D1E7C64AA4BE9609D5B776FA")
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void instantiateOnce(Context context) {
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getString(R.string.ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                if (mAdCallback != null) {
                    requestNewInterstitial();
                    mAdCallback.onAdClosed();
                }
            }
        });
        requestNewInterstitial();
    }

}
