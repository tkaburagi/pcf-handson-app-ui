package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Component
@Controller
@Service
public class UiController {

    @Autowired
    UiService uiService;


    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home(String id, Model model, HttpSession session) throws Exception {

        uiService.getAllBooks(model);
        uiService.getBookById(id, model);
        uiService.dummy(model);
        session.setAttribute("iam", "I'm a Session");
        return "ui/index";
    }
}
