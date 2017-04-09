<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!doctype html>
<html>
<head>
	<title>订单详情</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0 , maximum-scale=1.0, user-scalable=0">
	<link href="${ctxResource}/css/bootstrap.min.css" type="text/css" rel="stylesheet" media="all">
	<link href="${ctxResource}/css/wap.css" type="text/css" rel="stylesheet" media="all">
	<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctxResource}/js/bootstrap.min.js"></script>
</head>
<body>
	<script>
		function w_h(widthWin, heightWin) {
			var widthWin = $(window).width();
			var heightWin = $(window).height();
			var bodyfont;
			if (widthWin < 320) {
				bodyfont = 320 / 40;
			} else if (widthWin > 640) {
				bodyfont = 640 / 40;
			} else {
				bodyfont = widthWin / 40;
			}
			$(".wap").css("width", widthWin);
			$(".wap").css("height", heightWin);
			$("body").css("font-size", bodyfont);
			$(".detailed_one").css("height", heightWin - bodyfont * 12);
		}
		$(document).ready(function() {
			w_h()
		});
		window.onresize = function() {
			w_h();
		}
	</script>
	<div class="wap">
		<div class="choose_one">
			<ul>
				<li>
					<a href="javascript:history.go(-1);"><span class="glyphicon glyphicon-menu-left"></span>返回</a>
				</li>
				<li>订单详情</li>
				<li><a><span class="glyphicon glyphicon-qrcode"></span></a></li>
			</ul>
		</div>
		<div class="detailed_one">
			<div class="history_one">
				<a><span class="glyphicon glyphicon-time blue"></span>等待处理</a> 
				<a><span class="glyphicon glyphicon-refresh grey"></span>配送途中</a> 
				<a><span class="glyphicon glyphicon-check grey"></span>完成</a>
			</div>
			<p>
				购物清单：<i>共${order.orderNums }件</i>
			</p>
			<ul class="detailed_two">
				<c:forEach items="${items }" var="item">
					<li><i>${item.orderNum }</i>
					<p>${item.goodsName }</p>
						<span>$${item.price }</span></li>
				</c:forEach>
			</ul>
			<p>配送信息：</p>
			<ul class="detailed_three">
				<li><p>收货人：</p>
					<i>${order.orderName }</i></li>
				<li><p>电话：</p>
					<i>${order.phone }</i></li>
				<li><p>地址：</p>
					<i>${order.orderAddr }</i></li>
				<li><p>下单时间：</p>
					<i><fmt:formatDate value="${order.orderTime }" type="both"/></i></li>
				<li><p>付款方式：</p>
					<i>到店支付</i></li>
				<li><p>备注：</p>
					<i>${order.description }</i></li>
			</ul>
		</div>
		<div class="detailed_four">
			<ul>
				<li>￥${order.totalPrice }</li>
				<li><a>联系商家</a>|<a href="${ctx }/wechatdemo/goodsList">再次购买</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
