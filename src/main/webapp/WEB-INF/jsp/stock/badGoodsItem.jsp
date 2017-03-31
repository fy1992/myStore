<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>商品报损明细</title>
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
			<th width="120">单位</th>
			<th width="120">价格</th>
		</tr>
	</thead>
	<tbody>
        <c:forEach items="${list}" var="badGoodsItem">
            <tr class="text-c">
                <td>${badGoodsItem.goodsName}</td>
                <td>${badGoodsItem.goodsNo}</td>
                <td>${badGoodsItem.goodsNum}</td>
                <td>${badGoodsItem.goodsUnitName}</td>
                <td>${badGoodsItem.price}</td>
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
