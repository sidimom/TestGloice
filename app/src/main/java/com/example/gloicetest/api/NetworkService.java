package com.example.gloicetest.api;

import com.example.gloicetest.utils.Const;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetworkService {
    private static final NetworkService instance = new NetworkService();
    private AhuApi ahuApi;

    public static NetworkService getInstance() {
        return instance;
    }

    private NetworkService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ahuApi = retrofit.create(AhuApi.class);
    }

    public AhuApi getApi() {
        return ahuApi;
    }
}
