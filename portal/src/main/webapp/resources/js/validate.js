$
		.extend(
				$.fn.validatebox.defaults.rules,
				{
					idcard : {// 验证身份证
						validator : function(value) {
							return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
						},
						message : '身份证号码格式不正确'
					},
					phone : {// 验证电话号码
						validator : function(value) {
							return /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i
									.test(value);
						},
						message : '格式不正确,请使用下面格式:020-88888888'
					},
					mobile : {// 验证手机号码
						validator : function(value) {
							return /^(13|15|18)\d{9}$/i.test(value);
						},
						message : '手机号码格式不正确'
					},
					qq : {// 验证QQ,从10000开始
						validator : function(value) {
							return /^[1-9]\d{4,9}$/i.test(value);
						},
						message : 'QQ号码格式不正确'
					},
					integer : {// 验证整数 可正负数
						validator : function(value) {
							// return /^[+]?[1-9]+\d*$/i.test(value);

							return /^([+]?[0-9])|([-]?[0-9])+\d*$/i.test(value);
						},
						message : '请输入整数'
					},
					chinese : {// 验证中文
						validator : function(value) {
							return /^[\Α-\￥]+$/i.test(value);
						},
						message : '请输入中文'
					},
					english : {// 验证英语
						validator : function(value) {
							return /^[A-Za-z]+$/i.test(value);
						},
						message : '请输入英文'
					},
					unnormal : {// 验证是否包含空格和非法字符
						validator : function(value) {
							return /.+/i.test(value);
						},
						message : '输入值不能为空和包含其他非法字符'
					},
					username : {// 验证用户名
						validator : function(value) {
							return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
						},
						message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
					},
					faxno : {// 验证传真
						validator : function(value) {
							// return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[
							// ]){1,12})+$/i.test(value);
							return /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i
									.test(value);
						},
						message : '传真号码不正确'
					},
					zip : {// 验证邮政编码
						validator : function(value) {
							return /^[1-9]\d{5}$/i.test(value);
						},
						message : '邮政编码格式不正确'
					},
					ip : {// 验证IP地址
						validator : function(value) {
							return /d+.d+.d+.d+/i.test(value);
						},
						message : 'IP地址格式不正确'
					},
					name : {// 验证姓名，可以是中文或英文
						validator : function(value) {
							return /^[\Α-\￥]+$/i.test(value)
									| /^\w+[\w\s]+\w+$/i.test(value);
						},
						message : '请输入姓名'
					}
				});
