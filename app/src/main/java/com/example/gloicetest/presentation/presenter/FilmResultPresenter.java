package com.example.gloicetest.presentation.presenter;

import com.example.gloicetest.api.NetworkService;
import com.example.gloicetest.api.model.response.PopularFilmResponse;
import com.example.gloicetest.presentation.view.FilmResultView;
import com.example.gloicetest.utils.Const;

import org.jetbrains.annotations.NotNull;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class FilmResultPresenter extends MvpPresenter<FilmResultView> {

    public void getFilms(int page){
        getViewState().showLoading();

        NetworkService.getInstance().getApi()
                .getPopularFilms(Const.API_KEY, Const.LANGUAGE, page)
                .enqueue(new Callback<PopularFilmResponse>() {
                    @Override
                    public void onResponse(Call<PopularFilmResponse> call, @NotNull Response<PopularFilmResponse> response) {
                        getViewState().hideLoading();

                        PopularFilmResponse popularFilm = response.body();
                        if (popularFilm != null)
                            getViewState().setFilmResult(popularFilm.getTotalPages(), popularFilm.getResults());
                    }

                    @Override
                    public void onFailure(Call<PopularFilmResponse> call, Throwable t) {
                        getViewState().hideLoading();
                        getViewState().showError(t.toString());
                    }
                });
    }
}
