package com.sourabhkarkal.movieapp.realm.modal;

import com.sourabhkarkal.movieapp.modal.GenresDTO;
import com.sourabhkarkal.movieapp.modal.MovieDetailDTO;
import com.sourabhkarkal.movieapp.modal.RegionDTO;
import com.sourabhkarkal.movieapp.modal.SubEntityDTO;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sourabhkarkal on 24/02/17.
 */

public class RMovieDetailDTO extends RealmObject {

    @PrimaryKey
    private int id;
    private boolean adult;
    private String backdrop_path;
    private int budget;
    private String homepage;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private String poster_path;
    private double popularity;
    private String release_date;
    private int revenue;
    private int runtime;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private float vote_average;
    private int vote_count;
    private RealmList<RGenresDTO> genres;
    private RealmList<RGenresDTO> production_companies;
    private RealmList<RRegionDTO> production_countries;
    private RSubEntityDTO belongs_to_collection;

    public RMovieDetailDTO(){}

    public RMovieDetailDTO(MovieDetailDTO movieDetailDTO){
        setId(movieDetailDTO.getId());
        setAdult(movieDetailDTO.isAdult());
        setBackdrop_path(movieDetailDTO.getBackdrop_path());
        setBudget(movieDetailDTO.getBudget());
        setHomepage(movieDetailDTO.getHomepage());
        setImdb_id(movieDetailDTO.getImdb_id());
        setOriginal_language(movieDetailDTO.getOriginal_language());
        setOriginal_title(movieDetailDTO.getOriginal_title());
        setOverview(movieDetailDTO.getOverview());
        setPoster_path(movieDetailDTO.getPoster_path());
        setPopularity(movieDetailDTO.getPopularity());
        setRelease_date(movieDetailDTO.getRelease_date());
        setRevenue(movieDetailDTO.getRevenue());
        setRuntime(movieDetailDTO.getRuntime());
        setStatus(movieDetailDTO.getStatus());
        setTagline(movieDetailDTO.getTagline());
        setTitle(movieDetailDTO.getTitle());
        setVideo(movieDetailDTO.isVideo());
        setVote_average(movieDetailDTO.getVote_average());
        setVote_count(movieDetailDTO.getVote_count());
        if(movieDetailDTO.getGenres()!=null) {
            RealmList<RGenresDTO> genresDTOs = new RealmList<>();
            for(GenresDTO genresDTO:movieDetailDTO.getGenres())
                genresDTOs.add(new RGenresDTO(genresDTO));
            setGenres(genresDTOs);
        }
        if(movieDetailDTO.getProduction_companies()!=null) {
            RealmList<RGenresDTO> companiesDTO = new RealmList<>();
            for(GenresDTO genresDTO:movieDetailDTO.getProduction_companies())
                companiesDTO.add(new RGenresDTO(genresDTO));
            setProduction_companies(companiesDTO);
        }
        if(movieDetailDTO.getProduction_countries()!=null) {
            RealmList<RRegionDTO> countriesDTO = new RealmList<>();
            for(RegionDTO regionDTO:movieDetailDTO.getProduction_countries())
                countriesDTO.add(new RRegionDTO(regionDTO));
            setProduction_countries(countriesDTO);
        }
        setBelongs_to_collection(new RSubEntityDTO(movieDetailDTO.getBelongs_to_collection()));

    }


    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public RealmList<RGenresDTO> getGenres() {
        return genres;
    }

    public void setGenres(RealmList<RGenresDTO> genres) {
        this.genres = genres;
    }

    public RealmList<RGenresDTO> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(RealmList<RGenresDTO> production_companies) {
        this.production_companies = production_companies;
    }

    public RealmList<RRegionDTO> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(RealmList<RRegionDTO> production_countries) {
        this.production_countries = production_countries;
    }

    public RSubEntityDTO getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public void setBelongs_to_collection(RSubEntityDTO belongs_to_collection) {
        this.belongs_to_collection = belongs_to_collection;
    }
}
