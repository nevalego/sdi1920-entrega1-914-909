package com.uniovi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This class is aimed to receive the default request for the page
 * @author Nerea Valdés Egocheaga
 *
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
	return "index";
    }
}
