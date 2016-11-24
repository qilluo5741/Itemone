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
    <title>【BATP】停车后台用户注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
    	<link rel="shortcut icon" href="image/logo.png">
        <!-- CSS --><%--
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        --%><link rel="stylesheet" href="css/user/reset.css">
        <link rel="stylesheet" href="css/user/supersized.css">
        <link rel="stylesheet" href="css/user/style.css">
     	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
  </head>
  
  <body>
     <div class="page-container1">
            <h1>BATP停车用户注册</h1>
            <form action="" method="post">
                <input type="text" id="userName" name="userName" class="userName" placeholder="用户名/请根据自己的姓名填写，便于审核">
                <input type="text" id="userAccount"  name="userAccount" class="userAccount" placeholder="账号,大于6位">
                <input type="hidden" id="isKeyong" value="false"><!-- 是否可用 -->
                <div id="isKyMsg" style="font-size: 12px;color: red"></div>
                <input type="password" id="password" name="password" class="rp" placeholder="登录密码，大于6位">
                <input type="password" id="password1" name="password1" class="rp" placeholder="确认密码">
                <textarea  id="userRemark" rows="1" cols="" placeholder="说明：xxx职位"></textarea>
                	<div id="msg" style="font-size: 12px;color: red"><Br/></div>
                 <button type="button" id="login">注&nbsp;&nbsp;&nbsp;&nbsp;册</button>
            </form>
            <div class="connect">
                <p>
                    <a  href="login.jsp">登陆</a>
                </p>
            </div>
        </div><%--
         <div class="loginbm">Company Name © 享泊信息科技</div>
         --%><!-- Javascript -->
        <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
        <script src="js/user/supersized.3.2.7.min.js"></script>
        <script src="js/user/supersized-init.js"></script>
        <script type="text/javascript">
        	$(function(){
        		//验证一用户账号是否存在
        		$("#userAccount").blur(function(){
        				$.ajax({
							url:'web/user/valUserAccountIsExixts.do',
							data:{"userAccount":$("#userAccount").val()},
							type:"post",
							success:function(res){
								if(res=="true"){//存在
									$("#isKyMsg").html("请换一个账号试试吧！");
									$("#isKeyong").val("false");
								}
								else{
									$("#isKyMsg").html("<span style='color:green'>该账号可用</span>");
									$("#isKeyong").val("true");
								}
							},
							error:function(){
								$("#msg").html("后台维护中。。请稍候再试！");
							}
						});
        		});	
        		//登陆
        		$("#login").click(function(){
        			var $userName=$("#userName");
					var $userAccount= $("#userAccount");
					var $password =$("#password");
					var $password1= $("#password1");
        			var userRemark=$("#userRemark").val();
        			if($userName.val()==""){
        				$userName.css("borderColor","red");
        				$userName.hide(100); $userName.show(100);
        			}else if($userAccount.val()==""||$userAccount.val().length<6){
        				$userAccount.css("borderColor","red");
        				$userAccount.hide(100); $userAccount.show(100);
        			}else if($("#isKeyong").val()=="false"){
        				$userAccount.css("borderColor","red");
        				$userAccount.hide(100); $userAccount.show(100);
        				$("#isKyMsg").html("<br>请换一个账号试试吧！");
        			}else if($password.val()==""||$password.val().length<6){
        				$password.css("borderColor","red");
        				$password.hide(100); $password.show(100);
        			}
        			else if($password1.val()==""||$password1.val().length<6){
        				$password1.css("borderColor","red");
        				$password1.hide(100); $password1.show(100);
        			}
        			else if($password.val()!=$password1.val()){
        				$password.css("borderColor","red");
        				$password.hide(100); $password.show(100);
        				$password1.css("borderColor","red");
        				$password1.hide(100); $password1.show(100);
        				$("#msg").html("两次密码不一致");
        			}else{
        				//提交
        				$.ajax({
							url:'web/user/regist.do',
							data:{"userName":$userName.val(),"userPwd":$password.val(),"userRemark":userRemark,"userAccount":$userAccount.val()},
							type:"post",
							success:function(res){
								if(res="true"){
									$("#msg").html("<span style='color:green'>用户注册成功！，请联系管理员审核!</span>");
									$("#isKyMsg").html("");
									$("#isKeyong").val("false");
									//清空属性值
									$("input").val("");
								}else{
									$("#msg").html("注册失败！，请联系管理员!");
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
        			$("#userName").css("borderColor","#F0FFFF");
        			$("#userAccount").css("borderColor","#F0FFFF");
        			$("#password").css("borderColor","#F0FFFF");
        			$("#password1").css("borderColor","#F0FFFF");
        			$("#msg").html("<Br/>");
        			$("#isKyMsg").html("");
        		})
        	});
        </script>
  </body>
</html>
