package com.mwaqaspervez.burgerchamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemsActivity extends Fragment implements View.OnClickListener {

    private ItemsActivity instance = null;

    public ItemsActivity newInstance() {

        if (instance == null)
            return (instance = new ItemsActivity());

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_items, container, false);

        v.findViewById(R.id.tv_starters).setOnClickListener(this);
        v.findViewById(R.id.tv_chicken).setOnClickListener(this);
        v.findViewById(R.id.tv_beef).setOnClickListener(this);
        v.findViewById(R.id.tv_beverage).setOnClickListener(this);
        v.findViewById(R.id.tv_dessert).setOnClickListener(this);
        v.findViewById(R.id.tv_icecream).setOnClickListener(this);

        return v;
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
