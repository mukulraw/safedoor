package com.example.tvs.safedoors.HelpDesk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvs.safedoors.R;

/**
 * Created by tvs on 8/26/2017.
 */

public class HelpDiskAdapeter extends RecyclerView.Adapter<HelpDiskAdapeter.MyViewHolder> {

    Context context;

    public HelpDiskAdapeter(Context context){

        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.helpdesk_list_model,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
