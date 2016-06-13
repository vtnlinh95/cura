package com.kms.cura.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.kms.cura.R;
import com.kms.cura.utils.InputUtils;
import com.kms.cura.view.activity.RegisterDoctorActivity;

public class RegDoc_AccountFragment extends Fragment implements View.OnClickListener, TextWatcher {

    private EditText edtPhone, edtEmail, edtPwd, edtRePwd;
    private Button btnRegister;
    private ImageButton btnBack;

    boolean edittedPhone, edittedEmail, edittedPwd, edittedRePwd;

    public RegDoc_AccountFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragmentView = inflater.inflate(R.layout.fragment_reg_doc__account, container, false);
        edtPhone = setupEditText(myFragmentView, R.id.edtPhone, this);
        edtEmail = setupEditText(myFragmentView, R.id.edtEmail, this);
        edtPwd = setupEditText(myFragmentView, R.id.edtPwd, this);
        edtRePwd = setupEditText(myFragmentView, R.id.edtRePwd, this);
        btnRegister = (Button) myFragmentView.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        btnBack = (ImageButton) myFragmentView.findViewById(R.id.btnRegisterBack);
        btnBack.setOnClickListener(this);

        edittedPwd = false;
        edittedRePwd = false;
        reformData();
        return myFragmentView;
    }

    private EditText setupEditText(View parent, int resourceId, TextWatcher textWatcher) {
        EditText edt = (EditText) parent.findViewById(resourceId);
        edt.addTextChangedListener(textWatcher);
        return edt;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegisterBack) {
            Bundle bundle = createBundle();
            Fragment professional = new RegDoc_ProfessionalFragment();
            professional.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_DocReg, professional).commit();
        } else if (v.getId() == R.id.btnRegister) {
            if (checkInput()) {
                String phone = edtPhone.getText().toString();
                String email = edtEmail.getText().toString();
                String pwd = edtPwd.getText().toString();
                Bundle bundle = getArguments();
                // check in database

            }
        }
    }

    private boolean checkInput() {
        return checkEmail() && checkPwd() && checkRePwd() && checkPhone();
    }

    private boolean checkPhone() {
        return !edtPhone.getText().toString().isEmpty();
    }

    private boolean checkEmail() {
        String email = edtEmail.getText().toString();
        return InputUtils.isEmailValid(email);
    }

    private boolean checkPwd() {
        String pwd = edtPwd.getText().toString();
        return InputUtils.isPasswordValid(pwd);
    }

    private boolean checkRePwd() {
        String pwd = edtPwd.getText().toString();
        String rePwd = edtRePwd.getText().toString();
        return pwd.equals(rePwd);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        boolean flag = setErrorMessagePhone();
        flag &= setErrorMessageEmail();
        flag &= setErrorMessagePwd();
        flag &= setErrorMessageRePwd();
        btnRegister.setEnabled(flag);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private Bundle createBundle() {
        Bundle bundle = getArguments();
        bundle.putBoolean(RegisterDoctorActivity.PROFESSIONAL, true);
        bundle.putString(RegisterDoctorActivity.PHONE, edtPhone.getText().toString());
        bundle.putString(RegisterDoctorActivity.EMAIL, edtEmail.getText().toString());
        return bundle;
    }

    private void reformData() {
        Bundle bundle = getArguments();
        edtPhone.setText(bundle.getString(RegisterDoctorActivity.PHONE, ""));
        edtEmail.setText(bundle.getString(RegisterDoctorActivity.EMAIL, ""));
        edittedPhone = !edtPhone.getText().toString().equals("");
        edittedEmail = !edtEmail.getText().toString().equals("");
    }

    private boolean setErrorMessageEmail() {

        if (edtEmail.getText().toString().equals("")) {
            checkIfRequiredFieldIsEmpty(edittedEmail, edtEmail);
            edittedEmail = false;
            return false;
        }
        edittedEmail = true;
        if (!checkEmail() && edittedEmail) {
            edtEmail.setError(getString(R.string.email_error));
            return false;
        }
        return true;
    }

    private boolean setErrorMessagePwd() {
        if (edtPwd.getText().toString().equals("")) {
            checkIfRequiredFieldIsEmpty(edittedPwd, edtPwd);
            edittedPwd = false;
            return false;
        }
        edittedPwd = true;
        if (!checkPwd() && edittedPwd) {
            edtPwd.setError(getString(R.string.pwd_error));
            return false;
        }
        return true;
    }

    private void checkIfRequiredFieldIsEmpty(boolean isEditTextEdited, EditText editText) {
        if (isEditTextEdited) {
            editText.setError(getString(R.string.required_field));
        }
    }

    private boolean setErrorMessagePhone() {
        if (edtPhone.getText().toString().equals("")) {
            checkIfRequiredFieldIsEmpty(edittedPhone, edtPhone);
            edittedPhone = false;
            return false;
        }
        edittedPhone = true;
        return true;
    }

    private boolean setErrorMessageRePwd() {
        if (edtRePwd.getText().toString().equals("")) {
            checkIfRequiredFieldIsEmpty(edittedRePwd, edtRePwd);
            edittedRePwd = false;
            return false;
        }
        edittedRePwd = true;
        if (edittedRePwd) {
            if (!checkRePwd()) {
                edtRePwd.setError(getString(R.string.repwd_error));
                return false;
            }
            edtRePwd.setError(null);
        }
        return true;
    }
}
