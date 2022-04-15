package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
    ContactsManager contactsManager = ContactsManager.getInstance();
    PhoneInteractor phoneInteractor = new PhoneInteractor(this);

    ImageView iconCall, iconMessage, iconEmail, userPicture;
    Button deleteButton, editButton;
    TextView personNameTextView, phoneNumberTextView, emailAddressTextView;
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        personNameTextView = (TextView) findViewById(R.id.personNameTextViewSingleItem);
        phoneNumberTextView = findViewById(R.id.phoneTextView);
        emailAddressTextView = findViewById(R.id.emailAddressTextView);
        iconCall = findViewById(R.id.iconCall);
        iconMessage = findViewById(R.id.iconMessage);
        iconEmail = findViewById(R.id.iconEmail);
        deleteButton = findViewById(R.id.buttonDelete);
        editButton = findViewById(R.id.buttonEdit);
        userPicture = (ImageView) findViewById(R.id.userPicture);

        iconCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneInteractor.makeCall(contact.getPhoneNumber());
                Toast.makeText(ContactDetailsActivity.this, "Icon clickable test.", Toast.LENGTH_LONG).show();
            }
        });

        iconMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneInteractor.sendMessage(contact.getPhoneNumber());
                Toast.makeText(ContactDetailsActivity.this, "Icon clickable test.", Toast.LENGTH_LONG).show();
            }
        });

        iconEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneInteractor.sendEmail(contact.getEmailAddress());
                Toast.makeText(ContactDetailsActivity.this, "Icon clickable test.", Toast.LENGTH_LONG).show();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editContactIntent = new Intent(ContactDetailsActivity.this, AddNewContactActivity.class);
                editContactIntent.putExtra(ContactsManager.ID_KEY, contact.getId());
                startActivity(editContactIntent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactsManager.removeContact(contact);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSelectedContact();
        setValues();
    }

    private void getSelectedContact() {
        Intent currentIntent = getIntent();
        String parsedID = currentIntent.getStringExtra(ContactsManager.ID_KEY);
        contact = contactsManager.getContact(parsedID);
    }

    private void setValues() {
        personNameTextView.setText(contact.getName());
        phoneNumberTextView.setText(contact.getPhoneNumber());
        emailAddressTextView.setText(contact.getEmailAddress());
        userPicture.setImageURI(Uri.parse(contact.getUserPicture()));
    }
}