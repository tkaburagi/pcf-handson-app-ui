package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UiController {

    private final UiService uiService;

    public UiController(UiService uiService) {
        this.uiService = uiService;
    }

    private static Logger log = LoggerFactory.getLogger(UiController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home(String id, Model model, HttpSession session) throws Exception {

        log.info("Handling home");
        model.addAttribute("appinfo", this.uiService.getAppInfo());
        model.addAttribute("allbooks", this.uiService.getAllBooks());
        model.addAttribute("searchedBook", this.uiService.getBookById(id));
        model.addAttribute("message", this.uiService.dummy());
        session.setAttribute("iam", "I'm a Session");
        return "ui/index";
    }
}
