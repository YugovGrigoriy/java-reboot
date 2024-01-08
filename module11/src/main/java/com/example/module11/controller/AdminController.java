package com.example.module11.controller;

import com.example.module11.service.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    UserDB userDB;
    UserController userController;

    @PostMapping("/deleteUser")
    public ModelAndView deleteUser(@RequestParam("idUser") String id) {
        try {
            userDB.inCorId(id);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/error.jsp");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView();
        userDB.delete(id);

        return userController.mainPage();
    }

    @PostMapping("/updateUser")
    public ModelAndView updateUser(@RequestParam("idUser") String id,
                                   @RequestParam("newName") String newName,
                                   @RequestParam("newAge") String newAge) {
        try {
            userDB.inCorId(id);
            userDB.inCor(newAge,newName);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/error.jsp");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView();
        userDB.upDateUser(id,newName,Integer.parseInt(newAge));

        return userController.mainPage();
    }



    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    @Autowired
    public void setUserDB(UserDB userDB) {
        this.userDB = userDB;
    }
}
