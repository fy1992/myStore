<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!doctype html>
<html>
<head>
	<title>优惠券</title>
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
			;
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
				<li><a id="glyfl"><span
						class="glyphicon glyphicon-menu-left"></span>返回</a></li>
				<li>优惠券</li>
				<li></li>
			</ul>
		</div>
		<div class="personal_one">
			<div class="coupon_one cpone">
				<div class="coupon_two">
					<span class="coupon_three"> <i>ID:65426744</i>
						<p>满200元减50元</p>
					</span> <span class="coupon_four"><img src="../images/tiao.png">
					<p>已过期</p></span>
				</div>
				<ul class="coupon_five">
					<li><p>有效时间：</p>
						<span>3个月</span></li>
					<li><p>有效日期：</p>
						<span>2018年06月15日前</span></li>
					<li><p>使用规则：</p>
						<span>在本店消费满200元，可以免费50元</span></li>
				</ul>
			</div>
			<div class="coupon_one">
				<div class="coupon_two">
					<span class="coupon_three"> <i>ID:65426744</i>
						<p>满200元减50元</p>
					</span> <span class="coupon_four"><img src="../images/tiao.png">
					<p>已过期</p></span>
				</div>
				<ul class="coupon_five">
					<li><p>有效时间：</p>
						<span>3个月</span></li>
					<li><p>有效日期：</p>
						<span>2018年06月15日前</span></li>
					<li><p>使用规则：</p>
						<span>在本店消费满200元，可以免费50元</span></li>
				</ul>
			</div>
			<div class="coupon_one cpone">
				<div class="coupon_two">
					<span class="coupon_three"> <i>ID:65426744</i>
						<p>满200元减50元</p>
					</span> <span class="coupon_four"><img src="../images/tiao.png">
					<p>已过期</p></span>
				</div>
				<ul class="coupon_five">
					<li><p>有效时间：</p>
						<span>3个月</span></li>
					<li><p>有效日期：</p>
						<span>2018年06月15日前</span></li>
					<li><p>使用规则：</p>
						<span>在本店消费满200元，可以免费50元</span></li>
				</ul>
			</div>
			<div class="coupon_one cpone">
				<div class="coupon_two">
					<span class="coupon_three"> <i>ID:65426744</i>
						<p>满200元减50元</p>
					</span> <span class="coupon_four"><img src="../images/tiao.png">
					<p>已过期</p></span>
				</div>
				<ul class="coupon_five">
					<li><p>有效时间：</p>
						<span>3个月</span></li>
					<li><p>有效日期：</p>
						<span>2018年06月15日前</span></li>
					<li><p>使用规则：</p>
						<span>在本店消费满200元，可以免费50元</span></li>
				</ul>
			</div>
			<div class="coupon_one cpone">
				<div class="coupon_two">
					<span class="coupon_three"> <i>ID:65426744</i>
						<p>满200元减50元</p>
					</span> <span class="coupon_four"><img src="../images/tiao.png">
					<p>已过期</p></span>
				</div>
				<ul class="coupon_five">
					<li><p>有效时间：</p>
						<span>3个月</span></li>
					<li><p>有效日期：</p>
						<span>2018年06月15日前</span></li>
					<li><p>使用规则：</p>
						<span>在本店消费满200元，可以免费50元</span></li>
				</ul>
			</div>
			<div class="coupon_one cpone">
				<div class="coupon_two">
					<span class="coupon_three"> <i>ID:65426744</i>
						<p>满200元减50元</p>
					</span> <span class="coupon_four"><img src="../images/tiao.png">
					<p>已过期</p></span>
				</div>
				<ul class="coupon_five">
					<li><p>有效时间：</p>
						<span>3个月</span></li>
					<li><p>有效日期：</p>
						<span>2018年06月15日前</span></li>
					<li><p>使用规则：</p>
						<span>在本店消费满200元，可以免费50元</span></li>
				</ul>
			</div>
			<div class="coupon_one cpone">
				<div class="coupon_two">
					<span class="coupon_three"> <i>ID:65426744</i>
						<p>满200元减50元</p>
					</span> <span class="coupon_four"><img src="../images/tiao.png">
					<p>已过期</p></span>
				</div>
				<ul class="coupon_five">
					<li><p>有效时间：</p>
						<span>3个月</span></li>
					<li><p>有效日期：</p>
						<span>2018年06月15日前</span></li>
					<li><p>使用规则：</p>
						<span>在本店消费满200元，可以免费50元</span></li>
				</ul>
			</div>
			<div class="coupon_one cpone">
				<div class="coupon_two">
					<span class="coupon_three"> <i>ID:65426744</i>
						<p>满200元减50元</p>
					</span> <span class="coupon_four"><img src="../images/tiao.png">
					<p>已过期</p></span>
				</div>
				<ul class="coupon_five">
					<li><p>有效时间：</p>
						<span>3个月</span></li>
					<li><p>有效日期：</p>
						<span>2018年06月15日前</span></li>
					<li><p>使用规则：</p>
						<span>在本店消费满200元，可以免费50元</span></li>
				</ul>
			</div>
			<div class="coupon_one cpone">
				<div class="coupon_two">
					<span class="coupon_three"> <i>ID:65426744</i>
						<p>满200元减50元</p>
					</span> <span class="coupon_four"><img src="../images/tiao.png">
					<p>已过期</p></span>
				</div>
				<ul class="coupon_five">
					<li><p>有效时间：</p>
						<span>3个月</span></li>
					<li><p>有效日期：</p>
						<span>2018年06月15日前</span></li>
					<li><p>使用规则：</p>
						<span>在本店消费满200元，可以免费50元</span></li>
				</ul>
			</div>
			<div class="coupon_one cpone">
				<div class="coupon_two">
					<span class="coupon_three"> <i>ID:65426744</i>
						<p>满200元减50元</p>
					</span> <span class="coupon_four"><img src="../images/tiao.png">
					<p>已过期</p></span>
				</div>
				<ul class="coupon_five">
					<li><p>有效时间：</p>
						<span>3个月</span></li>
					<li><p>有效日期：</p>
						<span>2018年06月15日前</span></li>
					<li><p>使用规则：</p>
						<span>在本店消费满200元，可以免费50元</span></li>
				</ul>
			</div>
			<div class="coupon_one cpone">
				<div class="coupon_two">
					<span class="coupon_three"> <i>ID:65426744</i>
						<p>满200元减50元</p>
					</span> <span class="coupon_four"><img src="../images/tiao.png">
					<p>已过期</p></span>
				</div>
				<ul class="coupon_five">
					<li><p>有效时间：</p>
						<span>3个月</span></li>
					<li><p>有效日期：</p>
						<span>2018年06月15日前</span></li>
					<li><p>使用规则：</p>
						<span>在本店消费满200元，可以免费50元</span></li>
				</ul>
			</div>
			<div class="coupon_one ">
				<div class="coupon_two">
					<span class="coupon_three"> <i>ID:65426744</i>
						<p>满200元减50元</p>
					</span> <span class="coupon_four"><img src="../images/tiao.png">
					<p>已过期</p></span>
				</div>
				<ul class="coupon_five">
					<li><p>有效时间：</p>
						<span>3个月</span></li>
					<li><p>有效日期：</p>
						<span>2018年06月15日前</span></li>
					<li><p>使用规则：</p>
						<span>在本店消费满200元，可以免费50元</span></li>
				</ul>
			</div>
			<div class="coupon_one cpone">
				<div class="coupon_two">
					<span class="coupon_three"> <i>ID:65426744</i>
						<p>满200元减50元</p>
					</span> <span class="coupon_four"><img src="../images/tiao.png">
					<p>已过期</p></span>
				</div>
				<ul class="coupon_five">
					<li><p>有效时间：</p>
						<span>3个月</span></li>
					<li><p>有效日期：</p>
						<span>2018年06月15日前</span></li>
					<li><p>使用规则：</p>
						<span>在本店消费满200元，可以免费50元</span></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
