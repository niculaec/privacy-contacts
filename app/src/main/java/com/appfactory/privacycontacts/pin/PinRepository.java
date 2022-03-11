package com.appfactory.privacycontacts.pin;

import java.util.logging.Logger;

public class PinRepository {
    private String pinNumber;

    public void savePinNumber(String pinNumber){
        Logger.getLogger("Pin number was saved in Repository.");
        this.pinNumber = pinNumber;

    }
    public String readPinNumber(){
        Logger.getLogger("Pin number was successfully read from Repository.");
        return pinNumber;
    }
}
