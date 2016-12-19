package com.mwaqaspervez.burgerchamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class ItemStore extends AppCompatActivity implements View.OnClickListener {


    TextView quantity, totalPrice;
    private int quant = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_store);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        }
        init();
    }

    private void init() {

        ((TextView) findViewById(R.id.store_name)).setText(getIntent().getStringExtra("name"));
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

                startActivity(new Intent(this, Checkout.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                //Snackbar.make(view, "Added To Cart", Snackbar.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }
}
