package com.intelliworkz.skumschool.Login;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.intelliworkz.skumschool.AdminAddStudent.AddStudentActivity;
import com.intelliworkz.skumschool.AdminHome.AdminHomeActivity;
import com.intelliworkz.skumschool.Forgot_Password.Forgot_PasswordActivity;
import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.Home.HomeActivity;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsername,txtPassword;
    Button btnLogin;
    TextInputLayout inputLayoutUname,inputLayoutPass;
    TextView tv_swEnglish,tv_swHindi,tv_swGujarati,txtForgotPass;
    Locale myLocale;
    public static String str_language_Code="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.string.app_title);
        setContentView(R.layout.activity_login);

        inputLayoutUname = (TextInputLayout) findViewById(R.id.input_layout_uname);
        inputLayoutPass = (TextInputLayout) findViewById(R.id.input_layout_pass);

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtForgotPass = (TextView) findViewById(R.id.txtForgotPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        txtUsername.addTextChangedListener(new MyTextWatcher(txtUsername));
        txtPassword.addTextChangedListener(new MyTextWatcher(txtPassword));

        tv_swEnglish = (TextView) findViewById(R.id.tv_sw_english);
        tv_swHindi = (TextView) findViewById(R.id.tv_sw_hindi);
        tv_swGujarati = (TextView) findViewById(R.id.tv_sw_gujarati);

        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Forgot_PasswordActivity.class);
                startActivity(i);
            }
        });

        if(str_language_Code.equals("1"))
        {
            tv_swEnglish.setBackgroundResource(R.drawable.language_select_left_swborder);
            tv_swEnglish.setTextColor(getResources().getColor(R.color.colorWhite));
            tv_swHindi.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tv_swGujarati.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        else if(str_language_Code.equals("2"))
        {
            tv_swHindi.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tv_swHindi.setTextColor(getResources().getColor(R.color.colorWhite));
            tv_swGujarati.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tv_swEnglish.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        else if(str_language_Code.equals("3"))
        {
            tv_swGujarati.setBackgroundResource(R.drawable.language_select_right_swborder);
            tv_swGujarati.setTextColor(getResources().getColor(R.color.colorWhite));
            tv_swEnglish.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tv_swHindi.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        tv_swEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_language_Code = "1";
                changeLanguageDLG();
            }
        });

        tv_swHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_language_Code = "2";
                changeLanguageDLG();
            }
        });

        tv_swGujarati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_language_Code = "3";
                changeLanguageDLG();
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username = txtUsername.getText().toString();
                String Password = txtPassword.getText().toString();

                if(Username.equals("") && Password.equals(""))
                {
                    validateUname();
                }
                else if (Password.equals(""))
                {
                    validatePassword();
                }
                else
                {
                    GetLoginValid  loginValid = new GetLoginValid(Username,Password);
                    loginValid.execute();
                }

                Intent i=new Intent(getApplicationContext(),AdminHomeActivity.class);
                startActivity(i);
                finish();

            }
        });

    }

    private boolean validateUname() {

        if (txtUsername.getText().toString().trim().isEmpty())
        {
            inputLayoutUname.setError(getString(R.string.err_msg_uname));
            requestFocus(txtUsername);
            return false;
        }
        else
        {
            inputLayoutUname.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword() {

        if (txtPassword.getText().toString().trim().isEmpty())
        {
            inputLayoutPass.setError(getString(R.string.err_msg_pass));
            requestFocus(txtPassword);
            return false;
        }
        else
        {
            inputLayoutPass.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
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

                case R.id.txtUsername:
                    validateUname();
                    break;
                case R.id.txtPassword:
                    validatePassword();
                    break;
            }
        }
    }

    private void changeLanguageDLG() {

        if (str_language_Code.equals("1"))
        {
            myLocale = new Locale("en"); //english
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            str_language_Code = "1";
            Intent refresh = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(refresh);
            finish();
        }
        else if (str_language_Code.equals("2"))
        {
            myLocale = new Locale("hi"); //hindi
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            str_language_Code = "2";
            Intent refresh = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(refresh);
            finish();
        }
        else
        {
            myLocale = new Locale("guj");  //gujarati
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            str_language_Code = "3";
            Intent refresh = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(refresh);
            finish();
        }
    }

    private class GetLoginValid extends AsyncTask<String,Void,String> {

        String username,password,status,message,role;

        public GetLoginValid(String username, String password) {

            this.username = username;
            this.password = password;
        }

        @Override
        protected String doInBackground(String... params) {

            JSONObject loginList=new JSONObject();
            try
            {
                loginList.put("uname",username);
                loginList.put("pass",password);
                Postdata postdata=new Postdata();
                String loginPd=postdata.post(MainActivity.mainUrl+"login",loginList.toString());
                JSONObject j=new JSONObject(loginPd);
                status=j.getString("status");
                if(status.equals("1"))
                {
                    Log.d("Like","Successfully");
                    message = j.getString("message");
                    role = j.getString("role");
                }
                else
                {
                    message = j.getString("message");
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(status.equals("1"))
            {
                if(role.equals("Student"))
                {
                    Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(i);
                    finish();
                }
                else if(role.equals("Admin"))
                {
                    Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }
            else
            {
                txtUsername.setText("");
                txtPassword.setText("");
                validateUname();
                validatePassword();
            }
        }
    }
}
