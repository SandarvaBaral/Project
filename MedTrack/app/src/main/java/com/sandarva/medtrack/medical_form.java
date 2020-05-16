package com.sandarva.medtrack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class medical_form extends AppCompatActivity{

    public static final String TAG = "TAG";
    EditText userFirstName, userLastName, userDateOFBirth, userPhoneNumber, userEmergencyNumber, userMedDescription;
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
        setContentView(R.layout.activity_medical_form);

        firebaseAuth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        userFirstName = findViewById(R.id.firstName);
        userLastName = findViewById(R.id.lastName);
        userDateOFBirth = findViewById(R.id.dateOfBirth);
        userPhoneNumber = findViewById(R.id.phoneNumber);
        userEmergencyNumber = findViewById(R.id.emergencyNumber);
        userMedDescription = findViewById(R.id.medDescription);
        infoSubmitBtn = findViewById(R.id.buttonInfo);
        userGender = findViewById(R.id.gender);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("General Information");

        //Active Lister for submit button
        infoSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstN = userFirstName.getText().toString();
                String lastN = userLastName.getText().toString();
                String dofBirth = userDateOFBirth.getText().toString();
                String pNumber = userPhoneNumber.getText().toString();
                String eNumber = userEmergencyNumber.getText().toString();
                String mDescription = userMedDescription.getText().toString();

                if(TextUtils.isEmpty(firstN)){
                    userFirstName.setError("Please enter your first name.");
                    return;
                }

                if(TextUtils.isEmpty(lastN)){
                    userLastName.setError("Please enter your last name.");
                    return;
                }

                if(TextUtils.isEmpty(dofBirth)){
                    userDateOFBirth.setError("Please enter your date of birth.");
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

                if(TextUtils.isEmpty(mDescription)){
                    userMedDescription.setError("Please enter your brief medical history.");
                    return;
                }

                userID = firebaseAuth.getCurrentUser().getUid();
                DocumentReference documentReference = store.collection("patients").document(userID);
                Map<String, Object> user = new HashMap<>();
                store.collection("patients").document(userID).update(user);
                user.put("fName", firstN);
                user.put("lName", lastN);
                user.put("dBirth", dofBirth);
                user.put("phNumber", pNumber);
                user.put("emNumber", eNumber);
                user.put("mDesp", mDescription);
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
