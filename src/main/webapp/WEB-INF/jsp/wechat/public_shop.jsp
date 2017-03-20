<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!doctype html>
<html>
<head>
	<title>壹號掌柜</title>
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
			$(".personal_one").css("height", heightWin - bodyfont * 6);
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
				<li><a id="glyfl">
						<span	class="glyphicon glyphicon-menu-left"></span>返回</a>
				</li>
				<li>壹號掌柜</li>
				<li><a><span class="glyphicon glyphicon-qrcode"></span></a></li>
			</ul>
		</div>
		<div class="personal_one">
			<div class="shop_bg"></div>
			<ul class="shop_one">
				<li><p>店铺地址：</p>
					<span>应冈比亚共和国总统阿达马·巴罗邀请，全国政协副主</span></li>
				<li><p>联系电话：</p>
					<span>12546853562</span></li>
				<li><p>配送费：</p>
					<span>免费</span></li>
				<li><p>起送价：</p>
					<span>0.0元(满0元免配送费)</span></li>
				<li><p>送达时间：</p>
					<span>30-50分钟</span></li>
				<li><p>营业时间：</p>
					<span>00:00-23:59</span></li>
				<li><p>店铺简介：</p>
					<span>应冈比亚共和国总统阿达马·巴罗邀请，全国政协副主席马培华将作为国家主席习近平特使</span></li>
			</ul>
		</div>
	</div>
</body>
</html>
