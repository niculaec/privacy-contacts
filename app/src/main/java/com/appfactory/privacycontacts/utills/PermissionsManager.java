package com.appfactory.privacycontacts.utills;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionsManager {
    Context context;

    public boolean makeCallPermission() {
        //request permission.
        return ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean sendMessagePermission() {
        //request permission.
        return ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean sendEmailPermission() {
        //request permission.
        return ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
    }



//    public void textPermission(){
//        if (ContextCompat.checkSelfPermission(
//                CONTEXT, Manifest.permission.REQUESTED_PERMISSION) ==
//                PackageManager.PERMISSION_GRANTED) {
//            // You can use the API that requires the permission.
//            performAction(...);
//        } else if (shouldShowRequestPermissionRationale(...)) {
//            // In an educational UI, explain to the user why your app requires this
//            // permission for a specific feature to behave as expected. In this UI,
//            // include a "cancel" or "no thanks" button that allows the user to
//            // continue using your app without granting the permission.
//            showInContextUI(...);
//        } else {
//            // You can directly ask for the permission.
//            // The registered ActivityResultCallback gets the result of this request.
//            requestPermissionLauncher.launch(
//                    Manifest.permission.REQUESTED_PERMISSION);
//        }
//    }
}
