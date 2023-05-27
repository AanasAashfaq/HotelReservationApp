package com.example.fragmentactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.ktx.Firebase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText login_email,login_pass;
    private AppCompatButton l_btn;
    private TextView tos_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        initcomponents();
        auth = FirebaseAuth.getInstance();
        l_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getText().toString();
                String pass = login_pass.getText().toString();

                if(!email.isEmpty())
                {
                    if(!pass.isEmpty())
                    {
                        auth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, LandingActivity.class));
                            finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "LoginFailed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else
                    {
                        login_pass.setError("Please Enter password");
                    }
                }
                else if(email.isEmpty())
                {
                 login_email.setError("please enter email");
                }
                else
                {
                    login_email.setError("please Enter Valid email");
                }
            }
        });

        tos_btn.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this,signupActivity.class));
        });

    }

    private void initcomponents() {
        login_email = findViewById(R.id.l_email);
        login_pass = findViewById(R.id.l_password);
        l_btn = findViewById(R.id.loginbtn);
        tos_btn = findViewById(R.id.tosignup);
    }
}