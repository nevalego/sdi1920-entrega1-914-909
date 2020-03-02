package com.uniovi.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class NotaneitorApplicationTests {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)): //static String PathFirefox65 = "C:\\Program Files\\Mozilla
	// Firefox\\firefox.exe";
	// static String Geckdriver024 = "C:\\Path\\geckodriver024win64.exe"; //En
	// MACOSX (Debe ser la versión 65.0.1
	// y desactivar las actualizacioens automáticas):
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

	// Prueba 1. Registro de Usuario con datos válidos. 
	@Test
	public void Prueba1() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "/signup", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "pepe@correo.com", "Josefo", "Perez Nuñez", "123456", "123456");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "pepe@correo.com");
	}

	// Prueba 2.  Registro de Usuario con datos inválidos (email vacío, nombre vacío, apellidos vacíos). 
	@Test
	public void Prueba2() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "/signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "08993990A", "Josefo", "Perez", "77777", "77777");
		PO_View.getP();
		// COmprobamos el error de DNI repetido.
		PO_RegisterView.checkKey(driver, "Error.signup.dni.duplicate", PO_Properties.getSPANISH());
		// Comprobamos el error de longitud de DNI
		PO_RegisterView.checkKey(driver, "Error.signup.dni.length", PO_Properties.getSPANISH());
		// Comprobamos el error de ultimo caracter alfabetico de DNI
		PO_RegisterView.checkKey(driver, "Error.signup.dni.char", PO_Properties.getSPANISH());
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "08993990A", "Jose", "Perez", "77777", "77777");
		// COmprobamos el error de Nombre corto .
		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
		// COmprobamos el error de Apellido corto .
		PO_RegisterView.checkKey(driver, "Error.signup.lastName.length", PO_Properties.getSPANISH());
		// COmprobamos el error de longitud de contraseña .
		PO_RegisterView.checkKey(driver, "Error.signup.password.length", PO_Properties.getSPANISH());
		// Comprobamos el error de coindidencia de contraseña
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "08993990A", "Josefo", "Per", "77777", "77777");

	}
	
	// Prueba 3  Registro de Usuario con datos inválidos (repetición de contraseña inválida). 
	@Test
	public void Prueba3() {
		
	}
	
	// Prueba 4	 Registro de Usuario con datos inválidos (email existente). 
	@Test
	public void Prueba4() {
		
	}
	

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}
}
