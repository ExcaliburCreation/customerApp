package com.example.excaliburcreations.customerapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell pc on 28-Dec-17.
 */

public class AdapterItem extends ArrayAdapter<ClassOrder> {
    TextView tDIName;
    TextView tDIQuantity;
    ImageView tOrderTotal;

public AdapterItem(Context context, int resource, List<ClassOrder> objects) {
        super(context, resource, objects);
        }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.custom_item,parent,false);
        }
        tDIName = (TextView) convertView.findViewById(R.id.dIName);
        tDIQuantity = (TextView) convertView.findViewById(R.id.dIQuantity);

        ImageView tOrderTotal = (ImageView) convertView.findViewById(R.id.dIimage);
        ClassOrder classItem = getItem(position);

        tDIName.setText(classItem.getName());
        tDIQuantity.setText(classItem.getQuantity());

        return convertView;
    }
}



