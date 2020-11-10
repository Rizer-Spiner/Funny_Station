package com.example.session7.ui.getMeAJoke;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.session7.MainActivity3;
import com.example.session7.R;
import com.example.session7.ToastMatcher;
import com.example.session7.ui.dialog.SaveJokeDialog;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner.class)
public class GetMeAJokeFragmentTest {

    @Before
    public void setUp()
    {
        ActivityScenario<MainActivity3> activityScenario = ActivityScenario.launch(MainActivity3.class);
        onView(withId(R.id.nav_get_me_a_joke)).perform(click());
    }


    @Test
    public void isFragmentDisplayed()
    {
        onView(withId(R.id.getMeAJoke_fragment)).check(matches(isDisplayed()));
    }


    @Test
    public void isFragmentContentDisplayed()
    {
        onView(withId(R.id.button6)).check(matches(isDisplayed()));
        onView(withId(R.id.button8)).check(matches(withText("Get me a Joke")));
        onView(withId(R.id.textView8)).check(matches(isDisplayed()));
        onView(withId(R.id.textView8)).check(matches(withText("")));
        onView(withId(R.id.button8)).check(matches(isDisplayed()));
        onView(withText(R.id.button8)).check(matches(withText("Save")));
    }

    @Test
    public void isSaveWithNoJokeNavigationCorrect()
    {
        onView(withId(R.id.textView8)).perform(typeText(""));
        onView(withId(R.id.button8)).perform(click());
        onView(withText("Get your joke first!")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));

    }



    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}