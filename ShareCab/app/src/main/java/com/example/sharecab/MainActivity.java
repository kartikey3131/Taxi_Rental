package com.example.sharecab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button signout;
    private FirebaseAuth auth;
    private TextView username;
    private Button button;
    private List<User2>drivers = new ArrayList<>();
    DatabaseReference databaseReference;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signout = findViewById(R.id.signout);
        username =findViewById(R.id.username);
        button =findViewById(R.id.button3);
        auth =FirebaseAuth.getInstance();
        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        final Intent intent =getIntent();
        final String p;
        if(account!=null){
            String email = account.getEmail();
            p=email;
            username.setText(email);
        }
        else {
            String email = intent.getStringExtra("name");
            p=email;
            username.setText(email);
        }
        if(auth.getCurrentUser()!=null || account!=null)
        {
            signout.setVisibility(View.VISIBLE);
        }
        else {
            signout.setVisibility(View.INVISIBLE);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent(MainActivity.this,ListofDrivers.class);
                startActivity(intent1);
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth.getCurrentUser()!=null){
                    auth.signOut();
                    Toast.makeText(MainActivity.this, "Succesfully logged out ", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent1);
                }
                else if(account!=null){
                    mGoogleSignInClient.signOut().addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(MainActivity.this, "Succesfully logged out ", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent1);
                        }
                    });

                }
                else{
                    Toast.makeText(MainActivity.this, "Already Logged out ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        auth.signOut();
    }
}
