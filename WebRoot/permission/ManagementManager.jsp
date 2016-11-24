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
    
    <title>车位管理</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/css/pagination.css" type="text/css"></link>
    <script type="text/javascript" src="<%=basePath%>js/jquery.pagination.js"></script>
    <script type="text/javascript" src="lhgcalendar/lhgcore.js"></script>
   <script type="text/javascript" src="lhgcalendar/lhgcalendar.js"></script>
  <script type="text/javascript">
  	//点击分页按钮以后触发的动作
	function handlePaginationClick(new_page_index, pagination_container) {
	    $("#supplierForm").attr("action","web/manage/getManagement.do?pageIndex="+(new_page_index+1));
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
			$("#sss").click(function(){
				var is_delete=document.getElementById("is_delete").value;
				var chargetype=document.getElementById("chargetype").value;
				$("#key").val($("#mobile").val());
				$("#creabeegin").val($("#date_creabeegin").val());
				$("#creaend").val($("#date_creaend").val());
				$("#is_delete").val($("#is_delete").val());
				$("#chargetype").val($("#chargetype").val());
				$("#supplierForm").attr("action", "web/manage/getManagement.do?pageIndex="+(1));
				$("#supplierForm").submit();
			})
		});
	</script>
</head>
 <body>
 <form id="supplierForm"  method="post" action="">
 <div align="left" style="margin-top:20px;margin-left:45px;">
   <label>是否删除：</label>
       <select id="is_delete">
	       <option value="">全部</option>
	       <option value="0">未删除</option>
      	   <option value="1">已删除</option>
       </select>&nbsp;&nbsp;
       <label>收费类型</label>
       <select id="chargetype">
	       <option value="">全部</option>
	       <option value="0">次</option>
	       <option value="1">小时</option>
       </select>&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" id="date_creabeegin" onclick="J.calendar.get();" value="${creabeegin}" name="date_creabeegin" placeholder="开始时间" style="width:100px;"/>&nbsp;&nbsp;至&nbsp;&nbsp;
	<input type="text" id="date_creaend" onclick="J.calendar.get({dir:'right'});" value="${creaend}" name="date_creaend" placeholder="结束时间" style="width:100px;"/>
 </div>
  <div align="right" style="margin-top:-52px;"><br/>
   	 <input  name="mobile" placeholder="请输入手机号码" value="${key}" id="mobile" style="border-radius:10px;width:220px;background-color:#eee;border:0;" >&nbsp;&nbsp;
  	<button id="sss" type="button" class="btn btn-info">搜索</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </div>
  <table class="table table-striped">
	   <thead>
	      <tr>
	         <th>车位编号</th>
	         <th>车位姓名</th>
	         <th>车位手机</th>
	         <th>费用</th>
	         <th>费用标准</th>
	         <th>是否删除</th>
	         <th>发布时间</th>
	         <th>更新时间</th>
	         <th>小区名字</th>
	         <th>车位说明</th>
	         <th>入口</th>
	      </tr>
	   </thead>
	   <tbody>
	   <c:forEach items="${pager.list}" var="c"> 
	      <tr>
	           <td>${c.parkNo}</td>
	           <td>${c.userName}</td>
	           <td>${c.mobile}</td>
	           <td>${c.money}</td>
	           <td>
	             <c:choose>
						<c:when test="${c.chargetype == 0}">
							次
						</c:when>
						<c:when test="${c.chargetype == 1}">
							小时
						</c:when>
					</c:choose>
	          </td>
	          <td>
	             <c:choose>
						<c:when test="${c.is_delete == 0}">
							否
						</c:when>
						<c:when test="${c.is_delete == 1}">
							是
						</c:when>
					</c:choose>
	          </td>
	           <td>${fn:substring(c.releasedate,0,10)}</td>
	           <td>${fn:substring(c.update_time,0,10)}</td>
	           <td>${fn:substring(c.community_name,0,10)}</td>
	           <td>${fn:substring(c.park_instructions,0,10)}</td>
	           <td>${fn:substring(c.entrance,0,10)}</td>
	   </c:forEach>
	   </tbody>
	</table>
	<div style="" id="News-Pagination"  align="center"></div>
	<input id="key" name="key" type="hidden" value="${key}">
	<input id="creabeegin" name="creabeegin" type="hidden" value="${creabeegin}">
	<input id="creaend" name="creaend" type="hidden" value="${creaend}">
	<input id="is_delete" name="is_delete" type="hidden" value="${is_delete}">
	<input id="chargetype" name="chargetype" type="hidden" value="${chargetype}">
</form>
  </body>
</html>