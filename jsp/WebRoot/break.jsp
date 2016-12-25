<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
  <head><title>JSP Scriptlets</title></head>
  <body>
  <%
      for(int i=0; i<5; i++){
   %>
   break所在的循环, i=<%=i %>,  <br/>
   <%
       if(i==2) break;
       }
    %>
    break循环完毕.<br/>
    <%
        for(int i=0; i<5; i++){
        
     %>
    return所在的循环, i=<%=i %>,  <br/>
   <%
       if(i==2) return;
       }
    %>
    return循环完毕.<br/>
  </body>
</html>