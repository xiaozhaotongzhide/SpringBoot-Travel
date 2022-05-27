package com.wangbing.springboottravel.web.controller;

import com.wangbing.springboottravel.service.UserService;
import com.wangbing.springboottravel.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ActiveUserController extends HttpServlet {

    @GetMapping("/activeUserServlet")
    public void activeUserServlet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取激活码
        String code = req.getParameter("code");
        if (code != null) {
            //调用service完成激活
            UserService userService = new UserServiceImpl();
            boolean flag = userService.active(code);
            String msg = null;
            //判断标记
            if (flag) {
                //激活成功
                msg = "激活成功，请登录";
            }else {
                //激活失败
                msg = "激活失败，请练习管理员";
            }
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write(msg);
        }
    }
}
