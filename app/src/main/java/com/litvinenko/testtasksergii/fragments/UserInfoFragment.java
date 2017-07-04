package com.litvinenko.testtasksergii.fragments;


import com.litvinenko.testtasksergii.Constants;
import com.litvinenko.testtasksergii.R;
import com.litvinenko.testtasksergii.data.pojo.User;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInfoFragment extends Fragment {

  public static UserInfoFragment newInstance(User user) {
    Bundle args = new Bundle();
    args.putParcelable("User", user);
    UserInfoFragment fragment = new UserInfoFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_info, container, false);
    User user = getArguments().getParcelable("User");

    ImageView image = (ImageView) v.findViewById(R.id.ivInfoImage);
    TextView title = (TextView) v.findViewById(R.id.tvInfoTitle);
    TextView firstName = (TextView) v.findViewById(R.id.tvInfoFirst);
    TextView lastName = (TextView) v.findViewById(R.id.tvInfoLast);
    TextView nationality = (TextView) v.findViewById(R.id.tvInfoNat);
    TextView gender = (TextView) v.findViewById(R.id.tvInfoGender);

    // Set data

    if (user != null) {
      title.setText(user.getTitle());
      firstName.setText(user.getFirstName());
      lastName.setText(user.getLastName());
      nationality.setText(user.getNationality());
      gender.setText(user.getGender());
      new DownloadImageTask(image).doInBackground(user.getPicture());
    }

    return v;
  }

  private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView image;

    DownloadImageTask(ImageView image) {
      this.image = image;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
      Bitmap result = null;
      try {
        URL url = new URL(urls[0]);

        // Download bitmap using URL

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();
        result = BitmapFactory.decodeStream(input);
        input.close();
        connection.disconnect();
      } catch (Exception e) {
        Log.e(Constants.TAG, "ERROR in asynktask " + e.getMessage());
        e.printStackTrace();
      }
      return result;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
      image.setImageBitmap(bitmap);
    }
  }
}
