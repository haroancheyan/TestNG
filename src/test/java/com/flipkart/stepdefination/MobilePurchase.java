package com.flipkart.stepdefination;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobilePurchase extends Sample{
	@DataProvider(name = "mobile")
	public Object[][] mobileName(){
		return new Object[][] {{"SAMSUNG MOBILE"}};
	}
	
	@DataProvider(name = "tv")
	public Object[][] tvName(){
		return new Object[][] {{"SAMSUNG TV"}};
	}
	
	static WebDriver driver; 
	@BeforeClass(groups = "default")
	public static void launch() {
		System.out.println("launch");
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		
	}
    @AfterClass(groups = "default")
	public static void quit() {
    	System.out.println("quit");
		driver.quit();
		
	}
    long StartTime;
	@BeforeMethod(groups = "default")
    public void beforeMethod() {
		System.out.println("BEfore method");
		StartTime = System.currentTimeMillis();
    }
	
	@AfterMethod(groups = "default")
    public void afterMethod() {
		System.out.println("After method");
		long endtime = System.currentTimeMillis();
		System.out.println("Time Taken :"+(endtime-StartTime));
		
    }
	
	@Test(priority=1,groups = "haroan")
    public void method1() {
		System.out.println("login");
        driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
    }
	
	@Test(priority=2,dataProvider="mobile",groups = "haroan")
    public void method2(String name) {
		String[]name2 = name.split(" ");
		System.out.println("Search");
		driver.findElement(By.name("q")).sendKeys(name,Keys.ENTER);
		WebElement element = driver.findElement(By.xpath("(//div[contains(text(),'"+name2[0]+"')])[4]"));
		
			String name1 = element.getText();
			System.out.println(name1);
		    element.click();
    }
	
	@Test(priority=3,groups = "haroan")
    public void method3() {
		Set<String> id = driver.getWindowHandles();
		List<String> child = new ArrayList<>(id);
		driver.switchTo().window(child.get(1));
		
		WebElement element2 = driver.findElement(By.xpath("//span[contains(@class,'B_NuCI')]"));
		String product4 = element2.getText();
		System.out.println("Selected product is "+product4);
    }
	
	@Test(priority=4)
    public void method4() {
		WebElement buy = driver.findElement(By.xpath("//button[contains(@class,'_2KpZ6l _2U9uOA ihZ75k _3AWRsL')]"));
		WebElement spec = driver.findElement(By.xpath("//div[contains(text(),'Specifications')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", spec);
		String text = buy.getText();
		
        Assert.assertTrue(buy.isDisplayed());
		
		Assert.assertEquals("BUY", text);
		
    }
}
