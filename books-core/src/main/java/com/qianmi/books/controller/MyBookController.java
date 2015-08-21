package com.qianmi.books.controller;

import com.qianmi.books.dao.domain.TbBook;
import com.qianmi.books.dao.domain.TbUser;
import com.qianmi.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MyBookController extends BaseController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = {"mybook"}, method = RequestMethod.GET)
    public String main(String condition, Model model,HttpSession session) {

        Object obj = session.getAttribute("userInfo");
        if(obj == null){
            model.addAttribute("msg","请登陆后再进行操作！");
            model.addAttribute("url","/login");
            model.addAttribute("actionName","登陆");
        }
        TbUser userInfo = (TbUser) obj;

        TbBook tbBook = new TbBook();
        tbBook.setBookName(condition);
        tbBook.setOwner(userInfo.getUserId());
        model.addAttribute("bookList", bookService.queryBookList(tbBook));
        return "mybook";
    }
}