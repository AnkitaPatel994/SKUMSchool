package com.intelliworkz.skumschool.Admin.AdminRole;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RoleFragment extends Fragment {

    View view;
    private String title;
    private int page;
    TextView txtNotDataFoundRole;
    RecyclerView rvRoleNameList;
    RecyclerView.LayoutManager rvRoleNameListManager;
    RecyclerView.Adapter rvRoleNameListAdapter;
    ArrayList<HashMap<String,String>> roleUserListArray = new ArrayList<>();

    public RoleFragment() {
        // Required empty public constructor
    }

    public static RoleFragment newInstance(int page, String title) {
        RoleFragment fragment = new RoleFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_role, container, false);

        txtNotDataFoundRole = (TextView)view.findViewById(R.id.txtNotDataFoundRole);

        rvRoleNameList = (RecyclerView)view.findViewById(R.id.rvRoleNameList);
        rvRoleNameList.setHasFixedSize(true);

        rvRoleNameListManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvRoleNameList.setLayoutManager(rvRoleNameListManager);

        GetRoleLoginList getRoleLoginList = new GetRoleLoginList(title);
        getRoleLoginList.execute();

        return view;
    }

    private class GetRoleLoginList extends AsyncTask<String,Void,String> {

        String title,status,message;

        public GetRoleLoginList(String title) {

            this.title = title;

        }

        @Override
        protected String doInBackground(String... params) {

            roleUserListArray.clear();

            JSONObject roleList=new JSONObject();
            try {

                roleList.put("role",title);
                Postdata postdata=new Postdata();
                String rlPd=postdata.post(MainActivity.mainUrl+"roleUser",roleList.toString());
                JSONObject j=new JSONObject(rlPd);
                status=j.getString("status");
                if(status.equals("1"))
                {
                    Log.d("Like","Successfully");
                    message=j.getString("message");

                    JSONArray JsArry=j.getJSONArray("RoleUser");

                    for (int i=0;i<JsArry.length();i++)
                    {
                        HashMap<String,String > hashMap = new HashMap<>();
                        JSONObject jo=JsArry.getJSONObject(i);

                        String id=jo.getString("id");
                        String name=jo.getString("name");

                        hashMap.put("id",id);
                        hashMap.put("name",name);

                        roleUserListArray.add(hashMap);
                    }
                }
                else
                {
                    message=j.getString("message");
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
                rvRoleNameList.setVisibility(View.VISIBLE);
                txtNotDataFoundRole.setVisibility(View.GONE);
                rvRoleNameListAdapter=new RoleAdapter(getActivity(),roleUserListArray,title);
                rvRoleNameList.setAdapter(rvRoleNameListAdapter);
            }
            else
            {
                rvRoleNameList.setVisibility(View.GONE);
                txtNotDataFoundRole.setVisibility(View.VISIBLE);
            }
        }
    }
}
