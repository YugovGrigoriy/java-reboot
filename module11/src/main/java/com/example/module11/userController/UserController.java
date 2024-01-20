package com.example.module11.userController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

@Controller

public class UserController {

    @GetMapping(value = "/main-user-page")
    public ModelAndView mainPage() {


        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/mainPage.jsp");

        return modelAndView;
    }
}




