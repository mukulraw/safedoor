package com.technobrix.tbx.safedoors.Event;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.ConnectionDetector;
import com.technobrix.tbx.safedoors.GetAllMeetingPOJO.GetAllBean;
import com.technobrix.tbx.safedoors.GetAllMeetingPOJO.MeetingList;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by tvs on 10/31/2017.
 */

public class Meeting extends Fragment {

    RecyclerView grid;

    GridLayoutManager manager;

    MeetingAdapter adapter;

    List<MeetingList> list;

    ProgressBar bar;

    FloatingActionButton filter;

    ConnectionDetector cd;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.event_meeting, container, false);

        cd = new ConnectionDetector(getContext());

        grid = (RecyclerView) view.findViewById(R.id.grid);

        bar = (ProgressBar) view.findViewById(R.id.progress);

        filter = (FloatingActionButton) view.findViewById(R.id.filter);

        manager = new GridLayoutManager(getContext(), 1);

        list = new ArrayList<>();

        adapter = new MeetingAdapter(getContext(), list);

        grid.setLayoutManager(manager);

        grid.setAdapter(adapter);


        if (cd.isConnectingToInternet()) {

            bar.setVisibility(View.VISIBLE);

            Log.d("dfhisd", "to");

            bean b = (bean) getContext().getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            Call<GetAllBean> call = cr.me(b.socity);

            call.enqueue(new Callback<GetAllBean>() {
                @Override
                public void onResponse(Call<GetAllBean> call, Response<GetAllBean> response) {

                    Log.d("hfgshdf", "response");

                    list = response.body().getMeetingList();

                    adapter.setgrid(list);

                    bar.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<GetAllBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                    Log.d("shdfgkjdf", t.toString());
                }
            });

        } else {

            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cd.isConnectingToInternet()) {

                    final Dialog dialog = new Dialog(getActivity());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.filter_popup);
                    dialog.setCancelable(true);
                    dialog.show();


                    TextView filter = (TextView) dialog.findViewById(R.id.filter);
                    final DatePicker picker = (DatePicker) dialog.findViewById(R.id.picker);
                    Button clear = (Button) dialog.findViewById(R.id.clear);
                    Button ok = (Button) dialog.findViewById(R.id.ok);


                    clear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            adapter.setgrid(list);
                            dialog.dismiss();


                        }
                    });


                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            String day = String.valueOf(picker.getDayOfMonth());
                            String month = String.valueOf(picker.getMonth() + 1);
                            String year = String.valueOf(picker.getYear());

                            final String date = year + "-" + month + "-" + day;

                            bar.setVisibility(View.VISIBLE);

                            Log.d("vkfdlg", "hlo");

                            bean b = (bean) getContext().getApplicationContext();

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://safedoors.in")
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllApiInterface cr = retrofit.create(AllApiInterface.class);

                            Call<GetAllBean> call = cr.mdb(b.socity, date);

                            Log.d("hgdfg", date);

                            call.enqueue(new Callback<GetAllBean>() {
                                @Override
                                public void onResponse(Call<GetAllBean> call, Response<GetAllBean> response) {

                                    Log.d("bsgkflg", "response");

                                    adapter.setgrid(response.body().getMeetingList());

                                    bar.setVisibility(View.GONE);

                                    dialog.dismiss();

                                }

                                @Override
                                public void onFailure(Call<GetAllBean> call, Throwable t) {

                                    bar.setVisibility(View.GONE);

                                    Log.d("jfdsgjkd", t.toString());

                                }
                            });

                        }
                    });


                } else {
                    Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return view;

    }


    public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MyViewHolder> {


        Context context;

        List<MeetingList> list = new ArrayList<>();

        public MeetingAdapter(Context context, List<MeetingList> list) {

            this.context = context;
            this.list = list;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.mee_list_model, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            final MeetingList item = list.get(position);

            //holder.s.setText(String.valueOf(position + 1));
            holder.d.setText(item.getMeetingDate());
            holder.t.setText(item.getTitle());
            holder.time.setText(item.getMeetingTime());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.desc_popup);
                    dialog.setCancelable(true);
                    dialog.show();
                    TextView tv = (TextView)dialog.findViewById(R.id.text);

                    tv.setText(item.getDescription());

                }
            });

            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (cd.isConnectingToInternet()) {

                        bar.setVisibility(View.VISIBLE);

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://safedoors.in/")
                                .build();

                        AllApiInterface cr = retrofit.create(AllApiInterface.class);

                        Log.d("url", item.getMeetingAttachment());

                        cr.getFile(item.getMeetingAttachment()).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                try {

                                    String fn = item.getMeetingAttachment();

                                    String[] dd = fn.split("/");


                                    Log.d("asdasd", response.message());


                                    DownloadFileAsyncTask downloadFileAsyncTask = new DownloadFileAsyncTask(item.getMeetingAttachment(), dd[dd.length - 1]);
                                    downloadFileAsyncTask.execute(response.body().byteStream());

                                } catch (Exception e) {
                                    bar.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                bar.setVisibility(View.GONE);

                            }

                        });


                    } else {

                        Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }


                }
            });


        }

        public void setgrid(List<MeetingList> list) {


            this.list = list;

            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView s, d, t, time;

            ImageView image;

            public MyViewHolder(View itemView) {
                super(itemView);

                // s = (TextView)itemView.findViewById(R.id.one);
                d = (TextView) itemView.findViewById(R.id.date);
                t = (TextView) itemView.findViewById(R.id.title);
                time = (TextView) itemView.findViewById(R.id.time);
                image = (ImageView) itemView.findViewById(R.id.image);
            }
        }


        private class DownloadFileAsyncTask extends AsyncTask<InputStream, Void, Boolean> {

            String TAG = "asdasdasd";


            File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/SafeDoors/");


            final String filename;

            String key;

            String path = Environment.getExternalStorageDirectory().toString();

            byte[] decodedBytes = null;

            File file;

            public DownloadFileAsyncTask(String key, String name) {
                this.key = key;
                this.filename = name;
            }


            @Override
            protected Boolean doInBackground(InputStream... params) {

                if (!(dir.exists() && dir.isDirectory())) {
                    dir.mkdirs();
                }

                InputStream inputStream = params[0];


                try {
                    file = new File(dir, filename);

                    file.createNewFile();
                } catch (IOException e) {
                    Log.e(TAG, "IO Exception: " + e.getMessage());
                }


                OutputStream output = null;
                try {
                    output = new FileOutputStream(file);

                    byte[] buffer = new byte[1024]; // or other buffer size
                    int read;

                    Log.d(TAG, "Attempting to write to: " + dir + "/" + filename);
                    while ((read = inputStream.read(buffer)) != -1) {
                        output.write(buffer, 0, read);
                        Log.v(TAG, "Writing to buffer to output stream.");
                    }
                    Log.d(TAG, "Flushing output stream.");
                    output.flush();
                    Log.d(TAG, "Output flushed.");
                } catch (IOException e) {
                    Log.e(TAG, "IO Exception: " + e.getMessage());
                    e.printStackTrace();
                    return false;
                } finally {
                    try {
                        if (output != null) {
                            output.close();
                            Log.d("Asdasdasd", "Output stream closed sucessfully.");
                        } else {
                            Log.d(TAG, "Output stream is null");
                        }
                    } catch (IOException e) {
                        Log.e("Asdasdasd", "Couldn't close output stream: " + e.getMessage());
                        e.printStackTrace();
                        return false;
                    }


                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);


                bar.setVisibility(View.GONE);
                Toast.makeText(context, "Successfully Downloaded in Downloads/SafeDoors", Toast.LENGTH_SHORT).show();

            }
        }


    }

}
