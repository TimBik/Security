package ru.spring.files.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.files.dto.SignUpDto;
import ru.spring.files.service.ConfirmService;

import javax.servlet.http.HttpSession;


@Controller
public class ConfirmController {

    @Autowired
    private ConfirmService confirmService;

    @RequestMapping(value = "/confirm/*", method = RequestMethod.GET)
    public ModelAndView confirmCreate(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("confirm");
        if (session.getAttribute("user") != null) {
            SignUpDto signUpDto = (SignUpDto) session.getAttribute("user");
            confirmService.updateSate(signUpDto.getEmail());
        }
        return modelAndView;
    }
    @RequestMapping(value = "/confirm/redirect", method = RequestMethod.GET)
    public ModelAndView redirectWithUsingRedirectPrefix() {
        return new ModelAndView("redirect:/profile");
    }


}
