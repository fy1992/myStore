<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!doctype html>
<html>
<head>
	<title>历史订单</title>
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
			$("body").css("font-size", bodyfont);
			$(".wap").css("width", widthWin);
			$(".wap").css("height", heightWin);
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
				<li><a id="glyfl"><span class="glyphicon glyphicon-menu-left"></span>返回</a></li>
				<li>历史订单</li>
				<li><a><span class="glyphicon glyphicon-qrcode"></span></a></li>
			</ul>
		</div>
		<div class="personal_one">
			<div class="history_one">
				<a><span class="glyphicon glyphicon-time blue"></span>等待处理</a> 
				<a><span class="glyphicon glyphicon-refresh orange"></span>配送途中</a> 
				<a><span class="glyphicon glyphicon-check green"></span>完成</a>
			</div>
			<div class="history_two">
				<ul>
					<li class="history_dian">网单</li>
					<li>店单</li>
				</ul>
				<div class="history_two1" style="display: block">
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a> 
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a> 
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a>
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a>
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a>
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a>
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a>
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a>
				</div>
				<div class="history_two1">
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a>
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a>
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a>
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a>
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a>
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a>
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a>
					<a>
						<span class="glyphicon glyphicon-time blue"></span>
						<p>2017-02-15 11:31&nbsp;共99件<i>￥132544</i></p>
						<i class="glyphicon glyphicon-menu-right"></i>
					</a>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(".history_two ul li").click(function() {
			var index = $(this).index();
			$('.history_two1').hide();
			$('.history_two1:eq(' + index + ')').show();
			$(".history_two ul li").removeClass("history_dian");
			$(this).addClass("history_dian");
		});
	</script>
</body>
</html>
