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
    <form class="form form-horizontal" id="form-vip-edit" action = "<%=request.getContextPath()%>/server/vip/edit" type = "post">
        <div class="row cl">
            <label class="form-label col-3">是否启用：</label>
            <div class="formControls col-7">
                <div class="radio-box">
                    <input type="radio" id="vip_using-1" name="status" value = "1" <c:if test="${vip.status eq 1}">checked</c:if>>
                    <label for="vip_using-1">是</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="vip_using-2" name="status" value = "0" <c:if test="${vip.status eq 0}">checked</c:if>>
                    <label for="vip_using-2">否</label>
                </div>
            </div>
            <div class="col-2"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>会员编号：</label>
            <div class="formControls col-7">
                <span>${vip.vipNo}</span>
                <input value = "${vip.id}" name = "id" type="hidden"/>
            </div>
            <div class="col-2">
                <span id="help_tooltip" data-toggle="tooltip" data-placement="bottom" title="会员编号是会员的唯一标识，连锁门店之间也不能重复。">
                <i class="Hui-iconfont" style="cursor: pointer">&#xe633;</i>
            </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>会员名称：</label>
            <div class="formControls col-7">
                <input type="text" class="input-text radius" value="${vip.vipName}"  id="vip_name" name = "vipName">
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>会员等级：</label>
            <div class="formControls col-7">
                <div class="formControls col-5">
                    <div class="radius">
                        <select id="vipLevelID" name = "vipLevelID" class="select" style="height: 35px;">
                            <option value = "-1">- 会员等级 -</option>
                        </select>
                    </div>
                </div>
                <div class="formControls col-7">
                    <label class="form-label col-5">会员折扣：</label>
                    <div class="formControls col-7">
                        <input type="text" class="input-text radius text-r" value="${vip.rebate}" id="vip_rebate" name = "rebate">
                    </div>
                </div>
            </div>
            <div class="col-2"> %</div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>会员余额：</label>
            <div class="formControls col-7">
                <div class="formControls col-5">
                    <input type="text" class="input-text radius text-r" value="${vip.balance}"  id="vip_balance" name = "balance">元
                </div>
                <div class="formControls col-7">
                    <label class="form-label col-5">会员积分：</label>
                    <div class="formControls col-7">
                        <input type="text" class="input-text radius text-r" value="${vip.point}"  id="vip_point" name = "point">
                    </div>
                </div>
            </div>
            <div class="col-2"> 分</div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>联系电话：</label>
            <div class="formControls col-7">
                <input type = "text" class="input-text radius" id="vip_phone" name = "phone" value = "${vip.phone}"/>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class=""><hr/></div>
        <div class="row cl">
            <label class="form-label col-3">会员密码：</label>
            <div class="formControls col-7"><input type = "password" class="input-text radius" id="vip_password" name = "password" value = "${vip.password}"/></div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">会员生日：</label>
            <div class="formControls col-7">
                <input type = "text"  class="input-text radius mr-5" id="vip_birthday" name="birthday" style="width: 90%;" value = "${vip.birthday}"/>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">开卡日期：</label>
            <div class="formControls col-7">
                <input type = "text"  class="input-text radius mr-5"  id="vip_createCardDate" name="createCardDate" style="width: 90%;" value = "${vip.createCardDate}"/>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">到期日期：</label>
            <div class="formControls col-7">
                <input type = "text"  class="input-text radius mr-5"  id="vip_dueDate" name="dueDate" style="width: 90%;" value = "${vip.dueDate}"/>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">QQ号码：</label>
            <div class="formControls col-7">
                <input type = "text"  class="input-text radius mr-5"  id="vip_qq" name="qq" style="width: 90%;" value = "${vip.qq}"/>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">邮箱地址：</label>
            <div class="formControls col-7">
                <input type = "text"  class="input-text radius mr-5"  id="vip_email" name="email" style="width: 90%;" value = "${vip.email}"/>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">地址：</label>
            <div class="formControls col-9">
                <textarea rows="2" maxlength="200" class="edit_txt textarea radius" id="vip_addr" name = "addr" value = "${vip.addr}">${vip.addr}</textarea>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">备注：</label>
            <div class="formControls col-9">
                <textarea rows="2" maxlength="200" class="edit_txt textarea radius" id="vip_description" name = "description" value = "${vip.description}">${vip.description}</textarea>
            </div>
        </div>
        <div class="row cl">
            <div class="col-10 col-offset-5 mt-20">
                <input class="btn btn-primary radius" type="button" id="vipEditBtn" value="&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;">
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
<script type="text/javascript" src="${ctxResource}/js/My97DatePicker/WdatePicker.js"></script>
<script>
    $(function () {
        $.post("<%=request.getContextPath()%>/server/vip/allVipLevel",function (data) {
            for(var n in data){
                $("#vipLevelID").append("<option value='"+data[n].id+"'>"+data[n].name+"</option>");
            }
            $("#vipLevelID").val(${vip.vipLevelID});
        });

       var  validtor = $("#form-vip-edit").Validform({
            tiptype:4,
            showAllError:true,
            ajaxPost: true,
            ignoreHidden:true, //可选项 true | false 默认为false，当为true时对:hidden的表单元素将不做验证;
            tipSweep:true,//可选项 true | false 默认为false，只在表单提交时触发检测，blur事件将不会触发检测
            btnSubmit:"#vipEditBtn",
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
                ele:"#vip_name",
                datatype:"*",
                nullmsg :"会员姓名必填项"
            },
            {
                ele:"#vip_no",
                datatype:"*",
                nullmsg :"会员编号必填"
            },
            {
                ele:"#vip_phone",
                datatype:"m",
                errormsg:"请填写正确的手机号码",
                nullmsg :"联系电话必填"
            },
            {
                ele:"#vipLevelID",
                datatype:"*",
                nullmsg:"请选择会员等级"
            },
            {
                ele:"#vip_rebate",
                datatype:"n",
                nullmsg:"会员折扣必填",
                errormsg:"请填写具体数字"
            },
            {
                ele:"#vip_balance",
                datatype : /^\d+(?:\.\d{1,2})?$/,
                nullmsg:"会员余额必填",
                errormsg:"请填写具体数字"
            },
            {
                ele:"#vip_point",
                datatype:"n",
                nullmsg:"会员积分必填",
                errormsg:"请填写具体数字"
            },
            {
                ele:"#vip_email",
                datatype:"e",
                errormsg:"请填写正确的邮箱地址",
                ignore:"ignore"
            }
        ]);
        $("[data-toggle='tooltip']").tooltip();
    });
</script>
</body>
</html>