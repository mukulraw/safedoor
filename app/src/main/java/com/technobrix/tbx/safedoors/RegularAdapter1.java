package com.technobrix.tbx.safedoors;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.visitorListPOJO.VisitorList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by USER on 18-01-2018.
 */

public class RegularAdapter1 extends RecyclerView.Adapter<RegularAdapter1.MyViewHolder> {

    List<VisitorList> list = new ArrayList<>();
    Context context;


    public RegularAdapter1(Context context){

        this.context = context;
        this.list = list;
    }
    public void setGridData(List<VisitorList> list) {
        this.list = list;
        notifyDataSetChanged();
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.regular1_list_model , parent , false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RegularAdapter1.MyViewHolder holder, int position) {


        final VisitorList item = list.get(position);

        holder.time.setText(item.getIntime());
        holder.visitor.setText(item.getStaffName());

        holder.house.setVisibility(View.GONE);

        if (Objects.equals(item.getStatus(), "0")) {
            holder.out.setBackground(context.getResources().getDrawable(R.drawable.green_circle));
        } else if (Objects.equals(item.getStatus(), "1")) {
            holder.out.setBackground(context.getResources().getDrawable(R.drawable.red_circle));
        }

        holder.comment.setText(item.getComment());

        holder.out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                /*if (Objects.equals(item.getStatus(), "0"))
                {
                    progress.setVisibility(View.VISIBLE);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://safedoors.in")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    bean b = (bean) context.getApplicationContext();

                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                    Call<String> visitorOut = cr.out(b.socity, item.getVisitorId());

                    visitorOut.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            loadData();

                            Toast.makeText(context,"Visitor is out", Toast.LENGTH_SHORT).show();

                            progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            progress.setVisibility(View.GONE);
                        }
                    });
                }
*/



            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView time, visitor, house , comment;
        ImageButton out;


        public MyViewHolder(View itemView) {
            super(itemView);

            time = (TextView) itemView.findViewById(R.id.time);
            visitor = (TextView) itemView.findViewById(R.id.visitor);
            house = (TextView) itemView.findViewById(R.id.house);
            comment = (TextView) itemView.findViewById(R.id.comment);
            out = (ImageButton) itemView.findViewById(R.id.out);

        }
    }
}
