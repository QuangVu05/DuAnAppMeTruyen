package com.example.metruyen.Fagment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.metruyen.R;
import com.example.metruyen.activity.DoiMKActivity;
import com.example.metruyen.activity.HoSoActivity;
import com.example.metruyen.activity.SignIn;
import com.example.metruyen.activity.SignUp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link caidat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class caidat extends Fragment {
    private View view;
    private Button btnDangNhap,btnDangXuat,btnDangKy,btnHoSo;
    private ImageView profile_image;
    private TextView txtten12,txtemail12;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public caidat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment caidat.
     */
    // TODO: Rename and change types and number of parameters
    public static caidat newInstance(String param1, String param2) {
        caidat fragment = new caidat();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =inflater.inflate(R.layout.fragment_caidat, container, false);
        init();
        showUserInformation();


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), DoiMKActivity.class);
                startActivity(intent);
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), SignUp.class);
                startActivity(intent);

            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getContext(), SignIn.class);
                startActivity(intent);
            }
        });
        btnHoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), HoSoActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    private void init() {
        txtten12=view.findViewById(R.id.txtten12);
        profile_image =view.findViewById(R.id.profile_image);
        txtemail12=view.findViewById(R.id.txtemail12);
        btnDangNhap=view.findViewById(R.id.btnDangNhap);
        btnDangKy=view.findViewById(R.id.btnDangKy);
        btnDangXuat=view.findViewById(R.id.btnDangXuat);
        btnHoSo=view.findViewById(R.id.btnHoSo);

    }
    public void showUserInformation(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            if(name==null){
               txtten12.setVisibility(View.GONE);
            }else {
                txtten12.setVisibility(View.VISIBLE);
                txtten12.setText(name);

            }
            txtemail12.setText(email);
            Glide.with(getActivity()).load(photoUrl).error(R.drawable.ic_baseline_person_24).into(profile_image);

        }
    }



}