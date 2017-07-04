package com.litvinenko.testtasksergii.activity;


import com.litvinenko.testtasksergii.R;
import com.litvinenko.testtasksergii.data.ApiService;
import com.litvinenko.testtasksergii.data.pojo.ReceivedResponse;
import com.litvinenko.testtasksergii.data.RetroClient;
import com.litvinenko.testtasksergii.data.pojo.User;
import com.litvinenko.testtasksergii.fragments.UserInfoFragment;
import com.litvinenko.testtasksergii.fragments.UserListFragment;
import com.litvinenko.testtasksergii.fragments.UserListFragment.IOnMyUserClickListener;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IOnMyUserClickListener {

  private ArrayList<User> users = new ArrayList<>();

  private FragmentManager fManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);

    fManager = getSupportFragmentManager();

    // Checking Internet Connection

    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

    if (cm.getActiveNetworkInfo() != null) {

      // Progress Dialog for User Interaction
      final ProgressDialog dialog;
      dialog = new ProgressDialog(MainActivity.this);
      dialog.setTitle("Connect");
      dialog.setMessage("Getting Json");
      dialog.show();

      // Creating an object of our api interface
      ApiService api = RetroClient.getApiService();

      // Calling JSON
      Call<ReceivedResponse> call = api.getMyJSON();

      // Enqueue Callback will be call when get response...
      call.enqueue(new Callback<ReceivedResponse>() {
        @Override
        public void onResponse(@NonNull Call<ReceivedResponse> call,
            @NonNull Response<ReceivedResponse> response) {

          //Dismiss Dialog
          dialog.dismiss();

          if (response.isSuccessful()) {

            // Got Successfully, add users from results

            for (int i = 0; i < response.body().getResults().size(); i++) {
              users.add(new User(response.body().getResults().get(i).getGender(),
                  response.body().getResults().get(i).getName().getTitle(),
                  response.body().getResults().get(i).getName().getFirst(),
                  response.body().getResults().get(i).getName().getLast(),
                  response.body().getResults().get(i).getPicture().getLarge(),
                  response.body().getResults().get(i).getNat()));
            }

            UserListFragment fList = UserListFragment.newInstance(users);
            FragmentTransaction fTrans = fManager.beginTransaction();
            fTrans.replace(R.id.flFragmentContainer, fList, "List");
            fTrans.commit();

          } else {
            Toast.makeText(getBaseContext(), "Failed", Toast.LENGTH_SHORT).show();
          }
        }

        @Override
        public void onFailure(@NonNull Call<ReceivedResponse> call, @NonNull Throwable t) {
          dialog.dismiss();
        }
      });

    } else {
      Toast.makeText(getBaseContext(), "Internet connection not available",
          Toast.LENGTH_SHORT).show();
    }
  }

  /**
   * User list click
   *
   * @param position position of user clicked
   */
  @Override
  public void onUserClick(int position) {
    UserInfoFragment fInfo = UserInfoFragment.newInstance(users.get(position));
    FragmentTransaction fTrans = fManager.beginTransaction();
    fTrans.replace(R.id.flFragmentContainer, fInfo, "Info");
    fTrans.addToBackStack(null);
    fTrans.commit();
  }

  /**
   * Pop fragment from back stack
   */
  @Override
  public void onBackPressed() {
    if (fManager.getBackStackEntryCount() > 0) {
      fManager.popBackStack();
    } else {
      super.onBackPressed();
    }
  }
}
