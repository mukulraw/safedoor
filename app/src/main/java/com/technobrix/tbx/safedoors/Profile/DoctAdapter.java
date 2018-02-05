package com.technobrix.tbx.safedoors.Profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AddFamilyPOJO.AddFamilyBean;
import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.DocumentListPOJO.DocumentList;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class DoctAdapter extends RecyclerView.Adapter<DoctAdapter.MyViewHolder> {

    Context context;
    List<DocumentList>list = new ArrayList();

    public DoctAdapter(Context context ,  List<DocumentList>list){

        this.context = context;
        this.list = list;
    }


    @Override
    public DoctAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.doc_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DoctAdapter.MyViewHolder holder, int position) {

        final DocumentList item = list.get(position);

        holder.aadhar.setText(item.getTitle());

        holder.number.setText(item.getDocumentNo());


        holder.cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                bean b = (bean)context.getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                Call<AddFamilyBean> call = cr.deleteDoc(b.house_id , item.getId() , b.socity);

                call.enqueue(new Callback<AddFamilyBean>() {
                    @Override
                    public void onResponse(Call<AddFamilyBean> call, Response<AddFamilyBean> response) {


                        Toast.makeText(context , response.body().getMessage() , Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<AddFamilyBean> call, Throwable t) {

                    }
                });




            }
        });



    }
    public void set( List<DocumentList>list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView aadhar , number;
        ImageButton cross;


        public MyViewHolder(View itemView) {
            super(itemView);

            aadhar = (TextView)itemView.findViewById(R.id.title);
            number = (TextView)itemView.findViewById(R.id.number);
            cross = (ImageButton)itemView.findViewById(R.id.delete);

        }
    }
}
