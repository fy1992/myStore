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
    <div class="form form-horizontal" id="form-supplier-add">
        <div class="row cl mb-30">
            <div class="row cl">
                <label class="form-label col-3">是否启用：</label>
                <div class="formControls col-6">
                    <div class="radio-box">
                        <input type="radio" id="supplier_using-1" name="status" value = "1" checked>
                        <label for="supplier_using-1">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="supplier_using-2" name="status" value = "0">
                        <label for="supplier_using-2">否</label>
                    </div>
                </div>
                <div class="col-3"></div>
            </div>
            <div class="row cl">
                <label class="form-label col-3"><span class="c-red">* </span>门店编号：</label>
                <div class="formControls col-6">
                    <input type="text" class="input-text radius" id="supplier_no" name = "supplierNo" value="">
                </div>
                <div class="col-3">
                    <input type="button" class="btn btn-primary size-S radius" value="随机生成" id="randomNo"/>
                    <span style="display: none;" id="help_tooltip" data-toggle="tooltip" data-placement="bottom" title="编号是供货商信息的唯一标识，由不超过6位的数字、字母、下划线组成。
                        连锁店之间相同的编号，系统识别为同一个供货商。">
                        <i class="Hui-iconfont" style="cursor: pointer">&#xe633;</i>
                    </span>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3"><span class="c-red">* </span>供货商名称：</label>
                <div class="formControls col-6">
                    <input type="text" class="input-text radius" value=""  id="supplier_name" name = "name">
                </div>
                <div class="col-3"> </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3">搜索拼音码：</label>
                <div class="formControls col-6">
                    <input type="text" class="input-text radius" value=""  id="supplier_pinyin" name = "pinyin">
                </div>
                <div class="col-3"> </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3">联系人：</label>
                <div class="formControls col-6"><input type = "text" class="input-text radius" name = "contacts" id = "supplier_contacts"/></div>
                <div class="col-3"> </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3">联系电话：</label>
                <div class="formControls col-6"><input type = "text" class="input-text radius" id="supplier_phone" name = "phone"/></div>
                <div class="col-3"> </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3">联系邮箱：</label>
                <div class="formControls col-6"><input type = "text" class="input-text radius" id="supplier_email" name = "email"/></div>
                <div class="col-3"> </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3">配送费返点：</label>
                <div class="formControls col-6">
                    <input type = "text"  class="input-text radius mr-5" id="supplier_packingFeePoint" name="packingFeePoint" style="width: 90%;"/><label>&nbsp;%</label>
                </div>
                <div class="col-3"> </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3">固定返利点：</label>
                <div class="formControls col-6">
                    <input type = "text"  class="input-text radius mr-5"  id="supplier_rebatePoint" name="rebatePoint" style="width: 90%;"/><label>&nbsp;%</label>
                </div>
                <div class="col-3"> </div>
            </div>
        </div>
        <div class="row cl">
        	<div class="col-8">
	            <label class="form-label col-3">地址：</label>
	            <div class="formControls col-9">
	                <textarea rows="2" maxlength="200" class="edit_txt textarea radius" id="supplier_addr" name = "addr"></textarea>
	            </div>
	        </div>
        </div>
        <div class="row cl">
            <div class="col-8">
                <label class="form-label col-3">备注：</label>
                <div class="formControls col-9">
                    <textarea rows="2" maxlength="200" class="edit_txt textarea radius" id="supplier_description" name = "description"></textarea>
                </div>
            </div>
        </div>
        <div class="row cl" style="display: none;">
            <div class="row cl">
                <label class="form-label col-3">是否授权供货商：</label>
                <div class="formControls col-6">
                    <div class="radio-box">
                        <input type="radio" id="supplier_isAuthorize-1" name="authorize" value = "1" checked>
                        <label for="supplier_isAuthorize-1">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="supplier_isAuthorize-2" name="authorize" value = "0">
                        <label for="supplier_isAuthorize-2">否</label>
                    </div>
                </div>
                <div class="col-3"></div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-10 col-offset-5 mt-20">
                <input class="btn btn-primary radius" type="button" id="supplierAddBtn" value="&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;">
            </div>
        </div>
    </div>
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
    var validtor;
    $(function () {
        validtor = $("#form-supplier-add").Validform({
            btnSumbit : "#supplierAddBtn",
            tiptype:2,
            tipSweep:true,

        });
        $("[data-toggle='tooltip']").tooltip();
    });
    validtor.addRule([
        {
            ele : "#pinyin",
            datatype : "s"
        },
        {
            ele : "#supplier_no",
            datatype : "n",
            nullmsg : "供货商编号必填"
        },
        {
            ele : "#supplier_name",
            datatype : "s",
            nullmsg : "供货商姓名必填"
        },
        {
            ele : "#supplier_email",
            datatype : "e",
            errormsg : "请填写正确的邮箱"
        },
        {
            ele : "#supplier_phone",
            datatype : "m",
            errormsg : "请填写正确的手机号"
        },
        {
            ele : "#supplier_rebatePoint",
            datatype : "n",
            errormsg : "请填写正确的数值"
        },
        {
            ele : "#supplier_packingFeePoint",
            datatype : "n",
            errormsg : "请填写正确的数值"
        }
    ]);

    //随机生成编号
    $("#randomNo").on("click", function () {
        $.post("<%=request.getContextPath()%>/supplier/newSupplierNo", function(data){
            if(data.result == 1){
                $("#supplier_no").removeClass("Validform_error");
                $("#supplier_no").val(data.msg);
                $("#randomNo").css("display", "none");
                $("#help_tooltip").show();
            }
        });
    });

    $("#supplierAddBtn").on("click", function (){
        var supplier = new Object();
        supplier.name = $("#supplier_name").val();
        supplier.supplierNo = $("#supplier_No").val();
        supplier.description = $("#supplier_desc").val();
        supplier.contacts = $("#supplier_contacts").val();
        supplier.phone = $("#supplier_phone").val();
        supplier.email = $("#supplier_email").val();
        supplier.rebatePoint = $("#supplier_rebatePoint").val();
        supplier.packingFeePoint = $("#supplier_packingFeePoint").val();
        supplier.authorize = $("input[name='supplier_isAuthorize']:checked").val();
        supplier.status = $("input[name='supplier_using']:checked").val();
        supplier.pinyin = $("#supplier_pinyin").val();
        supplier.addr = $("#supplier_addr").val();
        alert(JSON.stringify(supplier));
        $.ajax({
            url : "<%=request.getContextPath()%>/supplier/add",
            type : "post",
            dataType : "json",
            data : JSON.stringify(supplier),
            success : function (data) {
                layer.msg(data.msg);
                parent.table.fnDraw();
                layer_close();
            }
        });
    });
</script>
</body>
</html>