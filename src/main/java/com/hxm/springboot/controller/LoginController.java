package com.hxm.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping(value = "/user/login")
    public String login(HttpSession session, Map<String,Object> map, @RequestParam("username") String username, @RequestParam("password") String password){
        if (!StringUtils.isEmpty(username) && "123".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }
        map.put("msg","用户名或者密码错误！");
        return "login";
    }
}
