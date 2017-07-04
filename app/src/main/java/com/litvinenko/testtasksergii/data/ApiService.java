package com.litvinenko.testtasksergii.data;

import com.litvinenko.testtasksergii.Constants.Network;
import com.litvinenko.testtasksergii.data.pojo.ReceivedResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {

  /**
   * Retrofit get annotation with URL, will return ReceivedResponse
   */
  @GET(Network.PARAMS)
  Call<ReceivedResponse> getMyJSON();
}