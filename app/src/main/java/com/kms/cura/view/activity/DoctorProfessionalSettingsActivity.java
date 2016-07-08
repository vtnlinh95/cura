package com.kms.cura.view.activity;


import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.Toast;

import com.kms.cura.R;
import com.kms.cura.entity.FacilityEntity;
import com.kms.cura.entity.SpecialityEntity;
import com.kms.cura.entity.user.DoctorUserEntity;
import com.kms.cura.utils.CurrentUserProfile;
import com.kms.cura.view.adapter.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DoctorProfessionalSettingsActivity extends AppCompatActivity {
    private static String ACTIVITY_NAME = "Professional";
    private ExpandableListAdapter listAdapter;
    private Toolbar toolbar;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private DoctorUserEntity entity;
    private ListView listView;
    private HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_professional_settings);
        entity = (DoctorUserEntity) CurrentUserProfile.getInstance().getEntity();
        initToolbar();

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listAdapter.setChildButtonText(0, "Add Specialties");
        listAdapter.setChildButtonText(1, "Add Facilities");
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

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.ProfessionalSettings, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        listView.setHeaderDividersEnabled(true);
        listView.setFooterDividersEnabled(true);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Specialties");
        listDataHeader.add("Facilities");

        // Adding child data
        List<String> Specialties = new ArrayList<String>();
        List<SpecialityEntity> specialityEntities = entity.getSpeciality();
        for (SpecialityEntity specialty : specialityEntities) {
            Specialties.add(specialty.getName());
        }
        Specialties.add("");

        List<String> Facilities = new ArrayList<String>();
        List<FacilityEntity> facilityEntities = entity.getFacilities();
        for (FacilityEntity facility : facilityEntities) {
            Facilities.add(facility.getName());
        }
        Facilities.add("");


        listDataChild.put(listDataHeader.get(0), Specialties); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Facilities);

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
}