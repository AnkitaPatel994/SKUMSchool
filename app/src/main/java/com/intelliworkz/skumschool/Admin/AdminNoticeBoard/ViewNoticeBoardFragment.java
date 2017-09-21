package com.intelliworkz.skumschool.Admin.AdminNoticeBoard;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.intelliworkz.skumschool.HttpHandler;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewNoticeBoardFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    View view;
    RecyclerView rvNoticeBoard;
    RecyclerView.LayoutManager rvNoticeBoardManager;
    RecyclerView.Adapter rvNoticeBoardAdapter;
    ArrayList<HashMap<String,String>> noticeBoardList=new ArrayList<>();

    public ViewNoticeBoardFragment() {
        // Required empty public constructor
    }

    public static ViewNoticeBoardFragment newInstance(String param1, String param2) {
        ViewNoticeBoardFragment fragment = new ViewNoticeBoardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_view_notice_board, container, false);

        rvNoticeBoard = (RecyclerView)view.findViewById(R.id.rvAdminNoticeBoard);
        rvNoticeBoard.setHasFixedSize(true);

        rvNoticeBoardManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvNoticeBoard.setLayoutManager(rvNoticeBoardManager);

        GetNoticeBoardList getNoticeBoard = new GetNoticeBoardList();
        getNoticeBoard.execute();

        return view;
    }

    private class GetNoticeBoardList extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {

            String response;
            HttpHandler h=new HttpHandler();
            response= h.serverConnection(MainActivity.mainUrl+"noticeBoardView");
            if(response!=null)
            {
                try {

                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray category=jsonObject.getJSONArray("noticeBoard");
                    for (int i=0;i<category.length();i++)
                    {
                        HashMap<String,String > nbListhm = new HashMap<>();
                        JSONObject j=category.getJSONObject(i);

                        String title=j.getString("title");
                        String description=j.getString("description");
                        String date=j.getString("date");

                        nbListhm.put("title",title);
                        nbListhm.put("description",description);
                        nbListhm.put("date",date);

                        noticeBoardList.add(nbListhm);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"Server Connection Not Found..",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            rvNoticeBoardAdapter=new NoticeBoardListAdapter(getActivity(),noticeBoardList);
            rvNoticeBoard.setAdapter(rvNoticeBoardAdapter);

        }
    }
}
