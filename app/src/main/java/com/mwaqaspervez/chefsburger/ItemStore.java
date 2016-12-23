package com.mwaqaspervez.chefsburger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class ItemStore extends AppCompatActivity implements View.OnClickListener {


    TextView quantity, totalPrice;
    FloatingActionButton fb;
    private int quant = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_store);
        fb = (FloatingActionButton) findViewById(R.id.store_fab);
        fb.setOnClickListener(this);
        if (getSharedPreferences("basket", MODE_PRIVATE).getString("item", null) == null)
            fb.setVisibility(View.GONE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        }
        init();
    }

    private void init() {

        ((TextView) findViewById(R.id.store_name)).setText(getIntent().getStringExtra("name"));

        if (getIntent().getStringExtra("detail").toLowerCase().equals("Undefined".toLowerCase()))
            findViewById(R.id.layout_card).setVisibility(View.GONE);
        else
            ((TextView) findViewById(R.id.store_detail)).setText(getIntent().getStringExtra("detail"));

        ((TextView) findViewById(R.id.store_price)).setText("Rs. " + getIntent().getIntExtra("price", 0) + "");
        quantity = (TextView) findViewById(R.id.store_quantity);
        totalPrice = (TextView) findViewById(R.id.store_total_price);
        totalPrice.setText("Rs. " + getIntent().getIntExtra("price", 0) + "");
        findViewById(R.id.store_add).setOnClickListener(this);
        findViewById(R.id.store_minus).setOnClickListener(this);
        findViewById(R.id.store_add_to_cart).setOnClickListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.store_add:
                quant++;
                quantity.setText(String.format(Locale.getDefault(), "%d", quant));
                totalPrice.setText("Rs. " + getIntent().getIntExtra("price", 0) * quant + "");
                break;

            case R.id.store_minus:

                if (quant != 1)
                    quant--;

                quantity.setText(String.format(Locale.getDefault(), "%d", quant));
                totalPrice.setText("Rs. " + getIntent().getIntExtra("price", 0) * quant + "");
                break;

            case R.id.store_add_to_cart:
                saveTransactionToCart();
                Snackbar.make(view, "Added To Basket", Snackbar.LENGTH_LONG)
                        .show();
                fb.setVisibility(View.VISIBLE);
                break;

            case R.id.store_fab:
                startActivity(new Intent(this, Checkout.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                break;
        }
    }

    private void saveTransactionToCart() {
        try {
            JSONArray jsonArray;

            if (getSharedPreferences("basket", MODE_PRIVATE).getString("item", null) == null)
                jsonArray = new JSONArray();
            else
                jsonArray = new JSONArray(getSharedPreferences("basket", MODE_PRIVATE).getString("item", null));

            jsonArray.put(new JSONObject()
                    .put("name", getIntent().getStringExtra("name"))
                    .put("detail", getIntent().getStringExtra("detail"))
                    .put("price", getIntent().getIntExtra("price", 0))
                    .put("quantity", quant));

            getSharedPreferences("basket", MODE_PRIVATE)
                    .edit()
                    .putString("item", jsonArray.toString())
                    .apply();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    protected void onResume() {

        if (getSharedPreferences("basket", MODE_PRIVATE).getString("item", null) == null) {
            if (fb != null)
                fb.setVisibility(View.GONE);
        }
        super.onResume();
    }
}
