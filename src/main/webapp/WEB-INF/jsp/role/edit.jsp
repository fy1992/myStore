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
    <form class="form form-horizontal" id="form-role-edit" method="post" action="<%=request.getContextPath()%>/server/role/editRole">
        <div class="row cl">
            <label class="form-label col-3">是否启用：</label>
            <div class="formControls col-6">
                <div class="radio-box">
                    <input type="radio" id="role_using-1" name="status" value = "1" <c:if test="${role.status eq 1}">checked</c:if>>
                    <label for="role_using-1">是</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="role_using-2" name="status" value = "0" <c:if test="${role.status eq 0}">checked</c:if>>
                    <label for="role_using-2">否</label>
                </div>
            </div>
            <div class="col-3"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>角色名称：</label>
            <div class="formControls col-6">
                <input type="text" class="input-text radius" value="${role.roleName}" id="roleName" name="roleName">
                <input type = "hidden" value = "${role.id}" name = "id"/>
            </div>
            <div class="col-3"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">角色权限：</label>
            <div class="formControls col-6">
                <div class="mb-40 pd-20 clearfixs" id="ckBox">
                    <input type="hidden" name = "permissionIds" id="permissions" value/>
                    <br clear="all" />
                </div>
            </div>
            <div class="col-3"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">备注：</label>
            <div class="formControls col-6">
                <textarea rows="2" maxlength="200" class="edit_txt textarea radius" id="role_desc" name="description" value="${role.description}">${role.description}</textarea>
            </div>
            <div class="col-3"> </div>
        </div>
        <div class="row cl" style="display: none;">
            <div class="row cl">
                <label class="form-label col-3">是否同步相应收银员的权限：</label>
                <div class="formControls col-6">
                    <div class="radio-box">
                        <input type="radio" id="isAsync-1" name="isAsync" value = "1" <c:if test="${role.isAsync eq 1}">checked</c:if>>>
                        <label for="isAsync-1">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="isAsync-2" name="isAsync" value = "0" <c:if test="${role.isAsync eq 0}">checked</c:if>>>
                        <label for="isAsync-2">否</label>
                    </div>
                </div>
                <div class="col-3"></div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-10 col-offset-5 mt-20">
                <input class="btn btn-primary radius" type="button" id="roleEditBtn" value="&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctxResource}/js/myself.js"></script>
<script type="text/javascript" src="${ctxResource}/js/Validform_v5.3.2_min.js"></script>
<script>
    $(function () {
        var  validtor = $("#form-role-edit").Validform({
            tiptype:3,
            showAllError:true,
            ajaxPost: true,
            ignoreHidden:true, //可选项 true | false 默认为false，当为true时对:hidden的表单元素将不做验证;
            tipSweep:true,//可选项 true | false 默认为false，只在表单提交时触发检测，blur事件将不会触发检测
            btnSubmit:"#roleEditBtn",
            callback:function (data) {
                window.parent.table.fnDraw();
            }
        });

        validtor.addRule([
            {
                ele:"#role_name",
                datatype:"*",
                nullmsg:"角色名称必填"
            }
        ]);

        $.post("<%=request.getContextPath()%>/server/permission/menu", {resourceType : 1}, function(data){
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
    })
</script>
</body>
</html>