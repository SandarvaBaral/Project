package com.sandarva.medtrack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class HomeActivity extends AppCompatActivity {

    TextView profileName, informationPage, emergencyProfile, medicineSearch, listDoctors, appointment, mReminder;
    ImageView userProfilePic;
    Button btnLogout;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore store;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid(); //userIdRef
        btnLogout = findViewById(R.id.logout);
        profileName = findViewById(R.id.nameTextView);
        userProfilePic = findViewById(R.id.userImageView);
        informationPage = findViewById(R.id.informationForm);
        emergencyProfile = findViewById(R.id.emergencyInfo);
        medicineSearch = findViewById(R.id.medSearch);
        listDoctors = findViewById(R.id.viewDoctors);
        appointment = findViewById(R.id.uAppointment);
        mReminder = findViewById(R.id.reminder_page);

        final DocumentReference documentReference = store.collection("patients").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                profileName.setText(documentSnapshot.getString("fName"));
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent backToHome = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(backToHome);
                finish();
            }
        });

        //Active listener for to back to Login page
        informationPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toInformationPage = new Intent(HomeActivity.this, medical_form.class);
                startActivity(toInformationPage);
            }
        });

        emergencyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toEmergencyPage = new Intent(HomeActivity.this, emergencyInfo.class);
                startActivity(toEmergencyPage);
            }
        });

        medicineSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSearchMed = new Intent(HomeActivity.this, search_medicine.class);
                startActivity(toSearchMed);
            }
        });

        listDoctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDoctorList = new Intent(HomeActivity.this, list_view_doctor.class);
                startActivity(toDoctorList);
            }
        });

        mReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toReminderPage = new Intent(HomeActivity.this, Reminder.class);
                startActivity(toReminderPage);
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAppointmentForm = new Intent(HomeActivity.this, appointments.class);
                startActivity(toAppointmentForm);
            }
        });

    }
}
