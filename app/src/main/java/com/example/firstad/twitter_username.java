package com.example.firstad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class twitter_username extends AppCompatActivity {
    String twitterusername="";
    String twitterfollowersstr;
    String twitterlikesstr;
    String twitterurl="https://socialblade.com/twitter/user/";
    String avgretweetstr;
    EditText twitteruser;
    Button twitterbtn;
    FirebaseUser currentFirebaseUser;
    DatabaseReference db;
    String uid;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_username);
        twitteruser=(EditText)findViewById(R.id.twitteruser);
        twitterbtn=(Button)findViewById(R.id.twitterbtn);
        //uid=currentFirebaseUser.getUid();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db= FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("twitter");

        twitterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twitterusername=twitteruser.getText().toString();
                twitterurl=twitterurl+twitterusername;
                new doit().execute();

                progressDialog=new ProgressDialog(twitter_username.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );
            }
        });
    }

    public class doit extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc= Jsoup.connect(twitterurl).get();
                Elements tfollowers = doc.select("#YouTubeUserTopInfoBlock > div:nth-child(2) > span:nth-child(3)");
                Elements likes=doc.select("#YouTubeUserTopInfoBlock > div:nth-child(4) > span:nth-child(3)");
                Elements retweet=doc.select("#YouTubeUserTopInfoBlock > div:nth-child(5) > span:nth-child(3)");
//               for(Element i:subscriber)
//               {
//                   subscriber_str=subscriber_str+i.text();
//               }
                twitterfollowersstr=tfollowers.text();
                twitterlikesstr=likes.text();
                avgretweetstr=retweet.text();
            }catch (Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //upload to database section
            Map map=new HashMap();
            map.put("value1",twitterusername);
            map.put("value2",twitterfollowersstr);
            map.put("value3",twitterlikesstr);
            map.put("value4",avgretweetstr);
            map.put("head","TWITTER");
            map.put("field1","Username");
            map.put("field2","Followers");
            map.put("field3","Likes");
            map.put("field4","Average Likes");
            map.put("img","https://firebasestorage.googleapis.com/v0/b/firstad-f90eb.appspot.com/o/twitter.png?alt=media&token=90244426-2c4a-4cd4-9761-5f40e770dd20");

            db.updateChildren(map);
            progressDialog.dismiss();


        }
    }


}
