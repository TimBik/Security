package ru.spring.files.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.files.dto.SignUpDto;
import ru.spring.files.service.SignUpService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    @Autowired
    private SignUpService signUpService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registrationCreate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute(name = "user") SignUpDto signUpDto, HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("user", signUpDto);
        signUpService.signUp(signUpDto);
        return null;
    }
}
