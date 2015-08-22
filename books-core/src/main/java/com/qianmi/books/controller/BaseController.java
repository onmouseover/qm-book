package com.qianmi.books.controller;

import com.qianmi.books.dao.domain.TbUser;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with of666.
 * User: of666
 * Date: 2015/8/21 0021
 * Time: 19:35
 */
public class BaseController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, HttpServletRequest request) {
        String msg =  "系统异常";
        //如果是ajax请求返回json结果，普通的就返回至error.jsp
        return isAjax(request) ? new ModelAndView(new MappingJackson2JsonView(), new HashMap<String, Object>())
                : new ModelAndView("error", "msg", msg);
    }
    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
    protected Map<String, Object> ajaxSuccess(Object data) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("data", data);
        retMap.put("result", "ok");
        retMap.put("rescode", 200);
        return retMap;
    }
    protected Map<String, Object> ajaxFail(String msg) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("result", "fail");
        jsonMap.put("msg", msg);
        return jsonMap;
    }

    protected TbUser getUserInfo(HttpSession session){
        Object obj = session.getAttribute("userInfo");
        if(obj != null){
            return (TbUser)obj;
        }
        return null;
    }
}
