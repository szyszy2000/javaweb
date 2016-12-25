<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="sun.misc.BASE64Decoder" />
<jsp:directive.page trimDirectiveWhitespaces="true" />
<%
    out.clear();                //清除遍历
    for(Cookie cookie : request.getCookies()){    //遍历Cookie
        
        if(cookie.getName().equals("file")){    //找到名为file的Cookie
	        byte[] binary= BASE64Decoder.class.newInstance().decodeBuffer(cookie.getValue().replace(" ", ""));    //解码BASE64编码的二进制内容
	        
	        response.setHeader("Content-Type", "image/gif");     //设置内容类型为gif图片
	        response.setHeader("Content-Disposition", "inline; filename=cookie.gif");
	        response.setHeader("connection", "close"); 
	        response.setContentLength(binary.length);          //设置输出内容的长度
	        response.getOutputStream().write(binary);          //输出到客户端
	        response.getOutputStream().flush();                //清空缓存
	        response.getOutputStream().close();                //关闭输出流
	        return;        
        
        }

    
    }
%>