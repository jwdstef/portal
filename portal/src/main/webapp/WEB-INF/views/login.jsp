<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/css/system/user/login.css" />
	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/easyui/locale/easyui-lang-zh_CN.js">
<title>登录页面</title>
<script type="text/javascript">
	$(function() {
		//得到焦点
		$("#password").focus(function() {
			console.log('focus');
			$("#left_hand").animate({
				left : "150",
				top : " -38"
			}, {
				step : function() {
					if (parseInt($("#left_hand").css("left")) > 140) {
						$("#left_hand").attr("class", "left_hand");
					}
				}
			}, 2000);
			$("#right_hand").animate({
				right : "-64",
				top : "-38px"
			}, {
				step : function() {
					if (parseInt($("#right_hand").css("right")) > -70) {
						$("#right_hand").attr("class", "right_hand");
					}
				}
			}, 2000);
		});
		//失去焦点
		$("#password").blur(function() {
			$("#left_hand").attr("class", "initial_left_hand");
			$("#left_hand").attr("style", "left:100px;top:-12px;");
			$("#right_hand").attr("class", "initial_right_hand");
			$("#right_hand").attr("style", "right:-112px;top:-12px");
		});
	});
</script>
</head>
<body>
	<div class="top_div"></div>
	
		<div
			style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
			<div style="width: 165px; height: 96px; position: absolute;">
				<div class="tou"></div>
				<div class="initial_left_hand" id="left_hand"></div>
				<div class="initial_right_hand" id="right_hand"></div>
			</div>
			<p style="padding: 30px 0px 10px; position: relative;">
				<span class="u_logo"></span>
				<input class="ipt" placeholder="请输入用户名或邮箱" name="userName" id="userName" />
			</p>
			<P style="position: relative;">
				<span class="p_logo"></span>
				<input type="password" class="ipt" placeholder="请输入密码" name="password" id="password" />
			</P>
			<div
				style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
				<p style="margin: 0px 35px 20px 45px;">
					<span style="float: left;"><a
						style="color: rgb(204, 204, 204);" href="#">忘记密码?</a></span> <span
						style="float: right;"><A
						style="color: rgb(204, 204, 204); margin-right: 10px;" href="#">注册</A>
						<a
						style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;"
						href="#" onclick="sub();" >登录</a> </span>
				</p>
			</div>
		</div>
	<script type="text/javascript">
		function sub(){
			var name=$("#userName").val();
			var pwd=$("#password").val();
			if(name==undefined||pwd==undefined){
				$.messager.alert("登录提示","请输入正确的账号密码");
				return;
			}
			$.post('login',{
				userName:name,
				password:pwd
			},function(data){
				if(data.success){
					window.location="${pageContext.request.contextPath}/home"
				}else{
					$.messager.alert('登录提示',data.errorMsg); 
				}
			});
		}
	</script>
</body></html>