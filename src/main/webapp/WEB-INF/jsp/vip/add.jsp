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
    <form class="form form-horizontal" id="form-vip-add" action = "<%=request.getContextPath()%>/server/vip/add" type = "post">
        <div class="row cl">
            <label class="form-label col-3">是否启用：</label>
            <div class="formControls col-7">
                <div class="radio-box">
                    <input type="radio" id="vip_using-1" name="status" value = "1" checked>
                    <label for="vip_using-1">是</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="vip_using-2" name="status" value = "0">
                    <label for="vip_using-2">否</label>
                </div>
            </div>
            <div class="col-2"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>会员编号：</label>
            <div class="formControls col-7">
                <input value = "" name = "vipNo" id="vip_no" class="input-text radius" type="text"/>
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
                <input type="text" class="input-text radius" value=""  id="vip_name" name = "vipName">
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>会员等级：</label>
            <div class="formControls col-7">
                <div class="formControls col-5">
                    <span class="select-box">
                        <select id="vipLevelID" name = "vipLevelID" class="select">
                            <option value selected>- 会员等级 -</option>
                        </select>
                    </span>
                </div>
                <div class="formControls col-7">
                    <label class="form-label col-5">会员折扣：</label>
                    <div class="formControls col-7">
                        <input type="number" min="0" class="input-text radius text-r" style="width: 87%" value="" id="vip_rebate" name = "rebate"><span>%</span>
                    </div>
                </div>
            </div>
            <div class="col-2"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>会员余额：</label>
            <div class="formControls col-7">
                <div class="formControls col-5">
                    <input type="text" class="input-text radius text-r" value="0"  id="vip_balance" name = "balance" style="width: 90%"><span>元</span>
                </div>
                <div class="formControls col-7">
                    <label class="form-label col-5">会员积分：</label>
                    <div class="formControls col-7">
                        <input type="number" min="0" class="input-text radius text-r" value="0"  id="vip_point" style="width: 87%" name = "point"><span>分</span>
                    </div>
                </div>
            </div>
            <div class="col-2"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>联系电话：</label>
            <div class="formControls col-7"><input type = "text" class="input-text radius" id="vip_phone" name = "phone" value = ""/></div>
            <div class="col-2"> </div>
        </div>
        <div class=""><hr/></div>
        <div class="row cl">
            <label class="form-label col-3">会员密码：</label>
            <div class="formControls col-7">
                <input type = "password" class="input-text radius" id="vip_password" name = "password" value = ""/>
            </div>
            <div class="col-2"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">会员生日：</label>
            <div class="formControls col-7">
                <input type = "text"  class="input-text radius mr-5" id="vip_birthday" onfocus="WdatePicker({maxDate:'%y-%M-%d',readOnly:true,skin:'twoer'})" name="birthday" value = ""/>
            </div>
            <div class="col-2"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">开卡日期：</label>
            <div class="formControls col-7">
                <input type = "text"  class="input-text radius mr-5"  id="vip_createCardDate" onfocus="WdatePicker({maxDate:'%y-%M-%d',readOnly:true,skin:'twoer'})" name="createCardDate" value = ""/>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">到期日期：</label>
            <div class="formControls col-7">
                <input type = "text"  class="input-text radius mr-5"  id="vip_dueDate" name="dueDate" onfocus="WdatePicker({maxDate:'2099-10-01',minDate:'#F{$dp.$D(\'vip_createCardDate\'||\'%y-%M-%d\')}',readOnly:true,skin:'twoer'})" value = ""/>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">QQ号码：</label>
            <div class="formControls col-7">
                <input type = "number" style="width: 100%" class="input-text radius mr-5"  id="vip_qq" name="qq" value = ""/>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">邮箱地址：</label>
            <div class="formControls col-7">
                <input type = "text"  class="input-text radius mr-5"  id="vip_email" name="email" value = ""/>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">地址：</label>
            <div class="formControls col-7">
                <textarea rows="2" maxlength="200" class="edit_txt textarea radius" id="vip_addr" name = "addr"></textarea>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">备注：</label>
            <div class="formControls col-7">
                <textarea rows="2" maxlength="200" class="edit_txt textarea radius" id="vip_description" name = "description"></textarea>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <div class="col-10 col-offset-5 mt-20">
                <input class="btn btn-primary radius" type="button" id="vipAddBtn" value="&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctxResource}/js/myself.js"></script>
<script type="text/javascript" src="${ctxResource}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctxResource}/js/Validform_v5.3.2_min.js"></script>
<script>
    $(function () {
       $.post("<%=request.getContextPath()%>/server/vip/allVipLevel",function (data) {
           for(var n in data){
               $("#vipLevelID").append("<option value='"+data[n].id+"'>"+data[n].name+"</option>");
           }
       });
       var  validtor = $("#form-vip-add").Validform({
            tiptype:4,
            showAllError:true,
            ajaxPost:true,
            ignoreHidden:true, //可选项 true | false 默认为false，当为true时对:hidden的表单元素将不做验证;
            tipSweep:true,//可选项 true | false 默认为false，只在表单提交时触发检测，blur事件将不会触发检测
            btnSubmit:"#vipAddBtn",
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
                datatype:"n",
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
                datatype:"n",
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