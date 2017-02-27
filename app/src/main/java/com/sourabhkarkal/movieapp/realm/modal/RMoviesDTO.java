package com.sourabhkarkal.movieapp.realm.modal;

import com.sourabhkarkal.movieapp.modal.MoviesDTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sourabhkarkal on 21/02/17.
 */

public class RMoviesDTO extends RealmObject{

    @PrimaryKey
    private String id;
    private String poster_path;
    private boolean adult;
    private String overview;
    private Date release_date;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private float vote_average;

    public RMoviesDTO(){}

    public RMoviesDTO(MoviesDTO moviesDTO){
        setId(moviesDTO.getId());
        setPoster_path(moviesDTO.getPoster_path());
        setAdult(moviesDTO.isAdult());
        setOverview(moviesDTO.getOverview());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(moviesDTO.getRelease_date());
            setRelease_date(date);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        setOriginal_title(moviesDTO.getOriginal_title());
        setOriginal_language(moviesDTO.getOriginal_language());
        setTitle(moviesDTO.getTitle());
        setBackdrop_path(moviesDTO.getBackdrop_path());
        setPopularity(moviesDTO.getPopularity());
        setVote_count(moviesDTO.getVote_count());
        setVideo(moviesDTO.isVideo());
        setVote_average(moviesDTO.getVote_average());
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }
}
