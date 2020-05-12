package com.example.gloicetest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gloicetest.api.model.response.FilmResult;
import com.example.gloicetest.databinding.ActivityMainBinding;
import com.example.gloicetest.presentation.presenter.FilmResultPresenter;
import com.example.gloicetest.presentation.view.FilmResultView;
import com.example.gloicetest.ui.adapter.FilmAdapter;
import com.example.gloicetest.ui.items.FilmItem;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements FilmResultView {

    public static final String FILM_KEY = "film_key";

    @InjectPresenter
    FilmResultPresenter presenter;
    private ActivityMainBinding binding;
    private FilmAdapter adapter;
    private int page = 1;
    private int totalPages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setRecyclerView();
        setArrowListener();
        refreshArrows();
        setPage();
    }

    private void setRecyclerView() {
        adapter = new FilmAdapter();
        adapter.setOnItemClickListener(item -> {
            Intent intent = new Intent(MainActivity.this, FilmDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(FILM_KEY, new FilmItem(item));
            intent.putExtras(bundle);
            startActivity(intent);
        });
        binding.rvFilms.setAdapter(adapter);
    }

    private void setArrowListener() {
        binding.ivArrowLeft.setOnClickListener(v -> {
            page --;
            setPage();

        });

        binding.ivArrowRight.setOnClickListener(v -> {
            page++;
            setPage();
        });
    }

    private void setPage() {
        binding.tvNumber.setText(String.valueOf(page));
        presenter.getFilms(page);
    }

    private void refreshArrows() {
        binding.ivArrowLeft.setEnabled(page - 1 > 0);
        binding.ivArrowRight.setEnabled(page < totalPages);
    }

    @Override
    public void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
        adapter.setEnable(false);
        binding.ivArrowRight.setEnabled(false);
        binding.ivArrowLeft.setEnabled(false);
    }

    @Override
    public void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
        adapter.setEnable(true);
        binding.ivArrowRight.setEnabled(true);
        binding.ivArrowLeft.setEnabled(true);
    }

    @Override
    public void showError(String error) {
        Snackbar.make(binding.snackbarContainer, error, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void setFilmResult(int totalPages, List<FilmResult> results) {
        this.totalPages = totalPages;
        if (results != null)
            adapter.setData(results);

        refreshArrows();
    }
}
