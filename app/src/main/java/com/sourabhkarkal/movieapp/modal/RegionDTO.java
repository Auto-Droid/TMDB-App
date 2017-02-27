package com.sourabhkarkal.movieapp.modal;

import com.sourabhkarkal.movieapp.realm.modal.RRegionDTO;

/**
 * Created by sourabhkarkal on 23/02/17.
 */

public class RegionDTO {

    private String iso_3166_1;
    private String name;


    public RegionDTO(){}

    public RegionDTO(RRegionDTO rRegionDTO){
        setIso_3166_1(rRegionDTO.getIso_3166_1());
        setName(rRegionDTO.getName());
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

    @Override
    public String toString() {
        return this.name;
    }
}
