package com.example.session7.ui.folders;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.session7.MainActivity3;
import com.example.session7.R;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner.class)
public class FolderFragmentUITest {

    @Before
    public void setUp() {
        ActivityScenario<MainActivity3> activityScenario = ActivityScenario.launch(MainActivity3.class);
        onView(withId(R.id.nav_folders)).perform(click());
    }


    @Test
    public void isFragmentDisplayed() {
        onView(withId(R.id.folder_fragment)).check(matches(isDisplayed()));
    }


    @Test
    public void isFragmentContentDisplayed() {
        onView(withId(R.id.recycleView)).check(matches(isDisplayed()));
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
        onView(withId(R.id.fab2)).check(matches(isDisplayed()));
    }

    @Test
    public void isNavigationCorrect() {

    }
}