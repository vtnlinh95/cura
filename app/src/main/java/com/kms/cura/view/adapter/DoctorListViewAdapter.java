package com.kms.cura.view.adapter;

/**
 * Created by duyhnguyen on 6/14/2016.
 */

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kms.cura.R;
import com.kms.cura.entity.SpecialityEntity;
import com.kms.cura.entity.user.DoctorUserEntity;
import com.kms.cura.entity.user.UserEntity;

public class DoctorListViewAdapter extends BaseAdapter {
    DoctorUserEntity[] result;
    Context context;
    int[] imageId;
    private static LayoutInflater inflater = null;

    public DoctorListViewAdapter(Activity activity, DoctorUserEntity[] users) {
        // TODO Auto-generated constructor stub
        result = users;
        context = activity;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView name;
        TextView speciality;
        RatingBar raiting;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_row, null);
        holder.name = (TextView) rowView.findViewById(R.id.doctorName);
        holder.speciality = (TextView) rowView.findViewById(R.id.speciality);
        holder.raiting = (RatingBar) rowView.findViewById(R.id.ratingBar);

        holder.name.setText(result[position].getName());
        StringBuilder sb = new StringBuilder();
        for(SpecialityEntity specialityEntity : result[position].getSpeciality()){
            sb.append(specialityEntity.getName());
            sb.append(", ");
        }
        sb.delete(sb.length()-1,sb.length());

        holder.speciality.setText(sb.toString());
        holder.raiting.setRating((float) result[position].getRating());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}
