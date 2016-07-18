package com.kms.cura.view.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kms.cura.R;
import com.kms.cura.entity.json.JsonToEntityConverter;
import com.kms.cura.entity.user.DoctorUserEntity;
import com.kms.cura.view.fragment.DoctorProfileFragment;

public class ViewDoctorProfileActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    private DoctorUserEntity doctorUserEntity;
    private Bundle bundle;
    private ImageView btnBack;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor_profile);
        setUpToolbarForViewDoctor();
        btnBack = setUpButton();
        loadFragemnt();
    }

    private void loadFragemnt() {
        data = getIntent().getStringExtra(SearchActivity.DOCTOR_SELECTED);
        doctorUserEntity = JsonToEntityConverter.convertJsonStringToEntity(data, DoctorUserEntity.getDoctorEntityType());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flProfileView, DoctorProfileFragment.newInstance(doctorUserEntity));
        transaction.commit();
    }

    private ImageView setUpButton() {
        ImageView button = (ImageView) findViewById(R.id.btnBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return button;
    }

    private void setUpToolbarForViewDoctor() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_view_doctor);
        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.book_appt_action) {
            Intent toBookAppts = new Intent(ViewDoctorProfileActivity.this, BookAppointmentActivity.class);
            toBookAppts.putExtra(SearchActivity.DOCTOR_SELECTED, data);
            ViewDoctorProfileActivity.this.startActivity(toBookAppts);
            return true;
        }
        return false;
    }
}
