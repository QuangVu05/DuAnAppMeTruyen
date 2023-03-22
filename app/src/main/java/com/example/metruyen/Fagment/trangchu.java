package com.example.metruyen.Fagment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.metruyen.Adapter.TruyenTranhAdapter;
import com.example.metruyen.R;
import com.example.metruyen.activity.ManHinhDocTruyenActivity;
import com.example.metruyen.activity.TruyenTranh;
import com.example.metruyen.my_interface.IClickItemTruyenTranhList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link trangchu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class trangchu extends Fragment {
    private RecyclerView rcv_Truyen;
    private TruyenTranhAdapter adapter;
    private List<TruyenTranh> listTruyen;
    private View view;
    private Context context;
    private SearchView searchView;
    private Toolbar toolbar;
    private GridLayoutManager gridLayoutManager;
    private IClickItemTruyenTranhList iClickItemTruyenTranhList;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public trangchu() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment trangchu.
     */
    // TODO: Rename and change types and number of parameters
    public static trangchu newInstance(String param1, String param2) {
        trangchu fragment = new trangchu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // context = getActivity();
        setHasOptionsMenu(true);
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
        view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        anhXa();
        setUp();
        getListtruyenDatabase();
        return view;
    }


    private List<TruyenTranh> getList() {
        //listTruyen.add(new TruyenTranh("THÔN PHỆ TINH KHÔNG","https://cdn.truyenfull.com/medias/covers/0/177-thon-phe-tinh-khong_cover_large.jpg",1));
       // listTruyen.add(new TruyenTranh("THÔN PHỆ TINH KHÔNG","https://cdn.truyenfull.com/medias/covers/0/177-thon-phe-tinh-khong_cover_large.jpg",1));
        //listTruyen.add(new TruyenTranh("THÔN PHỆ TINH KHÔNG","https://cdn.truyenfull.com/medias/covers/0/177-thon-phe-tinh-khong_cover_large.jpg",1));


        return listTruyen;

    }

    private void setUp() {
        listTruyen = new ArrayList<>();
        adapter = new TruyenTranhAdapter(getContext(), listTruyen, new IClickItemTruyenTranhList() {
            @Override
            public void onClickTruyenTranh(TruyenTranh truyenTranh) {
                onClickGoTo(truyenTranh);

            }
        });
        rcv_Truyen.setAdapter(adapter);


    }

    private void anhXa() {
        rcv_Truyen = view.findViewById(R.id.rcv_Truyen);
        rcv_Truyen.setLayoutManager(new LinearLayoutManager(getActivity()));
        gridLayoutManager = new GridLayoutManager(getContext(), 3);
        toolbar = view.findViewById(R.id.toolbar);
        rcv_Truyen.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_seach, menu);
        searchView = (SearchView) menu.findItem(R.id.timkiem).getActionView();
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                adapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        super.onCreateOptionsMenu(menu, inflater);

    }

    private void onClickGoTo(TruyenTranh truyenTranh) {
        Intent intent = new Intent(getActivity(), ManHinhDocTruyenActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_truyen",truyenTranh);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void getListtruyenDatabase(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("listtruyen").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listTruyen.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    TruyenTranh mtruyenTranh=postSnapshot.getValue(TruyenTranh.class);
                    listTruyen.add(mtruyenTranh);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Lấy dữ liệu không thành công", Toast.LENGTH_SHORT).show();

            }
        });
    }

//    public static trangchu getInstance(TruyenTranh truyenTranh){
//        trangchu mtrangchu=new trangchu();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("object_truyen",truyenTranh);
//        mtrangchu.setArguments(bundle);
//        return mtrangchu;
//
//    }
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        iClickItemTruyenTranhList = (IClickItemTruyenTranhList) context;
//    }




}