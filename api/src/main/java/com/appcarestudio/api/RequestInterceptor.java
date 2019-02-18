package com.appcarestudio.api;

import android.support.annotation.NonNull;



import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class RequestInterceptor implements Interceptor {

    private boolean isAuthorized;

    public RequestInterceptor(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();
      /*  //TODO lastLocation
        LatLng latLng = AppPrefs.getInstance().getLastKnownlocation();
        HttpUrl.Builder urlBuilder = originalHttpUrl.newBuilder()
                .addQueryParameter("lang", AppPrefs.getInstance().getLang())
                .addQueryParameter("lng", latLng.longitude + "")
                .addQueryParameter("lat", latLng.latitude + "")
                .addQueryParameter("app_id", AppPrefs.getInstance().getAppId());

        if (!AppPrefs.getInstance().getAuthToken().equals("")) {
            urlBuilder.addQueryParameter("session_id", AppPrefs.getInstance().getAuthToken());
        }
        HttpUrl url = urlBuilder.build();


        String appVersion = BuildConfig.VERSION_NAME;
        int androidVersion = Build.VERSION.SDK_INT;
        requestBuilder.addHeader("User-Agent", "PriceMonitor Android [" + appVersion + "] / [" + androidVersion + "]");
        requestBuilder.addHeader("lang", AppPrefs.getInstance().getLang());
        // MOSCOW 55.751244, 37.618423
        requestBuilder.addHeader("lng", latLng.longitude + "");
        requestBuilder.addHeader("lat", latLng.latitude + "");
        requestBuilder.addHeader("app_id", AppPrefs.getInstance().getAppId());
        if (AppPrefs.getInstance().isSigned()) {
            requestBuilder.addHeader("session_id", AppPrefs.getInstance().getAuthToken());
        }*/

       /* HttpUrl.Builder urlBuilder = originalHttpUrl.newBuilder()
                .addQueryParameter("s", isAuthorized ? "0" : "1");
        HttpUrl url = urlBuilder.build();

        String lookingVersion = "1";
        Request.Builder requestBuilder = originalRequest.newBuilder().url(originalHttpUrl);
        requestBuilder.addHeader("User-Agent", "Looking" + " " + lookingVersion +" " +"(Android)");

        requestBuilder.url(url);*/
       //Request request = requestBuilder.build();
        return chain.proceed(originalRequest);
    }
}
