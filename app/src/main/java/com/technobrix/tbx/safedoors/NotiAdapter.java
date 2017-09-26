package com.technobrix.tbx.safedoors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.GetNotificationPOJO.NotificationList;

import java.util.ArrayList;
import java.util.List;


public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.MyViewHolder> {

    Context context;
    List<NotificationList> list = new ArrayList<>();


    public NotiAdapter(Context context , List<NotificationList> list){

        this.context = context;
        this.list = list;
    }
    @Override
    public NotiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notify_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotiAdapter.MyViewHolder holder, int position) {

        NotificationList noti = list.get(position);
        holder.name.setText(noti.getMeetingPurpose());

    }

    public void Setgrid(List<NotificationList>list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
        }
    }
}
