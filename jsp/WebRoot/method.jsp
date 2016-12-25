<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="com.helloweenvsfei.util.ip.IPSeeker" %>

<%! //注意是%!
    private IPSeeker ipSeeker = IPSeeker.getInstance(); //全局变量
    
    //查询IP所在的地区
    public String getArea(String ip){  
    
        return ipSeeker.getArea(ip);
    
    }
    
    //查询IP所在的国家
    public String getCountry(String ip){
    
        return ipSeeker.getCountry(ip);
    
    }
    
    //方法三 正则表达式判断是否合法IP地址
    public boolean isValidIp(String ip){
    
        return ip!=null && ip.trim().matches("^[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+$");
    
    }
    
%>

<html>
  <head>
    <title>IP地址查询</title>
    <link rel='sytlesheet' type='text/css' href='css/style.css' />
  </head>
  <body>
    <br/>
    <%
        //从request中获取IP参数
        String ip = request.getParameter("ip");
        String area = "";
        String country = "";
        
        if(isValidIp(ip)){              //如果是合法的IP地址
            country = getCountry(ip);   //查询国家
            area = getArea(ip);         //查询地区
        
        }
     %>
     <div align="center">
         <form action="method.jsp" method="get">
           <fieldset style='width:500'>
             <legend>IP地址查询</legend>
             <table align="center" width="400">
                 <%
                     if(isValidIp(ip)){
                  %>
                  <tr>
                    <td align="right">IP地址：</td>
                    <td><%= ip %></td>
                  </tr>
                  <tr>
                    <td align="right">国家：</td>
                    <td><%= country %></td>
                  </tr>
                  <tr>
                    <td align="right">地区：</td>
                    <td><%= area %></td>
                  </tr>
                                    
                  <%
                  }
                   %>
                  <tr height="40">
                    <td align="right">请输入要查询的IP地址：</td>
                    <td><input type="text" name="ip" value="" sytle="width:200px;" /></td>
                  </tr>
                  <tr height="40">
                    <td colspan="2" align="center">
                      <input type="submit" name="search" value="查询IP地址" calss="button" />
                    </td>
                  </tr>
             </table>
           </fieldset>
         </form>
     </div>
  
  </body>
</html>
