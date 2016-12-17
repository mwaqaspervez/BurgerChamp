package com.mwaqaspervez.burgerchamp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

        LinearLayout layout = (LinearLayout) v.findViewById(R.id.item_starters);

        layout.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {


        startActivity(new Intent(view.getContext(), ItemsDetails.class));
    }
}
