package com.appfactory.privacycontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.appfactory.privacycontacts.contact.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    ArrayList<Contact> mContacts;
    LayoutInflater mInflater;

    public ContactAdapter(@NonNull Context context, @NonNull List<Contact> objects) {
        super(context, R.layout.contacts_lw_item, objects);

        mContacts= (ArrayList<Contact>) objects;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.contacts_lw_item, parent,false);
        }
        TextView textViewName = convertView.findViewById(R.id.personNameTextView);
        ImageView imageViewUserPicture = convertView.findViewById(R.id.userPicture);

        Contact contact = mContacts.get(position);
        textViewName.setText(contact.getName());
        imageViewUserPicture.setImageResource(R.drawable.logo);

        return convertView;
    }
}
