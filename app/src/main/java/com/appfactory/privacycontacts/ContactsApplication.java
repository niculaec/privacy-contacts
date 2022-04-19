package com.appfactory.privacycontacts;

import android.app.Application;

import com.appfactory.privacycontacts.contact.ContactRepository;
import com.appfactory.privacycontacts.pin.PinManager;
import com.appfactory.privacycontacts.pin.PinRepository;

public class ContactsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PinRepository.setApplication(this);
        ContactRepository.setApplication(this);
    }
}
