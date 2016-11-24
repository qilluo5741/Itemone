<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>菜单管理</title>
   <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="/sharebo/css/pagination.css" type="text/css"></link>
   <script type="text/javascript" src="/sharebo/js/jquery.pagination.js"></script>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script type="text/javascript">
  	//点击分页按钮以后触发的动作
	function handlePaginationClick(new_page_index, pagination_container) {
	    $("#supplierForm").attr("action", "web/permiss/menus.do?pageIndex="+(new_page_index+1));
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
<button  class="btn btn-primary" data-toggle="modal" data-target="#show">&nbsp;&nbsp;&nbsp;&nbsp;添加菜单&nbsp;&nbsp;&nbsp;&nbsp;</button>
  <hr/>
<table class="table table-striped">
   <thead>
      <tr>
         <th>菜单名称</th>
         <th>菜单路径</th>
         <th>菜单类型</th>
         <th>管理</th>
      </tr>
   </thead>
   <tbody>
   <c:forEach items="${pager.list}" var="m"> 
      <tr>
         <td>${m.menuName}</td>
          <td>${m.href}</td>
           <td>${m.mt.menuTypeName}</td>
         <td>
         	<button id="${m.menuId}" name="${m.mt.menuTypeId}" type="button" class="btn btn-success update_">修改</button>
			<button id="${m.menuId}" type="button" class="btn btn-danger delete_" >删除</button>
		</td>
      </tr>
   </c:forEach>
      
   </tbody>
</table>
<script type="text/javascript">
	$(function(){
		//点击修改，根据id 查询
		$(".update_").click(function(){
			var id=$(this).attr("id");
			var menuType=$(this).attr("name");
			//查询
			$.ajax({
					url:'web/permiss/getMenuById.do',
					data:{"menuId":id},
					type:"post",
					dataType:"json",
					success:function(res){
						//设置值
						$("#menuId").val(res.menuId);
						$("#menuName").val(res.menuName);
						$("#href").val(res.href);
						$("#menuType").val(menuType);
						$("#UpdateMenus").click();
					},
					error:function(){
						alert("后台维护中！");
					}
				});
		})
		//修改
		$("#updateMenu").click(function(){
			var menuid=$("#menuId").val();
			var menuName=$("#menuName").val();
			var　href=$("#href").val();
			var menuTypeId=$("#menuType").val();
			//alert("id为："+menuid);
			//验证菜单值是否合法
			if(menuName==""||href==""){
				alert("菜单名字，路径不能为空哟！");
			}else{
				//修改菜单
				$.ajax({
					url:'web/permiss/updateMenuById.do',
					data:{"menuId":menuid,"menuName":menuName,"href":href,"mt.menuTypeId":menuTypeId},
					type:"post",
					success:function(res){
						if(res=="0"){
							//刷新页面
							window.location.reload(); 
						}else{
							alert("修改失败！");
						}
					},
					error:function(){
						alert("后台维护中！");
					}
				});
			}
		});
		//删除菜单
		$(".delete_").click(function(){
			var menuId=$(this).attr("id");
			if(confirm("您确认要删除嘛？删除了就找不到了哟！")){
					$.ajax({
					url:'web/permiss/deleteMenusById.do',
					data:{"menuId":menuId},
					type:"post",
					success:function(res){
						if(res=="1"){
							//刷新页面
							window.location.reload(); 
						}else if(res=="0"){
							alert("还有角色拥有此菜单哟！不能删除滴！");
						}else{
							alert("删除失败！");
						}
					},
					error:function(){
						alert("后台维护中！");
					}
				});
			}
		});
		//添加菜单
		$("#addMenus").click(function(){
			//得到参数值
			var menuName=$("#add_menuName").val();
			var　href=$("#add_href").val();
			var menuTypeId=$("#add_menuType").val();
			//验证参数是否合法
			if(menuName==""){
				alert("菜单名字不能为空哟！");
			}else if(href==""){
				alert("路径不能为空！");
			}else{
				//添加菜单
				$.ajax({
					url:'web/permiss/addMenus.do',
					data:{"menuName":menuName,"href":href,"mt.menuTypeId":menuTypeId},
					type:"post",
					success:function(res){
						if(res=="0"){
							alert("\""+menuName+"\" 这个菜单已经存在了哟！不能重复添加哈！");
						}
						else if(res=="1"){
							//刷新页面
							window.location.reload(); 
						}else{
							alert("添加失败！");
						}
					},
					error:function(){
						alert("后台维护中！");
					}
				});
			}
		});
		
	})
</script>
<form id="supplierForm"  method="post" action=""></form>
<div style="" id="News-Pagination"  align="center"></div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="show" tabindex="-1" role="dialog" 
   aria-labelledby="showLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="showLabel">
               	新增菜单
            </h4>
         </div>
         <div class="modal-body">
            	菜单名字:
            	<input type="text" class="form-control"  placeholder="请输入菜单名称" id="add_menuName">
            	菜单类型：
            	 <select id="add_menuType" class="form-control">
			         <c:forEach items="${menutype}" var="m">
			         	<option value="${m.menuTypeId}">${m.menuTypeName}</option>
			         </c:forEach>
			      </select>
            	菜单路径:
            	<textarea class="form-control" rows="3"  placeholder="请输入菜单链接地址" id="add_href"></textarea>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">取消
            </button>
            <button type="button" id="addMenus" class="btn btn-primary" data-dismiss="modal">
              		确认添加
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<button  class="btn btn-primary" id="UpdateMenus" data-toggle="modal" data-target="#showUpdate" style="display: none;"></button>
<!-- 模态框（Modal） -->
<div class="modal fade" id="showUpdate" tabindex="-1" role="dialog" 
   aria-labelledby="showUpdateLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="showUpdateLabel">
               	修改菜单
            </h4>
         </div>
         <div class="modal-body">
            	<input type="hidden" class="form-control" id="menuId">
            	菜单名字:
            	<input type="text" class="form-control" id="menuName">
            	菜单类型：
            	 <select id="menuType" class="form-control">
			         <c:forEach items="${menutype}" var="m">
			         	<option value="${m.menuTypeId}">${m.menuTypeName}</option>
			         </c:forEach>
			      </select>
            	菜单路径:
            	<textarea class="form-control" rows="3" id="href"></textarea>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">取消
            </button>
            <button type="button" id="updateMenu" class="btn btn-primary" data-dismiss="modal">
              		确认修改
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
  </body>
</html>

