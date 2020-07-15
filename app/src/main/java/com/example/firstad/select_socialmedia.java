package com.example.firstad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class select_socialmedia extends AppCompatActivity {

    ImageView youtube_select;
    ImageView facebook_select;
    ImageView instagram_select;
    ImageView twitter_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_socialmedia);
        youtube_select=findViewById(R.id.youtube_select);
        facebook_select=findViewById(R.id.facebook_select);
        instagram_select=findViewById(R.id.instagram_select);
        twitter_select=findViewById(R.id.twitter_select);

        youtube_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(select_socialmedia.this, youtube_username.class);
                startActivity(i);
            }
        });
        facebook_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(select_socialmedia.this, facebook_username.class);
                startActivity(i);
            }
        });
        instagram_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(select_socialmedia.this, instagram_username.class);
                startActivity(i);
            }
        });
        twitter_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(select_socialmedia.this, twitter_username.class);
                startActivity(i);
            }
        });

    }
}
