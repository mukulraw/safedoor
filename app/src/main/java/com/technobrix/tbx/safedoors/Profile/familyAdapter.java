package com.technobrix.tbx.safedoors.Profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.FacilityPOJO.FacilityList;

import com.technobrix.tbx.safedoors.GetFamilyPOJO.FamiltList;
import com.technobrix.tbx.safedoors.R;

import java.util.ArrayList;
import java.util.List;


public class familyAdapter extends RecyclerView.Adapter<familyAdapter.myviewholder>{

    Context context;
    List<FamiltList> list = new ArrayList<>();

    public familyAdapter(Context context , List<FamiltList> list){

        this.context = context;
        this.list =  list;
    }
    @Override
    public familyAdapter.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.family_list_model , parent , false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(familyAdapter.myviewholder holder, int position) {


        FamiltList item = list.get(position);
        holder.name.setText(item.getName());
        holder.age.setText(item.getAge());
        holder.male.setText(item.getGender());
        holder.son.setText(item.getRelation());


    }

    public void Setgriddata (List<FamiltList>list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        TextView name , age , male , son;


        public myviewholder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            age = (TextView)itemView.findViewById(R.id.age);
            male = (TextView)itemView.findViewById(R.id.gender);
            son = (TextView)itemView.findViewById(R.id.relation);



        }
    }
}
