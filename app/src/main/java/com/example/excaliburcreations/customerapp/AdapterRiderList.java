package com.example.excaliburcreations.customerapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by dell pc on 30-Dec-17.
 */

public class AdapterRiderList extends ArrayAdapter<ClassRider> {
    public AdapterRiderList(Context context, int resource, List<ClassRider> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.custom_rider_select, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.r_name);
        TextView contactno = (TextView) convertView.findViewById(R.id.r_contact);
        TextView state = (TextView) convertView.findViewById(R.id.r_state);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.r_image);

        ClassRider classRider = getItem(position);

        name.setText(classRider.getRiderName());
        contactno.setText(classRider.getRiderContact());
        state.setText(classRider.getRiderStatus());

        Glide.with(imageView.getContext())
                .load(classRider.getRiderImageUrl())
                .into(imageView);

        return convertView;
    }
}
