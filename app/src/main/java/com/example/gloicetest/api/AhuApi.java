package com.example.gloicetest.api;

import com.example.gloicetest.api.model.response.PopularFilmResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AhuApi {
    @GET("/3/movie/popular")
    Call<PopularFilmResponse> getPopularFilms(@Query("api_key") String id, @Query("language") String language, @Query("page") int page);
}
