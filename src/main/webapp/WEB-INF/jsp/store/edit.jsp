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
    <link href="${ctxResource}/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <title></title>
</head>
<body>
<div class="pd-20 minwidth">
    <form class="form form-horizontal" id="form-store-edit" action="<%=request.getContextPath()%>/server/store/edit" method="POST">
        <b>账号信息</b>
        <div class="row cl">
            <label class="form-label col-3">是否启用：</label>
            <div class="formControls col-6">
                <div class="radio-box">
                    <input type="radio" id="store_using-1" name="status" value = "1" <c:if test="${store.status eq 1}">checked</c:if>>
                    <label for="store_using-1">是</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="store_using-2" name="status" value = "0" <c:if test="${store.status eq 0}">checked</c:if>>
                    <label for="store_using-2">否</label>
                </div>
            </div>
            <div class="col-3"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">账号名：</label>
            <div class="formControls col-6">
                <span>${user.loginName}</span>
            </div>
            <div class="col-3"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">手机：</label>
            <div class="formControls col-6"><input type = "text" class="input-text radius" id="phone" name = "mobile" value="${user.mobile}"/></div>
            <div class="col-3"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">邮箱：</label>
            <div class="formControls col-6"><input type = "text" class="input-text radius" id="email" name = "email" value = "${user.email}"/></div>
            <div class="col-3"> </div>
        </div>
        <div class=""><hr/></div>
        <b>店面信息</b>
        <div class="row cl">
            <label class="form-label col-3">门店编号：</label>
            <div class="formControls col-6">
                <span>${store.storeNo}</span>
                <input type="hidden" value = "${store.id}" name = "id">
                <input type="hidden" value = "${store.storeNo}" name = "storeNo">
            </div>
            <div class="col-3">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>门店名称：</label>
            <div class="formControls col-6">
                <input type="text" class="input-text radius" value="${store.name}"  id="store_name" name = "name">
            </div>
            <div class="col-3"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">联系人：</label>
            <div class="formControls col-6"><input type = "text" class="input-text radius" name = "contact" id = "store_contacts" value="${store.contact}"/></div>
            <div class="col-3"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">联系电话：</label>
            <div class="formControls col-6"><input type = "text" class="input-text radius" id="store_phone" name = "phone" value="${store.phone}"/></div>
            <div class="col-3"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">营业时间：</label>
            <div class="formControls col-6"><input type = "text" class="input-text radius" id="store_workTime" name = "workTime" value="${store.workTime}"/></div>
            <div class="col-3"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">所属行业：</label>
            <div class="formControls col-6">
                <span class="select-box radius">
                    <select id="industry" name = "industry" class="select">
                        <option value = "0">- 餐饮行业 -</option>
                    </select>
                </span>
            </div>
            <div class="col-3"> </div>
        </div>
        <c:if test="${user.rank eq 0}">
            <div class="row cl">
                <label class="form-label col-3">是否开启分店：</label>
                <div class="formControls col-6">
                    <div class="radio-box">
                        <input type="radio" id="multiple-1" name="multiple" value = "0" <c:if test="${store.multiple eq 0}">checked</c:if>>
                        <label for="store_using-1">否</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="multiple-2" name="multiple" value = "1" <c:if test="${store.multiple eq 1}">checked</c:if>>
                        <label for="store_using-2">是</label>
                    </div>
                </div>
                <div class="col-3"></div>
            </div>
        </c:if>
        <div class="row cl">
            <label class="form-label col-3">地址：</label>
            <div class="formControls col-6">
                <textarea rows="2" maxlength="200" class="edit_txt textarea radius" id="store_addr" name = "addr" value="${store.addr}">${store.addr}</textarea>
            </div>
            <div class="col-3"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">备注：</label>
            <div class="formControls col-6">
                <textarea rows="2" maxlength="200" class="edit_txt textarea radius" id="store_description" name = "description" value = "${store.description}">${store.description}</textarea>
            </div>
            <div class="col-3"> </div>
        </div>
        <div class="row cl">
            <div class="col-10 col-offset-5 mt-20">
                <input class="btn btn-primary radius" type="submit" id="storeEditBtn" value="&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;">
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
<script type="text/javascript" src="${ctxResource}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/Validform_v5.3.2_min.js"></script>
<script>
    $(function () {
        var  validtor = $("#form-store-edit").Validform({
            tiptype:4,
            showAllError:true,
            ajaxPost: true,
            ignoreHidden:true, //可选项 true | false 默认为false，当为true时对:hidden的表单元素将不做验证;
            tipSweep:true,//可选项 true | false 默认为false，只在表单提交时触发检测，blur事件将不会触发检测
            btnSubmit:"#storeEditBtn",
            callback:function(data){
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
                ele : "#store_name",
                datatype : "s",
                nullmsg : "门店名称必填"
            },
            {
                ele : "#store_phone",
                datatype : "m",
                errormsg : "请填写正确的手机号",
                ignore : "ignore"
            },
            {
                ele : "#phone",
                datatype : "m",
                errormsg : "请填写正确的手机号",
                ignore : "ignore"
            },
            {
                ele : "#email",
                datatype : "e",
                errormsg : "请填写正确的邮箱",
                ignore : "ignore"
            }
        ]);

        $("[data-toggle='tooltip']").tooltip();
    });
</script>
</body>
</html>