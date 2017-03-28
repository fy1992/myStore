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
<div class="pd-20 minwidth" style="width:780px;">
    <form class="form form-horizontal" id="form-vipSys-add" action = "<%=request.getContextPath()%>/server/vip/add" type = "post">
        <div class="row cl">
            <div class="col-3"></div>
            <label class="form-label col-6"><span class="col-title">会员卡背景图片</span><span class="PS">（将用在微信店铺的电子会员卡上）</span></label>
            <div class="col-3"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-4" style="background-color: #eeeeee;">
                <img src="" id="imgUrlBox"/>
                <input type="hidden" name = "imgUrl" id="imgUrl"/>
            </label>
            <div class="form-label col-8">
                <div class="row cl">
                    * 图片格式支持：jpg,jpeg,png<br/>
                    * 大小不超过200k，最佳尺寸640 * 360
                </div>
                <div>
                    <input type = "text" class="btnShowEditImages" name = "goodsImg" id = "goodsImg">
                </div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-3"></div>
            <label class="form-label col-6"><span class="col-title">微店新会员</span><span class="PS">（通过微信店铺注册的新会员）</span></label>
            <div class="col-3"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">赠送积分</label>
            <div class="formControls col-6">
                <input value = "1000" name = "wdPoint" id="wdPoint" class="input-text radius" type="text"/>
            </div>
            <div class="col-3">分</div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">赠优惠券</label>
            <div class="formControls col-6">
                <span class="select-box radius">
                    <select id="wdSalesCampaignId" name = "wdSalesCampaignId" class="select">
                        <option value="0">不赠送优惠券</option>
                    </select>
                </span>
            </div>
            <div class="col-3"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">默认等级</label>
            <div class="formControls col-6">
                <span class="select-box radius">
                    <select id="defaultVipLevelID" name = "defaultVipLevelID" class="select">
                        <option value="0">无</option>
                    </select>
                    <input type="hidden" name = "defaultVipLevelName" id="defaultVipLevelName"/>
                </span>
            </div>
            <div class="col-3"></div>
        </div>
        <div class="row cl">
            <div class="col-3"></div>
            <label class="form-label col-6"><span class="col-title">转介绍奖励</span><span class="PS">（通过微店会员中心分享注册链接）</span></label>
            <div class="col-3"></div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">赠送积分</label>
            <div class="formControls col-6">
                <input value = "1000" name = "zjsPoint" id="zjsPoint" class="input-text radius" type="text"/>
            </div>
            <div class="col-3">分</div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">赠优惠券</label>
            <div class="formControls col-6">
                <span class="select-box radius">
                    <select id="zjsSalesCampaignId" name = "zjsSalesCampaignId" class="select">
                        <option value="0">不赠送优惠券</option>
                    </select>
                </span>
            </div>
            <div class="col-3"></div>
        </div>
        <%--<div class="row cl">
            <label class="form-label col-6">其他设置</label>
        </div>
        <div class="row cl">
            <label class="form-label col-3">会员日</label>
            <div class="formControls col-7">
                <input value = "1000" name = "vipDate" id="vipDate" class="input-text radius" type="text"/> 分
            </div>
        </div>--%>
        <div class="row cl">
            <div class="col-10 col-offset-5 mt-20">
                <input class="btn btn-primary radius" type="button" id="vipSysBtn" value="&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctxResource}/js/myself.js"></script>
<script type="text/javascript" src="${ctxResource}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctxResource}/js/Validform_v5.3.2_min.js"></script>
<script>
    $(function () {
        $.post("<%=request.getContextPath()%>/server/salesCampaign/findSalesCampaigns",function (data) {
            for(var n in data){
                $("#zjsSalesCampaignId").append("<option value='"+data[n].id+"'>"+data[n].name+"</option>");
                $("#wdSalesCampaignId").append("<option value='"+data[n].id+"'>"+data[n].name+"</option>");
            }
        });

        $.post("<%=request.getContextPath()%>/server/vip/allVipLevel",function (data) {
            for(var n in data){
                $("#defaultVipLevelID").append("<option value='"+data[n].id+"'>"+data[n].name+"</option>");
            }
        });

        //编辑图片
        $("#btnShowEditImages").click(function(){
            layer_show("会员卡背景图片", "<%=request.getContextPath()%>/server/goods/uploadImg", "1000", "500");
        });

        var  validtor = $("#form-vipSys-add").Validform({
            tiptype:4,
            showAllError:true,
            ajaxPost:true,
            ignoreHidden:true, //可选项 true | false 默认为false，当为true时对:hidden的表单元素将不做验证;
            tipSweep:true,//可选项 true | false 默认为false，只在表单提交时触发检测，blur事件将不会触发检测
            btnSubmit:"#vipSysBtn",
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

    });
</script>
</body>
</html>