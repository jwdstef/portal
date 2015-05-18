<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/user/user.js"></script>
<style type="text/css">
.form {
	border-collapse: collapse;
	border: none;
	color: #cccccc;
	line-height: 28px;
}

.form td {
	border: solid #000 1px;
	border: 1px solid #cccccc;
	line-height: 28px;
}

.label_left {
	background-color: #f1f6ff;
	width: 80px;
}

.label_right {
	background-color: #fafcff;
	width: 180px;
}
.
</style>
</head>
<body style="height: 550px;">
	<table id="userDataGrid">
	</table>
	<div id="userDeditDialog">
		<div style="margin: 10px 30px; width: 540px">
			<form id="userSaveForm" action="${pageContext.request.contextPath  }/user/save" method="post" >
				<table class="form">
					<tr>
						<td class="label_left">&nbsp;* 账号:</td>
						<td class="label_right">
							<input type="text" id="userName" name="userName" />
							<input  type="hidden" id="userId" name="userId" />
							</td>
						<td class="label_left">&nbsp;* 是否锁定:</td>
						<td class="label_right">
							<select id="locked"
								class="easyui-combobox" name="locked" style="width: 50px;" data-options="panelHeight:'auto',value:'0'">
									<option value="0">否</option>
									<option value="1">是</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="label_left">&nbsp;* 密码:</td>
						<td class="label_right"><input class="easyui-validatebox"
							type="password" id="password" name="password" /></td>
						<td class="label_left">&nbsp;* 重复密码:</td>
						<td class="label_right"><input type="password" id="rePassword" name="rePassword"  /></td>
					</tr>
					<tr>
						<td class="label_left">&nbsp;真实姓名:</td>
						<td class="label_right"><input class="easyui-validatebox"
							type="text" id="realName" name="realName" /></td>
						<td class="label_left">&nbsp;邮箱:</td>
						<td class="label_right"><input class="easyui-validatebox"
							type="text" id="email" name="email" /></td>
					</tr>
					<tr>
						<td class="label_left">&nbsp;电话:</td>
						<td class="label_right"><input class="easyui-validatebox"
							type="text" id="phone" name="phone" /></td>
						<td class="label_left"></td>
						<td class="label_right"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>