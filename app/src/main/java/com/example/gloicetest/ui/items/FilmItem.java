package com.example.gloicetest.ui.items;

import com.example.gloicetest.api.model.response.FilmResult;

import java.io.Serializable;
import java.util.List;

public class FilmItem implements Serializable {
    private String posterPath;
    private boolean adult;
    private String overview;
    private String releaseDate;
    private List<Integer> genreIds;
    private int id;
    private String originalTitle;
    private String originalLanguage;
    private String title;
    private String backdropPath;
    private float popularity;
    private int voteCount;
    private boolean video;
    private float voteAverage;

    public FilmItem(FilmResult filmResult) {
        this.posterPath = filmResult.getPosterPath();
        this.adult = filmResult.isAdult();
        this.overview = filmResult.getOverview();
        this.releaseDate = filmResult.getReleaseDate();
        this.genreIds = filmResult.getGenreIds();
        this.id = filmResult.getId();
        this.originalTitle = filmResult.getOriginalTitle();
        this.originalLanguage = filmResult.getOriginalLanguage();
        this.title = filmResult.getTitle();
        this.backdropPath = filmResult.getBackdropPath();
        this.popularity = filmResult.getPopularity();
        this.voteCount = filmResult.getVoteCount();
        this.video = filmResult.isVideo();
        this.voteAverage = filmResult.getVoteAverage();
    }

    public String getPosterPath() {
        return posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }
}
