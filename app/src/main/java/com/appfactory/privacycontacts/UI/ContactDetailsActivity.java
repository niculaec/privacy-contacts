package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.appfactory.privacycontacts.R;

public class ContactDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        final EditText textName = findViewById(R.id.editTextPersonName);
        final EditText phoneNumber = findViewById(R.id.editTextPhone);
        final EditText emailAddress = findViewById(R.id.editTextEmailAddress);
        textName.getText();
        phoneNumber.getText();
        emailAddress.getText();
    }
}