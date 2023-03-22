package com.example.metruyen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.metruyen.R;
import com.example.metruyen.my_interface.ICickItemChuong;

import java.util.List;

public class ChuongAdapter extends RecyclerView.Adapter<ChuongAdapter.ChuongViewHolder>{
    private Context context;
    private List<Chuong> listChuong;
    //private List<TruyenTranh> truyenTranhsOld;
    private ICickItemChuong iCickItemChuong;

    public ChuongAdapter(Context context, List<Chuong> listChuong, ICickItemChuong iCickItemChuong) {
        this.context = context;
        this.listChuong = listChuong;
        this.iCickItemChuong = iCickItemChuong;
    }

    @NonNull
    @Override
    public ChuongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chuong, parent, false);
        return new ChuongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChuongViewHolder holder, int position) {
        final Chuong mchuong = listChuong.get(position);
        if (mchuong == null) {
            return;
        }else {
            // holder.imgTruyen.setImageResource(Integer.parseInt(truyenTranh.getIdAnh()));
            holder.txtchuong.setText(mchuong.getSochuong());
            holder.txttieude.setText(mchuong.getTieude());
            holder.txtdate.setText(mchuong.getDate());
            holder.Layout_itemchuong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iCickItemChuong.onClickChuong(mchuong);
               }
           });
        }

    }

    @Override
    public int getItemCount() {
        if (listChuong != null) {
            return listChuong.size();
        }
        return 0;
    }

    public class ChuongViewHolder extends RecyclerView.ViewHolder {

        private TextView txtchuong, txttieude,txtdate;
        private LinearLayout Layout_itemchuong;



        public ChuongViewHolder(@NonNull View itemView) {
            super(itemView);
            txttieude = itemView.findViewById(R.id.txttieude);
            txtchuong = itemView.findViewById(R.id.txtchuong);
            txtdate = itemView.findViewById(R.id.txtdate);
            Layout_itemchuong=itemView.findViewById(R.id.Layout_itemchuong);

        }
    }
}
