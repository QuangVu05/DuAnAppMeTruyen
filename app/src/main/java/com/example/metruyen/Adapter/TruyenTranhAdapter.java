package com.example.metruyen.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.metruyen.R;

import com.example.metruyen.activity.TruyenTranh;
import com.example.metruyen.my_interface.IClickItemTruyenTranhList;

import java.util.ArrayList;
import java.util.List;

public class TruyenTranhAdapter extends RecyclerView.Adapter<TruyenTranhAdapter.TruyenTranhViewHolder> implements Filterable {
    private Context context;
    private List<TruyenTranh> truyenTranhs;
    private List<TruyenTranh> truyenTranhsOld;
    private IClickItemTruyenTranhList iClickItemTruyenTranhList;

    public TruyenTranhAdapter(Context context,List<TruyenTranh> truyenTranhs, IClickItemTruyenTranhList iClickItemTruyenTranhList) {
        this.context=context;
        this.truyenTranhs =  truyenTranhs;
        this.truyenTranhsOld =  truyenTranhs;
        this.iClickItemTruyenTranhList=iClickItemTruyenTranhList;
        notifyDataSetChanged();
    }



//    public void setData(List<TruyenTranh>  truyenTranhs,IClickItemTruyenTranhList iClickItemTruyenTranhList) {
//        this.truyenTranhs =  truyenTranhs;
//        this.truyenTranhsOld =  truyenTranhs;
//        this.iClickItemTruyenTranhList=iClickItemTruyenTranhList;
//       notifyDataSetChanged();
//
//    }

    @NonNull
    @Override
    public TruyenTranhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_truyen, parent, false);
        return new TruyenTranhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TruyenTranhViewHolder holder, int position) {
        final TruyenTranh truyenTranh = truyenTranhs.get(position);
        if (truyenTranh == null) {
            return;
        }else {
        // holder.imgTruyen.setImageResource(Integer.parseInt(truyenTranh.getIdAnh()));
        holder.txtTenTruyen.setText(truyenTranh.getName());
        holder.txtChap.setText(truyenTranh.getTacgia());
       Glide.with(holder.itemView.getContext()).load(truyenTranh.getIdAnh()).into(holder.imgTruyen);
        holder.Layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemTruyenTranhList.onClickTruyenTranh(truyenTranh);
            }
        });
        }
    }


    @Override
    public int getItemCount() {
        if (truyenTranhs != null) {
            return truyenTranhs.size();
        }
        return 0;
    }


    public class TruyenTranhViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgTruyen;
        private TextView txtChap, txtTenTruyen;
        private CardView Layout_item;


        public TruyenTranhViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTruyen = itemView.findViewById(R.id.imgTruyen);
            txtChap = itemView.findViewById(R.id.txtChap);
            txtTenTruyen = itemView.findViewById(R.id.txtTenTruyen);
           Layout_item = itemView.findViewById(R.id.Layout_item);

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    truyenTranhs = truyenTranhsOld;
                } else {
                    List<TruyenTranh> mlist = new ArrayList<>();
                    for (TruyenTranh truyenTranh : truyenTranhsOld) {
                        if (truyenTranh.getName().toLowerCase().contains(strSearch.toLowerCase())) {
                            mlist.add(truyenTranh);
                        }
                    }
                    truyenTranhs = mlist;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = truyenTranhs;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                truyenTranhs = (List<TruyenTranh>) results.values;
                notifyDataSetChanged();

            }
        };
    }

}