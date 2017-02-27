package com.sourabhkarkal.movieapp.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sourabhkarkal.movieapp.realm.modal.RGenresDTO;
import com.sourabhkarkal.movieapp.realm.modal.RMovieDetailDTO;
import com.sourabhkarkal.movieapp.realm.modal.RRegionDTO;
import com.sourabhkarkal.movieapp.realm.modal.RSubEntityDTO;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by sourabhkarkal on 24/02/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetailDTO {

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
    private List<GenresDTO> genres;
    private List<GenresDTO> production_companies;
    private List<RegionDTO> production_countries;
    private SubEntityDTO belongs_to_collection;

    public MovieDetailDTO(){}

    public MovieDetailDTO(RMovieDetailDTO rMovieDetailDTO){
        setId(rMovieDetailDTO.getId());
        setAdult(rMovieDetailDTO.isAdult());
        setBackdrop_path(rMovieDetailDTO.getBackdrop_path());
        setBudget(rMovieDetailDTO.getBudget());
        setHomepage(rMovieDetailDTO.getHomepage());
        setImdb_id(rMovieDetailDTO.getImdb_id());
        setOriginal_language(rMovieDetailDTO.getOriginal_language());
        setOriginal_title(rMovieDetailDTO.getOriginal_title());
        setOverview(rMovieDetailDTO.getOverview());
        setPoster_path(rMovieDetailDTO.getPoster_path());
        setPopularity(rMovieDetailDTO.getPopularity());
        setRelease_date(rMovieDetailDTO.getRelease_date());
        setRevenue(rMovieDetailDTO.getRevenue());
        setRuntime(rMovieDetailDTO.getRuntime());
        setStatus(rMovieDetailDTO.getStatus());
        setTagline(rMovieDetailDTO.getTagline());
        setTitle(rMovieDetailDTO.getTitle());
        setVideo(rMovieDetailDTO.isVideo());
        setVote_average(rMovieDetailDTO.getVote_average());
        setVote_count(rMovieDetailDTO.getVote_count());
        if(rMovieDetailDTO.getGenres()!=null) {
            ArrayList<GenresDTO> genresDTOs = new ArrayList<>();
            for(RGenresDTO rGenresDTO:rMovieDetailDTO.getGenres())
                genresDTOs.add(new GenresDTO(rGenresDTO));
            setGenres(genresDTOs);
        }
        if(rMovieDetailDTO.getProduction_companies()!=null) {
            ArrayList<GenresDTO> companiesDTO = new ArrayList<>();
            for(RGenresDTO rGenresDTO:rMovieDetailDTO.getProduction_companies())
                companiesDTO.add(new GenresDTO(rGenresDTO));
            setProduction_companies(companiesDTO);
        }
        if(rMovieDetailDTO.getProduction_countries()!=null) {
            ArrayList<RegionDTO> countriesDTO = new ArrayList<>();
            for(RRegionDTO rRegionDTO:rMovieDetailDTO.getProduction_countries())
                countriesDTO.add(new RegionDTO(rRegionDTO));
            setProduction_countries(countriesDTO);
        }
        setBelongs_to_collection(new SubEntityDTO(rMovieDetailDTO.getBelongs_to_collection()));

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

    public List<GenresDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresDTO> genres) {
        this.genres = genres;
    }

    public List<GenresDTO> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<GenresDTO> production_companies) {
        this.production_companies = production_companies;
    }

    public List<RegionDTO> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<RegionDTO> production_countries) {
        this.production_countries = production_countries;
    }

    public SubEntityDTO getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public void setBelongs_to_collection(SubEntityDTO belongs_to_collection) {
        this.belongs_to_collection = belongs_to_collection;
    }
}
