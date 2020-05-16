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
import com.google.common.base.MoreObjects;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText emailID, password;
    Button btnSignIn, btnDocSignin;
    TextView tvSignUp;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        tvSignUp = findViewById(R.id.signUp);
        btnSignIn = findViewById(R.id.Login);
        btnDocSignin = findViewById(R.id.docLogin);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                //FireBase authentication listener to check if the user email ID and password exist
                if(firebaseUser != null) {
                    Toast.makeText(LoginActivity.this,"Logged Successfully",Toast.LENGTH_SHORT);
                    Intent homePage = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(homePage);
                } else {
                    Toast.makeText(LoginActivity.this,"Please Login",Toast.LENGTH_SHORT);
                }

            }
        };

        //Sign in button Action
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailID.getText().toString();
                String pwd = password.getText().toString();
                //Check if the email is empty
                if (email.isEmpty()) {
                    emailID.setError("Please Enter The Email"); //Display error
                    emailID.requestFocus(); //Highlight the field
                } else if (pwd.isEmpty()) {
                    password.setError("Please Enter The Password");
                    password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please Check If Details Are Filled In", Toast.LENGTH_SHORT);
                } else if (!(email.isEmpty() && pwd.isEmpty())) { //If the information is correct Sign in
                    firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Error, Check the username and password", Toast.LENGTH_SHORT);
                            } else {
                                Intent toHome = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(toHome);
                            }
                        }
                    });

                } else {
                    //Only show this if error occurred during Signing up
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT);
                }
            }
        });


        //Add code for doctor login
        btnDocSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailID.getText().toString();
                String pwd = password.getText().toString();
                //Check if the email is empty
                if (email.isEmpty()) {
                    emailID.setError("Please Enter The Email"); //Display error
                    emailID.requestFocus(); //Highlight the field
                } else if (pwd.isEmpty()) {
                    password.setError("Please Enter The Password");
                    password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please Check If Details Are Filled In", Toast.LENGTH_SHORT);
                } else if (!(email.isEmpty() && pwd.isEmpty())) { //If the information is correct Sign in
                    firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Error, Check the username and password", Toast.LENGTH_SHORT);
                            } else {
                                Intent toHome = new Intent(LoginActivity.this,doctorHome.class);
                                startActivity(toHome);
                            }
                        }
                    });

                } else {
                    //Only show this if error occurred during Signing up
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT);
                }
            }
        });

        //Active Listener on sign up
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Move to sign up screen
                Intent toSignUp = new Intent(LoginActivity.this, SignUp.class);
                startActivity(toSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);

    }
}