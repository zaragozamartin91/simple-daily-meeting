package com.ast.dm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FooController {
    @GetMapping(path = "/foo")
    public String foo() {
        return "hola";
    }

    @PostMapping(path = "/foo")
    public String dummyUpload(FooForm fooForm, Model model) {
        model.addAttribute("name", fooForm.getName());
        return "index";
    }
}
