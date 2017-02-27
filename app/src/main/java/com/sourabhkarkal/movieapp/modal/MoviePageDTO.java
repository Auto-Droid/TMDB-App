package com.sourabhkarkal.movieapp.modal;

import java.util.List;

/**
 * Created by sourabhkarkal on 24/02/17.
 */

public class MoviePageDTO {

    private int page;
    private List<MoviesDTO> results;
    private int total_results;
    private int total_pages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MoviesDTO> getResults() {
        return results;
    }

    public void setResults(List<MoviesDTO> results) {
        this.results = results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
