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
    <form id="registerForm" class="registerBox" action="<%=request.getContextPath()%>/register" method="post">
        <div class="register-title">
            <span style="padding-left: 10px;">账户注册</span>
            <a style="text-decoration:none;color:green;float: right;padding-right: 10px;" href="<%=request.getContextPath()%>/login">已有账号</a>
        </div>
        <div class="info-area">
            <div class="row cl item">
                <label class="register-label form-label col-2">店铺账号：</label>
                <div class="formControls col-10">
                    <input id="username" type="text" name="username" placeholder="" class="input-text radius item-input">
                </div>
            </div>
            <div class="row cl item">
                <div class="row col-6">
                    <label class="register-label form-label col-4">登录密码：</label>
                    <div class="formControls col-8">
                        <input id="password" name="password" type="password" placeholder="请输入密码" class="input-text radius item-input">
                    </div>
                </div>
                <div class="row col-6">
                    <label class="register-label form-label col-4">确认密码：</label>
                    <div class="formControls col-8">
                        <input id="checkPassword" name="checkPassword" type="password" placeholder="重复输入登陆密码" class="input-text radius item-input">
                    </div>
                </div>
            </div>
            <div class="row cl item">
                <div class="col-6">
                    <label class="register-label form-label col-4">手机号码：</label>
                    <div class="formControls col-8">
                        <input id="phone" name="mobile" type="text" class="input-text radius item-input">
                    </div>
                </div>
                <div class="col-6">
                    <label class="register-label form-label col-4">电子邮箱：</label>
                    <div class="formControls col-8">
                        <input id="email" name="email" type="text" class="input-text radius item-input">
                    </div>
                </div>
            </div>
            <div class="row cl item">
                <div class="row col-6">
                    <label class="register-label form-label col-4">店铺名称：</label>
                    <div class="formControls col-8">
                        <input id="storeName" name="name" type="text" class="input-text radius item-input">
                    </div>
                </div>
                <div class="row col-6">
                    <label class="register-label form-label col-4">所属行业：</label>
                    <div class="formControls col-8">
                        <select class="select-box item-input" id="industry" name = "industry" style="height: 38px;">
                            <option value selected>选择所属行业</option>
                            <option value="1">餐饮行业</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row cl item">
                <label class="register-label form-label col-2">详细地址：</label>
                <div class="formControls col-10">
                    <input type="text" class="input-text radius item-input" name = "addr" id = "addr"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="formControls col-10 col-offset-3">
                <button id="btn" class="btn btn-primary radius size-L" value="" type="submit">免费注册</button>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/Validform_v5.3.2_min.js"></script>
<script>
    $(function(){
        $(window).keydown(function(event){
            if(event.keyCode == 13){
                $('#btn').click();
            }
        });

        var  validtor = $("#registerForm").Validform({
            tiptype:4,
            showAllError:true,
            ajaxPost:true,
            ignoreHidden:true, //可选项 true | false 默认为false，当为true时对:hidden的表单元素将不做验证;
            tipSweep:true,//可选项 true | false 默认为false，只在表单提交时触发检测，blur事件将不会触发检测
            btnSubmit:"#btn",
            callback:function (data) {
                if(data.result == 1){
                    layer.msg(data.msg, {time : 2000, icon : 6}, function () {
                        window.location.href = "login";
                    });
                }else{
                    layer.msg(data.msg, {time : 2000, icon : 5});
                }
            }
        });
        validtor.addRule([
            {
                ele:"#username",
                datatype:"*",
                nullmsg :"* 用户名为必填项"
            },
            {
                ele:"#password",
                datatype:"*",
                nullmsg :"* 登录密码为必填项"
            },
            {
                ele:"#checkPassword",
                datatype:"*",
                recheck:"password",
                errormsg:"* 两次输入的密码不一致"
            },
            {
                ele:"#phone",
                datatype:"m",
                nullmsg:"* 手机号码必填",
                errormsg:"* 请输入正确的手机号码",
            },
            {
                ele:"#addr",
                datatype:"*",
                nullmsg:"* 详细地址必填"
            },
            {
                ele:"#storeName",
                datatype:"*",
                nullmsg:"* 店铺名称为必填项"
            },
            {
                ele:"#industry",
                datatype:"*",
                nullmsg:"* 请选择所属行业"
            },
            {
                ele:"#email",
                datatype:"e",
                errormsg:"* 请填写正确的邮箱地址",
                nullmsg:"* 电子邮箱为必填项"
            }
        ]);
    });
</script>
</body>
</html>
