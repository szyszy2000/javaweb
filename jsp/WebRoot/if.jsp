<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
  <head><title>JSP Scriptlets</title></head>
  <body>
  <%
      String param = request.getParameter("param");
      if("1".equals(param)){
      
   %>
   11111111111111111111111<br/>
   <%
       }
       else if("2".equals(param)){   
    %>
    2222222222222222222222<br/>
    <%
        }
        else if("3".equals(param)){
         
     %>
    3333333333333333333333<br/>
    <%
        }
        else{
     %>
     请使用参数 param=1,2,3 选择要显示的诗歌
    <%
     }
     %>
  </body>
</html>