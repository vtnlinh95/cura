package com.kms.cura.view.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kms.cura.R;
import com.kms.cura.entity.DayOfTheWeek;
import com.kms.cura.entity.OpeningHour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by linhtnvo on 6/23/2016.
 */
public class WorkingHourExpandableAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<HashMap<String, OpeningHour>> allworkingHours;

    public WorkingHourExpandableAdapter(Context mContext, List<HashMap<String, OpeningHour>> allworkingHours) {
        this.mContext = mContext;
        this.allworkingHours = allworkingHours;
    }

    @Override
    public int getGroupCount() {
        //The expandable listview has only one group so that return 1
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return DayOfTheWeek.values().length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        //Because we only have one group, so the id alwys is 0
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_working_hour_layout, null);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        HashMap<String, OpeningHour> workingHoursUnsort = allworkingHours.get(childPosition);
        List<Map.Entry<String,OpeningHour>> workingHours = new ArrayList<Map.Entry<String,OpeningHour>>(workingHoursUnsort.entrySet());
        Collections.sort(workingHours, new Comparator<Map.Entry<String, OpeningHour>>() {
            @Override
            public int compare(Map.Entry<String, OpeningHour> lhs, Map.Entry<String, OpeningHour> rhs) {
                if(lhs.getValue().getOpenTime().before(rhs.getValue().getOpenTime()) &&
                        lhs.getValue().getCloseTime().before(rhs.getValue().getCloseTime())){
                    return -1;
                }
                else{
                    return 1;
                }
            }
        });
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.working_hour_adapter, parent, false);
        }

        TextView dayOftheWeek = (TextView) convertView.findViewById(R.id.txtDayofTheWeek);
        LinearLayout workingTimeLayout = (LinearLayout) convertView.findViewById(R.id.layoutWorkingTime);
        if (workingTimeLayout.getChildCount() > 0) {
            workingTimeLayout.removeAllViews();
        }
        dayOftheWeek.setText(DayOfTheWeek.getDayOfTheWeek(childPosition).toString().substring(0, 3));
        if (workingHours.size() == 0) {
            workingTimeLayout.addView(createWorkingHourDetails("N/A", null));
        } else {
            for (int j=0; j<workingHours.size(); ++j) {
                Map.Entry<String,OpeningHour> entry = workingHours.get(j);
                workingTimeLayout.addView(createWorkingHourDetails(entry.getKey(), entry.getValue()));
            }
        }
        if (childPosition % 2 == 0) {
            dayOftheWeek.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_grey_2));
            workingTimeLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_grey_2));
        }
        return convertView;
    }

    private View createWorkingHourDetails(String faciliyName, OpeningHour openingHour) {
        LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(R.layout.working_hour_details, null);
        TextView txtWHTime = (TextView) root.findViewById(R.id.txtWHTime);
        TextView txtWHFacility = (TextView) root.findViewById(R.id.txtWHFacility);
        if (openingHour == null) {
            txtWHTime.setVisibility(View.GONE);
            txtWHFacility.setText(faciliyName);
            txtWHFacility.setGravity(Gravity.CENTER);
        } else {
            txtWHTime.setText(openingHour.getTime() + " | ");
            txtWHFacility.setText(faciliyName);
        }
        return root;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
