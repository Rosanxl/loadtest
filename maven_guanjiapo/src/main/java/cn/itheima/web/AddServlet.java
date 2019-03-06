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

/**
 * @author Rosan
 * @other template
 **/
@WebServlet(urlPatterns = "/addServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        ZhangWuService zws = (ZhangWuService) applicationContext.getBean("zhangWuService");

        request.setCharacterEncoding("utf-8");
        //获取账户信息
        String zwid = request.getParameter("zwid");
        String flname = request.getParameter("flname");
        String zhangHu = request.getParameter("zhangHu");
        String money = request.getParameter("money");
        String createtime = request.getParameter("createtime");
        String description = request.getParameter("description");
        ZhangWu zw=new ZhangWu();
//        zw.setZwid(Integer.parseInt(zwid));
        zw.setFlname(flname);
        zw.setMoney(Integer.parseInt(money));
        zw.setZhangHu(zhangHu);
        zw.setDescription(description);
        zw.setCreatetime(createtime);
         //调用service方法执行添加
//        ZhangWuServiceImpl zws=new ZhangWuServiceImpl();
        zws.addZhangWu(zw);
        //重定向
        response.sendRedirect("/listServlet");
    }
}
