package com.example.sharecab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RiderProfile extends AppCompatActivity {

    private TextView rider_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_profile);
        rider_name =findViewById(R.id.rider_name);
        Intent intent =getIntent();
        String s =intent.getStringExtra("Name").toString();
        rider_name.setText(s);
    }
}
