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
    <form class="form form-horizontal" id="form-sales-add" method="post" action="<%=request.getContextPath()%>/server/employee/salesAdd">
        <div class="row cl mb-30">
            <div class="row cl">
                <label class="form-label col-3">是否启用：</label>
                <div class="formControls col-7">
                    <div class="radio-box">
                        <input type="radio" id="sales_using-1" name="status" value = "1" checked>
                        <label for="sales_using-1">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="sales_using-2" name="status" value = "0">
                        <label for="sales_using-2">否</label>
                    </div>
                </div>
                <div class="col-2"></div>
            </div>
            <div class="row cl">
                <label class="form-label col-3"><span class="c-red">* </span>编号：</label>
                <div class="formControls col-7">
                    <input type="text" class="input-text radius" value="" id="sales_No" name = "salesNo">
                </div>
                <div class="col-2"> </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3"><span class="c-red">* </span>姓名：</label>
                <div class="formControls col-7">
                    <input type="text" class="input-text radius" value=""  id="sales_name" name="salesName">
                </div>
                <div class="col-2"> </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3">手机：</label>
                <div class="formControls col-7"><input type = "text" class="input-text radius" id="sales_phone" name = "phone"/></div>
                <div class="col-2"> </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3">提成：</label>
                <div class="formControls col-7"><input type = "text" class="input-text radius" id="sales_percentage" name = "percentage"/></div>
                <div class="col-2"> %</div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-10 col-offset-5 mt-20">
                <input class="btn btn-primary radius" type="submit" id="salesAddBtn" value="&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;">
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
        var  validtor = $("#form-sales-add").Validform({
            tiptype:3,
            showAllError:true,
            ajaxPost: true,
            ignoreHidden:true, //可选项 true | false 默认为false，当为true时对:hidden的表单元素将不做验证;
            tipSweep:true,//可选项 true | false 默认为false，只在表单提交时触发检测，blur事件将不会触发检测
            btnSubmit:"#salesAddBtn",
            callback:function (data) {
                window.parent.table.fnDraw();
            }
        });

        validtor.addRule([
            {
                ele:"#sales_No",
                datatype:"n",
                errormsg:"只能填写数字",
                nullmsg:"导购员编号必填"
            },
            {
                ele:"#sales_name",
                datatype:"*",
                nullmsg:"导购员姓名必填"
            },
            {
                ele:"#sales_phone",
                datatype:"m",
                errormsg:"请填写正确的手机号码",
                ignore : "ignore"
            }
        ]);
    })
</script>
</body>
</html>