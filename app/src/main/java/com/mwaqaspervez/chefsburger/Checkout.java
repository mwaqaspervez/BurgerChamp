package com.mwaqaspervez.chefsburger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class Checkout extends AppCompatActivity {

    private CheckOutAdapter adapter;
    private TextView total;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_checkout);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        total = (TextView) findViewById(R.id.checkout_total);
        findViewById(R.id.order_now).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Checkout.this, SendOrderForm.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
            }
        });


        adapter = new CheckOutAdapter();
        RecyclerView listView = (RecyclerView) findViewById(R.id.item_listview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        addData();
        listView.setLayoutManager(manager);
        listView.setAdapter(adapter);
        total.setText("Rs." + adapter.getTotal() + "");

    }

    private void addData() {


        try {
            JSONArray array = new JSONArray(getSharedPreferences("basket", MODE_PRIVATE).getString("item", null));

            for (int i = 0; i < array.length(); i++) {
                adapter.add(new SingleCheckOutItem(
                        array.getJSONObject(i).getString("name"),
                        array.getJSONObject(i).getInt("quantity"),
                        array.getJSONObject(i).getInt("price")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.checkout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            this.finish();
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        } else {
            getSharedPreferences("basket", MODE_PRIVATE)
                    .edit()
                    .clear()
                    .apply();
            adapter.clear();
            findViewById(R.id.order_now).setEnabled(false);
            findViewById(R.id.order_now).setBackgroundColor(
                    ContextCompat.getColor(this, R.color.gray));
            total.setText("Rs. 00");

            Snackbar.make(findViewById(R.id.item_listview), "Order Cleared", Snackbar.LENGTH_LONG)
                    .show();

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.ViewHolder> {

        private List<SingleCheckOutItem> list;

        CheckOutAdapter() {
            list = new ArrayList<>();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_checkout_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.txtName.setText(list.get(position).getName() + " (" + list.get(position).getQuantity() + ")");
            holder.tvPrice.setText("Rs." + list.get(position).getPrice() * list.get(position).getQuantity() + "");
            holder.quantity.setText("" + list.get(position).getQuantity() + "");
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void add(SingleCheckOutItem item) {
            list.add(item);
            notifyDataSetChanged();
        }

        void remove(int position) {
            list.remove(position);
            notifyItemRemoved(position);
        }

        SingleCheckOutItem getItem(int position) {
            return list.get(position);
        }

        void add(SingleCheckOutItem item, int position) {
            list.add(position, item);
            notifyItemInserted(position);
        }

        int getTotal() {
            int total = 0;
            for (SingleCheckOutItem item : list)
                total += (item.getPrice() * item.getQuantity());

            return total;
        }

        void clear() {
            list.clear();
            notifyDataSetChanged();
        }


        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView txtName;
            private TextView tvPrice;
            private LinearLayout editLayout;
            private TextView quantity;
            private int quant = 1;
            private ImageView edit;

            ViewHolder(View itemView) {
                super(itemView);
                txtName = (TextView) itemView.findViewById(R.id.checkout_name_quantity);
                tvPrice = (TextView) itemView.findViewById(R.id.checkout_price);
                quantity = (TextView) itemView.findViewById(R.id.checkout_quantity);
                edit = (ImageView) itemView.findViewById(R.id.checkout_edit);
                edit.setOnClickListener(this);
                itemView.findViewById(R.id.checkout_remove).setOnClickListener(this);
                itemView.findViewById(R.id.checkout_minus).setOnClickListener(this);
                itemView.findViewById(R.id.checkout_add).setOnClickListener(this);
                editLayout = (LinearLayout) itemView.findViewById(R.id.edit_layout);

            }

            @Override
            public void onClick(View view) {
                switch (view.getId()) {

                    case R.id.checkout_edit:
                        if (editLayout.getVisibility() == View.GONE) {
                            editLayout.setVisibility(View.VISIBLE);
                            quant = list.get(getAdapterPosition()).getQuantity();
                            edit.setImageResource(R.drawable.ic_top_arrow);
                        } else {
                            editLayout.setVisibility(View.GONE);
                            edit.setImageResource(R.drawable.ic_down_arrow);
                        }
                        break;

                    case R.id.checkout_remove:

                        final SingleCheckOutItem item = adapter.getItem(getAdapterPosition());
                        final int position = getAdapterPosition();
                        Snackbar.make(view, "Item Removed", Snackbar.LENGTH_LONG)
                                .setActionTextColor(Color.GREEN)
                                .setAction("UNDO", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        adapter.add(item, position);
                                        tvPrice.setText("Rs. " + list.get(position).getPrice() * quant + "");
                                        total.setText("Rs. " + adapter.getTotal() + "");
                                    }
                                }).show();
                        adapter.remove(getAdapterPosition());
                        total.setText("Rs. " + adapter.getTotal() + "");
                        break;

                    case R.id.checkout_minus:
                        if (quant == 1)
                            quant = 1;
                        else
                            quant--;

                        tvPrice.setText("Rs. " + list.get(getAdapterPosition()).getPrice() * quant + "");
                        quantity.setText("" + quant + "");

                        list.get(getAdapterPosition()).setQuantity(quant);
                        txtName.setText(list.get(getAdapterPosition()).getName() + " (" + list.get(getAdapterPosition()).getQuantity() + ")");
                        total.setText("Rs. " + adapter.getTotal() + "");
                        break;
                    case R.id.checkout_add:
                        quant++;
                        tvPrice.setText("Rs. " + list.get(getAdapterPosition()).getPrice() * quant + "");
                        quantity.setText("" + quant + "");

                        list.get(getAdapterPosition()).setQuantity(quant);
                        txtName.setText(list.get(getAdapterPosition()).getName() + " (" + list.get(getAdapterPosition()).getQuantity() + ")");
                        total.setText("Rs. " + adapter.getTotal() + "");
                        break;
                }
            }
        }
    }


}

