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
    
    <title>车位订单查询</title>
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
    <script type="text/javascript" src="js/index.js"></script>
  <script type="text/javascript">
  	//点击分页按钮以后触发的动作
	function handlePaginationClick(new_page_index, pagination_container) {
	    $("#supplierForm").attr("action","web/Ownerorder/Ownerorder.do?pageIndex="+(new_page_index+1));
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
			$("#ssss").click(function(){
				$("#key").val($("#mobile").val());
				 $("#supplierForm").attr("action", "web/Ownerorder/Ownerorder.do?pageIndex="+(1));
				$("#supplierForm").submit();
			})
		});
	</script>
</head>
 <body>
 <div align="left" style="margin-top:20px;margin-left:10px;">
	  <label>订单状态:</label>
	  <input type="checkbox" name="orderstate" id="orderstate1" onclick="checkAll()" value=""/>全部
	  <input type="checkbox" name="orderstate" onclick="selFirst()" value=""/>未支付
	  <input type="checkbox" name="orderstate" onclick="selFirst()" value=""/>已支付
	  <input type="checkbox" name="orderstate" onclick="selFirst()" value=""/>已取消
	  <input type="checkbox" name="orderstate" onclick="selFirst()" value=""/>已完成
	  <input type="checkbox" name="orderstate" onclick="selFirst()" value=""/>已拒绝
	  <br>
	  <label>支付类型:</label>
	  <input type="checkbox" name="paytype"  id="paytype1" onclick="selectAll()" value=""/>全部
	  <input type="checkbox" name="paytype" onclick="selone()" value=""/>微信支付
	  <input type="checkbox" name="paytype" onclick="selone()" value=""/>支付宝
	  <input type="checkbox" name="paytype" onclick="selone()" value=""/>余额支付
   <div align="right" style="margin-top:-72px;"><br/>
 	<input  name="mobile" placeholder="请输入手机号码或者姓名" value="${key}" id="mobile" style="border-radius:10px;width:220px;background-color:#eee;border:0;" >
  	&nbsp;&nbsp;
  	<button id="ssss" type="button" class="btn btn-info">搜索</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </div></div>
  <table class="table table-striped">
	   <thead>
	      <tr>
	         <th>订单编号</th>
	         <th>车位编号</th>
	         <th>订单时间</th>
	         <th>确认时间</th>
	         <th>支付时间</th>
	         <th>支付类型</th>
	         <th>订单状态</th>
	         <th>车牌号</th>
	         <th>用户姓名</th>
	         <th>用户手机号</th>
	         <th>订单金额</th>
	         <th>收费类型</th>
	      </tr>
	   </thead>
	   <tbody>
	   <c:forEach items="${pager.list}" var="c"> 
	      <tr>
	           <td>${c.ordernum}</td>
	           <td>${c.parkno}</td>
	           <td>${fn:substring(c.placeorde_time,0,10)}</td>
	           <td>${fn:substring(c.supplierconfirm_time,0,10)}</td>
	           <td>${fn:substring(c.payment_time,0,10)}</td>
	           <td>
	           <c:choose>
						<c:when test="${c.payType == 0}">
							余额支付
						</c:when>
						<c:when test="${c.payType == 1}">
							支付宝
						</c:when>
						<c:when test="${c.payType == 2}">
							微信支付
						</c:when>
			   </c:choose>
	           </td>
	           <td>
	           <c:choose>
						<c:when test="${c.order_state == 0}">
							未支付
						</c:when>
						<c:when test="${c.order_state == 1}">
							已支付
						</c:when>
						<c:when test="${c.order_state == 2}">
							已取消
						</c:when>
						<c:when test="${c.order_state == 3}">
							已接受
						</c:when>
						<c:when test="${c.order_state == 4}">
							已拒绝
						</c:when>
			   </c:choose>
	           </td>
	           <td>${c.vehicleid}</td>
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
	   </c:forEach>
	   </tbody>
	</table>
<form id="supplierForm"  method="post" action="">
	<div style="" id="News-Pagination"  align="center"></div>
	<input id="key" name="key" type="hidden" value="${key}">
</form>
  </body>
</html>