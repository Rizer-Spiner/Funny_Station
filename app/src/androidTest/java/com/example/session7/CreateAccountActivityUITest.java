package com.example.session7;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4ClassRunner.class)
public class CreateAccountActivityUITest {

    @Rule
    public ActivityTestRule<CreateAccountActivity> accountActivityActivityTestRule = new ActivityTestRule<>(CreateAccountActivity.class);

    @Test
    public void isActivityInView()
    {
        onView(withId(R.id.create_account_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void isActivityContentDisplayed()
    {
        onView(withText("User name")).check(matches(isDisplayed()));
        onView(withId(R.id.username)).check(matches(isDisplayed()));
        onView(withText("Email")).check(matches(isDisplayed()));
        onView(withId(R.id.email)).check(matches(isDisplayed()));
        onView(withText("Password")).check(matches(isDisplayed()));
        onView(withId(R.id.password)).check(matches(isDisplayed()));
        onView(withText("Repeat password")).check(matches(isDisplayed()));
        onView(withId(R.id.reEnterPassword)).check(matches(isDisplayed()));
        onView(withText("Create Account")).check(matches(isDisplayed()));
        onView(withId(R.id.progressBar2)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));

    }

    @Test
    public void isActivityContentCorrect()
    {
        onView(withText("User name")).check(matches(withText("User name")));
        onView(withId(R.id.username)).check(matches(withText("")));
        onView(withText("Email")).check(matches(withText("Email")));
        onView(withId(R.id.email)).check(matches(withText("")));
        onView(withText("Password")).check(matches(withText("Password")));
        onView(withId(R.id.password)).check(matches(withText("")));
        onView(withText("Repeat password")).check(matches(withText("Repeat password")));
        onView(withId(R.id.reEnterPassword)).check(matches(withText("")));
        onView(withText("Create Account")).check(matches(withText("Create Account")));

    }

    @Test
    public void createAccount()
    {

        onView(withId(R.id.username)).check(matches(withText("New User")));

        onView(withId(R.id.email)).check(matches(withText("roxana@bedtime.com")));

        onView(withId(R.id.password)).check(matches(withText("SomethingP1")));

        onView(withId(R.id.reEnterPassword)).check(matches(withText("SomethingP1")));

        onView(withId(R.id.progressBar2)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withText(R.id.nav_home)).check(matches(isDisplayed()));

    }

    @Test
    public void createAccountFail()
    {

        createAccount();

        onView(withId(R.id.username)).check(matches(withText("New User")));

        onView(withId(R.id.email)).check(matches(withText("roxana@bedtime.com")));

        onView(withId(R.id.password)).check(matches(withText("SomethingP1")));

        onView(withId(R.id.reEnterPassword)).check(matches(withText("SomethingP1")));

        onView(withId(R.id.progressBar2)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(withText("Error occurred! ")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));


    }


}