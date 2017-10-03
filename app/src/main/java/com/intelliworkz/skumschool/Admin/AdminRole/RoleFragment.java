package com.intelliworkz.skumschool.Admin.AdminRole;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.intelliworkz.skumschool.R;

public class RoleFragment extends Fragment {

    View view;
    private String title;
    private int page;
    RecyclerView rvRoleNameList;
    RecyclerView.LayoutManager rvRoleNameListManager;
    RecyclerView.Adapter rvRoleNameListAdapter;

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

        /*tv.setText("Selected Tab :\n\t"+ page + " -- " + title);*/

        rvRoleNameList = (RecyclerView)view.findViewById(R.id.ae_ivEventList);
        rvRoleNameList.setHasFixedSize(true);

        rvRoleNameListManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvRoleNameList.setLayoutManager(rvRoleNameListManager);

        return view;
    }

}
