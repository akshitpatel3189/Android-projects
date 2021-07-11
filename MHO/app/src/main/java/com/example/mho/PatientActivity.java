package com.example.mho;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientActivity extends AppCompatActivity {

    Button download;
    TextView ptname,ward,mobile,drname;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference,reference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        ptname=findViewById(R.id.patientname);
        ward=findViewById(R.id.room);
        mobile=findViewById(R.id.mobileno);
        drname=findViewById(R.id.adrname);

        download = findViewById(R.id.btndownload);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientActivity.this, Pdflist.class));
            }
        });

    }
    public void logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(PatientActivity.this, LoginActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.logoutMenu:{
                logout();
            }
            case R.id.rereshMenu:{
                refresh();
            }
        }
        return true;
    }

    public void refresh() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Patients").child("q0OaWHW974VBTMlb8d5Z3bBrOrF3");
        reference = FirebaseDatabase.getInstance().getReference().child("Doctors");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String pname = dataSnapshot.child("name").getValue().toString();
                String room = dataSnapshot.child("roomno").getValue().toString();
                String mob = dataSnapshot.child("mobileno").getValue().toString();
                ptname.setText(pname);
                ward.setText(room);
                mobile.setText(mob);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String dname = dataSnapshot.child("Name").getValue().toString();
                drname.setText(dname);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
