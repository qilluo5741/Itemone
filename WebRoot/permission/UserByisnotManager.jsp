<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title>用户管理</title>
   <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="<%=basePath%>/css/pagination.css" type="text/css"></link>
   <script type="text/javascript" src="<%=basePath%>js/jquery.pagination.js"></script>
   <script type="text/javascript" src="lhgcalendar/lhgcore.js"></script>
   <script type="text/javascript" src="lhgcalendar/lhgcalendar.js"></script>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
 	<script type="text/javascript">
  	//点击分页按钮以后触发的动作
	function handlePaginationClick(new_page_index, pagination_container) {
	    $("#supplierForm").attr("action","web/user/getUserInfonot.do?pageIndex="+(new_page_index+1));
	    $("#supplierForm").submit();
	    return false;
	}
  	$(function(){
			$("#News-Pagination").pagination(${pager.totalRecords}, {
		        items_per_page:${pager.pageSize}, // 每页显示多少条记录
		        current_page:${pager.pageIndex}- 1, // 当前显示第几页数据
		        num_display_entries:2, // 分页显示的条目数
		        next_text:"下一页",
		        prev_text:"上一页",
		        maxentries:${pager.totalRecords},
		        num_edge_entries:3, // 连接分页主体，显示的条目数
		        callback:handlePaginationClick
			});
		});
  </script>
 <body>
 <hr style="height:1px;color:#eee">
<div align="left" style="margin-top:20px;margin-left:45px;">
		<input type="text" id="date_creabeegin" onclick="J.calendar.get();" value="${creabeegin}" name="date_creabeegin" placeholder="开始时间" style="width:100px;"/>
		&nbsp;&nbsp;至&nbsp;&nbsp;<input type="text" id="date_creaend" onclick="J.calendar.get({dir:'right'});" value="${creaend}" name="date_creaend" placeholder="结束时间" style="width:100px;"/>
  <div align="right" style="margin-top:-52px;"><br/>
	 	<input  name="mobile" placeholder="请输入已经注册手机号码" value="${key}" id="mobile" style="border-radius:10px;width:220px;background-color:#eee;border:0;" >&nbsp;&nbsp;
	  	<button id="ss" type="button" class="btn btn-info">搜索</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </div></div>
  <hr style="height:1px;color:#eee">
  <table class="table table-striped">
	   <thead>
	      <tr>
	         <th>用户姓名</th>
	         <th>手机号码</th>
	         <th>头像</th>
	         <th>性别</th>
	         <th>邀请码</th>
	         <th>是否为代理</th>
	         <th>注册时间</th>
	         <th>最后更新时间</th>
	         <th>支付宝姓名</th>
	         <th>支付宝账号</th>
	      </tr>
	   </thead>
	   <tbody>
	   <c:forEach items="${pager.list}" var="c"> 
	      <tr>
	           <td>${c.userName}</td>
	           <td>${c.mobile}</td>
	           <td>
	            <c:choose>
						<c:when test="${c.headportrait == null}">
							  <img src="image/4.jpg" width="40px;" height="40px;" style="border-radius:50%"/></td>
						</c:when>
						<c:when test="${c.headportrait != null}">
							 <img src="${c.headportrait}" width="40px;" height="40px;" style="border-radius:50%"/></td>
						</c:when>
				</c:choose>
	           <td>
	             <c:choose>
						<c:when test="${c.sex == 0}">
							未填写
						</c:when>
						<c:when test="${c.sex == 1}">
							男
						</c:when>
						<c:when test="${c.sex == 2}">
							女
						</c:when>
					</c:choose>
	          </td>
	           <td>${c.invitecode}</td>
	           <td>
		           <c:choose>
						<c:when test="${c.is_guard == 0}">
							否
						</c:when>
						<c:when test="${c.is_guard == 1}">
							是
						</c:when>
					</c:choose>
			   </td>
	           <td>${fn:substring(c.date_created,0,19)}</td>
	           <td>${fn:substring(c.date_updated,0,19)}</td>
	           <td>${c.payname}</td>
	           <td>${c.payno}</td>
	      </tr>
	   </c:forEach>
	   </tbody>
	</table>
	<script type="text/javascript">
		$(function(){
			$("#ss").click(function(){
				$("#key").val($("#mobile").val());
				$("#creabeegin").val($("#date_creabeegin").val());
				$("#creaend").val($("#date_creaend").val());
				$("#supplierForm").attr("action","web/user/getUserInfonot.do?pageIndex="+(1));
				$("#supplierForm").submit();
			})
		});
	</script>
<form id="supplierForm"  method="post" action="">
	<div style="" id="News-Pagination"  align="center"></div>
	<input id="key" name="key" type="hidden" value="${key}">
	<input id="creabeegin" name="creabeegin" type="hidden" value="${creabeegin}">
	<input id="creaend" name="creaend" type="hidden" value="${creaend}">
</form>
</body>
</html>