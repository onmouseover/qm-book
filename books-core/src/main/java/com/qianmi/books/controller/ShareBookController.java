package com.qianmi.books.controller;

import com.qianmi.books.dao.domain.TbUser;
import com.qianmi.books.exception.CheckedException;
import com.qianmi.books.form.BookForm;
import com.qianmi.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class ShareBookController extends BaseController {

    @Autowired
    private BookService bookService;
    @RequestMapping(value = "shareBooks",method = RequestMethod.GET)
    public String shareBook() {
        return "shareBook";
    }

    @RequestMapping(value = "/shareBook", method = RequestMethod.POST)
    public String createBill(BookForm form,HttpSession session,Model model){
        try {
            Object obj = session.getAttribute("userInfo");
            if(obj == null){
                model.addAttribute("msg","您还没有登陆，请登陆后再操作！");
                model.addAttribute("url","/login");
                model.addAttribute("actionName","登陆");
                return "error";
            }
            TbUser tbUser = (TbUser)obj;
            form.setOwner(tbUser.getUserId());
            bookService.addBook(form);
        } catch (CheckedException e) {
            return "addError";
        }
        return "registSuccess";
    }
}
