package com.litvinenko.testtasksergii.data.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

  public User(String gender, String title, String firstName, String lastName,
      String picture, String nationality) {
    this.gender = gender;
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.picture = picture;
    this.nationality = nationality;
  }

  public User(Parcel in) {
    gender = in.readString();
    title = in.readString();
    firstName = in.readString();
    lastName = in.readString();
    picture = in.readString();
    nationality = in.readString();
  }

  private String gender;
  private String title;
  private String firstName;
  private String lastName;
  private String picture;
  private String nationality;

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(gender);
    dest.writeString(title);
    dest.writeString(firstName);
    dest.writeString(lastName);
    dest.writeString(picture);
    dest.writeString(nationality);
  }

  public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

    public User createFromParcel(Parcel in) {
      return new User(in);
    }

    public User[] newArray(int size) {

      return new User[size];
    }
  };
}
