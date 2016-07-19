package com.kms.cura.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kms.cura.R;
import com.kms.cura.constant.EventConstant;
import com.kms.cura.controller.ErrorController;
import com.kms.cura.controller.UserController;
import com.kms.cura.event.EventBroker;
import com.kms.cura.event.EventHandler;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

public class SplashScreen extends AppCompatActivity implements EventHandler {

    private EventBroker broker;
    DilatingDotsProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        broker = EventBroker.getInstance();
        progressBar = (DilatingDotsProgressBar) findViewById(R.id.progress);
        if (UserController.checkSignIn(this)) {
            progressBar.showNow();
            registerEvent();
            UserController.autoSignIn(this);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void handleEvent(String event, Object data) {
        switch (event) {
            case EventConstant.LOGIN_SUCCESS:
                switch ((String) data) {
                    case EventConstant.TYPE_PATIENT:
                        Intent toHomePatient = new Intent(this, PatientViewActivity.class);
                        startActivity(toHomePatient);
                        unregisterEvent();
                        finish();
                        break;
                    case EventConstant.TYPE_DOCTOR:
                        Intent toHomeDoctor = new Intent(this, DoctorViewActivity.class);
                        startActivity(toHomeDoctor);
                        unregisterEvent();
                        finish();
                        break;
                }
                break;
            case EventConstant.LOGIN_FAILED:
                ErrorController.showDialog(this, "Login failed :" + data);
                break;
            case EventConstant.CONNECTION_ERROR:
                if (data != null) {
                    ErrorController.showDialog(this, "Error " + data);
                } else {
                    ErrorController.showDialog(this, "Error " + getResources().getString(R.string.ConnectionError));
                }
                break;
            case EventConstant.INTERNAL_ERROR:
                String internalError = getResources().getString(R.string.InternalError);
                ErrorController.showDialog(this, internalError + " : " + data);
        }
    }

    @Override
    protected void onPause() {
        unregisterEvent();
        super.onPause();
    }

    @Override
    protected void onResume() {
        registerEvent();
        super.onResume();
    }

    public void registerEvent() {
        broker.register(this, EventConstant.LOGIN_SUCCESS);
        broker.register(this, EventConstant.LOGIN_FAILED);
        broker.register(this, EventConstant.CONNECTION_ERROR);
        broker.register(this, EventConstant.INTERNAL_ERROR);
    }

    public void unregisterEvent() {
        broker.unRegister(this, EventConstant.LOGIN_SUCCESS);
        broker.unRegister(this, EventConstant.LOGIN_FAILED);
        broker.unRegister(this, EventConstant.CONNECTION_ERROR);
        broker.unRegister(this, EventConstant.INTERNAL_ERROR);
    }
}
