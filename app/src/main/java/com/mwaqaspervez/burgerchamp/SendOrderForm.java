package com.mwaqaspervez.burgerchamp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


public class SendOrderForm extends AppCompatActivity {


    private CheckBox rememberMe;
    private EditText name, phone, address, city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_order_layout);
        checkIfDataExist();


        rememberMe = (CheckBox) findViewById(R.id.remember_me);
        name = (EditText) findViewById(R.id.ed_name);
        phone = (EditText) findViewById(R.id.ed_phone);
        address = (EditText) findViewById(R.id.ed_address);
        city = (EditText) findViewById(R.id.ed_city);


        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences preferences = getSharedPreferences("rememberMe", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                if (b) {

                    editor.putString("name", name.getText().toString());
                    editor.putString("phone", phone.getText().toString());
                    editor.putString("address", address.getText().toString());
                    editor.putString("city", city.getText().toString());
                } else
                    editor.clear();


                editor.apply();

            }
        });

    }

    private void checkIfDataExist() {
        SharedPreferences preferences = getSharedPreferences("rememberMe", MODE_PRIVATE);
        if (preferences.getString("name", null) != null) {
            name.setText(preferences.getString("name", ""));
            phone.setText(preferences.getString("phone", ""));
            address.setText(preferences.getString("address", ""));
            city.setText(preferences.getString("city", ""));
            rememberMe.setChecked(true);
        }
    }
}
