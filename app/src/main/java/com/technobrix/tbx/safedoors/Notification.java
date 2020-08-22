package com.technobrix.tbx.safedoors;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Notification extends AppCompatActivity {

    RecyclerView recyclerView;

    GridLayoutManager manager;

    NotiAdapter adapter;

    List<BeNotify> list;

    ProgressBar bar;

    Toolbar toolbar;

    RequestQueue requestQueue;

    ConnectionDetector cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        cd = new ConnectionDetector(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(R.drawable.arrowleft);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        manager = new GridLayoutManager(getApplication(), 1);

        list = new ArrayList<>();
        adapter = new NotiAdapter(this, list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        bar = (ProgressBar) findViewById(R.id.progress);


        if (cd.isConnectingToInternet()){

            RequestQueue requestQueue = Volley.newRequestQueue(this);

            StringRequest strreq = new StringRequest(Request.Method.POST,
                    "http://safedoors.in/app_api/get_all_view_notification.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            try {
                                JSONObject obj = new JSONObject(response);

                                JSONArray jsonArray = obj.getJSONArray("notification_list");

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    BeNotify bean = new BeNotify();

                                    String type = jsonObject.getString("type");
                                    String id = jsonObject.getString("id");


                                    //Log.d("type", type);


                                    switch (type) {
                                        case "notice_board":

                                            bean.setType(type);
                                            bean.setTitle("New Notice Added");
                                            list.add(bean);

                                            break;
                                        case "meeting_arrange":


                                            bean.setType(type);

                                            JSONObject data = jsonObject.getJSONObject("notify_data");

                                            String title = data.getString("title");
                                            String desc = data.getString("description");
                                            String fDate = data.getString("from_date");
                                            String fTime = data.getString("from_time");

                                            bean.setTitle(title);
                                            bean.setDescription(desc);
                                            bean.setStartdate(fDate);
                                            bean.setStarttime(fTime);

                                            list.add(bean);

                                            break;
                                        case "event_arrange":

                                            bean.setType(type);

                                            JSONObject data1 = jsonObject.getJSONObject("notify_data");

                                            String title1 = data1.getString("title");
                                            String desc1 = data1.getString("description");
                                            String fDate1 = data1.getString("from_date");
                                            String fTime1 = data1.getString("from_time");
                                            String tDate = data1.getString("to_date");
                                            String tTime = data1.getString("to_time");

                                            bean.setTitle(title1);
                                            bean.setDescription(desc1);
                                            bean.setStartdate(fDate1);
                                            bean.setStarttime(fTime1);
                                            bean.setEnddate(tDate);
                                            bean.setEndtime(tTime);

                                            list.add(bean);

                                            break;
                                        case "visitor_out":

                                            bean.setType(type);

                                            JSONObject data2 = jsonObject.getJSONObject("notify_data");

                                            String vType = data2.getString("visitor_type");

                                            String title2 = "";

                                            String pic = "";
                                            String pic1 = "";

                                            if (Objects.equals(vType, "Regular"))
                                            {
                                                String n = data2.getString("staff_name");

                                                title2 = n + " left";

                                                pic = "";
                                                pic1 = "";
                                            }
                                            else if (Objects.equals(vType, "New"))
                                            {
                                                String n = data2.getString("visitor_name");

                                                title2 = n + " left";

                                                String p = data2.getString("pic");
                                                String p1 = data2.getString("pic1");

                                                Log.d("p" ,p1);

                                                pic = p;
                                                pic1 = p1;
                                            }



                                            String desc2 = data2.getString("comment");
                                            //String fDate2 = data2.getString("from_date");
                                            //String fTime2 = data2.getString("from_time");
                                            String tTime2 = data2.getString("outtime");

                                            bean.setTitle(title2);
                                            bean.setImage(pic);
                                            bean.setImage1(pic1);

                                            //Log.d("pic" , pic);
                                            bean.setDescription(desc2);
                                            bean.setStarttime(tTime2);


                                            list.add(bean);

                                            break;
                                        case "visitor_entry":

                                            bean.setType(type);

                                            JSONObject data3 = jsonObject.getJSONObject("notify_data");

                                            String vType2 = data3.getString("visitor_type");

                                            String title3 = "";

                                            String pic2 = "";
                                            String pic3 = "";

                                            if (Objects.equals(vType2, "Regular"))
                                            {
                                                String n = data3.getString("staff_name");

                                                title3 = n + " entered";

                                                pic2 = "";
                                                pic3 = "";
                                            }
                                            else if (Objects.equals(vType2, "New"))
                                            {
                                                String n = data3.getString("visitor_name");

                                                title3 = n + " entered";

                                                String p = data3.getString("pic");
                                                String p2 = data3.getString("pic1");

                                                Log.d("p2" , p2);

                                                pic2 = p;
                                                pic3 = p2;
                                            }



                                            String desc3 = data3.getString("comment");
                                            //String fDate3 = data3.getString("from_date");
                                            //String fTime3 = data3.getString("from_time");
                                            String tTime3 = data3.getString("intime");

                                            bean.setTitle(title3);
                                            bean.setImage(pic2);
                                            bean.setImage1(pic3);
                                            bean.setDescription(desc3);
                                            bean.setStarttime(tTime3);
                                            //Log.d("pic" , pic2);

                                            list.add(bean);

                                            break;

                                        case "help_desk":
                                            bean.setType(type);
                                            bean.setTitle("New Helpline Number Added");
                                            list.add(bean);


                                            break;

                                        case "facility_book":

                                            bean.setType(type);
                                            bean.setTitle("New Facility Added");
                                            list.add(bean);


                                            break;

                                        default:
                                            break;
                                    }


                                    bar.setVisibility(View.GONE);
                                }

                                adapter.Setgrid(list);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Log.d("asdasd", response);

                            adapter.notifyDataSetChanged();

                            // get response
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError e) {

                    e.printStackTrace();

                    bar.setVisibility(View.GONE);
                }

            }) {
                @Override
                public Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();

                    bean b = (bean) getApplicationContext();
                    params.put("socity_id", b.socity);
                    params.put("house_id", b.house_id);

                    Log.d("house" , b.house_id);
                    return params;
                }
            };
            requestQueue.add(strreq);
            //Volley.getInstance().addToRequestQueue(strreq);







        }else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        bar.setVisibility(View.VISIBLE);






        /*RequestQueue requestQueue= Volley.newRequestQueue(this);
        //  Create json array request
        StringRequest stringRequest=new StringRequest(Request.Method.POST,"http://safedoors.in/app_api/get_all_view_notification.php",new Response.Listener<String>(){
            public void onResponse(String response){
                // Successfully download json
                // So parse it and populate the listview

                try {
                    JSONObject obj = new JSONObject(response);

                    JSONArray jsonArray = obj.getJSONArray("notification_list");

                    for (int i = 0 ; i < jsonArray.length() ; i++)
                    {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String type = jsonObject.getString("type");

                        Log.d("type" , type);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("asdasd" , response);

                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Error", "Unable to parse json array");
            }
        });
        // add json array request to the request queue
        requestQueue.add(stringRequest);
    }
*/


    }


    @Override
    protected void onResume() {
        super.onResume();

        toolbar.setTitle("NOTIFICATION");
    }
}
