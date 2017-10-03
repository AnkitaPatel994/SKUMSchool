package com.intelliworkz.skumschool.Admin.AdminNoticeBoard;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shyam group on 9/12/2017.
 */

public class NoticeBoardSwipeListAdapter extends RecyclerView.Adapter<NoticeBoardSwipeListAdapter.ViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> noticeBoardList;
    ViewBinderHelper binderHelper = new ViewBinderHelper();

    public NoticeBoardSwipeListAdapter(Context context, ArrayList<HashMap<String, String>> noticeBoardList) {
        this.context = context;
        this.noticeBoardList = noticeBoardList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noticeboard_view_swipe, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final String id = noticeBoardList.get(position).get("id");
        final String Title = noticeBoardList.get(position).get("title");
        final String Description = noticeBoardList.get(position).get("description");
        final String Date = noticeBoardList.get(position).get("date");

        holder.txtnbsTitle.setText(Title);

        holder.deleteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetNoticeBoardDelete getNoticeBoardDelete = new GetNoticeBoardDelete(id,holder,position);
                getNoticeBoardDelete.execute();
                //noticeBoardList.clear();

            }
        });

        holder.itemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context,android.R.style.Theme_Light_NoTitleBar);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog);

                TextView txtnbDTitle=(TextView)dialog.findViewById(R.id.txtnbDTitle);
                TextView txtnbDDate=(TextView)dialog.findViewById(R.id.txtnbDDate);
                TextView txtnbDDescription=(TextView)dialog.findViewById(R.id.txtnbDDescription);

                txtnbDTitle.setText(Title);
                txtnbDDate.setText(Date);
                txtnbDDescription.setText(Description);

                ImageView imgClose=(ImageView)dialog.findViewById(R.id.imgClose);

                imgClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return noticeBoardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtnbsTitle;/*,txtnbDescription,txtnbDate;*/
        private View deleteLayout,itemList;
        private SwipeRevealLayout swipeLayout;

        public ViewHolder(View v) {
            super(v);

            txtnbsTitle= (TextView)v.findViewById(R.id.txtnbsTitle);
            swipeLayout = (SwipeRevealLayout) itemView.findViewById(R.id.swipe_layout);
            deleteLayout = itemView.findViewById(R.id.delete_layout);
            itemList = itemView.findViewById(R.id.itemList);
            /*txtnbDescription= (TextView)v.findViewById(R.id.txtnbDescription);
            txtnbDate= (TextView)v.findViewById(R.id.txtnbDate);*/
        }
    }

    private class GetNoticeBoardDelete extends AsyncTask<String,Void,String> {

        String id,status,message;
        ViewHolder holder;
        int position;

        public GetNoticeBoardDelete(String id, ViewHolder holder, int position) {

            this.id = id;
            this.holder = holder;
            this.position = position;

        }

        @Override
        protected String doInBackground(String... params) {

            JSONObject nbDList=new JSONObject();

            try {

                nbDList.put("id",id);
                Postdata postdata=new Postdata();
                String nbDPd=postdata.post(MainActivity.mainUrl+"noticeBoardDelete",nbDList.toString());
                JSONObject j=new JSONObject(nbDPd);
                status=j.getString("status");
                if(status.equals("1"))
                {
                    Log.d("Like","Successfully");
                    message = j.getString("message");
                }
                else
                {
                    message = j.getString("message");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(status.equals("1"))
            {
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
                noticeBoardList.remove(position);
                notifyItemRemoved(position);

            }
            else
            {
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
