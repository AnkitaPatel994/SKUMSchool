package com.intelliworkz.skumschool.Forgot_Password;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.intelliworkz.skumschool.Login.LoginActivity;
import com.intelliworkz.skumschool.R;


public class Forgot_PasswordActivity extends AppCompatActivity {

    EditText txtEmail;
    Button btnSubmit;
    TextInputLayout input_layout_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_forgot_password);
        setContentView(R.layout.activity_forgot__password);

        if(getSupportActionBar()!= null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        input_layout_email = (TextInputLayout) findViewById(R.id.input_layout_email);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        txtEmail.addTextChangedListener(new MyTextWatcher(txtEmail));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtEmail.getText().toString().equals("123"))
                {
                    Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(i);
                    finish();
                }
                else if (!validateEmail())
                {
                    return;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private boolean validateEmail() {

        if (txtEmail.getText().toString().trim().isEmpty())
        {
            input_layout_email.setError(getString(R.string.err_msg_email));
            requestFocus(txtEmail);
            return false;
        }
        else if (!txtEmail.getText().toString().trim().equals("123"))
        {
            input_layout_email.setError(getString(R.string.err_msg_email));
            requestFocus(txtEmail);
            return false;
        }
        else
        {
            input_layout_email.setErrorEnabled(false);
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

                case R.id.txtEmail:
                    validateEmail();
                    break;
            }
        }
    }

}
