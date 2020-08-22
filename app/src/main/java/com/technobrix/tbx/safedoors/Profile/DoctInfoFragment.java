package com.technobrix.tbx.safedoors.Profile;

import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AddFamilyPOJO.AddFamilyBean;
import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.ConnectionDetector;
import com.technobrix.tbx.safedoors.DocumentListPOJO.DoctorBean;
import com.technobrix.tbx.safedoors.DocumentListPOJO.DocumentList;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.addDocBean;
import com.technobrix.tbx.safedoors.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.app.Activity.RESULT_OK;


public class DoctInfoFragment extends Fragment {

    RecyclerView recyclerView;

    GridLayoutManager manager;

    DoctAdapter adapter;

    List<DocumentList> list;

    TextView attach;
    ProgressBar bar;

    Uri selectedImageUri;

    private final int PICK_IMAGE_REQUEST = 2;
    private final int PICK_IMAGE_REQUEST1 = 3;



    Dialog dialog;

    ConnectionDetector cd;

    FloatingActionButton add;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.docinfo, container, false);

        cd = new ConnectionDetector(getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        add = (FloatingActionButton) view.findViewById(R.id.add);

        manager = new GridLayoutManager(getContext(), 1);

        list = new ArrayList();

        adapter = new DoctAdapter(getContext(), list);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(manager);

        bar = (ProgressBar) view.findViewById(R.id.progress);

        bar.setVisibility(View.VISIBLE);

        Log.d("fjgsd", "response");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cd.isConnectingToInternet()) {
                    dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.adddocument_dialog);
                    dialog.setCancelable(true);
                    dialog.show();


                    final String[] t = new String[1];


                    Spinner title = (Spinner) dialog.findViewById(R.id.spinner);

                    final List<String> list = new ArrayList<String>();

                    list.add("Aadhar Card");
                    list.add("PAN Card");
                    list.add("Voter Card Id");
                    list.add("Passport");
                    list.add("Driving Licence");


                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_list_item_1, list);

                    title.setAdapter(dataAdapter);

                    title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            t[0] = list.get(i);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });


                    final EditText number = (EditText) dialog.findViewById(R.id.number);
                    attach = (TextView) dialog.findViewById(R.id.attach);
                    Button submit = (Button) dialog.findViewById(R.id.submit);


                    attach.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            intent.setType("*/*");
                            startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);
                            //dialog.dismiss();


                        }
                    });

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            String n = number.getText().toString();

                            String a = attach.getText().toString();

                            if (n.length() > 0) {

                                if (a.length() > 0) {


                                    MultipartBody.Part body = null;

                                    File file = new File(attach.getText().toString());

                                    RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);


                                    body = MultipartBody.Part.createFormData("document", file.getName(), reqFile);


                                    bar.setVisibility(View.VISIBLE);
                                    bean b = (bean) getContext().getApplicationContext();

                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl("http://safedoors.in")
                                            .addConverterFactory(ScalarsConverterFactory.create())
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    AllApiInterface cr = retrofit.create(AllApiInterface.class);
                                    Call<addDocBean> call = cr.addDoc(b.userId, t[0], b.house_id, b.socity, n, body);

                                    call.enqueue(new Callback<addDocBean>() {
                                        @Override
                                        public void onResponse(Call<addDocBean> call, Response<addDocBean> response) {


                                            dialog.dismiss();

                                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                            bar.setVisibility(View.GONE);
                                            //loadImage();

                                        }

                                        @Override
                                        public void onFailure(Call<addDocBean> call, Throwable t) {

                                            bar.setVisibility(View.GONE);

                                        }
                                    });


                                } else {
                                    Toast.makeText(getContext(), "Please Attached a File ", Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                Toast.makeText(getContext(), "Invalid Document Number", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });


                } else {
                    Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }


            }
        });

        if (cd.isConnectingToInternet()) {

            bar.setVisibility(View.VISIBLE);

            bean b = (bean) getContext().getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            Call<DoctorBean> call = cr.doc(b.house_id, b.socity);
            call.enqueue(new Callback<DoctorBean>() {
                @Override
                public void onResponse(Call<DoctorBean> call, Response<DoctorBean> response) {

                    adapter.set(response.body().getDocumentList());

                    bar.setVisibility(View.GONE);

                    Log.d("diya", String.valueOf(response.body().getDocumentList().size()));


                }

                @Override
                public void onFailure(Call<DoctorBean> call, Throwable t) {


                    bar.setVisibility(View.GONE);

                    Log.d("jiya", t.toString());

                }
            });


        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


        return view;
    }


    public class DoctAdapter extends RecyclerView.Adapter<DoctAdapter.MyViewHolder> {

        Context context;
        List<DocumentList> list = new ArrayList();

        public DoctAdapter(Context context, List<DocumentList> list) {

            this.context = context;
            this.list = list;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.doc_list_model, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            final DocumentList item = list.get(position);

            holder.aadhar.setText(item.getTitle());

            holder.number.setText(item.getDocumentNo());


            holder.cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (cd.isConnectingToInternet()) {

                        bar.setVisibility(View.VISIBLE);

                        bean b = (bean) context.getApplicationContext();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://safedoors.in")
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllApiInterface cr = retrofit.create(AllApiInterface.class);

                        Call<AddFamilyBean> call = cr.deleteDoc(b.house_id, item.getId(), b.socity);

                        call.enqueue(new Callback<AddFamilyBean>() {
                            @Override
                            public void onResponse(Call<AddFamilyBean> call, Response<AddFamilyBean> response) {


                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                bar.setVisibility(View.GONE);


                            }

                            @Override
                            public void onFailure(Call<AddFamilyBean> call, Throwable t) {


                                bar.setVisibility(View.GONE);
                            }
                        });


                    } else {
                        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }


                }
            });


            holder.number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (cd.isConnectingToInternet()) {

                        bar.setVisibility(View.VISIBLE);

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://safedoors.in/")
                                .build();

                        AllApiInterface cr = retrofit.create(AllApiInterface.class);


                        cr.getFile(item.getDocument()).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                try {

                                    String fn = item.getDocument();

                                    String[] dd = fn.split("/");


                                    Log.d("asdasd", item.getDocument());


                                    DownloadFileAsyncTask downloadFileAsyncTask = new DownloadFileAsyncTask(item.getDocument(), dd[dd.length - 1]);
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

        public void set(List<DocumentList> list) {

            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView aadhar, number;
            ImageButton cross;


            public MyViewHolder(View itemView) {
                super(itemView);

                aadhar = (TextView) itemView.findViewById(R.id.title);
                number = (TextView) itemView.findViewById(R.id.number);
                cross = (ImageButton) itemView.findViewById(R.id.delete);

            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();

            String mCurrentPhotoPath = getPath(getContext(), selectedImageUri);

            attach.setText(mCurrentPhotoPath);


        } else if (requestCode == PICK_IMAGE_REQUEST1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();


            MultipartBody.Part body = null;


            String mCurrentPhotoPath = getPath(getContext(), selectedImageUri);

            File file = new File(mCurrentPhotoPath);


            RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            body = MultipartBody.Part.createFormData("backimg", file.getName(), reqFile);


            //bar.setVisibility(View.VISIBLE);
           /* bean b = (bean)getContext().getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);
            Call<UpdateBean> call = cr.ground(b.userId , file.getName() , body);



            call.enqueue(new Callback<UpdateBean>() {
                @Override
                public void onResponse(Call<UpdateBean> call, Response<UpdateBean> response) {

                    bar.setVisibility(View.GONE);
                    loadImage();

                }

                @Override
                public void onFailure(Call<UpdateBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });*/
        } else {
            super.onActivityResult(requestCode, resultCode, data);
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
        final String column = "_data";
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
            Toast.makeText(getContext(), "Successfully Downloaded in Downloads/SafeDoors", Toast.LENGTH_SHORT).show();

        }
    }


}



