package com.appfactory.privacycontacts.pin;

import com.appfactory.privacycontacts.utills.Logger;

public class PinRepository {
    private String pinNumber;

    public void savePinNumber(String pinNumber){
        Logger.log("Pin number was saved in Repository.");
        this.pinNumber = pinNumber;
    }

    public String readPinNumber(){
        Logger.log("Pin number was successfully read from Repository.");
        return pinNumber;
    }
}
