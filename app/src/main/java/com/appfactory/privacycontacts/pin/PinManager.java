package com.appfactory.privacycontacts.pin;

import com.appfactory.privacycontacts.db.DataEncryption;
import com.appfactory.privacycontacts.utills.Logger;

public class PinManager {

    private final PinRepository pinRepository = new PinRepository();
    private static final PinManager INSTANCE = new PinManager();
    DataEncryption dataEncryption = DataEncryption.getInstance();

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
            String pinHash = dataEncryption.getMD5EncryptedString(pinNumber);
            pinRepository.savePinHash(pinHash);
            dataEncryption.setPassword(pinNumber);
            Logger.log("Pin number " + pinNumber + " is saved.");
            return true;
        }
        Logger.log("Pin number not saved. ");
        return false;
    }

    public boolean isRegistered() {
        return !pinRepository.readPinHash().isEmpty();
    }

    public boolean loginWithPin(String pinNumber) {
        String pinHash = dataEncryption.getMD5EncryptedString(pinNumber);
        if (pinHash != null && pinRepository.readPinHash().equals(pinHash)) {
            dataEncryption.setPassword(pinNumber);
            Logger.log("Successful login.");
            return true;
        }
        Logger.log("Please check Pin number.");
        return false;
    }

}
