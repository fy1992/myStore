<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!doctype html>
<html>
<head>
	<title>商品详情</title>
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
			$(".personal_one").css("height", heightWin - bodyfont * 12);
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
				<li><a href="javascript:history.go(-1);"><span
						class="glyphicon glyphicon-menu-left"></span>返回</a></li>
				<li>商品详情</li>
				<li></li>
			</ul>
		</div>
		<div class="personal_one">
			<div class="details_two">
				<ul>
					<li class="history_dian">基本信息</li>
					<li>商品描述</li>
				</ul>
				<div class="details details_one" style="display: block">
					<img src="../images/1.jpg">
					<ul>
						<li><p>商品名称:</p>
							<span>我记到发咯乒乓球男垃圾卡仕达放假就去弄asdlfkjienniqnqlf</span></li>
						<li><p>商品价格:</p>
							<span>￥12346545</span></li>
					</ul>

				</div>
				<div class="details details_one">
					<ul>
						<li><p>商品名称:</p>
							<span>我记到发咯乒乓球男垃圾卡仕达放假就去弄asdlfkjienniqnqlf</span></li>
						<li><p>商品价格:</p>
							<span>￥12346545</span></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="details_three">
			<ul>
				<li><div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div></li>
				<li><a>加入购物车</a></li>
			</ul>
		</div>
	</div>
	<script>
		$(".details_two ul li").click(function() {
			var index = $(this).index();
			$('.details').hide();
			$('.details:eq(' + index + ')').show();
			$(".details_two ul li").removeClass("history_dian");
			$(this).addClass("history_dian");
		});
		$(".add").click(function() {
			var adds = parseInt($(this).parent().children().eq(1).html());
			var addb = $(this).parent().children().eq(0);
			if (adds == 0) {
				addb.removeClass("cgrey");
				addb.addClass("corange");
			}
			var add_a = adds + 1;
			$(this).siblings("i").html(add_a);
		});
		$(".rem").click(function() {
			var rems = parseInt($(this).parent().children().eq(1).html());
			var remb = $(this);
			if (rems < 2) {
				remb.removeClass("corange");
				remb.addClass("cgrey");
				$(this).siblings("i").html(0);
			} else {
				var rem_a = rems - 1;
				$(this).siblings("i").html(rem_a);
			}
		});
	</script>
</body>
</html>
