package com.qianmi.books.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created with of666.
 * User: of666
 * Date: 2015/8/21 0021
 * Time: 21:13
 */
@Controller
@RequestMapping("/")
public class UserInfoController {
    @RequestMapping(value = "userInfo",method = RequestMethod.GET)
    public String main(HttpSession session,Model model) {
        Object obj = session.getAttribute("userInfo");
        if(obj == null){
            model.addAttribute("msg","请登陆后再进行操作！");
            model.addAttribute("url","/login");
            model.addAttribute("actionName","登陆");
            return "error";
        }
        return "userInfo";
    }

}
