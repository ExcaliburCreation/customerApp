package com.example.excaliburcreations.customerapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by dell pc on 01-Aug-17.
 */

public class AdapterOrderDialog extends ArrayAdapter<ClassOrder> {
    public AdapterOrderDialog(Context context, int resource, List<ClassOrder> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.dialog_layout,parent,false);
        }
//        TextView tName = (TextView) convertView.findViewById(R.id.tdhName);
//        TextView tAddress = (TextView) convertView.findViewById(R.id.tdhAddress);
//        TextView tContact = (TextView) convertView.findViewById(R.id.tdhContactno);
//        TextView tAmount = (TextView) convertView.findViewById(R.id.tdhAmount);
//        TextView tPaymethod = (TextView) convertView.findViewById(R.id.tdhPaymethod);
//        ClassOrder classOrder = getItem(position);
//
//        tName.setText(classOrder.getConsigneeName());
//        tAddress.setText(classOrder.getAddress());
//        tContact.setText(classOrder.getContactno());
//        tAmount.setText(classOrder.getAmount());
//        tPaymethod.setText(classOrder.getPayMethod());
        return convertView;
    }
}