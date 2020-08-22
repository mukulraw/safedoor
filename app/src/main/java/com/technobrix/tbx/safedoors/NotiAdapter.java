package com.technobrix.tbx.safedoors;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.MyViewHolder> {

    Context context;
    List<BeNotify> list = new ArrayList<>();


    public NotiAdapter(Context context, List<BeNotify> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public NotiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notify_list_model, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final NotiAdapter.MyViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        final Bitmap[] bmp = new Bitmap[1];

        final BeNotify item = list.get(position);

        String type = item.getType();

        switch (type) {
            case "notice_board":

                holder.type.setVisibility(View.VISIBLE);
                holder.title.setVisibility(View.VISIBLE);
                holder.date.setVisibility(View.GONE);
                holder.des.setVisibility(View.GONE);
                //holder.image.setVisibility(View.GONE);
                holder.time.setVisibility(View.GONE);
                holder.line.setVisibility(View.GONE);
                holder.to.setVisibility(View.GONE);
                holder.to1.setVisibility(View.GONE);

                holder.type.setText("NOTICE BOARD");
                holder.title.setText(item.getTitle());

                break;
            case "meeting_arrange":

                holder.type.setVisibility(View.VISIBLE);
                holder.title.setVisibility(View.VISIBLE);
                holder.des.setVisibility(View.VISIBLE);
                //holder.image.setVisibility(View.GONE);
                holder.out.setVisibility(View.GONE);
                holder.in.setVisibility(View.VISIBLE);
                //holder.date.setVisibility(View.GONE);
                holder.date1.setVisibility(View.VISIBLE);
                holder.date2.setVisibility(View.GONE);
                holder.line.setVisibility(View.GONE);
                holder.to1.setVisibility(View.GONE);
                holder.to.setVisibility(View.GONE);


                holder.type.setText("NEW MEETING ADDED");
                holder.title.setText(item.getTitle());
                holder.des.setText(item.getDescription());
                holder.date1.setText(item.getStartdate());
                holder.in.setText(item.getStarttime());


                break;
            case "event_arrange":


                holder.type.setVisibility(View.VISIBLE);
                holder.title.setVisibility(View.VISIBLE);
                holder.des.setVisibility(View.VISIBLE);
                //holder.image.setVisibility(View.GONE);
                holder.date.setVisibility(View.VISIBLE);
                holder.time.setVisibility(View.VISIBLE);
                holder.to1.setVisibility(View.VISIBLE);
                holder.to.setVisibility(View.VISIBLE);

                holder.type.setText("NEW EVENT ADDED");
                holder.title.setText(item.getTitle());
                holder.des.setText(item.getDescription());
                holder.in.setText(item.getStarttime());
                holder.out.setText(item.getEndtime());
                holder.date1.setText(item.getStartdate());
                holder.date2.setText(item.getEnddate());


                break;
            case "visitor_out":


                holder.type.setVisibility(View.VISIBLE);
                holder.title.setVisibility(View.VISIBLE);
                holder.in.setVisibility(View.VISIBLE);
                holder.out.setVisibility(View.GONE);
                holder.date2.setVisibility(View.GONE);
                holder.date1.setVisibility(View.VISIBLE);
                holder.des.setVisibility(View.VISIBLE);
                //holder.image.setVisibility(View.VISIBLE);
                holder.to.setVisibility(View.GONE);
                holder.to1.setVisibility(View.GONE);


                holder.type.setText("GATEKEEPER");
                holder.title.setText(item.getTitle());
                //holder.in.setText(item.getStarttime());
                if (item.getDescription().length() > 0) {
                    holder.des.setVisibility(View.VISIBLE);
                    holder.line.setVisibility(View.VISIBLE);
                    holder.des.setText(item.getDescription());
                } else {
                    holder.des.setVisibility(View.GONE);
                    holder.line.setVisibility(View.GONE);
                }
                Log.d("liklikjj", item.getImage());


                String tt = item.getStarttime();

                try {
                    String[] dd = tt.split(" ");

                    holder.in.setText(dd[1] + " " + dd[2]);
                    //holder.out.setText(item.getEndtime());
                    holder.date1.setText(dd[0]);

                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {

                    Log.d("image", item.getImage());
                    Log.d("image1", item.getImage1());

                } catch (Exception e) {
                    e.printStackTrace();
                }


                final DisplayImageOptions imageOptions = new DisplayImageOptions.Builder().cacheInMemory(true)
                        .cacheOnDisk(true).resetViewBeforeLoading(false).build();

                final ImageLoader loader = ImageLoader.getInstance();

                loader.displayImage(item.getImage(), holder.image, imageOptions);


                holder.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.image_dialog);
                        dialog.setCancelable(true);
                        dialog.show();

                        ImageView im = dialog.findViewById(R.id.image);
                        loader.displayImage(item.getImage(), im, imageOptions);

                    }
                });




                final DisplayImageOptions imageOptions1 = new DisplayImageOptions.Builder().cacheInMemory(true)
                        .cacheOnDisk(true).resetViewBeforeLoading(false).build();

                final ImageLoader loader1 = ImageLoader.getInstance();

                loader1.displayImage(item.getImage1(), holder.image1, imageOptions1);


                holder.image1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.image_dialog);
                        dialog.setCancelable(true);
                        dialog.show();

                        ImageView im = dialog.findViewById(R.id.image);
                        loader1.displayImage(item.getImage1(), im, imageOptions1);

                    }
                });


                break;
            case "visitor_entry":


                holder.type.setVisibility(View.VISIBLE);
                holder.title.setVisibility(View.VISIBLE);
                holder.in.setVisibility(View.VISIBLE);
                holder.out.setVisibility(View.GONE);
                holder.date1.setVisibility(View.VISIBLE);
                holder.date2.setVisibility(View.GONE);
                holder.des.setVisibility(View.VISIBLE);
               // holder.image.setVisibility(View.VISIBLE);
                holder.to.setVisibility(View.GONE);
                holder.to1.setVisibility(View.GONE);


                holder.type.setText("GATEKEEPER");
                holder.title.setText(item.getTitle());
                //holder.in.setText(item.getStarttime());

                if (item.getDescription().length() > 0) {
                    holder.des.setVisibility(View.VISIBLE);
                    holder.line.setVisibility(View.VISIBLE);
                    holder.des.setText(item.getDescription());
                } else {
                    holder.des.setVisibility(View.GONE);
                    holder.line.setVisibility(View.GONE);
                }


                String tt1 = item.getStarttime();

                try {
                    String[] dd = tt1.split(" ");

                    holder.in.setText(dd[1] + " " + dd[2]);
                    Log.d("dd1", dd[1]);
                    //holder.out.setText(item.getEndtime());
                    holder.date1.setText(dd[0]);
                    Log.d("dd0", dd[0]);

                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {

                    Log.d("image", item.getImage());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //holder.in.setText(item.getStarttime());
                holder.out.setText(item.getEndtime());
                //holder.date1.setText(item.getStartdate());
                //holder.date2.setText(item.getEnddate());

                final DisplayImageOptions imageOptions3 = new DisplayImageOptions.Builder().cacheInMemory(true)
                        .cacheOnDisk(true).resetViewBeforeLoading(false).build();

                final ImageLoader loader3 = ImageLoader.getInstance();

                loader3.displayImage(item.getImage(), holder.image, imageOptions3);


                holder.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.image_dialog);
                        dialog.setCancelable(true);
                        dialog.show();

                        ImageView im = dialog.findViewById(R.id.image);
                        loader3.displayImage(item.getImage(), im, imageOptions3);

                    }
                });




                final DisplayImageOptions imageOptions14 = new DisplayImageOptions.Builder().cacheInMemory(true)
                        .cacheOnDisk(true).resetViewBeforeLoading(false).build();

                final ImageLoader loader14 = ImageLoader.getInstance();

                loader14.displayImage(item.getImage1(), holder.image1, imageOptions14);


                holder.image1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.image_dialog);
                        dialog.setCancelable(true);
                        dialog.show();

                        ImageView im = dialog.findViewById(R.id.image);
                        loader14.displayImage(item.getImage1(), im, imageOptions14);

                    }
                });



                break;

            case "help_desk":

                holder.type.setVisibility(View.VISIBLE);
                holder.title.setVisibility(View.VISIBLE);
                holder.date.setVisibility(View.GONE);
                holder.des.setVisibility(View.GONE);
                //holder.image.setVisibility(View.GONE);
                holder.time.setVisibility(View.GONE);
                holder.line.setVisibility(View.GONE);
                holder.to.setVisibility(View.GONE);
                holder.to1.setVisibility(View.GONE);

                holder.type.setText("HELP DESK");
                holder.title.setText(item.getTitle());


                break;

            case "facility_book":

                holder.type.setVisibility(View.VISIBLE);
                holder.title.setVisibility(View.VISIBLE);
                holder.date.setVisibility(View.GONE);
                holder.des.setVisibility(View.GONE);
                //holder.image.setVisibility(View.GONE);
                holder.time.setVisibility(View.GONE);
                holder.line.setVisibility(View.GONE);
                holder.to.setVisibility(View.GONE);
                holder.to1.setVisibility(View.GONE);

                holder.type.setText("FACILITY");
                holder.title.setText(item.getTitle());


                break;

            default:
                break;
        }


        /*holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.image_dialog);
                dialog.setCancelable(true);

                dialog.show();

                ImageView imageView = dialog.findViewById(R.id.image);

                imageView.setImageBitmap(bmp[0]);

                //dialog.dismiss();
            }
        });
*/

    }

    public void Setgrid(List<BeNotify> list) {

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, date1, date2, type, des, in, out, line, to, to1;
        CircleImageView image , image1;

        RelativeLayout date, time;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            date1 = (TextView) itemView.findViewById(R.id.date1);
            date2 = (TextView) itemView.findViewById(R.id.date2);
            in = (TextView) itemView.findViewById(R.id.in);
            out = (TextView) itemView.findViewById(R.id.out);
            type = (TextView) itemView.findViewById(R.id.type);
            line = (TextView) itemView.findViewById(R.id.line);
            image = (CircleImageView) itemView.findViewById(R.id.image);
            image1 = (CircleImageView) itemView.findViewById(R.id.image1);
            date = (RelativeLayout) itemView.findViewById(R.id.date);
            time = (RelativeLayout) itemView.findViewById(R.id.time);
            des = (TextView) itemView.findViewById(R.id.des);
            to1 = (TextView) itemView.findViewById(R.id.to1);
            to = (TextView) itemView.findViewById(R.id.to);
        }
    }
}
