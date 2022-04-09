package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.appfactory.privacycontacts.ContactAdapter;
import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.contact.Contact;
import com.appfactory.privacycontacts.contact.ContactsManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ContactsListActivity extends AppCompatActivity {
    ContactsManager contactsManager = ContactsManager.getInstance();
    private ListView listView;
    FloatingActionButton fob;

    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list_view);
        fob = findViewById(R.id.floatingActionButtonAdd);
        setUpList();
        setUpOnclickListener();

        contactsManager.getAllContacts().sort(new Comparator<Contact>() {
            // usage of Comparator.class to sort the contacts list.
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(listView.getAdapter());
    }

    private void setUpList() {
        listView = (ListView) findViewById(R.id.contactListView);
        ContactAdapter arrayAdapter = new ContactAdapter(this, 0, contactsManager.getAllContacts());
        listView.setAdapter(arrayAdapter);
    }
    private void setUpOnclickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Contact selectedContact =(Contact) listView.getItemAtPosition(position);
                Intent contactDetailIntent = new Intent(ContactsListActivity.this, ContactDetailsActivity.class);
                contactDetailIntent.putExtra(ContactsManager.ID_KEY,selectedContact.getId());
                startActivity(contactDetailIntent);
            }
        });

        fob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsListActivity.this,AddNewContactActivity.class);
                startActivity(intent);
                contactsManager.addContact(contact);
            }
        });
    }
}