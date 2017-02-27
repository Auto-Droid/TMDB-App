package com.sourabhkarkal.movieapp.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sourabhkarkal.movieapp.realm.modal.RSubEntityDTO;

/**
 * Created by sourabhkarkal on 23/02/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubEntityDTO {

    private int id;
    private String name;
    private String poster_path;
    private String backdrop_path;

    public SubEntityDTO(){}

    public SubEntityDTO(RSubEntityDTO rSubEntityDTO){
        setId(rSubEntityDTO.getId());
        setName(rSubEntityDTO.getName());
        setBackdrop_path(rSubEntityDTO.getBackdrop_path());
        setPoster_path(rSubEntityDTO.getPoster_path());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
