package com.technobrix.tbx.safedoors;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.technobrix.tbx.safedoors.visitorListPOJO.VisitorList;
import com.technobrix.tbx.safedoors.visitorListPOJO.visitorListBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NewNotification extends AppCompatActivity {

    private static final int TAKE_PICTURE = 1;
    static final int RC_TAKE_PHOTO = 1;
    private Uri imageUri;

    EditText name, purpuse;

    Spinner member;

    List<String> memberName;
    List<String> memberId;
    List<String> houseId;
    List<VisitorList> list;

    String mCurrentPhotoPath = "";
    String membId = "";
    String houId = "";

    ImageView image, log;

    File file;
    Uri fileUri;

    Button submit;

    ProgressBar progress;

    TextView cap;

    SharedPreferences pref;

    SharedPreferences.Editor edit;

    Toolbar toolbar;

    RecyclerView grid;

    GridLayoutManager manager;

    RegularAdapter1 adapter;

    EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notification);

        comment = (EditText) findViewById(R.id.comment);

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

        toolbar.setTitle("Notification");

        grid = (RecyclerView) findViewById(R.id.grid);

        manager = new GridLayoutManager(getApplicationContext(), 1);

        list = new ArrayList<>();

        adapter = new RegularAdapter1(this, list);

        grid.setLayoutManager(manager);

        grid.setAdapter(adapter);
        grid.setNestedScrollingEnabled(false);


        memberName = new ArrayList<>();
        memberId = new ArrayList<>();
        houseId = new ArrayList<>();

        name = (EditText) findViewById(R.id.name);

        purpuse = (EditText) findViewById(R.id.purpuse);

        member = (Spinner) findViewById(R.id.spinner);

        image = (ImageView) findViewById(R.id.image);

        cap = (TextView) findViewById(R.id.capture);

        submit = (Button) findViewById(R.id.submit);

        progress = (ProgressBar) findViewById(R.id.progress);

        log = (ImageView) findViewById(R.id.log);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {

        list.clear();

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

                Log.d("asdasdSize", String.valueOf(response.body().getVisitorList().size()));

                for (int i = 0; i < response.body().getVisitorList().size(); i++) {
                    if (Objects.equals(response.body().getVisitorList().get(i).getVisitorType(), "New")) {
                        list.add(response.body().getVisitorList().get(i));
                    }
                }

                adapter.setGridData(list);


                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<visitorListBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });
    }

    public class RegularAdapter1 extends RecyclerView.Adapter<RegularAdapter1.MyViewHolder> {

        List<VisitorList> list = new ArrayList<>();
        Context context;


        public RegularAdapter1(Context context, List<VisitorList> list) {
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
            return new MyViewHolder(view);
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            final VisitorList item = list.get(position);

            holder.time.setText(item.getIntime());

            holder.visitor.setText(item.getVisitorName());
            holder.car.setText(item.getCarNo());

            holder.house.setText(item.getHouseNo());


            final DisplayImageOptions imageOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(false).build();
            final ImageLoader loader = ImageLoader.getInstance();

            loader.displayImage(item.getPic(), holder.imageView, imageOptions);


            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.image_dialog);
                    dialog.setCancelable(true);
                    dialog.show();


                    ImageView im = dialog.findViewById(R.id.image);
                    loader.displayImage(item.getPic(), im, imageOptions);

                }
            });


            final DisplayImageOptions imageOptions1 = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(false).build();
            final ImageLoader loader1 = ImageLoader.getInstance();

            loader1.displayImage(item.getPic1(), holder.imageView1, imageOptions1);


            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.image_dialog);
                    dialog.setCancelable(true);
                    dialog.show();


                    ImageView im = dialog.findViewById(R.id.image);
                    loader1.displayImage(item.getPic1(), im, imageOptions1);

                }
            });






            if (Objects.equals(item.getStatus(), "0")) {
                holder.out.setBackground(context.getResources().getDrawable(R.drawable.green_circle));
            } else if (Objects.equals(item.getStatus(), "1")) {
                holder.out.setBackground(context.getResources().getDrawable(R.drawable.red_circle));
            }


            if (Objects.equals(item.getStatus(), "0")) {

                String intime = item.getIntime();

                String[] str = intime.split(" ");

                holder.time.setText(str[1] + " " + str[2]);
                holder.date.setText(str[0]);


            } else if (Objects.equals(item.getStatus(), "1")) {

                //holder.time.setText(item.getOuttime());

                String outtime = item.getOuttime();

                String[] maa = outtime.split(" ");

                holder.time.setText(maa[1] + " " + maa[2]);
                holder.date.setText(maa[0]);


            }
            holder.comment.setText(item.getComment());

           /* holder.out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (Objects.equals(item.getStatus(), "0")){
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

                                //loadData();


                                Toast.makeText(context,"Visitor is out", Toast.LENGTH_SHORT).show();
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

            TextView time, visitor, house, comment, date , car;
            ImageButton out;

            CircleImageView imageView , imageView1;

            public MyViewHolder(View itemView) {
                super(itemView);


                time = (TextView) itemView.findViewById(R.id.time);
                car = (TextView) itemView.findViewById(R.id.car);
                visitor = (TextView) itemView.findViewById(R.id.visitor);
                house = (TextView) itemView.findViewById(R.id.house);
                comment = (TextView) itemView.findViewById(R.id.comment);
                out = (ImageButton) itemView.findViewById(R.id.out);
                date = (TextView) itemView.findViewById(R.id.date);
                imageView = (CircleImageView) itemView.findViewById(R.id.image);
                imageView1 = (CircleImageView) itemView.findViewById(R.id.image2);
            }
        }
    }


}
