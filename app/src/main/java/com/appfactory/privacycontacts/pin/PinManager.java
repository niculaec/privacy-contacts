package com.appfactory.privacycontacts.pin;

import java.util.logging.Logger;

public class PinManager {
    private PinRepository pinRepository = new PinRepository();

    boolean registerPin(String pinNumber) {
        if (pinNumber != null) {
            pinRepository.savePinNumber(pinNumber);
            Logger.getLogger("Pin number " + pinNumber + " is saved.");
            return true;
        }
        Logger.log("Pin number not saved. ");
        return false;
    }

    boolean loginWithPin(String pinNumber) {
        if (pinNumber.equals(pinRepository.readPinNumber())) {
            Logger.getLogger("Successful login.");
            return true;
        }
        Logger.getLogger("Please check Pin number.");
        return false;
    }
}
