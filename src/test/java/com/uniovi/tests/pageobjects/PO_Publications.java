package com.uniovi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_Publications extends PO_NavView {

	static public void checkMyPublicationsText(WebDriver driver, int language) {
		// Esperamos a que se cargue el titulos de la página
		SeleniumUtils.textoPresentePagina(driver, p.getString("publications.title", language));
		// Esperamos a que se cargue el texto de la página
		SeleniumUtils.textoPresentePagina(driver, p.getString("publications.text", language));

		// Esperamos a que se cargue el boton de busqueda
		SeleniumUtils.textoPresentePagina(driver, p.getString("publications.update", language));
		// Esperamos a que se cargue la columna email
		SeleniumUtils.textoPresentePagina(driver, p.getString("publications.info.details", language));

		// Esperamos a que se cargue la columna nombre
		SeleniumUtils.textoPresentePagina(driver, p.getString("publication.info.title", language));
		// Esperamos a que se cargue la columna apellidos
		SeleniumUtils.textoPresentePagina(driver, p.getString("publication.info.text", language));

		// Esperamos a que se cargue las opciones añadir amigos
		SeleniumUtils.textoPresentePagina(driver, p.getString("publication.info.date", language));
		// Esperamos a que se cargue el footer
		SeleniumUtils.textoPresentePagina(driver, p.getString("footer.message", language));
	}

	static public void checkAddPublication(WebDriver driver, int language) {
		// Esperamos a que se cargue el titulos de la página
		SeleniumUtils.textoPresentePagina(driver, p.getString("publication.title", language));
		// Esperamos a que se cargue el texto de la página
		SeleniumUtils.textoPresentePagina(driver, p.getString("users.send", language));

		// Esperamos a que se cargue el boton de busqueda
		SeleniumUtils.textoPresentePagina(driver, p.getString("publication.info.user", language));

		// Esperamos a que se cargue la columna nombre
		SeleniumUtils.textoPresentePagina(driver, p.getString("publication.info.title", language));
		// Esperamos a que se cargue la columna apellidos
		SeleniumUtils.textoPresentePagina(driver, p.getString("publication.info.text", language));

		// Esperamos a que se cargue las opciones añadir amigos
		SeleniumUtils.textoPresentePagina(driver, p.getString("publication.info.date", language));

		// Esperamos a que se cargue las opciones añadir amigos
		SeleniumUtils.textoPresentePagina(driver, p.getString("users.send", language));
		// Esperamos a que se cargue el footer
		SeleniumUtils.textoPresentePagina(driver, p.getString("footer.message", language));
	}

	static public void checkMyPublicationsListChangeIdiom(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		checkMyPublicationsText(driver, locale1);
		// Cambiamos a segundo idioma
		changeIdiom(driver, textIdiom2);
		// COmprobamos que el texto de bienvenida haya cambiado a segundo idioma
		checkMyPublicationsText(driver, locale2);
		// Volvemos a Español.
		changeIdiom(driver, textIdiom1);
		// Esperamos a que se cargue el saludo de bienvenida en Español
		checkMyPublicationsText(driver, locale1);
	}
	
	static public void checkAddPublicationChangeIdiom(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		checkAddPublication(driver, locale1);
		// Cambiamos a segundo idioma
		changeIdiom(driver, textIdiom2);
		// COmprobamos que el texto de bienvenida haya cambiado a segundo idioma
		checkAddPublication(driver, locale2);
		// Volvemos a Español.
		changeIdiom(driver, textIdiom1);
		// Esperamos a que se cargue el saludo de bienvenida en Español
		checkAddPublication(driver, locale1);
	}

}
