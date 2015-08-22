package com.qianmi.books.controller;

import com.qianmi.books.dao.domain.TbBook;
import com.qianmi.books.dao.domain.TbUser;
import com.qianmi.books.exception.CheckedException;
import com.qianmi.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = { "index", "/" }, method = RequestMethod.GET)
    public String main(String condition, Model model) {
        TbBook tbBook = new TbBook();
        tbBook.setBookName(condition);
        model.addAttribute("bookList", bookService.queryBookList(tbBook));
        return "index";
    }

    @RequestMapping(value = "/index/borrowBook", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> borrowBook(HttpServletRequest request, String sellerUserId,
            String bookId) {
        TbUser userInfo = (TbUser) request.getSession().getAttribute("userInfo");
        if (userInfo == null) {
            return ajaxFail("抱歉，您还没有登陆。");
        }

        try {
            String apply = bookService.apply(userInfo.getUserId(), sellerUserId, bookId);
            return ajaxSuccess("请妥善保管您的预约码：" + apply);
        } catch (CheckedException e) {
            return ajaxFail(e.getMessage());
        }
    }
}