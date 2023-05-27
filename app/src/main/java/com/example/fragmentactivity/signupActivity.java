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

import com.example.fragmentactivity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class signupActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword;
    private AppCompatButton signup_btn;
    private TextView tol_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_signup);
        initcomponents();
        auth = FirebaseAuth.getInstance();

        signup_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String user = signupEmail.getText().toString();
                String pass = signupPassword.getText().toString();
                if(user.isEmpty())
                {
                    signupEmail.setError("Email cannot be Empty");
                }
                if(pass.isEmpty())
                {
                    signupPassword.setError("Password cannot be Empty");
                }
                else{
                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(signupActivity.this, "Signup Success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(signupActivity.this,LoginActivity.class));
                            }
                            else
                            {
                                Toast.makeText(signupActivity.this, "Signup UnSuccess", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
        tol_btn.setOnClickListener(view->{
            startActivity(new Intent(signupActivity.this,LoginActivity.class));
        });

    }

    private void initcomponents() {
        signupEmail = findViewById(R.id.name);
        signupPassword = findViewById(R.id.password);
        signup_btn = findViewById(R.id.s_btn);
        tol_btn = findViewById(R.id.toLogin);
    }
}










