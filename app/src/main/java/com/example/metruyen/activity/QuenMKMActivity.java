package com.example.metruyen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.metruyen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class QuenMKMActivity extends AppCompatActivity {
    private Button btnsend;
    private EditText edtemail11;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mkmactivity);
        btnsend=findViewById(R.id.btnsend);
        edtemail11=findViewById(R.id.edtemail11);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void sendEmail() {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = edtemail11.getText().toString().trim();
        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(QuenMKMActivity.this, "Lấy password thành công", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(QuenMKMActivity.this, SignIn.class);
                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(QuenMKMActivity.this, "Lấy password không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}