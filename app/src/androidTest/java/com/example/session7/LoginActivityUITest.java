package com.example.session7;

import android.view.View;

import androidx.annotation.ContentView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class LoginActivityUITest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void isActivityInView()
    {
        onView(withId(R.id.login_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void isLayoutContentCorrectDisplayed()
    {
        onView(withId(R.id.logo_image)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.app_title)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.emailLogin)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.passwordLogin)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.loginButton)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.createLoginButton)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void isLayoutContentCorrect()
    {
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
        onView(withId(R.id.logo_image)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.app_title)).check(matches(withText(R.string.funny_station)));

        onView(withId(R.id.emailLogin)).check(matches(withText("")));
        onView(withId(R.id.passwordLogin)).check(matches(withText("")));

        onView(withId(R.id.loginButton)).check(matches(withText(R.string.login)));
        onView(withId(R.id.createLoginButton)).check(matches(withText(R.string.no_account_yet_create_one)));

    }

    @Test
    public void navigationSunnyScenario()
    {
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

        onView(withId(R.id.emailLogin)).perform(typeText("spiridon.roxana1999@gmail.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordLogin)).perform(typeText("Fictiune31"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.nav_home)).check(matches(isDisplayed()));
    }

    @Test
    public void navigationNoAccount()
    {
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

        onView(withId(R.id.emailLogin)).perform(typeText("spiridon.roxana1999@gmail.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordLogin)).perform(typeText("Fictiune31"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withId(R.id.create_account_layout)).check(matches(isDisplayed()));


    }

    @Test
    public void loginFailToast()
    {
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

        onView(withId(R.id.emailLogin)).perform(typeText("spsasa9@gmail.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordLogin)).perform(typeText("Fictisadasdsadasune31"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withText("Error occurred! ")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }
}