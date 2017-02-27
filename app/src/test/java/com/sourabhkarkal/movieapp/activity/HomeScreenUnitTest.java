package com.sourabhkarkal.movieapp.activity;


import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourabhkarkal.movieapp.BuildConfig;
import com.sourabhkarkal.movieapp.modal.MoviePageDTO;
import com.sourabhkarkal.movieapp.service.DataRequest;
import com.sourabhkarkal.movieapp.service.DataResponse;
import com.sourabhkarkal.movieapp.service.RestTemplateExecutor;
import com.sourabhkarkal.movieapp.service.iWebListener;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.internal.IOException;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest({Realm.class})

public class HomeScreenUnitTest{

    Realm mockRealm;

    @Before
    public void setup() {
        mockRealm = MockSupport.mockRealm();
    }

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Test
    public void realmShouldBeMocked() {
        assertThat(Realm.getDefaultInstance(), is(mockRealm));
    }

    @Test
    public void testDateMethod(){
        Calendar c = Calendar.getInstance();
        System.out.println("Current time -> " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        assertNotNull(formattedDate);

    }

}
