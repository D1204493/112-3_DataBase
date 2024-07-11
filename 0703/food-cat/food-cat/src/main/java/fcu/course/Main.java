package fcu.course;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Main {
  public static void main(String[] args) {
//    Student tom = new Student("001","Tom",20,"Computer");
//    System.out.println(tom.toString());

    DateTime now = new DateTime();
    System.out.println(now);

    DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    String strNow = format.print(now);
    System.out.println(strNow);

    String xmasDay = "2024-12-25 00:00:00";
    DateTime xmas = format.parseDateTime(xmasDay);
    System.out.println(xmas);

    DateTime day20 = now.minusWeeks(1);
    String strDay20 = format.print(day20);
    System.out.println(strDay20);

    day20 = xmas.plusDays(20);
    strDay20 = format.print(day20);
    System.out.println(strDay20);


    try {
      FileUtils.copyFile(new File("pom.xml"),new File("pom.backup"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


  }
}