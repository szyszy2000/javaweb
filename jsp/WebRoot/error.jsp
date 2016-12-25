<%@ page language="java" contentType="text/html; charset=utf-8" isErrorPage="true" %>
<html>
  <head><title>error</title></head>
  <body>
    <%
        out.println("程序抛出了一个异常：" + exception);
     %>
  </body>
</html>