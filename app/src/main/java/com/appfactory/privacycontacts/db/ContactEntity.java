package com.appfactory.privacycontacts.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ContactEntity {
    @NonNull
    @PrimaryKey
    public String id;

    public String name;

    public String phoneNumber;

    public String emailAddress;

    public String userPicture;

}
