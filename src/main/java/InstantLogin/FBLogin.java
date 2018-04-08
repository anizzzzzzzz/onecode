package InstantLogin;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anish Maharjan
 */
public class FBLogin implements Login
{    
    private WebElement user,pass,btn;

    @Override
    public void login(String username, String password,int i) throws InterruptedException
    {
       System.setProperty("webdriver.gecko.driver","driver/geckodriver");
       
      driver.manage().window().maximize();
      
      int c=i;
      
      if(c==0)
      {
          driver.manage().window().maximize();
          driver.get("http://www.facebook.com");
          
          user=driver.findElement(By.name("email"));
          pass=driver.findElement(By.name("pass"));        
        
          user.sendKeys(username);
          pass.sendKeys(password);
          Thread.sleep(2000);
        
          btn=driver.findElement(By.id("loginbutton"));        
          btn.click();
         
      }
      else
      {
          try
          {
                  driver.manage().window().maximize();
                  String selectLinkOpeninNewTabs = Keys.chord(Keys.CONTROL,"t"); 
                  driver.findElement(By.tagName("body")).sendKeys(selectLinkOpeninNewTabs);
                  Thread.sleep(2000); 
                  
                  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles()); 
                  driver.switchTo().window(tabs.get(c)); 
                  driver.get("http://www.facebook.com");
                  
                  user=driver.findElement(By.name("email"));
                  pass=driver.findElement(By.name("pass"));        
        
                  user.sendKeys(username);
                  pass.sendKeys(password);
                  Thread.sleep(2000);
        
                  btn=driver.findElement(By.id("loginbutton"));        
                  btn.click();
                  
          }
          catch(Exception ex)
          {
              
          }
      }
    }
    
}
