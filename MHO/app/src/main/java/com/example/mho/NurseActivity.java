package com.example.mho;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class NurseActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    TextView ward,ptname, p1;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse);

        p1 = findViewById(R.id.p1);
        ptname=findViewById(R.id.patientName);
        ward=findViewById(R.id.Room);

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NurseActivity.this,NurseUpload.class));
            }
        });
    }
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(NurseActivity.this, LoginActivity.class));
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

    public void refresh() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Patients").child("q0OaWHW974VBTMlb8d5Z3bBrOrF3");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String pname = dataSnapshot.child("name").getValue().toString();
                String room = dataSnapshot.child("roomno").getValue().toString();
                ptname.setText(pname);
                ward.setText(room);
                p1.setText(pname);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}
