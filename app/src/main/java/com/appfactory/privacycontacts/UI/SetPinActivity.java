package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.pin.PinManager;
import com.appfactory.privacycontacts.pin.PinRepository;

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
                if (!(pinNumber.equals(confirmPinEditText.getText().toString()))) {
                    confirmPinEditText.setError("Pin don't mach");
                    return;
                }
                if (pinManager.registerPin(pinNumber)) {
                    Intent intent = new Intent(SetPinActivity.this, ContactsListActivity.class);
                    startActivity(intent);
                } else {
                    confirmPinEditText.setError("Pin not valid, use only 4 digits number");
                }
            }
        });
    }
}
