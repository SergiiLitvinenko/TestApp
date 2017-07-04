package com.litvinenko.testtasksergii.data;


import com.litvinenko.testtasksergii.Constants.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

  /**
   * Get Retrofit Instance
   */
  private static Retrofit getRetrofitInstance() {
    return new Retrofit.Builder()
        .baseUrl(Network.ROOT_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  /**
   * Get API Service
   *
   * @return API Service
   */
  public static ApiService getApiService() {
    return getRetrofitInstance().create(ApiService.class);
  }

}
