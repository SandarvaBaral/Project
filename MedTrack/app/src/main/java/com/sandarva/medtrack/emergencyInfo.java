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

public class emergencyInfo extends AppCompatActivity {

    TextView  proFullName, proFirstName, proSecondName, proDOB, proPhNum, proEmergencyNum, proMedInfo, urGender;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore store;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_info);

        firebaseAuth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        proFirstName = findViewById(R.id.fnameTextView);
        proSecondName = findViewById(R.id.lnameTextView);
        proDOB = findViewById(R.id.dobTextView);
        proPhNum = findViewById(R.id.phTextView);
        proEmergencyNum = findViewById(R.id.ephTextView);
        proMedInfo = findViewById(R.id.minfoTextView);
        proFullName = findViewById(R.id.fnTextView);
        urGender = findViewById(R.id.genderTextView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Emergency Information");

        final DocumentReference documentReference = store.collection("patients").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                proFirstName.setText(documentSnapshot.getString("fName"));
                proSecondName.setText(documentSnapshot.getString("lName"));
                proDOB.setText(documentSnapshot.getString("dBirth"));
                proPhNum.setText(documentSnapshot.getString("phNumber"));
                proEmergencyNum.setText(documentSnapshot.getString("emNumber"));
                proMedInfo.setText(documentSnapshot.getString("mDesp"));
                proFullName.setText(documentSnapshot.getString("fName"));
                urGender.setText(documentSnapshot.getString("gender"));

            }
        });

    }
}
