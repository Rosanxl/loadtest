package cn.itheima.web;

import cn.itheima.service.ZhangWuService;
import cn.itheima.service.impl.ZhangWuServiceImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Rosan
 * @other template
 **/
@WebServlet(urlPatterns = "/deleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        ZhangWuService zws = (ZhangWuService) applicationContext.getBean("zhangWuService");



        String zwid = request.getParameter("zwid");
//        ZhangWuServiceImpl zws=new ZhangWuServiceImpl();
        zws.deleteZhangWu(Integer.parseInt(zwid));
        response.sendRedirect("/listServlet");
    }
}
