package com.example.sharecab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class Details extends AppCompatActivity {

    private EditText nq;
    private EditText ct;
    private Spinner type;
    private Button submit;
    private FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        databaseReference = FirebaseDatabase.getInstance().getReference("Riders");
        nq =findViewById(R.id.name);
        ct =findViewById(R.id.contact);
        type =findViewById(R.id.type);
        submit =findViewById(R.id.submit);
        auth = FirebaseAuth.getInstance();
        Intent intent =getIntent();
        final String email_s1 =intent.getStringExtra("email");
        final String password_s1 =intent.getStringExtra("password");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nam =nq.getText().toString().trim();
                final String con =ct.getText().toString().trim();
                final String e="Rider";
                final String ty = type.getSelectedItem().toString().trim();
                if(ty.equals(e)){
                    final ProgressDialog progress = new ProgressDialog(Details.this);
                    progress.setMessage("Registering...");
                    progress.show();
                    auth.createUserWithEmailAndPassword(email_s1, password_s1).addOnCompleteListener(Details.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String id = databaseReference.push().getKey();
                                User1 u = new User1(nam,con,e);
                                databaseReference.child(id).setValue(u);
                                FirebaseFirestore.setLoggingEnabled(true);
                                Intent intent = new Intent(Details.this, MainActivity.class);
                                intent.putExtra("name",email_s1);
                                startActivity(intent);
                            } else {
                                String s=task.getException().toString();
                                Toast.makeText(Details.this, "Registration failed/Email Id is already registered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Intent intent1 =new Intent(Details.this,Rider.class);
                    intent1.putExtra("email",email_s1);
                    intent1.putExtra("password",password_s1);
                    intent1.putExtra("name",nam);
                    intent1.putExtra("contact",con);
                    startActivity(intent1);
                }
            }
        });
    }
}
