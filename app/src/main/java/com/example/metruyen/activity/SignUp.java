package com.example.metruyen.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.metruyen.MainActivity;
import com.example.metruyen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
   private Button btnsingup;
   private EditText edtemail, edtpass, edtconpass;
   private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        anhXa();
        initListener();

    }

    private void anhXa() {
        btnsingup = findViewById(R.id.btnsingup);
        edtemail = findViewById(R.id.edtemail);
        edtconpass = findViewById(R.id.conpass);
        edtpass = findViewById(R.id.edtpass);
        progressDialog=new ProgressDialog(this);
    }

    private void initListener() {
        btnsingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSingUp();
            }
        });
    }

    private void onClickSingUp() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        progressDialog.show();
        String memail = (String) edtemail.getText().toString().trim();
        String mpass = (String) edtpass.getText().toString().trim();
        String conpass=(String) edtconpass.getText().toString().trim();

        if (memail.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
        }
        if (mpass.isEmpty() || mpass.equals(conpass) == false) {
            Toast.makeText(this, "Vui lòng nhập password", Toast.LENGTH_SHORT).show();
            edtpass.getText().clear();
            edtconpass.getText().clear();

        }
        else{


            mAuth.createUserWithEmailAndPassword(memail, mpass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(SignUp.this, MainActivity.class);
                                startActivity(intent);
                                finishAffinity();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUp.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
          }
        }
    }
