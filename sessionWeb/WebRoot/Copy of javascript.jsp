<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript">
      function getCookie(name){    //返回名为name的Cookie
          var str = document.cookie;    //获取Cookie字符串
          if(!str || str.indexOf(name + "=") < 0 )    //寻找name=
              return;
          
          var cookies = str.split("; ");     //用;将所有Cookie分隔开
          
          for(var i=0; i<cookies.length; i++){    //遍历每个Cookie
          
              var cookie = cookies[i];            //当前Cookie
              if(cookie.indexOf(name + "=") == 0){    //如果名字为name
              
                  var value = cookie.substring(name.length + 1);    //获取value
                  return decodeURI(value);                           //将value解码,并返回
              
              }
          }
      }
      function setCookie(name, value){    //设置Cookie
      
          document.cookie = name + "=" + encodeURI(value);
      
      }
      
    </script>
  </head>
  <body>
    <div align="center" style="margin:10px">
      <fieldset>
        <legend>当前有效的Cookie</legend>
        <div id="cookieDiv"></div>
        <script type="text/javascript">
            cookieDiv.innerHTML = document.cookie;
        </script>
      </fieldset>
      <fieldset>
        <legend>欢迎您</legend>
        <table>
          <tr>
            <td>读取Cookie:</td>
            <td><input name="name1" /><input class="button" type="button" value="读取" onclick="alert(getCookie(name1.value));" /></td>
          </tr>
          <tr>
            <td>设置Cookie:</td>
            <td></td>
          </tr>
          <tr>
            <td align="right">Name属性:</td>
            <td><input name="name2" /></td>
          </tr>
          <tr>
            <td align="right">Value属性:</td>
            <td><input name="value2"/></td>
          </tr>
          <tr>
            <td>  </td>
            <td><input type="button" value="设置" onclick="setCookie(name2.value, value2.value); cookieDiv.innerHTML=document.cookie;" class="button" /></td>
          </tr>
        </table>
      </fieldset>
    </div>
  </body>
</html>