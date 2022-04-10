package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.pin.PinManager;

public class SetPinActivity extends AppCompatActivity {
    EditText enterPinEditText, confirmPinEditText;
    Button savePinButton;
    PinManager pinManager = PinManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pin);
        enterPinEditText = findViewById(R.id.textEnterPin);
        confirmPinEditText = findViewById(R.id.textConfirmPin);
        savePinButton = findViewById(R.id.buttonSetPinOk);


        savePinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pinNumber = enterPinEditText.getText().toString();
                if (!(pinNumber.equals(confirmPinEditText.getText().toString()))){
                    confirmPinEditText.setError("Pin don't mach");
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