package fcu.course;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
  public static void main(String[] args) {

    WebDriver driver = new ChromeDriver();
    driver.get("https://www.vscinemas.com.tw/vsweb/film/index.aspx");
    System.out.println(driver.getTitle());
    System.out.println();

    /* 測試1
    try {
    List<WebElement> elements = driver.findElements(By.cssSelector(".movieList .infoArea"));

    for(WebElement element : elements) {
      WebElement nameElement = element.findElement(By.cssSelector("a"));
      WebElement engNameElement = element.findElement(By.cssSelector("h3"));
      WebElement startDateElement = element.findElement(By.cssSelector("time"));
      System.out.println(nameElement.getText());
      System.out.println(engNameElement.getText());
      System.out.println(startDateElement.getText());
     }
    } catch (NoSuchElementException e) {
      e.printStackTrace();
    } finally {
      driver.quit();
    }
}
     */

    //測試3
    try(FileWriter writer = new FileWriter("movies.csv");
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("電影名稱","英文名稱","日期"))) {
      List<WebElement> elements = driver.findElements(By.cssSelector(".movieList .infoArea"));
      for (WebElement element : elements) {
        WebElement nameElement = element.findElement(By.cssSelector("a"));
        WebElement engNameElement = element.findElement(By.cssSelector("h3"));
        WebElement startDateElement = element.findElement(By.cssSelector("time"));
        printer.printRecord(nameElement.getText(),engNameElement.getText(),startDateElement.getText());
//        System.out.println(nameElement.getText());
//        System.out.println(engNameElement.getText());
//        System.out.println(startDateElement.getText());
//        System.out.println();
      }
    } catch (NoSuchElementException | IOException e) {
      e.printStackTrace();
    } finally {
      driver.quit();
    }


    /* 測試2
    List<WebElement> elementsTop = driver.findElements(By.cssSelector(".mainLink > a"));
    for (WebElement element : elementsTop) {
      System.out.println(element.getText());
    }
    driver.quit();
     */


  }
}