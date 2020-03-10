package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_UserListView extends PO_NavView{

	public static void makeASearch(WebDriver driver, String textToSearch) {
		WebElement search = driver.findElement(By.id("searchText"));
		search.click();
		search.clear();
		search.sendKeys(textToSearch);
		
		//Pulsamos para realizar la busqueda
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnSearch", getTimeout());
		elementos.get(0).click();
	}
}
