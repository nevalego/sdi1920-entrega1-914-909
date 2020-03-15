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

import com.uniovi.tests.pageobjects.PO_FriendsView;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_Publications;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_UserListView;
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
	//static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	//static String Geckdriver024 = "D:\\Universidad\\SDI\\Pruebas Selenium\\geckodriver024win64.exe";

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
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.getP();
		// Nos dirigimos a la opcion añadir usuarios que solo puede ser accedida por el admin
		PO_NavView.checkNavMode(driver, "users-menu", "btnAddUser");
		SeleniumUtils.textoPresentePagina(driver, "Agregar usuario");
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
		PO_RegisterView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
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
		PO_RegisterView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
		PO_View.getP();
		// Comprobamos que devuelve error por contraseña invalida
		// PO_RegisterView.checkKey(driver, "Error.login.password.notMatch",
		// PO_Properties.getSPANISH());

	}

	// Prueba 9 Desconexion
	@Test
	public void Prueba9() {
		// Vamos al formulario de loggin
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();
		PO_HomeView.clickOption(driver, "/logout", "class", "btn btn-primary");
		PO_HomeView.checkElement(driver, "text", "Identificate");

	}

	// Prueba 10 Boton desconexion no visible si no se esta loggeado
	@Test
	public void Prueba10() {
		PO_HomeView.checkIdentification(driver, PO_Properties.getSPANISH());
	}

	// Prueba 11 Mostrar todos los usuarios registrados
	@Test
	public void Prueba11() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();

		// Nos dirigimos a la lista de usuarios
		PO_NavView.checkNavMode(driver, "users-menu", "btnListUsers");

		// Comprobamos que el usuario loggeado no esta presente
		SeleniumUtils.textoNoPresentePagina(driver, "pedrod@gmail.com");
		// El admin tampoco debe ser visible
		SeleniumUtils.textoNoPresentePagina(driver, "admin@email.com");

		// Comprobamos que los demas usuarios aparecen todos
		SeleniumUtils.textoPresentePagina(driver, "lucasnu@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "mariar@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "maral@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "pelaval@gmail.com");

	}

	// Prueba 12 Realizar busqueda vacia y comprobar que salen todos los resultados
	@Test
	public void Prueba12() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();

		// Nos dirigimos a la lista de usuarios
		PO_NavView.checkNavMode(driver, "users-menu", "btnListUsers");

		// Realizamos la busqueda con campos vacios
		PO_UserListView.makeASearch(driver, "");

		// Comprobamos que el usuario loggeado no esta presente
		SeleniumUtils.textoNoPresentePagina(driver, "pedrod@gmail.com");
		// El admin tampoco debe ser visible
		SeleniumUtils.textoNoPresentePagina(driver, "admin@email.com");

		// Comprobamos que los demas usuarios aparecen todos
		SeleniumUtils.textoPresentePagina(driver, "lucasnu@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "mariar@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "maral@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "pelaval@gmail.com");

	}

	// Prueba 13 Realizar busqueda con terminos no existentes
	@Test
	public void Prueba13() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();

		// Nos dirigimos a la lista de usuarios
		PO_NavView.checkNavMode(driver, "users-menu", "btnListUsers");

		// Realizamos la busqueda con campos vacios
		PO_UserListView.makeASearch(driver, "Politico honrado");

		// Comprobamos que el usuario loggeado no esta presente
		SeleniumUtils.textoNoPresentePagina(driver, "pedrod@gmail.com");
		// El admin tampoco debe ser visible
		SeleniumUtils.textoNoPresentePagina(driver, "admin@email.com");

		// Comprobamos que los demas usuarios tampoco aparecen
		SeleniumUtils.textoNoPresentePagina(driver, "lucasnu@gmail.com");
		SeleniumUtils.textoNoPresentePagina(driver, "mariar@gmail.com");
		SeleniumUtils.textoNoPresentePagina(driver, "maral@gmail.com");
		SeleniumUtils.textoNoPresentePagina(driver, "pelaval@gmail.com");

	}

	// Prueba 14 Realizar busqueda especifica y aparecen los usuarios concretos
	@Test
	public void Prueba14() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();

		// Nos dirigimos a la lista de usuarios
		PO_NavView.checkNavMode(driver, "users-menu", "btnListUsers");

		// Realizamos la busqueda con campos vacios
		PO_UserListView.makeASearch(driver, "Mar");

		// Comprobamos que el usuario loggeado no esta presente
		SeleniumUtils.textoNoPresentePagina(driver, "pedrod@gmail.com");
		// El admin tampoco debe ser visible
		SeleniumUtils.textoNoPresentePagina(driver, "admin@email.com");

		// Comprobamos que los usuarios que corresponden aparecen

		SeleniumUtils.textoPresentePagina(driver, "mariar@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "maral@gmail.com");

		// Comprobamos que no aparecen usuarios que no corresponden
		SeleniumUtils.textoNoPresentePagina(driver, "lucasnu@gmail.com");
		SeleniumUtils.textoNoPresentePagina(driver, "pelaval@gmail.com");
	}

	// Prueba 15, enviar una invitacion a un usuario
	@Test
	public void Prueba15() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "lucasnu@gmail.com", "123456");
		PO_View.getP();

		// Nos dirigimos a la lista de usuarios
		PO_NavView.checkNavMode(driver, "users-menu", "btnListUsers");

		// Mandamos una peticion de amistad
		PO_UserListView.addFriend(driver, "maral@gmail.com");

		// Salimos de sesion
		PO_HomeView.clickOption(driver, "/logout", "class", "btn btn-primary");

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario del usuario al que enviamos la peticion
		PO_LoginView.fillForm(driver, "maral@gmail.com", "123456");

		PO_NavView.checkNavMode(driver, "friends-menu", "btnListInvitations");

		// Comprobamos que el usuario que corresponde aparece
		SeleniumUtils.textoPresentePagina(driver, "lucasnu@gmail.com");
	}

	// Prueba 16, enviar una invitacion a un usuario al que ya se le envio una
	// invitacion
	@Test
	public void Prueba16() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();

		// Nos dirigimos a la lista de usuarios
		PO_NavView.checkNavMode(driver, "users-menu", "btnListUsers");

		// Mandamos una peticion de amistad
		PO_UserListView.addFriend(driver, "lucasnu@gmail.com");

		// Comprobamos que salta el mensaje de error
		SeleniumUtils.textoPresentePagina(driver, "Ya existe una invitacion para este usuario");
	}

	// Prueba 17, Comprobar que un usuario tiene mas de una invitacion de amistad
	@Test
	public void Prueba17() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();

		// Nos dirigimos a la lista de invitaciones
		PO_NavView.checkNavMode(driver, "friends-menu", "btnListInvitations");

		// Comprobamos que aparecen los usuarios dichos
		SeleniumUtils.textoPresentePagina(driver, "mariar@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "admin@email.com");
	}

	// Prueba 18, Comprobar que un usuario desaparece cuando aceptas su invitacion
	@Test
	public void Prueba18() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();

		// Nos dirigimos a la lista de invitaciones
		PO_NavView.checkNavMode(driver, "friends-menu", "btnListInvitations");
		SeleniumUtils.textoPresentePagina(driver, "mariar@gmail.com");
		PO_FriendsView.aceptInvitation(driver, "mariar@gmail.com");

		// Comprobamos que salta el mensaje de error
		SeleniumUtils.textoNoPresentePagina(driver, "mariar@gmail.com");
	}

	// Prueba 19 Mostrar listado de amigos de un usuario y que este completa
	@Test
	public void Prueba19() {
		// Hacemos login para accerder a todas las funciones
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();

		// Nos movemos a la opcion listar amigos
		PO_NavView.checkNavMode(driver, "friends-menu", "btnListFriends");

		// Comprobamos que el usuario que corresponde aparece
		SeleniumUtils.textoPresentePagina(driver, "maral@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "pelaval@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "mariar@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "lucasnu@gmail.com");
		
		// Comprobamos que no aparece ninguno mas
		SeleniumUtils.textoNoPresentePagina(driver, "pedrod@gmail.com");

	}

	// Prueba 20 Realizar prueba de internacionalizacion en 4 páginas
	@Test
	public void Prueba20() {
		// Comprobamos la página de inicio
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());

		// Hacemos login para accerder a todas las funciones
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();

		// Hacemos comprobacion de las opciones de la barra de navegacion tienen la
		// internacionalizacion correcta
		PO_NavView.checkNavBarChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());

		// Nos dirigimos a la Agregar Publicaciones
		PO_NavView.checkNavMode(driver, "publications-menu", "btnAddPublication");

		PO_Publications.checkAddPublicationChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());

		// Nos dirigimos a la Listar Publicaciones
		PO_NavView.checkNavMode(driver, "publications-menu", "btnListPublications");
		PO_Publications.checkMyPublicationsListChangeIdiom(driver, "btnSpanish", "btnEnglish",
				PO_Properties.getSPANISH(), PO_Properties.getENGLISH());

		// Hacemos la comprobacion de la pantalla de Gestion de Usuarios Ver usuarios
		// Nos dirigimos a la lista de usuarios
		PO_NavView.checkNavMode(driver, "users-menu", "btnListUsers");
		PO_UserListView.checkUserListChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());

		// Hacemos la comprobacion de la pantalla de Gestion de Usuarios Ver usuarios
		// Nos dirigimos a la lista de usuarios
		PO_NavView.checkNavMode(driver, "friends-menu", "btnListInvitations");
		PO_FriendsView.checkMyInvitationsListChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());

	}

	// Prueba 21, Comprobar que no se puede listar usuarios sin loggearse
	// Y que te redirige a login
	@Test
	public void Prueba21() {
		// Intento 1, comprobamos que se nos redirige a la pantalla de identificarse
		// En lugar de la lista de usuarios
		driver.navigate().to("http://localhost:8091/user/list");
		SeleniumUtils.textoPresentePagina(driver, "Identificate");
		SeleniumUtils.textoNoPresentePagina(driver, "Buscar");
		SeleniumUtils.textoNoPresentePagina(driver, "Usuarios");

	}

	// Prueba 22, Comprobar que no se puede listar publicaciones sin logearse
	// Y que te redirige a login
	@Test
	public void Prueba22() {
		// Intento 1, comprobamos que se nos redirige a la pantalla de identificarse
		// En lugar de la lista de usuarios
		driver.navigate().to("http://localhost:8091/publication/list");
		SeleniumUtils.textoPresentePagina(driver, "Identificate");
		SeleniumUtils.textoNoPresentePagina(driver, "Publicaciones");
		SeleniumUtils.textoNoPresentePagina(driver, "Actualizar");

	}

	// Prueba 23, Comprobar que no se puede acceder a opciones de admin como
	// usuario estandar
	@Test
	public void Prueba23() {
		// Comprobamos que desde admin se puede acceder
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario admin
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.getP();
		// Intentamos acceder a una opcion exclusiva de admin como es agregar usuarios
		driver.navigate().to("http://localhost:8091/user/add");
		SeleniumUtils.textoPresentePagina(driver, "Agregar usuario");
		PO_HomeView.clickOption(driver, "/logout", "class", "btn btn-primary");

		// Nos dirigimos a login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();
		// Intentamos acceder a una opcion exclusiva de admin como es agregar usuarios
		driver.navigate().to("http://localhost:8091/user/add");
		// Comprobamos que nos salta una pantalla con el error de sesion prohibida
		PO_View.checkElement(driver, "h1", "HTTP Status 403 – Forbidden");

	}

	// Prueba 24, Añadir una publicacion y comprobar que se ha introducido
	@Test
	public void Prueba24() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();

		// Nos dirigimos a la lista de publicaciones
		PO_NavView.checkNavMode(driver, "publications-menu", "btnAddPublication");
		// Agregamos una publicacion especifica
		PO_Publications.addPublications(driver, "This is Prueba24", "Prueba");
		// Comprobamos que no nos saco de la pagina de Publicaciones
		PO_Publications.checkMyPublicationsText(driver, PO_Properties.getSPANISH());

		// Comprobamos que esta nuestra nueva publicacion
		SeleniumUtils.textoPresentePagina(driver, "This is Prueba24");
	}

	// Prueba 25, Comprobar que en caso de no rellenar los campos al agregar una
	// publicacion
	// Salta un error
	@Test
	public void Prueba25() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();

		// Nos dirigimos a la lista de publicaciones
		PO_NavView.checkNavMode(driver, "publications-menu", "btnAddPublication");
		PO_Publications.addPublications(driver, "", "texto");
		// Comprobamos que no nos saco de la pagina de Publicaciones
		PO_Publications.checkAddPublication(driver, PO_Properties.getSPANISH());
	}

	// Prueba 26, Comprobar que todas las publicaciones de un usuario aparecen
	@Test
	public void Prueba26() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "pedrod@gmail.com", "123456");
		PO_View.getP();

		// Nos dirigimos a la lista de publicaciones
		PO_NavView.checkNavMode(driver, "publications-menu", "btnListPublications");

		// Comprobamos que aparecen las publicaciones esperadas
		SeleniumUtils.textoPresentePagina(driver, "Buenos dias");
		SeleniumUtils.textoPresentePagina(driver, "Me ha pasado algo genial");
	}

	// Prueba 27, Comprobar que se muestran las publicaciones de un amigo
	@Test
	public void Prueba27() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "maral@gmail.com", "123456");
		PO_View.getP();

		// Nos dirigimos a la lista de amigos
		PO_NavView.checkNavMode(driver, "friends-menu", "btnListFriends");

		PO_FriendsView.seeFriendsDetails(driver, "pedrod@gmail.com");
		// Comprobamos que aparecen las publicaciones esperadas
		SeleniumUtils.textoPresentePagina(driver, "Buenos dias");
		SeleniumUtils.textoPresentePagina(driver, "Me ha pasado algo genial");
	}

	// Prueba 28, Comprobar que no se entra en la pagina de publicaciones de una
	// persona de la que no eres amigo
	@Test
	public void Prueba28() {
		// Intento 1, comprobamos que si intentamos acceder al perfil de una persona
		// sin loggearse nos lleva directamente a identificar
		driver.navigate().to("http://localhost:8091/friendship/details/10");
		SeleniumUtils.textoPresentePagina(driver, "Identificate");

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario sin amigos
		PO_LoginView.fillForm(driver, "lucasnu@gmail.com", "123456");
		PO_View.getP();

		// Nos dirigimos directamente al perfil de amigo de maral@gmail.com
		// Entre la cual no comparten amistad
		driver.navigate().to("http://localhost:8091/friendship/details/10");

		// Comprobamos que nos redirige a la pagina de amigos
		PO_UserListView.checkUserListText(driver, PO_Properties.getSPANISH());

	}

	// Prueba 31 Mostrar todos los usuarios desde admin
	@Test
	public void Prueba31() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con un usuario standar
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.getP();

		// Nos dirigimos a la lista de usuarios
		PO_NavView.checkNavMode(driver, "users-menu", "btnListUsers");

		// Comprobamos que el usuario loggeado no esta presente
		SeleniumUtils.textoPresentePagina(driver, "pedrod@gmail.com");
		// El admin tampoco debe ser visible
		SeleniumUtils.textoPresentePagina(driver, "admin@email.com");

		// Comprobamos que los demas usuarios aparecen todos
		SeleniumUtils.textoPresentePagina(driver, "lucasnu@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "mariar@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "maral@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "pelaval@gmail.com");
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}
}