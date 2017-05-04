package com.example.anthonylee.gift;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

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
    private int User_Rate;
    private String User_Photo;
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
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    GiftInfo  gift = ds.getValue(GiftInfo.class);
                    gift_list.add(gift);
                    Log.e("giftinfo",gift.getRuid());
                }

//                String gUid = gift.get("GUid").toString();
//                String gType = gift.get("Gtype").toString();
//                String rUid = gift.get("RUid").toString();
//                GiftInfo giftInfo = new GiftInfo(gUid,gType,rUid);
                for (int i=0; i<gift_list.size(); i++) {
                    if (gift_list.get(i).getRuid().equals(uid)) {
                        Log.e("filtered","get");
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


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
//        for (int i=0; i<gift_list.size(); i++) {
//            if (gift_list.get(i).getRuid().equals(uid)) {
//                Log.e("filtered","get");
//                gift_list_filtered.add(gift_list.get(i));
//            }
//        }
//
//        List<ItemObject> rowListItem = getAllItemList();
//        if (!rowListItem.isEmpty()) {
//            lLayout = new GridLayoutManager(Gift_Received.this, 3);
//
//            RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view);
//            rView.setHasFixedSize(true);
//            rView.setLayoutManager(lLayout);
//
//            RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(Gift_Received.this, rowListItem);
//            rView.setAdapter(rcAdapter);
//        } else {
//            // 設定textView = "目前尚未收到禮物"
//            Toast.makeText(Gift_Received.this,"尚未收到任何禮物",Toast.LENGTH_SHORT).show();
//        }

        //User_level
        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Toast.makeText(getApplication(),"GETDATA",Toast.LENGTH_LONG).show();
                Long U_Rate = (Long)dataSnapshot.child(uid).child("Rate").getValue();
                String U_Photo = dataSnapshot.child(uid).child("Photo").getValue().toString();
                //Toast.makeText(getApplication(),U_Photo+ "=>U_Photo",Toast.LENGTH_LONG).show();



                User_Rate = U_Rate.intValue();
                User_Photo = U_Photo;
//                for (DataSnapshot ds : dataSnapshot.getChildren()){
//                    if (ds.getValue().toString().equals(uid)){
//                        User_Rate = ds.child("Rate").getValue(Long.class).intValue();
//                        User_Photo = ds.child("Photo").getValue(String.class);
//                    }
//                }
                //Toast.makeText(getApplication(),User_Photo,Toast.LENGTH_LONG).show();
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

                Toast.makeText(getApplication(), User_Photo, Toast.LENGTH_LONG).show();
                Uri u_photo = Uri.parse(User_Photo);
                Picasso.with(getApplication())
                        .load(u_photo)
                        .into(user_photo);
                user_level.setText(User_Level);
                user_rate.setText("目前積分：" + User_Rate);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        if (User_Rate < 100){
//            User_Level = level[0];
//        } else if (User_Rate >= 100 && User_Rate < 300){
//            User_Level = level[1];
//        } else if (User_Rate >= 300 && User_Rate < 700){
//            User_Level = level[2];
//        } else if (User_Rate >= 700 && User_Rate < 1500){
//            User_Level = level[3];
//        } else if (User_Rate >= 1500){
//            User_Level = level[4];
//        }
//
//            Toast.makeText(getApplication(), User_Photo, Toast.LENGTH_LONG).show();
//            Uri u_photo = Uri.parse(User_Photo);
//            user_photo.setImageURI(u_photo);
//            user_level.setText(User_Level);
//            user_rate.setText("目前積分：" + User_Rate);

    }


    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        Log.e("gift_list_filtered",gift_list_filtered.size()+"");
        for (int i=0; i<gift_list_filtered.size(); i++){

            allItems.add(new ItemObject(gift_list_filtered.get(i).getGuid(),g_photo[Integer.parseInt(gift_list_filtered.get(i).getGtype())]));
        }
//        Toast.makeText(getApplication(),allItems.get(0).getName(),Toast.LENGTH_LONG).show();
        return allItems;
    }
}
