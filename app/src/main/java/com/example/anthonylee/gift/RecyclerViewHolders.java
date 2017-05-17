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
                    if (Gift_Sending.rowListItem.get(getAdapterPosition()).getPhoto() == R.drawable.glass_locked){
                        Gift_Sending.rowListItem.get(getAdapterPosition()).setToSend(false);
                        itemView.setBackgroundResource(0);
                    } else if (Gift_Sending.rowListItem.get(getAdapterPosition()).getPhoto() == R.drawable.bouquet_locked){
                        Gift_Sending.rowListItem.get(getAdapterPosition()).setToSend(false);
                        itemView.setBackgroundResource(0);
                    } else if (Gift_Sending.rowListItem.get(getAdapterPosition()).getPhoto() == R.drawable.teddy_bear_locked){
                        Gift_Sending.rowListItem.get(getAdapterPosition()).setToSend(false);
                        itemView.setBackgroundResource(0);
                    } else if (Gift_Sending.rowListItem.get(getAdapterPosition()).getPhoto() == R.drawable.ice_cream_locked){
                        Gift_Sending.rowListItem.get(getAdapterPosition()).setToSend(false);
                        itemView.setBackgroundResource(0);
                    } else if (Gift_Sending.rowListItem.get(getAdapterPosition()).getPhoto() == R.drawable.sweater_locked){
                        Gift_Sending.rowListItem.get(getAdapterPosition()).setToSend(false);
                        itemView.setBackgroundResource(0);
                    } else if (Gift_Sending.rowListItem.get(getAdapterPosition()).getPhoto() == R.drawable.gift_locked){
                        Gift_Sending.rowListItem.get(getAdapterPosition()).setToSend(false);
                        itemView.setBackgroundResource(0);
                    } else if (Gift_Sending.rowListItem.get(getAdapterPosition()).getPhoto() == R.drawable.purse_locked){
                        Gift_Sending.rowListItem.get(getAdapterPosition()).setToSend(false);
                        itemView.setBackgroundResource(0);
                    } else if (Gift_Sending.rowListItem.get(getAdapterPosition()).getPhoto() == R.drawable.bicycle_locked){
                        Gift_Sending.rowListItem.get(getAdapterPosition()).setToSend(false);
                        itemView.setBackgroundResource(0);
                    } else if (Gift_Sending.rowListItem.get(getAdapterPosition()).getPhoto() == R.drawable.motorbiking_locked){
                        Gift_Sending.rowListItem.get(getAdapterPosition()).setToSend(false);
                        itemView.setBackgroundResource(0);
                    } else {
                        Gift_Sending.rowListItem.get(getAdapterPosition()).setToSend(true);
                        itemView.setBackgroundResource(R.drawable.chose_style);
                    }
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