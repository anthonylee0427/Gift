package com.example.anthonylee.gift;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.anthonylee.gift.R.drawable.gift;

public class Gift_Received extends AppCompatActivity {

    private GridLayoutManager lLayout;
    private ImageView user_photo;
    private TextView user_level;
    private TextView user_rate;

    private String uname;
    private String uid = "1";
    private Long User_Rate;
    private String User_Photo = "";
    private ArrayList<GiftInfo> gift_list = new ArrayList<GiftInfo>();
    private ArrayList<GiftInfo> gift_list_filtered = new ArrayList<GiftInfo>();
    private int[] g_photo = new int[]{R.drawable.glass, R.drawable.bouquet, R.drawable.teddy_bear, R.drawable.ice_cream, R.drawable.sweater, gift, R.drawable.purse, R.drawable.bicycle, R.drawable.motorbiking};
    private String[] level = new String[]{"初心者","新人","黃金","白金","鑽石"};
    private String User_Level;

    public Gift_Received() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift_received);

        user_photo = (ImageView)findViewById(R.id.user_photo);
        user_level = (TextView)findViewById(R.id.user_level);
        user_rate = (TextView)findViewById(R.id.user_rate);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Gift");
        DatabaseReference myRef1 = database.getReference("User");

        // Read from the database
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                GiftInfo  gift = dataSnapshot.getValue(GiftInfo.class);
//                String gUid = gift.get("GUid").toString();
//                String gType = gift.get("Gtype").toString();
//                String rUid = gift.get("RUid").toString();
//                GiftInfo giftInfo = new GiftInfo(gUid,gType,rUid);
                gift_list.add(gift);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        for (int i=0; i<gift_list.size(); i++) {
            if (gift_list.get(i).getRuid().equals(uid)) {
                gift_list_filtered.add(gift_list.get(i));
            }
        }

        List<ItemObject> rowListItem = getAllItemList();
        if (!rowListItem.isEmpty()) {
            lLayout = new GridLayoutManager(Gift_Received.this, 3);

            RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view);
            rView.setHasFixedSize(true);
            rView.setLayoutManager(lLayout);

            RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(Gift_Received.this, rowListItem);
            rView.setAdapter(rcAdapter);
        } else {
            // 設定textView = "目前尚未收到禮物"
            Toast.makeText(Gift_Received.this,"尚未收到任何禮物",Toast.LENGTH_SHORT).show();
        }

        //User_level
        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long U_Rate = dataSnapshot.child(uid).child("Rate").getValue(Long.class);
                String U_Photo = dataSnapshot.child(uid).child("Photo").getValue(String.class);

                User_Rate = U_Rate;
                User_Photo = U_Photo;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (User_Rate < 100){
            User_Level = level[0];
        } else if (User_Rate >= 100 && User_Rate < 300){
            User_Level = level[1];
        } else if (User_Rate >= 300 && User_Rate < 700){
            User_Level = level[2];
        } else if (User_Rate >= 700 && User_Rate < 1500){
            User_Level = level[3];
        } else if (User_Rate >= 1500){
            User_Level = level[4];
        }
        Uri u_photo = Uri.parse(User_Photo);
        user_photo.setImageURI(u_photo);
        user_level.setText(User_Level);
        user_rate.setText("目前積分："+User_Rate);
    }


    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        for (int i=0; i<gift_list_filtered.size(); i++){
            allItems.add(new ItemObject(gift_list_filtered.get(i).getGuid(),g_photo[Integer.parseInt(gift_list_filtered.get(i).getGtype())]));
        }

        return allItems;
    }
}
