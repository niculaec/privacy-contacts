package com.appfactory.privacycontacts;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;
import android.provider.ContactsContract;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


import com.appfactory.privacycontacts.UI.ContactsListActivity;
import com.appfactory.privacycontacts.UI.EnterPinActivity;
import com.appfactory.privacycontacts.UI.LandingActivity;
import com.appfactory.privacycontacts.UI.SetPinActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<LandingActivity> activityRule =
            new ActivityScenarioRule<>(LandingActivity.class);
    @Test
    public void pinEditTextHintTest() {
        onView(withId(R.id.textEnterPin)).check(matches(withHint("Please enter your PIN")));
    }

    @Test
    public void registerPinTest() {
        onView(withId(R.id.textEnterPin)).perform(ViewActions.typeText("1234"),ViewActions.closeSoftKeyboard());
        onView(withId(R.id.textConfirmPin)).perform(ViewActions.typeText("1234"),ViewActions.closeSoftKeyboard());
        onView(withId(R.id.buttonSetPinOk)).perform(click());

        clearStorageAppDetails();// at the end of the test

    }

    public void clearStorageAppDetails(){
        InstrumentationRegistry.getInstrumentation().getTargetContext().deleteDatabase("contacts-database");
        InstrumentationRegistry.getInstrumentation().getTargetContext().deleteSharedPreferences("com.appfactory.privacycontacts_preferences");
        //InstrumentationRegistry.getInstrumentation().getTargetContext().
    }

    public void resetApp(){
            ContactsContract.Intents.release()
            rule.scenario.close()
            ContactsContract.Intents.init()
            ActivityScenario.launch<LandingActivity>(intent)
    }
}
