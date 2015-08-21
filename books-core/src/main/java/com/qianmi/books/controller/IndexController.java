package com.qianmi.books.controller;

import com.qianmi.books.dao.domain.TbBook;
import com.qianmi.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = {"index","/"}, method = RequestMethod.GET)
    public String main(String condition, Model model) {
        TbBook tbBook = new TbBook();
        tbBook.setBookName(condition);
        // model.addAttribute("bookList", bookService.queryBookList(tbBook));

        List<TbBook> tbBooks = new ArrayList<TbBook>();
        int i = 0;
        while (i++ < 20) {
            tbBook = new TbBook();
            tbBook.setBookName(i + " bookName ");
            tbBooks.add(tbBook);
        }

        model.addAttribute("bookList", tbBooks);
        return "index";
    }
}