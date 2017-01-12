<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctxResource}/js/html5.js"></script>
    <script type="text/javascript" src="${ctxResource}/js/respond.min.js"></script>
    <![endif]-->
    <link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/login.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
    <style>
        .header{background:rgba(255,255,255,0.1) no-repeat center;}
        .loginBox{ background: rgba(255,255,255,0.3); }
        .loginBox .bg-grey{ background:rgba(255,255,255,0.8); }
        .hd_msg a{ color:#fff}
        .hd_msg a:hover{ color:#fff; text-decoration:underline}
        .formControls label{ color: #fff;}
        .login_logo{color:#fff;font-size:32px;padding-left:20px}
        .login_logo:hover{color:#fff;text-decoration:none;}
    </style>
    <title>GoldenLeopard后台系统后台登录</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
</head>
<body>
<iframe class="baniframe" sandbox="allow-scripts allow-same-origin" id="mh" src="${ctxResource}/js/a7.html" width="100%" height="100%" style="position: absolute; z-index: 1; border: 0;"></iframe>
<div class="header"><a target="_blank" class="login_logo">GoldenLeopard后台系统</a></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
     	<div class="bg-grey">
       		<i class="Hui-iconfont">&#xe60d;</i>
         	<input id="username" type="text" name="username" placeholder="请输入用户名" class="input-text size-L">
     	</div>
     	<div class="bg-grey">
       		<i class="Hui-iconfont">&#xe60e;</i>
         	<input id="password" name="password" type="password" placeholder="请输入密码" class="input-text size-L">
     	</div>
      <div class="row">
        <div class="formControls col-10 col-offset-3">
          <button id="btn" class="btn btn-primary radius size-L" value="" type="submit">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
        </div>
      </div>
  </div>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script>
$(function(){
    $(window).keydown(function(event){
        if(event.keyCode == 13){
            $('#btn').click();
        }
    });

	$('#btn').click(function(){
			var name = $('#username').val();
			if(name == "" || name == null){ $("#namek").show(150); return false;}
			else{ $("#namek").hide();}
			var pwd = $('#password').val();
			if(pwd == "" || pwd == null){ $('#pwdk').show(250); return false; }
			else{ $("#pwdk").hide();}
			var ckt = $("#checkbox").is(':checked');
			var ckv ;
			if(ckt == true){ ckv = 0; }
			else{ ckv = 1; }
			$.post("<%=request.getContextPath()%>/login", { loginName : name, password: pwd },
		   		function(data){
					if(data.result == 1){
						window.location = "<%=request.getContextPath()%>/index";
					}else{
						layer.alert(data.msg);
					}
		  		}
		  );
	});
});
</script>
</body>
</html>
