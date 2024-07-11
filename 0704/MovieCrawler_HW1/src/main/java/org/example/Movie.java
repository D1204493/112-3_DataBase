package org.example;

import org.openqa.selenium.WebElement;

public class Movie implements Comparable<Movie> {
  private String name;
  private String englishName;
  private String date;
  private String strDay40;


  public Movie(String name, String englishName, String date, String strDay40) {
    this.name = name;
    this.englishName = englishName;
    this.date = date;
    this.strDay40 = strDay40;
  }

  public String getName() {
    return name;
  }

  public String getEnglishName() {
    return englishName;
  }

  public String getDate() {
    return date;
  }

  public String getStrDay40() {
    return strDay40;
  }

  @Override
  public String toString() {
    return  name + "," +
            englishName + "," +
            date + "," +
            strDay40 ;
  }

  @Override
  public int compareTo(Movie otherMovie) {
    return this.date.compareTo(otherMovie.getDate());
  }




}
