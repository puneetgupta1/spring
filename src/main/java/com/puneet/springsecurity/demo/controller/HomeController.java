package com.puneet.springsecurity.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping("/")
	public ModelAndView showHome(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}

	@GetMapping("/leaders")
	public String showLeaders() {
		return "leaders";
	}

	//add a mapping for /systems
	@GetMapping("/systems")
	public String showSystems() {
		return "systems";
	}
}
