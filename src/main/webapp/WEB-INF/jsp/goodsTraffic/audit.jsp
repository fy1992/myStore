<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>门店订货审核</title>
	<link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
	<link href="${ctxResource}/css/admin.css" rel="stylesheet" type="text/css" />
	<link href="${ctxResource}/css/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
	<link href="${ctxResource}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<nav class="clearfix navbar">
	<div class="c-success f-l mr-10"><i class="Hui-iconfont mr-5 f-16">&#xe601;</i>审核</div>
	<div class="f-l">&gt;</div>
	<div class="f-l ml-10 mr-10"><i class="Hui-iconfont mr-5">&#xe619;</i>配货</div>
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
			<th>配货量</th>
			<th width="120">配货价(元/单位)</th>
			<th>小计(元)</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody>
        <c:forEach items="${list}" var="orderGood">
            <tr class="text-c">
                <td>${orderGood.goodsName}</td>
                <td>${orderGood.goodsNo}</td>
                <td>${orderGood.orderNum}</td>
                <td>${orderGood.mainUnitName}</td>
                <td>${orderGood.distributeNum}</td>
                <td>${orderGood.price}</td>
                <td>${orderGood.priceSum}</td>
                <td>${orderGood.description}</td>
            </tr>
        </c:forEach>
	</tbody>
</table>

<div class="cfpdBtnbox">
	<div class="f-l ml-20">共 <b class="c-primary">${categoriesNum}</b> 种商品， <b class="c-primary">${num}</b> 件， 总计 <b class="c-primary">${totalprice}</b> 元。</div>
    <c:choose>
		<c:when test="${status eq -1}">
			<a class="btn btn-primary size-M f-r" id="recover">恢复</a>
		</c:when>
		<c:otherwise>
			<a class="btn btn-primary size-M f-r" id="pass" >审核通过</a>
			<a class="btn btn-default size-M f-r" id="nopass">作废</a>
        </c:otherwise>
    </c:choose>
	<%--<a class="btn btn-default size-M f-r disabled" id="obsolete" style="display: none;">已作废</a>--%>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
<script>
$(function(){
	//作废
	$("#nopass").click(function(){
        $.post("<%=request.getContextPath()%>/server/goodsTraffic/audit", {"id": ${goodsTrafficId}, "type" : -1}, function (data) {
			layer.msg(data.msg, {time : 1500, icon:6}, function () {
                window.parent.table.fnDraw();
                layer_close();
            })
        });
	});
	//恢复
	$("#recover").click(function(){
		layer.msg('确定要恢复吗？', {
			time: 0 ,//不自动关闭
			btn: ['确定', '取消'],
			yes: function(index){
                $.post("<%=request.getContextPath()%>/server/goodsTraffic/audit", {id : "${goodsTrafficId}", type : 0}, function (data) {
                    $("#pass,#nopass").css("display","block");
                    $("#recover,#obsolete").css("display","none");
                    layer.msg(data.msg, {time:1000, icon:6}, function () {
                        window.parent.table.fnDraw();
                        layer_close();
                    });
                })
            }
		});
	});
	//通过
	$("#pass").click(function(){
	    $.post("<%=request.getContextPath()%>/server/goodsTraffic/audit", {"id": "${goodsTrafficId}", "type" : 1}, function (data) {
			window.parent.table.fnDraw();
            window.location.href = data.msg;
        });
	});
});
</script>
</body>
</html>
