package com.technobrix.tbx.safedoors;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.technobrix.tbx.safedoors.Acc.Accounting;
import com.technobrix.tbx.safedoors.Event.Event;
import com.technobrix.tbx.safedoors.GetImagePOJO.GetBean;
import com.technobrix.tbx.safedoors.GetProfilePOJO.GetProfileBean;
import com.technobrix.tbx.safedoors.Desscusion.DisscusionForm;
import com.technobrix.tbx.safedoors.Inventory_List.Inventory;
import com.technobrix.tbx.safedoors.NoticeBoard.NoticeBoard1;
import com.technobrix.tbx.safedoors.Profile.Profile;
import com.technobrix.tbx.safedoors.SplashLogin.Login;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawer;
    TextView accounting, notice, event, keeper, meeting, developer, inventory, helpdisk, profile, discuss, logout, facility, help, booking, feedback;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    ImageView back;
    CircleImageView pro;

    TextView owner, name, house, socity;

    ConnectionDetector cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cd = new ConnectionDetector(MainActivity.this);

        pref = getSharedPreferences("pref", MODE_PRIVATE);
        edit = pref.edit();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        accounting = (TextView) findViewById(R.id.accounting);
        notice = (TextView) findViewById(R.id.notice);
        event = (TextView) findViewById(R.id.event);
        // meeting = (TextView) findViewById(R.id.meeting);
        inventory = (TextView) findViewById(R.id.inventor);
        // help = (TextView) findViewById(R.id.helpp);
        helpdisk = (TextView) findViewById(R.id.help);
        profile = (TextView) findViewById(R.id.profiled);
        discuss = (TextView) findViewById(R.id.discussion);
        facility = (TextView) findViewById(R.id.facility);
        booking = (TextView) findViewById(R.id.booking);
        logout = (TextView) findViewById(R.id.logout);
        feedback = (TextView) findViewById(R.id.feedback);
        keeper = (TextView) findViewById(R.id.keeper);
        developer = (TextView) findViewById(R.id.developer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);

        pro = (CircleImageView) findViewById(R.id.pro);
        back = (ImageView) findViewById(R.id.back);

        name = (TextView) findViewById(R.id.name);
        house = (TextView) findViewById(R.id.house);
        owner = (TextView) findViewById(R.id.owner);
        socity = (TextView) findViewById(R.id.socity);

        drawer = (DrawerLayout) findViewById(R.id.activity_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        loadImage();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Profile fragment = new Profile();
        ft.replace(R.id.replace, fragment);
        // ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.commit();
        drawer.closeDrawer(GravityCompat.START);


        toolbar.setTitle("Profile");


        if (cd.isConnectingToInternet()) {
            bean b = (bean) getApplicationContext();

            name.setText("Username - " + b.name);

            house.setText("Flat No. - " + b.flat);
            owner.setText("Full Name - " + b.owner_name);
            socity.setText("Society Name - " + b.society_name);
            Log.d("owner", b.owner_name);

            //bar.setVisibility(View.VISIBLE);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);
            Call<GetProfileBean> call = cr.getprofile(b.userId);

            call.enqueue(new Callback<GetProfileBean>() {
                @Override
                public void onResponse(Call<GetProfileBean> call, Response<GetProfileBean> response) {


                }

                @Override
                public void onFailure(Call<GetProfileBean> call, Throwable t) {

                    Log.d("mmmm", t.toString());

                }
            });


        } else {

            Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


        accounting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                Accounting fragment = new Accounting();
                ft.replace(R.id.replace, fragment);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();

                drawer.closeDrawer(GravityCompat.START);

                toolbar.setTitle("Accounting");

            }
        });


        facility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                Facility fragment = new Facility();
                ft.replace(R.id.replace, fragment);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

                toolbar.setTitle("Facility Booking");

            }
        });


        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                BookingTabs fragment = new BookingTabs();
                ft.replace(R.id.replace, fragment);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

                toolbar.setTitle("My Booking");

            }
        });


        keeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                GateRegularVisitor fragment = new GateRegularVisitor();
                ft.replace(R.id.replace, fragment);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

                toolbar.setTitle("Regular Visitor");

            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Event fragment = new Event();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                ft.replace(R.id.replace, fragment);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

                toolbar.setTitle("Event");

            }
        });


        helpdisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Help fragment = new Help();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                ft.replace(R.id.replace, fragment);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

                toolbar.setTitle("HelpDesk");

            }
        });

        discuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                DisscusionForm fragment = new DisscusionForm();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                ft.replace(R.id.replace, fragment);
                // ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

                toolbar.setTitle("Discussion Form");

            }
        });

        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Inventory fragment = new Inventory();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                ft.replace(R.id.replace, fragment);
                // ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();

                drawer.closeDrawer(GravityCompat.START);

                toolbar.setTitle("Inventory");

            }
        });
       /* meeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Meeting fragment = new Meeting();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                ft.replace(R.id.replace, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

                toolbar.setTitle("Meeting Arrangement");

            }
        });*/
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Profile fragment = new Profile();
                ft.replace(R.id.replace, fragment);
                // ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);


                ft.commit();
                drawer.closeDrawer(GravityCompat.START);


                toolbar.setTitle("Profile");

            }
        });


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Profile fragment = new Profile();
                ft.replace(R.id.replace, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();*/


                Intent i = new Intent(MainActivity.this, Feedback.class);
                startActivity(i);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Profile fragment = new Profile();
                ft.replace(R.id.replace, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();*/


                Intent i = new Intent(MainActivity.this, DeveloperFeedback.class);
                startActivity(i);
                drawer.closeDrawer(GravityCompat.START);

            }
        });


        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                NoticeBoard1 fragment = new NoticeBoard1();
                while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStackImmediate();
                }
                ft.replace(R.id.replace, fragment);
                // ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

                toolbar.setTitle("Notice Board");

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(MainActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.closlog);
                dialog.setCancelable(true);
                dialog.show();

                Button yes = dialog.findViewById(R.id.yes);
                Button no = dialog.findViewById(R.id.no);

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                    }
                });


                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent i = new Intent(MainActivity.this, Login.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);


                        edit.remove("user");
                        edit.remove("type");
                        edit.remove("pass");
                        edit.apply();

                        bean b = (bean) getApplicationContext();

                        b.name = "";
                        b.userId = "";
                        b.email = "";

                        startActivity(i);
                        finish();


                    }
                });


            }
        });


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

            Intent i = new Intent(MainActivity.this, Notification.class);
            startActivity(i);

        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {


            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStackImmediate();


            } else {

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.popup);
                dialog.setCancelable(true);
                dialog.show();

                Button yes = dialog.findViewById(R.id.yes);
                Button no = dialog.findViewById(R.id.no);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        finish();
                    }
                });


                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                    }
                });


                Log.d("nisha", "nisha");
            }


        }

    }


    @Override
    protected void onResume() {
        super.onResume();

        loadImage();

    }

    public void loadImage() {


        bean b = (bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<GetBean> call = cr.get(b.userId);

        call.enqueue(new Callback<GetBean>() {
            @Override
            public void onResponse(Call<GetBean> call, Response<GetBean> response) {

                Log.d("asdasd", "Asdasd");

                DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                        .cacheOnDisc(true).resetViewBeforeLoading(false).build();
                ImageLoader loader = ImageLoader.getInstance();
                loader.displayImage(response.body().getBackgroundImage(), back, options);
                loader.displayImage(response.body().getProfileImage(), pro, options);

            }

            @Override
            public void onFailure(Call<GetBean> call, Throwable t) {


            }
        });
    }

}
