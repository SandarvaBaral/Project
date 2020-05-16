  package com.sandarva.medtrack;
//
  import androidx.appcompat.app.AppCompatActivity;
//

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
  import android.widget.Toast;

  import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class appointments extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mfirstName, mSecondName, mPhoneNumber, mEmailAddress, mDate;
    Button mSubmitAppointment;
    RadioGroup mTimeSelect1;
    RadioButton mTimeChoosen;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore store;
    String userID;
    String time;

      @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        firebaseAuth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        mfirstName = findViewById(R.id.etName);
        mSecondName = findViewById(R.id.etLName);
        mPhoneNumber = findViewById(R.id.et_number);
        mEmailAddress = findViewById(R.id.et_email_address);
        mDate = findViewById(R.id.et_date_app);
        mSubmitAppointment = findViewById(R.id.submit_appointment);
        mTimeSelect1 = findViewById(R.id.radio_btn_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Book an Appointment");

        mSubmitAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String first_name = mfirstName.getText().toString();
                String last_name = mSecondName.getText().toString();
                String phone_number = mPhoneNumber.getText().toString();
                String email_address = mEmailAddress.getText().toString();
                String appointment_date = mDate.getText().toString();

                if (TextUtils.isEmpty(first_name)) {
                    mfirstName.setError("Please enter your first name");
                    return;
                }

                if (TextUtils.isEmpty(last_name)) {
                    mSecondName.setError("Please enter your last name");
                    return;
                }

                if (TextUtils.isEmpty(phone_number)) {
                    mPhoneNumber.setError("Please enter your phone number");
                    return;
                }

                if (TextUtils.isEmpty(email_address)) {
                    mEmailAddress.setError("Please enter your email address");
                    return;
                }

                if (TextUtils.isEmpty(appointment_date)) {
                    mDate.setError("Please enter the date");
                    return;
                }

                userID = firebaseAuth.getCurrentUser().getUid();
                DocumentReference documentReference = store.collection("appointment").document(userID);
                Map<String, Object> user = new HashMap<>();
                store.collection("appointment").document(userID).update(user);
                user.put("FirstName", first_name);
                user.put("SecondName", last_name);
                user.put("PhoneNumber", phone_number);
                user.put("EmailAddress", email_address);
                user.put("AppointmentDate", appointment_date);
                user.put("Time", time);

                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(appointments.this, "Appointment Booked", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mTimeSelect1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                mTimeChoosen = mTimeSelect1.findViewById(checkedId);

                switch (checkedId) {
                    case R.id.radioButton:
                        time = mTimeChoosen.getText().toString();
                        break;

                    case R.id.radioButton1:
                        time = mTimeChoosen.getText().toString();
                        break;

                    case R.id.radioButton2:
                        time = mTimeChoosen.getText().toString();
                        break;

                    case R.id.radioButton3:
                        time = mTimeChoosen.getText().toString();
                        break;
                    default:
                }
            }
        });
    }
}