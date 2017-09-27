package com.intelliworkz.skumschool.Admin.AdminAddStudent;

        import android.app.DatePickerDialog;
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
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
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

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.Locale;

public class AddStudentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Spinner spnStd,spnMedium;
    ArrayList<String> stdArrList=new ArrayList<>();
    ArrayList<String> stdArrList1=new ArrayList<>();
    String mainurl= MainActivity.mainUrl;
    String mediumArr[]={"Select Medium","ENG","GUJ"};

    String classStud,medium;



    Button btnAdd;
    EditText txtAddStdName,txtAddDOB,txtAddRNo,txtAddAddress,txtAddPcode,txtAddAddmissionDate,txtAddGRNo,txtAddVanNo,
            txtAddRPhone,txtAddOPhone,txtAddFathername,txtAddFOccup,txtAddFMono,txtAddFemail,txtAddMothername,txtAddMOccup,txtAddMMono,txtAddMEmail;
    TextInputLayout input_layout_name,input_layout_RNo,input_layout_medium,input_layout_Address,input_layout_PCode,input_layout_DOB,input_layout_AddmissionDate,input_layout_GRNo,
            input_layout_VanNo,input_layout_RPhone,input_layout_OPhone,input_layout_Fathername,input_layout_FOccup,input_layout_FMoNo,
            input_layout_Femail,input_layout_Mothername,input_layout_MOccup,input_layout_MMoNo,input_layout_MEmail;
    //Date Picker
    Context context;
    Calendar myCalendar = Calendar.getInstance();
    String dateFormat = "dd MMMM yyyy";
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog.OnDateSetListener dateAddmission;
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
    public AddStudentFragment() {
        // Required empty public constructor
    }
    public static AddStudentFragment newInstance(String param1, String param2) {
        AddStudentFragment fragment = new AddStudentFragment();
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_add_student, container, false);

        btnAdd=(Button)v.findViewById(R.id.btnAdd);
        spnStd=(Spinner)v.findViewById(R.id.spnStd);
        spnMedium=(Spinner)v.findViewById(R.id.spnMedium);

        txtAddStdName=(EditText)v.findViewById(R.id.txtAddStdName);
        txtAddRNo=(EditText)v.findViewById(R.id.txtAddRNo);
        txtAddAddress=(EditText)v.findViewById(R.id.txtAddAddress);
        txtAddPcode=(EditText)v.findViewById(R.id.txtAddPcode);
        txtAddDOB=(EditText)v.findViewById(R.id.txtAddDOB);
        txtAddAddmissionDate=(EditText)v.findViewById(R.id.txtAddAddmissionDate);
        txtAddGRNo=(EditText)v.findViewById(R.id.txtAddGRNo);
        txtAddVanNo=(EditText)v.findViewById(R.id.txtAddVanNo);
        txtAddRPhone=(EditText)v.findViewById(R.id.txtAddRPhone);
        txtAddOPhone=(EditText)v.findViewById(R.id.txtAddOPhone);
        txtAddFathername=(EditText)v.findViewById(R.id.txtAddFathername);
        txtAddFOccup=(EditText)v.findViewById(R.id.txtAddFOccup);
        txtAddFMono=(EditText)v.findViewById(R.id.txtAddFMono);
        txtAddFemail=(EditText)v.findViewById(R.id.txtAddFemail);
        txtAddMothername=(EditText)v.findViewById(R.id.txtAddMothername);
        txtAddMOccup=(EditText)v.findViewById(R.id.txtAddMOccup);
        txtAddMMono=(EditText)v.findViewById(R.id.txtAddMMono);
        txtAddMEmail=(EditText)v.findViewById(R.id.txtAddMEmail);

        input_layout_name=(TextInputLayout)v.findViewById(R.id.input_layout_name);
        input_layout_RNo=(TextInputLayout)v.findViewById(R.id.input_layout_RNo);
        input_layout_medium=(TextInputLayout)v.findViewById(R.id.input_layout_medium);
        input_layout_Address=(TextInputLayout)v.findViewById(R.id.input_layout_Address);
        input_layout_PCode=(TextInputLayout)v.findViewById(R.id.input_layout_PCode);
        input_layout_DOB=(TextInputLayout)v.findViewById(R.id.input_layout_DOB);
        input_layout_AddmissionDate=(TextInputLayout)v.findViewById(R.id.input_layout_AddmissionDate);
        input_layout_GRNo=(TextInputLayout)v.findViewById(R.id.input_layout_GRNo);
        input_layout_VanNo=(TextInputLayout)v.findViewById(R.id.input_layout_VanNo);
        input_layout_RPhone=(TextInputLayout)v.findViewById(R.id.input_layout_RPhone);
        input_layout_OPhone=(TextInputLayout)v.findViewById(R.id.input_layout_OPhone);
        input_layout_Fathername=(TextInputLayout)v.findViewById(R.id.input_layout_FOccup);
        input_layout_FOccup=(TextInputLayout)v.findViewById(R.id.input_layout_FOccup);
        input_layout_FMoNo=(TextInputLayout)v.findViewById(R.id.input_layout_FMoNo);
        input_layout_Femail=(TextInputLayout)v.findViewById(R.id.input_layout_Femail);
        input_layout_Mothername=(TextInputLayout)v.findViewById(R.id.input_layout_Mothername);
        input_layout_MOccup=(TextInputLayout)v.findViewById(R.id.input_layout_MOccup);
        input_layout_MMoNo=(TextInputLayout)v.findViewById(R.id.input_layout_MMoNo);
        input_layout_MEmail=(TextInputLayout)v.findViewById(R.id.input_layout_MEmail);

        txtAddStdName.addTextChangedListener(new MyTextWatcher(txtAddStdName));
        txtAddRNo.addTextChangedListener(new MyTextWatcher(txtAddRNo));
        txtAddAddress.addTextChangedListener(new MyTextWatcher(txtAddAddress));
        txtAddPcode.addTextChangedListener(new MyTextWatcher(txtAddPcode));
        txtAddDOB.addTextChangedListener(new MyTextWatcher(txtAddDOB));
        txtAddAddmissionDate.addTextChangedListener(new MyTextWatcher(txtAddAddmissionDate));
        txtAddGRNo.addTextChangedListener(new MyTextWatcher(txtAddGRNo));
        txtAddVanNo.addTextChangedListener(new MyTextWatcher(txtAddVanNo));
        txtAddFathername.addTextChangedListener(new MyTextWatcher(txtAddFathername));
        txtAddFOccup.addTextChangedListener(new MyTextWatcher(txtAddFOccup));
        txtAddFMono.addTextChangedListener(new MyTextWatcher(txtAddFMono));
        txtAddFemail.addTextChangedListener(new MyTextWatcher(txtAddFemail));
        txtAddMothername.addTextChangedListener(new MyTextWatcher(txtAddMothername));
        txtAddMMono.addTextChangedListener(new MyTextWatcher(txtAddMMono));
        txtAddMOccup.addTextChangedListener(new MyTextWatcher(txtAddMOccup));

        ArrayAdapter<String> adM=new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.medspiiner,R.id.txtMed,mediumArr);
        spnMedium.setAdapter(adM);

        //Date Picker Code Start
        date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }
        };
        txtAddDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dateAddmission=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateAddDate();
            }
        };
        txtAddAddmissionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),dateAddmission,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //Date Picker Code Stop

        GetStandard getStandard=new GetStandard();
        getStandard.execute();

        spnStd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int pos= Integer.parseInt(String.valueOf(spnStd.getItemIdAtPosition(position)));
                classStud= String.valueOf(spnStd.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnMedium.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medium= String.valueOf(spnMedium.getItemAtPosition(position));
                //Toast.makeText(getActivity(),"medium"+medium,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=txtAddStdName.getText().toString();
                String rollno=txtAddRNo.getText().toString();
                String address=txtAddAddress.getText().toString();
                String pincode=txtAddPcode.getText().toString();
                String bod=txtAddDOB.getText().toString();
                String addmissionDate=txtAddAddmissionDate.getText().toString();
                String gr_no=txtAddGRNo.getText().toString();
                String van_no=txtAddVanNo.getText().toString();
                String f_name=txtAddFathername.getText().toString();
                String f_occupation=txtAddFOccup.getText().toString();
                String f_mobile=txtAddFMono.getText().toString();
                String f_emailid=txtAddFemail.getText().toString();
                String m_name=txtAddMothername.getText().toString();
                String m_occupation=txtAddMOccup.getText().toString();
                String m_mobile=txtAddMMono.getText().toString();

                String re_mobile=txtAddRPhone.getText().toString();
                String of_mobile=txtAddOPhone.getText().toString();
                String m_emailid=txtAddMEmail.getText().toString();



                if(name.equals(""))
                {
                    validateStdName();
                }
                else if(rollno.equals(""))
                {
                    validateRollNo();
                }
                else if(medium.equals("Select Medium"))
                {
                    validateMedium();
                }
                else if(address.equals(""))
                {
                    validateAddress();
                }
                else if(pincode.equals(""))
                {
                    validatePincode();
                }
                else if(bod.equals(""))
                {
                    validateDOB();
                }
                else if(addmissionDate.equals(""))
                {
                    validateAddmissionDate();
                }
                else if(gr_no.equals(""))
                {
                    validateGrNo();
                }
                else if(van_no.equals(""))
                {
                    validateVanNo();
                }
                else if(f_name.equals(""))
                {
                    validateFatherName();
                }
                else if(f_occupation.equals(""))
                {
                    validateFoccup();
                }
                else if(f_mobile.equals(""))
                {
                    validateFmono();
                }
                else if(f_emailid.equals(""))
                {
                    validateFemail();
                }
                else if(m_name.equals(""))
                {
                    validateMname();
                }
                else if(m_occupation.equals(""))
                {
                    validateMoccup();
                }
                else if(m_mobile.equals(""))
                {
                    validateMmno();
                }
                else
                {
                    InsertStud insertStud=new InsertStud(name,classStud,rollno,medium,address,pincode,bod,addmissionDate,gr_no,van_no,re_mobile,of_mobile,f_name,f_occupation,f_mobile,f_emailid,m_name,m_occupation,m_mobile,m_emailid);
                    insertStud.execute();
                }

            }
        });
        return v;
    }

    private void updateDate() {
        txtAddDOB.setText(sdf.format(myCalendar.getTime()));

    }
    private void updateAddDate() {
        txtAddAddmissionDate.setText(sdf.format(myCalendar.getTime()));
    }

    private boolean validateStdName() {
        if (txtAddStdName.getText().toString().trim().isEmpty())
        {
            input_layout_name.setError(getString(R.string.err_msg_stdname));
            requestFocus(txtAddStdName);
            return false;
        }
        else
        {
            input_layout_name.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateRollNo() {
        if (txtAddRNo.getText().toString().trim().isEmpty())
        {
            input_layout_RNo.setError(getString(R.string.err_msg_rno));
            requestFocus(txtAddRNo);
            return false;
        }
        else
        {
            input_layout_RNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMedium() {

        if (medium.equals("Select Medium"))
        {
            /*input_layout_medium.setError(getString(R.string.err_msg_rno));
            requestFocus(spnMedium);*/
            Toast.makeText(getActivity(),"Please select Medium",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            input_layout_medium.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAddress () {
        if (txtAddAddress.getText().toString().trim().isEmpty())
        {
            input_layout_Address.setError(getString(R.string.err_msg_address));
            requestFocus(txtAddAddress);
            return false;
        }
        else
        {
            input_layout_Address.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePincode () {
        String MobilePattern = "[0-9]{6}";
        if(!txtAddPcode.getText().toString().matches(MobilePattern))
        {
            input_layout_PCode.setError(getString(R.string.err_msg_validpno));
            requestFocus(txtAddPcode);
            return false;
        }
        else
        {
            input_layout_PCode.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDOB () {
        if (txtAddDOB.getText().toString().trim().isEmpty())
        {
            input_layout_DOB.setError(getString(R.string.err_msg_dob));
            requestFocus(txtAddDOB);
            return false;
        }
        else
        {
            input_layout_DOB.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAddmissionDate () {
        if (txtAddAddmissionDate.getText().toString().trim().isEmpty())
        {
            input_layout_AddmissionDate.setError(getString(R.string.err_msg_admissiondate));
            requestFocus(txtAddAddmissionDate);
            return false;
        }
        else
        {
            input_layout_AddmissionDate.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateGrNo () {
        if (txtAddGRNo.getText().toString().trim().isEmpty())
        {
            input_layout_GRNo.setError(getString(R.string.err_msg_grno));
            requestFocus(txtAddGRNo);
            return false;
        }
        else
        {
            input_layout_GRNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateVanNo() {
        if (txtAddVanNo.getText().toString().trim().isEmpty())
        {
            input_layout_VanNo.setError(getString(R.string.err_msg_vanno));
            requestFocus(txtAddVanNo);
            return false;
        }
        else
        {
            input_layout_VanNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFatherName() {
        if (txtAddFathername.getText().toString().trim().isEmpty())
        {
            input_layout_Fathername.setError(getString(R.string.err_msg_fname));
            requestFocus(txtAddFathername);
            return false;
        }
        else
        {
            input_layout_Fathername.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFoccup() {
        if (txtAddFOccup.getText().toString().trim().isEmpty())
        {
            input_layout_FOccup.setError(getString(R.string.err_msg_foccup));
            requestFocus(txtAddFOccup);
            return false;
        }
        else
        {
            input_layout_FOccup.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFmono() {
        String MobilePattern = "[0-9]{10}";
        if(!txtAddFMono.getText().toString().matches(MobilePattern))
        {
            input_layout_FMoNo.setError(getString(R.string.err_msg_validmono));
            requestFocus(txtAddFMono);
            return false;
        }
        else
        {
            input_layout_FMoNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFemail() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!txtAddFemail.getText().toString().matches(emailPattern))
        {
            input_layout_Femail.setError(getString(R.string.err_msg_email));
            requestFocus(txtAddFemail);
            return false;
        }
        else
        {
            input_layout_Femail.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMname() {
        if (txtAddMothername.getText().toString().trim().isEmpty())
        {
            input_layout_Mothername.setError(getString(R.string.err_msg_mname));
            requestFocus(txtAddMothername);
            return false;
        }
        else
        {
            input_layout_Mothername.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMoccup() {
        if (txtAddMOccup.getText().toString().trim().isEmpty())
        {
            input_layout_MOccup.setError(getString(R.string.err_msg_moccup));
            requestFocus(txtAddMOccup);
            return false;
        }
        else
        {
            input_layout_MOccup.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMmno() {

        String MobilePattern = "[0-9]{10}";
        if(!txtAddMMono.getText().toString().matches(MobilePattern))
        {
            input_layout_MMoNo.setError(getString(R.string.err_msg_validmono));
            requestFocus(txtAddMMono);
            return false;
        }
        else
        {
            input_layout_MMoNo.setErrorEnabled(false);
        }
        return true;
    }
    private void requestFocus(View v) {
        if (v.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public class MyTextWatcher implements TextWatcher {
        private View view;
        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId())
            {
                case R.id.txtAddStdName:
                    validateStdName();
                    break;
                case R.id.txtAddRNo:
                    validateRollNo();
                    break;
                case R.id.txtAddAddress:
                    validateAddress();
                    break;
                case R.id.txtAddPcode:
                    validatePincode();
                    break;
                case R.id.txtAddDOB:
                    validateDOB();
                    break;
                case R.id.txtAddAddmissionDate:
                    validateAddmissionDate();
                    break;
                case R.id.txtAddGRNo:
                    validateGrNo();
                    break;
                case R.id.txtAddVanNo:
                    validateVanNo();
                    break;
                case R.id.txtAddFathername:
                    validateFatherName();
                    break;
                case R.id.txtAddFOccup:
                    validateFoccup();
                    break;
                case R.id.txtAddFMono:
                    validateFmono();
                    break;
                case R.id.txtAddFemail:
                    validateFemail();
                    break;
                case R.id.txtAddMothername:
                    validateMname();
                    break;
                case R.id.txtAddMOccup:
                    validateMoccup();
                    break;
                case R.id.txtAddMMono:
                    validateMmno();
                    break;

            }
        }
    }

    private class GetStandard extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... params) {
            stdArrList.add("Select Standard");
            String response;
            HttpHandler h=new HttpHandler();
            response= h.serverConnection(mainurl+"Allclassdiv");
            if(response!=null)
            {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray stdArr=jsonObject.getJSONArray("class");
                    for (int i=0;i<stdArr.length();i++)
                    {
                        JSONObject j=stdArr.getJSONObject(i);

                        String id=j.getString("id");
                        String std=j.getString("std");
                        String div=j.getString("div");
                        String stdDiv=std+div;

                        //tabTitlesId.add(catId);

                        stdArrList.add(stdDiv);
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

            ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, stdArrList);
            spnStd.setAdapter(ad);
        }
    }

    private class InsertStud extends AsyncTask<String,Void,String>{
        String name,classStud,rollno,medium,address,pincode,bod,addmissionDate,gr_no,van_no,re_mobile,of_mobile,f_name,f_occupation,
                f_mobile,f_emailid,m_name,m_occupation,m_mobile,m_emailid,status,message;
        public InsertStud(String name,String classStud,String rollno,String medium,String address, String pincode, String bod, String addmissionDate, String gr_no, String van_no, String re_mobile, String of_mobile, String f_name, String f_occupation, String f_mobile, String f_emailid, String m_name, String m_occupation, String m_mobile, String m_emailid) {
            this.name=name;
            this.classStud=classStud;
            this.rollno=rollno;
            this.medium=medium;
            this.address=address;
            this.pincode=pincode;
            this.bod=bod;
            this.addmissionDate=addmissionDate;
            this.gr_no=gr_no;
            this.van_no=van_no;
            this.re_mobile=re_mobile;
            this.of_mobile=of_mobile;
            this.f_name=f_name;
            this.f_occupation=f_occupation;
            this.f_mobile=f_mobile;
            this.f_emailid=f_emailid;
            this.m_name=m_name;
            this.m_occupation=m_occupation;
            this.m_mobile=m_mobile;
            this.m_emailid=m_emailid;
        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject stdList=new JSONObject();
            try {

                stdList.put("name",name);
                stdList.put("class",classStud);
                stdList.put("rollno",rollno);
                stdList.put("medium",medium);
                stdList.put("address",address);
                stdList.put("pincode",pincode);
                stdList.put("bod",bod);
                stdList.put("addmissionDate",addmissionDate);
                stdList.put("gr_no",gr_no);
                stdList.put("van_no",van_no);
                stdList.put("re_mobile",re_mobile);
                stdList.put("of_mobile",of_mobile);
                stdList.put("f_name",f_name);
                stdList.put("f_occupation",f_occupation);
                stdList.put("f_mobile",f_mobile);
                stdList.put("f_emailid",f_emailid);
                stdList.put("m_name",m_name);
                stdList.put("m_occupation",m_occupation);
                stdList.put("m_mobile",m_mobile);
                stdList.put("m_emailid",m_emailid);

                Postdata postdata=new Postdata();
                String stdPd=postdata.post(MainActivity.mainUrl+"studentInsert",stdList.toString());
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
                Toast.makeText(getActivity(),""+message,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(),AddStudentActivity.class);
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
