<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <script type="text/javascript" src="lib/PIE_IE678.js"></script>
    <![endif]-->
    <link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/admin.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
    <title></title>
</head>
<body>
<div class="pd-20 minwidth">
    <form class="form form-horizontal" id="form-user-edit" action = "<%=request.getContextPath()%>/server/user/edit" type = "post">
        <div class="row cl">
            <label class="form-label col-3">账号：</label>
            <div class="formControls col-7">
                <span>${user.loginName}</span>
                <input value = "${user.id}" name = "id" type="hidden"/>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">用户名：</label>
            <div class="formControls col-7">
                <input type="text" class="input-text radius" value="${user.username}"  id="user_username" name = "username">
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">电话：</label>
            <div class="formControls col-7">
                <input type="text" class="input-text radius" value="${user.mobile}"  id="user_mobile" name = "mobile">
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">邮箱：</label>
            <div class="formControls col-7"><input type = "text" class="input-text radius" name = "email" id = "user_email" value = "${user.email}"/></div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">地址：</label>
            <div class="formControls col-7">
                <textarea rows="2" maxlength="200" class="edit_txt textarea radius" id="user_addr" name = "addr" value = "${user.addr}">${user.addr}</textarea>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">用户权限：</label>
            <div class="formControls col-7">
                <div class="mb-40 pd-20 clearfixs" id="ckBox">
                    <input type="hidden" name = "permissionIds" id="permissions" value/>
                    <br clear="all" />
                </div>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <div class="col-10 col-offset-5 mt-20">
                <input class="btn btn-primary radius" type="button" id="userEditBtn" value="&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctxResource}/js/myself.js"></script>
<script type="text/javascript" src="${ctxResource}/js/Validform_v5.3.2_min.js"></script>
<script>
    $(function () {
        var  validtor = $("#form-vipLevel-add").Validform({
            tiptype:4,
            showAllError:true,
            ajaxPost:true,
            ignoreHidden:true, //可选项 true | false 默认为false，当为true时对:hidden的表单元素将不做验证;
            tipSweep:true,//可选项 true | false 默认为false，只在表单提交时触发检测，blur事件将不会触发检测
            btnSubmit:"#userEditBtn",
            callback:function (data) {
                if(data.result == 1){
                    window.parent.table.fnDraw();
                    layer.msg(data.msg, {time : 2000, icon : 6}, function () {
                        layer_close();
                    });
                }else{
                    layer.msg(data.msg, {time : 2000, icon : 5});
                }
            }
        });

        $.post("<%=request.getContextPath()%>/server/permission/menu", {resourceType : 1},function(data){
            for(var n in data){
                $("#ckBox").append(
                    "<label><input type=\"checkbox\" name=\"ck1\" value = '"+data[n].id+"'/>"+data[n].name+"</label>"
                );
            }
            $("#ckBox").append("<br clear=\"all\"/>");
            var result = "${permissions}";
            if(result && result != 0){
                var tagslist = result.split(",");
                $("input[type='checkbox']").each(function() {
                    if($.inArray($(this).val(), tagslist) != -1){
                        $(this).attr("checked", true);
                    }
                });
            }

            $("input[type='checkbox']").on("click", function() {
                var ids = [];
                $("input[type='checkbox']:checked").each(function (i) {
                    ids.push($(this).val());
                });
                $("#permissions").val(ids);
            });
        })
    });
</script>
</body>
</html>