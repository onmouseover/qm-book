package com.qianmi.books.controller;

import com.qianmi.books.dao.domain.TbUser;
import com.qianmi.books.service.BookService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with of666.
 * User: of666
 * Date: 2015/8/21 0021
 * Time: 21:13
 */
@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String main() {
        return "login";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String createBill(TbUser tbUser, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isBlank(tbUser.getUserName()) || StringUtils.isBlank(tbUser.getPassword())) {
                model.addAttribute("msg", "请输入正确的用户名或密码！");
                model.addAttribute("url", "/login");
                model.addAttribute("actionName", "登陆");
                return "error";
            }
            TbUser userInfo = bookService.getUserByName(tbUser.getUserName());
            if (userInfo == null || !tbUser.getPassword().equals(userInfo.getPassword())) {
                model.addAttribute("msg", "用户名或密码错误！");
                model.addAttribute("url", "/login");
                model.addAttribute("actionName", "继续登陆");
                return "error";
            }
            request.getSession().setAttribute("userInfo", userInfo);
        } catch (Exception e) {
            model.addAttribute("msg", "用户名或密码错误！");
            model.addAttribute("url", "/login");
            model.addAttribute("actionName", "继续登陆");
            return "error";
        }
        try {
            response.sendRedirect("/index");
        } catch (IOException e) {
            model.addAttribute("msg", "抱歉，系统跳转有误，请刷新页面后重试。");
            model.addAttribute("url", "/login");
            model.addAttribute("actionName", "继续登陆");
            return "error";
        }
        return "index";
    }


    @RequestMapping(value = "loginout", method = RequestMethod.GET)
    public String loginout(HttpSession session, HttpServletResponse response,Model model) {
        session.removeAttribute("userInfo");
        try {
            response.sendRedirect("/index");
        } catch (IOException e) {
            model.addAttribute("msg", "抱歉，系统跳转有误，请刷新页面后重试。");
            model.addAttribute("url", "/login");
            model.addAttribute("actionName", "继续登陆");
            return "error";
        }
        return "error";
    }
}
