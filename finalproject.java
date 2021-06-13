package ff;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class finalproject {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\prajwal1\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(5,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://sso.eservices.jud.ct.gov/foreclosures/Public/PendPostbyTownList.aspx");
        WebElement ele = driver.findElement(By.xpath("//*[contains(text(),'Bridgeport')]"));
		
		JavascriptExecutor je = (JavascriptExecutor)driver;
		je.executeScript("arguments[0].scrollIntoView();", ele);
		ele.click();
		
		List<WebElement> rowelements = driver.findElements(By.xpath("//*[@id='ctl00_cphBody_GridView1']/tbody/tr"));
		
		int rowsize = rowelements.size();
		
		
		for (int i=2;i<=rowsize;i++) 
		
		{
			
			String Link = "//*[@id='ctl00_cphBody_GridView1']/tbody/tr["+ i +"]/td[5]";
			 
			String actaulcalvalue = "//*[@id='ctl00_cphBody_GridView1']/tbody/tr["+ i +"]/td[2]"; 
			WebElement ele1 = driver.findElement(By.xpath(actaulcalvalue));
			WebElement ele2 = driver.findElement(By.xpath(Link));
			String value = ele1.getText();
			String[] tokens = value.split("\\r?\\n");
			String datesvalue = tokens[0];
			// Date seperated from time
			LocalDate listdate = LocalDate.parse(datesvalue, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        	LocalDate Currentdate	= LocalDate.now();  
        	
        	// Local date value compared the date from list
			Period period = Period.between(Currentdate, listdate);
			System.out.println(period.getDays());
			
			long diffdays = period.getDays();
			
			if(diffdays <= 15) 
			{	
				String opentabs = Keys.chord(Keys.CONTROL,Keys.ENTER);
				ele2.findElement(By.tagName("a")).sendKeys(opentabs);
			}
			
			
			
			
		}
		driver.close();

	}

}
