/* Author : Sandeep Dasari
 */

import org.testng.annotations.Test; 
import java.io.FileReader; 
import java.io.IOException; 
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
  
public class AbbviePublish { 
	public WebDriver driver ;
	
	
	@Test
    public void abbviePublish() throws IOException, InterruptedException { 
    	
        FileReader reader=new FileReader("C:\\Users\\Sandeep Kumar Dasari\\eclipse-workspace\\SampleProject\\src\\main\\java\\prop\\abbvieUrl.properties"); 
        Properties props=new Properties(); 
        props.load(reader); 
        
        driver = new ChromeDriver(); 
        driver.manage().window().maximize();       
        driver.get(props.getProperty("BASEURL"));
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(props.getProperty("UID"));
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(props.getProperty("PWD"));
        driver.findElement(By.xpath("//button[@id='submit-button']")).click();
            
        for (int i = 1; i <= 2347; i++)
        {	

        driver.get(props.getProperty("URL"+i));  
        
        if(driver.findElements(By.xpath("//button[@class='coral3-Button coral3-Button--warning']")).size()!=0)
        {
        	driver.findElement(By.xpath("//button[@class='coral3-Button coral3-Button--warning']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[@id='pageinfo-trigger']")).click();
            Thread.sleep(2000);       
            driver.findElement(By.xpath("//button[@title='Publish Page']")).click(); 
            Thread.sleep(2000); 
        }

 //       if(driver.findElements(By.xpath("//button[@class='coral3-Button coral3-Button--warning']")).size()==0)
        else
        {
            driver.findElement(By.xpath("//a[@id='pageinfo-trigger']")).click();
            Thread.sleep(2000);       
            driver.findElement(By.xpath("//button[@title='Publish Page']")).click(); 
            Thread.sleep(2000); 
        	
        }
          
        int st= driver.findElements(By.xpath("//coral-alert-content[contains(text(),'The page has been published')]")).size();
        if(st !=0)
        {
        	System.out.println("Published successfully");
        } else {System.out.println("Not published");}
        
        System.out.println("Published URL"+i); 
         
        }      
   
        driver.close();
    } 
}


