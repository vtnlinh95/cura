package com.kms.cura.view.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.kms.cura.R;
import com.kms.cura.entity.SpecialityEntity;
import com.kms.cura.entity.user.DoctorUserEntity;
import com.kms.cura.view.UpdateSpinner;
import com.kms.cura.view.adapter.DoctorListViewAdapter;
import com.kms.cura.view.adapter.SpinnerHintAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class searchActivity extends AppCompatActivity implements OnItemSelectedListener {
    private UpdateSpinner updateSpinner;
    private int checkedSort;
    private ArrayAdapter<CharSequence> adapter;
    private ListView lv;
    private Spinner spinner;

    List<SpecialityEntity> list1 = new ArrayList<SpecialityEntity>();
    List<SpecialityEntity> list2 = new ArrayList<SpecialityEntity>();
    List<SpecialityEntity> list3 = new ArrayList<SpecialityEntity>();
    DoctorUserEntity u1 = new DoctorUserEntity("1", "Duy", "duy123@yahoo.com", "123456", null, null, list1, 5, 1, 5, 6, null, null, null, null, null);
    DoctorUserEntity u2 = new DoctorUserEntity("2", "Dung", "dung123@yahoo.com", "123456", null, null, list2, 4, 5, 10, 50, null, null, null, null, null);
    DoctorUserEntity u3 = new DoctorUserEntity("3", "Linh", "linh123@yahoo.com", "123456", null, null, list2, 2, 1, 15, 15, null, null, null, null, null);
    DoctorUserEntity u4 = new DoctorUserEntity("4", "Toan", "toan123@yahoo.com", "123456", null, null, list3, 3.5, 1, 20, 26, null, null, null, null, null);
    DoctorUserEntity u5 = new DoctorUserEntity("5", "Jenny", "Jenny123@yahoo.com", "123456", null, null, list1, 2.5, 1, 10, 13, null, null, null, null, null);
    private List<DoctorUserEntity> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        list1.add(new SpecialityEntity(null, "Dentist"));
        list2.add(new SpecialityEntity(null, "Surgeion"));
        list2.add(new SpecialityEntity(null, "Dentist"));
        list2.add(new SpecialityEntity(null, "abv"));
        list2.add(new SpecialityEntity(null, "sdf"));
        list2.add(new SpecialityEntity(null, "czasdfadfxv"));
        list3.add(new SpecialityEntity(null, "ertcggdfgarasdfasdfasdfeerzxcvzxcvert"));
        list3.add(new SpecialityEntity(null, "Denterist"));
        list3.add(new SpecialityEntity(null, "sdfs"));
        list3.add(new SpecialityEntity(null, "sdf"));
        list3.add(new SpecialityEntity(null, "Dewewerqwerwerqwerntxcvist"));
        list3.add(new SpecialityEntity(null, "Dentis22222t"));

        users = initArray();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_search);
        lv = (ListView) findViewById(R.id.listView1);
        lv.setAdapter(new DoctorListViewAdapter(this, users));

        initAdapter();
        initSpinner();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    private List<DoctorUserEntity> initArray() {
        List<DoctorUserEntity> array = new ArrayList<DoctorUserEntity>();
        array.add(u1);
        array.add(u2);
        array.add(u3);
        array.add(u4);
        array.add(u5);
        return array;
    }

    private void initAdapter() {
        adapter = ArrayAdapter.createFromResource(this, R.array.sort_by, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    private void initSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(
                new SpinnerHintAdapter(
                        adapter,
                        R.layout.sort_spinner_row,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        if (position == 1) {
            Collections.sort(users, new NameComparator());
        } else if (position == 2) {
            Collections.sort(users, new RatingComparator());
        } else if (position == 3) {
            Collections.sort(users, new PriceComparator());
        }
        lv.setAdapter(new DoctorListViewAdapter(this, users));
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    static class NameComparator implements Comparator<DoctorUserEntity> {
        public int compare(DoctorUserEntity u1, DoctorUserEntity u2) {
            String a1 = u1.getName();
            String a2 = u2.getName();
            return a1.compareTo(a2);
        }
    }

    static class RatingComparator implements Comparator<DoctorUserEntity> {
        public int compare(DoctorUserEntity u1, DoctorUserEntity u2) {
            Double a1 = u1.getRating();
            Double a2 = u2.getRating();
            return a2.compareTo(a1);
        }
    }

    static class PriceComparator implements Comparator<DoctorUserEntity> {
        public int compare(DoctorUserEntity u1, DoctorUserEntity u2) {
            Double a1 = u1.getMinPrice();
            Double a2 = u2.getMinPrice();
            return a1.compareTo(a2);
        }
    }
}

