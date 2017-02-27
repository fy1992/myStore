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
    <form class="form form-horizontal" id="form-cashier-add" method="post" action="<%=request.getContextPath()%>/server/employee/cashierAdd">
        <div class="row cl mb-30">
            <div class="row cl">
                <label class="form-label col-3">是否启用：</label>
                <div class="formControls col-7">
                    <div class="radio-box">
                        <input type="radio" id="cashier_using-1" name="status" value = "1" checked>
                        <label for="cashier_using-1">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="cashier_using-2" name="status" value = "0">
                        <label for="cashier_using-2">否</label>
                    </div>
                </div>
                <div class="col-2"></div>
            </div>
            <div class="row cl">
                <label class="form-label col-3"><span class="c-red">* </span>编号：</label>
                <div class="formControls col-7">
                    <input type="text" class="input-text radius" value="" name="cashierNo" id="cashier_No"/>
                </div>
                <div class="col-2"> </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3"><span class="c-red">* </span>姓名：</label>
                <div class="formControls col-7">
                    <input type="text" class="input-text radius" value="" name="cashierName" id="cashier_name"/>
                </div>
                <div class="col-2"> </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3"><span class="c-red">* </span>密码：</label>
                <div class="formControls col-7">
                    <input type="text" class="input-text radius" value="" name="password" id="cashier_password"/>
                </div>
                <div class="col-2"> </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3">电话：</label>
                <div class="formControls col-7">
                    <input type = "text" class="input-text radius" id="cashier_phone" name="mobile"/>
                </div>
                <div class="col-2"> </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3">角色：</label>
                <div class="formControls col-7">
                <span class="select-box radius">
                    <select class="select" id = "cashier_role" name = "roles">
                        <option value = "0">- 无 -</option>
                    </select>
                </span>
                </div>
                <div class="col-2"> </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">备注：</label>
            <div class="formControls col-7">
                <textarea rows="2" maxlength="200" class="edit_txt textarea radius" id="cashier_desc" name = "description"></textarea>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <div class="col-10 col-offset-5 mt-20">
                <input class="btn btn-primary radius" type="button" id="cashierAddBtn" value="&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;">
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
        $.post("<%=request.getContextPath()%>/server/role/findAll", function(data){
            for(var n in data){
                $("#cashier_role").append("<option value = '"+data[n].id+"'>"+data[n].name+"</option>");
            }
        });

        var  validtor = $("#form-cashier-add").Validform({
            tiptype:3,
            showAllError:true,
            ajaxPost: true,
            ignoreHidden:true, //可选项 true | false 默认为false，当为true时对:hidden的表单元素将不做验证;
            tipSweep:true,//可选项 true | false 默认为false，只在表单提交时触发检测，blur事件将不会触发检测
            btnSubmit:"#cashierAddBtn"
        });

        validtor.addRule([
            {
                ele:"#cashier_No",
                datatype:"n",
                errormsg:"只能填写数字",
                nullmsg:"收银员编号必填"
            },
            {
                ele:"#cashier_name",
                datatype:"*",
                nullmsg:"收银员姓名必填"
            },
            {
                ele:"#cashier_password",
                datatype:"*",
                nullmsg:"密码必填"
            }
        ]);
    })
</script>
</body>
</html>