package com.technobrix.tbx.safedoors.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.FacilityPOJO.FacilityList;

import com.technobrix.tbx.safedoors.GetFamilyPOJO.FamiltList;
import com.technobrix.tbx.safedoors.GetVehiclePOJO.VehicleBean;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.RemovePOJO.RemoveBean;
import com.technobrix.tbx.safedoors.bean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


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


        final FamiltList item = list.get(position);
        holder.name.setText(item.getFullname());
        holder.age.setText(item.getAge());
        holder.male.setText(item.getGender());
        holder.son.setText(item.getRelation());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  /*  Intent i = new Intent(context , ViewFamilyInfo.class);
                    context.startActivity(i);
*/

                Intent i = new Intent(context, ViewFamilyInfo.class);
                Bundle b = new Bundle();
                b.putString("name", item.getName());
                b.putString("username", item.getFullname());
                b.putString("age", item.getAge());
                b.putString("male", item.getGender());
                b.putString("son", item.getRelation());
                b.putString("id", item.getId());
                i.putExtras(b);
                context. startActivity(i);

            }
        });

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

       // ImageButton remove;


        public myviewholder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            age = (TextView)itemView.findViewById(R.id.age);
            male = (TextView)itemView.findViewById(R.id.gender);
            son = (TextView)itemView.findViewById(R.id.relation);
           // remove = (ImageButton) itemView.findViewById(R.id.close);





        }
    }
}
