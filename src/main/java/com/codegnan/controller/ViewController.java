package com.codegnan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home() {
        return "upload"; // JSP name upload.jsp
    }
}