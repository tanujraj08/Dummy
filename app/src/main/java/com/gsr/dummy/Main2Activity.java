package com.gsr.dummy;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "FireLog";
    private RecyclerView mMainList;
    private Button mSms;
    private Button mWhatsapp;
    private FirebaseFirestore mFirestore;
    private QuestionListAdapter questionListAdapter;

    private List<Questions> questionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        questionList=new ArrayList<>();
        questionListAdapter=new QuestionListAdapter(questionList);

        mMainList=findViewById(R.id.main_list);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setHasFixedSize(true);
        mMainList.setAdapter(questionListAdapter);

        mSms=findViewById(R.id.sms);
        mWhatsapp=findViewById(R.id.whatsapp);

        mSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Main2Activity.this, Twiliosms.class);
                intent.putExtra(SyncStateContract.Constants.QUERY,query)
                startActivity(intent);
            }
        });

        mFirestore=FirebaseFirestore.getInstance();
        mFirestore.collection("Questions").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if(e !=null){
                    Log.d(TAG,"Error : " +e.getMessage());
                }

                for (DocumentChange doc :documentSnapshots.getDocumentChanges()){

                    if(doc.getType()==DocumentChange.Type.ADDED){

                        Questions questions=doc.getDocument().toObject(Questions.class);
                        questionList.add(questions);

                        questionListAdapter.notifyDataSetChanged();
                    }

                }
            }
        });
    }
}
