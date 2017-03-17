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
    <form class="form form-horizontal" id="form-vipLevel-add" action = "<%=request.getContextPath()%>/server/vip/vipLevelAdd" type = "post">
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>促销类型：</label>
            <div class="formControls col-7">
                <input value = "" name = "name" id="name" class="input-text radius" type="text"/>
            </div>
            <div class="col-2"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>促销名称：</label>
            <div class="formControls col-7">
                <input type="text" class="input-text radius" value=""  id="rebate" name = "rebate">
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">适用范围：</label>
            <div class="formControls col-7">
                <input type = "checkbox" name = "scope" value = "0" id = "scope1"> <label for="scope1">实体店</label> |
                <input type = "checkbox" name = "scope" value = "1" id = "scope2"> <label for="scope2">网店</label>
                <input type="hidden" name = "appScope" id = "appScope"/>
            </div>
            <div class="col-2"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>开始日期：</label>
            <div class="formControls col-7">
                <div class="formControls col-5">
                    <input type="text" class="input-text radius text-r" value="0"  id="vip_startDate" name = "startDate" style="width: 90%">
                </div>
                <div class="formControls col-7">
                    <label class="form-label col-5">结束日期：</label>
                    <div class="formControls col-7">
                        <input type="text" class="input-text radius text-r" value="0"  id="vip_endDate" style="width: 87%" name = "endDate">
                    </div>
                </div>
            </div>
            <div class="col-2"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-7">优惠劵验证（是否需要优惠劵才可享受此优惠）：</label>
            <div class="formControls col-3">
                <div class="radio-box">
                    <input type="radio" id="level_using-1" name="coupon" value = "1">
                    <label for="level_using-1">是</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="level_using-2" name="coupon" value = "0" checked>
                    <label for="level_using-2">否</label>
                </div>
            </div>
            <div class="col-2"></div>
        </div>
        <div class="row cl">
            <div class="col-10 col-offset-5 mt-20">
                <input class="btn btn-primary radius" type="button" id="vipLevelAddBtn" value="&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;">
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
            ajaxPost:{
                url : "<%=request.getContextPath()%>/server/vip/vipLevelAdd",
                success : function (data, obj) {
                    layer.msg(data);
                }
            },
            ignoreHidden:true, //可选项 true | false 默认为false，当为true时对:hidden的表单元素将不做验证;
            tipSweep:true,//可选项 true | false 默认为false，只在表单提交时触发检测，blur事件将不会触发检测
            btnSubmit:"#vipLevelAddBtn",
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
                ele:"#name",
                datatype:"*",
                nullmsg :"会员姓名必填项"
            },
            {
                ele:"#rebate",
                datatype:"n",
                nullmsg :"会员编号必填"
            }
        ]);
    });
</script>
</body>
</html>