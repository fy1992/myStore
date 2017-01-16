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
    <link href="${ctxResource}/css/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
    <title></title>
</head>
<body>
<div class="pd-20">
    <div class="form form-horizontal" id="form-goodsUnit-add">
        <div class="cl pl-20 pt-10 pb-10">
            <span style="float: left;">共 种单位</span>
            <div class="text-r">
                <input type="text" id="goods_unit_add"  style="width:200px" class="input-text"/>
                <button onclick="add()" class="btn btn-primary radius">新增</button>
            </div>
        </div>
        <div class="row cl">
            <ul class="unitDivUl" id="unitUl"></ul>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script>
$(function () {
    $.post("<%=request.getContextPath()%>/goods/goodsUnit",function(data){
        $("#unitUl").append("<li>"+data[i].name+"</li>");
    });
});

//添加
function add() {
    var unitName = $.trim($("#goods_unit_add").val());
    $.post("<%=request.getContextPath()%>/goods/goodsUnit", {"name":unitName}, function (data) {
        if(data.result == 1){
            layer.msg("单位添加成功");
        }else{
            layer.msg("该单位已存在")
        }
    });
}
</script>
</body>
</html>