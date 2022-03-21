package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.contact.Contact;
import com.appfactory.privacycontacts.contact.ContactsManager;

public class ContactDetailsActivity extends AppCompatActivity {
    ContactsManager contactsManager = new ContactsManager();

    ImageView iconCall , iconMessage, iconEmail, userPicture;
    Button deleteButton, editButton;
    EditText nameEditText, phoneNumberEditText, emailAddressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        nameEditText = findViewById(R.id.editTextPersonName);
        phoneNumberEditText = findViewById(R.id.editTextPhone);
        emailAddressEditText = findViewById(R.id.editTextEmailAddress);
        iconCall = findViewById(R.id.iconCall);
        iconMessage = findViewById(R.id.iconMessage);
        iconEmail = findViewById(R.id.iconEmail);
        deleteButton = findViewById(R.id.buttonDelete);
        editButton = findViewById(R.id.buttonEdit);
        userPicture = findViewById(R.id.userPicture);

        nameEditText.getText().toString();
        phoneNumberEditText.getText();
        emailAddressEditText.getText();
        
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
                contactsManager.removeContact();
            }
        });
    }
}