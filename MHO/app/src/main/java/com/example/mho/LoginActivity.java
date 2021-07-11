package com.example.mho;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText name, password;
    Button login, userreg;
    TextView forgotpass;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    Spinner spinner;
    DatabaseReference databaseReference, databaseReference1, databaseReference2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitity_login);

        userreg = findViewById(R.id.Register);
        name = findViewById(R.id.etuid);
        password = findViewById(R.id.etpass);
        login = findViewById(R.id.Login);
        forgotpass = findViewById(R.id.forgotpassword);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Patients");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Doctors");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Nurses");

        spinner = findViewById(R.id.spinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.usernme, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = name.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String item = spinner.getSelectedItem().toString();
                if(item.equals("Doctor")) {
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.show();

                    firebaseAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        startActivity(new Intent(LoginActivity.this, DoctorActivity.class));
                                        Toast.makeText(LoginActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else if(item.equals("Patient")) {
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.show();

                    firebaseAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        startActivity(new Intent(LoginActivity.this, PatientActivity.class));
                                        Toast.makeText(LoginActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.show();

                    firebaseAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        startActivity(new Intent(LoginActivity.this, NurseActivity.class));
                                        Toast.makeText(LoginActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        userreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotActivity.class));
            }
        });
    }
}
