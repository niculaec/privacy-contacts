package com.appfactory.privacycontacts.UI;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.intent.Intents.intended;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import android.content.Intent;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
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
        Intents.init();
        TestUtils.clearStorageAppDetails();
    }

    @After // Clear device storage after testing
    public void clearStorageAfter() {
        Intents.release();
        TestUtils.clearStorageAppDetails();
    }

    @Test
    public void performCallTest() {

        TestUtils.registerWithPin();
        onView(withId(R.id.floatingActionButtonAdd)).perform(click());
        onView(withId(R.id.editTextPersonName)).perform(ViewActions.typeText("Alexandru"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextPhone)).perform(ViewActions.typeText("abd"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editTextEmailAddress)).perform(ViewActions.typeText("Alexandru@gmail.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.buttonSave)).perform(click());

        onView(withText("Alexandru")).perform(ViewActions.click());
        onView(withId(R.id.iconCall)).perform(click());
        intended(IntentMatchers.hasAction(Intent.ACTION_CALL));

    }
}
