package com.example.mho;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {

    private EditText email;
    private Button sendmail;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        email = findViewById(R.id.etxtemail);
        sendmail = findViewById(R.id.btnsndmail);
        firebaseAuth = FirebaseAuth.getInstance();

        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrmail = email.getText().toString();

                if(TextUtils.isEmpty(usrmail)){
                    Toast.makeText(ForgotActivity.this, "Enter valid Email", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.sendPasswordResetEmail(usrmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotActivity.this, "Please Check Email", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotActivity.this,LoginActivity.class));
                            }
                            else {
                                String msg = task.getException().getMessage();
                                Toast.makeText(ForgotActivity.this, "ERROR, " +msg, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
