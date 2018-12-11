package com.example.zdzitavetskaya_darya.movie.api;

import android.util.Log;

import com.example.zdzitavetskaya_darya.movie.constants.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message ->
            Log.e("OK_HTTP",message));

    private static final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

    public static Retrofit getRetrofitInstance() {

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .baseUrl(Constants.BASE_URL)
                    .build();
        }
        return retrofit;
    }
}
