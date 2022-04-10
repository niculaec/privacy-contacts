package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.pin.PinManager;
import com.appfactory.privacycontacts.pin.PinRepository;

public class EnterPinActivity extends AppCompatActivity {
    PinManager pinManager = new PinManager();
    EditText textEnterPin;
    Button buttonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);
        textEnterPin = findViewById(R.id.textEnterPin);
        buttonOk = findViewById(R.id.buttonLoginOk);


        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textEnterPin.getText() == null){
                    Toast.makeText(EnterPinActivity.this, "Please enter a Pin.", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(EnterPinActivity.this,ContactsListActivity.class);
                startActivity(intent);
            }
        });
    }
}