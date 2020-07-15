package com.example.firstad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class product_details extends AppCompatActivity {

    Button imageUpload;
    static final int ImageBack=1;
    private StorageReference Folder;
    EditText prodTitle;
    EditText prodDesc;
    EditText prodReq;
    EditText prodOpening;
    EditText prodAff;
    EditText prodcomp;
    EditText prodtime;
    String title;
    String desc;
    String req;
    String opnening;
    String aff;
    String comp;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        imageUpload=(Button)findViewById(R.id.uploadImg);
        prodTitle=findViewById(R.id.prodTitle);
        prodDesc=findViewById(R.id.prodDesc);
        prodReq=findViewById(R.id.prodReq);
        prodOpening=findViewById(R.id.prodOpening);
        prodAff=findViewById(R.id.prodAff);
        prodcomp=findViewById(R.id.prodcomp);
        prodtime=findViewById(R.id.prodtime);

        Folder= FirebaseStorage.getInstance().getReference().child("company_img");
        prodTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prodTitle.setBackgroundResource(R.drawable.smallyellow);
            }
        });
        imageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=prodTitle.getText().toString();
                desc=prodDesc.getText().toString();
                req=prodReq.getText().toString();
                opnening=prodOpening.getText().toString();
                aff=prodAff.getText().toString();
                time=prodtime.getText().toString();
                comp=prodcomp.getText().toString();
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,ImageBack);

            }
        });
    }
//    public void UploadData(View view)
//    {
//        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        startActivityForResult(intent,ImageBack);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ImageBack)
        {
            if(resultCode==RESULT_OK)
            {
                Uri ImageData =data.getData();
                final StorageReference Imagename =Folder.child("image"+ImageData.getLastPathSegment());
                Imagename.putFile(ImageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                DatabaseReference imagestore= FirebaseDatabase.getInstance().getReference().child("home");
                                HashMap<String, String> map=new HashMap<>();
                                map.put("img_url", String.valueOf(uri));
                                map.put("company_name", comp);
                                map.put("date", time);
                                map.put("description", desc);
                                map.put("title", title);


                                imagestore.push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(product_details.this,"UPLOADED",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });

                    }
                });
            }
        }
    }
}
