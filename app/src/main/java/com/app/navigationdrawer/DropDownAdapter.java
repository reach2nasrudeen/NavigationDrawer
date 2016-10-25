package com.app.navigationdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kumar on 25/10/16.
 */
public class DropDownAdapter extends BaseAdapter {
    ArrayList<String> dropdonwList;
    Context mContext;

    public DropDownAdapter(Context context, ArrayList<String> dropdonwList) {
        this.mContext = context;
        this.dropdonwList = dropdonwList;
    }

    @Override
    public int getCount() {
        return dropdonwList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String listData = dropdonwList.get(position);
        convertView = LayoutInflater.from(mContext).inflate(R.layout.dropdown_row,null);
        View dropDownRowLayout = convertView.findViewById(R.id.dropDownRowLayout);
        TextView cardTitle = (TextView) convertView.findViewById(R.id.itemTitle);
        cardTitle.setText(listData);
        if(SpinnerActivity.dropDownSelectedPosition == position){
            dropDownRowLayout.setBackgroundResource(R.drawable.round_cornor_pressed);
        }else {
            dropDownRowLayout.setBackgroundResource(R.drawable.round_cornor_normal);
        }
        return convertView;
    }
}
