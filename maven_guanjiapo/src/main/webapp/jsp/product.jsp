<%@page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>列表</title>
    <style>
        table{
            width: auto;
            border: 1px solid;
            /*color: #37a1ff;*/
            margin: 0px auto ;
        }
        caption{
            text-align: center;
            margin-bottom: 20px;
            color: #183580;
            font-size: 26px;
            font-family: 楷体;
        }
        td{
            text-align: center;
        }
        #above{
            text-align: left;
            margin-top: 20px;
            margin-left: 200px;
            margin-bottom: 20px;
        }
        #head{
            text-align: center;
            font-size: large;
        }
    </style>
    <script src="../js/jquery-1.8.3.js"></script>
</head>
<body>
<div align="right">
    <font color="#ff7f50" >
        <c:if test="${not empty user}">${user.username}</c:if>
        <c:if test="${empty user}">
            <a href="/WEB-INF/views/login.jsp">请登录</a>
        </c:if>
    </font>欢迎您！<br>
    <a href="/exitServlet">退出</a>&nbsp;&nbsp;
</div>
</div>
<div id="head">
    <span>账务信息</span>
</div>
    <div id="above">
        <form action="/pageQueryServlet?pageNum=1&pageSize=5">
            <input type="text" id="myinput" name="flname" value="${tishi}">
            <input type="submit" value="查询" >
        </form>
    </div>

    <table cellpadding="0" cellspacing="0px" border="1px">

        <tr>
            <th width="100" height="30">账务id</th>
            <th width="100">账务名称</th>
            <th width="100">财产</th>
            <th width="100">银行</th>
            <th width="120">时间</th>
            <th width="200">描述</th>
            <th width="200">操作</th>
        </tr>
        <c:forEach items="${zwlist}" var="zw" varStatus="status">
            <tr>
                <td>${zw.zwid}</td>
                <td>${zw.flname}</td>
                <td>${zw.zhangHu}</td>
                <td>${zw.money}</td>
                <td>${zw.createtime}</td>
                <td>${zw.description}</td>
                <td><a href="/deleteServlet?zwid=${zw.zwid}" >删除</a></td>
            </tr>
        </c:forEach>
        <form action="/addServlet">
            <tr>
                <td><%--<input type="text" name="zwid" width="100%">--%></td>
                <td><input type="text" name="flname" width="100%"></td>
                <td><input type="text" name="zhangHu" width="100%"></td>
                <td><input type="text" name="money" width="100%"></td>
                <td><input type="text" name="createtime" width="100%"></td>
                <td><input type="text" name="description" width="100%"></td>
                <td><input type="submit" value="添加"></td>
                <%--<td><a href="/addServlet" >添加</a></td>--%>
            </tr>
            <tr>
                <td colspan="6">
                    <c:if test="${pageNum!=1}">
                        <a href="/pageQueryServlet?pageNum=${pageNum-1}&pageSize=5">上一页</a>
                    </c:if>
                    <c:forEach begin="1" end="${pageCount}" var="i">
                        <a href="/pageQueryServlet?pageNum=${i}&pageSize=5" <c:if test="${pageNum==i}">style="color: red"</c:if> >第${i}页</a>
                    </c:forEach>
                    <c:if test="${pageNum!=pageCount}">
                        <a href="/pageQueryServlet?pageNum=${pageNum+1}&pageSize=5">下一页</a>
                    </c:if>
                </td>

            </tr>
        </form>
    </table>
</body>
</html>