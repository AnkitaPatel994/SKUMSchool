package com.intelliworkz.skumschool.Admin.AdminAttendence;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pc-6 on 9/27/2017.
 */

class ViewAttendStudAdapter extends RecyclerView.Adapter<ViewAttendStudAdapter.ViewHolder> {

    public static String stdDiv,stdRollno;
    Context context;
    ArrayList<HashMap<String, String>> stuArrList;
    View v;

    public ViewAttendStudAdapter(Context context, ArrayList<HashMap<String, String>> stuArrList) {
        this.context=context;
        this.stuArrList=stuArrList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.stuattend_view,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String name=stuArrList.get(position).get("name");
        final String rollno=stuArrList.get(position).get("rollno");
        final String classStud=stuArrList.get(position).get("class");
        holder.stuAttendViewName.setText(name);
        holder.stuAttendViewRollno.setText(classStud+rollno);

        holder.layAttendViewstd.setOnClickListener(new View.OnClickListener() {
            boolean clicked = true;

            @Override
            public void onClick(View v) {
                if(!clicked)
                {
                    holder.layAttendViewstd.setBackgroundResource(R.drawable.card_green);
                    clicked=true;
                    //Toast.makeText(context,classStud,Toast.LENGTH_SHORT).show();
                    DeleteAttendance deleteAttendance=new DeleteAttendance(classStud,rollno);
                    deleteAttendance.execute();


                }
                else
                {
                    holder.layAttendViewstd.setBackgroundResource(R.drawable.card_red);
                    clicked=false;

                   /* InsertAttenedance insertAttenedance=new InsertAttenedance(classStud, rollno);
                    insertAttenedance.execute();*/
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return stuArrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stuAttendViewName,stuAttendViewRollno;
        LinearLayout layAttendViewstd;
        public ViewHolder(View itemView) {
            super(itemView);
            stuAttendViewName=(TextView)v.findViewById(R.id.stuAttendViewName);
            stuAttendViewRollno=(TextView)v.findViewById(R.id.stuAttendViewRollno);
            layAttendViewstd=(LinearLayout) v.findViewById(R.id.layAttendViewstd);
        }
    }

    private class InsertAttenedance extends AsyncTask<String,Void,String>{
        String status,message,s_stddiv,s_rollno;

        public InsertAttenedance(String classStud, String rollno) {

            s_stddiv = classStud;
            s_rollno = rollno;
        }


        @Override
        protected String doInBackground(String... params) {
            JSONObject AttendList=new JSONObject();
            try {

                AttendList.put("s_stddiv",s_stddiv);
                AttendList.put("s_rollno",s_rollno);

                Postdata postdata=new Postdata();
                String stdPd=postdata.post(MainActivity.mainUrl+"attendenceInsert",AttendList.toString());
                JSONObject j=new JSONObject(stdPd);
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
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class DeleteAttendance extends AsyncTask<String,Void,String>{

        String status,message,s_stddiv,s_rollno;

        public DeleteAttendance(String classStud, String rollno) {
            s_stddiv=classStud;
            s_rollno=rollno;
        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject AttendList=new JSONObject();
            try {

                AttendList.put("s_stddiv",s_stddiv);
                AttendList.put("s_rollno",s_rollno);

                Postdata postdata=new Postdata();
                String stdPd=postdata.post(MainActivity.mainUrl+"attendenceDelete",AttendList.toString());
                JSONObject j=new JSONObject(stdPd);
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
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
