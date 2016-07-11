package com.kms.cura.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.kms.cura.R;
import com.kms.cura.entity.OpeningHour;
import com.kms.cura.entity.WorkingHourEntity;
import com.kms.cura.entity.user.DoctorUserEntity;
import com.kms.cura.utils.CurrentUserProfile;
import com.kms.cura.view.adapter.WorkingHourExpandableSettingsAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DoctorWorkingHourSettingsActivity extends AppCompatActivity {
    private static String ACTIVITY_NAME = "Working Hours";
    private ExpandableListView listWorkingHour;
    private Toolbar toolbar;
    private List<WorkingHourEntity> listWH;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_working_hour_settings);
        DoctorUserEntity doctorUserEntity = (DoctorUserEntity) CurrentUserProfile.getInstance().getEntity();
        initToolbar();
        listWH = doctorUserEntity.getWorkingTime();
        setUpListWorkingHour();
    }

    private void setUpListWorkingHour() {
        listWorkingHour = (ExpandableListView) this.findViewById(R.id.listWorkingTimeSettings);
        List<HashMap<String, OpeningHour>> list = convertWorkingHour();
        WorkingHourExpandableSettingsAdapter adapter = new WorkingHourExpandableSettingsAdapter(this, list);
        listWorkingHour.setAdapter(adapter);
        listWorkingHour.setGroupIndicator(null);
        listWorkingHour.expandGroup(0);
        listWorkingHour.setChildDivider(getResources().getDrawable(R.color.black));
        listWorkingHour.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView arg0, View itemView, int itemPosition, long itemId) {
                listWorkingHour.expandGroup(itemPosition);
                return true;
            }
        });

    }

    private List<HashMap<String, OpeningHour>> convertWorkingHour() {
        List<HashMap<String, OpeningHour>> list = new ArrayList<>();
        for (int i = 0; i < 7; ++i) {
            HashMap<String, OpeningHour> workingtime = new HashMap<>();
            for (WorkingHourEntity workingHourEntity : listWH) {
                for (int k = 0; k < workingHourEntity.getWorkingTime().size(); ++k) {
                    if (workingHourEntity.getWorkingTime().get(k).getDayOfTheWeek().getCode() == i) {
                        workingtime.put(workingHourEntity.getFacilityEntity().getName(), workingHourEntity.getWorkingTime().get(k));
                    }
                }
            }
            list.add(workingtime);
        }
        return list;
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.working_hours_settings_toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(ACTIVITY_NAME);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
