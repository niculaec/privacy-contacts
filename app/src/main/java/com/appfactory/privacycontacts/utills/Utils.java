package com.appfactory.privacycontacts.utills;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;

import com.appfactory.privacycontacts.UI.AddNewContactActivity;

import java.io.ByteArrayOutputStream;

public class Utils {


    /**
     * Serialize the user picture from an Uri
     *
     * @param uri     user picture uri
     * @param context provided context
     * @return String Base64 representation of user picture.
     */
    public static String getBase64FromUri(Uri uri, Context context) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);// getting the Bitmap from mediaStore
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();//creating an Array of bytes
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, false);
            scaledBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);// compress the data to PNG format and keep the 100% quality
            byte[] byteArray = outputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);// encode the base64 to string
        } catch (Exception e) {
            return "";// return an empty string
        }
    }

    /**
     * Deserialize the user picture from bytes to string.
     *
     * @param pictureBase64 representation of the image
     * @return The decoded the byte array into a bitmap
     */
    public static Bitmap getBitmapFromBase64(String pictureBase64) {
        byte[] decodedString = Base64.decode(pictureBase64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}