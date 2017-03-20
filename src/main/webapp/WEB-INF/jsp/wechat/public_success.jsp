<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!doctype html>
<html>
<head>
	<title>下单成功</title>
	<meta charset="utf-8">
	<meta name="viewport"	content="width=device-width, initial-scale=1.0, minimum-scale=1.0 , maximum-scale=1.0, user-scalable=0">
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
			$(".success_one").css("height", heightWin - bodyfont * 12);
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
					<a id="glyfl"><span	class="glyphicon glyphicon-menu-left"></span>返回</a>
				</li>
				<li>下单成功</li>
				<li></li>
			</ul>
		</div>
		<div class="success_one">
			<p>
				<span class="glyphicon glyphicon-ok success_co"></span>
			</p>
			<span>下单状态：下单成功</span> 
			<span>订单号：20170217163807436410</span> <i>2017-02-17	16:35:21</i>
		</div>
		<div class="success_two">
			<ul>
				<li><a>致电商家：15136072409</a></li>
				<li><a>查看订单</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
