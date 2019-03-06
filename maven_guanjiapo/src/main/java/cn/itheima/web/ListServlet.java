package cn.itheima.web;

import cn.itheima.domain.ZhangWu;
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
import java.util.List;

/**
 * @author Rosan
 * @other template
 **/
@WebServlet(urlPatterns = "/listServlet")
public class ListServlet extends HttpServlet {
//    private ZhangWuServiceImpl zws=new ZhangWuServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        ZhangWuService zws = (ZhangWuService) applicationContext.getBean("zhangWuService");

        String flname = request.getParameter("flname");
        System.out.println("flame"+flname);
        List<ZhangWu> list=zws.selectAll(flname,5,1);
        request.setAttribute("zwlist",list);
        request.setAttribute("tishi",flname);
        request.getRequestDispatcher("/jsp/product.jsp").forward(request,response);


    }
}
