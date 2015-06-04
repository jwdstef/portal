<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理页面</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/validate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/role/role.js"></script>
</head>
<body style="height: 550px;">
	<table id="roleDataGrid">
	</table>
	
	<div id="roleFulBar">
		<div class="datagrid-toolbar">
			<table ellspacing="0" cellpadding="0">
				<tr>
					<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加角色</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除角色</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改角色</a></td>
				</tr>
			</table>
		</div>
		<div style="margin-left:10px;">
			  <strong>角色名称: </strong> <input id="roleNameSearch" class="easyui-searchbox" data-options="prompt:'请输入角色名称',searcher:queryByRoleName" style="width:180px"></input>
		</div>
	</div>
</body>
</html>