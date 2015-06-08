$(function() {
	$("#roleDataGrid").datagrid({
		url:'../role/',
		method:'post',
		fitColumns:true,
		loadMsg:'角色信息正在加载请稍等......',
		toolbar:'#roleFulBar',
		rownumbers:true,
		pagination:true,
		columns:[{
			field : 'ck',
			checkbox : true
		},{
			field : 'roleId',
			title : '角色ID',
			hidden : true
		},{
			field:'roleName',
			title:'角色名称',
			width:200
		},{
			field:'remark',
			title:'备注',
			width:200
		}],
		queryParams:{
			"queryParams['roleName']" : $('#roleNameSearch').val()
		},
		onLoadSuccess:function(data){
			if(data.rows.length==0){
				var body = $(this).data().datagrid.dc.body2;
				body.find('table tbody').append('<tr><td width="'+body.width()+'" style="height: 25px; text-align: center;" colspan="6">没有数据</td></tr>');
			}else{
				alert("zs");
			}
		}
	});
});

function queryByRoleName() {
	$("#roleDataGrid").datagrid('reload');
}