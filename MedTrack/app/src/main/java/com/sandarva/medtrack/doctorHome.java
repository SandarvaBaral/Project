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

public class doctorHome extends AppCompatActivity {

    TextView profileName, informationPage, emergencyProfile, medicineSearch, mPatients, mAppointments,mReminder_set;
    ImageView userProfilePic;
    Button btnLogout;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore store;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        firebaseAuth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid(); //userIdRef
        btnLogout = findViewById(R.id.logout);
        profileName = findViewById(R.id.nameTextView);
        userProfilePic = findViewById(R.id.userImageView);
        informationPage = findViewById(R.id.informationForm);
        emergencyProfile = findViewById(R.id.emergencyInfo);
        medicineSearch = findViewById(R.id.medSearch);
        mPatients = findViewById(R.id.viewPatients);
        mAppointments = findViewById(R.id.viewAppointment);
        mReminder_set = findViewById(R.id.reminder_setter_doc);

        final DocumentReference documentReference = store.collection("doctors").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                profileName.setText(documentSnapshot.getString("firstName"));
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent backToHome = new Intent(doctorHome.this, LoginActivity.class);
                startActivity(backToHome);
                finish();
            }
        });

        //Active listener for to back to Login page
        informationPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toInformationPage = new Intent(doctorHome.this, doctorprofile.class);
                startActivity(toInformationPage);
            }
        });

        emergencyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toEmergencyPage = new Intent(doctorHome.this, doctorInformation.class);
                startActivity(toEmergencyPage);
            }
        });

        medicineSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSearchMeds = new Intent(doctorHome.this, search_medicine.class);
                startActivity(toSearchMeds);
            }
        });

        mAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toViewAppointment = new Intent(doctorHome.this, appointment_view_list.class);
                startActivity(toViewAppointment);
            }
        });

        mPatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPatientsPage = new Intent(doctorHome.this, ListViewMain.class);
                startActivity(toPatientsPage);
            }
        });

        mReminder_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toReminderDoc = new Intent(doctorHome.this, Reminder_Doctor.class);
                startActivity(toReminderDoc);
            }
        });
    }
}
