package com.gsr.dummy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button mSubmit;
    private Button mFetch;
    private EditText mQuestion;
    private EditText mAnswer;

    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirestore=FirebaseFirestore.getInstance();

        mSubmit=findViewById(R.id.Submit);
        mFetch=findViewById(R.id.Fetch);

        mQuestion=findViewById(R.id.Question);
        mAnswer=findViewById(R.id.Answer);

        mFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Question=mQuestion.getText().toString();
                String Answer=mAnswer.getText().toString();

                Map<String,String> userMap=new HashMap<>();

                userMap.put("query",Question);
                userMap.put("answer",Answer);


                mFirestore.collection("Questions").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this,"Query Added to Firestore",Toast.LENGTH_LONG).show();
                        mQuestion.setText("");
                        mAnswer.setText("");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error=e.getMessage();
                        Toast.makeText(MainActivity.this,"Error: "+error,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
