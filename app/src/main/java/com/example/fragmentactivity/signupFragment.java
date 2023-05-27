package com.example.fragmentactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link signupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class signupFragment extends Fragment {

    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword;
    private AppCompatButton signup_btn;
    private TextView tol_btn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public signupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment signupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static signupFragment newInstance(String param1, String param2) {
        signupFragment fragment = new signupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signup, container, false);

        initComponents(v);
        settingUpListeners(v);



        return v;
    }

    void settingUpListeners(View v) {
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
                                Toast.makeText(v.getContext(), "Signup Success", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(v.getContext(),loginFragment.class));
                                startActivity(new Intent(getContext(),LandingActivity.class));
                            }
                            else
                            {
                                Toast.makeText(v.getContext(), "Signup UnSuccess", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        tol_btn.setOnClickListener(view->{
            startActivity(new Intent(v.getContext(),loginFragment.class));
        });

    }


    private void initComponents(View v) {
        signupEmail = v.findViewById(R.id.name);
        signupPassword = v.findViewById(R.id.password);
        signup_btn = v.findViewById(R.id.s_btn);
        tol_btn = v.findViewById(R.id.toLogin);
        auth = FirebaseAuth.getInstance();
    }
}