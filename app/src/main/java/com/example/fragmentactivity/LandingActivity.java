package com.example.fragmentactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fragmentactivity.data.hotel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class LandingActivity extends AppCompatActivity {
    EditText h_name,h_address,h_amneties;
    Button add_btn;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        initcomponents();
        settinguplistners();

    }

    private void settinguplistners() {
        add_btn.setOnClickListener(view->{
            hotel h1 = new hotel();
            h1.setHotelname(h_name.getText().toString());
            h1.setHoteladdress(h_address.getText().toString());
            h1.setHotelamneties(h_amneties.getText().toString());
            String id = db.collection("Hotel").document().getId();
            h1.setId(id);
            db.collection("Hotel").document(id).set(h1).addOnCompleteListener(task->{
                if(task.isSuccessful()){
                    Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
            db.collection("Hotel").document("DqJI7Eidhj4mDTOxc4uo").addSnapshotListener((value, error) -> {


            });
            db.collection("Hotel").document("").delete().addOnCompleteListener();
            //getall
            db.collection("Hotel").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        List<hotel> myList =  value.toObjects(hotel.class);

                }
            });
        });
    }

    private void initcomponents() {
        h_name= findViewById(R.id.Hotelname);
        h_address = findViewById(R.id.Hoteladdress);
        h_amneties = findViewById(R.id.Hotelamneties);
        add_btn = findViewById(R.id.addBtn);
        db = FirebaseFirestore.getInstance();

    }
}