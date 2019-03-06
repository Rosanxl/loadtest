package cn.itheima.controller;

import cn.itheima.domain.User;
import cn.itheima.service.ZhangWuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @Author Rosan
 * @Version v1.0
 * @Date 2019/2/21
 * @Description TODO
 **/
@Controller
@RequestMapping(method ={RequestMethod.POST,RequestMethod.GET} )
public class UserController {
    @Autowired
    private  ZhangWuService zws;

   /* @ModelAttribute
    public void getApplicationContext(HttpServletRequest request){
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        ZhangWuService zws = (ZhangWuService) applicationContext.getBean("zhangWuService");
    }*/


    @RequestMapping("loginJsp")
    public String loginJsp(){
        return "login";
    }
    /**
     * 用户登陆
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("loginController")
    public String loginUser(Model model, HttpServletRequest request, HttpServletResponse response,User user) throws UnsupportedEncodingException {
//        request.setCharacterEncoding("utf-8");
        //获取用户讯息
        /*User user=new User();
        user.setPassword(password);
        user.setUsername(username);*/
        System.out.println("用户名："+user.getUsername()+"密码"+user.getPassword());
        String username=user.getUsername();
        String password=user.getPassword();
        //校验用户名和密码
        boolean b=zws.selectUser(username,password);
        if (b){
            System.out.println("登陆成功");
            //记住用户名和密码
            String remember = request.getParameter("remember");
            if ("true".equals(remember)){
                Cookie namecookie=new Cookie("username",username);
                Cookie pscookie=new Cookie("password",password);
                namecookie.setMaxAge(30*60);
                pscookie.setMaxAge(30*60);
                response.addCookie(namecookie);
                response.addCookie(pscookie);
            }
            request.getSession().setAttribute("user",user);
//            request.getRequestDispatcher("/jsp/product.jsp").forward(request,response);
            //重定向防止反复提交请求
//            response.sendRedirect("/jsp/product.jsp");
            return "product";
        }else{
            request.setAttribute("errorMessage", "用户名或者密码错误");
//            request.getRequestDispatcher("/login.jsp").forward(request,response);
//            response.sendRedirect("/login.jsp");
            System.out.println("账户名或者密码错误");
            return "login";
        }
    }
}
