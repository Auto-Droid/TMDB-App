package com.sourabhkarkal.movieapp.realm.modal;

import com.sourabhkarkal.movieapp.modal.RegionDTO;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sourabhkarkal on 23/02/17.
 */

public class RRegionDTO extends RealmObject{

    @PrimaryKey
    private String iso_3166_1;
    private String name;

    public RRegionDTO(){}

    public RRegionDTO(RegionDTO regionDTO){
        setIso_3166_1(regionDTO.getIso_3166_1());
        setName(regionDTO.getName());
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
