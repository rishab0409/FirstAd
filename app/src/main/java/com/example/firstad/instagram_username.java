package com.example.firstad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class instagram_username extends AppCompatActivity {
    String followersstr="";
    String engagementretestr="";
    String avglikesstr="";
    EditText instausername;
    Button instabtn;
    String instausernamestr="";
    String urlinsta="https://socialblade.com/instagram/user/";
    TextView testi;
    FirebaseUser currentFirebaseUser;
    DatabaseReference db;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_username);
        instausername=(EditText)findViewById(R.id.instausername);
        instabtn=(Button)findViewById(R.id.instabtn);

       // uid=currentFirebaseUser.getUid();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db= FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("instagram");

        instabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instausernamestr=instausername.getText().toString();
                urlinsta=urlinsta+instausernamestr;
                new doit().execute();
            }
        });
    }

    public class doit extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc= Jsoup.connect(urlinsta).get();
                Elements followers = doc.select("#YouTubeUserTopInfoBlock > div:nth-child(3) > span:nth-child(3)");
                Elements engagement=doc.select("#YouTubeUserTopInfoBlock > div:nth-child(5) > span:nth-child(4)");
                Elements avglikes=doc.select("#YouTubeUserTopInfoBlock > div:nth-child(6) > span:nth-child(3)");
//               for(Element i:subscriber)
//               {
//                   subscriber_str=subscriber_str+i.text();
//               }
                followersstr=followers.text();
                engagementretestr=engagement.text();
                avglikesstr=avglikes.text();
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
            //testi.setText(followersstr+"gap"+engagementretestr+"gap"+avglikesstr);
            Map map=new HashMap();
            map.put("value1",instausernamestr);
            map.put("value2",followersstr);
            map.put("value3",engagementretestr);
            map.put("value4",avglikesstr);
            map.put("head","INSTAGRAM");
            map.put("field1","Username");
            map.put("field2","Followers");
            map.put("field3","Engagement Rate ");
            map.put("field4","Average Likes");
            map.put("img","https://firebasestorage.googleapis.com/v0/b/firstad-f90eb.appspot.com/o/instagram.png?alt=media&token=e66963a9-c258-49fa-8744-0652d4a60beb");
            db.updateChildren(map);
            Toast.makeText(instagram_username.this,"data uploaded",Toast.LENGTH_SHORT).show();
        }
    }
}
