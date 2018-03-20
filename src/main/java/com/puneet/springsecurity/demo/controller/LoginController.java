package com.puneet.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {

        //return "plain-login";

        return "fancy-login";
    }

    //add a access denied controller here - since it is security related so added here
    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
