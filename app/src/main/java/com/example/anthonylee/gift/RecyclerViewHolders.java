package com.example.anthonylee.gift;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView giver_name;
    public ImageView gift_view;
    private RecyclerViewAdapter.ItemClickCallback itemClickCallback;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        giver_name = (TextView)itemView.findViewById(R.id.giver_name);
        gift_view = (ImageView)itemView.findViewById(R.id.gift_view);
    }

    @Override
    public void onClick(View view) {
        itemClickCallback.onItemClick(getAdapterPosition());
    }
}