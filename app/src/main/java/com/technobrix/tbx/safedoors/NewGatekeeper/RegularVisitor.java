package com.technobrix.tbx.safedoors.NewGatekeeper;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.RegularNotification;
import com.technobrix.tbx.safedoors.StaffListPOJO.StaffList;
import com.technobrix.tbx.safedoors.StaffListPOJO.staffListBean;
import com.technobrix.tbx.safedoors.bean;
import com.technobrix.tbx.safedoors.entryBean;
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

public class RegularVisitor extends AppCompatActivity {

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_visitor);

        grid = (RecyclerView) findViewById(R.id.grid);
        comment = (EditText) findViewById(R.id.comment);

        manager = new GridLayoutManager(getApplicationContext(), 1);

        list = new ArrayList<>();
        list1 = new ArrayList<>();

        adapter1 = new RegularAdapter1(this, list);

        grid.setAdapter(adapter1);

        grid.setNestedScrollingEnabled(false);

        grid.setLayoutManager(manager);

        spinner = (SearchableSpinner) findViewById(R.id.spinner);

        in = (Button) findViewById(R.id.in);


        name = new ArrayList<>();
        id = new ArrayList<>();
        status = new ArrayList<>();

        progress = (ProgressBar) findViewById(R.id.progress);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        toolbar.setTitle("Regular Visitor");

        /*plus = (FloatingActionButton) findViewById(R.id.plus);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RegularVisitor.this, AddStaff.class);
                startActivity(i);


            }
        });
*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.notification) {

            Intent i = new Intent(RegularVisitor.this, RegularNotification.class);
            startActivity(i);

        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {

        list.clear();


        final bean b = (bean) getApplicationContext();

        progress.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<staffListBean> call = cr.getStaffList(b.socity);

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



















       /* list.clear();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        progress.setVisibility(View.VISIBLE);

        bean b = (bean) getApplicationContext();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);


        Call<visitorListBean> call = cr.getVisitors(b.socity);

        call.enqueue(new Callback<visitorListBean>() {
            @Override
            public void onResponse(Call<visitorListBean> call, Response<visitorListBean> response) {

                Log.d("asdasdSize" , String.valueOf(response.body().getVisitorList().size()));

                for (int i = 0; i < response.body().getVisitorList().size(); i++)
                {
                    if (Objects.equals(response.body().getVisitorList().get(i).getVisitorType(), "Regular")) {
                        list.add(response.body().getVisitorList().get(i));
                    }
                }

                adapter1.setGridData(list);


                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<visitorListBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });*/
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
        public void onBindViewHolder(final MyViewHolder holder, int position) {

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
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.regular1_list_model, parent, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {


            final StaffList item = list.get(position);


            holder.name.setText(item.getStaffName());
            holder.phone.setText(item.getPhone());
            holder.profess.setText(item.getProfesstion());
            holder.close.setVisibility(View.GONE);



            if (Objects.equals(item.getStaffStaus(), "In")) {
                holder.out.setBackground(context.getResources().getDrawable(R.drawable.green_circle));
            } else if (Objects.equals(item.getStaffStaus(), "Out")) {
                holder.out.setBackground(context.getResources().getDrawable(R.drawable.red_circle));
            }


            holder.out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (Objects.equals(item.getStaffStaus(), "In")) {

                        final Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.out_popup);
                        dialog.setCancelable(true);
                        dialog.show();


                        Button submit = dialog.findViewById(R.id.yes);
                        Button cancel = dialog.findViewById(R.id.no);
                        final ProgressBar progressBar = dialog.findViewById(R.id.progress);

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                dialog.dismiss();
                            }
                        });


                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                progressBar.setVisibility(View.VISIBLE);

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
                                        dialog.dismiss();

                                        progressBar.setVisibility(View.GONE);


                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {

                                        progressBar.setVisibility(View.GONE);
                                    }
                                });

                            }
                        });


                    } else if (Objects.equals(item.getStaffStaus(), "Out")) {


                        final Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.in_popup);
                        dialog.setCancelable(true);
                        dialog.show();

                        final EditText comment = dialog.findViewById(R.id.comment);
                        Button no = dialog.findViewById(R.id.cancel);
                        Button yes = dialog.findViewById(R.id.submit);
                        final ProgressBar progressBar1 = dialog.findViewById(R.id.progress);


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

                                progressBar1.setVisibility(View.VISIBLE);


                                        Retrofit retrofit = new Retrofit.Builder()
                                                .baseUrl("http://safedoors.in")
                                                .addConverterFactory(ScalarsConverterFactory.create())
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build();

                                        bean b = (bean) context.getApplicationContext();

                                        AllApiInterface cr = retrofit.create(AllApiInterface.class);
                                        Call<entryBean> call1 = cr.regularIn(b.socity, comment.getText().toString(), b.userId, "Regular", item.getStaffId());


                                        call1.enqueue(new Callback<entryBean>() {
                                            @Override
                                            public void onResponse(Call<entryBean> call, Response<entryBean> entryBean) {

                                                //in.setVisibility(View.VISIBLE);
                                                //already.setVisibility(View.GONE);

                                                Toast.makeText(RegularVisitor.this, "Visitor is In", Toast.LENGTH_SHORT).show();

                                                loadData();
                                                dialog.dismiss();

                                                progressBar1.setVisibility(View.GONE);


                                            }

                                            @Override
                                            public void onFailure(Call<entryBean> call, Throwable t) {
                                                progressBar1.setVisibility(View.GONE);
                                            }
                                        });



                            }
                        });


                    }


                }
            });


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


}



