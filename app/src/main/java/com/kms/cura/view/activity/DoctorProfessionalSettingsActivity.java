package com.kms.cura.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.kms.cura.R;
import com.kms.cura.entity.Entity;
import com.kms.cura.entity.user.DoctorUserEntity;
import com.kms.cura.utils.CurrentUserProfile;
import com.kms.cura.view.adapter.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DoctorProfessionalSettingsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static String ACTIVITY_NAME = "Professional";
    private static int SPECIALTIES_WORKING_HOUR_POSITION = 0;
    private static int FACILITIES_OTHER__POSITION = 1;
    private ExpandableListAdapter listAdapter;
    private Toolbar toolbar;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private DoctorUserEntity entity;
    private ListView listView;
    private HashMap<String, List<String>> listDataChild;
    List<String> specialties, facilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_professional_settings);
        entity = (DoctorUserEntity) CurrentUserProfile.getInstance().getEntity();

        initToolbar();

        initExpandableListView();

        initListView();
    }

    private void initListView() {
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.ProfessionalSettings, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        listView.setHeaderDividersEnabled(true);
        listView.setFooterDividersEnabled(true);
        listView.setOnItemClickListener(this);
    }

    private void initExpandableListView() {
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listAdapter.setChildButtonText(SPECIALTIES_WORKING_HOUR_POSITION, getResources().getString(R.string.add_specialties));
        listAdapter.setChildButtonText(FACILITIES_OTHER__POSITION, getResources().getString(R.string.add_facilities));
        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setFooterDividersEnabled(true);
        expListView.setChildIndicator(null);
        expListView.setChildDivider(getResources().getDrawable(R.color.transparent));
        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (v.getId() == R.id.button_x_delete_button) {
                    Toast.makeText(
                            getApplicationContext(),
                            listDataHeader.get(groupPosition)
                                    + " : "
                                    + listDataChild.get(
                                    listDataHeader.get(groupPosition)).get(
                                    childPosition), Toast.LENGTH_SHORT)
                            .show();

                }
                return true;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding Group data
        listDataHeader.add(getResources().getString(R.string.specialties));
        listDataHeader.add(getResources().getString(R.string.facilities));

        // Adding child data
        ArrayList<Entity> dummy = new ArrayList<Entity>();
        dummy.addAll(entity.getSpeciality());

        specialties = getEntitiesData(dummy);

        dummy.clear();
        dummy.addAll(entity.getFacilities());

        facilities = getEntitiesData(dummy);

        listDataChild.put(listDataHeader.get(SPECIALTIES_WORKING_HOUR_POSITION), specialties); // Header, Child data
        listDataChild.put(listDataHeader.get(FACILITIES_OTHER__POSITION), facilities);

    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.doctorProfessional_toolbar);
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

    private List<String> getEntitiesData(ArrayList<Entity> entities) {

        List<String> result = new ArrayList<String>();
        for (Entity specialty : entities) {
            result.add(specialty.getName());
        }
        result.add("");
        return result;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        if (position == SPECIALTIES_WORKING_HOUR_POSITION) {
            intent = new Intent(this, DoctorWorkingHourSettingsActivity.class);
        } else{
            intent = new Intent(this, OtherSettingsActivity.class);
        }
        startActivity(intent);

    }

}