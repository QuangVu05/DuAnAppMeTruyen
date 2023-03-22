package com.example.metruyen.activity;

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
import com.google.firebase.auth.FirebaseUser;

public class DoiMKActivity extends AppCompatActivity {
    private EditText edtdmk,compassdmk;
    private Button btndmk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mkactivity);
        edtdmk=findViewById(R.id.edtdmk);
        compassdmk=findViewById(R.id.conpassdmk);
        btndmk=findViewById(R.id.btndmk);

        btndmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doiMK();
            }
        });

    }

    private void doiMK() {
        String mpass =  edtdmk.getText().toString().trim();
        String mconpass= compassdmk.getText().toString().trim();
        if (mpass.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập password mới", Toast.LENGTH_SHORT).show();
        }
        if (mpass.isEmpty() || mpass.equals(mconpass) == false) {
            Toast.makeText(this, "Vui lòng nhập lại password", Toast.LENGTH_SHORT).show();
            edtdmk.getText().clear();
            compassdmk.getText().clear();


        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updatePassword(mpass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DoiMKActivity.this, "Đổi password thành công", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(DoiMKActivity.this, "Đổi password không thành công", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

}