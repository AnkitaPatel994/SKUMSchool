package com.intelliworkz.skumschool.Admin.AdminRole;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.intelliworkz.skumschool.HttpHandler;
import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shyam group on 9/12/2017.
 */

public class RoleAdapter extends RecyclerView.Adapter<RoleAdapter.ViewHolder> {

    Context context;
    View v;
    ArrayList<HashMap<String, String>> roleUserListArray;
    ArrayList<String> roleArrId=new ArrayList<>();
    ArrayList<String> roleArrName=new ArrayList<>();
    String title,pos;

    public RoleAdapter(Context context, ArrayList<HashMap<String, String>> roleUserListArray, String title) {

        this.context = context;
        this.roleUserListArray = roleUserListArray;
        this.title = title;

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

        //final String id = roleUserListArray.get(position).get("id");
        final String username = roleUserListArray.get(position).get("username");
        String name = roleUserListArray.get(position).get("name");

        holder.txtAttClass.setText(name);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context,android.R.style.Theme_Light_NoTitleBar);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_role);

                final Spinner spRoleList = (Spinner)dialog.findViewById(R.id.spRoleList);
                Button btnRoleSave = (Button)dialog.findViewById(R.id.btnRoleSave);

                GetRole getRole = new GetRole(spRoleList);
                getRole.execute();

                spRoleList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        pos = roleArrId.get(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                btnRoleSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GetEditUser getEditUser = new GetEditUser(username);
                        getEditUser.execute();
                    }
                });

                ImageView imgCloseRole=(ImageView)dialog.findViewById(R.id.imgCloseRole);

                imgCloseRole.setOnClickListener(new View.OnClickListener() {
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
        return roleUserListArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtAttClass;

        public ViewHolder(View v) {
            super(v);

            txtAttClass= (TextView)v.findViewById(R.id.txtAttClass);

        }
    }

    private class GetRole extends AsyncTask<String,Void,String> {

        Spinner spRoleList;

        public GetRole(Spinner spRoleList) {
            this.spRoleList = spRoleList;
        }

        @Override
        protected String doInBackground(String... params) {

            roleArrName.clear();

            String response;
            HttpHandler h=new HttpHandler();
            response= h.serverConnection(MainActivity.mainUrl+"roleView");
            if(response!=null)
            {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray roleArr=jsonObject.getJSONArray("role");
                    for (int i=0;i<roleArr.length();i++)
                    {
                        JSONObject j=roleArr.getJSONObject(i);

                        String id=j.getString("id");
                        String name=j.getString("name");

                        roleArrId.add(id);
                        roleArrName.add(name);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(context,
                            android.R.layout.simple_spinner_item, roleArrName);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spRoleList.setAdapter(adapter);

            for(int i = 0; i<roleArrName.size();i++)
            {
                if(title.equals(roleArrId.get(i)))
                {
                    spRoleList.setSelection(i);
                }
            }
        }
    }

    private class GetEditUser extends AsyncTask<String,Void,String> {

        String username,status,message;

        public GetEditUser(String username) {
            this.username = username;

        }

        @Override
        protected String doInBackground(String... params) {

            JSONObject eroleList=new JSONObject();
            try {
                eroleList.put("username",username);
                eroleList.put("role",pos);
                Postdata postdata=new Postdata();
                String rlPd=postdata.post(MainActivity.mainUrl+"userEdit",eroleList.toString());
                JSONObject j=new JSONObject(rlPd);
                status=j.getString("status");
                if(status.equals("1"))
                {
                    Log.d("Like", "Successfully");
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
                Intent i = new Intent(context,AdminRoleActivity.class);
                context.startActivity(i);
                ((Activity)context).finish();
            }
            else
            {
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
