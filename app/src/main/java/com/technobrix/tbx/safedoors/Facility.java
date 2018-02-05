package com.technobrix.tbx.safedoors;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.technobrix.tbx.safedoors.FacilityPOJO.Bean;
import com.technobrix.tbx.safedoors.FacilityPOJO.FacilityList;
import com.technobrix.tbx.safedoors.LoginPOJO.LoginBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Facility extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    FacilityAdapter adapter;
    ProgressBar bar;
    TextView date;
    List<FacilityList> list;

    String date1;

    ConnectionDetector cd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.facility, container, false);

        cd = new ConnectionDetector(getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.facility);

        date = (TextView) view.findViewById(R.id.date);

        bar = (ProgressBar) view.findViewById(R.id.progress);

        manager = new GridLayoutManager(getContext(), 1);

        list = new ArrayList<>();

        adapter = new FacilityAdapter(getActivity(), list);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);

        Log.d("kamal", "hii");

        Calendar c = Calendar.getInstance();
        //System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyy");
        String formattedDate = df.format(c.getTime());

        date.setText(formattedDate);

        loadData(formattedDate);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.date_dialog);
                dialog.setCancelable(false);
                dialog.show();


                final DatePicker picker = (DatePicker) dialog.findViewById(R.id.picker);
                Button ok = (Button) dialog.findViewById(R.id.ok);
                Button cancel = (Button) dialog.findViewById(R.id.cancel);


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String day = String.valueOf(picker.getDayOfMonth());
                        String month = String.valueOf(picker.getMonth() + 1);
                        String year = String.valueOf(picker.getYear());

                        date1 = day + "-" + month + "-" + year;

                        date.setText(day + "-" + month + "-" + year);

                        dialog.dismiss();

                        loadData(date1);

                    }
                });


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();


                    }
                });

            }
        });
        return view;
    }


    private void loadData(String date) {

        if (cd.isConnectingToInternet()) {
            bar.setVisibility(View.VISIBLE);

            bean b = (bean) getContext().getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);


            Call<Bean> call = cr.bean(b.socity, date);

            call.enqueue(new Callback<Bean>() {
                @Override
                public void onResponse(Call<Bean> call, Response<Bean> response) {

                    bar.setVisibility(View.GONE);

                    adapter.setgriddata(response.body().getFacilityList());

                }

                @Override
                public void onFailure(Call<Bean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });


        } else {

            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }


    public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.MyViewHolder> {

        Context context;
        List<FacilityList> list = new ArrayList<>();


        public FacilityAdapter(Context context, List<FacilityList> list) {

            this.context = context;
            this.list = list;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.facility_list_model, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {


            final Bitmap[] bmp = new Bitmap[1];

            final FacilityList item = list.get(position);
            holder.name.setText(item.getName());
            holder.lorem.setText("INR " + item.getPricePer());
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .cacheOnDisc(true).resetViewBeforeLoading(false).build();
            ImageLoader loader = ImageLoader.getInstance();

            //loader.displayImage(item.getFacilityImg(), holder.image);

            loader.loadImage(item.getFacilityImg(), new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    bmp[0] = loadedImage;
                    holder.image.setImageBitmap(bmp[0]);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });


            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.image_dialog);
                    dialog.setCancelable(true);

                    dialog.show();

                    ImageView imageView = dialog.findViewById(R.id.image);

                    imageView.setImageBitmap(bmp[0]);

                }
            });


            try {

                holder.linear.removeAllViews();

                for (int i = 0; i < item.getData().size(); i++) {

                    TextView tv = new TextView(context);
                    tv.setText("Booked in between " + item.getData().get(i).getFromTime() + "  to  " + item.getData().get(i).getToTime());
                    holder.linear.addView(tv);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            holder.book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (cd.isConnectingToInternet()) {

                        if (item.getData().size() > 0) {

                            Toast.makeText(context, "Facility Already booked for this Date", Toast.LENGTH_SHORT).show();


                        } else {


                            final Dialog dialog = new Dialog(context);
                            dialog.setContentView(R.layout.book_dialog);
                            dialog.setCancelable(true);
                            dialog.show();


                            final TextView date = (TextView) dialog.findViewById(R.id.date);
                            final TextView time1 = (TextView) dialog.findViewById(R.id.time1);
                            final TextView time2 = (TextView) dialog.findViewById(R.id.time2);
                            Button ok = (Button) dialog.findViewById(R.id.ok);


                            try {

                                for (int i = 0; i < item.getData().size(); i++) {

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            date.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    final Dialog dialog = new Dialog(context);
                                    dialog.setContentView(R.layout.datedialog);
                                    dialog.setCancelable(true);
                                    dialog.show();

                                    final DatePicker picker = (DatePicker) dialog.findViewById(R.id.picker);
                                    Button submit = (Button) dialog.findViewById(R.id.submit);

                                    submit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            String day = String.valueOf(picker.getDayOfMonth());
                                            String month = String.valueOf(picker.getMonth() + 1);
                                            String year = String.valueOf(picker.getYear());

                                            String date1 = day + "-" + month + "-" + year;

                                            date.setText(date1);

                                            dialog.dismiss();


                                        }
                                    });


                                }
                            });


                            time1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                    final Dialog dialog = new Dialog(context);
                                    dialog.setContentView(R.layout.time_dialog);
                                    dialog.setCancelable(true);
                                    dialog.show();

                                    final TimePicker picker = (TimePicker) dialog.findViewById(R.id.picker);
                                    Button ok = (Button) dialog.findViewById(R.id.ok);

                                    ok.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            Integer hour = picker.getCurrentHour();
                                            Integer min = picker.getCurrentMinute();

                                            String time = hour + " : " + min;


                                            String format = "";

                                            if (hour == 0) {
                                                hour += 12;
                                                format = "AM";
                                            } else if (hour == 12) {
                                                format = "PM";
                                            } else if (hour > 12) {
                                                hour -= 12;
                                                format = "PM";
                                            } else {
                                                format = "AM";
                                            }

                                            time1.setText(new StringBuilder().append(hour).append(" : ").append(min)
                                                    .append(" ").append(format));


                                            //time1.setText(time);
                                            dialog.dismiss();


                                        }
                                    });


                                }
                            });

                            time2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    final Dialog dialog = new Dialog(context);
                                    dialog.setContentView(R.layout.time_dialog);
                                    dialog.setCancelable(true);
                                    dialog.show();

                                    final TimePicker picker = (TimePicker) dialog.findViewById(R.id.picker);
                                    Button ok = (Button) dialog.findViewById(R.id.ok);

                                    ok.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {


                                            Integer hour = picker.getCurrentHour();
                                            Integer min = picker.getCurrentMinute();

                                            String time = hour + " : " + min;


                                            String format = "";

                                            if (hour == 0) {
                                                hour += 12;
                                                format = "AM";
                                            } else if (hour == 12) {
                                                format = "PM";
                                            } else if (hour > 12) {
                                                hour -= 12;
                                                format = "PM";
                                            } else {
                                                format = "AM";
                                            }

                                            time2.setText(new StringBuilder().append(hour).append(" : ").append(min)
                                                    .append(" ").append(format));


                                            //time1.setText(time);
                                            dialog.dismiss();


                                        }
                                    });


                                }
                            });


                            ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    String t = time1.getText().toString();
                                    String t1 = time2.getText().toString();
                                    String d = date.getText().toString();

                                    if (d.length() > 0) {

                                        if (t.length() > 0) {

                                            if (t1.length() > 0) {

                                                bar.setVisibility(View.VISIBLE);

                                                bean b = (bean) context.getApplicationContext();

                                                Retrofit retrofit = new Retrofit.Builder()
                                                        .baseUrl("http://safedoors.in")
                                                        .addConverterFactory(ScalarsConverterFactory.create())
                                                        .addConverterFactory(GsonConverterFactory.create())
                                                        .build();

                                                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                                                Call<bookFacilityBean> call = cr.bookFacility(b.socity, b.house_id, item.getId(), b.userId, date.getText().toString(), item.getPricePer(), time1.getText().toString(), time2.getText().toString());

                                                call.enqueue(new Callback<bookFacilityBean>() {
                                                    @Override
                                                    public void onResponse(Call<bookFacilityBean> call, Response<bookFacilityBean> response) {

                                                        bar.setVisibility(View.GONE);

                                                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                                        dialog.dismiss();

                                                    }

                                                    @Override
                                                    public void onFailure(Call<bookFacilityBean> call, Throwable t) {
                                                        dialog.dismiss();
                                                        t.printStackTrace();
                                                        bar.setVisibility(View.GONE);
                                                    }
                                                });


                                            } else {

                                                Toast.makeText(context, "Invalid End Time", Toast.LENGTH_SHORT).show();
                                            }


                                        } else {

                                            Toast.makeText(context, "Invalid Start Time", Toast.LENGTH_SHORT).show();
                                        }


                                    } else {
                                        Toast.makeText(context, "Invalid Date", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });


                        }


                    } else {

                        Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }


                }
            });

        }

        public void setgriddata(List<FacilityList> list) {

            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView name, lorem, one, book;

            TextView date, time1, time2;
            ImageView image;
            LinearLayout linear;

            public MyViewHolder(View itemView) {
                super(itemView);

                name = (TextView) itemView.findViewById(R.id.name);
                lorem = (TextView) itemView.findViewById(R.id.text);
                image = (ImageView) itemView.findViewById(R.id.image);
                book = (TextView) itemView.findViewById(R.id.book);
                date = (TextView) itemView.findViewById(R.id.date);
                time1 = (TextView) itemView.findViewById(R.id.time1);
                time2 = (TextView) itemView.findViewById(R.id.time2);
                linear = (LinearLayout) itemView.findViewById(R.id.linear);


            }
        }
    }


}
