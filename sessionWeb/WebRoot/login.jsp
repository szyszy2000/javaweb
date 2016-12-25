<%@ page language="java" pageEncoding="UTF-8" isErrorPage="true" %>
<%
    request.setCharacterEncoding("UTF-8");        //设置request编码方式
    response.setCharacterEncoding("UTF-8");       //设置response编码方式
    
    if("POST".equals(request.getMethod())){       //如果是以POST方式登录
    
        Cookie usernameCookie = new Cookie("username", request.getParameter("username"));  //新建名为username的cookie
        Cookie visittimeCookie = new Cookie("visitTimes", "0");                            //新建Cookie
        
        response.addCookie(usernameCookie);         //添加到response中
        response.addCookie(visittimeCookie);        //response会将Cookie发送到客户端
        
        response.sendRedirect(request.getContextPath() + "/cookie.jsp");    //显示Cookie页面
        return;
    
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>请先登录</title>
    <link rel="sytlesheet" type="text/css" href="css/style.css" />
  </head>
  <body>
    <div align="center" style="margin:10px; " >
      <fieldset>
        <legend>登录</legend>
        <form action="login.jsp" method="post">
          <table>
            <tr>
              <td></td>
              <td>
                <span><img src="images/errorstate.gif" /></span>
                <span style="color:red; "><%= exception.getMessage() %></span>
              </td>
            </tr>
            <tr>
              <td>账号：</td>
              <td><input type="text" name="username" style="width:200px; " /></td>
            </tr>
            <tr>
              <td>密码：</td>
              <td><input type="password" name="password" style="width:200px; " /></td>
            </tr>
            <tr>
              <td></td>
              <td><input type="submit" value="登录" class="button" /></td>
            </tr>
          </table>
        </form>
      </fieldset>
    </div>
  </body>
</html>

