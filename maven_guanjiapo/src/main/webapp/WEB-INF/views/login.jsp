<%@page contentType="text/html; charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆/注册页面</title>
    <style>
        table{
            width: auto;
            border: 1px solid;
            color: orange;
            margin: 100px auto ;
            padding: 20px;
        }
        caption{
            text-align: center;
            margin-bottom: 20px;
            color: #183580;
            font-size: 24px;
            font-family: 楷体;
        }
        .error{
            color: lightcoral;
        }
    </style>
    <script src="../../js/jquery-1.8.3.js"></script>
    <script src="../../js/jquery.validate.js"></script>
    <script>
        $(function () {
            $("#myform").validate({
                rules:{
                    username:"required",
                    password:{
                        required:true,
                        number:true
                    }
                },
                messages:{
                    username:"用户名不能为空",
                    password:{
                        required:"密码不能为空",
                        number:"密码必须是数字"
                    }
                }
            })
        })
    </script>
</head>
<body>
<form action="/loginController" method="post" id="myform">
    <table cellpadding="15" >
        <caption>登陆界面</caption>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" placeholder="请输入用户名" value="${cookie.username.value}"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" placeholder="请输入密码" value="${cookie.password.value}"></td>
        </tr>
        <tr>
            <td>
                <input type="checkbox" name="remember" value="true" <c:if test="${not empty cookie.username && not empty cookie.username }">checked</c:if> > <font size="2px">记住我</font>
            </td>
            <td>
                <input type="submit"value="登陆">&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" value="注册">
            </td>
        </tr>
        <tr>
            <td><font color="red">${errorMessage} </font></td>
        </tr>
    </table>
</form>
</body>
</html>