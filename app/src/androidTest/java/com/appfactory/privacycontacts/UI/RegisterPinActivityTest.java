package com.appfactory.privacycontacts.UI;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import androidx.test.core.app.ActivityScenario;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.UI.LandingActivity;


@RunWith(AndroidJUnit4.class)
public class RegisterPinActivityTest {

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

    @Test // Testing the textView hints
    public void pinEditTextHintTest() {
        onView(ViewMatchers.withId(R.id.textEnterPin)).check(matches(withHint("Please enter your PIN")));
        onView(withId(R.id.textConfirmPin)).check(matches(withHint("Confirm PIN")));
    }

    @Test
    public void registerAndLoginTest() {
        TestUtils.registerWithPin();

        TestUtils.restartApplication(activityRule);

        TestUtils.loginWithPin();

        onView(withId(R.id.contactListView)).check(matches(isDisplayed()));
    }
}
