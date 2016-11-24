<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>小区查询</title>
   <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="<%=basePath%>/css/pagination.css" type="text/css"></link>
   <script type="text/javascript" src="<%=basePath%>js/jquery.pagination.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script type="text/javascript">
  	//点击分页按钮以后触发的动作
	function handlePaginationClick(new_page_index, pagination_container) {
	    $("#supplierForm").attr("action", "web/community/communityvague.do?pageIndex="+(new_page_index+1));//+"&key="+$("#key").val()
	    $("#supplierForm").submit();
	    return false;
	}$(function(){
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
  </head>
  <body style="margin-top: 25px;">
   <div align="left" style="margin-top:5px;margin-left:15px;">
   	<p>你愣着干什么，赶快搜索呗！</p>
  <div align="right" style="margin-top:-52px;"><br/>
 	<input  name="communityname" placeholder="请输入小区名字或者小区地址" value="${key}" id="communityname" style="border-radius:10px;width:220px;background-color:#eee;border:0;" >
  	&nbsp;&nbsp;
  	<button id="ss" type="button" class="btn btn-info">搜索</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </div></div>
	<table class="table table-striped">
	   <thead>
	      <tr>
	      <%--<th>小区ID</th>--%>
	         <th>小区名字</th>
	         <th>小区地址</th>
	         <th>行政区</th>
	         <th>录入时间</th>
	      </tr>
	   </thead>
	   <tbody>
	   <c:forEach items="${pager.list}" var="c"> 
	      <tr>
	            <%--<td>${c.communityId}</td>--%>
	           <td>${c.community_name}</td>
	           <td>${c.community_address}</td>
	           <td>${c.administrative}</td>
	           <td>${c.addtime}</td>
	           <td>
	           <c:choose>
					<c:when test="${c.isaudit == 0}">
					<button type="button" id="${c.communityId}" class="btn btn-success">未审核</button>
					</c:when>
					<c:when test="${c.isaudit == 1}">
						<button type="button" id="${c.communityId}" class="btn btn-primary">已审核</button>
					</c:when>
					<c:when test="${c.isaudit == 2}">
						<button type="button" id="${c.communityId}" class="btn btn-warning">已拒绝</button>
					</c:when>
					</c:choose>
	          </td>
	      </tr>
	   </c:forEach>
	   </tbody>
	</table>
	<script type="text/javascript">
		$(function(){
			$("#ss").click(function(){
				$("#key").val($("#communityname").val());
				 $("#supplierForm").attr("action", "web/community/communityvague.do?pageIndex="+(1));
				$("#supplierForm").submit();
			})
		});
	</script>
<form id="supplierForm"  method="post" action="">
	<div style="" id="News-Pagination"  align="center"></div>
	<input id="key" name="key" type="hidden" value="${key}">
</form>
  </body>
</html>
