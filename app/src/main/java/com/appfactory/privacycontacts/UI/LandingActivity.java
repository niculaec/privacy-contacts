package com.appfactory.privacycontacts.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.pin.PinManager;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        if(PinManager.getInstance().isRegistered()){
            Intent intent = new Intent(LandingActivity.this, EnterPinActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Intent intentSetPin = new Intent(LandingActivity.this, SetPinActivity.class);
        startActivity(intentSetPin);
        finish();
    }
}