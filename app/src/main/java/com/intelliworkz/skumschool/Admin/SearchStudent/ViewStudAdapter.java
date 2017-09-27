package com.intelliworkz.skumschool.Admin.SearchStudent;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.intelliworkz.skumschool.Admin.AdminAddStudent.AddStudentActivity;
import com.intelliworkz.skumschool.Admin.AdminAttendence.AdminAttendenceActivity;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.Student.Profile.ProfileActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pc-6 on 9/26/2017.
 */

class ViewStudAdapter extends RecyclerView.Adapter<ViewStudAdapter.ViewHolder> {
    Context context;
    ArrayList<HashMap<String, String>> stuArrList;
    View v;

    public ViewStudAdapter(Context context, ArrayList<HashMap<String, String>> stuArrList) {
        this.context=context;
        this.stuArrList=stuArrList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      /*  v= LayoutInflater.from(parent.getContext()).inflate(R.layout.newslist,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;*/
      v= LayoutInflater.from(parent.getContext()).inflate(R.layout.stud_view,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name=stuArrList.get(position).get("name");
        final String id=stuArrList.get(position).get("id");
        String rollno=stuArrList.get(position).get("rollno");
        String classStud=stuArrList.get(position).get("class");
        holder.stuViewName.setText(name);
        holder.stuViewRollno.setText(classStud+rollno);
        holder.layViewstd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, StudentDetailActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("id",id);
                context.startActivity(i);
              // Toast.makeText(context,"id"+id,Toast.LENGTH_SHORT).show();
            }
        });
   }

    @Override
    public int getItemCount() {
        return stuArrList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stuViewName,stuViewRollno;
        LinearLayout layViewstd;

        public ViewHolder(View v) {
            super(v);
            stuViewName=(TextView)v.findViewById(R.id.stuViewName);
            stuViewRollno=(TextView)v.findViewById(R.id.stuViewRollno);
            layViewstd=(LinearLayout) v.findViewById(R.id.layViewstd);

        }
    }
}
