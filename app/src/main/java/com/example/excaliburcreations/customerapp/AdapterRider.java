package com.example.excaliburcreations.customerapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell pc on 30-Sep-17.
 */

public class AdapterRider extends ArrayAdapter<ClassRider> {
    public AdapterRider(Context context, int resource, List<ClassRider> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.custom_rider, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.txtRiderName);
        TextView contact = (TextView) convertView.findViewById(R.id.txtRiderContact);
        TextView status = (TextView) convertView.findViewById(R.id.txtRiderStatus);

        ClassRider classRider = getItem(position);
        name.setText(classRider.getRiderName());
        contact.setText(classRider.getRiderContact());
        status.setText(classRider.getRiderStatus());

        return convertView;
    }
}
