<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
  <head>
    <title>Java Bean Actions</title>
    <link rel='stylesheet' type='text/css' href='css/style.css' />
  </head>
  <body><br/>
    <%-- 声明Person类对象person --%>
    <jsp:useBean id="person" class="com.helloweenvsfei.jsp.bean.Person" scope="page" />
    
    <%-- 设置Person的所有属性，属性值从request中自动取得。*表示所有属性--%>
    <jsp:setProperty name="person" property="*" />
    
    <div align="center">
      <form action="useBean.jsp" method="post">
        <fieldset style='width: 300'>
          <legend>请填写Person信息</legend>
          <table>
            <tr>
              <td align="right" style="font-weight:bold;">姓名:</td>
              <td>
                <%-- 获取person的name属性 --%>
                <jsp:getProperty property="name" name="person"/>
              </td>
            </tr>
            <tr>
              <td align="right" style="font-weight:bold;">年龄:</td>
              <td>
                <%-- 获取person的age属性 --%>
                <jsp:getProperty property="age" name="person"/>
              </td>
            </tr>
            <tr>
              <td align="right" style="font-weight:bold;">性别:</td>
              <td>
                <%-- 获取person的sex属性 --%>
                <jsp:getProperty property="sex" name="person"/>
              </td>
            </tr>
            <tr>
              <td align="right" style="font-weight:bold;" ></td>
              <td><input type="button" onclick="history.go(-1);" value="返回" class="button" /></td>
            </tr>
          </table>
        </fieldset>
      </form>
    
    </div>
  </body>
</html>