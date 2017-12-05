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
 * Created by dell pc on 29-Jul-17.
 */

public class AdapterOrder extends ArrayAdapter<ClassOrder> {
    public AdapterOrder(Context context, int resource, List<ClassOrder> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.customorder,parent,false);
        }
        TextView tOrderArea = (TextView) convertView.findViewById(R.id.tArea);
        TextView tOrderTime = (TextView) convertView.findViewById(R.id.tTime);
        TextView tOrderTotal = (TextView) convertView.findViewById(R.id.tTotal);
        ClassOrder classOrder = getItem(position);

        tOrderArea.setText(classOrder.getName());
        tOrderTime.setText("");
        tOrderTotal.setText(classOrder.getTotal());
        return convertView;
    }
}
