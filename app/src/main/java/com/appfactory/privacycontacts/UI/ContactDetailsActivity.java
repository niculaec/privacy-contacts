package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.appfactory.privacycontacts.utills.Utils;

public class ContactDetailsActivity extends AppCompatActivity {
    ContactsManager contactsManager = ContactsManager.getInstance();
    PhoneInteractor phoneInteractor = new PhoneInteractor(this);

    ImageView iconCall, iconMessage, iconEmail, userPictureImageView;
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
        userPictureImageView = (ImageView) findViewById(R.id.userPicture);

        iconCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneInteractor.makeCall(contact.getPhoneNumber());
            }
        });

        iconMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contact.getPhoneNumber().isEmpty()) {
                    new AlertDialog.Builder(ContactDetailsActivity.this)
                            .setMessage("To send a message, enter a valid number.")
                            .setPositiveButton(android.R.string.ok,null)
                            .show();
                } else {
                    phoneInteractor.sendMessage(contact.getPhoneNumber());
                }
            }
        });

        iconEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneInteractor.sendEmail(contact.getEmailAddress());
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
                new AlertDialog.Builder(ContactDetailsActivity.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this contact?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                contactsManager.removeContact(contact);
                                finish();
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
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
        if (!contact.getUserPicture().isEmpty()) {
            userPictureImageView.setImageBitmap(Utils.getBitmapFromBase64(contact.getUserPicture()));
        }

    }
}