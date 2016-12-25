<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
  <head>
    <title>计数器</title>
    <link rel='stylesheet' type='text/css' href='css/style.css' />
  </head>
  <body><br/>
    <%-- 定义一个session范围内的计数器 记录个人访问信息 --%>
    <jsp:useBean id="personCount" class="com.helloweenvsfei.jsp.bean.Counter" scope="session" />
    <%-- 定义一个application范围内的计数器 记录个人访问信息 --%>
    <jsp:useBean id="totalCount" class="com.helloweenvsfei.jsp.bean.Counter" scope="application" />
    <div align="center">
      <form action="counter.jsp" method="get">
        <fieldset style='width: 300' >
          <legend>计数器</legend>
          <table align="center" width="400">
            <tr>
              <td width=150 align="right" style="font-weight:bold; ">您的访问次数：</td>
              <td>
                <%-- 获取个人的访问次数 --%>
                <jsp:getProperty property="count" name="personCount"/>次
              </td>
            </tr>
            <tr>
              <td width=150 align="right" style="font-weight:bold; ">总共的访问次数：</td>
              <td>
                <%-- 获取所有人的访问次数 --%>
                <jsp:getProperty property="count" name="totalCount"/>次
              </td>
            </tr>
          </table>
        </fieldset>
      </form>
    </div>
  </body>
</html>