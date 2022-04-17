package com.appfactory.privacycontacts.contact;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.UUID;

@Entity
public class Contact  {

    @ColumnInfo
    private static final String savedId = UUID.randomUUID().toString();
    @ColumnInfo
    private final String name;

    @ColumnInfo
    private final String phoneNumber;

    @ColumnInfo
    private final String emailAddress;

    @ColumnInfo
    private final String userPicture;

    @PrimaryKey
    @NonNull
    private final String id;

    @Ignore
    private Contact(String name, String phoneNumber, String emailAddress, String userPicture, @NonNull String id) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.userPicture = userPicture;
        this.id = id;
    }


    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", userPicture='" + userPicture + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public String getId() {
        return id;
    }


    public static class Builder {

        public static Contact createContact(String id, String name, String phoneNumber, String emailAddress, String userPicture) {
            if (!checkValidParams(name, phoneNumber, emailAddress, userPicture)) {
                return null;
            }
            return new Contact(name, phoneNumber, emailAddress, userPicture, id);
        }

        public static Contact createContact(String name, String phoneNumber, String emailAddress, String userPicture) {
            return createContact(UUID.randomUUID().toString(),name,phoneNumber,emailAddress,userPicture);

        }

        private static boolean checkValidParams(String name, String phoneNumber, String emailAddress, String userPicture) {
            if (name == null || name.isEmpty() ||
                    phoneNumber == null ||
                    emailAddress == null ||
                    userPicture == null) {
                return false;
            }
            return true;
        }

        public static Contact createUpdatedContact(Contact oldContact, String name, String phoneNumber, String emailAddress, String userPicture) {
            if (!checkValidParams(name, phoneNumber, emailAddress, userPicture)) {
                return null;
            }
            return new Contact(name, phoneNumber, emailAddress, userPicture, oldContact.id);
        }
    }
}
