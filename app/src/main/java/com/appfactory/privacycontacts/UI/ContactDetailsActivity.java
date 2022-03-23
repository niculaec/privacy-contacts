package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.contact.Contact;
import com.appfactory.privacycontacts.contact.ContactsManager;
import com.appfactory.privacycontacts.utills.PhoneInteractor;

public class ContactDetailsActivity extends AppCompatActivity {
    ContactsManager contactsManager = new ContactsManager();
    PhoneInteractor phoneInteractor = new PhoneInteractor();

    ImageView iconCall , iconMessage, iconEmail, userPicture;
    Button deleteButton, editButton;
    TextView personNameTextView, phoneNumberTextView, emailAddressTextView;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        personNameTextView = findViewById(R.id.personNameTextView);
        phoneNumberTextView = findViewById(R.id.phoneTextView);
        emailAddressTextView = findViewById(R.id.emailAddressTextView);
        iconCall = findViewById(R.id.iconCall);
        iconMessage = findViewById(R.id.iconMessage);
        iconEmail = findViewById(R.id.iconEmail);
        deleteButton = findViewById(R.id.buttonDelete);
        editButton = findViewById(R.id.buttonEdit);
        userPicture = findViewById(R.id.userPicture);
        
        iconCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactDetailsActivity.this, "Icon clickable test.", Toast.LENGTH_LONG).show();
            }
        });

        iconMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactDetailsActivity.this, "Icon clickable test.", Toast.LENGTH_LONG).show();
            }
        });

        iconEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactDetailsActivity.this, "Icon clickable test.", Toast.LENGTH_LONG).show();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactsManager.removeContact(contact);
            }
        });

        //In the future we will receive contactId when the activity starts.
        contact = contactsManager.getAllContacts().get(0);
        personNameTextView.setText(contact.getName());
        phoneNumberTextView.setText(contact.getPhoneNumber());
        emailAddressTextView.setText(contact.getEmailAddress());
        //userPicture.setImageURI(contact.getUserPicture());
    }
}