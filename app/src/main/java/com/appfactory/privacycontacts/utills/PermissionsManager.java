package com.appfactory.privacycontacts.utills;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PermissionsManager {
    Activity activity;

    public PermissionsManager(Activity activity) {
        this.activity = activity;
    }

    public boolean hasPermission(String permissionName) {
        if(ActivityCompat.checkSelfPermission(activity,
                permissionName) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        ActivityCompat.requestPermissions(activity,new String[]{permissionName},0);
        return false;
    }

    public boolean hasCallPermission() {
        return hasPermission(Manifest.permission.CALL_PHONE);
        //Request permission to make a call
    }

    public boolean hasMessagePermission() {
        return  hasPermission(Manifest.permission.SEND_SMS);
        //Request permission to send SMS.
    }
}
