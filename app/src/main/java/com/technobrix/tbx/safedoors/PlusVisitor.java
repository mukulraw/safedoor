package com.technobrix.tbx.safedoors;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllMemeberPOJO.MemberBean;
import com.technobrix.tbx.safedoors.visitorListPOJO.VisitorList;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PlusVisitor extends AppCompatActivity {

    private static final int TAKE_PICTURE = 1;
    private static final int TAKE_PICTURE1 = 2;

    static final int RC_TAKE_PHOTO = 1;
    static final int RC_TAKE_PHOTO1= 2;
    private Uri imageUri;

    EditText name , car;

    SearchableSpinner member;
    Spinner purpose;

    List<String> memberName, purpousname;
    List<String> memberId, purposeid;
    List<String> houseId;
    List<VisitorList> list;

    String mCurrentPhotoPath = "";
    String membId = "";
    String houId = "";
    String pur = "";

    ImageView image, log , image1;

    File file, file1;
    Uri fileUri , fileUri1;

    Button submit;

    TextView cap , cap1;

    Toolbar toolbar;

    EditText comment;

    ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);


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

        toolbar.setTitle("Add");


        list = new ArrayList<>();

        memberName = new ArrayList<>();
        memberId = new ArrayList<>();
        houseId = new ArrayList<>();
        purpousname = new ArrayList<>();
        purposeid = new ArrayList<>();

        name = (EditText) findViewById(R.id.name);
        car = (EditText) findViewById(R.id.car);


        member = (SearchableSpinner) findViewById(R.id.spinner);
        purpose = (Spinner) findViewById(R.id.purpose);

        image = (ImageView) findViewById(R.id.image);
        image1 = (ImageView) findViewById(R.id.image1);

        cap = (TextView) findViewById(R.id.capture);
        cap1 = (TextView) findViewById(R.id.capture1);

        submit = (Button) findViewById(R.id.submit);

        progress = (ProgressBar) findViewById(R.id.progress);

        log = (ImageView) findViewById(R.id.log);


        cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file = new File(getExternalCacheDir(),
                        String.valueOf(System.currentTimeMillis()) + ".jpg");
                //fileUri = Uri.fromFile(file);
                fileUri = FileProvider.getUriForFile(
                        PlusVisitor.this,
                        PlusVisitor.this.getApplicationContext()
                                .getPackageName() + ".provider", file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, RC_TAKE_PHOTO);


            }
        });





        cap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                file1 = new File(getExternalCacheDir(),
                        String.valueOf(System.currentTimeMillis()) + ".jpg");
                //fileUri = Uri.fromFile(file);
                fileUri1 = FileProvider.getUriForFile(
                        PlusVisitor.this,
                        PlusVisitor.this.getApplicationContext()
                                .getPackageName() + ".provider", file1);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri1);
                startActivityForResult(intent, RC_TAKE_PHOTO1);


            }
        });



        progress.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bean b = (bean) getApplicationContext();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<MemberBean> call = cr.member(b.socity);

        call.enqueue(new Callback<MemberBean>() {
            @Override
            public void onResponse(Call<MemberBean> call, Response<MemberBean> response) {



                memberName.clear();
                memberId.clear();
                houseId.clear();
                purposeid.clear();

                memberName.add("Select House No.");

                Log.d("size", String.valueOf(response.body().getMemberList().size()));

                for (int i = 0; i < response.body().getMemberList().size(); i++) {

//                    Log.d("data" , response.body().getMemberList().get(i).getHouseName());


                    if (response.body().getMemberList().get(i).getHouseName() != null) {
                        memberName.add(response.body().getMemberList().get(i).getHouseName());
                        memberId.add(response.body().getMemberList().get(i).getMemberId());
                        houseId.add(response.body().getMemberList().get(i).getHouseNo());
                    }


                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlusVisitor.this, android.R.layout.simple_list_item_1, memberName);

                progress.setVisibility(View.GONE);

                member.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<MemberBean> call, Throwable t) {

                progress.setVisibility(View.GONE);

            }
        });


        member.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i>0){

                    membId = memberId.get(i-1);
                    houId = houseId.get(i-1);
                }

                else {

                    membId = "";
                    houId = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        purpousname.add("Event");
        purpousname.add("Singing");
        purpousname.add("Meeting");
        purpousname.add("Party");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlusVisitor.this, android.R.layout.simple_list_item_1, purpousname);

        progress.setVisibility(View.GONE);

        purpose.setAdapter(adapter);


        purpose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                pur = purpousname.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String n = name.getText().toString();
                final String c = car.getText().toString();
                String p = pur;




                Calendar cal = Calendar.getInstance();


                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                Date todayDate = new Date();
                todayDate.getDay();
                todayDate.getHours();
                todayDate.getMinutes();
                todayDate.getMonth();
                todayDate.getTime();


                if (houId.length()>0){

                    if (n.length() > 0) {

                        if (p.length() > 0) {

                            if (file != null) {
                                MultipartBody.Part body = null;

                                //File file = new File(mCurrentPhotoPath);


                                RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                                body = MultipartBody.Part.createFormData("visitor_pic", file.getName(), reqFile);


                                String date = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);

                                String time = "" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);


                                MultipartBody.Part body1 = null;

                                RequestBody reqFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);

                                body1 = MultipartBody.Part.createFormData("visitor_pic1", file1.getName(), reqFile1);


                                String date1 = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);

                                String time1 = "" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);




                                progress.setVisibility(View.VISIBLE);


                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://safedoors.in")
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                bean b = (bean) getApplicationContext();

                                AllApiInterface cr = retrofit.create(AllApiInterface.class);
                                Call<entryBean> call = cr.regularNew(b.socity, pur, b.userId, "New", n, houId, body , body1 , c);


                                call.enqueue(new Callback<entryBean>() {
                                    @Override
                                    public void onResponse(Call<entryBean> call, Response<entryBean> response) {

                                        Toast.makeText(PlusVisitor.this, "Visitor is In", Toast.LENGTH_SHORT).show();


                                        name.setText("");
                                        car.setText("");


                                        member.setSelection(0);

                                        file = null;
                                        file1 = null;
                                        fileUri = null;
                                        fileUri1 = null;

                                        image.setImageBitmap(null);
                                        image1.setImageBitmap(null);

                                        //loadData();

                                        progress.setVisibility(View.GONE);
                                        finish();

                                    }

                                    @Override
                                    public void onFailure(Call<entryBean> call, Throwable t) {

                                        progress.setVisibility(View.GONE);

                                    }
                                });
                            } else {

                                Toast.makeText(PlusVisitor.this, "Please capture a Pic", Toast.LENGTH_SHORT).show();

                            }


                        } else {
                            Toast.makeText(PlusVisitor.this, "Please enter a valid Purpose", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(PlusVisitor.this, "Please enter a valid Name", Toast.LENGTH_SHORT).show();
                    }


                }
                else {
                    Toast.makeText(PlusVisitor.this, "Please choose a House No.", Toast.LENGTH_SHORT).show();
                }



            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        //loadData();
    }

   /* public void loadData() {

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

                Log.d("asdasdSize" , String.valueOf(response.body().getVisitorList().size()));

                for (int i = 0; i < response.body().getVisitorList().size(); i++)
                {
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

*/


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_TAKE_PHOTO && resultCode == RESULT_OK) {


            //mCurrentPhotoPath = getPath(GateKeeper.this , fileUri);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);

                image.setImageBitmap(bitmap);



            } catch (IOException e) {
                e.printStackTrace();
            }

            //Log.d("asdasd" , String.valueOf(fileUri));


        }

        else if (requestCode ==RC_TAKE_PHOTO1 && resultCode == RESULT_OK){

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri1);


                image1.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static String getPath(final Context context, final Uri uri) {
        final boolean isKitKatOrAbove = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isKitKatOrAbove && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                }
                // DownloadsProvider
                else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                }
                // MediaProvider
                else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[]{
                            split[1]
                    };

                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }

        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.notification)
        {

            Intent i = new Intent(PlusVisitor . this , Notification.class);
            startActivity(i);

        }


        return super.onOptionsItemSelected(item);
    }*/


}
