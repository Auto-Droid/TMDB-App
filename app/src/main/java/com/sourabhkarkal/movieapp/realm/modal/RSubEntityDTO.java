package com.sourabhkarkal.movieapp.realm.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sourabhkarkal.movieapp.modal.SubEntityDTO;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sourabhkarkal on 25/02/17.
 */
public class RSubEntityDTO extends RealmObject{

    @PrimaryKey
    private int id;
    private String name;
    private String poster_path;
    private String backdrop_path;

    public RSubEntityDTO(){}

    public RSubEntityDTO(SubEntityDTO subEntityDTO){
        if(subEntityDTO!=null) {
            setId(subEntityDTO.getId());
            setName(subEntityDTO.getName());
            setBackdrop_path(subEntityDTO.getBackdrop_path());
            setPoster_path(subEntityDTO.getPoster_path());
        }
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
