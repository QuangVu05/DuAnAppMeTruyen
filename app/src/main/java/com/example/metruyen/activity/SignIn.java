package com.example.metruyen.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.metruyen.MainActivity;
import com.example.metruyen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
   private TextView txquen,txup;
   private EditText memail,mpass;
   private Button btnSingin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initUi();
        txup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });
        btnSingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSingIn();
            }
        });
        txquen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignIn.this, QuenMKMActivity.class);
                startActivity(intent);

            }
        });

    }

    private void onClickSingIn() {
        String email=memail.getText().toString().trim();
        String password=mpass.getText().toString().trim();
        if(email.isEmpty()){
            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập password", Toast.LENGTH_SHORT).show();
        }
        else {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(SignIn.this, MainActivity.class);
                                startActivity(intent);
                                finishAffinity();

                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(SignIn.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }

    private void initUi() {
        progressDialog=new ProgressDialog(this);
        txquen=findViewById(R.id.txquen);
        txup=findViewById(R.id.txup);
        memail=findViewById(R.id.email);
        mpass=findViewById(R.id.pass);
        btnSingin=findViewById(R.id.btnSingin);

    }


}