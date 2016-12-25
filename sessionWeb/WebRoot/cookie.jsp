<%@ page language="java" pageEncoding="UTF-8" errorPage="login.jsp" %>
<% 
    request.setCharacterEncoding("UTF-8");     //设置request编码
    
    String username = "";                      //用户名
    int visitTimes = 0;                        //访问次数
    
    Cookie[] cookies = request.getCookies();   //所有的Cookie
    
    //遍历Cookie寻找账号与登录次数
    for(int i=0; cookies!=null && i<cookies.length; i++){
        
        Cookie cookie = cookies[i];             //第i个Cookie
        
        
        if("username".equals(cookie.getName())){   //如果Cookie名为username则记录该Cookie的内容
            username = cookie.getValue();        
        }
        else if("visitTimes".equals(cookie.getName())){    //如果Cookie名为visitTimes则记录该Cookie的内容
            visitTimes = Integer.parseInt(cookie.getValue());
        }
       
    }
    
    if(username == null || username.trim().equals("")){    //如果没有找到用户名，则转到登录界面
    
        throw new Exception("您还没有登录。请先登录");
    
    }
    
    //修改Cookie, 更新用户的访问次数
    Cookie visitTimeCookie = new Cookie("visitTimes", Integer.toString((++visitTimes)));
    
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
    <div align="center" style="margin:10px; ">
      <fieldset>
        <legend>登录信息</legend>
        <form action="login.jsp" method="post">
          <table>
            <tr>
              <td>您的账号:</td>
              <td><%= username %></td>
            </tr>
            <tr>
              <td>登录次数:</td>
              <td><%= visitTimes %></td>
            </tr>
            <tr>
              <td></td>
              <td>
                <input type="button" value="刷新" onclick="location='<%= request.getRequestURI() %>?ts=' + new Date().getTime();" class="button"/>
              </td>
            </tr>
          </table>
        </form>
      </fieldset>
    </div>
  </body>
</html>


