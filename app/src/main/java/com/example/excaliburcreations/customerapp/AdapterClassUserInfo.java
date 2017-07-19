package com.example.excaliburcreations.customerapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by dell pc on 20-Jul-17.
 */

public class AdapterClassUserInfo extends ArrayAdapter<ClassUserInfo> {
    public AdapterClassUserInfo(Context context, int resource, List<ClassUserInfo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.customorder,parent,false);
        }
        ClassUserInfo classUserInfo = getItem(position);

        return convertView;
    }
}
