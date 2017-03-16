<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>货流管理</title>
	<link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
	<link href="${ctxResource}/css/admin.css" rel="stylesheet" type="text/css" />
	<link href="${ctxResource}/css/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
	<link href="${ctxResource}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table class="table table-border table-bordered table-bg" id="xpjgl">
	<thead>
		<tr class="text-c">
			<th>商品名称</th>
			<th>条码</th>
			<th>供货商</th>
			<th>单位</th>
			<th>货流量</th>
			<th width="120">实收量</th>
			<th width="120">进货价</th>
			<th>小计</th>
		</tr>
	</thead>
	<tbody>
        <c:forEach items="${list}" var="orderGood">
            <tr class="text-c">
                <td>${orderGood.goodsName}</td>
                <td>${orderGood.goodsNo}</td>
                <td>
                    <c:choose>
                        <c:when test="${empty orderGood.supplierName}">-</c:when>
                        <c:otherwise>${orderGood.supplierName}</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${empty orderGood.mainUnitName}">-</c:when>
                        <c:otherwise>${orderGood.mainUnitName}</c:otherwise>
                    </c:choose>
                </td>
                <td>${orderGood.orderNum}</td>
                <td>${orderGood.distributeNum}</td>
                <td>${orderGood.price}</td>
                <td>${orderGood.priceSum}</td>
            </tr>
        </c:forEach>
        <tr class="text-c" style="border: 1px solid #ddd;background: #f5f5f5;">
            <td>合计</td>
            <td>${num} 种商品</td>
            <td>-</td>
            <td>-</td>
            <td>-</td>
            <td>${trafficManage.goodsNum}</td>
            <td>-</td>
            <td>${trafficManage.totalPrice}</td>
        </tr>
	</tbody>
</table>

<div class="cfpdBtnbox">
	<div class="f-l ml-20"><b class="c-primary" id = "status"></b> <span id="time"></span></div>
    <c:if test="${trafficManage.status eq 0}">
        <a class="btn btn-primary size-M f-r" id="pass">确认进货</a>
        <a class="btn btn-default size-M f-r" id="nopass">拒绝进货</a>
    </c:if>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctxResource}/js/myself.js"></script>
<script>
$(function(){
    if(${trafficManage.status ne 0}){
        var msg = "${trafficManage.status}" == "-1" ? "已拒绝进货" : "已完成进货";
        var msgClass = "${trafficManage.status}" == "-1" ? "c-danger" : "c-success";
        $("#status").text(msg);
        $("#status").prop("class", msgClass);
        $("#time").text(format("${trafficManage.optTime}"));
    }
	//拒绝
	$("#nopass").click(function(){
        $.post("<%=request.getContextPath()%>/server/goodsTrafficManage/prepare", {"id": ${trafficManage.id}, "type" : -1}, function (data) {
			layer.msg(data.msg, {time : 1500, icon:6}, function () {
                window.parent.table.fnDraw();
				$("#status").text(data.msg);
                $("#status").prop("class", "c-danger");
				$("#time").text(format(data.object));
            })
        });
	});
	//确认
	$("#pass").click(function(){
	    $.post("<%=request.getContextPath()%>/server/goodsTrafficManage/prepare", {"id": "${trafficManage.id}", "type" : 1}, function (data) {
			window.parent.table.fnDraw();
            $("#status").text(data.msg);
            $("#status").prop("class", "c-success");
            $("#time").text(format(data.object));
        });
	});
});
</script>
</body>
</html>
