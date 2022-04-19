package com.appfactory.privacycontacts.pin;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.appfactory.privacycontacts.utills.Logger;

public class PinRepository {
    public static final String PIN_KEY = "PIN";
    private static Application APPLICATION;


    public static void setApplication(Application application) {
        APPLICATION = application;
    }

    public void savePinHash(String pinNumber) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(APPLICATION);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PIN_KEY, pinNumber);
        editor.apply();
        Logger.log("Pin number was saved in Repository.");
    }

    public String readPinHash() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(APPLICATION);
        String password = preferences.getString(PIN_KEY, "");
        Logger.log("Pin number was successfully read from Repository.");
        return password;
    }
}
