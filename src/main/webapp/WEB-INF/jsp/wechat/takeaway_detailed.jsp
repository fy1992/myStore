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
					<a id="glyfl"><span class="glyphicon glyphicon-menu-left"></span>返回</a>
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
				购物清单：<i>共5件</i>
			</p>
			<ul class="detailed_two">
				<li><i>1</i>
				<p>网袜kid你法师大富科技阿斯你法师大富科技阿斯蒂芬蒂芬</p>
					<span>$12546811</span></li>
				<li><i>1</i>
				<p>网袜kid你法师大富蒂芬</p>
					<span>$125468</span></li>
				<li><i>1</i>
				<p>网袜kid你法师大富科技阿斯蒂芬</p>
					<span>$125468</span></li>
				<li><i>1</i>
				<p>网袜kid你法师大富科技蒂芬</p>
					<span>$125468</span></li>
				<li><i>1</i>
				<p>网袜kid你法师大富科技蒂芬</p>
					<span>$125468</span></li>
			</ul>
			<p>配送信息：</p>
			<ul class="detailed_three">
				<li><p>收货人：</p>
					<i>阿斯蒂芬</i></li>
				<li><p>电话：</p>
					<i>12496547236</i></li>
				<li><p>地址：</p>
					<i>网袜kid你法师大富科技阿斯你法师大富科技阿斯蒂芬蒂芬</i></li>
				<li><p>配送时间：</p>
					<i>2017-05-15 11:31:31</i></li>
				<li><p>付款方式：</p>
					<i>网单，货到付款</i></li>
				<li><p>备注：</p>
					<i>网袜kid你法师大富科技阿斯你法师大富科技阿斯蒂</i></li>
			</ul>
		</div>
		<div class="detailed_four">
			<ul>
				<li>￥23816124</li>
				<li><a>联系商家</a>|<a>再次购买</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
