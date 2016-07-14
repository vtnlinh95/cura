package com.kms.cura.view.activity;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kms.cura.R;
import com.kms.cura.controller.ErrorController;
import com.kms.cura.controller.SymptomController;
import com.kms.cura.entity.ConditionEntity;
import com.kms.cura.entity.SymptomEntity;
import com.kms.cura.view.fragment.AddDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class ConditionInfoActivity extends AppCompatActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener, AdapterView.OnItemClickListener {

    public static final String DIALOG_TAG = "addDialog";
    public static final String TYPE = "Condition";
    private ConditionEntity conditionEntity;
    private List<SymptomEntity> symptomEntities;
    private TextView tvDescription, btnSeeMore;
    private ListView lvSymptom;
    private Button btnFind;
    private ImageButton btnBack;
    private ArrayAdapter<String> adapter;
    private ProgressDialog pDialog;
    private boolean expand = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition_info);
        conditionEntity = new ConditionEntity(getIntent().getStringExtra(ConditionEntity.ID),
                getIntent().getStringExtra(ConditionEntity.NAME),
                getIntent().getStringExtra(ConditionEntity.DESCRIPTION));
        setUpButton();
        showDescription();
        showCommonSymptom();
        modifyToolbar();
    }

    private void setUpButton() {
        btnFind = (Button) findViewById(R.id.btnFindDoc);
        btnFind.setOnClickListener(this);
        btnBack = (ImageButton) findViewById(R.id.btnConditionBack);
        btnBack.setOnClickListener(this);
        btnSeeMore = (TextView) findViewById(R.id.btnSeeMore);
        btnSeeMore.setOnClickListener(this);
    }

    private void showDescription() {
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvDescription.setText(conditionEntity.getDescription());
    }

    private void showCommonSymptom() {
        lvSymptom = (ListView) findViewById(R.id.lvCommonSymptom);
        lvSymptom.setOnItemClickListener(this);

        new AsyncTask<Object, Void, Void>() {
            private Exception exception = null;

            @Override
            protected void onPreExecute() {
                pDialog = new ProgressDialog(ConditionInfoActivity.this);
                pDialog.setMessage("Loading...");
                pDialog.setCancelable(false);
                showProgressDialog();
            }

            @Override
            protected Void doInBackground(Object[] params) {
                try {
                    symptomEntities = SymptomController.getAssociatedSymptom(conditionEntity);
                } catch (Exception e) {
                    exception = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                hideProgressDialog();
                if (exception != null) {
                    ErrorController.showDialog(ConditionInfoActivity.this, "Error : " + exception.getMessage());
                } else {
                    adapter = new ArrayAdapter<>(ConditionInfoActivity.this, android.R.layout.simple_list_item_1, getSymptomNameList(symptomEntities));
                    lvSymptom.setAdapter(adapter);
                    setListViewHeightBasedOnChildren(lvSymptom);
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

    private ArrayList<String> getSymptomNameList(List<SymptomEntity> symptomEntities) {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < symptomEntities.size(); ++i) {
            names.add(symptomEntities.get(i).getName());
        }
        return names;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnFindDoc) {
            Intent intent = new Intent(this, DoctorSearchActivity.class);
            intent.putExtra(ConditionEntity.ID, conditionEntity.getId());
            intent.putExtra(ConditionEntity.NAME, conditionEntity.getName());
            intent.putExtra(ConditionEntity.DESCRIPTION, conditionEntity.getDescription());
            startActivity(intent);
        } else if (v.getId() == R.id.btnConditionBack) {
            finish();
        } else if (v.getId() == R.id.btnSeeMore) {
            changeState();
        }
    }

    private void changeState() {
        if (!expand) {
            expand = true;
            ObjectAnimator animation = ObjectAnimator.ofInt(tvDescription, "maxLines", 40);
            animation.setDuration(500).start();
            btnSeeMore.setText(getString(R.string.seeLess));
        } else {
            expand = false;
            ObjectAnimator animation = ObjectAnimator.ofInt(tvDescription, "maxLines", 4);
            animation.setDuration(500).start();
            btnSeeMore.setText(getString(R.string.seeMore));
        }
    }

    private void modifyToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbConditionInfo);
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
        SymptomEntity entity = symptomEntities.get(position);
        Intent intent = new Intent(this, SymptomInfoActivity.class);
        intent.putExtra(SymptomEntity.ID, entity.getId());
        intent.putExtra(SymptomEntity.NAME, entity.getName());
        startActivity(intent);
    }

    private Bundle createBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(AddDialogFragment.TITLE_KEY, getString(R.string.add_title_condition));
        bundle.putString(AddDialogFragment.TYPE_KEY, TYPE);
        bundle.putString(ConditionEntity.ID, conditionEntity.getId());
        bundle.putString(ConditionEntity.NAME, conditionEntity.getName());
        bundle.putString(ConditionEntity.DESCRIPTION, conditionEntity.getDescription());
        return bundle;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LinearLayoutCompat.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
