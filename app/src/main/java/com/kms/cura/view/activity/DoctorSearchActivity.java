package com.kms.cura.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.kms.cura.R;
import com.kms.cura.entity.ConditionEntity;
import com.kms.cura.view.fragment.PatientHomeFragment;

public class DoctorSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private Fragment patientHomeFragment;
    private ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_search);
        btnBack = (ImageButton) findViewById(R.id.btnDocSearchBack);
        btnBack.setOnClickListener(this);
        patientHomeFragment = PatientHomeFragment.newInstance(this, this);
        patientHomeFragment.setArguments(createConditionBundle(getIntent()));
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentDocSearch, patientHomeFragment).commit();
    }


    private Bundle createConditionBundle(Intent intent) {
        Bundle bundle = new Bundle();
        bundle.putString(ConditionEntity.ID, intent.getStringExtra(ConditionEntity.ID));
        bundle.putString(ConditionEntity.NAME, intent.getStringExtra(ConditionEntity.NAME));
        bundle.putString(ConditionEntity.DESCRIPTION, intent.getStringExtra(ConditionEntity.DESCRIPTION));
        bundle.putBoolean(PatientHomeFragment.AUTO_FILL, true);
        return bundle;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDocSearchBack) {
            finish();
        }
    }
}
