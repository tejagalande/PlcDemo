package com.example.plcdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView tvKey1, tvKey2, tvKey3, tvValue1, tvValue2, tvValue3;
    Button btnGetData;
    private static final String FIREBASE_DATABASE_URL = "https://plc-test-89784-default-rtdb.firebaseio.com/";
    Firebase mFirebase;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvKey1 = findViewById(R.id.tvKey1);
        tvKey2 = findViewById(R.id.tvKey2);
        tvKey3 = findViewById(R.id.tvKey3);

        tvValue1 = findViewById(R.id.tvValue1);
        tvValue2 = findViewById(R.id.tvValue2);
        tvValue3 = findViewById(R.id.tvValue3);

        btnGetData = findViewById(R.id.btnGet);
        Firebase.setAndroidContext(MainActivity.this);
        mFirebase = new Firebase(FIREBASE_DATABASE_URL);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("PLC");


        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Object value = snapshot.getValue();
                        Log.i("Value",value.toString());

                        String child = snapshot.child("TEST").getValue().toString();
                        Log.i("Child",child);


                        for (DataSnapshot d : snapshot.getChildren()){
                            Object data = d.getChildrenCount();
                            Log.i("DataSnapShot",data.toString());

                        }
                        for (int i = 0; i < snapshot.getChildrenCount(); i++){

                            Object data = snapshot.getChildren();
                            Log.i("Arraylist item",data.toString());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}