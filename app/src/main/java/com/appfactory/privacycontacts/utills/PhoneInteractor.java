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
        try {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});// we create an String Array and initialise in line with email address
            context.startActivity(emailIntent);
            Logger.log("Email intent has been sent");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
