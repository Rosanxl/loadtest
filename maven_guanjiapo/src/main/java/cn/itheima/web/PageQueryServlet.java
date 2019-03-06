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
@WebServlet("/pageQueryServlet")
public class PageQueryServlet extends HttpServlet {



//    private ZhangWuServiceImpl zws=new ZhangWuServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        ZhangWuService zws = (ZhangWuService) applicationContext.getBean("zhangWuService");

        String flname = request.getParameter("flname");
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        //分页计算
        int pSize=5;//每页显示数量
        int pNum=1;//当前页
        int pageCount=0;//总记录数

        if (""!=pageSize&&null!=pageSize){
            pSize = Integer.parseInt(pageSize);
        }
        if (""!=pageNum&&null!=pageNum){
            pNum = Integer.parseInt(pageNum);
        }
        int startNum=pSize*(pNum-1);
        System.out.println("flame"+flname);
        List<ZhangWu> list=zws.selectAll(flname,startNum,pSize);
        //查询总记录数
        int totalCount=zws.queryTotalPage();
        //计算总页数
        if (totalCount%pSize==0){
            pageCount=totalCount/pSize;
        }else{
            pageCount=totalCount/pSize+1;
        }

        request.setAttribute("zwlist",list);
        request.setAttribute("tishi",flname);
        request.setAttribute("pageCount",pageCount);
        request.setAttribute("pageNum",pNum);
        //todo
        request.getRequestDispatcher("/jsp/product.jsp").forward(request,response);

    }
}
