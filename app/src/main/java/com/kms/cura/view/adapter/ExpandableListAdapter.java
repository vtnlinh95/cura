package com.kms.cura.view.adapter;

/**
 * Created by duyhnguyen on 7/7/2016.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kms.cura.R;
import com.kms.cura.controller.UserController;
import com.kms.cura.view.activity.AccountTypeSelectionActivity;
import com.kms.cura.view.activity.BookAppointmentActivity;

public class ExpandableListAdapter extends BaseExpandableListAdapter implements View.OnClickListener {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private List<String> buttonText;
    boolean expanded;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        buttonText = new ArrayList<String>();

    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null || expanded == true) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
            ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.button_x_delete_button);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), _listDataHeader.get(groupPosition) + " : " + _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (childPosition % 2 == 0) {
            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.light_grey_2));
        }
        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        //the last row is used as footer
        if (isLastChild) {
            LayoutInflater inflater = (LayoutInflater) convertView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_footer, null);
            Button button = (Button) convertView.findViewById(R.id.button_list_item_button);
            button.setText(buttonText.get(groupPosition));
            button.setOnClickListener(this);
            expanded = true;
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTextColor(convertView.getContext().getResources().getColor(R.color.black));
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setChildButtonText(int parentGroup, String text) {
        buttonText.add(parentGroup, text);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_list_item_button) {
            Button button = (Button) v;
            String buttonText = button.getText().toString();

            if (buttonText.equals("Add Specialties")) {
                Toast.makeText(v.getContext(), "Add new Specialities", Toast.LENGTH_SHORT).show();
            } else if (buttonText.equals("Add Facilities")) {
                Toast.makeText(v.getContext(), "Add new Facilities", Toast.LENGTH_SHORT).show();
            }
        }


    }
}