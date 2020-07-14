package com.example.sharecab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    private EditText email;
    private EditText password;
    private Button login;
    private Button toggle;
    private EditText re_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email =findViewById(R.id.email);
        password= findViewById(R.id.password);
        re_password = findViewById(R.id.re_password);
        toggle = findViewById(R.id.button2);
        login = findViewById(R.id.button);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String option = toggle.getText().toString();
                if(!option.equals("Show")){
                    password.setTransformationMethod(new PasswordTransformationMethod());
                    re_password.setTransformationMethod(new PasswordTransformationMethod());
                    toggle.setText("Show");
                }
                else {
                    password.setTransformationMethod(null);
                    re_password.setTransformationMethod(null);
                    toggle.setText("Hide");
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email_s = email.getText().toString().trim();
                String password_s = password.getText().toString().trim();
                String re_passwords =re_password.getText().toString().trim();
                if(email_s.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please Enter an Email-Id", Toast.LENGTH_SHORT).show();
                }
                else if(password_s.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please Enter a Password", Toast.LENGTH_SHORT).show();
                }
                else if(password_s.length()<6){
                    Toast.makeText(SignupActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                }
                else if(!password_s.equals(re_passwords)){
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent =new Intent(SignupActivity.this, Details.class);
                    intent.putExtra("email",email_s);
                    intent.putExtra("password",password_s);
                    startActivity(intent);
                }
            }
        });
    }
}
