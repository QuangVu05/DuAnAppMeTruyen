package com.example.metruyen.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;

import com.bumptech.glide.Glide;
import com.example.metruyen.Fagment.caidat;
import com.example.metruyen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.IOException;
import java.io.InputStream;

public class HoSoActivity extends AppCompatActivity {
    public static final int MY_REQUEST_CODE=10;
    private EditText edtname,edtemail1;
    private Button btnUpdate;
    private ImageView image_avatar;
    private Uri uri;
    private caidat mcaidat;

    final private ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if(result.getResultCode()==RESULT_OK){
                        Intent intent=result.getData();
                        if(intent==null){
                            return;
                        }
                        uri=intent.getData();
                        setUri(uri);
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(uri);
                            ExifInterface exif = new ExifInterface(inputStream);
                           int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
                           Matrix matrix = new Matrix();
                            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                                matrix.postRotate(90);
                            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                                matrix.postRotate(180);
                            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                                matrix.postRotate(270);
                            }
                            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            setBitMap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ho_so);
        mcaidat= new caidat();
        anhxa();
        setUser();
        initListener();
    }

    private void initListener() {
        image_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickanh();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpdate();

            }
        });
    }



    private void onClickanh() {
//        if(caidat==null){
//            return;
//        }
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
           openGallery();
            return;
        }
        if(this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            openGallery();
        }
        else {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            this.requestPermissions(permissions,MY_REQUEST_CODE);

        }

    }

    private void setUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
        edtname.setText(user.getDisplayName());
        edtemail1.setText(user.getEmail());
        Glide.with(this).load(user.getPhotoUrl()).error(R.drawable.ic_baseline_person_24).into(image_avatar);

    }

    private void anhxa() {
        edtname=findViewById(R.id.edtname);
        edtemail1=findViewById(R.id.edtemail1);
        btnUpdate=findViewById(R.id.btnUpdate);
        image_avatar=findViewById(R.id.image_avatar);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==MY_REQUEST_CODE){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
            else {
                Toast.makeText(this, "Vui lòng cho phép truy cập", Toast.LENGTH_SHORT).show();
                openGallery();
            }
        }
    }
    public void openGallery(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
    }
    public  void setBitMap(Bitmap bitmap){
        image_avatar.setImageBitmap(bitmap);
    }
    private void onClickUpdate() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
        String strfullName=edtname.getText().toString().trim();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(strfullName)
                .setPhotoUri(uri)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {if (task.isSuccessful()) {
                        Toast.makeText(HoSoActivity.this, "Update thành công", Toast.LENGTH_SHORT).show();
                        mcaidat.showUserInformation();

                    }
                    else {
                        Toast.makeText(HoSoActivity.this, "Update không thành công", Toast.LENGTH_SHORT).show();
                    }
                    }



                });

    }




    public void setUri(Uri uri) {
        this.uri = uri;
    }
}