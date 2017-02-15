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
    <div class="form form-horizontal" id="form-goods-add">
        <div class="row cl mb-30">
            <div class="col-8">
            	<div class="row cl">
		            <label class="form-label col-3">是否启用：</label>
		            <div class="formControls col-6">
		            	<div class="radio-box">
				          	<input type="radio" id="using-1" name="using" value = "1" checked>
				          	<label for="using-1">是</label>
				        </div>
				        <div class="radio-box">
				          	<input type="radio" id="using-2" name="using" value = "2">
				          	<label for="using-2">否</label>
				        </div>
		            </div>
		            <div class="col-3"></div>
		        </div>
                <div class="row cl">
                    <label class="form-label col-3"><span class="c-red">* </span>条码：</label>
                    <div class="formControls col-6">
                        <input type="text" class="input-text radius" value="" id="goodsNo" placeholder="请输入条码" >
                    </div>
                    <div class="col-3"> </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-3"><span class="c-red">* </span>品名：</label>
                    <div class="formControls col-6">
                        <input type="text" class="input-text radius" value=""  id="goodsName" placeholder="请输入品名" >
                    </div>
                    <div class="col-3"> </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-3"><span class="c-red">* </span>分类：</label>
                    <div class="formControls col-6">
                    	<span class="select-box radius">
                        <select id="categories"  class="select">
                            <option value = "-1">- 请选择商品分类 -</option>
                        </select>
                        </span>
                    </div>
                    <div class="col-3"> </div>
                </div>
                <div class="row cl">
                	<label class="form-label col-3"><span class="c-red">* </span>售价：</label>
                	<div class="formControls col-6"><input type = "text" class="input-text radius" name = "price" id="price" style="width: 90%;"/>&nbsp;<label>元</label></div>
                	<div class="col-3"> </div>
                </div>
                <div class="row cl">
                	<label class="form-label col-3"><span class="c-red">* </span>进价：</label>
                	<div class="formControls col-6"><input type = "text" class="input-text radius" id = "bid" style="width: 90%;"/>&nbsp;<label>元</label></div>
                	<div class="col-3"> </div>
                </div>
                <div class="row cl">
                	<label class="form-label col-3">库存：</label>
                	<div class="formControls col-6"><input type = "text" class="input-text radius text-r" value="0" id = "stock" placeholder="请输入库存量（单位：个）"/></div>
                	<div class="col-3"> </div>
                </div>
            </div>
            <div class="col-4">
                <div class="formControls clearfix">
                </div>
                <div class="clearfix">
                	<div id="btnShowEditImages" class="defaultImage">
                    	<h1>编辑图片</h1>
	                	<img src="" id="imgPath"/>
                	</div>
                </div>
            </div>
        </div>

        <div class="row cl text-l vipbox pt-20">
            <div class="col-6 cl">
                <label class="form-label f-l col-3">会员折扣：</label>
                <div class="formControls f-l col-6">
                    <div class="radio-box">
                        <input type="radio" id="vip-1" name="vip" value = "1">
                        <label for="vip-1">是</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="vip-2" name="vip" value = "0" checked>
                        <label for="vip-2">否</label>
                    </div>
                </div>
            </div>
            <div class="col-6 cl">
                <label class="form-label col-3">会员价：</label>
                <div class="formControls col-6">
                    <input type = "text" class="input-text radius mr-5"  id = "vipPrice" style="width: 90%;" disabled/><label>元</label>
                </div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-6 cl">
                <label class="form-label col-3">批发价：</label>
                <div class="formControls col-6">
                    <input type = "text"  class="input-text radius mr-5" id="tradePrice" style="width: 90%;"/><label>&nbsp;元</label>
                </div>
            </div>
            <div class="col-6 cl">
                <label class="form-label col-3">主单位：</label>
                <div class="formControls col-6">
                    <span class="select-box radius">
                        <select id="mainUnit"  class="select">
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
	                <input type="text" name="pinyin" class="input-text radius" placeholder="请输入拼音码"/>
	            </div>
	            <div class="col-3"></div>
        	</div>
        	<div class="col-6 cl">
        		<label class="form-label col-3">供货商：</label>
	            <div class="formControls col-6">
	            	<span class="select-box radius">
		                <select class="select" id = "supplier">
		                    <option>- 请选择供货商 -</option>
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
                    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'productionDate\')||\'%y-%M-%d\'}'})" id="productionDate" class="input-text Wdate radius" placeholder="请选择生产日期"/>
                </div>
                <div class="col-3"> </div>
            </div>
            <div class="col-6">
                <label class="form-label col-3">保质期：</label>
                <div class="formControls col-6">
                    <input type="text" class="input-text radius mr-6" id="ExpirationDate" placeholder="请输入保质期"/><label>&nbsp;天</label>
                </div>
                <div class="col-3"> </div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-6">
                <label class="form-label col-3">库存上限：</label>
                <div class="formControls col-6">
                    <input type="text"  class="input-text radius" id="stockUp" placeholder="请输入库存上限"/>
                </div>
                <div class="col-3"> </div>
            </div>
            <div class="col-6">
                <label class="form-label col-3">库存下限：</label>
                <div class="formControls col-6">
                    <input type="text"  class="input-text radius" id="stockDown" placeholder="请输入库存下限"/>
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
	                <textarea rows="2" maxlength="200" class="edit_txt textarea radius" id="goodsDescription" placeholder="在这里写上您的备注"></textarea>
	            </div>
	        </div>
        </div>
        <div class="row cl">
            <div class="col-10 col-offset-5 mt-20">
                <input class="btn btn-primary radius" type="button" id="userAddBtn" value="&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;">
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
<script type="text/javascript" src="${ctxResource}/js/My97DatePicker/WdatePicker.js"></script>
<script>
$(function(){
	$("#cardType").click(function(){
	    var stsIds = $("#stsIds").val();
	    var url = "<%=request.getContextPath()%>/goods/smallTicketSelect";
	    if(stsIds){
            url += "?stsIds="+ stsIds;
        }
        layer_show("厨房票打设置", url, "550", "400");
	});
	
	$("#labelChange").click(function(){
	    var url = "<%=request.getContextPath()%>/goods/goodsTagsSelect";
        var tagsIds = [];
        $(".tagsIds").each(function(){
            if($(this).val()){
                tagsIds.push($(this).val());
            }
        });
        if(tagsIds.length > 0){
            url += "?tagsIds=" + tagsIds.toString();
        }
        layer_show("选择标签", url, "550", "400");
	});

	//编辑图片
	$("#btnShowEditImages").click(function(){
        layer_show("商品图片", "<%=request.getContextPath()%>/goods/uploadImg", "1000", "500");
	});

	$(function(){
	    //分类
	    $.post("<%=request.getContextPath()%>/categories/categoriesList", function(data){
            for(var n in data){
                $("#categories").append("<option value = "+data[n].id+">"+data[n].name+"</option>");
            }
        });
        //供应商
        $.post("<%=request.getContextPath()%>/supplier/allSupplier", function(data){
            for(var n in data){
                $("#supplier").append("<option value = "+data[n].id+">"+data[n].name+"</option>");
            }
        });
        //单位
        $.post("<%=request.getContextPath()%>/goods/getAllGoodsUnit", function(data){
            for(var n in data){
                $("#mainUnit").append("<option value = "+data[n].id+">"+data[n].name+"</option>");
            }
        });

        if($("input[name='vip']:checked").val() == 1){
            $("#vipPrice").attr("disabled", false);
        }

        $("input[name='vip']").on("click", function(){
            $("#vipPrice").attr("disabled", $(this).val() == 1 ? false : true);
        })
    });

	$("#userAddBtn").on("click", function () {
        var goods = new Object();
        goods.name = $("#goodsName").val();
        goods.goodsNo = $("#goodsNo").val();
        goods.imgUrl = $("#imgUrl").val();
        goods.isVipSet = $("input[name = 'vip']:checked").val();
        goods.status = $("input[name = 'using']:checked").val();
        goods.stockDown = $("#stockDown").val();
        goods.stockUp = $("#stockUp").val();
        goods.description = $("#goodsDescription").val();
        goods.goodsTags = $("#tagsIds").val();
        goods.smallTicket = $("#stsIds").val();
        goods.expirationDate = $("#ExpirationDate").val();
        goods.productionDate = $("#productionDate").val();
        goods.supplier = $("#supplier").val();
        goods.pinyin = $("#pinyin").val();
        goods.mainUnit = $("#mainUnit").val();
        goods.tradePrice = $("#tradePrice").val();
        goods.vipPrice = $("#vipPrice").val();
        goods.imgUrl = $("#imgPath").val();
        goods.stock = $("#stock").val();
        goods.price = $("#price").val();
        goods.bid = $("#bid").val();
        goods.categories = $("#categories").val();
        $.post("<%=request.getContextPath()%>/goods/addGoods",{goods : JSON.stringify(goods)}, function (data) {
            layer.msg(data.msg);
        })
    });
})
</script>
</body>
</html>