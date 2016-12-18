package com.mwaqaspervez.burgerchamp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private List<Item> items;
    private String title;

    ItemsAdapter() {
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

    public void setTitle(String title) {
        this.title = title;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name, price, isSpecial;

        ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.item_name);
            price = (TextView) itemView.findViewById(R.id.item_price);
            isSpecial = (TextView) itemView.findViewById(R.id.item_special);
            name.setOnClickListener(this);
            price.setOnClickListener(this);
            isSpecial.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ItemStore.class);
            intent.putExtra("name", items.get(getAdapterPosition()).getName());
            intent.putExtra("price", items.get(getAdapterPosition()).getPrice());
            intent.putExtra("isSpecial", items.get(getAdapterPosition()).isSpecial());
            intent.putExtra("detail", items.get(getAdapterPosition()).getDetail());
            intent.putExtra("title", title);
            view.getContext().startActivity(intent);
        }
    }


}
