package com.appfactory.privacycontacts.utills;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class PhoneInteractor {
    PermissionsManager permissionsManager;
    Context context;

    public PhoneInteractor(Activity activity) {
        permissionsManager = new PermissionsManager(activity);
        this.context = activity;
    }

    public void makeCall(String phoneNumber) {
        if (!permissionsManager.hasCallPermission()) {
            return;
        }
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        // on below line we are setting data to it.
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        // on below line we are checking if the calling permissions are granted not.
        context.startActivity(callIntent);
        Logger.log("The call is made");
    }

    public void sendMessage(String phoneNumber) {
        // on below line we are passing our contact number.
        if (!permissionsManager.hasMessagePermission()) {
            return;
        }
        Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
        context.startActivity(smsIntent);
        Logger.log("Message has been sent");
    }

    public void sendEmail(String emailAddress) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + emailAddress)); // only email apps should handle this);
        try {
            context.startActivity(emailIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
