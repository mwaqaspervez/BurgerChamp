package com.mwaqaspervez.burgerchamp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private List<Item> items;

    public ItemsAdapter() {
        this.items = new ArrayList<>();
    }


    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_single_detail_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ViewHolder holder, int position) {

        if (items.get(position).isSpecial())
            holder.isSpecial.setVisibility(View.VISIBLE);
        else
            holder.isSpecial.setVisibility(View.GONE);

        holder.price.setText("Price - " + items.get(position).getPrice() + "rs");
        holder.name.setText(items.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

     void add(Item item) {
        items.add(item);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, price, isSpecial;

        ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.item_name);
            price = (TextView) itemView.findViewById(R.id.item_price);
            isSpecial = (TextView) itemView.findViewById(R.id.item_special);

        }
    }


}
