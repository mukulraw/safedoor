package com.technobrix.tbx.safedoors.NoticeBoard;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.NoticeListPOJO.NoticeList;
import com.technobrix.tbx.safedoors.R;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {

    Context context;
    List<NoticeList> list = new ArrayList<>();

    public NoticeAdapter(Context context , List<NoticeList> list){

        this.context = context;
        this.list = list;
    }

    public void setGridData(List<NoticeList> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public NoticeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notice_board_list_model , parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoticeAdapter.MyViewHolder holder, int position) {
        NoticeList item = list.get(position);


        holder.title.setText(item.getNotice());
        holder.date.setText(item.getDate());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title , date;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.title);
            date = (TextView)itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FragmentManager fragmentManager =((AppCompatActivity)context).getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    NoticeBoard2 fragment = new NoticeBoard2();
                    ft.replace(R.id.replace, fragment);
                    ft.addToBackStack(null);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    ft.commit();


                }
            });
        }
    }
}
