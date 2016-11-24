<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户管理</title>
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="/sharebo/css/pagination.css" type="text/css"></link>
   <script type="text/javascript" src="/sharebo/js/jquery.pagination.js"></script>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script type="text/javascript">
  	//点击分页按钮以后触发的动作
	function handlePaginationClick(new_page_index, pagination_container) {
	    $("#supplierForm").attr("action", "web/permiss/users.do?pageIndex="+(new_page_index+1));
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
		         <th>用户姓名</th>
		         <th>用户账号</th>
		         <th>用户角色</th>
		         <th>用户状态</th>
		         <th>注册时间</th>
		         <th>备注</th>
		      </tr>
		   </thead>
		   <tbody>
		    	<c:forEach  items="${pager.list}" var="u">
		    		  <tr>
				         <td>${u.userName}</td>
				         <td>${u.userAccount}</td>
				         <td>${u.roleName}
				         	<button id="${u.userId}"  name="${u.roleId}" type="button" class="btn btn-info btn-xs updateRole_">修改</button>
				         </td>
				         <td>
				         <c:if test="${u.userStatus==0}">
				         	未审核&nbsp;
				         	<button name="${u.userId}" type="button" class="btn btn-success btn-xs ok_">通过</button>
				         	<button name="${u.userId}" type="button" class="btn btn-danger btn-xs no_">不通过</button>
				         </c:if>
				          <c:if test="${u.userStatus==1}">
				         	<span style="color: green;">成功</span>
				         </c:if>
				         <c:if test="${u.userStatus==2}">
				         	<span style="color: red;">失败</span>
				         </c:if>	
				         </td>
				          <td>${u.userTime}</td>
				         <td>
				         	 <button title="${u.userRemark}" type="button" class="btn btn-warning btn-xs showR">显示</button>
				         </td>
				      </tr>
		    	</c:forEach>
		   </tbody>
		</table>
	<script type="text/javascript">
		$(function(){
			//修改角色
			$(".updateRole_").click(function(){
				$("#roleVal").val($(this).attr("name"));
				$("#update_r").attr("name",$(this).attr("name"));
				//用户id
				$("#update_r").attr("title",$(this).attr("id"));
				$("#updateShowRole_").click();
			});
			//提交修改角色
			$("#update_r").click(function(){
				$.ajax({
					url:"web/permiss/updateUserRole.do",
					type:"POST",
					data:{"userId":$(this).attr("title"),"roleId":$("#roleVal").val()},
					dataType:"text",
					success:function(res){
						if(res=="0"){
							window.location.reload();
						}else{
							alert("修改失败！");
						}
					},
					error:function(res){
						alert("后台维护中！！");
					}
				});
			});
			//接受通过
			$(".ok_").click(function(){
				var $t=$(this);
				$.ajax({
					url:"web/permiss/updateUserStatus.do",
					type:"POST",
					data:{"userId":$(this).attr("name"),"userStatus":1},
					dataType:"text",
					success:function(res){
						if(res=="0"){
							$t.parent().html("<span style=\"color: green;\">成功</span>");
						}else{
							alert("审核失败！");
						}
					},
					error:function(res){
						alert("后台维护中！！");
					}
				});
			});
			//拒绝通过
			$(".no_").click(function(){
				var $t=$(this);
				$.ajax({
					url:"web/permiss/updateUserStatus.do",
					type:"POST",
					data:{"userId":$(this).attr("name"),"userStatus":2},
					dataType:"text",
					success:function(res){
						if(res=="0"){
							$t.parent().html("<span style=\"color: red;\">失败</span>");
						}else{
							alert("审核失败！");
						}
					},
					error:function(res){
						alert("后台维护中！！");
					}
				});
			});
			//显示备注
			$(".showR").click(function(){
				$("#showBody").html($(this).attr("title"));
				$("#showRemark_").click();
			});
		})
	</script>	
<form id="supplierForm"  method="post" action=""></form>
<div style="" id="News-Pagination"  align="center"></div>	
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-lg" id="updateShowRole_" data-toggle="modal"  style="display: none;"  data-target="#updateShowRole"> </button>

<!-- 模态框（Modal） -->
<div class="modal fade" id="updateShowRole" tabindex="-1" role="dialog" 
   aria-labelledby="updateShowRolelLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
              修改角色
            </h4>
         </div>
         <div class="modal-body">
			      <select id="roleVal" class="form-control">
			         <c:forEach items="${role}" var="r">
			         	<option value="${r.roleId}">${r.roleName}</option>
			         </c:forEach>
			      </select>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button id="update_r" title="" name="" type="button" class="btn btn-primary">
               		提交更改
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>


	
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
