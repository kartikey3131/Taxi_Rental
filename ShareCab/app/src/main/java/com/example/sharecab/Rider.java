package com.example.sharecab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

public class Rider extends AppCompatActivity {

    private EditText mode;
    private EditText pric;
    private EditText ca;
    private Button register;
    private FirebaseAuth auth1;
    DatabaseReference databaseReference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Drivers");
        mode =findViewById(R.id.model);
        pric =findViewById(R.id.price);
        ca =findViewById(R.id.car);
        register =findViewById(R.id.register);
        auth1 =FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String m=mode.getText().toString();
                final String p =pric.getText().toString();
                final String c =ca.getText().toString();
                if(m.isEmpty())
                {
                    Toast.makeText(Rider.this,"Please Enter Car model",Toast.LENGTH_SHORT);
                }
                else if(p.isEmpty()){
                    Toast.makeText(Rider.this,"Please Enter Price",Toast.LENGTH_SHORT);
                }
                else if(c.isEmpty()){
                    Toast.makeText(Rider.this,"Please Enter Car number",Toast.LENGTH_SHORT);
                }
                else{
                    final ProgressDialog progress = new ProgressDialog(Rider.this);
                    progress.setMessage("Registering...");
                    progress.show();
                    Intent intent1 =getIntent();
                    final String email_s2 = intent1.getStringExtra("email").trim();
                    String password_s2 =intent1.getStringExtra("password").trim();
                    final String nam1 =intent1.getStringExtra("name").trim();
                    final String con1 =intent1.getStringExtra("contact").trim();
                    auth1.createUserWithEmailAndPassword(email_s2, password_s2).addOnCompleteListener(Rider.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String id1 = databaseReference1.push().getKey();
                                String e="Driver";
                                User2 u =new User2(nam1,con1,c,m,p,e);
                                databaseReference1.child(id1).setValue(u);
                                Intent intent = new Intent(Rider.this, MainActivity.class);
                                intent.putExtra("name",email_s2);
                                startActivity(intent);
                            } else {
                                String s=task.getException().toString();
                                Toast.makeText(Rider.this, "Registration failed/Email Id is already registered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
