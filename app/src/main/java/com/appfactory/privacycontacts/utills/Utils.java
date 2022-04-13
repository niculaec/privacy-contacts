package com.appfactory.privacycontacts.utills;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;

import com.appfactory.privacycontacts.UI.AddNewContactActivity;

import java.io.ByteArrayOutputStream;

public class Utils {

    public static String getBase64FromUri(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(AddNewContactActivity.this.getContentResolver(), uri);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            byte[] byteArray = outputStream.toByteArray();

            //Use your Base64 String as you wish
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }catch (Exception e){
            return "";
        }
    }

    public static Bitmap getBitmapFromBase64(String base64){
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
