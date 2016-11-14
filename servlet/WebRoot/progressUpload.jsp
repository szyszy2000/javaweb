<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>My JSP 'progressUpload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<style type="text/css">
	  body, td, div {font-size: 12px; font-familly: 宋体;}
	  #progressBar {width: 400px; height: 12px; background: #FFFFFF; border: 1px; solid #000000; padding: 1px;}
	  #progressBarItem {width: 30%; height: 100%; background: #FF0000; }
	</style>
	
  </head>
  
  <body>
    <ol><li><br></li></ol>
    <form action="servlet/ProgressUploadServlet" method="post" enctype="multipart/form-data" target="upload_iframe" onsubmit="showStatus();">
      <input type="file" name="file1" style="width: 350px;"/><br/>
      <input type="file" name="file2" style="width: 350px;"/><br/>
      <input type="file" name="file3" style="width: 350px;"/><br/>
      <input type="file" name="file4" style="width: 350px;"/>
      <input type="submit" value="开始上传" id="btnSubmit"/>
    </form>
    <div id="status" style="display: none;">
                 上传进度条：
      <div id="progressBar"><div id="progressBarItem"></div></div>
      <div id="statusInfo"></div>
    </div>
	<script type="text/javascript">
	  //是否上传结束标志位
	  var _finished = true;
	  
	  //返回指定id的HTML元素
	  function $(obj){
	      return document.getElementById(obj);
	  }
	  
	  //显示进度条
	  function showStatus(){
	      
	      _finished = false;
	      
	      //将隐藏的进度条显示
	      $('status').style.display = 'block';
	      //设置进度条初始值为1%
	      $('progressBarItem').style.width = '1%';
	      //把提交按钮置灰，防止重复提交
	      $('btnSubmit').disabled = true;
	      
	      //1秒钟后执行requestStatus()方法，更新上传进度
	      setTimeout("requestStatus()", 1000);
	  }
	  //向服务器请求上传进度信息
	  function requestStatus(){
	      //如果已经结束，则返回
	      if(_finished) return;
	      
	      //获取Ajax请求
	      var req = createRequest();
	      //设置请求路径
	      req.open("GET", "servlet/ProgressUploadServlet");
	      //请求完毕就执行callback(req)
	      req.onreadystatechange=function(){callback(req);}
	      //发送请求
	      req.send(null);
	      //1秒钟后重新请求
	      setTimeout("requestStatus()", 1000);
	  }
	  
	  //返回Ajax请求对象
	  function createRequest(){
	      //Netscape浏览器
	      if(window.XMLHttpRequest){
	          return new XMLHttpRequest();
	      }
	      //IE浏览器
	      else{
	          try{
	              return new ActiveXObject("Msxml2.XMLHTTP");
	          }catch(e){
	              return new ActiveXObject("Microsoft.XMLHTTP");
	          }
	      }
	      return null;
	  
	  }
	  //刷新进度条
	  function callback(req){
	      //请求结束后
	      if(req.readyState == 4) {
	          //如果发生错误，则显示错误信息
	          if(req.status != 200){
	              _debug("发生错误。 req.status: " + req.status + "");
	              return;
	          }
	          //显示debug信息
	          _debug("status.jsp 返回值：" + req.responseText);
	          
	          //处理进度信息。格式：百分比||已完成数(M)||文件总长度(M)||传输速率(K)||已用时间(s)||估计总时间(s)||估计剩余时间(s)||正在上传第几个文件
	          var ss = req.responseText.split("||");
	          $('progressBarItem').style.width = '' + ss[0] + '%';
	          $('statusInfo').innerHTML = '已完成百分比：' + ss[0] + '% <br/>已完成数(M)：' + ss[1] + '<br/> 文件总长度(M)：' + ss[2]
	              + '<br/> 传输速率(K)：' + ss[3] + '<br/> 已用时间(s)：' + ss[4] + '<br/> 估计总时间(s)：' + ss[5]
	              + '<br/> 估计剩余时间(s)：' + ss[6] + '<br/>正在上传第几个文件：' + ss[7];
	          
	          if(ss[1] == ss[2]){
	              _finished = true;
	              $('statusInfo').innerHTML += "<br/><br/><br/>上传完成。";
	              $('btnSubmit').disabled = false;
	          }
	      }
	  
	  }
	  
	  //显示调试信息
	  function _debug(obj){
	      var div = document.createElement("DIV");
	      div.innerHTML = "[debug]: " + obj;
	      document.body.appendChild(div);
	  }
	  
	</script>    
  </body>
</html>
