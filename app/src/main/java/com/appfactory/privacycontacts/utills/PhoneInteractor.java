package com.appfactory.privacycontacts.utills;

import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.view.View;

import com.appfactory.privacycontacts.UI.ContactDetailsActivity;

public class PhoneInteractor {
    ContactDetailsActivity contactDetailsActivity = new ContactDetailsActivity();

    void makeCall(String phoneNumber) {

        iconCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(phoneNumber));
                contactDetailsActivity.startActivity(callIntent);
            }
        });
        Logger.log("The call is made");
    }

    void sendMessage(String phoneNumber) {
        Logger.log("Message has been sent");
    }

    void SendEmail(String emailAddress) {
        Logger.log("Email has been sent");

    }
}
