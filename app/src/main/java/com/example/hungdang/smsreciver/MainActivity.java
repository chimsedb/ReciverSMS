package com.example.hungdang.smsreciver;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<ContactsSMS> list;
    RecyclerView recyclerView;
    AdapterRCSMS adapterRCSMS;
    ArrayList<String> namedevice;

    private DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    Menu menu;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcSMS);
        list = new ArrayList<ContactsSMS>();

        database = FirebaseDatabase.getInstance();
        DatabaseReference Ref = database.getReference();
        DatabaseReference myRef = Ref.child("message").child("message60:45:cb:48:38:f7");

        mDrawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        DatabaseReference Refx = database.getReference();
                        DatabaseReference myRefx = Refx.child("message").child(menuItem.getTitle().toString());

                        list.clear();
                        myRefx.addChildEventListener(new ChildEventListener() {

                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                                String tinnhan = dataSnapshot.child("number").getValue(String.class);
                                String noidung = dataSnapshot.child("message").getValue(String.class).toString();
                                ContactsSMS contactsSMS = new ContactsSMS();
                                contactsSMS.setNoiDung(noidung);
                                contactsSMS.setTinNhan(tinnhan);
                                list.add(contactsSMS);
                                adapterRCSMS.notifyDataSetChanged();
                                Log.d("dsadsa","tin nhan" + contactsSMS.getTinNhan());
//                }

//                Log.d("number 545435345345 : ", "11dsadsa"+list.get(11).getTinNhan());
                            }

                            @Override
                            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        mDrawerLayout.closeDrawers();
                        return true;
                    }

                });

//        myRef.child("xe dap31").setValue("221");




//        Log.d("31231231231",list.get(0).getTinNhan());

//        ReadFB();

        adapterRCSMS = new AdapterRCSMS(list);
        recyclerView.setAdapter(adapterRCSMS);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayout.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
//        contactsSMS = new ContactsSMS();

        myRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                String tinnhan = dataSnapshot.child("number").getValue(String.class);
                String noidung = dataSnapshot.child("message").getValue(String.class).toString();
                ContactsSMS contactsSMS = new ContactsSMS();
                contactsSMS.setNoiDung(noidung);
                contactsSMS.setTinNhan(tinnhan);
                list.add(contactsSMS);
                adapterRCSMS.notifyDataSetChanged();
                Log.d("dsadsa","tin nhan" + contactsSMS.getTinNhan());
//                }

//                Log.d("number 545435345345 : ", "11dsadsa"+list.get(11).getTinNhan());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        final int[] id = {R.id.nav_item_one,R.id.nav_item_two,R.id.nav_item_three,R.id.nav_item_four,R.id.nav_item_five,
//                R.id.nav_item_six,R.id.nav_item_seven};
        DatabaseReference myRef1 = Ref.child("message");
        myRef1.addChildEventListener(new ChildEventListener() {
            int i=0;
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String device = dataSnapshot.getKey();
//                Log.d("321321312","3213213"+namedevice.get(0));
                menu = navigationView.getMenu();
//                menu.findItem(id[i]).setTitle(device);
                menu.add(device);
                i++;
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
