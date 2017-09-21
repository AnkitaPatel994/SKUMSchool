package com.intelliworkz.skumschool.Admin.AdminNoticeBoard;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class AddNoticeBoardFragment extends Fragment {

    View view;
    EditText txtAdminNbTitle,txtAdminNbDescription;
    Button btnAddnbSubmit;
    TextInputLayout input_layout_Admin_nbTitle,input_layout_Admin_nbDescription;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public AddNoticeBoardFragment() {
        // Required empty public constructor
    }

    public static AddNoticeBoardFragment newInstance(String param1, String param2) {
        AddNoticeBoardFragment fragment = new AddNoticeBoardFragment();
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

        view = inflater.inflate(R.layout.fragment_add_notice_board, container, false);

        txtAdminNbTitle = (EditText)view.findViewById(R.id.txtAdminNbTitle);
        txtAdminNbDescription = (EditText)view.findViewById(R.id.txtAdminNbDescription);

        btnAddnbSubmit = (Button) view.findViewById(R.id.btnAddnbSubmit);

        input_layout_Admin_nbTitle = (TextInputLayout)view.findViewById(R.id.input_layout_Admin_nbTitle);
        input_layout_Admin_nbDescription = (TextInputLayout)view.findViewById(R.id.input_layout_Admin_nbDescription);

        txtAdminNbTitle.addTextChangedListener(new MyTextWatcher(txtAdminNbTitle));
        txtAdminNbDescription.addTextChangedListener(new MyTextWatcher(txtAdminNbDescription));

        btnAddnbSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Title = txtAdminNbTitle.getText().toString();
                String Description = txtAdminNbDescription.getText().toString();

                if(Title.equals("") && Description.equals(""))
                {
                    validateTitle();
                }
                else if (Description.equals(""))
                {
                    validateDescription();
                }
                else
                {
                    InsertNoticeBoard  insertNoticeBoard = new InsertNoticeBoard(Title,Description);
                    insertNoticeBoard.execute();
                }
            }
        });
        return view;
    }

    private boolean validateTitle() {

        if (txtAdminNbTitle.getText().toString().trim().isEmpty())
        {
            input_layout_Admin_nbTitle.setError(getString(R.string.err_msg_empty));
            requestFocus(txtAdminNbTitle);
            return false;
        }
        else
        {
            input_layout_Admin_nbTitle.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDescription() {

        if (txtAdminNbDescription.getText().toString().trim().isEmpty())
        {
            input_layout_Admin_nbDescription.setError(getString(R.string.err_msg_empty));
            requestFocus(txtAdminNbDescription);
            return false;
        }
        else
        {
            input_layout_Admin_nbDescription.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {

            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        }
    }

    public class MyTextWatcher implements TextWatcher {

        private View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

                case R.id.txtAdminNbTitle:
                    validateTitle();
                    break;
                case R.id.txtAdminNbDescription:
                    validateDescription();
                    break;
            }
        }
    }

    private class InsertNoticeBoard extends AsyncTask<String,Void,String> {

        String title,description,status,message;

        public InsertNoticeBoard(String title, String description) {

            this.title = title;
            this.description = description;

        }

        @Override
        protected String doInBackground(String... params) {

            JSONObject nbList=new JSONObject();
            try {

                nbList.put("title",title);
                nbList.put("description",description);
                Postdata postdata=new Postdata();
                String nbPd=postdata.post(MainActivity.mainUrl+"noticeBoardInsert",nbList.toString());
                JSONObject j=new JSONObject(nbPd);
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
                Toast.makeText(getActivity(),""+message,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(),AdminNoticeBoardActivity.class);
                startActivity(i);
                getActivity().finish();
            }
            else
            {
                Toast.makeText(getActivity(),""+message,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
