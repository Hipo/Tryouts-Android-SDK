package com.hipo.tryouts.androidsdk;


import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class TryoutsService {

    private final static String TRYOUTS_BASE_URL = "https://api.tryouts.io/v1/";
    private static TryoutsApi tryoutsApi;

    public static void init() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();

                if(!TextUtils.isEmpty(Tryouts.getApiKey()) && !TextUtils.isEmpty(Tryouts.getApiSecret()))  {
                    builder.header("Authorization", String.format("%s:%s", Tryouts.getApiKey(), Tryouts.getApiSecret()));
                }

                Request request = builder.build();

                return chain.proceed(request);
            }
        };

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(logging)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TRYOUTS_BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        tryoutsApi = retrofit.create(TryoutsApi.class);
    }

    public static TryoutsApi getApi() {

        if(tryoutsApi == null ) {
            init();
        }
        return tryoutsApi;
    }

}
