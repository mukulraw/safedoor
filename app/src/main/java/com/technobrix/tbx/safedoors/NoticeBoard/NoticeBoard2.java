package com.technobrix.tbx.safedoors.NoticeBoard;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.MainActivity;
import com.technobrix.tbx.safedoors.R;

public class NoticeBoard2 extends Fragment {

    TextView date, no;
    WebView des;
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.notice_board2, container, false);
        toolbar = (Toolbar) ((MainActivity) getContext()).findViewById(R.id.toolbar);

        date = (TextView) view.findViewById(R.id.date);
        //admin = (TextView)view.findViewById(R.id.admin);
        des = (WebView) view.findViewById(R.id.notice);
        no = (TextView) view.findViewById(R.id.no);


        Bundle b = getArguments();

        String d = b.getString("date");
        //String a = b.getString("title");
        String de = b.getString("description");
        String n = b.getString("title");

        date.setText(d);
        //admin.setText(a);

        des.loadDataWithBaseURL(null , de , "text/html", "utf-8",null);
        no.setText("Notice : " + n);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = ((MainActivity) getContext()).getSupportFragmentManager();
                fm.popBackStack();

            }
        });

    }
}
