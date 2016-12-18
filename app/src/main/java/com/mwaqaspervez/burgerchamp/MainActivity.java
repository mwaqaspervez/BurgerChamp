package com.mwaqaspervez.burgerchamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_starters).setOnClickListener(this);
        findViewById(R.id.tv_chicken).setOnClickListener(this);
        findViewById(R.id.tv_beef).setOnClickListener(this);
        findViewById(R.id.tv_beverage).setOnClickListener(this);
        findViewById(R.id.tv_dessert).setOnClickListener(this);
        findViewById(R.id.tv_icecream).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(view.getContext(), ItemsDetails.class);
        switch (view.getId()) {

            case R.id.tv_starters:
                intent.putExtra("selected", "starters");
                break;
            case R.id.tv_chicken:
                intent.putExtra("selected", "chicken");
                break;
            case R.id.tv_beef:
                intent.putExtra("selected", "beef");
                break;
            case R.id.tv_beverage:
                intent.putExtra("selected", "beverage");
                break;
            case R.id.tv_icecream:
                intent.putExtra("selected", "icecream");
                break;
            case R.id.tv_dessert:
                intent.putExtra("selected", "dessert");
                break;
        }
        startActivity(intent);
    }
}
