package com.intelliworkz.skumschool.Student.Calender;

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

class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> activityListArray;

    public EventAdapter(Context context, ArrayList<HashMap<String, String>> activityListArray) {
        this.context = context;
        this.activityListArray = activityListArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendar_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String Day = activityListArray.get(position).get("activity_day");
        String Title = activityListArray.get(position).get("title");
        String Description = activityListArray.get(position).get("description");
        holder.txtDay.setText(Day);
        holder.txtTitle.setText(Title);
        holder.txtDescription.setText(Description);
    }

    @Override
    public int getItemCount() {
        return activityListArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtDay,txtTitle,txtDescription;
        public ViewHolder(View v) {
            super(v);
            txtDay= (TextView)v.findViewById(R.id.txtDay);
            txtTitle= (TextView)v.findViewById(R.id.txtTitle);
            txtDescription= (TextView)v.findViewById(R.id.txtDescription);
        }
    }
}
