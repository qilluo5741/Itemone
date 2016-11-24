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
    
    <title>菜单类型管理</title>
   <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  	
  <body>
<button  class="btn btn-primary" data-toggle="modal" data-target="#show">&nbsp;&nbsp;&nbsp;&nbsp;添加&nbsp;&nbsp;&nbsp;&nbsp;</button>
  <hr/>
<table class="table table-striped">
   <thead>
      <tr>
         <th>菜单类型名称</th>
         <th>管理</th>
      </tr>
   </thead>
   <tbody>
   <c:forEach items="${menusType}" var="mt">
      <tr>
         <td class="meunsTypeName" width="50%">${mt.menuTypeName}</td>
         <td class="menusManager">
         	<button id="${mt.menuTypeId}" type="button" class="btn btn-success update_">修改</button>
			<button id="${mt.menuTypeId}" type="button" class="btn btn-danger delete_" >删除</button>
		</td>
      </tr>
   </c:forEach>
      
   </tbody>
</table>
<script type="text/javascript">
	$(function(){
		
		//修改改变该文字状态
		$(".update_").click(function(){
			//得到文本对象
			var $testObj=$(this).parent().parent().children(".meunsTypeName");
			//得到原始值
			var name=$testObj.text();
			//嵌入文本框
			var testVal="<div style='width: 100%'> <div class='input-group'> " +
			"<input type='text' class='form-control ' value='"+name+"'>" +
			"<span class='input-group-btn'> <button class='btn btn-default' id='"+$(this).attr("id")+"' onclick='javascript:$.confirm(this)' type='button'>确定  </button>" +
			" <button class='btn btn-default' type='button' val_id="+name+" onclick='javasscript:$.cancel(this);'> 取消  </button> " +
			"</span> </div> </div>";
			//设置值
			$testObj.html(testVal);
			//禁用修改按钮
			$(this).attr("disabled","disabled");
		});
		//点击取消
		$.cancel=function(obj){
			var $del_=$(obj).parent().parent().parent().parent(".meunsTypeName");
			$del_.html($(obj).attr("val_id"));
			$del_.next().children(".update_").removeAttr("disabled");
		}
		//点击确认
		$.confirm=function(obj){
			//得到id
			var id=$(obj).attr("id");
			//得到修改的值
			var val=$(obj).parent().parent().children("input").val();
			//验证修改的值
			if(val==""){
				alert("菜单类型不能为空！");
			}else{
				//执行修改
				var $del_=$(obj).parent().parent().parent().parent(".meunsTypeName");
				$del_.html(val);
				$del_.next().children(".update_").removeAttr("disabled");
				$.ajax({
					url:'web/permiss/updateMeunsTypeById.do',
					data:{"menuTypeId":id,"menuTypeName":val},
					type:"post",
					success:function(res){
						if(res=="fasle"){
							alert("修改失败！");
						}
					},
					error:function(){
						alert("后台维护中！");
					}
				});
			}
		}
		//删除
		$(".delete_").click(function(){
			if(confirm("你确定要删除吗？您可要想好了哟！")){
				var $rem=$(this).parent().parent();
				$.ajax({
				url:'web/permiss/deleteMeunsTypeById.do',
				data:{"menuTypeId":$(this).attr("id")},
				type:"post",
				success:function(res){
					if(res=="0"){
						alert("该类型存在菜单！不能删除！");
					}else if(res=="1"){
						//得到当前这一行对象删除
						$rem.remove();
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
		//添加
		$("#addMenusType").click(function(){
			//获取菜单名字
			var mtName=$("#mtypeName").val();
			//获取菜单说明
			var mtRemork=$("#mtypeRemork").val();
			if(mtName==""){
				alert("没有菜单类型名字是空的哟！");
			}
			else{
				//执行添加
				$.ajax({
				url:'web/permiss/addMenuType.do',
				data:{"menuTypeName":mtName,"remork":mtRemork},
				type:"post",
				success:function(res){
					if(res=="0"){
						alert("该菜单类型已经存在！不能重复添加了哟！");
					}else if(res=="1"){
						//添加成功！
						window.location.reload(true); 
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
	});
	
	
</script>

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
               	添加菜单
            </h4>
         </div>
         <div class="modal-body">
            	<input type="text" class="form-control" id="mtypeName" placeholder="请输入菜单类型名称">
            	<textarea class="form-control" rows="3" id="mtypeRemork" placeholder="菜单说明"></textarea>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">取消
            </button>
            <button type="button" id="addMenusType" class="btn btn-primary" data-dismiss="modal">
              		确认添加
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
  </body>
</html>
