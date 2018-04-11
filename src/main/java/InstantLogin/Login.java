package InstantLogin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anish Maharjan
 */
public interface Login 
{
    public static WebDriver driver = new ChromeDriver();
    
    public void login(String username, String password, int i) throws InterruptedException;
}
