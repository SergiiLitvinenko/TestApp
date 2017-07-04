package com.litvinenko.testtasksergii.fragments;


import com.litvinenko.testtasksergii.R;
import com.litvinenko.testtasksergii.data.ListArrayAdapter;
import com.litvinenko.testtasksergii.data.pojo.User;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class UserListFragment extends ListFragment {

  private IOnMyUserClickListener userClickListener;

  private ArrayList<User> users;

  public static UserListFragment newInstance(ArrayList<User> users) {
    Bundle args = new Bundle();
    args.putParcelableArrayList("Users", users);
    UserListFragment fragment = new UserListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    userClickListener = (IOnMyUserClickListener) activity;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_list, container, false);
    users = getArguments().getParcelableArrayList("Users");

    // Set custom adapter

    ListArrayAdapter adapter = new ListArrayAdapter(getContext(), R.layout.list_item, users);
    setListAdapter(adapter);

    return v;
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    userClickListener.onUserClick(position);
  }

  public interface IOnMyUserClickListener {

    void onUserClick(int position);
  }
}
