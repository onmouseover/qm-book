package com.qianmi.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ShareBookController {

    @RequestMapping(value = "shareBooks",method = RequestMethod.GET)
    public String shareBook() {
        return "shareBook";
    }
}
