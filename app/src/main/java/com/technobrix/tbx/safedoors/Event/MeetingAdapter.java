package com.technobrix.tbx.safedoors.Event;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.GetAllMeetingPOJO.MeetingList;
import com.technobrix.tbx.safedoors.R;

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

/**
 * Created by tvs on 10/31/2017.
 */

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MyViewHolder> {


    Context context;

    List<MeetingList>list = new ArrayList<>();

    public MeetingAdapter(Context context ,  List<MeetingList>list){

        this.context = context;
        this.list = list;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.mee_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final MeetingList item = list.get(position);

        //holder.s.setText(String.valueOf(position + 1));
        holder.d.setText(item.getMeetingDate());
        holder.t.setText(item.getTitle());
        holder.time.setText(item.getMeetingTime());

       holder.image.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               Retrofit retrofit = new Retrofit.Builder()
                       .baseUrl("https://onnway.com/")
                       .build();

               AllApiInterface cr = retrofit.create(AllApiInterface.class);

               cr.getFile(item.getMeetingAttachment()).enqueue(new Callback<ResponseBody>() {
                   @Override
                   public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                       try {

                           String fn = item.getMeetingAttachment();

                           String[] dd = fn.split("/");



                           DownloadFileAsyncTask downloadFileAsyncTask = new DownloadFileAsyncTask(item.getMeetingAttachment(), dd[dd.length - 1]);
                           downloadFileAsyncTask.execute(response.body().byteStream());

                       }catch (Exception e)
                       {

                           e.printStackTrace();
                       }

                   }

                   @Override
                   public void onFailure(Call<ResponseBody> call, Throwable t) {



                   }

               });




           }
       });


    }

    public void setgrid(List<MeetingList>list){


        this.list = list;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView s , d , t , time;

        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

           // s = (TextView)itemView.findViewById(R.id.one);
            d = (TextView)itemView.findViewById(R.id.date);
            t = (TextView)itemView.findViewById(R.id.title);
            time = (TextView)itemView.findViewById(R.id.time);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }


    private class DownloadFileAsyncTask extends AsyncTask<InputStream, Void, Boolean> {

        String TAG = "asdasdasd";



        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+ "/SafeDoors/");



        final String filename;

        String key;

        String path = Environment.getExternalStorageDirectory().toString();

        byte[] decodedBytes = null;

        File file;

        public DownloadFileAsyncTask(String key , String name)
        {
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
                file = new File(dir , filename);

                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
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
                    }
                    else{
                        Log.d(TAG, "Output stream is null");
                    }
                } catch (IOException e){
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


            Toast.makeText(context , "Successfully Downloaded in Downloads/SafeDoors" , Toast.LENGTH_SHORT).show();

        }
    }


}
