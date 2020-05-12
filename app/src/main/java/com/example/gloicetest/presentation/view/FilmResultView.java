package com.example.gloicetest.presentation.view;

import com.example.gloicetest.api.model.response.FilmResult;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface FilmResultView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showLoading();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void hideLoading();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(String error);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setFilmResult(int totalPages, List<FilmResult> results);
}
