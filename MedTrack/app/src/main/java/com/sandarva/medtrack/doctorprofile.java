package com.sandarva.medtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class doctorprofile extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText userFirstName, userLastName, userDocID, userPhoneNumber, userEmergencyNumber, userDepartment;
    RadioGroup userGender;
    RadioButton genderOption;
    Button infoSubmitBtn;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore store;
    String userID;
    String uGender;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorprofile);
        firebaseAuth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        userFirstName = findViewById(R.id.firstName);
        userLastName = findViewById(R.id.lastName);
        userDocID = findViewById(R.id.doctorID);
        userPhoneNumber = findViewById(R.id.phoneNumber);
        userEmergencyNumber = findViewById(R.id.emergencyNumber);
        userDepartment = findViewById(R.id.doctorDepartment);
        infoSubmitBtn = findViewById(R.id.buttonInfo);
        userGender = findViewById(R.id.gender);

      //  getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("General Information");

        infoSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstN = userFirstName.getText().toString();
                String lastN = userLastName.getText().toString();
                String doctorID = userDocID.getText().toString();
                String pNumber = userPhoneNumber.getText().toString();
                String eNumber = userEmergencyNumber.getText().toString();
                String department = userDepartment.getText().toString();

                if(TextUtils.isEmpty(firstN)){
                    userFirstName.setError("Please enter your first name.");
                    return;
                }

                if(TextUtils.isEmpty(lastN)){
                    userLastName.setError("Please enter your last name.");
                    return;
                }

                if(TextUtils.isEmpty(doctorID)){
                    userDocID.setError("Please enter your ID.");
                    return;
                }

                if(TextUtils.isEmpty(pNumber)){
                    userPhoneNumber.setError("Please enter your number.");
                    return;
                }

                if(TextUtils.isEmpty(eNumber)){
                    userEmergencyNumber.setError("Please enter your emergency number.");
                    return;
                }

                if(TextUtils.isEmpty(department)){
                    userDepartment.setError("Please enter your Department.");
                    return;
                }

                userID = firebaseAuth.getCurrentUser().getUid();
                DocumentReference documentReference = store.collection("doctors").document(userID);
                Map<String, Object> user = new HashMap<>();
                store.collection("doctors").document(userID).update(user);
                user.put("firstName", firstN);
                user.put("lastName", lastN);
                user.put("DoctorID", doctorID);
                user.put("phNumber", pNumber);
                user.put("emNumber", eNumber);
                user.put("DoctorDepartment", department);
                user.put("gender",uGender);

                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG,"onSuccess: user info saved successfully for "+ userID);
                    }
                });

            }
        });

        userGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                genderOption = userGender.findViewById(checkedId);

                switch (checkedId){
                    case R.id.male:
                        uGender = genderOption.getText().toString();
                        break;
                    case R.id.female:
                        uGender = genderOption.getText().toString();
                        break;

                    default:
                }
            }
        });

    }

    }
