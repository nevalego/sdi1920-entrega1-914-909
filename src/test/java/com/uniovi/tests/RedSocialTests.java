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
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
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
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "D:\\Universidad\\SDI\\Pruebas Selenium\\geckodriver024win64.exe";

	// Rutas Nerea
	// static String PathFirefox65 = "/Archivos de programa/Mozilla
	// Firefox/firefox.exe";
	// static String Geckdriver024 = "/Users/nerea/Documents/2 SEMESTRE/SDI/5. Web
	// testing con
	// Selenium/PL-SDI-Sesión5-material/PL-SDI-Sesión5-material/geckodriver024win64.exe";

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
		PO_RegisterView.fillForm(driver, "prueba@hotmail.com", "Prueba", "Prueba", "123", "123");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "prueba@hotmail.com");
	}

	// Prueba 2. Registro de Usuario con datos inválidos (email vacío, nombre vacío,
	// apellidos vacíos).
	@Test
	public void Prueba2() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "/signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "", "Josefo", "Perez", "123456", "123456");
		PO_View.getP();
		// COmprobamos el error de email vacio.
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
		// Comprobamos el error de ultimo caracter alfabetico de DNI
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "j@m", "", "", "", "");
		// COmprobamos el error de Nombre corto .
		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
		// Comprobamos el error de longitud de email
		PO_RegisterView.checkKey(driver, "Error.signup.email.length", PO_Properties.getSPANISH());
		// COmprobamos el error de Apellido corto .
		PO_RegisterView.checkKey(driver, "Error.signup.lastName.length", PO_Properties.getSPANISH());
		// COmprobamos el error de longitud de contraseña .
		PO_RegisterView.checkKey(driver, "Error.signup.password.length", PO_Properties.getSPANISH());

	}

	@Test
	public void Prueba3() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		// Caso 1:
		PO_RegisterView.fillForm(driver, "josefo2@correo.com", "Josefo", "Perez", "77777", "66666");
		PO_View.getP();
		// COmprobamos el error de DNI repetido.
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());

		// Caso 2:
		PO_RegisterView.fillForm(driver, "josefo2@correo.com", "Jose", "Perez", "1", "1");
		// COmprobamos el error de Nombre corto .
		PO_RegisterView.checkKey(driver, "Error.signup.password.length", PO_Properties.getSPANISH());
	}

	@Test
	public void Prueba4() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		// Caso 1:
		PO_RegisterView.fillForm(driver, "pedrod@gmail.com", "Pedro", "Perez", "123", "123");
		PO_View.getP();
		// COmprobamos el error de email repetido repetido.
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());

	}

	// Prueba 5 inicio de sesion como admin
	@Test
	public void Prueba5() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario.
		// Caso 1:
		PO_LoginView.fillForm(driver, "ednu@gmail.com", "123456");
		PO_View.getP();
		// Nos dirigimos a la opcion lista de usuarios
		PO_NavView.checkNavMode(driver, "users-menu", "btnListUsers");
		// Comprobamos que el email del usuario no se encuentra entre los usuarios
		// mostrados
		PO_View.checkElement(driver, "text", "ednu@gmail.com");
	}

	// Prueba 6 inicio de sesion como user
	@Test
	public void Prueba6() {
		// Vamos al formulario de loggin
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();
		// Nos dirigimos a la opcion lista de usuarios
		PO_NavView.checkNavMode(driver, "users-menu", "btnListUsers");
		// Comprobamos que el email del usuario no se encuentra entre los usuarios
		// mostrados
		SeleniumUtils.textoNoPresentePagina(driver, "pedrod@gmail.com");

	}

	// Prueba 7 inicio de sesion con datos invalidos
	// Campos Vacios
	@Test
	public void Prueba7() {
		// Vamos al formulario de loggin
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "", "");
		PO_View.getP();
		// En caso de que se dejen vacios devuelve los mensajes de error respectivos
		// PO_RegisterView.checkKey(driver, "Error.login.email.empty",
		// PO_Properties.getSPANISH());
		// PO_RegisterView.checkKey(driver, "Error.login.password.empty",
		// PO_Properties.getSPANISH());
		// PO_RegisterView.checkKey(driver, "Error.login.email.notExist",
		// PO_Properties.getSPANISH());
	}

	// Prueba 8 inicio de sesion con datos invalidos
	// Contraseña incorrecta
	@Test
	public void Prueba8() {
		// Vamos al formulario de loggin
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "444444");
		PO_View.getP();
		// Comprobamos que devuelve error por contraseña invalida
		// PO_RegisterView.checkKey(driver, "Error.login.password.notMatch",
		// PO_Properties.getSPANISH());

	}
	
	// Prueba 9 Desconexion
		// Contraseña incorrecta
		@Test
		public void Prueba9() {
			// Vamos al formulario de loggin
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
			// Rellenamos el formulario con un usuario standar
			PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
			PO_View.getP();
			PO_HomeView.clickOption(driver, "/logout", "class", "btn btn-primary");
			PO_HomeView.checkElement(driver, "text", "Identificate");
			// Comprobamos que devuelve error por contraseña invalida
			// PO_RegisterView.checkKey(driver, "Error.login.password.notMatch",
			// PO_Properties.getSPANISH());

		}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}
}