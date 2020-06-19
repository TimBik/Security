package ru.spring.files.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.files.dto.UserDto;
import ru.spring.files.model.User;
import ru.spring.files.service.SignInService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class SignInController {

    @Autowired
    private SignInService signInService;

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public ModelAndView signInCreate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signIn");
        return modelAndView;
    }
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ModelAndView signIn(@ModelAttribute(name = "user") UserDto userDto, HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            Optional<User> user = signInService.signIn(userDto);
            if (user.isPresent()) {
                session.setAttribute("user", userDto);
                return new ModelAndView("redirect:/file_load");
            } else return new ModelAndView("redirect:/signIn");
        }
        else return new ModelAndView("redirect:/file_load");
    }

}
