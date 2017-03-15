<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>配货</title>
	<link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/admin.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<nav class="clearfix navbar">
	<div class="f-l mr-10"><i class="Hui-iconfont mr-5 f-16">&#xe619;</i>审核</div>
	<div class="f-l">&gt;</div>
	<div class="c-success f-l ml-10 mr-10"><i class="Hui-iconfont mr-5">&#xe601;</i>配货</div>
	<div class="f-l">&gt;</div>
	<div class="f-l ml-10"><i class="Hui-iconfont mr-5">&#xe619;</i>完成</div>
</nav>
<table class="table table-border table-bordered table-bg" id="xpjgl">
	<thead>
		<tr class="text-c">
			<th>商品名称</th>
			<th>条码</th>
			<th>请求量</th>
			<th>单位</th>
			<th width="120">配货量</th>
			<th width="120">配货价(元/单位)</th>
			<th>小计(元)</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody id = "orderGoodsInfos">
	</tbody>
</table>

<div class="cfpdBtnbox">
	<div class="f-l ml-20">共 <b class="c-primary" id="categoriesNum">${categoriesNum}</b> 种商品， <b class="c-primary" id="goodsNum">${num}</b> 件， 总计 <b class="c-primary" id="priceTotal">${totalprice}</b> 元。</div>
	<a class="btn btn-primary size-M f-r" id="pgoods">配货</a>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/Validform_v5.3.2_min.js"></script>
<script>
$(function(){
    $.post("<%=request.getContextPath()%>/server/goodsTraffic/findOrderGoodsInfosByGoodsTrafficId",
        {"id" : ${goodsTrafficId}},
        function(data){
            var categoriesArr = [];
            for(var n in data){
                if($.inArray(data[n].catetoriesId, categoriesArr) != -1){
                    categoriesArr.push(data[n].categoriesId);
                }
                $("#orderGoodsInfos").append(
                    "<tr>" +
                    "<td>"+data[n].goodsName+"<input type = 'hidden' value='"+data[n].id+"' class='orderGoodsInfo_id'></td>" +
                    "<td>"+data[n].goodsNo+"</td>" +
                    "<td>"+data[n].orderNum+"</td>" +
                    "<td>"+ (data[n].mainUnit == null || data[n].mainUnit == "" ? "无" : data[n].mainUnit) +"</td>" +
                    "<td><input type=\"text\" class=\"input-text text-r num\"  value=\""+data[n].orderNum+"\" /></td>" +
                    "<td><input type=\"text\" class=\"input-text text-r price\"  value=\""+data[n].price+"\" /></td>" +
                    "<td class=\"total\">"+data[n].priceSum+"</td>" +
                    "<td>"+ (data[n].description == null || data[n].description == "" ? "-" : data[n].description) +"</td>" +
                    "</tr>"
                );
            }

            $(".num").blur(function(){
                var goodsNum = 0, priceTotal = 0;
                $(".num").each(function(){
                    goodsNum += Number($(this).val());
                    priceTotal += $(this).val() * $(this).parent().next().children().val();
                });
                $(this).parent().next().next().text(($(this).val() * $(this).parent().next().children().val()).toFixed(2));
                $("#goodsNum").html(goodsNum);
                $("#priceTotal").html(priceTotal.toFixed(2));
            });

            $(".price").blur(function(){
                var goodsNum = 0, priceTotal = 0;
                $(".price").each(function(){
                    goodsNum = $(this).parent().prev().children().val();
                    priceTotal += Number($(this).val() * goodsNum);
                });
                $(this).parent().next().text(($(this).val() * $(this).parent().prev().children().val()).toFixed(2));
                $("#priceTotal").html(priceTotal.toFixed(2));
            });
        }
    );
    function orderGoodsInfo(id, distributeNum, price, priceSum){
        this.id = id;
        this.distributeNum = distributeNum;
        this.price = price;
        this.priceSum = priceSum;
    }
	//配货
	$("#pgoods").click(function(){
        var orderGoodsInfoArr = [];
        var len = $(".orderGoodsInfo_id").length;
        for(var i = 0; i < len; i++){
            var id = $(".orderGoodsInfo_id").eq(i).val();
            var distributeNum = $(".num").eq(i).val();
            var price = $(".price").eq(i).val();
            var total = $(".total").eq(i).text();
            if(!price || !distributeNum){
                layer.msg("请输入具体值", {time : 1500, icon :5});
                return false;
            }
            orderGoodsInfoArr.push(new orderGoodsInfo(id, distributeNum, price, total));
        }

        $.post("<%=request.getContextPath()%>/server/goodsTraffic/prepare", {"id": ${goodsTrafficId}, "orderGoodsInfos" : JSON.stringify(orderGoodsInfoArr)}, function (data) {
            window.parent.parent.table.fnDraw();
            layer.closeAll('iframe');
            layer.open({
                type: 2,
                title: false,
                shadeClose: false,
                shade: false,
                closeBtn: 0,
                maxmin: false, //开启最大化最小化按钮
                area: ['950px', '307px'],
                content: "/store/" + data.msg
            });
        });
	});
});

</script>

</body>
</html>
