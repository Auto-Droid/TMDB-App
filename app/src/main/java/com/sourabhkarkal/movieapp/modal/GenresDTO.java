package com.sourabhkarkal.movieapp.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sourabhkarkal.movieapp.realm.modal.RGenresDTO;

/**
 * Created by sourabhkarkal on 23/02/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenresDTO {

    private int id;
    private String name;

    public GenresDTO(){}

    public GenresDTO(RGenresDTO rGenresDTO){
        setId(rGenresDTO.getId());
        setName(rGenresDTO.getName());
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

    @Override
    public String toString() {
        return this.name;
    }
}
