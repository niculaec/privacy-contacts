package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.appfactory.privacycontacts.R;

public class ContactDetailsActivity extends AppCompatActivity {

    ImageView iconCall , iconMessage, iconEmail, userPicture;
    Button deleteButton, editButton;
    EditText textNameEditText, phoneNumberEditText, emailAddressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        textNameEditText = findViewById(R.id.editTextPersonName);
        phoneNumberEditText = findViewById(R.id.editTextPhone);
        emailAddressEditText = findViewById(R.id.editTextEmailAddress);
        iconCall = findViewById(R.id.iconCall);
        iconMessage = findViewById(R.id.iconMessage);
        iconEmail = findViewById(R.id.iconEmail);
        deleteButton = findViewById(R.id.buttonDelete);
        editButton = findViewById(R.id.buttonEdit);
        userPicture = findViewById(R.id.userPicture);

        textNameEditText.getText();
        phoneNumberEditText.getText();
        emailAddressEditText.getText();
    }
}