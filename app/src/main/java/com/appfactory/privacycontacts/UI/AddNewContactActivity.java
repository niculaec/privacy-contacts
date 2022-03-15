package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.contact.ContactsManager;

public class AddNewContactActivity extends AppCompatActivity {
    ContactsManager contactsManager = new ContactsManager();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
    }
}