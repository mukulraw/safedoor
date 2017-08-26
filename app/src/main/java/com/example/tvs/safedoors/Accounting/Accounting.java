package com.example.tvs.safedoors.Accounting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tvs.safedoors.R;


public class Accounting extends Fragment {

    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.accounting_layout , container , false);

        linearLayout1 = (LinearLayout)view.findViewById(R.id.linear1);
        linearLayout2 = (LinearLayout)view.findViewById(R.id.linear2);
        linearLayout3 = (LinearLayout)view.findViewById(R.id.linear3);
        linearLayout4 = (LinearLayout)view.findViewById(R.id.linear4);
        linearLayout5 = (LinearLayout)view.findViewById(R.id.linear5);
        return view;
    }
}
