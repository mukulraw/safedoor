package com.technobrix.tbx.safedoors.Event;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.EventDatePOJO.MeetingList;
import com.technobrix.tbx.safedoors.MeetingArragemenmt.Meeting;
import com.technobrix.tbx.safedoors.R;

import java.util.ArrayList;
import java.util.List;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {


    Context context;

    List<MeetingList> list = new ArrayList<>();

    public EventAdapter(Context context ,  List<MeetingList> list){

        this.context = context;

        this .list = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.event_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        MeetingList item = list.get(position);

        String daa = item.getMeetingDate();
        String[] d1 = daa.split("-");

        holder.d.setText(d1[2]);
       holder. m.setText(d1[1] + " " + d1[0]);

       holder. title.setText(item.getTitle());
       holder.desc.setText(item.getDescription());


    }

    public void Setgrid(List<MeetingList> list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()  {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView d , m , title , desc;

        public MyViewHolder(View itemView) {
            super(itemView);

            d = (TextView)itemView.findViewById(R.id.day);
            m = (TextView)itemView.findViewById(R.id.month);
            title = (TextView)itemView.findViewById(R.id.title);
            desc = (TextView)itemView.findViewById(R.id.desc);

        }
    }
}
