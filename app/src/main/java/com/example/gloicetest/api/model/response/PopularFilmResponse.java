package com.example.gloicetest.api.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularFilmResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<FilmResult> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<FilmResult> getResults() {
        return results;
    }

    public void setResults(List<FilmResult> results) {
        this.results = results;
    }
}
