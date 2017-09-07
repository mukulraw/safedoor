package com.technobrix.tbx.safedoors.NoticeBoard;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technobrix.tbx.safedoors.R;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {

    Context context;

    public NoticeAdapter(Context context){

        this.context = context;
    }
    @Override
    public NoticeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notice_board_list_model , parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoticeAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);

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
