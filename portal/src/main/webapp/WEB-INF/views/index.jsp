<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
<script type="text/javascript">
	$(function() {
		$("#menuTree").tree(
				{
					url : '${pageContext.request.contextPath}/menu/treeMenu',
					method : 'post',
					animate : true,
					onClick : function(node) {
						if ($(this).tree('isLeaf', node.target)
								&& node.attributes.url != undefined) {
							addTab(node.text,node.attributes.url);
						} else {
							$(this).tree(
									node.state === 'closed' ? 'expand'
											: 'collapse', node.target);
							node.state = node.state === 'closed' ? 'open'
									: 'closed';
						}
					}
				});
	});
	function addTab(title, url) {
		//如果存在tab，激活该tab为选中状态,return.
		if ($("#mainTab").tabs('exists', title)) {
			$("#mainTab").tabs('select', title);
			return;
		}
		//不存在，创建一个新德tab
		$("#mainTab").tabs(
				"add",
				{
					title : title,
					closable : true,
					content : '<iframe scrolling="auto" frameborder="0"  src="'
							+ url
							+ '" style="width:100%;height:100%;"></iframe>'
				});
	}
</script>
<title>快速开发系统主页</title>
</head>
<body style="width: 1200px; height: 768px; margin: 10px auto;">
	<div class="easyui-layout" style="width: 1200px; height: 768px;">
		<div data-options="region:'north'" style="height: 70px">
			<h1 style="text-align: center; line-height: 35px;">快速开发系统</h1>
		</div>
		<div data-options="region:'south',split:true" style="height: 50px;"></div>
		<div data-options="region:'west',split:true" title="菜单"
			style="width: 200px;">
			<ul id="menuTree"></ul>
		</div>
		<div data-options="region:'center'">
			<div id="mainTab" class="easyui-tabs" data-options="fit:true">
				<div title="主页">
					<h1 style="text-align: center;">快速开发系统</h1>
					<div style="text-align: left;font-size: 20px;">
						v1.0 版本更新<br/>
						1. 采用了Spring+SpringMvc+mybatis+shiro+easyui技术
						2. 实现菜单管理，用户管理，角色权限管理
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>