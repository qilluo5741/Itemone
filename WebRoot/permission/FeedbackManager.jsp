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
    
    <title>用户信息反馈</title>
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
	    $("#supplierForm").attr("action","web/feed/getFeedback.do?pageIndex="+(new_page_index+1));
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
</head>
 <body>
  <table class="table table-striped">
	   <thead>
	      <tr>
	         <th>反馈图片</th>
	         <th>反馈时间</th>
	         <th>反馈姓名</th>
	         <th>反馈手机</th>
	         <th>反馈年龄</th>
	         <th>反馈性别</th>
	         <th>反馈内容</th>
	      </tr>
	   </thead>
	   <tbody>
	   <c:forEach items="${pager.list}" var="c"> 
	      <tr>
	      		<td>
		           <c:choose>
						<c:when test="${c.feedback_img == null}">
							  <img src="image/4.jpg" width="40px;" height="40px;" style="border-radius:50%"/></td>
						</c:when>
						<c:when test="${c.feedback_img != null}">
							 <img src="${c.feedback_img}" width="40px;" height="40px;" style="border-radius:50%"/></td>
						</c:when>
					</c:choose>
				</td>
	           <td>${fn:substring(c.feedback_time,0,19)}</td>
	       	   <td>${c.userName}</td>
	           <td>${c.mobile}</td>
	           <td>${c.age}后</td>
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
	          <td><button title="${c.feedback_content}" type="button" class="btn btn-warning btn-xs showR">显示</button><td>
	     </tr>
	   </c:forEach>
	   </tbody>
	</table>
	<script type="text/javascript">
		$(function(){
			//显示内容
			$(".showR").click(function(){
				$("#showBody").html($(this).attr("title"));
				$("#showRemark_").click();
			});
		})
	</script>
	<form id="supplierForm"  method="post" action="">
	<div style="" id="News-Pagination"  align="center"></div>
</form>
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-lg" data-toggle="modal" id="showRemark_" style="display: none;" data-target="#showRemark">
</button>
<!-- 模态框（Modal） -->
<div class="modal fade" id="showRemark" tabindex="-1" role="dialog" 
   aria-labelledby="myshowRemarkLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" 
               aria-hidden="true">×
            </button>
            <h4 class="modal-title" id="myshowRemarkLabel">
        			用户备注
            </h4>
         </div>
         <div id="showBody" class="modal-body">
         		 
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
         </div>
      </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
  </body>
</html>