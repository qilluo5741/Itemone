<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>http本地测试工具</title>
    <!-- jQuery -->
	<script src="<%=basePath%>js/index/jquery.min.js"></script>
  </head>
  <body>
    	<input id="url" type="text"  style="width: 100%;height: 50px;font-size: 20px;"   placeholder="请求路径以及参数">
    	<input id="bd" type="text"  style="width: 100%;height: 50px;font-size: 20px;"   placeholder="data">
    	<input id="btn" type="button" style="width: 100%;height: 50px;color:white;background-color: green;" value="请求">
    	<div id='ts' style="width:100%;height:400px; border:1px solid #000;overflow:auto;"></div>
  </body>
  <script type="text/javascript">
  	$(function(){
  		$("#btn").click(function(){
  			var val=$("#bd").val();
	  		$.ajax({
				url:$("#url").val()+"?"+val,
				type:"POST",
				dataType:"json",
				success:function(res){
					$("#ts").html(JSON.stringify(res));
				},
				error:function(res){
					$("#ts").html("请求失败！"+JSON.stringify(res));
				}
			});
  		});
  	})
  </script>
</html>
