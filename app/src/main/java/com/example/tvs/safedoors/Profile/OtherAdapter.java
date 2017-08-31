package com.example.tvs.safedoors.Profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvs.safedoors.R;


public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.myviewholder> {

    Context context;

    public OtherAdapter(Context context){
        this.context = context;
    }
    @Override
    public OtherAdapter.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.other_list_model , parent , false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(OtherAdapter.myviewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public  class myviewholder extends RecyclerView.ViewHolder {
        public myviewholder(View itemView) {
            super(itemView);
        }
    }
}
