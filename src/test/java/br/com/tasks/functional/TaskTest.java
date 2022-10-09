package br.com.tasks.functional;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TaskTest {
	
	public static String remote_url_chrome = "http://hib-1:4444/wd/hub";

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		driver.set(new RemoteWebDriver(new URL(remote_url_chrome), options));
		driver.navigate().to("http://tomcat:8000/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaCinSucesso() {
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
