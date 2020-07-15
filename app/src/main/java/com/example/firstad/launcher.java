package com.example.firstad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class launcher extends AppCompatActivity {

    private static int splashtimeout=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);


        new Handler().postDelayed(new Runnable() {
            @Override
            public  void run(){
                Intent homeIntent=new Intent(launcher.this,register_as.class);//name of the 2nd activity
                startActivity(homeIntent);
                finish();
            }

        },splashtimeout
        );
    }
}
