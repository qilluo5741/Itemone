<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
  <head>
 	
    <base href="<%=basePath%>">
     <meta charset="utf-8">
    <title>【BATP】停车后台管理登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
    	<link rel="shortcut icon" href="image/logo.png">
        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="css/user/reset.css">
        <link rel="stylesheet" href="css/user/supersized.css">
        <link rel="stylesheet" href="css/user/style.css">
     	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
         <script type="text/javascript">
	  	if (window != top){
			top.location.href = location.href;
		}
  </script>
  </head>
  
  <body>
     <div class="page-container">
            <h1>BATP停车后台管理</h1>
            <form action="" method="post">
                <input type="text" id="username" name="username" class="username" placeholder="账号">
                <input type="password" id="password" name="password" class="password" placeholder="密码">
               	<div id="msg" style="font-size: 12px;color: red"><Br/> </div>
                <button type="button" id="login">登录</button>
            </form>
            <div class="connect">
                <p>
                    <a  href="regist.jsp">用户注册</a>
                </p>
            </div>
        </div>
         <div class="loginbm">Company Name © 享泊信息科技</div>
         <!-- Javascript -->
        <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
        <script src="js/user/supersized.3.2.7.min.js"></script>
        <script src="js/user/supersized-init.js"></script>
      
         <script type="text/javascript">
        	$(function(){
        		$("#login").click(function(){
        			//得到用户名密码
        			var userName=$("#username").val();
        			var password=$("#password").val();
        			if(userName==""){
        					//验证登录
        					$("#username").css("borderColor","red");
        					$("#username").hide(100);
        					$("#username").show(100);
        				
        			}else if(password==""){
        				//验证登录
        					$("#password").css("borderColor","red");
        					$("#password").hide(100);
        					$("#password").show(100);
        			}
        			else{
        				//执行登录操作前 清空密码框
        				$("#password").val("");
        				$.ajax({
							url:'web/user/login.do',
							data:{"userName":userName,"userPwd":password},
							type:"post",
							success:function(res){
								if(res=="1"){
									//跳转首页
									window.location.href="index.jsp";
								}else if(res=="0"){
									$("#msg").html("用户名/密码 错误！");
								}
								else if(res=="3"){
									$("#msg").html("用户审核中...");
								}
								else{
									$("#msg").html("用户审核失败！请联系管理员!");
								}
							},
							error:function(){
								$("#msg").html("后台维护中。。请稍候再试！");
							}
						});
        			}
        		});
        		$("input").keyup(function(){
        			//返回颜色
        			$("#password").css("borderColor","#F0FFFF");
        			$("#username").css("borderColor","#F0FFFF");
        			$("#msg").html("<Br/>");
        		})	
        	})
        </script>
  </body>
</html>
