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
    <link href="${ctxResource}/css/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
    <title>易捷云收银管理系统</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
</head>
<body>
<div class="header"><a target="_blank" class="login_logo">易捷云收银管理系统</a></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
        <div class="loginTitle">
           <span>账户登录</span>
        </div>
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
      <div class="row">
          <div class="formControls col-10 col-offset-8 mt-20">
              <a href="register" class="ml-30" style="color:#3bb4f2;text-decoration:none">还没账号？</a>
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
			if(name == "" || name == null){return false;}
			var pwd = $('#password').val();
			if(pwd == "" || pwd == null){return false; }
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
