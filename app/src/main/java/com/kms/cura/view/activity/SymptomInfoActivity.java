package com.kms.cura.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.kms.cura.R;
import com.kms.cura.controller.ConditionController;
import com.kms.cura.controller.ErrorController;
import com.kms.cura.entity.ConditionEntity;
import com.kms.cura.entity.SymptomEntity;
import com.kms.cura.view.fragment.AddDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class SymptomInfoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, Toolbar.OnMenuItemClickListener {

    public static final String DIALOG_TAG = "addDialog";
    public static final String TYPE = "Symptom";
    private SymptomEntity symptomEntity;
    private List<ConditionEntity> conditionEntities;
    private ListView lvCondition;
    private ImageButton btnBack;
    private ArrayAdapter<String> adapter;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_info);
        symptomEntity = new SymptomEntity(getIntent().getStringExtra(ConditionEntity.ID),
                getIntent().getStringExtra(ConditionEntity.NAME));
        showCommonCondition();
        setUpButton();
        modifyToolbar();
    }

    private void setUpButton() {
        btnBack = (ImageButton) findViewById(R.id.btnSymptomBack);
        btnBack.setOnClickListener(this);
    }


    private void showCommonCondition() {
        lvCondition = (ListView) findViewById(R.id.lvCommonCondition);
        lvCondition.setOnItemClickListener(this);
        new AsyncTask<Object, Void, Void>() {
            private Exception exception = null;

            @Override
            protected void onPreExecute() {
                pDialog = new ProgressDialog(SymptomInfoActivity.this);
                pDialog.setMessage("Loading...");
                pDialog.setCancelable(false);
                showProgressDialog();
            }

            @Override
            protected Void doInBackground(Object[] params) {
                try {
                    conditionEntities = ConditionController.getAssociatedCondition(symptomEntity);
                } catch (Exception e) {
                    exception = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                hideProgressDialog();
                if (exception != null) {
                    ErrorController.showDialog(SymptomInfoActivity.this, "Error : " + exception.getMessage());
                } else {
                    adapter = new ArrayAdapter<>(SymptomInfoActivity.this, android.R.layout.simple_list_item_1, getConditionNameList(conditionEntities));
                    lvCondition.setAdapter(adapter);
                }
            }
        }.execute();
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private ArrayList<String> getConditionNameList(List<ConditionEntity> conditionEntities) {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < conditionEntities.size(); ++i) {
            names.add(conditionEntities.get(i).getName());
        }
        return names;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSymptomBack) {
            finish();
        }
    }

    private void modifyToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbSymptomInfo);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_add);
        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.btnAdd) {
            Bundle bundle = createBundle();
            AddDialogFragment addDialog = new AddDialogFragment();
            addDialog.setArguments(bundle);
            addDialog.show(getSupportFragmentManager(), DIALOG_TAG);
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ConditionEntity entity = conditionEntities.get(position);
        Intent intent = new Intent(this, ConditionInfoActivity.class);
        intent.putExtra(ConditionEntity.ID, entity.getId());
        intent.putExtra(ConditionEntity.NAME, entity.getName());
        intent.putExtra(ConditionEntity.DESCRIPTION, entity.getDescription());
        startActivity(intent);
    }

    private Bundle createBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(AddDialogFragment.TITLE_KEY, getString(R.string.add_title_symptom));
        bundle.putString(AddDialogFragment.TYPE_KEY, TYPE);
        bundle.putString(SymptomEntity.ID, symptomEntity.getId());
        bundle.putString(SymptomEntity.NAME, symptomEntity.getName());
        return bundle;
    }
}
