package com.baizhi.action;

import com.baizhi.entity.Manager;
import com.baizhi.service.ManagerService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 管理员的控制层
 */
@Controller
@RequestMapping("manager")
public class ManagerAction{
    @Autowired
    private ManagerService managerService;
    //------登录方法-----------
    @RequestMapping("login")
    public String login(Model model, String username, String password,HttpSession session) {
        //调用方法
        Manager lon = managerService.login(username);
        if (lon != null) {
            //MD5解密
            String s = DigestUtils.md5Hex(password + lon.getStatus());
            //比较密码
            if (lon.getPassword().equals(s)) {
                session.setAttribute("manager",lon);
                session.setAttribute("login","ok");
                return "redirect:/emp/selectEmp";
            } else {
                System.out.println("密码错误！");
                 //= "密码错误";
                model.addAttribute("miss","密码错误！");
                return "login";
            }
        } else {
            System.out.println("用户不存在！");
            model.addAttribute("miss","用户不存在！") ;
            return "login";
        }
    }

    //-----注册方法---------------
    @RequestMapping("register")
    public String register(Manager manager, String code, HttpServletRequest request,HttpSession session) {
        String cod = (String) session.getAttribute("code");
        if (code.equals(cod)) {
            //调用方法
            managerService.register(manager);
            //跳转页面
            return "redirect:/login.jsp";
        } else {
            request.setAttribute("miss","验证错误！");
            return "regist";
        }
    }

    //-----注册方法的副本利用Ajax判断此用户名是否存在

    @RequestMapping("regist")
    @ResponseBody
    public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        //获取输出流
        PrintWriter writer = response.getWriter();
        String user = request.getParameter("name");
        //调用方法
        System.out.println("用户-----" + user);
        Manager login = managerService.login(user);
        System.out.println(login + "-----随想");
        //判断用户名是否存在
        if (login != null) {
            writer.print("用户名存在！");
        }
        return null;
    }
}
