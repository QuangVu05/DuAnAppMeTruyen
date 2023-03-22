package com.example.metruyen.Fagment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.metruyen.R;
import com.example.metruyen.ViewPage.ViewPager2tutruyenAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tutruyen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tutruyen extends Fragment {
    private View view;

    private ViewPager2 viewPager2tutruyen;
    private ViewPager2tutruyenAdapter tutruyenAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TabLayout tableLayout;


    public tutruyen() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tutruyen.
     */
    // TODO: Rename and change types and number of parameters
    public static tutruyen newInstance(String param1, String param2) {
        tutruyen fragment = new tutruyen();
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
        view =inflater.inflate(R.layout.fragment_tutruyen, container, false);
        anhXa();

        viewPager2tutruyen.setAdapter(tutruyenAdapter);
        new TabLayoutMediator(tableLayout, viewPager2tutruyen, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position){
                    case 0:
                        tab.setText("Lịch sử");
                        break;
                    case 1:
                        tab.setText("Đánh dấu");
                        break;
                }

            }
        }).attach();
        return view;
    }

    private void anhXa() {

        viewPager2tutruyen=view.findViewById(R.id.viewPager2tutruyen);
        tutruyenAdapter=new ViewPager2tutruyenAdapter(this);
        tableLayout= view.findViewById(R.id.tabLayout);
    }


}