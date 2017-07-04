package com.litvinenko.testtasksergii.data;

import com.litvinenko.testtasksergii.Constants;
import com.litvinenko.testtasksergii.R;
import com.litvinenko.testtasksergii.data.pojo.User;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ListArrayAdapter extends ArrayAdapter<User> {

  public ListArrayAdapter(@NonNull Context context, @LayoutRes int resource,
      @NonNull List<User> objects) {
    super(context, resource, objects);
  }

  @SuppressLint("InflateParams")
  @NonNull
  @Override
  public View getView(int position, View convertView, @NonNull ViewGroup parent) {

    View v = convertView;

    if (v == null) {
      LayoutInflater li;
      li = LayoutInflater.from(getContext());
      v = li.inflate(R.layout.list_item, null);
    }

    User user = getItem(position);

    if (user != null) {
      TextView title = (TextView) v.findViewById(R.id.tvListTitle);
      TextView firstName = (TextView) v.findViewById(R.id.tvListFirst);
      TextView lastName = (TextView) v.findViewById(R.id.tvListLast);
      TextView nationality = (TextView) v.findViewById(R.id.tvListNat);
      TextView gender = (TextView) v.findViewById(R.id.tvListGender);

      if (title != null) {
        title.setText(user.getTitle());
      }

      if (firstName != null) {
        firstName.setText(user.getFirstName());
      }

      if (lastName != null) {
        lastName.setText(user.getLastName());
      }

      if (nationality != null) {
        nationality.setText(user.getNationality());
      }

      if (gender != null) {
        gender.setText(user.getGender());
      }
    }

    return v;
  }
}