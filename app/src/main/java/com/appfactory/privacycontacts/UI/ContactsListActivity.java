package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.appfactory.privacycontacts.ContactAdapter;
import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.contact.Contact;
import com.appfactory.privacycontacts.contact.ContactsManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ContactsListActivity extends AppCompatActivity {
    ContactsManager contactsManager = new ContactsManager();
    private final ArrayList<Contact> contactsList = new ArrayList<Contact>();
    ImageView userPicture;
    TextView personNameTextView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        listView = findViewById(R.id.contactListView);

        Collections.sort(contactsList, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        ContactAdapter arrayAdapter = new ContactAdapter(this, contactsList);
        ListView listView = findViewById(R.id.contactListView);
        listView.setAdapter(arrayAdapter);
    }
}