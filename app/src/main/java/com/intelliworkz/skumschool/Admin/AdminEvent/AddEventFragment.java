package com.intelliworkz.skumschool.Admin.AdminEvent;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class AddEventFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    View view;
    TextInputLayout input_layout_Admin_evDate,input_layout_Admin_evTitle,input_layout_Admin_evDescription;
    EditText txtAdminevDate,txtAdminevTitle,txtAdminevDescription;
    Button btnAddevSubmit;

    Calendar c = Calendar.getInstance();
    int startYear = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH);
    int startDay = c.get(Calendar.DAY_OF_MONTH);

    public AddEventFragment() {
        // Required empty public constructor
    }

    public static AddEventFragment newInstance(String param1, String param2) {
        AddEventFragment fragment = new AddEventFragment();
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
        view = inflater.inflate(R.layout.fragment_add_event, container, false);

        input_layout_Admin_evDate = (TextInputLayout)view.findViewById(R.id.input_layout_Admin_evDate);
        input_layout_Admin_evTitle = (TextInputLayout)view.findViewById(R.id.input_layout_Admin_evTitle);
        input_layout_Admin_evDescription = (TextInputLayout)view.findViewById(R.id.input_layout_Admin_evDescription);

        btnAddevSubmit = (Button)view.findViewById(R.id.btnAddevSubmit);

        txtAdminevDate = (EditText)view.findViewById(R.id.txtAdminevDate);
        txtAdminevTitle = (EditText)view.findViewById(R.id.txtAdminevTitle);
        txtAdminevDescription = (EditText)view.findViewById(R.id.txtAdminevDescription);

        txtAdminevDate.addTextChangedListener(new MyTextWatcher(txtAdminevDate));
        txtAdminevTitle.addTextChangedListener(new MyTextWatcher(txtAdminevTitle));
        txtAdminevDescription.addTextChangedListener(new MyTextWatcher(txtAdminevDescription));

        txtAdminevDate.setOnClickListener(this);

        btnAddevSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Date = txtAdminevDate.getText().toString();
                String Title = txtAdminevTitle.getText().toString();
                String Description = txtAdminevDescription.getText().toString();

                if(Date.equals(""))
                {
                    validateDate();
                }
                else if(Title.equals(""))
                {
                    validateTitle();
                }
                else if (Description.equals(""))
                {
                    validateDescription();
                }
                else
                {
                    InsertEvent  insertEvent = new InsertEvent(Date,Title,Description);
                    insertEvent.execute();
                }
            }
        });

        return view;
    }

    private boolean validateDate() {

        if (txtAdminevDate.getText().toString().trim().isEmpty())
        {
            input_layout_Admin_evDate.setError(getString(R.string.err_msg_empty));
            requestFocus(txtAdminevDate);
            return false;
        }
        else
        {
            input_layout_Admin_evDate.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateTitle() {

        if (txtAdminevTitle.getText().toString().trim().isEmpty())
        {
            input_layout_Admin_evTitle.setError(getString(R.string.err_msg_empty));
            requestFocus(txtAdminevTitle);
            return false;
        }
        else
        {
            input_layout_Admin_evTitle.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDescription() {

        if (txtAdminevDescription.getText().toString().trim().isEmpty())
        {
            input_layout_Admin_evDescription.setError(getString(R.string.err_msg_empty));
            requestFocus(txtAdminevDescription);
            return false;
        }
        else
        {
            input_layout_Admin_evDescription.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus())
        {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == txtAdminevDate)
        {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener()
            {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                {
                    startYear=year;
                    startMonth=monthOfYear;
                    startDay=dayOfMonth;
                    txtAdminevDate.setText(startDay + "-" + (startMonth + 1) + "-" + startYear);
                }
            }, startYear, startMonth, startDay);
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            datePickerDialog.show();
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

                case R.id.txtAdminevDate:
                    validateDate();
                    break;
                case R.id.txtAdminevTitle:
                    validateTitle();
                    break;
                case R.id.txtAdminevDescription:
                    validateDescription();
                    break;
            }
        }
    }

    private class InsertEvent extends AsyncTask<String,Void,String> {

        String date,title,description,status,message;

        public InsertEvent(String date, String title, String description) {

            this.date = date;
            this.title = title;
            this.description = description;
        }

        @Override
        protected String doInBackground(String... params) {

            JSONObject evList=new JSONObject();
            try {

                evList.put("activity_date",date);
                evList.put("title",title);
                evList.put("description",description);
                Postdata postdata=new Postdata();
                String evPd=postdata.post(MainActivity.mainUrl+"activityInsert",evList.toString());
                JSONObject j=new JSONObject(evPd);
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
                Intent i = new Intent(getContext(),AdminEventActivity.class);
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
