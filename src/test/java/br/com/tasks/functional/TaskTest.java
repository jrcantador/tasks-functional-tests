package br.com.tasks.functional;

import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TaskTest {	
	public Capabilities chromeCapabilities = DesiredCapabilities.chrome();

	public WebDriver acessarAplicacao() throws MalformedURLException {
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), chromeCapabilities);		
		driver.navigate().to("http://tomcat:8000/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaCinSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("saveButton")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String message = driver.findElement(By.id("message")).getText();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Assert.assertEquals("Success!", message);
		} finally {
			driver.quit();
		}
	}

}
