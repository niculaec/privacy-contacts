package com.appfactory.privacycontacts.pin;


import com.appfactory.privacycontacts.utills.Logger;

public class PinManager {
    private PinRepository pinRepository = new PinRepository();

    boolean registerPin(String pinNumber) {
        if (pinNumber != null) {
            pinRepository.savePinNumber(pinNumber);
            Logger.log("Pin number " + pinNumber + " is saved.");
            return true;
        }
        Logger.log("Pin number not saved. ");
        return false;
    }

    boolean loginWithPin(String pinNumber) {
        if (pinNumber.equals(pinRepository.readPinNumber())) {
            Logger.log("Successful login.");
            return true;
        }
        Logger.log("Please check Pin number.");
        return false;
    }
}
