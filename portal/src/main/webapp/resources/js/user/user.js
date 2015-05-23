$(function() {
	var basePath = "${pageContext.request.contextPath }";
	$.extend($.fn.validatebox.defaults.rules, {
		equals : {
			validator : function(value, param) {
				return value == $(param[0]).val();
			},
			message : '密码输入不一致，请重新输入'
		}
	});
	$("#userDataGrid").datagrid({
		url : '../user/',
		method : 'post',
		pagination : true,
		rownumbers : true,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'userId',
			title : '用户ID',
			hidden : true,
			width : 100
		}, {
			field : 'userName',
			title : '用户名称',
			width : 200
		}, {
			field : 'locked',
			title : '是否锁定',
			width : 100
		}, {
			field : 'realName',
			title : '昵称',
			width : 100
		}, {
			field : 'email',
			title : '邮箱',
			width : 100
		}, {
			field : 'phone',
			title : '电话',
			width : 100
		} ] ],
		toolbar : [ {
			iconCls : 'icon-add',
			text : '新增用户',
			handler : function() {
				initDialog();
				$("#userDeditDialog").dialog({
					title : '用户新增页面',
					closed : false
				});
			}
		}, '-', {
			iconCls : 'icon-delete',
			text : '删除用户',
			handler : function() {
				var rows = $("#userDataGrid").datagrid("getSelections");
				if (rows.length > 0) {
					var ids = "";
					$.each(rows, function(n, row) {
						ids = ids + row.userId;
						if (rows.length != n + 1) {
							ids = ids + ",";
						}
					});
					$.post("../user/delete", {
						'ids' : ids
					}, function(data) {
						if (data) {
							$.messager.alert("提示", "删除用户成功", "info");
							$("#userDataGrid").datagrid('load');
						} else {
							$.messager.alert("提示", "删除用户失败", "error");
						}
					});
				}
			}
		}, '-', {
			iconCls : 'icon-edit',
			text : '修改用户',
			handler : function() {
				var rows = $("#userDataGrid").datagrid("getSelections")
				if (rows.length != 1) {
					$.messager.alert("提示", "请选择一个用户修改其密码", "info");
				} else {
					$("#userDeditDialog").dialog({
						title : '用户修改页面'
					}).dialog('open');
					$("#userId").val(rows[0].userId);
					$("#salt").val(rows[0].salt);
					$("#locked").combobox("setValue", rows[0].locked)
					console.log(rows[0].userId);
					$("#userName").val(rows[0].userName);
					$("#userName").attr("readonly", true);
					$("#realName").val(rows[0].realName);
					$("#email").val(rows[0].email);
					$("#phone").val(rows[0].phone);

					initUpdateDialog();
				}
			}
		}, '-', {
			iconCls : 'icon-help',
			text : '重置密码',
			handler : function() {
				var rows = $("#userDataGrid").datagrid("getSelections");
				if (rows.length != 1) {
					$.messager.alert("提示", "请选择一个用户修改其密码", "info");
				} else {
					$.post('../user/reSetPwd', {
						'userId' : rows[0].userId,
						'salt' : rows[0].salt
					}, function(data) {
						$.messager.alert("提示", "密码重置成功", "info");
						$("#userDataGrid").datagrid("clearSelections");
						$("#userDataGrid").datagrid("clearChecked");
					});
				}
			}
		} ]
	});
	// 初始化用戶user弹出框
	$("#userDeditDialog").dialog({
		title : '用户新增页面',
		closed : true,
		width : 600,
		height : 250,
		modal : true,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				$("#userSaveForm").submit();
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$("#userDeditDialog").dialog({
					closed : true
				});
			}
		} ]
	});

	$('#userSaveForm').form({
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			if (data) {
				$.messager.alert("提示", "用户信息添加成功", "info");
				$("#userDeditDialog").dialog('close');
				$("#userDataGrid").datagrid('load');
				$("#userSaveForm").form('clear');
			}

		}
	});

});
// 修改初始化对话框
function initUpdateDialog() {
	$("#rePassword").validatebox({
		validType : "equals['#password']"
	});
	$("#email").validatebox({
		validType : 'email'
	});
	$("#password").validatebox({
		required : false,
		validType : 'length[10,50]'
	});
	$("#rePassword").validatebox({
		required : false,
		validType : "equals['#password']"
	});
	$('#userSaveForm').form({
		url : '../user/update',
		type : 'post',
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			if (data) {
				$.messager.alert("提示", "用户信息更新成功", "info");
				$("#userDeditDialog").dialog('close');
				$("#userDataGrid").datagrid('load');
				$("#userSaveForm").form('clear');
			}

		}
	});
}
// 新增初始化对话框
function initDialog() {
	$("#locked").combobox("setValue", false);
	$("#userName").attr("readonly", false);
	$("#userName").validatebox({
		required : true,
		validType : 'length[10,50]'
	});

	$("#password").validatebox({
		required : true,
		validType : 'length[10,50]'
	});
	$("#rePassword").validatebox({
		required : true,
		validType : "equals['#password']"
	});
	$("#email").validatebox({
		validType : 'email'
	});

}