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
			;
			$(".wap").css("width", widthWin);
			$(".wap").css("height", heightWin);
			$("body").css("font-size", bodyfont);
			$(".choose_two").css("height", heightWin - bodyfont * 12);
		}
		$(document).ready(function() {
			w_h()
			$(".calculator i").each(function() {
				var remapk = parseInt($(this).html());
				if (remapk > 0) {
					$(this).parent().children().eq(0).removeClass("cgrey");
					$(this).parent().children().eq(0).addClass("corange");
				}
			});
		});
		window.onresize = function() {
			w_h();
		}
	</script>
	<div class="wap">
		<div class="choose_one">
			<ul>
				<li><a id="glyfl"><span class="glyphicon glyphicon-th"></span>分类</a></li>
				<li><a><span class="glyphicon glyphicon-home"></span>壹號掌柜</a></li>
				<li><a><span class="glyphicon glyphicon-user"></span></a></li>
			</ul>
		</div>
		<div class="choose_two">
			<div class="choose_wares">
				<img src="../images/1.jpg">
				<ul>
					<li>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</li>
					<li><p>￥356478</p>
						<div class="calculator">
							<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>12</i>
							<span class="glyphicon glyphicon-plus-sign add corange"></span>
						</div></li>
				</ul>
			</div>
			<div class="choose_wares">
				<img src="../images/1.jpg">
				<ul>
					<li>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</li>
					<li><p>￥356478</p>
						<div class="calculator">
							<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>0</i>
							<span class="glyphicon glyphicon-plus-sign add corange"></span>
						</div></li>
				</ul>
			</div>
			<div class="choose_wares">
				<img src="../images/1.jpg">
				<ul>
					<li>海鲜大杂烩意大利</li>
					<li><p>￥356478</p>
						<div class="calculator">
							<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>0</i>
							<span class="glyphicon glyphicon-plus-sign add corange"></span>
						</div></li>
				</ul>
			</div>
			<div class="choose_wares">
				<img src="../images/1.jpg">
				<ul>
					<li>海鲜大杂烩意大利</li>
					<li><p>￥356478</p>
						<div class="calculator">
							<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>0</i>
							<span class="glyphicon glyphicon-plus-sign add corange"></span>
						</div></li>
				</ul>
			</div>
			<div class="choose_wares">
				<img src="../images/1.jpg">
				<ul>
					<li>海鲜大杂烩意大利</li>
					<li><p>￥356478</p>
						<div class="calculator">
							<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>0</i>
							<span class="glyphicon glyphicon-plus-sign add corange"></span>
						</div></li>
				</ul>
			</div>
			<div class="choose_wares">
				<img src="../images/1.jpg">
				<ul>
					<li>海鲜大杂烩意大利</li>
					<li><p>￥356478</p>
						<div class="calculator">
							<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>0</i>
							<span class="glyphicon glyphicon-plus-sign add corange"></span>
						</div></li>
				</ul>
			</div>
			<div class="choose_wares">
				<img src="../images/1.jpg">
				<ul>
					<li>海鲜大杂烩意大利</li>
					<li><p>￥356478</p>
						<div class="calculator">
							<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>0</i>
							<span class="glyphicon glyphicon-plus-sign add corange"></span>
						</div></li>
				</ul>
			</div>
			<div class="choose_wares">
				<img src="../images/1.jpg">
				<ul>
					<li>海鲜大杂烩意大利</li>
					<li><p>￥356478</p>
						<div class="calculator">
							<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>0</i>
							<span class="glyphicon glyphicon-plus-sign add corange"></span>
						</div></li>
				</ul>
			</div>
			<div class="choose_wares">
				<img src="../images/1.jpg">
				<ul>
					<li>海鲜大杂烩意大利</li>
					<li><p>￥356478</p>
						<div class="calculator">
							<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>0</i>
							<span class="glyphicon glyphicon-plus-sign add corange"></span>
						</div></li>
				</ul>
			</div>
			<div class="choose_wares">
				<img src="../images/1.jpg">
				<ul>
					<li>海鲜大杂烩意大利</li>
					<li><p>￥356478</p>
						<div class="calculator">
							<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>0</i>
							<span class="glyphicon glyphicon-plus-sign add corange"></span>
						</div></li>
				</ul>
			</div>
			<div class="choose_wares">
				<img src="../images/1.jpg">
				<ul>
					<li>海鲜大杂烩意大利</li>
					<li><p>￥356478</p>
						<div class="calculator">
							<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>0</i>
							<span class="glyphicon glyphicon-plus-sign add corange"></span>
						</div></li>
				</ul>
			</div>
			<div class="choose_wares">
				<img src="../images/1.jpg">
				<ul>
					<li>海鲜大杂烩意大利</li>
					<li><p>￥356478</p>
						<div class="calculator">
							<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>0</i>
							<span class="glyphicon glyphicon-plus-sign add corange"></span>
						</div></li>
				</ul>
			</div>
			<div class="choose_wares">
				<img src="../images/1.jpg">
				<ul>
					<li>海鲜大杂烩意大利</li>
					<li><p>￥356478</p>
						<div class="calculator">
							<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>0</i>
							<span class="glyphicon glyphicon-plus-sign add corange"></span>
						</div></li>
				</ul>
			</div>
		</div>
		<div class="choose_three">
			<ul>
				<li><a href="public_shopping.html"><span
						class="glyphicon glyphicon-shopping-cart"></span>购物车:￥23546816</a><i>99</i></li>
				<li><a><span class="glyphicon glyphicon-search"></span></a></li>
			</ul>
		</div>
		<ul class="glyfl">
			<li><a>全部显示</a></li>
			<li><a>促销商品</a></li>
			<li><a>套餐</a></li>
			<li><a>热菜</a></li>
			<li><a>川菜</a></li>
			<li><a>推品</a></li>
			<li><a>合作商品</a></li>
			<li><a>推荐商品合作商</a></li>
			<li><a>推荐商合作商品</a></li>
			<li><a>推荐商品</a></li>
			<li><a>推荐商品</a></li>
			<li><a>推荐商品</a></li>
			<li><a>推荐商品</a></li>
		</ul>
	</div>
	<script>
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
		$("#glyfl").click(function() {
			$(".glyfl").css("display", "block")
		});
		$(".glyfl li a").click(function() {
			$(".glyfl").css("display", "none")
		});
	</script>
</body>
</html>
