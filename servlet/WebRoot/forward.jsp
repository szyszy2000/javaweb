<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<jsp:directive.page import="java.util.Date" />
<jsp:directive.page import="java.text.SimpleDateFormat" />
<%
    Date date = (Date)request.getAttribute("date");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Forward跳转</title>
    
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
  </head>
  <body>
    <div align='center'><br/>
      <fieldset style=width: 90%><legend>Forward 跳转</legend><br/> 
      <div style='line'>
          <div>从ForwardServlet中取到的Date为</div>
          <div><%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(date) %></div>
      </div><br/>
      <div style='line'>
          <div align="center">
              <input type='button' onclick='location="<%= request.getContextPath() %>/servlet/ForwardServlet?destination=servlet";' value='跳转到 Servlet' class=button />
              <input type='button' onclick='location="<%= request.getContextPath() %>/servlet/ForwardServlet?destination=file";' value='跳转到 web.xml' class=button />
              <input type='button' onclick='location="<%= request.getContextPath() %>/servlet/ForwardServlet?destination=jsp";' value='跳转到 JSP' class=button />
          </div>
      </div>
    </div>
  </body>
</html>
