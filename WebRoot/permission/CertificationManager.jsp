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
    <title>车主认证</title>
   <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="<%=basePath%>/css/pagination.css" type="text/css"></link>
   <script type="text/javascript" src="<%=basePath%>js/jquery.pagination.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script type="text/javascript">
  	//点击分页按钮以后触发的动作
	function handlePaginationClick(new_page_index, pagination_container) {
	    $("#supplierForm").attr("action", "web/Certification/Certification.do?pageIndex="+(new_page_index+1));
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
	<script type="text/javascript">
  	$(function(){
		$(".update_").click(function(){
			if(confirm("你确定要通过审核吗？")){
				$.ajax({
				url:'web/Certification/updateCertificationId.do',
				data:{"certificationId":$(this).attr("id")},
				type:"post",
				success:function(res){
					if(res=="1"){
						alert("审核失败！");
					}else{
						location.reload();
					}
				},error:function(){
					location.reload();
				}
			});
		   }
		});
		$(".update_1").click(function(){
			if(confirm("你确定要拒绝通过吗？")){
				$.ajax({
				url:'web/Certification/updateCertificationIds.do',
				data:{"certificationId":$(this).attr("id")},
				type:"post",
				success:function(res){
					if(res=="1"){
						alert("拒绝失败！");
					}else{
						location.reload();
					}
				},error:function(){
					location.reload();
				}
			});
		   }
		});
	});
  </script>
  </head>
  <body>
	<table class="table table-striped">
	   <thead>
	      <tr>
	         <th>驾驶证（正面）</th>
	         <th>驾驶证（反面）</th>
	         <th>行驶证（正面）</th>
	         <th>行驶证（背面）</th>
	         <th>邮箱</th>
	         <th>认证时间</th>
	         <th>审核时间</th>
	         <th>用户姓名</th>
	         <th>用户手机</th>
	         <th>认证状态</th>
	      </tr>
	   </thead>
	   <tbody>
	   <c:forEach items="${pager.list}" var="c"> 
	      <tr>
		       <td><img src="${c.driver_license_positive}" width="80px" height="80px" style="border-radius:50%"/></td>
		       <td><img src="${c.driver_license_reverse}" width="80px" height="80px" style="border-radius:50%"/></td>
		       <td><img src="${c.vehicle_license_positive}" width="80px" height="80px" style="border-radius:50%"/></td>
		       <td><img src="${c.vehicle_license_reverse}" width="80px" height="80px" style="border-radius:50%"/></td>
	           <td>${c.email}</td>
	           <td>${fn:substring(c.audittime,0,10)}</td>
	           <td>${fn:substring(c.thistime,0,10)}</td>
	           <td>${c.userName}</td>
	           <td>${c.mobile}</td>
	           <td>
	           <c:choose>
					<c:when test="${c.certification_status == 0}">
						<button class="btn btn-success update_" data-toggle="modal" data-target="#myModal" id="${c.certificationId}" >通过审核</button>
						<button class="btn btn-danger update_1" data-toggle="modal" data-target="#myModal" id="${c.certificationId}" >拒绝通过</button>
					</c:when>
					<c:when test="${c.certification_status == 1}">
						<button type="button" id="${c.certificationId}" class="btn btn-primary">已认证</button>
					</c:when>
					<c:when test="${c.certification_status == 2}">
						<button type="button" id="${c.certificationId}" class="btn btn-primary">拒绝认证</button>
					</c:when>
					</c:choose>
	          </td>
	      </tr>
	   </c:forEach>
	   </tbody>
	</table>
<form id="supplierForm"  method="post" action="">
	<div style="" id="News-Pagination"  align="center"></div>
</form>
  </body>
</html>
