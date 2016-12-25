<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="java.net.URLEncoder" />
<%!
    boolean isNull(String str){  //返回字符串是否为空
    
        return str==null || str.trim().length()==0;
    
    }
%>
<%
    request.setCharacterEncoding("UTF-8");    //设置request编码
    
    if("POST".equals(request.getMethod())){    //如果是POST提交数据
        String name = request.getParameter("name");        //获取name参数
        String value = request.getParameter("value");      //获取value参数
        String maxAge = request.getParameter("maxAge");    //获取maxAge参数
        String domain = request.getParameter("domain");    //获取domain参数
        String path = request.getParameter("path");        //获取path参数
        String comment = request.getParameter("comment");        //获取comment参数
        String secure = request.getParameter("secure");        //获取secure参数  
        
        
        if(!isNull(name)){    //如果name参数不为空则生成新的Cookie
        
            Cookie cookie = new Cookie(URLEncoder.encode(name, "UTF-8"), URLEncoder.encode(value, "UTF-8"));
            
            if(!isNull(maxAge)){    //若maxAge非空则设置maxAge属性        
                cookie.setMaxAge(Integer.parseInt(maxAge));    
        	}   

            if(!isNull(domain)){    //若domain非空则设置domain属性        
                cookie.setDomain(domain);    
        	}   
        	    
            if(!isNull(path)){    //若path非空则设置path属性        
                cookie.setPath(path);    
        	}    
        	
            if(!isNull(comment)){    //若comment非空则设置comment属性        
                cookie.setComment(comment);    
        	}   
        	
            if(!isNull(secure)){    //若secure非空则设置secure属性        
                cookie.setSecure("true".equalsIgnoreCase(secure));    
        	}  
        	
        	response.addCookie(cookie);    //覆盖旧的Cookie 
        }
 
    }
    
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Cookie</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="css/style.css">
  </head>
  <body>
    <div align="center" style="margin:10px; ">
      <fieldset>
        <legend>当前有效的Cookie</legend>
        <script type="text/javascript">
            document.write(document.cookie);
        </script>
      </fieldset>
      <fieldset>
        <legend>设置新Cookie</legend>
        <form action="setCookie.jsp" method="POST">
          <table>
            <tr>
              <td>name:</td>
              <td><input name="name" type="text" style="width:200px;" /></td>
            </tr>
            <tr>
              <td>value:</td>
              <td><input name="value" type="text" style="width:200px;" /></td>
            </tr>
            <tr>
              <td>maxAge:</td>
              <td><input name="maxAge" type="text" style="width:200px;" /></td>
            </tr>
            <tr>
              <td>domain:</td>
              <td><input name="domain" type="text" style="width:200px;" /></td>
            </tr>
            <tr>
              <td>path:</td>
              <td><input name="path" type="text" style="width:200px;" /></td>
            </tr>
            <tr>
              <td>comment:</td>
              <td><input name="comment" type="text" style="width:200px;" /></td>
            </tr>
            <tr>
              <td>secure:</td>
              <td><input name="secure" type="text" style="width:200px;" /></td>
            </tr>
            <tr>
              <td></td>
              <td><input type="submit" value="提交" class="button" /><input type="button" value="刷新" onclick="location='setCookie.jsp'"/></td>
            </tr>
          </table>
        </form>
      </fieldset>
    </div>
  </body>
</html>

