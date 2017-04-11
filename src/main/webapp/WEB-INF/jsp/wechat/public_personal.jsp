<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!doctype html>
<html>
<head>
	<title>个人中心</title>
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
				<li>
					<a href="javascript:history.go(-1);"><span class="glyphicon glyphicon-menu-left"></span>返回</a>
				</li>
				<li>个人中心</li>
				<li><a>修改</a></li>
			</ul>
		</div>
		<div class="personal_one">
			<div class="personal_bg">
				<a class="personal_bga"><span class="glyphicon glyphicon-user"></span>${vip.vipName }</a>
			</div>
			<ul class="personal_two">
				<li><a><i>0</i>优惠券</a></li>
				<li><a><i>0</i>积分</a></li>
				<li><a><i>0</i>余额</a></li>
			</ul>
			<ul class="personal_three">
				<li><p>卡号：</p><span>${vip.vipNo }</span></li>
				<li><p>类型：</p><span>普通会员</span></li>
				<li><p>手机：</p><span>${vip.phone }</span></li>
				<a><li><p>支付：</p><span class="glyphicon glyphicon-qrcode"></span></li></a>
				<a href="${ctx }/wechatdemo/order_history"><li><i>历史订单</i><i class="glyphicon glyphicon-menu-right"></i></li></a>
			</ul>
		</div>
	</div>
</body>
</html>
