package com.appfactory.privacycontacts.UI;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import com.appfactory.privacycontacts.R;

public class TestUtils {

    public static void clearStorageAppDetails(){
        InstrumentationRegistry.getInstrumentation().getTargetContext().deleteDatabase("contacts-database");
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(    InstrumentationRegistry.getInstrumentation().getTargetContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * restating the application
     */
    public static void restartApplication(ActivityScenarioRule activityRule) {
        Intents.release();
        activityRule.getScenario().close();
        Intents.init();
        ActivityScenario.launch(new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), LandingActivity.class));
    }

    public static void registerWithPin(){
        onView(withId(R.id.textEnterPin)).perform(ViewActions.typeText("1234"),ViewActions.closeSoftKeyboard());
        onView(withId(R.id.textConfirmPin)).perform(ViewActions.typeText("1234"),ViewActions.closeSoftKeyboard());
        onView(withId(R.id.buttonSetPinOk)).perform(click());
    }

    public static void loginWithPin(){
        onView(withId(R.id.textEnterPin)).perform(ViewActions.typeText("1234"),ViewActions.closeSoftKeyboard());// testing EnterPinActivity
        onView(withId(R.id.buttonLoginOk)).perform(click());
    }
}
