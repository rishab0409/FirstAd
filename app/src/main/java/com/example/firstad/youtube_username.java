package com.example.firstad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

public class youtube_username extends AppCompatActivity {
    String subscriber_str="";
    EditText username;
    Button btn;
    String user;
    String url="https://socialblade.com/youtube/user/";
    TextView test;
    String views_str="";
    String monthlyviews_str="";
    FirebaseUser currentFirebaseUser;
    DatabaseReference db;

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_username);
        username=(EditText)findViewById(R.id.usery);
        test=(TextView)findViewById(R.id.test);
        btn=(Button)findViewById(R.id.userbtny);

      //  uid=currentFirebaseUser.getUid();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db= FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("youtube");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user=username.getText().toString();
                url=url+user;
                new doit().execute();
            }
        });


    }
    public class doit extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
               Document doc=Jsoup.connect(url).get();
                Elements subscriber = doc.select("#YouTubeUserTopInfoBlock > div:nth-child(3) > span:nth-child(3)");
                Elements views=doc.select("#YouTubeUserTopInfoBlock > div:nth-child(4) > span:nth-child(3)");
                Elements monthlyviews=doc.select("#socialblade-user-content > div:nth-child(3) > div:nth-child(3) > p:nth-child(1)");
//               for(Element i:subscriber)
//               {
//                   subscriber_str=subscriber_str+i.text();
//               }
                subscriber_str=subscriber.text();
              views_str=views.text();
              monthlyviews_str=monthlyviews.text();
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
           // test.setText(subscriber_str+"gap"+views_str+"gap"+monthlyviews_str);
            Map map=new HashMap();
            map.put("value1",user);
            map.put("value2",subscriber_str);
            map.put("value3",views_str);
            map.put("value4",monthlyviews_str);
            map.put("head","YOUTUBE");
            map.put("field1","Username");
            map.put("field2","Subscribers");
            map.put("field3","Total Views");
            map.put("field4","Monthly Views");
            map.put("img","https://firebasestorage.googleapis.com/v0/b/firstad-f90eb.appspot.com/o/youtube.png?alt=media&token=2a600b48-5c0f-44e9-808c-722e3a43d143");


            db.updateChildren(map);

        }
    }
}
