package com.example.anthonylee.gift;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView giver_name;
    public ImageView gift_view;
    public RecyclerViewAdapter.ItemClickCallback itemClickCallback;

    public RecyclerViewHolders(final View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        giver_name = (TextView)itemView.findViewById(R.id.giver_name);
        gift_view = (ImageView)itemView.findViewById(R.id.gift_view);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Gift_Sending.rowListItem.get(getAdapterPosition()).isSent()){
                    Gift_Sending.rowListItem.get(getAdapterPosition()).setToSend(false);
                    itemView.setBackgroundResource(0);
                }else {
                    Gift_Sending.rowListItem.get(getAdapterPosition()).setToSend(true);
                    itemView.setBackgroundResource(R.drawable.chose_style);
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        Log.e("Position", "" + getAdapterPosition());
        itemClickCallback.onItemClick(getAdapterPosition());

    }
}