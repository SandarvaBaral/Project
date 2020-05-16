package com.sandarva.medtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText emailID, password;
    Button btnSignUp, btnDocSignUp;
    TextView tvSignIn;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
        emailID = findViewById(R.id.userEmail);
        password = findViewById(R.id.userPassword);
        tvSignIn = findViewById(R.id.toLogin);
        btnSignUp = findViewById(R.id.signUpButton);
        btnDocSignUp = findViewById(R.id.docSignUP);

        //Active listener for Sign Up Button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailID.getText().toString();
                String pwd = password.getText().toString();

                //Check if the email is empty
                if (email.isEmpty()) {
                    emailID.setError("Please enter email id"); //Display error
                    emailID.requestFocus(); //Highlight the field
                } else if (pwd.isEmpty()) {
                    password.setError("Please Enter The Password");
                    password.requestFocus();

                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please Check If Details Are Filled In", Toast.LENGTH_SHORT);
                } else if (!(email.isEmpty() && pwd.isEmpty())) { //If the information is correct create the account
                    firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "Registration Unsuccessful", Toast.LENGTH_SHORT);
                            } else {
                                //On Successful It will bring you to the home screen
                                startActivity(new Intent(SignUp.this, HomeActivity.class));
                            }
                        }
                    });

                } else {
                    //Only show this if error occurred during Signing up
                    Toast.makeText(SignUp.this, "Error", Toast.LENGTH_SHORT);
                }
            }
        });

        btnDocSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailID.getText().toString();
                String pwd = password.getText().toString();

                //Check if the email is empty
                if (email.isEmpty()) {
                    emailID.setError("Please enter email id"); //Display error
                    emailID.requestFocus(); //Highlight the field
                } else if (pwd.isEmpty()) {
                    password.setError("Please Enter The Password");
                    password.requestFocus();

                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please Check If Details Are Filled In", Toast.LENGTH_SHORT);
                } else if (!(email.isEmpty() && pwd.isEmpty())) { //If the information is correct create the account
                    firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "Registration Unsuccessful", Toast.LENGTH_SHORT);
                            } else {
                                //On Successful It will bring you to the home screen
                                startActivity(new Intent(SignUp.this, doctorHome.class));
                            }
                        }
                    });

                } else {
                    //Only show this if error occurred during Signing up
                    Toast.makeText(SignUp.this, "Error", Toast.LENGTH_SHORT);
                }
            }
        });

        //Active listener for to back to Login page
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToLogin = new Intent(SignUp.this, LoginActivity.class);
                startActivity(backToLogin);
            }
        });

    }
}
