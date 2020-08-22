package com.technobrix.tbx.safedoors;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.DeletePOJO.DeleteBean;
import com.technobrix.tbx.safedoors.StaffListPOJO.StaffList;
import com.technobrix.tbx.safedoors.StaffListPOJO.staffListBean;
import com.technobrix.tbx.safedoors.visitorListPOJO.VisitorList;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

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

public class GateRegularVisitor extends Fragment {

    Button in;

    SearchableSpinner spinner;

    Toolbar toolbar;

    ProgressBar progress;

    List<String> name;

    List<String> id;

    List<String> status;

    String iidd, sstt;

    TextView already;

    RecyclerView grid;

    GridLayoutManager manager;

    RegularAdapter adapter;
    RegularAdapter1 adapter1;

    EditText filter, comment;

    List<VisitorList> list1;
    List<StaffList> list;

    FloatingActionButton plus;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gateregularvisitor, container, false);


        grid = (RecyclerView) view.findViewById(R.id.grid);
        comment = (EditText) view.findViewById(R.id.comment);

        manager = new GridLayoutManager(getContext(), 1);

        list = new ArrayList<>();
        list1 = new ArrayList<>();

        adapter1 = new RegularAdapter1(getContext(), list);

        grid.setAdapter(adapter1);

        grid.setNestedScrollingEnabled(false);

        grid.setLayoutManager(manager);

        spinner = (SearchableSpinner) view.findViewById(R.id.spinner);

        in = (Button) view.findViewById(R.id.in);


        name = new ArrayList<>();
        id = new ArrayList<>();
        status = new ArrayList<>();

        progress = (ProgressBar) view.findViewById(R.id.progress);
        plus = (FloatingActionButton) view.findViewById(R.id.plus);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getContext(), AddStaff.class);
                startActivity(i);
            }
        });

        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }

        });

        toolbar.setTitle("Regular Visitor");*/

        /*final bean b = (bean) getContext().getApplicationContext();

        progress.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<staffListBean> call = cr.getStaffList2(b.socity , b.house_id);

        call.enqueue(new Callback<staffListBean>() {
            @Override
            public void onResponse(Call<staffListBean> call, Response<staffListBean> response) {

                for (int i = 0; i < response.body().getStaffList().size(); i++) {
                    name.add(response.body().getStaffList().get(i).getStaffName());
                    id.add(response.body().getStaffList().get(i).getStaffId());
                    status.add(response.body().getStaffList().get(i).getStaffStaus());
                }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_model, name);
                spinner.setAdapter(adapter);

                Log.d("fdsg", String.valueOf(response.body().getStaffList().size()));

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<staffListBean> call, Throwable t) {
                progress.setVisibility(View.GONE);

                Log.d("jdhfsd", t.toString());
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                iidd = id.get(i);
                sstt = status.get(i);

                if (Objects.equals(sstt, "Out")) {
                    in.setVisibility(View.VISIBLE);
                    //already.setVisibility(View.GONE);
                } else {
                    in.setVisibility(View.VISIBLE);
                    //already.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/


       /* in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress.setVisibility(View.VISIBLE);

                Call<entryBean> call1 = cr.regularIn(b.socity, comment.getText().toString(), b.userId, "Regular", iidd);

                Log.d("society", b.socity);
                Log.d("user", b.userId);
                Log.d("id", iidd);

                call1.enqueue(new Callback<entryBean>() {
                    @Override
                    public void onResponse(Call<entryBean> call, Response<entryBean> entryBean) {

                        in.setVisibility(View.VISIBLE);
                        //already.setVisibility(View.GONE);

                        Toast.makeText(getContext(), "Visitor is In", Toast.LENGTH_SHORT).show();

                        loadData();

                        progress.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<entryBean> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                    }
                });


            }
        });*/

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {

        list.clear();


        final bean b = (bean) getContext().getApplicationContext();

        progress.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<staffListBean> call = cr.getStaffList2(b.socity, b.house_id);

        call.enqueue(new Callback<staffListBean>() {
            @Override
            public void onResponse(Call<staffListBean> call, Response<staffListBean> response) {


                list = response.body().getStaffList();

                adapter1.setGridData(list);

                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<staffListBean> call, Throwable t) {
                progress.setVisibility(View.GONE);

                Log.d("jdhfsd", t.toString());
            }
        });


    }

    public class RegularAdapter1 extends RecyclerView.Adapter<RegularAdapter1.MyViewHolder> {

        List<StaffList> list = new ArrayList<>();
        Context context;


        public RegularAdapter1(Context context, List<StaffList> list) {

            this.context = context;
            this.list = list;
        }

        public void setGridData(List<StaffList> list) {
            this.list = list;
            notifyDataSetChanged();
        }


        @Override
        public RegularAdapter1.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.regular1_list_model, parent, false);

            return new RegularAdapter1.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RegularAdapter1.MyViewHolder holder, int position) {


            final StaffList item = list.get(position);


            holder.name.setText(item.getStaffName());
            holder.phone.setText(item.getPhone());
            holder.profess.setText(item.getProfesstion());


            if (Objects.equals(item.getStaffStaus(), "In")) {
                holder.out.setBackground(context.getResources().getDrawable(R.drawable.green_circle));
            } else if (Objects.equals(item.getStaffStaus(), "Out")) {
                holder.out.setBackground(context.getResources().getDrawable(R.drawable.red_circle));
            }


            holder.close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    final bean b = (bean) getContext().getApplicationContext();

                    progress.setVisibility(View.VISIBLE);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://safedoors.in")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


                    final AllApiInterface cr = retrofit.create(AllApiInterface.class);

                    Call<DeleteBean> call = cr.delete(b.socity, item.getStaffId(), b.house_id);

                    call.enqueue(new Callback<DeleteBean>() {
                        @Override
                        public void onResponse(Call<DeleteBean> call, Response<DeleteBean> response) {

                            loadData();
                            progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<DeleteBean> call, Throwable t) {
                            progress.setVisibility(View.GONE);

                        }
                    });


                }
            });

           /* holder.out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (Objects.equals(item.getStaffStaus(), "In")) {

                        final Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.in_popup);
                        dialog.setCancelable(true);
                        dialog.show();


                        EditText comment = dialog.findViewById(R.id.comment);
                        Button submit = dialog.findViewById(R.id.submit);
                        Button cancel = dialog.findViewById(R.id.cancel);

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                dialog.dismiss();
                            }
                        });


                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("mukul", "in");
                                progress.setVisibility(View.VISIBLE);

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://safedoors.in")
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                bean b = (bean) context.getApplicationContext();

                                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                                Call<String> visitorOut = cr.out(b.socity, item.getStaffId());

                                visitorOut.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {


                                        loadData();

                                        Toast.makeText(context, "Visitor is out", Toast.LENGTH_SHORT).show();

                                        progress.setVisibility(View.GONE);

                                        dialog.dismiss();

                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        progress.setVisibility(View.GONE);
                                    }
                                });

                            }
                        });


                    } else if (Objects.equals(item.getStaffStaus(), "Out")) {


                        final Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.out_popup);
                        dialog.setCancelable(true);
                        dialog.show();

                        final TextView comment = dialog.findViewById(R.id.comment);
                        Button no = dialog.findViewById(R.id.no);
                        Button yes = dialog.findViewById(R.id.yes);


                        no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                dialog.dismiss();
                            }
                        });


                        yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                Log.d("nisha", "out");
                                in.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        progress.setVisibility(View.VISIBLE);


                                        Retrofit retrofit = new Retrofit.Builder()
                                                .baseUrl("http://safedoors.in")
                                                .addConverterFactory(ScalarsConverterFactory.create())
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build();

                                        bean b = (bean) context.getApplicationContext();

                                        AllApiInterface cr = retrofit.create(AllApiInterface.class);
                                        Call<entryBean> call1 = cr.regularIn(b.socity, comment.getText().toString(), b.userId, "Regular", iidd);

                                        Log.d("society", b.socity);
                                        Log.d("user", b.userId);
                                        Log.d("id", iidd);

                                        call1.enqueue(new Callback<entryBean>() {
                                            @Override
                                            public void onResponse(Call<entryBean> call, Response<entryBean> entryBean) {

                                                in.setVisibility(View.VISIBLE);
                                                //already.setVisibility(View.GONE);

                                                Toast.makeText(getContext(), "Visitor is In", Toast.LENGTH_SHORT).show();

                                                loadData();
                                                dialog.dismiss();

                                                progress.setVisibility(View.GONE);


                                            }

                                            @Override
                                            public void onFailure(Call<entryBean> call, Throwable t) {
                                                progress.setVisibility(View.GONE);
                                            }
                                        });


                                    }
                                });


                            }
                        });


                    }


                }
            });
*/

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView name, profess, phone;
            ImageButton out;
            ImageView close;


            public MyViewHolder(View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.name);
                profess = itemView.findViewById(R.id.professional);
                phone = itemView.findViewById(R.id.phone);
                out = itemView.findViewById(R.id.out);
                close = itemView.findViewById(R.id.close);

            }
        }
    }

    public class RegularAdapter extends RecyclerView.Adapter<RegularAdapter.MyViewHolder> {

        List<VisitorList> list = new ArrayList<>();
        Context context;


        public RegularAdapter(Context context, List<VisitorList> list) {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<VisitorList> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.regular_list_model, parent, false);
            return new RegularAdapter.MyViewHolder(view);
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

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

            /*holder.out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (Objects.equals(item.getStatus(), "0")) {
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

                                Toast.makeText(context, "Visitor is out", Toast.LENGTH_SHORT).show();

                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                progress.setVisibility(View.GONE);
                            }
                        });
                    }


                }
            });
*/

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView time, visitor, house, comment;
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


}
