package com.sandarva.medtrack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class doctorInformation extends AppCompatActivity {

    TextView proFullName, proFirstName, proSecondName, proDocID, proPhNum, proEmergencyNum, proDocDep, urGender;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore store;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_information);

        firebaseAuth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        proFirstName = findViewById(R.id.fnameTextView);
        proSecondName = findViewById(R.id.lnameTextView);
        proDocID = findViewById(R.id.doctorIDTextView);
        proPhNum = findViewById(R.id.phTextView);
        proEmergencyNum = findViewById(R.id.ephTextView);
        proDocDep = findViewById(R.id.deparmentTextView);
        proFullName = findViewById(R.id.fnTextView);
        urGender = findViewById(R.id.genderTextView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Doctor Profile");

        final DocumentReference documentReference = store.collection("doctors").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                proFirstName.setText(documentSnapshot.getString("firstName"));
                proSecondName.setText(documentSnapshot.getString("lastName"));
                proDocID.setText(documentSnapshot.getString("DoctorID"));
                proPhNum.setText(documentSnapshot.getString("phNumber"));
                proEmergencyNum.setText(documentSnapshot.getString("emNumber"));
                proDocDep.setText(documentSnapshot.getString("DoctorDepartment"));
                proFullName.setText(documentSnapshot.getString("firstName"));
                urGender.setText(documentSnapshot.getString("gender"));

            }
        });

    }
}
