package com.qianmi.books.controller;

import com.qianmi.books.dao.domain.TbBook;
import com.qianmi.books.dao.domain.TbUser;
import com.qianmi.books.exception.CheckedException;
import com.qianmi.books.service.BookService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            return "error";
        }
        TbUser userInfo = (TbUser) obj;

        TbBook tbBook = new TbBook();
        tbBook.setBookName(condition);
        tbBook.setOwner(userInfo.getUserId());
        model.addAttribute("bookList", bookService.queryBookList(tbBook));
        return "mybook";
    }

    @RequestMapping(value = "/borrowBook", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> borrowBook(String userName, String bookId,String borrowCode,String bookState
    ,String type,HttpSession session){
        if( StringUtils.isBlank(bookId)
                || StringUtils.isBlank(borrowCode)){
            return ajaxFail("请输入正确的数据!");
        }
        if(!"2".equals(bookState)){
            if(StringUtils.isBlank(userName)){
                return ajaxFail("请输入借书人名称!");
            }
        }
        TbUser tbUser = getUserInfo(session);
        if(tbUser == null){
            return ajaxFail("请登录后再做操作!");
        }
        try{
            if("2".equals(type)){//还书
                bookService.confirmBookBack(tbUser.getUserId(),bookId);
            }else{
                if("2".equals(bookState)){
                    bookService.lend(tbUser.getUserId(),borrowCode);
                }else{
                    TbUser toUserInfo = bookService.getUserByName(userName);
                    if(toUserInfo == null){
                        return ajaxFail("请输入正确的用户信息！");
                    }
                    bookService.lend(toUserInfo.getUserId(),tbUser.getUserId(),bookId,borrowCode);
                }
            }

        } catch (CheckedException e) {
            return ajaxFail(e.getMessage());
        } catch (Exception e){
            return ajaxFail("出问题啦~~！");
        }
        return ajaxSuccess("操作成功！");
    }
}