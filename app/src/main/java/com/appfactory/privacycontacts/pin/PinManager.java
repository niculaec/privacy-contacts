package com.appfactory.privacycontacts.pin;


import com.appfactory.privacycontacts.utills.Logger;

public class PinManager {
    private PinRepository pinRepository = new PinRepository();

    public boolean registerPin(String pinNumber) {
        //TODO check pin contain only numbers
        if (pinNumber != null && pinNumber.length() == 4 ) {
            pinRepository.savePinNumber(pinNumber);
            Logger.log("Pin number " + pinNumber + " is saved.");
            return true;
        }
        Logger.log("Pin number not saved. ");
        return false;
    }

    public boolean loginWithPin(String pinNumber) {
        if (pinNumber.equals(pinRepository.readPinNumber())) {
            Logger.log("Successful login.");
            return true;
        }
        Logger.log("Please check Pin number.");
        return false;
    }
}
