<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!doctype html>
<html>
<head>
	<title>确认订单</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0 , maximum-scale=1.0, user-scalable=0">
	<link href="${ctxResource}/css/wap.css" type="text/css" rel="stylesheet" media="all">
	<link href="${ctxResource}/css/bootstrap.min.css" type="text/css" rel="stylesheet" media="all">
	<link href="${ctxResource}/css/jquery-weui.min.css" type="text/css" rel="stylesheet" media="all">
	<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctxResource}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctxResource}/js/jquery-weui.min.js"></script>
</head>
<body>
	<div class="wap">
		<div class="choose_one">
			<ul>
				<li><a id="glyfl"><span class="glyphicon glyphicon-menu-left"></span>返回</a></li>
				<li>确认订单</li>
				<li></li>
			</ul>
		</div>
		<div class="awayorder_one">
			<p>客户信息：</p>
			<ul class="awayorderul1">
				<li><p>姓名：</p>
					<input type="text" placeholder="请填写姓名"></li>
				<li><p>电话：</p>
					<input type="text" placeholder="请填写联系电话"></li>
				<li><p>地址：</p>
					<input type="text" value="到店消费" disabled></li>
				<li><p>时间：</p>
					<input type="text" id="time-format" value="${time }" placeholder="请填写到店时间" ></li>
			</ul>
			<p>支付方式：</p>
			<ul class="awayorderul2">
				<li><p>到店支付</p></li>
			</ul>
			<p>添加备注：</p>
			<textarea placeholder="给商家留言 " class="awayorderul3"></textarea>
		</div>
		<div class="shopping_two">
			<ul>
				<li><span class="glyphicon glyphicon-shopping-cart"></span>购物车:￥${totalPrice }<i>${totalNum }</i></li>
				<li><a>提交订单</a></li>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
	
		$("#time-format").datetimePicker({
	        times: function () {
	            return [
					{	
						values: (function () {
							var hours = [];
							for (var i=0; i<24; i++){
								hours.push(i > 9 ? i : '0'+i);
							} 
							return hours;
						})()
					},
	              	{
	                	divider: true,  // 这是一个分隔符
	               		content: ':'
	              	},
					{
						values: (function () {
							return ['00', '15', '30', '45'];
	                	})()
					}
	            ];
	          },
		});
	
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
			$(".awayorder_one").css("height", heightWin - bodyfont * 12);
		}
		
		$(document).ready(function() {
			w_h()
		});
		
		window.onresize = function() {
			w_h();
		}
	</script>
</body>
</html>