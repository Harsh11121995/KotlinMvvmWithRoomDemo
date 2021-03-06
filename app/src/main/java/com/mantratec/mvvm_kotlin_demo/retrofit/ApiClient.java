package com.mantratec.mvvm_kotlin_demo.retrofit;



import androidx.core.os.BuildCompat;

import com.mantratec.mvvm_kotlin_demo.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final long DEFAULT_TIMEOUT = 60000L;
    private static Retrofit retrofit = null;
    private static Retrofit retrofitGet = null; // create separate object for GET, to allow retry on connection failure

    /**
     * get retrofit object for POST specific API, retry on connection failure is restricted
     *
     * @return retrofit object
     */
    public static Retrofit getClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(false)
                    .addInterceptor(loggingInterceptor)//print request and response on log
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS) // connection time between device to server
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS) // response time between server to device
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS) // send data time between device to server after connection established
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_DOMAIN)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}