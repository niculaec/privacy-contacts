package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.appfactory.privacycontacts.ContactAdapter;
import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.contact.Contact;
import com.appfactory.privacycontacts.contact.ContactsManager;

import java.util.ArrayList;

public class ContactsListActivity extends AppCompatActivity {
    ContactsManager contactsManager = new ContactsManager();
    static final ArrayList<Contact> contactsList = new ArrayList<Contact>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list_view);

        setUpList();
        setUpOnclickListener();

//        Collections.sort(contactsList, new Comparator<Contact>() {
//            @Override
//            public int compare(Contact o1, Contact o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        });

        ContactAdapter arrayAdapter = new ContactAdapter(this, 0, contactsList);
        ListView listView = findViewById(R.id.contactListView);
        listView.setAdapter(arrayAdapter);
    }

    private void setUpList() {
        listView = (ListView) findViewById(R.id.contactListView);
        ContactAdapter adapter = new ContactAdapter(getApplicationContext(),0,contactsList);
        listView.setAdapter(adapter);
    }
    private void setUpOnclickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Contact selectContact = (Contact) (listView.getItemAtPosition(position));
                Intent contactDetail = new Intent(getApplicationContext(), ContactDetailsActivity.class);
                contactDetail.putExtra("id",selectContact.getId());
                startActivity(contactDetail);
            }
        });

    }
}