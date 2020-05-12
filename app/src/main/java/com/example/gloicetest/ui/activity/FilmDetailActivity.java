package com.example.gloicetest.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.gloicetest.R;
import com.example.gloicetest.databinding.ActivityFilmDetailBinding;
import com.example.gloicetest.ui.items.FilmItem;
import com.example.gloicetest.utils.Const;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class FilmDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFilmDetailBinding binding = ActivityFilmDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getArguments();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            FilmItem filmResult = (FilmItem) bundle.getSerializable(MainActivity.FILM_KEY);
            if (filmResult != null){
                binding.tvFilmName.setText(filmResult.getTitle());
                binding.tvVote.setText(String.valueOf(filmResult.getVoteAverage()));
                binding.tvPopularity.setText(String.valueOf(filmResult.getPopularity()));
                binding.tvDescription.setText(filmResult.getOverview());
                String dateFormatFrom = "yyyy-MM-dd";
                String dateFormatTo = "dd.MM.yyyy";
                try {
                    @SuppressLint("SimpleDateFormat") Date date = new SimpleDateFormat(dateFormatFrom).parse(filmResult.getReleaseDate());
                    if (date != null){
                        @SuppressLint("SimpleDateFormat") String dateString = new SimpleDateFormat(dateFormatTo).format(date);
                        binding.tvReleaseDate.setText(dateString);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(filmResult.getPosterPath())){
                    binding.ivPoster.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_poster_not_found));
                }else{
                    Picasso.get()
                            .load(Const.IMAGE_URL + filmResult.getPosterPath())
                            .placeholder(R.drawable.ic_poster_not_found)
                            .error(R.drawable.ic_poster_not_found)
                            .into(binding.ivPoster);
                }
            }
        }

    }

    private void getArguments() {
    }
}
