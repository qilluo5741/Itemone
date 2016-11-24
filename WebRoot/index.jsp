<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<title>【BATP】后台管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
 <!-- Bootstrap Core CSS -->
<link href="<%=basePath%>css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="<%=basePath%>css/index/style.css" rel='stylesheet' type='text/css' />
<!-- Graph CSS -->
<link href="<%=basePath%>css/index/font-awesome.css" rel="stylesheet"> 
<!-- jQuery -->
<script src="<%=basePath%>js/index/jquery.min.js"></script>
<!----webfonts--->
<link href='http://fonts.useso.com/css?family=Roboto:400,100,300,500,700,900' rel='stylesheet' type='text/css'>
<!---//webfonts--->  
<!-- Nav CSS -->
<link href="<%=basePath%>css/index//custom.css" rel="stylesheet">
<!-- Metis Menu Plugin JavaScript -->
<script src="<%=basePath%>js/index/metisMenu.min.js"></script>
<script src="<%=basePath%>js/index/custom.js"></script>
</head>
<body>
<div id="wrapper">
     <!-- Navigation -->
        <nav class="top1 navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">管理系统</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=basePath%>index.jsp">【BATP】管理系统</a>
            </div>
            <!-- /.navbar-header -->
            <ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
	        		<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">${user.userName}</a>
	      		</li>
			 </ul>
			<form class="navbar-form navbar-right">
              <input type="text" class="form-control" value="搜索..." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '搜索...';}">
            </form>
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                    <!-- 遍历菜单前验证菜单是否存在 -->
                    <c:if test="${empty menuValidation}">
                    	<c:redirect url="web/user/menus.do"/>
                    </c:if>
                    <!-- 菜单开始 -->
                      <c:forEach items="${menuTypes}" var="menutype">
                      	 <li>
	                            <a href="#"><i class="fa fa-laptop nav_icon"></i>${menutype.menuTypeName}<span class="fa arrow"></span></a>
	                            <ul class="nav nav-second-level">
	                               <c:forEach items="${menutype.menus}" var="menu">
	                               		 <li>
		                                    <a class="menus" href="javascript:;" title="${menu.href}">${menu.menuName}</a>
		                                </li>
	                               </c:forEach>
	                            </ul>
	                        </li>
                      </c:forEach>
                     <!-- 菜单结束-->
                      <li>
                      <a href="#"><i class="fa fa-laptop nav_icon"></i>设置<span class="fa arrow"></span></a>
                       <ul class="nav nav-second-level">
	                         <li>
		                         <!--<a class="menus" href="javascript:;" title="退出登陆">更换密码</a>-->
		                         <a class="menus" href="<%=basePath%>web/user/exit.do" title="退出登陆">退出登陆</a>
		                     </li>
		                 </li>
		              </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
        <div id="page-wrapper">
			<iframe src ="<%=basePath%>/backgroud.jsp" height="550" frameborder="0" marginheight="0" marginwidth="0" frameborder="0" scrolling="auto" id="ifm" name="ifm"  width="100%"> 
			</iframe> 
       	<div class="copy">
            <p>Copyright &copy; 2016.Company 享泊信息科技 All rights reserved.</p>
	    </div>
		</div>
       </div>
      <!-- /#page-wrapper -->
   </div>
     <script type="text/javascript">
     	$(function(){
     		$(".menus").click(function(){
     			$("#ifm").attr("src",$(this).attr("title"));
     		});
     	})
     </script>
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
</body>
</html>

