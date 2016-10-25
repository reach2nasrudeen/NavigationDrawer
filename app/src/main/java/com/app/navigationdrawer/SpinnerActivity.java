package com.app.navigationdrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerActivity extends AppCompatActivity {
    private View spinnerLayout;
    private TextView dropDownTitle;
    private ArrayList<String> dropdownList;
    public static int dropDownSelectedPosition=0;
    Point p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        initSpinner();
    }
    private void initSpinner(){
        spinnerLayout = findViewById(R.id.spinnerLayout);
        dropDownTitle = (TextView) findViewById(R.id.dropDownTitle);
        spinnerLayout.setOnClickListener(onClickListener);
        dropDownTitle.setOnClickListener(onClickListener);
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (p != null)
                spinnerLayout.setBackgroundResource(R.drawable.round_cornor_pressed);
            showPopup(SpinnerActivity.this, p);
        }
    };
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        int[] location = new int[2];
        View spinnerLayout = findViewById(R.id.spinnerLayout);

        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        spinnerLayout.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions
        p = new Point();
        p.x = location[0];
        p.y = location[1];
    }
    // The method that displays the popup.
    private void showPopup(final Activity context, Point p) {
        int popupWidth = WindowManager.LayoutParams.MATCH_PARENT;
        int popupHeight = WindowManager.LayoutParams.WRAP_CONTENT;

        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.dropdownLayout);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.dropdown_layout, viewGroup);
        layout.setAnimation(AnimationUtils.loadAnimation(this, R.anim.popupanim));
        final PopupWindow popup = new PopupWindow(context);
        dropdownList = prepareDropDownData();
        DropDownAdapter adapter = new DropDownAdapter(getApplicationContext(),dropdownList);
        ListView listViewSort = (ListView) layout.findViewById(R.id.dropDownList);
        listViewSort.setAdapter(adapter);
        // set on item selected
        listViewSort.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dropDownSelectedPosition = i;
                popup.dismiss();
                spinnerLayout.setBackgroundResource(R.drawable.round_cornor_normal);
                dropDownTitle.setText(dropdownList.get(i));
                Log.i("Text -->",dropdownList.get(i));
            }
        });
        // Creating the PopupWindow
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 30;
        int OFFSET_Y = 60;

        // Clear the default translucent background
//        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x , p.y + OFFSET_Y);
    }
    private ArrayList prepareDropDownData() {
        ArrayList<String> arrayList= new ArrayList<String>();
        arrayList.add("test");
        arrayList.add("test1");
        arrayList.add("test2");
        arrayList.add("test3");
        arrayList.add("test4");
        return arrayList;
    }
}
