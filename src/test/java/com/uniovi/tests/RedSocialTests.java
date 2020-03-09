package com.uniovi.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

// Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class RedSocialTests {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)): //static String PathFirefox65 = "C:\\Program Files\\Mozilla
	// Firefox\\firefox.exe";
	// static String Geckdriver024 = "C:\\Path\\geckodriver024win64.exe"; //En
	// MACOSX (Debe ser la versión 65.0.1
	// y desactivar las actualizacioens automáticas):
	
	// Rutas Miguel
	// static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	// static String Geckdriver024 = "D:\\Universidad\\SDI\\Pruebas Selenium\\geckodriver024win64.exe";
	
	// Rutas Nerea
	static String PathFirefox65 = "/Archivos de programa/Mozilla Firefox/firefox.exe";
	static String Geckdriver024 = "/Users/nerea/Documents/2 SEMESTRE/SDI/5. Web testing con Selenium/PL-SDI-Sesión5-material/PL-SDI-Sesión5-material/geckodriver024win64.exe";
	
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8091";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() throws Exception {
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {

	}

	// Prueba1. Registro de usuario con datos válidos
	@Test
	public void Prueba1() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "/signup", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "jose@hotmail.com", "Josefo", "Perez", "123", "123");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "jose@hotmail.com");
	}
	
	// Prueba2. Registro de Usuario con datos inválidos (email vacío, nombre vacío, apellidos vacíos).
	@Test
	public void Prueba2() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "/signup", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "", "", "", "123", "123");
		// Comprobamos el error de campo vacío
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getENGLISH());
	}

	// Prueba3. Registro de Usuario con datos inválidos (repetición de contraseña inválida).
	@Test
	public void Prueba3() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "/signup", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "jose@hotmail.com", "Josefo", "Perez", "123", "123");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "jose@hotmail.com");
	}
	
	//Prueba4. Registro de Usuario con datos inválidos (email existente).
	@Test
	public void Prueba4() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "/signup", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "jose@hotmail.com", "Josefo", "Perez", "123", "123");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "jose@hotmail.com");
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}
}