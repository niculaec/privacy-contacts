package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.contact.ContactsManager;
import com.appfactory.privacycontacts.utills.Logger;

public class AddNewContactActivity extends AppCompatActivity {
    ContactsManager contactsManager = new ContactsManager();
    Button save, cancel;
    EditText personName, phoneNumber, emailAddress;
    ImageView userPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        Intent i = new Intent();
        
        save = findViewById(R.id.buttonSave);
        cancel = findViewById(R.id.buttonCancel);
        personName = findViewById(R.id.editTextPersonName);
        phoneNumber = findViewById(R.id.editTextPhone);
        emailAddress = findViewById(R.id.editTextEmailAddress);
        userPicture = findViewById(R.id.userPicture);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personName.getText();
                phoneNumber.getText();
                emailAddress.getText();
                Toast.makeText(AddNewContactActivity.this, "Contact was saved.", Toast.LENGTH_LONG).show();
                Logger.log("Contact was saved.");
            }
        });
    }



}