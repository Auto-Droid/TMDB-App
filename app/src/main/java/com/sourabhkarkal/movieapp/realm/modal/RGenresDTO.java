package com.sourabhkarkal.movieapp.realm.modal;

import com.sourabhkarkal.movieapp.modal.GenresDTO;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sourabhkarkal on 23/02/17.
 */

public class RGenresDTO extends RealmObject{

    @PrimaryKey
    private int id;
    private String name;

    public RGenresDTO(){}

    public RGenresDTO(GenresDTO genresDTO){
        setId(genresDTO.getId());
        setName(genresDTO.getName());
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
}
