package com.sourabhkarkal.movieapp.activity;

/**
 * Created by sourabhkarkal on 27/02/17.
 */


import com.sourabhkarkal.movieapp.modal.MoviesDTO;
import com.sourabhkarkal.movieapp.realm.modal.RGenresDTO;
import com.sourabhkarkal.movieapp.realm.modal.RMovieDetailDTO;
import com.sourabhkarkal.movieapp.realm.modal.RMoviesDTO;
import com.sourabhkarkal.movieapp.realm.modal.RRegionDTO;
import com.sourabhkarkal.movieapp.realm.modal.RSubEntityDTO;

import org.powermock.api.mockito.PowerMockito;

import io.realm.Realm;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public class MockSupport {

    public static Realm mockRealm() {
        mockStatic(Realm.class);

        Realm mockRealm = PowerMockito.mock(Realm.class);

        when(mockRealm.createObject(RRegionDTO.class)).thenReturn(new RRegionDTO());
        when(mockRealm.createObject(RSubEntityDTO.class)).thenReturn(new RSubEntityDTO());
        when(mockRealm.createObject(RGenresDTO.class)).thenReturn(new RGenresDTO());
        when(mockRealm.createObject(RMoviesDTO.class)).thenReturn(new RMoviesDTO());
        when(mockRealm.createObject(RMovieDetailDTO.class)).thenReturn(new RMovieDetailDTO());

        when(Realm.getDefaultInstance()).thenReturn(mockRealm);

        return mockRealm;
    }
}
