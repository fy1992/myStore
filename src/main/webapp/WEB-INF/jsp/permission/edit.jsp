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
    <form class="form form-horizontal" id="form-permission-edit" action="<%=request.getContextPath()%>/server/permission/edit" method="post">
        <div class="row cl">
            <label class="form-label col-3">权限类型：</label>
            <div class="formControls col-6">
                <input type="radio" value="0" name="type" id="type-1" <c:if test="${permission.type eq 0}">checked</c:if>/> <label for="type-1">服务端</label>&nbsp;
                <input type="radio" value="1" name="type" id="type-2" <c:if test="${permission.type eq 1}">checked</c:if>/> <label for="type-2">客户端</label>
            </div>
            <div class="col-3"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>权限名称：</label>
            <div class="formControls col-6">
                <input type="text" class="input-text radius" value="${permission.name}" id="permission_name" name="name">
                <input type="hidden" class="input-text radius" value="${permission.id}"  name="id">
            </div>
            <div class="col-3"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>权限url：</label>
            <div class="formControls col-6">
                <input type="text" class="input-text radius" value="${permission.url}" name="url" id="permission_url">
            </div>
            <div class="col-3"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>授权key：</label>
            <div class="formControls col-6">
                <input type="text" class="input-text radius" name="perKey" id="perKey" value="${permission.perKey}">
            </div>
            <div class="col-3"> </div>
        </div>
        <c:if test="${permission.resourceType eq 1}">
        <div class="row cl">
            <label class="form-label col-3">所属目录：</label>
            <div class="formControls col-6">
                <span class="select-box radius">
                    <select id="parent" name = "parentId" class="select">
                        <option value = "0">- 请选择所属目录 -</option>
                    </select>
                </span>
            </div>
            <div class="col-3"> </div>
        </div>
        </c:if>
        <div class="row cl">
            <label class="form-label col-3">备注：</label>
            <div class="formControls col-6">
                <textarea rows="2" maxlength="200" class="edit_txt textarea radius" id="description" name = "description" value="${permission.description}">${permission.description}</textarea>
            </div>
            <div class="col-3"> </div>
        </div>
        <div class="row cl">
            <div class="col-10 col-offset-5 mt-20">
                <input class="btn btn-primary radius" type="submit" id="permissionEditBtn" value="&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;">
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
        initParent(0);
        $("input[name='type']").on("click", function () {
            initParent($(this).val());
        });

        var  validtor = $("#form-permission-edit").Validform({
            tiptype:4,
            showAllError:true,
            ajaxPost: true,
            ignoreHidden:true, //可选项 true | false 默认为false，当为true时对:hidden的表单元素将不做验证;
            tipSweep:true,//可选项 true | false 默认为false，只在表单提交时触发检测，blur事件将不会触发检测
            btnSubmit:"#permissionEditBtn",
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

        validtor.addRule([
            {
                ele:"#permission_name",
                datatype:"*",
                nullmsg:"权限名称必填"
            },
            {
                ele:"#permission_url",
                datatype:"*",
                nullmsg:"权限url必填"
            },
            {
                ele:"#perKey",
                datatype:"*",
                nullmsg:"授权key必填"
            }
        ]);
    })


    function initParent(type){
        $.post("<%=request.getContextPath()%>/server/permission/menu", {resourceType : 0}, function (data) {
            $("#parent").empty();
            for(var n in data){
                $("#parent").append("<option value = '"+data[n].id+"'>"+data[n].name+"</option>");
            }
            $("#parent").val(${permission.parentId});
        });
    }
</script>
</body>
</html>