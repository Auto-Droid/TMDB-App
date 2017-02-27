package com.sourabhkarkal.movieapp.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.sourabhkarkal.movieapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeScreenUITest {

    @Rule
    public ActivityTestRule<HomeScreen> mActivityTestRule = new ActivityTestRule<>(HomeScreen.class);

    @Test
    public void homeScreenUITest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rvMovieList), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(2, click()));


        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnDetailBook), withText("BOOK TICKETS"),
                        withParent(allOf(withId(R.id.htab_maincontent),
                                withParent(withId(R.id.mainfragment)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        pressBack();

        pressBack();

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.rvMovieList), isDisplayed()));
        recyclerView3.perform(actionOnItemAtPosition(19, click()));

        pressBack();

    }

}
