package com.ceiba.parqueadero.funcionales;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class registrarVehiculoIT {
	private static WebDriver driver = null;

	@BeforeClass
	public static void inicializarDriver() {
		/*
		 * System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver.exe");
		 * driver = new ChromeDriver();
		 */
	}

	@AfterClass
	public static void liquidarDriver() {
		// driver.quit();
	}

	@Test
	public void comprobarFlujoCorrectoRegistrarVehiculo() {
		/*
		 * driver.get("http://localhost:4200/parqueadero/");
		 * 
		 * WebElement butonRegistrarElement = driver.findElement(By.id("registrar"));
		 * butonRegistrarElement.click();
		 * 
		 * WebDriverWait wait1 = new WebDriverWait(driver, 10); WebElement placaElement
		 * = driver.findElement(By.id("placa"));
		 * 
		 * wait1.until(ExpectedConditions.visibilityOf(placaElement));
		 * 
		 * placaElement.sendKeys("ZDR125");
		 * 
		 * WebElement butonFormRegistrarElement =
		 * driver.findElement(By.id("formRegistrar"));
		 * butonFormRegistrarElement.click();
		 * 
		 * WebDriverWait wait = new WebDriverWait(driver, 1); WebElement mensajeCreado =
		 * driver.findElement(By.id("mensajeSucces"));
		 * wait.until(ExpectedConditions.visibilityOf(mensajeCreado));
		 */
	}
}
