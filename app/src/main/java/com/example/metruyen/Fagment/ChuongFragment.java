package com.example.metruyen.Fagment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.metruyen.Adapter.Chuong;
import com.example.metruyen.Adapter.ChuongAdapter;
import com.example.metruyen.R;
import com.example.metruyen.activity.NoidungActivity2;
import com.example.metruyen.my_interface.ICickItemChuong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChuongFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChuongFragment extends Fragment {
    private RecyclerView rcv_Chuong;
    private ChuongAdapter chuongAdapter;
    private List<Chuong> chuongList;
    private View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChuongFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChuongFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChuongFragment newInstance(String param1, String param2) {
        ChuongFragment fragment = new ChuongFragment();
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
         view=inflater.inflate(R.layout.fragment_chuong, container, false);
         anhxa();
        getListChuongDatabase();
        return view;
    }
//    private List<Chuong> getList() {
//
//        chuongList.add(new Chuong("Chuong 1","Lâm An","21/3/2023"));
//        chuongList.add(new Chuong("Chuong 2","Lâm An","21/3/2023"));
//        chuongList.add(new Chuong("Chuong 3","Lâm An","21/3/2023"));
//        chuongList.add(new Chuong("Chuong 3","Lâm An","21/3/2023"));
//
//        return chuongList;
//
//
//    }

    private void anhxa() {
        rcv_Chuong = view.findViewById(R.id.rcv_Chuong);
        chuongList= new ArrayList<>();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rcv_Chuong.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        rcv_Chuong.addItemDecoration(dividerItemDecoration);

        chuongAdapter=new ChuongAdapter(getContext(),chuongList, new ICickItemChuong() {
            @Override
            public void onClickChuong(Chuong chuong) {
                onClickGoTo(chuong);

            }
        });
        rcv_Chuong.setAdapter(chuongAdapter);

    }
    private void onClickGoTo(Chuong mchuong) {
        //String chuong=mchuong.getChuong();
         //String tieude=mchuong.getTieude();
        //tring date=mchuong.getDate();
        //Chuong chuong1=new Chuong(chuong,tieude,date);
        Intent intent = new Intent(getActivity(), NoidungActivity2.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_Chuong",  mchuong);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void getListChuongDatabase(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("listChuonng").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chuongList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Chuong chuong=postSnapshot.getValue(Chuong.class);
                    chuongList.add(chuong);

                }
                chuongAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Lấy dữ liệu không thành công", Toast.LENGTH_SHORT).show();

            }
        });
    }
}