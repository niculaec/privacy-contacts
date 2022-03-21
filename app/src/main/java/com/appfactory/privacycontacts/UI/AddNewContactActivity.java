package com.appfactory.privacycontacts.UI;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

public class AddNewContactActivity extends AppCompatActivity {
    ContactsManager contactsManager = new ContactsManager();
    Button saveButton, cancelButton;
    EditText personNameEditText, phoneNumber, emailAddress;
    ImageView userPicture;
    Uri imageUri;

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result != null && result.getResultCode() == RESULT_OK){
                if (result.getData()!=null) {
                    userPicture.setImageURI(result.getData().getData());
                    imageUri = result.getData().getData();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        //This Intent i will be used to change the activity.

        saveButton = findViewById(R.id.buttonSave);
        cancelButton = findViewById(R.id.buttonCancel);
        personNameEditText = findViewById(R.id.editTextPersonName);
        phoneNumber = findViewById(R.id.editTextPhone);
        emailAddress = findViewById(R.id.editTextEmailAddress);
        userPicture = findViewById(R.id.userPicture);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getText has to be pass the values to contactManager in order to call addContact method
                String personName = personNameEditText.getText().toString();
                phoneNumber.getText();
                emailAddress.getText();
                Contact aContact = Contact.Builder.createContact(personName, "124141", "dfasdasd", imageUri.toString());
                if (aContact == null){
                    Toast.makeText(AddNewContactActivity.this, "Contact information invalid.", Toast.LENGTH_LONG).show();
                    Logger.log("Contact information invalid.");
                    return;
                }
                contactsManager.addContact(aContact);

                Toast.makeText(AddNewContactActivity.this, "Contact was saved.", Toast.LENGTH_LONG).show();
                Logger.log("Contact was saved.");
                finish();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        userPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                setResult(RESULT_OK, galleryIntent);
                startForResult.launch(galleryIntent);
                galleryIntent.getData();
            }
        });
    }
}
//TO DO:
// used also for updateContact. new intent (passam id contactului) si folosim .setText