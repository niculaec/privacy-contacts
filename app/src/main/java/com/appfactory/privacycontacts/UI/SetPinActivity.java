package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.pin.PinManager;

public class SetPinActivity extends AppCompatActivity {
    EditText enterPIN, confirmPIN;
    Button savePIN;

    PinManager pinManager = new PinManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pin);
        enterPIN = findViewById(R.id.textEnterPin);
        confirmPIN = findViewById(R.id.textConfirmPin);
        savePIN = findViewById(R.id.buttonSetPinOk);

        savePIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pinNumber = enterPIN.getText().toString();
                if (!(pinNumber.equals(confirmPIN.getText().toString()))){
                    confirmPIN.setError("Pin don't mach");
                    return;
                }
                pinManager.registerPin(pinNumber);
                Intent intent = new Intent(SetPinActivity.this,EnterPinActivity.class);
                startActivity(intent);
            }
        });
    }
}
// This code is good for pinRepository!
//SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putString("PIN", pinNumber);
//                editor.apply();

// try {
//         SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//         String password = preferences.getString("PIN", "");
//         if (!password.isEmpty()) {
//         Toast.makeText(SetPinActivity.this, "Set a PIN number.", Toast.LENGTH_LONG).show();
//         }
//         } catch (Exception e) {
//         e.printStackTrace();
//         }