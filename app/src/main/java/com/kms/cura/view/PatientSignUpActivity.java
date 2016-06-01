package com.kms.cura.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kms.cura.R;
import com.kms.cura.utils.InputUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatientSignUpActivity extends AppCompatActivity implements TextWatcher {
    private EditText edtFirstName, edtEmail, edtPassword, edtPasswordReenter;
    private Button btnRegister;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);
        initEditText();
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Patient Register Function
            }
        });

    }


    public void initEditText() {
        edtFirstName = initEditText(R.id.edtFirstName, this);
        edtEmail = initEditText(R.id.edtEmail, this);
        edtPassword = initEditText(R.id.edtPassword, this);
        edtPasswordReenter = initEditText(R.id.edtPasswordReenter, this);
    }

    public EditText initEditText(int id, TextWatcher watcher) {
        EditText tmp = (EditText) findViewById(id);
        tmp.addTextChangedListener(watcher);
        return tmp;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //Ignore
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        validateFirstName();
        validateEmail();
        validatePassword();
        validatePasswordReenter();
    }

    @Override
    public void afterTextChanged(Editable s) {
        btnRegister.setEnabled(validateFirstName() && validateEmail() && validatePassword() && validatePasswordReenter());
    }

    public String getEditTextText(EditText src) {
        return src.getText().toString();
    }

    public boolean validateFirstName() {
        if (!InputUtils.isNotEmpty(getEditTextText(edtFirstName))) {
            edtFirstName.setError(getResources().getString(R.string.FirstNameError));
            return false;
        }
        return true;
    }

    public boolean validateEmail() {
        if (!InputUtils.isEmailValid(getEditTextText(edtEmail))) {
            edtEmail.setError(getResources().getString(R.string.EmailError));
            return false;
        }
        return true;
    }

    public boolean validatePassword() {
        if (!InputUtils.isPasswordValid(getEditTextText(edtPassword))) {
            edtPassword.setError(getResources().getString(R.string.PasswordError));
            return false;
        }
        return true;
    }

    public boolean validatePasswordReenter() {
        if (!getEditTextText(edtPasswordReenter).equals(getEditTextText(edtPassword))) {
            edtPasswordReenter.setError(getResources().getString(R.string.PasswordReenterError));
            return false;
        }
        return true;
    }
}
