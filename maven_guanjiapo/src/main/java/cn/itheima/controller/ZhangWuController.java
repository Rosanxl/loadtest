package cn.itheima.controller;

import cn.itheima.domain.User;
import cn.itheima.domain.ZhangWu;
import cn.itheima.service.ZhangWuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author Rosan
 * @Version v1.0
 * @Date 2019/2/21
 * @Description TODO
 **/
@Controller
@RequestMapping(method ={RequestMethod.POST,RequestMethod.GET} )
public class ZhangWuController {
    @Autowired
    private ZhangWuService zws;



    @RequestMapping("mylogin")
    public String login(){
        return "login";
    }
    @RequestMapping("myproduct")
    public String product(){
        return "product";
    }

    /**
     * 添加方法
     * @param model
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "addZhangWu",method = RequestMethod.POST)
    public String addZhangWu(Model model, HttpServletRequest request, HttpServletResponse response, ZhangWu zhangWu) throws IOException {
//        request.setCharacterEncoding("utf-8");
        //获取账户信息
        /*String zwid = request.getParameter("zwid");
        String flname = request.getParameter("flname");
        String zhangHu = request.getParameter("zhangHu");
        String money = request.getParameter("money");
        String createtime = request.getParameter("createtime");
        String description = request.getParameter("description");*/
        /*ZhangWu zw=new ZhangWu();
//        zw.setZwid(Integer.parseInt(zwid));
        zw.setFlname(flname);
        zw.setMoney(Integer.parseInt(money));
        zw.setZhangHu(zhangHu);
        zw.setDescription(description);
        zw.setCreatetime(createtime);*/
        //调用service方法执行添加
//        ZhangWuServiceImpl zws=new ZhangWuServiceImpl();
        zws.addZhangWu(zhangWu);
        //重定向
//        response.sendRedirect("/listServlet");
        return "redirect:pageQueryZhangWu?pageNum=1&pageSize=5";
    }

    /**
     * 查询列表
     * @param model
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    /*@RequestMapping("queryListZhangWu")
    public String queryListZhangWu(Model model, HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "flname",required = false)String   flname){
//        String flname = request.getParameter("flname");
//        System.out.println("flame"+flname);
        List<ZhangWu> list=zws.selectAll(flname,5,1);
        request.setAttribute("zwlist",list);
        request.setAttribute("tishi",flname);
//        request.getRequestDispatcher("/jsp/product.jsp").forward(request,response);
        return "forward:myproduct";
    }*/

    /**
     * 删除
     * @param model
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("deleteZhangWu")
    public String deleteZhangWu(Model model, HttpServletRequest request, HttpServletResponse response,@RequestParam("zwid")String zwid) throws IOException, ServletException {
//        String zwid = request.getParameter("zwid");
        zws.deleteZhangWu(Integer.parseInt(zwid));
//        response.sendRedirect("/listServlet");
        return "redirect:pageQueryZhangWu?pageNum=1&pageSize=5";
    }

    /**
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("exitZhangWu")
    public String exitZhangWu(Model model, HttpServletRequest request){
        request.getSession().invalidate();
//        response.sendRedirect("/jsp/product.jsp");
        return "redirect:myproduct";
    }

    /**
     * 分页查询
     * @param model
     * @param request
     * @param response
     * @param pageSize
     * @param flname 提示信息
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "pageQueryZhangWu")
    public String pageQueryZhangWu(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "pageSize",required = false)String pageSize,@RequestParam(value = "flname",required = false)String flname,@RequestParam(value = "pageNum",required = false)String pageNum) {
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
//        System.out.println("flame"+flname);
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
//        request.getRequestDispatcher("/jsp/product.jsp").forward(request,response);
        return "forward:myproduct";
    }


    }
