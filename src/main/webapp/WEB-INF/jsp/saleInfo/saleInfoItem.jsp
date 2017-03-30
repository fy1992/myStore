<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>销售单据明细</title>
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
			<th>商品数量</th>
			<th width="120">商品原价</th>
			<th width="120">实收金额</th>
			<th width="120">利润</th>
		</tr>
	</thead>
	<tbody>
        <c:forEach items="${list}" var="saleInfoItem">
            <tr class="text-c">
                <td>${saleInfoItem.goodsName}</td>
                <td>${saleInfoItem.goodsNo}</td>
                <td>${saleInfoItem.goodsNum}</td>
                <td>${saleInfoItem.goodsPrice}</td>
                <td>${saleInfoItem.realPrice}</td>
                <td>${saleInfoItem.gain}</td>
            </tr>
        </c:forEach>
	</tbody>
</table>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctxResource}/js/myself.js"></script>
</body>
</html>
