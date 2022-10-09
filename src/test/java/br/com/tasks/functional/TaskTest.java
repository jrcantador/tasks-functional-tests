package br.com.tasks.functional;

import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TaskTest {		

	public WebDriver acessarAplicacao() throws MalformedURLException {
		ChromeOptions chromeOptions = new ChromeOptions();
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://hub:4444/wd/hub"), chromeOptions);	
		driver.navigate().to("http://tomcat:8000/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaCinSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("tasks")).sendKeys("Teste via selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			driver.findElement(By.id("saveButton")).click();
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success", message);
		} finally {
			driver.quit();
		}
	}

}
