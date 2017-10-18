package com.technobrix.tbx.safedoors.Profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technobrix.tbx.safedoors.R;

import java.util.ArrayList;
import java.util.List;


public class DoctAdapter extends RecyclerView.Adapter<DoctAdapter.MyViewHolder> {

    Context context;
    //List<>list = new ArrayList();

    @Override
    public DoctAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.doc_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DoctAdapter.MyViewHolder holder, int position) {





    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
