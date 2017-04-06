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
    <form class="form form-horizontal" id="form-goods-edit" action = "<%=request.getContextPath()%>/server/goods/editGoods" type = "post">
        <div class="row cl mb-30">
            <div class="col-8">
            	<div class="row cl">
		            <label class="form-label col-3">是否启用：</label>
		            <div class="formControls col-6">
		            	<div class="radio-box">
                            <input type="radio" id="using-1" name="status" value = "1" <c:if test="${goodsDto.status eq 1}">checked</c:if>>
				          	<label for="using-1">是</label>
				        </div>
				        <div class="radio-box">
				          	<input type="radio" id="using-2" name="status" value = "0" <c:if test="${goodsDto.status eq 0}">checked</c:if>>
				          	<label for="using-2">否</label>
				        </div>
		            </div>
		            <div class="col-3"></div>
		        </div>
                <div class="row cl">
                    <label class="form-label col-3"><span class="c-red">* </span>条码：</label>
                    <div class="formControls col-6">
                        <span>${goodsDto.goodsNo}</span>
                        <input name = "id" value = "${goodsDto.id}" type = "hidden"/>
                        <input name = "intermediary" value = "${goodsDto.intermediary}" type = "hidden"/>
                        <input name = "hasRaws" value = "${goodsDto.hasRaws}" type = "hidden"/>
                        <input name = "useRawPrice" value = "${goodsDto.useRawPrice}" type = "hidden"/>
                        <input name = "autoFinished" value = "${goodsDto.autoFinished}" type = "hidden"/>
                        <input name = "goodsNo" value = "${goodsDto.goodsNo}" type = "hidden"/>
                    </div>
                    <div class="col-3"></div>
                </div>
                <div class="row cl">
                    <label class="form-label col-3"><span class="c-red">* </span>品名：</label>
                    <div class="formControls col-6">
                        <input type="text" class="input-text radius" value="${goodsDto.name}"  id="goodsName" name = "name">
                    </div>
                    <div class="col-3"> </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-3"><span class="c-red">* </span>分类：</label>
                    <div class="formControls col-6">
                    	<span class="radius">
                        <select name = "categoriesId" class="select" id="categories">
                            <option value selected>- 请选择商品分类 -</option>
                        </select>
                        </span>
                    </div>
                    <div class="col-3"> </div>
                </div>
                <div class="row cl">
                	<label class="form-label col-3"><span class="c-red">* </span>售价：</label>
                	<div class="formControls col-6"><input type = "text" class="input-text radius text-r" name = "price" value = "${goodsDto.price}" id="price" style="width: 90%;"/>&nbsp;<label>元</label></div>
                	<div class="col-3"> </div>
                </div>
                <div class="row cl">
                	<label class="form-label col-3"><span class="c-red">* </span>进价：</label>
                	<div class="formControls col-6"><input type = "text" class="input-text radius text-r" id = "bid" name = "bid" value = "${goodsDto.bid}" style="width: 90%;"/>&nbsp;<label>元</label></div>
                	<div class="col-3"> </div>
                </div>
                <div class="row cl">
                	<label class="form-label col-3">库存：</label>
                	<div class="formControls col-6"><input type = "number" style="width: 100%;" class="input-text radius text-r" id = "stock" name = "stock" value = "${goodsDto.stock}" placeholder="请输入库存量（单位：个）"/></div>
                	<div class="col-3"> </div>
                </div>
            </div>
            <div class="col-4">
                <div class="formControls clearfix">
                </div>
                <div class="clearfix">
                	<div id="btnShowEditImages" class="defaultImage">
                    	<h1>编辑图片</h1>
	                	<img src="${goodsDto.goodsImg}" id="imgPath"/>
                        <input type = "hidden" value="${goodsDto.goodsImg}" name = "goodsImg" id = "goodsImg">
                	</div>
                </div>
            </div>
        </div>

        <div class="row cl text-l vipbox pt-20">
            <div class="col-6 cl">
                <label class="form-label f-l col-3">会员折扣：</label>
                <div class="formControls f-l col-6">
                    <div class="radio-box">
                        <input type="radio" id="vip-1" name="vipSet" value = "1" <c:if test="${goodsDto.vipSet eq 1}">checked</c:if>>
                        <label for="vip-1">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="vip-2" name="vipSet" value = "0" <c:if test="${goodsDto.vipSet eq 0}">checked</c:if>>
                        <label for="vip-2">否</label>
                    </div>
                </div>
            </div>
            <div class="col-6 cl">
                <label class="form-label col-3">会员价：</label>
                <div class="formControls col-6">
                    <input type = "text" class="input-text radius mr-5"  id = "vipPrice" name = "vipPrice" value = "${goodsDto.vipPrice}" style="width: 90%;" disabled/><label>元</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-6 cl">
                <label class="form-label col-3">批发价：</label>
                <div class="formControls col-6">
                    <input type = "text"  class="input-text radius mr-5" id="tradePrice" name = "tradePrice" value = "${goodsDto.tradePrice}" style="width: 90%;"/><label>&nbsp;元</label>
                </div>
            </div>
            <div class="col-6 cl">
                <label class="form-label col-3">主单位：</label>
                <div class="formControls col-6">
                    <span class="select-box radius">
                        <select id="mainUnit" name = "mainUnit" class="select">
                            <option value = "-1">- 请选择 -</option>
                        </select>
                    </span>
                </div>
            </div>
        </div>
        <div class="row cl">
        	<div class="col-6 cl">
        		<label class="form-label col-3">拼音码：</label>
	            <div class="formControls col-6">
	                <input type="text" name="pinyin" class="input-text radius" placeholder="请输入拼音码" value = "${goodsDto.pinyin}"/>
	            </div>
	            <div class="col-3"></div>
        	</div>
        	<div class="col-6 cl">
        		<label class="form-label col-3">供货商：</label>
	            <div class="formControls col-6">
	            	<span class="select-box radius">
		                <select class="select" id = "supplier" name = "supplierId">
		                    <option value = "0">- 请选择供货商 -</option>
		                </select>
	                </span>
	            </div>
	            <div class="col-3"> </div>
        	</div>
        </div>
        <div class="row cl">
            <div class="col-6">
                <label class="form-label col-3">生产日期：</label>
                <div class="formControls col-6">
                    <input type="text" onfocus="WdatePicker({maxDate:'%y-%M-%d',readOnly:true,skin:'twoer'})" id="productionDate" name="productionDate" value = "${goodsDto.productionDate}" class="input-text Wdate radius" placeholder="请选择生产日期"/>
                </div>
                <div class="col-3"> </div>
            </div>
            <div class="col-6">
                <label class="form-label col-3">保质期：</label>
                <div class="formControls col-6">
                    <input type="number" min="0" style="width: 90%;" class="input-text radius mr-6" id="ExpirationDate" name = "shelfLife" value = "${goodsDto.shelfLife}" /><label>&nbsp;天</label>
                </div>
                <div class="col-3"> </div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-6">
                <label class="form-label col-3">库存上限：</label>
                <div class="formControls col-6">
                    <input type="number" min="0" style="width: 100%;"  class="input-text radius" id="stockUp" name = "stockUp" value = "${goodsDto.stockUp}"/>
                </div>
                <div class="col-3"> </div>
            </div>
            <div class="col-6">
                <label class="form-label col-3">库存下限：</label>
                <div class="formControls col-6">
                    <input type="number" min="0" style="width: 100%;" class="input-text radius" id="stockDown" name = "stockDown"  value = "${goodsDto.stockDown}"/>
                </div>
                <div class="col-3"> </div>
            </div>
        </div>
        <div class="row cl">
        	<div class="col-6">
	            <label class="form-label col-3">厨房票打：</label>
	            <div class="formControls col-5">
	            	已选中 <label class="c-red" id = "smallTicketNum">0</label>个<a id="cardType" class="btn btn-default radius size-S ml-15">去选择</a>
	            </div>
	            <div class="col-3"> </div>
	        </div>
	        <div class="col-6">
	            <label class="form-label col-3">商品标签：</label>
	            <div class="formControls col-5">
	            	<a class="btn btn-default radius size-S" id="labelChange">+ 选择标签</a>
	            	<div class="labelBox mt-10" id="showGoodsTags">
	            	</div>
	            </div>
	            <div class="col-3"> </div>
	        </div>
        </div>
        <div class="row cl">
        	<div class="col-8">
	            <label class="form-label col-3">备注：</label>
	            <div class="formControls col-9">
	                <textarea rows="2" maxlength="200" class="edit_txt textarea radius" id="goodsDescription" name = "description"  value = "${goodsDto.description}">${goodsDto.description}</textarea>
	            </div>
	        </div>
        </div>
        <div class="row cl">
            <div class="col-10 col-offset-5 mt-20">
                <input class="btn btn-primary radius" type="submit" id="goodsEditBtn" value="&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;">
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
<script type="text/javascript" src="${ctxResource}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctxResource}/js/Validform_v5.3.2_min.js"></script>
<script>
$(function(){
    //随机生成编号
    $("#randomNo").on("click", function () {
        $.post("<%=request.getContextPath()%>/server/goods/newGoodsNo", function(data){
            if(data.result == 1){
                $("#goodsNo").val(data.msg);
                $("#goodsNo").removeClass("Validform_error");
                $("#goodsNo").parent().find("span").removeClass("Validform_error").addClass("Validform_right").text("通过消息验证！").css("color", "black");
                $("#randomNo").css("display", "none");
                $("#help_tooltip").show();
            }
        });
    });
    if(${goodsDto.smallTickets ne "0"}){
        var sts = "${goodsDto.smallTickets}";
        $("#smallTicketNum").text("${goodsDto.smallTickets}".split(",").length);
        $("#smallTicketNum").append("<input type = 'hidden' value = '"+sts+"' id = 'stsIds' name = 'smallTickets'>");
    }

    if(${goodsDto.goodsTagss ne "0"}){
        var arr = "${goodsDto.goodsTagsName}".split(",");
        var gts = "${goodsDto.goodsTagss}";
        arr.forEach(function (result) {
            $("#showGoodsTags").append(
                "<a class=\"btn btn-primary size-MINI mr-5\">"+result+"</a>"
            );
        });
        $("#showGoodsTags").append(
            "<input type = 'hidden' value = '"+gts+"' id = 'tagsIds' name ='goodsTagss'/>"
        );
    }

    var  validtor = $("#form-goods-edit").Validform({
        tiptype:4,
        showAllError:true,
        ajaxPost: true,
        ignoreHidden:true, //可选项 true | false 默认为false，当为true时对:hidden的表单元素将不做验证;
        tipSweep:true,//可选项 true | false 默认为false，只在表单提交时触发检测，blur事件将不会触发检测
        btnSubmit:"#goodsEdiBtn",
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
            ele:"#goodsNo",
            datatype:"n",
            errormsg:"只能填写数字",
            nullmsg:"商品条码必填"
        },
        {
            ele:"#goodsName",
            datatype:"s",
            nullmsg:"商品名称必填"
        },
        {
            ele:"#categories",
            datatype:"*",
            nullmsg:"请选择商品分类"
        },
        {
            ele:"#price",
            datatype : /^\d+(?:\.\d{1,2})?$/,
            errormsg:"请填写正确的数值",
            nullmsg:"销售价必填"
        },
        {
            ele:"#bid",
            datatype:/^\d+(?:\.\d{1,2})?$/,
            errormsg:"请填写正确的数值",
            nullmsg:"进货价必填"
        }
    ]);

	$("#cardType").click(function(){
	    var stsIds = $("#stsIds").val();
	    if(!stsIds){
            stsIds = "${goodsDto.smallTickets}";
        }
	    var url = "<%=request.getContextPath()%>/server/goods/smallTicketSelect";
	    if(stsIds){
            url += "?stsIds="+ stsIds;
        }
        layer_show("厨房票打设置", url, "550", "400");
	});
	
	$("#labelChange").click(function(){
	    var url = "<%=request.getContextPath()%>/server/goods/goodsTagsSelect";
        var tagsIds = $("#tagsIds").val();
        $(".tagsIds").each(function(){
            if($(this).val()){
                tagsIds.push($(this).val());
            }
        });
        if(!tagsIds || tagsIds.length == 0){
            tagsIds = "${goodsDto.goodsTagss}";
        }
        url += "?tagsIds=" + tagsIds.toString();
        layer_show("选择标签", url, "550", "400");
	});

	//编辑图片
	$("#btnShowEditImages").click(function(){
        layer_show("商品图片", "<%=request.getContextPath()%>/server/goods/uploadImg", "1000", "500");
	});

    //分类
    $.post("<%=request.getContextPath()%>/server/categories/categoriesList", function(data){
        for(var n in data){
            $("#categories").append("<option value = "+data[n].id+">"+data[n].name+"</option>");
        }
        $("#categories").val(${goodsDto.categoriesId});
    });
    //供应商
    $.post("<%=request.getContextPath()%>/server/supplier/allSupplier", function(data){
        for(var n in data){
            $("#supplier").append("<option value = "+data[n].id+">"+data[n].name+"</option>");
        }
        $("#supplier").val(${goodsDto.supplierId});
    });

    //单位
    $.post("<%=request.getContextPath()%>/server/goods/getAllGoodsUnit", function(data){
        for(var n in data){
            $("#mainUnit").append("<option value = "+data[n].id+">"+data[n].name+"</option>");
        }
        $("#mainUnit").val(${goodsDto.mainUnit});
    });

    if($("input[name='vipSet']:checked").val() == 0){
        $("#vipPrice").attr("disabled", false);
    }

    $("input[name='vipSet']").on("click", function(){
        $("#vipPrice").attr("disabled", $(this).val() != 0);
    })
})
</script>
</body>
</html>