package com.litvinenko.testtasksergii.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Result {

  @SerializedName("gender")
  @Expose
  private String gender;
  @SerializedName("name")
  @Expose
  private Name name;
  @SerializedName("picture")
  @Expose
  private Picture picture;
  @SerializedName("nat")
  @Expose
  private String nat;

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Picture getPicture() {
    return picture;
  }

  public void setPicture(Picture picture) {
    this.picture = picture;
  }

  public String getNat() {
    return nat;
  }

  public void setNat(String nat) {
    this.nat = nat;
  }

}