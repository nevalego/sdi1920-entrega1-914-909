package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.*;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_NavView extends PO_View {

	/**
	 * * CLicka una de las opciones principales (a href) y comprueba que se vaya a
	 * la vista con el elemento de tipo type con el texto Destino * @param driver:
	 * apuntando al navegador abierto actualmente. * @param textOption: Texto de la
	 * opción principal. * @param criterio: "id" or "class" or "text" or
	 * "@attribute" or "free". Si el valor de criterio es free es una expresion
	 * xpath completa. * @param textoDestino: texto correspondiente a la búsqueda de
	 * la página destino.
	 */
	public static void clickOption(WebDriver driver, String textOption, String criterio, String textoDestino) {
		// CLickamos en la opción de registro y esperamos a que se cargue el enlace de
		// Registro.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "@href", textOption, getTimeout());
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();
		// Esperamos a que sea visible un elemento concreto
		elementos = SeleniumUtils.EsperaCargaPagina(driver, criterio, textoDestino, getTimeout());
		// Tiene que haber un sólo elemento.
		assertTrue(elementos.size() == 1);
	}

	/**
	 * * Selecciona el enlace de idioma correspondiente al texto textLanguage
	 * * @param driver: apuntando al navegador abierto actualmente. * @param
	 * textLanguage: el texto que aparece en el enlace de idioma ("English" o
	 * "Spanish")
	 */
	public static void changeIdiom(WebDriver driver, String textLanguage) {
		// clickamos la opción Idioma.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnLanguage", getTimeout());
		elementos.get(0).click();
		// Esperamos a que aparezca el menú de opciones.
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "languageDropdownMenuButton", getTimeout());
		// SeleniumUtils.esperarSegundos(driver, 2); //CLickamos la opción Inglés
		// partiendo de la opción Español
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", textLanguage, getTimeout());
		elementos.get(0).click();
	}

	/**
	 * Navega a traves de las opciones de la barra de navegacion seleccionando el
	 * menu y la opcion correspondiendtes.
	 * 
	 * @param driver
	 * @param menu
	 * @param option
	 */
	public static void checkNavMode(WebDriver driver, String menu, String option) {
		// clickamos la opción Idioma.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", menu, getTimeout());
		elementos.get(0).click();
		// Esperamos a que aparezca el menú de opciones.
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", option, getTimeout());
		// SeleniumUtils.esperarSegundos(driver, 2);

		elementos.get(0).click();
	}

	static public void checkNavText(WebDriver driver, int language) {
		// Esperamos a que se cargue el menu home
		SeleniumUtils.textoPresentePagina(driver, p.getString("nav.option.home", language));
		// Esperamos a que se cargue la opcion publicaciones
		SeleniumUtils.textoPresentePagina(driver, p.getString("nav.option.publications", language));

		// Esperamos a que se cargue el menu publicaciones añadir
		SeleniumUtils.textoPresentePagina(driver, p.getString("nav.option.publications.add", language));
		// Esperamos a que se cargue el menu publicaciones listar
		SeleniumUtils.textoPresentePagina(driver, p.getString("nav.option.publications.list", language));

		// Esperamos a que se cargue el menu usuarios
		SeleniumUtils.textoPresentePagina(driver, p.getString("nav.option.users", language));
		// Esperamos a que se cargue la opcion listar usuarios
		SeleniumUtils.textoPresentePagina(driver, p.getString("nav.option.users.list", language));

		// Esperamos a que se cargue el menu amigos
		SeleniumUtils.textoPresentePagina(driver, p.getString("nav.option.friends", language));
		// Esperamos a que se cargue la opcion listar invitaciones
		SeleniumUtils.textoPresentePagina(driver, p.getString("nav.option.friends.invitations", language));
		// Esperamos a que se cargue la opcion listar amigos
		SeleniumUtils.textoPresentePagina(driver, p.getString("nav.option.friends.list", language));

		//Comprobamos el pie de pagina
		SeleniumUtils.textoPresentePagina(driver, p.getString("footer.message", language));
	}

	static public void checkNavBarChangeIdiom(WebDriver driver, String textIdiom1, 
			String textIdiom2, int locale1, int locale2 ) {    
		//Esperamos a que se cargue el saludo de bienvenida en Español    
		checkNavText(driver, locale1);    
		//Cambiamos a segundo idioma    
		changeIdiom(driver,  textIdiom2);    
		//COmprobamos que el texto de bienvenida haya cambiado a segundo idioma    
		checkNavText(driver, locale2);    
		//Volvemos a Español.    
		changeIdiom(driver, textIdiom1);    
		//Esperamos a que se cargue el saludo de bienvenida en Español    
		checkNavText(driver, locale1); 
	 }
	
}
