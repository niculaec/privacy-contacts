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
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(AddNewContactActivity.this.getContentResolver(), uri);// getting the Bitmap from mediaStore
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();//creating an Array of bytes
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);// compress the data to PNG format and keep the 100% quality
            byte[] byteArray = outputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);// encode the base64 to string
        }catch (Exception e){
            return "";// return an empty string
        }
    }

    public static Bitmap getBitmapFromBase64(String base64){// deserializer the user picture from bytes to string
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);// decode the byte array to string
    }
}
