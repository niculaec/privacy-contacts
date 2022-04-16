package com.appfactory.privacycontacts.pin;

import com.appfactory.privacycontacts.utills.Logger;

public class PinManager {

    private final PinRepository pinRepository = new PinRepository();
    private static final PinManager INSTANCE = new PinManager();

    private PinManager() {
    }

    public static PinManager getInstance() {
        return INSTANCE;
    }

    public boolean registerPin(String pinNumber) {

        boolean pinIsNumber;
        try {
            Integer.parseInt(pinNumber);
            pinIsNumber = true;
        } catch (Exception e) {
            pinIsNumber = false;
        }

        if (pinNumber != null && pinNumber.length() == 4 && pinIsNumber) {
            pinRepository.savePinNumber(pinNumber);
            Logger.log("Pin number " + pinNumber + " is saved.");
            return true;
        }
        Logger.log("Pin number not saved. ");
        return false;
    }

    public boolean isRegistered() {
        return !pinRepository.readPinNumber().isEmpty();
    }

    public boolean loginWithPin(String pinNumber) {
        if (pinNumber != null && pinRepository.readPinNumber().equals(pinNumber)) {
            Logger.log("Successful login.");
            return true;
        }
        Logger.log("Please check Pin number.");
        return false;
    }
}
