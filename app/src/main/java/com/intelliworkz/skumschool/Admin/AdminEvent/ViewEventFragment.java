package com.intelliworkz.skumschool.Admin.AdminEvent;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;
import com.intelliworkz.skumschool.Student.Calender.CalenderActivity;
import com.intelliworkz.skumschool.Student.Calender.EventAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ViewEventFragment extends Fragment {

    View view;
    RecyclerView ae_ivEventList;
    RecyclerView.LayoutManager ae_ivEventListManager;
    RecyclerView.Adapter ae_ivEventListAdapter;
    ImageView ae_previousButton,ae_forwardButton;
    TextView ae_txtMonthName,ae_txtNotDataFound;
    private int currentMonthIndex = 0;
    ArrayList<HashMap<String,String>> adminActivityListArray=new ArrayList<>();

    public ViewEventFragment() {
        // Required empty public constructor
    }


    public static ViewEventFragment newInstance(String param1, String param2) {
        ViewEventFragment fragment = new ViewEventFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_event, container, false);

        ae_ivEventList = (RecyclerView)view.findViewById(R.id.ae_ivEventList);
        ae_ivEventList.setHasFixedSize(true);

        ae_ivEventListManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ae_ivEventList.setLayoutManager(ae_ivEventListManager);

        ae_txtMonthName = (TextView)view.findViewById(R.id.ae_txtMonthName);

        ae_previousButton = (ImageView)view.findViewById(R.id.ae_previousButton);
        ae_forwardButton = (ImageView)view.findViewById(R.id.ae_forwardButton);

        ae_txtNotDataFound = (TextView)view.findViewById(R.id.ae_txtNotDataFound);

        DateFormat dateFormat = new SimpleDateFormat("MMMM");
        Date date = new Date();

        String monthName = dateFormat.format(date);
        ae_txtMonthName.setText(monthName);

        final String currentmonthName = dateFormat.format(date);
        if(monthName.equals(currentmonthName))
        {
            ae_previousButton.setVisibility(View.GONE);
            ae_txtMonthName.setPadding(200,0,0,0);
        }

        GetAdminActivityList adminActivityList = new GetAdminActivityList(monthName);
        adminActivityList.execute();

        ae_previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentMonthIndex--;
                Calendar cal =  Calendar.getInstance();
                cal.add(Calendar.MONTH ,currentMonthIndex);

                String monthName  = new SimpleDateFormat("MMMM").format(cal.getTime());

                ae_txtMonthName.setText(monthName);

                if(monthName.equals(currentmonthName))
                {
                    ae_previousButton.setVisibility(View.GONE);
                    ae_txtMonthName.setPadding(200,0,0,0);
                }

                GetAdminActivityList adminActivityList = new GetAdminActivityList(monthName);
                adminActivityList.execute();
            }
        });

        ae_forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentMonthIndex++;
                Calendar cal =  Calendar.getInstance();
                cal.add(Calendar.MONTH ,currentMonthIndex);

                ae_previousButton.setVisibility(View.VISIBLE);
                ae_txtMonthName.setPadding(0,0,0,0);

                String monthName  = new SimpleDateFormat("MMMM").format(cal.getTime());

                ae_txtMonthName.setText(monthName);

                GetAdminActivityList adminActivityList = new GetAdminActivityList(monthName);
                adminActivityList.execute();

            }
        });

        return view;
    }

    private class GetAdminActivityList extends AsyncTask<String,Void,String> {

        String monthName,status;

        public GetAdminActivityList(String monthName) {
            this.monthName = monthName;
        }

        @Override
        protected String doInBackground(String... strings) {

            adminActivityListArray.clear();

            JSONObject activityList=new JSONObject();
            try {
                activityList.put("month",monthName);
                Postdata postdata=new Postdata();
                String adPd=postdata.post(MainActivity.mainUrl+"activity",activityList.toString());
                JSONObject j=new JSONObject(adPd);
                status=j.getString("status");
                if(status.equals("1"))
                {
                    Log.d("Like","Successfully");
                    JSONArray JsArry=j.getJSONArray("activity_list");

                    for (int i=0;i<JsArry.length();i++)
                    {
                        HashMap<String,String > hashMap = new HashMap<>();
                        JSONObject jo=JsArry.getJSONObject(i);

                        String id=jo.getString("id");
                        String title=jo.getString("title");
                        String description=jo.getString("description");
                        String activity_date=jo.getString("activity_date");
                        String activity_day=jo.getString("activity_day");

                        hashMap.put("id",id);
                        hashMap.put("title",title);
                        hashMap.put("description",description);
                        hashMap.put("activity_date",activity_date);
                        hashMap.put("activity_day",activity_day);

                        adminActivityListArray.add(hashMap);
                    }
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
                ae_ivEventList.setVisibility(View.VISIBLE);
                ae_txtNotDataFound.setVisibility(View.GONE);
                ae_ivEventListAdapter=new EventAdapter(getActivity(),adminActivityListArray);
                ae_ivEventList.setAdapter(ae_ivEventListAdapter);
            }
            else
            {
                ae_ivEventList.setVisibility(View.GONE);
                ae_txtNotDataFound.setVisibility(View.VISIBLE);
            }
        }
    }
}
