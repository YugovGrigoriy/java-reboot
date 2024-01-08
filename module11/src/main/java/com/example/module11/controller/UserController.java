package com.example.module11.controller;

import com.example.module11.service.UserDB;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;


@Controller
@RequestMapping(value = "/main-user-page")
public class UserController {

    UserDB userDB;

    @GetMapping
    public ModelAndView mainPage() {

        ModelAndView modelAndView = new ModelAndView();
        setUserForTable(modelAndView);
        modelAndView.setViewName("/mainPage.jsp");
        return setUserForTable(modelAndView);
    }


    @GetMapping("/createUser")
    public ModelAndView create(@RequestParam("name") String name,
                               @RequestParam("age") String age) {

            try {
                userDB.inCor(age, name);
            } catch (Exception e) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("/error.jsp");
                return modelAndView;
            }
            userDB.createUser(Integer.parseInt(age), name);
            return mainPage();

        }





    public ModelAndView setUserForTable(ModelAndView modelAndView) {
        for (long i = 1; i < 11; i++) {
            try {
                modelAndView.addObject(
                        "user" + i,
                        userDB.findByIdUser(i).getId() +
                                "- " + userDB.findByIdUser(i).getName() +
                                " " + userDB.findByIdUser(i).getAge()
                );
            } catch (NoSuchElementException e) {
                modelAndView.addObject("user" + i, "тут будет ваш пользователь");
            }
        }
        return modelAndView;
    }

    @Autowired
    public void setUserDB(UserDB userDB) {
        this.userDB = userDB;
    }
}
