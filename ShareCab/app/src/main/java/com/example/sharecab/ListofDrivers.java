package com.example.sharecab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class ListofDrivers extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private ArrayList<User2> list;
    RecyclerView recyclerView;
    ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_drivers);
        recyclerView = findViewById(R.id.drivers);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListofDrivers.this));
        databaseReference =FirebaseDatabase.getInstance().getReference().child("Drivers");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list =new ArrayList<User2>();
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    User2 u =ds.getValue(User2.class);
                    list.add(u);
                }
                adapter =new ListAdapter(ListofDrivers.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

}
