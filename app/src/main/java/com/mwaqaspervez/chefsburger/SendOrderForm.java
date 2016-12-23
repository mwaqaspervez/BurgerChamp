package com.mwaqaspervez.chefsburger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


public class SendOrderForm extends AppCompatActivity {


    private CheckBox rememberMe;
    private EditText name, phone, address;

    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return wifi.isConnected() || mobile.isConnected();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_order_layout);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        rememberMe = (CheckBox) findViewById(R.id.remember_me);
        name = (EditText) findViewById(R.id.ed_name);
        phone = (EditText) findViewById(R.id.ed_phone);
        address = (EditText) findViewById(R.id.ed_address);
        findViewById(R.id.bt_send_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog dialog = ProgressDialog.show(SendOrderForm.this, "", "Please Wait...",
                        true);
                dialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        if (isNetworkAvailable(SendOrderForm.this)) {
                            new AlertDialog.Builder(SendOrderForm.this)
                                    .setMessage("Thank you for using Chef's Burger." +
                                            " Your order will be delivered in about 30 minutes.")
                                    .setTitle("Order Submitted.")
                                    .setCancelable(true)
                                    .create()
                                    .show();

                        } else {
                            new AlertDialog.Builder(SendOrderForm.this)
                                    .setMessage("Could Not Connect To Network.")
                                    .setTitle("Error Submitting.")
                                    .setCancelable(true)
                                    .create()
                                    .show();
                        }
                    }
                }, 5000);

            }
        });


        checkIfDataExist();
        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences preferences = getSharedPreferences("rememberMe", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                if (b) {
                    editor.putString("name", name.getText().toString());
                    editor.putString("phone", phone.getText().toString());
                    editor.putString("address", address.getText().toString());
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
            rememberMe.setChecked(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}
