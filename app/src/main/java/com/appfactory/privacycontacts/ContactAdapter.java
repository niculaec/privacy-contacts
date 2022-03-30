package com.appfactory.privacycontacts;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.appfactory.privacycontacts.contact.Contact;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {


    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> contactList) {
        super(context, resource, contactList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_single_item, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.personNameTextView);
        ImageView imageViewUserPicture = convertView.findViewById(R.id.userPicture);
        textViewName.setText(contact.getName());
        //imageViewUserPicture.setImageResource(Integer.parseInt(contact.getUserPicture()));

        return convertView;
    }
}
