package com.mwaqaspervez.burgerchamp;

import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemsActivity extends Fragment {

    public static ItemsActivity instance = null;

    public ItemsActivity newInstance() {

        if (instance == null)
            return (instance = new ItemsActivity());

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_items, container,false);

        return v;
    }
}
