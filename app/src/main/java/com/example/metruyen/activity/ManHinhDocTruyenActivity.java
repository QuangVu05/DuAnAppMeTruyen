package com.example.metruyen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.metruyen.Fagment.GioiThieuFragment;
import com.example.metruyen.Fagment.trangchu;
import com.example.metruyen.R;
import com.example.metruyen.ViewPage.ViewPager2DocChuyen;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ManHinhDocTruyenActivity extends AppCompatActivity  {
    private ViewPager2 mviewPager2dochuyen;
    private ViewPager2DocChuyen viewPager2DocChuyen;
    private TabLayout mtableLayout;
    private ImageView imageAnhTT;
    private TextView txtTen,txttrangthai,txttacgia,txtTheloai;
    private ImageButton btn_back;
    private String gioithieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_doc_truyen);
        anhXa();
        viewPager2DocChuyen = new ViewPager2DocChuyen(this);
        mviewPager2dochuyen.setAdapter(viewPager2DocChuyen);
        setViewPager();
        nhanDuLieu();
        setBtn_back();
    }

    private void setViewPager() {
        new TabLayoutMediator(mtableLayout, mviewPager2dochuyen, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position){
                    case 0:
                        tab.setText("Giới thiệu");
                        break;
                    case 1:
                        tab.setText("Chương");
                        break;
                }

            }
        }).attach();
    }


    private void anhXa(){
        mviewPager2dochuyen = findViewById(R.id.mviewPager2dochuyen);
         mtableLayout=findViewById(R.id.mtabLayout);
         imageAnhTT=findViewById(R.id.imageAnhTT);
         txtTen=findViewById(R.id.txtTen);
         txttacgia=findViewById(R.id.txttacgia);
         txttrangthai=findViewById(R.id.txttrangthai);
         txtTheloai=findViewById(R.id.txtTheloai);
         btn_back=findViewById(R.id.btn_back);


    }
   private void nhanDuLieu(){
      TruyenTranh truyenTranh= (TruyenTranh) getIntent().getExtras().get("object_truyen");
       Glide.with(this).load(truyenTranh.getIdAnh()).into(imageAnhTT);
      txtTen.setText(truyenTranh.getName());
      txttacgia.setText(truyenTranh.getTacgia());
      txttrangthai.setText(truyenTranh.getTrangthai());
      txtTheloai.setText(truyenTranh.getTheloai());
      gioithieu=truyenTranh.getGioithieu();

   }
//   private void guidulieu(){
//       Bundle b= new Bundle();
//       String t=txttacgia.getText().toString();
//       b.putString("key_11", t);
//
//// Khởi tạo Fragment và set Bundle vào Fragment
//       GioiThieuFragment gioiThieuFragment = new GioiThieuFragment();
//       gioiThieuFragment.setArguments(b);
//   }
   private void setBtn_back(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ManHinhDocTruyenActivity.this, trangchu.class);
                startActivity(intent);
            }
        });
   }





}