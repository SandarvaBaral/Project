package com.sandarva.medtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Reminder_Doctor extends AppCompatActivity {

    EditText mMedName, mMedTime, mMedShape, mMedDescription;
    Button ReminderSubmitBtn;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore store;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder__doctor);

        firebaseAuth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        mMedName = findViewById(R.id.edit_view_medicine_name);
        mMedTime = findViewById(R.id.edit_view_medicine_time);
        mMedShape = findViewById(R.id.edit_view_medicine_shape);
        mMedDescription = findViewById(R.id.edit_medicine_view_description);
        ReminderSubmitBtn = findViewById(R.id.button_submit_reminder);

//        getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Reminder");

        ReminderSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mmMedName = mMedName.getText().toString();
                String mmTime = mMedTime.getText().toString();
                String mmShape = mMedShape.getText().toString();
                String mmDiscription = mMedDescription.getText().toString();

                userID = firebaseAuth.getCurrentUser().getUid();
                Map<String, String> user = new HashMap<>();
                user.put("name", mmMedName);
                user.put("time", mmTime);
                user.put("shape", mmShape);
                user.put("description", mmDiscription);
                store.collection("reminder").add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(Reminder_Doctor.this, "Reminder Sent", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}