package com.qianmi.books.controller;

import com.qianmi.books.dao.query.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String main() {
        return "index";
    }
}