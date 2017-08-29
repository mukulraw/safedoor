package com.example.tvs.safedoors.Profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvs.safedoors.R;

/**
 * Created by TBX on 8/29/2017.
 */

public class familyAdapter extends RecyclerView.Adapter<familyAdapter.myviewholder>{

    Context context;

    public familyAdapter(Context context){
        this.context = context;
    }
    @Override
    public familyAdapter.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.family_list_model , parent , false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(familyAdapter.myviewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        public myviewholder(View itemView) {
            super(itemView);
        }
    }
}
