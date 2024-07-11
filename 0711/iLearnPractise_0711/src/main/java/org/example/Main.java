package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
  public static void main(String[] args) {

    WebDriver driver = new ChromeDriver();
    driver.get("https://ilearn.fcu.edu.tw/");

//    List<WebElement> elements = driver.findElements(By.cssSelector(".movieList .infoArea"));

    driver.findElement(By.cssSelector("span.login > a")).click();
//      WebElement loginLink = driver.findElement(By.linkText("登入"));
//      loginLink.click();

    driver.findElement(By.cssSelector("div.login-form-username > input")).sendKeys("D1204493");
    driver.findElement(By.cssSelector("div.login-form-password > input")).sendKeys("fcu1410605020");
    driver.findElement(By.cssSelector("div.login-form-submit > button")).click();

//    WebElement option = driver.findElement(By.cssSelector("select option"));
//    Select select = new Select(option);
//    select.selectByValue("A9999");

    driver.findElement(By.xpath("//select[@name='lmc-filtersemester']/option[@value='A9999']")).click();

    List<WebElement> elements = driver.findElements(By.cssSelector(".teachers span"));
    for (WebElement element : elements) {
      String teacherName = element.getText();
      System.out.println(teacherName);
    }

    try (FileWriter writer = new FileWriter("iLearnTeacherInfo.csv");
         CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("課堂老師"))) {

      for (WebElement element : elements) {
        String teacherName = element.getText();
        printer.printRecord(teacherName);
      }

    } catch (NoSuchElementException | NumberFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      driver.quit();
    }


  }
}


