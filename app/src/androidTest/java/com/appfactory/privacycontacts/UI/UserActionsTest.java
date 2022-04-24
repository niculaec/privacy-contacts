package com.appfactory.privacycontacts.UI;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.appfactory.privacycontacts.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UserActionsTest {
    @Rule //the rule of test
    public ActivityScenarioRule<LandingActivity> activityRule =
            new ActivityScenarioRule<>(LandingActivity.class);
    @Before // Clear device storage before test to start
    public void clearStorage() {
        TestUtils.clearStorageAppDetails();
    }

    @After // Clear device storage after testing
    public void clearStorageAfter() {
        TestUtils.clearStorageAppDetails();
    }

    @Test
    public void performCallTest() {
        TestUtils.registerWithPin();
        onView(withId(R.id.floatingActionButtonAdd)).perform(click());
        onView(withId(R.id.editTextPersonName)).perform(ViewActions.typeText("Alexandru"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextPhone)).perform(ViewActions.typeText("077777777"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextEmailAddress)).perform(ViewActions.typeText("Alexandru@gmail.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.buttonSave)).perform(click());

        TestUtils.restartApplication(activityRule);

        TestUtils.loginWithPin();
        onView(withText("Alexandru")).perform(ViewActions.click());
        onView(withId(R.id.iconCall)).perform(click());
        TestUtils.allowPermissionsIfNeeded();
        // onView(withId(R.id.iconCall)).perform(click());

    }
}
