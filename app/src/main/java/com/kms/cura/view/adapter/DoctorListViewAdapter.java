package com.kms.cura.view.adapter;

/**
 * Created by duyhnguyen on 6/14/2016.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
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
    List<DoctorUserEntity> result;
    Context context;
    int[] imageId;
    private static LayoutInflater inflater = null;

    public DoctorListViewAdapter(Activity activity, List<DoctorUserEntity> users) {
        // TODO Auto-generated constructor stub
        result = users;
        context = activity;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
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

        holder.name.setText(result.get(position).getName());
        holder.speciality.setText(getSpecialityString(position));
        holder.raiting.setRating((float) result.get(position).getRating());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked " + result.get(position), Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

    @NonNull
    private String getSpecialityString(int position) {
        StringBuilder sb = new StringBuilder();
        for (SpecialityEntity specialityEntity : result.get(position).getSpeciality()) {
            sb.append(specialityEntity.getName());
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        if (sb.length() > 50) {
            sb.replace(50, sb.length(), "...");
            sb.trimToSize();
        }
        return sb.toString();
    }

}
