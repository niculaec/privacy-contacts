package com.appfactory.privacycontacts.UI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.appfactory.privacycontacts.ContactAdapter;
import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.contact.Contact;
import com.appfactory.privacycontacts.contact.ContactRepository;
import com.appfactory.privacycontacts.contact.ContactsManager;

import java.util.ArrayList;
import java.util.Comparator;

public class ContactsListActivity extends AppCompatActivity {
    ContactsManager contactsManager = ContactsManager.getInstance();
    private ListView listView;
    FloatingActionButton fob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list_view);
        fob = findViewById(R.id.floatingActionButtonAdd);
        setUpList();
        setUpOnclickListener();
    }
    @Override
    public void onBackPressed()
    {
        finishAffinity();
    }

    private void setUpList() {
        listView = findViewById(R.id.contactListView);
        ContactRepository.ContactsCallBack contactsCallBack = new ContactRepository.ContactsCallBack() {
            @Override
            public void onCallBack(ArrayList<Contact> contactsList) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        contactsList.sort(new Comparator<Contact>() {
                            // usage of Comparator.class to sort the contacts list.
                            @Override
                            public int compare(Contact o1, Contact o2) {
                                return o1.getName().compareTo(o2.getName());
                            }
                        });
                        ContactAdapter arrayAdapter = new ContactAdapter(ContactsListActivity.this, 0, contactsList);
                        contactsManager.setArrayAdapter(arrayAdapter);
                        listView.setAdapter(arrayAdapter);
                    }
                });
            }
        };
        contactsManager.getAllContacts(contactsCallBack);
    }

    private void setUpOnclickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Contact selectedContact = (Contact) listView.getItemAtPosition(position);
                Intent contactDetailIntent = new Intent(ContactsListActivity.this, ContactDetailsActivity.class);
                contactDetailIntent.putExtra(ContactsManager.ID_KEY, selectedContact.getId());
                startActivity(contactDetailIntent);
            }
        });

        fob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsListActivity.this, AddNewContactActivity.class);
                startActivity(intent);
            }
        });
    }
}