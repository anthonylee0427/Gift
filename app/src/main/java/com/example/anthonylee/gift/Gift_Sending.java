package com.example.anthonylee.gift;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnthonyLee on 4/23/17.
 */

public class Gift_Sending extends AppCompatActivity implements RecyclerViewAdapter.ItemClickCallback{


    private GridLayoutManager lLayout;
    private ImageView F_Photo;
    private TextView Friend;
    private Button Send;

    private List<ItemObject> rowListItem;
    private RecyclerViewAdapter rcAdapter;
    private RecyclerView rView;

    private String uid = "1";
    private String fid = "2";
    private String fname = "";
    private Long User_Rate = new Long(0);
    private String Friend_Photo;
    private int[] g_photo = new int[]{R.drawable.glass, R.drawable.bouquet, R.drawable.teddy_bear, R.drawable.ice_cream, R.drawable.sweater, R.drawable.gift, R.drawable.purse, R.drawable.bicycle, R.drawable.motorbiking};
    private ArrayList<String> g_type;
    private String[] level = new String[]{"初心者","新人","黃金","白金","鑽石"};
    private String User_Level;

    public Gift_Sending(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift_sending);

        F_Photo = (ImageView)findViewById(R.id.friend_photo);
        Friend = (TextView)findViewById(R.id.friend);
        Send = (Button)findViewById(R.id.send);

        //set up recycler view
        rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(Gift_Sending.this, 3);

        rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        rcAdapter = new RecyclerViewAdapter(Gift_Sending.this, rowListItem);
        rView.setAdapter(rcAdapter);

        rcAdapter.setItemClickCallback(this);


        //function to send
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Gift");
        DatabaseReference myRef1 = database.getReference("User");

        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long U_Rate = dataSnapshot.child(uid).child("Rate").getValue(Long.class);

                fname = dataSnapshot.child(fid).child("User_Name").getValue(String.class);
                Friend_Photo = dataSnapshot.child(fid).child("Photo").getValue(String.class);
                User_Rate = U_Rate;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Uri f_photo = Uri.parse(Friend_Photo);
        F_Photo.setImageURI(f_photo);
        Friend.setText("你想送給"+fname+"什麼禮物呢？");

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> allGiftSent = getAllGiftSent();
                for (int i=0;i<allGiftSent.size();i++) {
                    GiftInfo new_gift = new GiftInfo(uid, allGiftSent.get(i), fid);
                    myRef.push().setValue(new_gift);
                }
            }
        });

    }

    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<ItemObject>();

        if (User_Rate<100){
            allItems.add(new ItemObject("Glass",R.drawable.glass_locked));
            allItems.add(new ItemObject("Bouquet",R.drawable.bouquet_locked));
            allItems.add(new ItemObject("Teddy Bear",R.drawable.teddy_bear_locked));
            allItems.add(new ItemObject("Ice Cream",R.drawable.ice_cream_locked));
            allItems.add(new ItemObject("Sweater",R.drawable.sweater_locked));
            allItems.add(new ItemObject("Gift",R.drawable.gift_locked));
            allItems.add(new ItemObject("Purse",R.drawable.purse_locked));
            allItems.add(new ItemObject("Bicycle",R.drawable.bicycle_locked));
            allItems.add(new ItemObject("Scooter",R.drawable.motorbiking_locked));
        } else if (User_Rate>=100 && User_Rate<200){
            allItems.add(new ItemObject("Glass",g_photo[0]));
            allItems.add(new ItemObject("Bouquet",R.drawable.bouquet_locked));
            allItems.add(new ItemObject("Teddy Bear",R.drawable.teddy_bear_locked));
            allItems.add(new ItemObject("Ice Cream",R.drawable.ice_cream_locked));
            allItems.add(new ItemObject("Sweater",R.drawable.sweater_locked));
            allItems.add(new ItemObject("Gift",R.drawable.gift_locked));
            allItems.add(new ItemObject("Purse",R.drawable.purse_locked));
            allItems.add(new ItemObject("Bicycle",R.drawable.bicycle_locked));
            allItems.add(new ItemObject("Scooter",R.drawable.motorbiking_locked));
        } else if (User_Rate>=200 && User_Rate<300){
            allItems.add(new ItemObject("Glass",g_photo[0]));
            allItems.add(new ItemObject("Bouquet",g_photo[1]));
            allItems.add(new ItemObject("Teddy Bear",R.drawable.teddy_bear_locked));
            allItems.add(new ItemObject("Ice Cream",R.drawable.ice_cream_locked));
            allItems.add(new ItemObject("Sweater",R.drawable.sweater_locked));
            allItems.add(new ItemObject("Gift",R.drawable.gift_locked));
            allItems.add(new ItemObject("Purse",R.drawable.purse_locked));
            allItems.add(new ItemObject("Bicycle",R.drawable.bicycle_locked));
            allItems.add(new ItemObject("Scooter",R.drawable.motorbiking_locked));
        } else if (User_Rate>=300 && User_Rate<400){
            allItems.add(new ItemObject("Glass",g_photo[0]));
            allItems.add(new ItemObject("Bouquet",g_photo[1]));
            allItems.add(new ItemObject("Teddy Bear",g_photo[2]));
            allItems.add(new ItemObject("Ice Cream",R.drawable.ice_cream_locked));
            allItems.add(new ItemObject("Sweater",R.drawable.sweater_locked));
            allItems.add(new ItemObject("Gift",R.drawable.gift_locked));
            allItems.add(new ItemObject("Purse",R.drawable.purse_locked));
            allItems.add(new ItemObject("Bicycle",R.drawable.bicycle_locked));
            allItems.add(new ItemObject("Scooter",R.drawable.motorbiking_locked));
        } else if (User_Rate>=400 && User_Rate<500){
            allItems.add(new ItemObject("Glass",g_photo[0]));
            allItems.add(new ItemObject("Bouquet",g_photo[1]));
            allItems.add(new ItemObject("Teddy Bear",g_photo[2]));
            allItems.add(new ItemObject("Ice Cream",g_photo[3]));
            allItems.add(new ItemObject("Sweater",R.drawable.sweater_locked));
            allItems.add(new ItemObject("Gift",R.drawable.gift_locked));
            allItems.add(new ItemObject("Purse",R.drawable.purse_locked));
            allItems.add(new ItemObject("Bicycle",R.drawable.bicycle_locked));
            allItems.add(new ItemObject("Scooter",R.drawable.motorbiking_locked));
        } else if (User_Rate>=500 && User_Rate<600){
            allItems.add(new ItemObject("Glass",g_photo[0]));
            allItems.add(new ItemObject("Bouquet",g_photo[1]));
            allItems.add(new ItemObject("Teddy Bear",g_photo[2]));
            allItems.add(new ItemObject("Ice Cream",g_photo[3]));
            allItems.add(new ItemObject("Sweater",g_photo[4]));
            allItems.add(new ItemObject("Gift",R.drawable.gift_locked));
            allItems.add(new ItemObject("Purse",R.drawable.purse_locked));
            allItems.add(new ItemObject("Bicycle",R.drawable.bicycle_locked));
            allItems.add(new ItemObject("Scooter",R.drawable.motorbiking_locked));
        } else if (User_Rate>=600 && User_Rate<700){
            allItems.add(new ItemObject("Glass",g_photo[0]));
            allItems.add(new ItemObject("Bouquet",g_photo[1]));
            allItems.add(new ItemObject("Teddy Bear",g_photo[2]));
            allItems.add(new ItemObject("Ice Cream",g_photo[3]));
            allItems.add(new ItemObject("Sweater",g_photo[4]));
            allItems.add(new ItemObject("Gift",g_photo[5]));
            allItems.add(new ItemObject("Purse",R.drawable.purse_locked));
            allItems.add(new ItemObject("Bicycle",R.drawable.bicycle_locked));
            allItems.add(new ItemObject("Scooter",R.drawable.motorbiking_locked));
        } else if (User_Rate>=700 && User_Rate<800){
            allItems.add(new ItemObject("Glass",g_photo[0]));
            allItems.add(new ItemObject("Bouquet",g_photo[1]));
            allItems.add(new ItemObject("Teddy Bear",g_photo[2]));
            allItems.add(new ItemObject("Ice Cream",g_photo[3]));
            allItems.add(new ItemObject("Sweater",g_photo[4]));
            allItems.add(new ItemObject("Gift",g_photo[5]));
            allItems.add(new ItemObject("Purse",g_photo[6]));
            allItems.add(new ItemObject("Bicycle",R.drawable.bicycle_locked));
            allItems.add(new ItemObject("Scooter",R.drawable.motorbiking_locked));
        } else if (User_Rate>=800 && User_Rate<900){
            allItems.add(new ItemObject("Glass",g_photo[0]));
            allItems.add(new ItemObject("Bouquet",g_photo[1]));
            allItems.add(new ItemObject("Teddy Bear",g_photo[2]));
            allItems.add(new ItemObject("Ice Cream",g_photo[3]));
            allItems.add(new ItemObject("Sweater",g_photo[4]));
            allItems.add(new ItemObject("Gift",g_photo[5]));
            allItems.add(new ItemObject("Purse",g_photo[6]));
            allItems.add(new ItemObject("Bicycle",g_photo[7]));
            allItems.add(new ItemObject("Scooter",R.drawable.motorbiking_locked));
        } else if (User_Rate>=900){
            allItems.add(new ItemObject("Glass",g_photo[0]));
            allItems.add(new ItemObject("Bouquet",g_photo[1]));
            allItems.add(new ItemObject("Teddy Bear",g_photo[2]));
            allItems.add(new ItemObject("Ice Cream",g_photo[3]));
            allItems.add(new ItemObject("Sweater",g_photo[4]));
            allItems.add(new ItemObject("Gift",g_photo[5]));
            allItems.add(new ItemObject("Purse",g_photo[6]));
            allItems.add(new ItemObject("Bicycle",g_photo[7]));
            allItems.add(new ItemObject("Scooter",g_photo[8]));
        }

        return allItems;
    }


    @Override
    public void onItemClick(int p) {
        ItemObject item = (ItemObject) rowListItem.get(p);

        //update our data
        if (item.isSent()){
            item.setToSend(false);
        } else {
            item.setToSend(true);
        }

        //pass new data to adapter and update
        rcAdapter.setListData(rowListItem);
        rcAdapter.notifyDataSetChanged();
    }

    private ArrayList<String> getAllGiftSent(){
        for (int x=0; x<rowListItem.size(); x++){
            if (rowListItem.get(x).isSent()){
                g_type.add(String.valueOf(x));
            }
        }
        return g_type;
    }
}
