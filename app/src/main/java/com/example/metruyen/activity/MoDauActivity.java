package com.example.metruyen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metruyen.MainActivity;
import com.example.metruyen.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MoDauActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mo_dau);
        Handler hendeler =new Handler();
        hendeler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();

            }
        },2000);
    }

    private void nextActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            Intent intent=new Intent(MoDauActivity.this, SignIn.class);
            startActivity(intent);
        }
        else {
            Intent intent=new Intent(MoDauActivity.this, MainActivity.class);
            startActivity(intent);

        }
        finish();

    }
}