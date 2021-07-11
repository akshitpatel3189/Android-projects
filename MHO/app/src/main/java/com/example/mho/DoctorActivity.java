package com.example.mho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import com.google.firebase.storage.StorageReference;

public class DoctorActivity extends AppCompatActivity {

    private Button download;
    private StorageReference storageRef;
    private FirebaseStorage storage;
    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference,reference;
    TextView ptname,mobile,drname, pickdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        download = findViewById(R.id.downloadbtn);
        pickdate = findViewById(R.id.pick_a_date);
        storageRef = FirebaseStorage.getInstance().getReference();
        ptname=findViewById(R.id.ptname);
        mobile=findViewById(R.id.pmobileno);
        drname=findViewById(R.id.Doctorname);


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorActivity.this, Pdflist.class));
            }
        });

        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorActivity.this,NotificationActivity.class));
            }
        });
    }


    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(DoctorActivity.this, LoginActivity.class));
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
                Logout();
            }
            case R.id.rereshMenu:{
                refresh();
            }
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {}

    public void refresh() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Patients").child("q0OaWHW974VBTMlb8d5Z3bBrOrF3");
        reference = FirebaseDatabase.getInstance().getReference().child("Doctors");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String dname = dataSnapshot.child("Name").getValue().toString();
                drname.setText(dname);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String pname = dataSnapshot.child("name").getValue().toString();
                String mob = dataSnapshot.child("mobileno").getValue().toString();
                ptname.setText(pname);
                mobile.setText(mob);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}