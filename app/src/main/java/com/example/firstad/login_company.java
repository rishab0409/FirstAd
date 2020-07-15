package com.example.firstad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_company extends AppCompatActivity {
    EditText compemail;
    EditText comppass;
    Button compreg;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_company);

        compemail=findViewById(R.id.compemail);
        comppass=findViewById(R.id.comppass);
        compreg=findViewById(R.id.compreg);
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null ){
                    Toast.makeText(login_company.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(login_company.this, MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(login_company.this,"Please Sign Up",Toast.LENGTH_SHORT).show();
                }
            }
        };

        compreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = compemail.getText().toString();
                String pwd = comppass.getText().toString();

                if(email.isEmpty()){
                    compemail.setError("Please enter email id");
                    compemail.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    comppass.setError("Please enter your password");
                    comppass.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(login_company.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(login_company.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(login_company.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(login_company.this,select_socialmedia.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(login_company.this,"Error Occurred!", Toast.LENGTH_SHORT).show();

                }
            }

        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
