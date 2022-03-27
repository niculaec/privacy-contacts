package com.appfactory.privacycontacts.utills;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class PhoneInteractor {
    PermissionsManager permissionsManager = new PermissionsManager();
    Context context;

    public PhoneInteractor(Context context) {
        this.context = context;
    }

    public void makeCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        // on below line we are setting data to it.
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        // on below line we are checking if the calling permissions are granted not.
        if (permissionsManager.makeCallPermission()) {//request permission.
            return;
        }
        context.startActivity(callIntent);
        Logger.log("The call is made");
    }

    public void sendMessage(String phoneNumber) {
        // on below line we are passing our contact number.
        Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
        smsIntent.putExtra("sms_body", "Enter your message");
        context.startActivity(smsIntent);
        Logger.log("Message has been sent");
    }

    public void sendEmail(String emailAddress) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:" + emailAddress));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
        if (permissionsManager.sendEmailPermission()) {
            context.startActivity(emailIntent);
        }
        Logger.log("Email has been sent");

    }
}
