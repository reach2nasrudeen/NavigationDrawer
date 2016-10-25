package com.app.navigationdrawer;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Spinner mSpinner;
    MaterialBetterSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initNavigationDrawer();
        String[] itemCountry = {
                "India",
                "UK",
                "US"};
        spinner =   (MaterialBetterSpinner)findViewById(R.id.spinner);
        ArrayAdapter<String> itemMenuAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_dropdown_item_1line, itemCountry);;
//        countrySpinner.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/Amaranth-Regular.ttf"));
        spinner.setAdapter(itemMenuAdapter);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Clicked "+i,Toast.LENGTH_SHORT).show();
            }
        });
//        // Spinner element
//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//
//        // Spinner Drop down elements
//        List<String> languages = new ArrayList<String>();
//        languages.add("Andorid Andorid Andorid Andorid Andorid Andorid Andorid Andorid");
//        languages.add("IOS");
//        languages.add("PHP");
//        languages.add("Java");
//        languages.add(".Net");
//
//
//        // Creating adapter for spinner
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_row, languages);
//
//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        spinner.setAdapter(dataAdapter);
//
//        // Spinner click listener
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        final MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
//        spinner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                spinner.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),android.R.color.white));
//            }
//        });
//        spinner.setItems("Ice Cream Sandwich");
//        spinner.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {
//            @Override
//            public void onNothingSelected(MaterialSpinner spinner) {
//                spinner.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),android.R.color.transparent));
//            }
//        });
//        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
//                spinner.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),android.R.color.transparent));
//            }
//        });
////        mSpinner = (Spinner) findViewById(R.id.spinner_rss);
//
//        String[] items = new String[]{
//          "testtesttesttesttesttestesttesttestttesttest","testesttesttesttest1","tetesttesttestst3"
//        };
//        List<String> spinnerItems = new ArrayList<String>();
//
//        for(int i = 0; i < items.length; i++)
//        {
//            spinnerItems.add(items[i]);
//        }
//
//        SpinnerAdapter adapter = new SpinnerAdapter(getApplicationContext(), spinnerItems);
//        mSpinner.setAdapter(adapter);
//
//        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
//        {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                mSpinner.setDropDownVerticalOffset(-116);
//            }
//        }
        displayView(0);
    }
    public void initNavigationDrawer() {

        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){
                    case R.id.logout:
                        Toast.makeText(MainActivity.this, "Logout Clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();

                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        TextView tv_email = (TextView)header.findViewById(R.id.tv_email);
        tv_email.setText("raj.amalw@app.com");
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Dashboard_Fragment();
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
        }
    }
}
