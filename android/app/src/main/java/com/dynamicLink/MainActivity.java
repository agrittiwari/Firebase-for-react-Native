package com.dynamicLink;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;

import android.os.Bundle;
import android.os.RemoteException;


import com.google.firebase.analytics.FirebaseAnalytics;

// import com.android.installreferrer.api;
import android.util.Log;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerClient.InstallReferrerResponse;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;


public class MainActivity extends ReactActivity {
InstallReferrerClient referrerClient;
/**
 * Returns the name of the main component registered from JavaScript. This is used to schedule
 * rendering of the component.
 */
@Override
protected String getMainComponentName() {
return "dynamicLink";
}

    // [START declare_analytics]
    private FirebaseAnalytics mFirebaseAnalytics;
    // [END declare_analytics]

    private static final String TAG = "MyActivity";


   
    
/**
 * Returns the instance of the {@link ReactActivityDelegate}. There the RootView is created and
 * you can specify the renderer you wish to use - the new renderer (Fabric) or the old renderer
 * (Paper).
 */
@Override
protected ReactActivityDelegate createReactActivityDelegate() {
  return new MainActivityDelegate(this, getMainComponentName());
}

@Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
    
      // [START shared_app_measurement]
      // Obtain the FirebaseAnalytics instance.
      mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
      // [END shared_app_measurement]

      

                referrerClient = InstallReferrerClient.newBuilder(this).build();


                referrerClient.startConnection(new InstallReferrerStateListener() {

              @Override
              public void onInstallReferrerSetupFinished(int responseCode) {
                  switch (responseCode) {
                      case InstallReferrerResponse.OK:
                          // Connection established.
                          // Log.v( "InstallReferrer connected");
                          try {
                            ReferrerDetails response = referrerClient.getInstallReferrer();

                            String referrerUrl = response.getInstallReferrer();

                            long referrerClickTime = response.getReferrerClickTimestampSeconds();

                            long appInstallTime = response.getInstallBeginTimestampSeconds();

                            boolean instantExperienceLaunched = response.getGooglePlayInstantParam();

                          logReferrerUrlCustomEvent( referrerUrl, referrerClickTime, appInstallTime, instantExperienceLaunched);
 
                           Log.v(TAG, "event logged, code ran");
                          } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                          }
                          break;
                      case InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                          // API not available on the current Play Store app.
                          break;
                      case InstallReferrerResponse.SERVICE_UNAVAILABLE:
                          // Connection couldn't be established.
                          break;
                  }
              }

        @Override
        public void onInstallReferrerServiceDisconnected() {
            // Try to restart the connection on the next request to
            // Google Play by calling the startConnection() method.
        }

});





    }


    public void  logReferrerUrlCustomEvent(String url, Long clickTime, Long installTime, Boolean instantExperience) {


     
      // [START referreUrlParams]
              Bundle referrerUrlParams = new Bundle();
      
      referrerUrlParams.putString("referrer_url",url);
      referrerUrlParams.putLong("referrer_click_time",clickTime);
      referrerUrlParams.putLong("app_intsall_time",installTime);
      referrerUrlParams.putBoolean("instant_experience",instantExperience);

      mFirebaseAnalytics.logEvent("play_instant_referrer_event", referrerUrlParams); 
// [END referreUrlParams]
      }

public static class MainActivityDelegate extends ReactActivityDelegate {
  public MainActivityDelegate(ReactActivity activity, String mainComponentName) {
    super(activity, mainComponentName);
  }

  @Override
  protected ReactRootView createRootView() {
    ReactRootView reactRootView = new ReactRootView(getContext());
    // If you opted-in for the New Architecture, we enable the Fabric Renderer.
    reactRootView.setIsFabric(BuildConfig.IS_NEW_ARCHITECTURE_ENABLED);
    return reactRootView;
  }

  @Override
  protected boolean isConcurrentRootEnabled() {
    // If you opted-in for the New Architecture, we enable Concurrent Root (i.e. React 18).
    // More on this on https://reactjs.org/blog/2022/03/29/react-v18.html
    return BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
  }
}

}
