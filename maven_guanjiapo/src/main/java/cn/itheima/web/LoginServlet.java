package cn.itheima.web;

import cn.itheima.domain.User;
import cn.itheima.service.ZhangWuService;
import cn.itheima.service.impl.ZhangWuServiceImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {



//    private ZhangWuServiceImpl ls=new ZhangWuServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        ZhangWuService zws = (ZhangWuService) applicationContext.getBean("zhangWuService");

        request.setCharacterEncoding("utf-8");
        //获取用户讯息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user=new User();
        user.setPassword(password);
        user.setUsername(username);
        System.out.println("用户名："+username+"密码"+password);
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
            response.sendRedirect("/jsp/product.jsp");
        }else{
            request.setAttribute("errorMessage", "用户名或者密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
//            response.sendRedirect("/login.jsp");
            System.out.println("账户名或者密码错误");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
