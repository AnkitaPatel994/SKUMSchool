package com.intelliworkz.skumschool.Admin.AdminNoticeBoard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intelliworkz.skumschool.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shyam group on 9/12/2017.
 */

public class NoticeBoardListAdapter extends RecyclerView.Adapter<NoticeBoardListAdapter.ViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> noticeBoardList;

    public NoticeBoardListAdapter(Context context, ArrayList<HashMap<String, String>> noticeBoardList) {
        this.context = context;
        this.noticeBoardList = noticeBoardList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noticeboard_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String Title = noticeBoardList.get(position).get("title");
        String Description = noticeBoardList.get(position).get("description");
        String Date = noticeBoardList.get(position).get("date");

        holder.txtnbTitle.setText(Title);
        holder.txtnbDescription.setText(Description);
        holder.txtnbDate.setText(Date);
    }

    @Override
    public int getItemCount() {
        return noticeBoardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtnbTitle,txtnbDescription,txtnbDate;
        public ViewHolder(View v) {
            super(v);

            txtnbTitle= (TextView)v.findViewById(R.id.txtnbTitle);
            txtnbDescription= (TextView)v.findViewById(R.id.txtnbDescription);
            txtnbDate= (TextView)v.findViewById(R.id.txtnbDate);
        }
    }
}
