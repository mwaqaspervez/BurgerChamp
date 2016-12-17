package com.mwaqaspervez.burgerchamp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ItemsDetails extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<Item> items;
    private ItemsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        adapter = new ItemsAdapter();
        addData();

        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return super.onOptionsItemSelected(item);
    }

    public void addData() {

        String[] items = getResources().getStringArray(R.array.starters);
        List<Integer> price = new ArrayList<>();
        List<String> name = new ArrayList<>();
        List<Boolean> isSpecial = new ArrayList<>();

        for (String item : items) {
            String[] x = item.split("-");
            price.add(Integer.valueOf(x[0]));
            name.add(x[1]);
            isSpecial.add(!x[2].equals("0"));
        }

        for (int j = 0; j < price.size(); j++)
            adapter.add(new Item(price.get(j), name.get(j), isSpecial.get(j)));

    }
}
