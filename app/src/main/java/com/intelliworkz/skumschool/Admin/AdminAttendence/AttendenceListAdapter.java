package com.intelliworkz.skumschool.Admin.AdminAttendence;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
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

public class AttendenceListAdapter extends RecyclerView.Adapter<AttendenceListAdapter.ViewHolder> {

    Context context;
    View v;
    ArrayList<String> stdAttendArrList;

    public AttendenceListAdapter(Context context, ArrayList<String> stdAttendArrList) {
        this.context = context;
        this.stdAttendArrList = stdAttendArrList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendence_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final String AttClass = stdAttendArrList.get(position);
        holder.txtAttClass.setText("Standard " + AttClass);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,AdminAttendenceActivity.class);
                i.putExtra("pos",AttClass);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return stdAttendArrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtAttClass;

        public ViewHolder(View v) {
            super(v);

            txtAttClass= (TextView)v.findViewById(R.id.txtAttClass);

        }
    }
    public void filterList(ArrayList<String> stdAttendArrList) {
        this.stdAttendArrList = stdAttendArrList;
        notifyDataSetChanged();
    }
}
