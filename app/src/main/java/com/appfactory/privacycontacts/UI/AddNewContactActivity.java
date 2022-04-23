package com.appfactory.privacycontacts.UI;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.contact.Contact;
import com.appfactory.privacycontacts.contact.ContactsManager;
import com.appfactory.privacycontacts.utills.Logger;
import com.appfactory.privacycontacts.utills.Utils;

public class AddNewContactActivity extends AppCompatActivity {
    ContactsManager contactsManager = ContactsManager.getInstance();
    Button saveButton, cancelButton;
    EditText personNameEditText, phoneNumberEditText, emailAddressEditText;
    ImageView userPictureImageView;
    String userPictureBase64;
    Contact contact;

    // The new way from android x above to write onActivityResult.
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null && result.getResultCode() == RESULT_OK) {
                if (result.getData() != null) {
                    userPictureBase64 = Utils.getBase64FromUri(result.getData().getData(), AddNewContactActivity.this);
                    userPictureImageView.setImageBitmap(Utils.getBitmapFromBase64(userPictureBase64));
                }
            }
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        saveButton = findViewById(R.id.buttonSave);
        cancelButton = findViewById(R.id.buttonCancel);
        personNameEditText = findViewById(R.id.editTextPersonName);
        phoneNumberEditText = findViewById(R.id.editTextPhone);
        emailAddressEditText = findViewById(R.id.editTextEmailAddress);
        userPictureImageView = findViewById(R.id.userPicture);

        Intent currentIntent = getIntent();
        String parsedID = currentIntent.getStringExtra(ContactsManager.ID_KEY);
        contact = contactsManager.getContact(parsedID);

        if (contact != null) {
            personNameEditText.setText(contact.getName());
            phoneNumberEditText.setText(contact.getPhoneNumber());
            emailAddressEditText.setText(contact.getEmailAddress());

            if (!contact.getUserPicture().isEmpty()) {
                userPictureImageView.setImageBitmap(Utils.getBitmapFromBase64(contact.getUserPicture()));
            }
            saveButton.setText("Update");
        }


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getText has to be pass the values to contactManager in order to call addContact method
                String personName = personNameEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();
                String emailAddress = emailAddressEditText.getText().toString();// get the value and convert to String.
                if(userPictureBase64 == null){
                    userPictureBase64 = (contact != null) ? contact.getUserPicture() : ""; // if in line (ternary operator).
                }
                if (contact != null) {
                    contactsManager.updateContact(contact, personName, phoneNumber, emailAddress, userPictureBase64);
                } else {
                    Contact aContact = Contact.Builder.createContact(personName, phoneNumber, emailAddress, userPictureBase64);
                    if (aContact == null) {
                        Toast.makeText(AddNewContactActivity.this, "Contact information invalid.", Toast.LENGTH_LONG).show();
                        Logger.log("Contact information invalid.");
                        return;
                    }
                    contactsManager.addContact(aContact);
                }
                Toast.makeText(AddNewContactActivity.this, "Contact was saved.", Toast.LENGTH_LONG).show();
                Logger.log("Contact was saved.");
                Intent contactsListIntent = new Intent(AddNewContactActivity.this, ContactsListActivity.class);
                startActivity(contactsListIntent);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // This activity is finished once the user press cancelButton,
                // the android OS are taking the previous activity (ContactsList) from DDR memory and displayed on the screen.(Activity life cycle).
            }
        });

        userPictureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                setResult(RESULT_OK, galleryIntent);
                startForResult.launch(galleryIntent);
            }
        });
    }
}