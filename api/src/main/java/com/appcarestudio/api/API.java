package com.appcarestudio.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by IGOR on 26.03.2018.
 */

public abstract class API {

    public static final int TIMEOUT_IN_SEC = 15;

    public abstract String getDomenUrl();

    public abstract String getAdditionalPath();

    public String getBaseUrl() {
        return getDomenUrl() + getAdditionalPath();
    }




    public  Map<String, String> createDefaultParam() {
        Map<String, String> stringMap = new HashMap<>();
       /* stringMap.put("lang", AppPrefs.getInstance().getLang());
        LatLng latLng = AppPrefs.getInstance().getLastKnownlocation();
        stringMap.put("lng", latLng.longitude  + "");
        stringMap.put("lat", latLng.latitude + "");
        stringMap.put("app_id", AppPrefs.getInstance().getAppId());
        if (AppPrefs.getInstance().isSigned()) {
            stringMap.put("session_id", AppPrefs.getInstance().getAuthToken());
        }*/
        return stringMap;
    }


    private  OkHttpClient provideOkHttpClient(boolean isAuthorized) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.readTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor(isAuthorized));
        okHttpClient.addInterceptor(new ResponseInterceptor(isAuthorized));
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(logInterceptor);
        okHttpClient.followRedirects(true);
        okHttpClient.followSslRedirects(true);
        return okHttpClient.build();
    }


    private  Gson provideGsonClient() {
/*        Gson gson = new GsonBuilder().setDateFormat(DateUtils.PATTERN_PARSEGSON):
                registerTypeAdapter(City.class, new Stilist.CityStateDeserializer()).create();*/
        Gson gson = new GsonBuilder().create();
        return gson;
    }

    private  Gson provideGoogleGsonClient() {
        Gson gson =
                new GsonBuilder()
                        .create();
        return gson;
    }


/*
    public static ApiUsers getApiUsers(boolean isAuthorized) {
        Retrofit retrofit = provideDefaultRetrofit(isAuthorized);
        return retrofit.create(ApiUsers.class);
    }


    public static ApiNews getApiNews(boolean isAuthorized) {
        Retrofit retrofit = provideDefaultRetrofit(isAuthorized);
        return retrofit.create(ApiNews.class);
    }


    public static ApiShops getApiShops(boolean isAuthorized) {
        Retrofit retrofit = provideDefaultRetrofit(isAuthorized);
        return retrofit.create(ApiShops.class);
    }
*/


    public  Retrofit provideDefaultRetrofit(boolean isAuthorized) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(PrimitiveConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(provideGsonClient()))
                .client(provideOkHttpClient(isAuthorized))
                .build();
        return retrofit;
    }


}
