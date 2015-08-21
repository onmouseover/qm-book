package com.qianmi.books.controller;

import com.qianmi.books.dao.domain.TbUser;
import com.qianmi.books.exception.CheckedException;
import com.qianmi.books.form.BookForm;
import com.qianmi.books.service.BookService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with of666.
 * User: of666
 * Date: 2015/8/21 0021
 * Time: 21:13
 */
@Controller
@RequestMapping("/")
public class RegistController {
    @Autowired
    private BookService bookService;
    @RequestMapping(value = "regist",method = RequestMethod.GET)
    public String main(String toUserId,Model model) {
        model.addAttribute("toUserId",toUserId);
        return "regist";
    }

    @RequestMapping(value = "/doRegist", method = RequestMethod.POST)
    public String doRegist(TbUser tbUser, Model model,HttpServletRequest request){
        try {
            if(StringUtils.isBlank(tbUser.getUserName()) || StringUtils.isBlank(tbUser.getPassword())){
                model.addAttribute("msg","请输入正确的用户名或密码！");
                model.addAttribute("url","/regist");
                model.addAttribute("actionName","继续注册！");
                return "error";
            }
            TbUser userInfo = bookService.register(tbUser);
            request.getSession().setAttribute("userInfo",userInfo);
        } catch (CheckedException e) {
            model.addAttribute("msg","请输入正确的用户名或密码！");
            model.addAttribute("url","/regist");
            model.addAttribute("actionName","继续注册！");
            return "error";
        }
        return "userInfo";
    }
}
