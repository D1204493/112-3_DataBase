package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
  public static void main(String[] args) {

    WebDriver driver = new ChromeDriver();
    driver.get("https://www.vscinemas.com.tw/vsweb/film/index.aspx");
    System.out.println(driver.getTitle());
    System.out.println();

  /*
    DateTime now = new DateTime();
    DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd"); //設定格式
    String strNow = format.print(now);
    System.out.println(strNow);  //印出現在日期

    DateTime day40 = now.plusDays(40);
    String strDay40 = format.print(day40);
    System.out.println(strDay40);
  */

    DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd"); //設定格式
    List<Movie> sortMovieDate = new ArrayList<>();

    try {
    for (int i = 1; i <= 3; i++) {
      driver.findElements(By.cssSelector(".pagebar a")).get(i).click();

      List<WebElement> elements = driver.findElements(By.cssSelector(".movieList .infoArea"));
      for (WebElement element : elements) {
        WebElement nameElement = element.findElement(By.cssSelector("a"));
        WebElement engNameElement = element.findElement(By.cssSelector("h3"));
        WebElement startDateElement = element.findElement(By.cssSelector("time"));

        DateTime dateStr = DateTime.parse(startDateElement.getText());
        DateTime day40 = dateStr.plusDays(40);
        String strDay40 = format.print(day40); // 日期格式為yyyy-MM-dd
//        System.out.println(strDay40);

        Movie movie = new Movie(nameElement.getText(), engNameElement.getText(), startDateElement.getText(), strDay40);
        sortMovieDate.add(movie);
//        System.out.println(nameElement.getText());
//        System.out.println(engNameElement.getText());
//        System.out.println(startDateElement.getText());
//        System.out.println();
      }
      Collections.sort(sortMovieDate);
      System.out.println(sortMovieDate);
    }

      try (FileWriter writer = new FileWriter("moviesInfo.csv");
           CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("電影名稱", "電影英文名稱", "上映日期", "下映日期"))) {

        for(Movie movieDate: sortMovieDate) {  //先把陣列的資料抓出來，再把陣列裡面要的資料抓出來
          printer.printRecord(movieDate.getName(),movieDate.getEnglishName(),movieDate.getDate(),movieDate.getStrDay40());
        }

      } catch (NoSuchElementException | NumberFormatException e) {
        e.printStackTrace();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

  } finally {
      driver.quit();
    }



  }
}