package com.example.fragmentactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link loginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loginFragment extends Fragment {

    private FirebaseAuth auth;
    private EditText login_email,login_pass;
    private Button l_btn;
    private Button tos_btn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public loginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment loginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static loginFragment newInstance(String param1, String param2) {
        loginFragment fragment = new loginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        initComponents(v);
        settingUpListeners(v);

        return v;
    }

    void settingUpListeners(View v) {
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
                                Toast.makeText(v.getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(v.getContext(), LandingActivity.class));

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(v.getContext(), "LoginFailed", Toast.LENGTH_SHORT).show();
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
            startActivity(new Intent(v.getContext(),signupFragment.class));
        });


    }

    void initComponents(View v) {
        login_email = v.findViewById(R.id.l_email);
        login_pass = v.findViewById(R.id.l_password);
        l_btn = v.findViewById(R.id.loginbtn);
        tos_btn = v.findViewById(R.id.tosignup);
        auth = FirebaseAuth.getInstance();
    }
}