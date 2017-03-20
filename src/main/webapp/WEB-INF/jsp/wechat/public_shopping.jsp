<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!doctype html>
<html>
<head>
	<title>购物车</title>
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
			$(".shopping_one").css("height", heightWin - bodyfont * 12);
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
				<li><a id="glyfl">
						<span	class="glyphicon glyphicon-menu-left"></span>返回</a></li>
				<li>购物车</li>
				<li><a class="remab">
						<span	class="glyphicon glyphicon-trash"></span>清空</a></li>
			</ul>
		</div>
		<div class="shopping_one">
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
			<ul class="shopping_wares">
				<li><span>海鲜大杂烩意大利面加海鲜大杂烩意大利面加海鲜大杂烩意大利面加俩蛋</span>
				<p>￥356478</p></li>
				<li>
					<div class="calculator">
						<span class="glyphicon glyphicon-minus-sign rem cgrey"></span> <i>1</i>
						<span class="glyphicon glyphicon-plus-sign add corange"></span>
					</div>
				</li>
			</ul>
		</div>
		<div class="shopping_two">
			<ul>
				<li><span class="glyphicon glyphicon-shopping-cart"></span>购物车:￥23546816<i>99</i></li>
				<li><a>下单</a></li>
			</ul>
		</div>
	</div>
	<script>
		$(".remab").click(function() {
			$(".shopping_wares").remove();
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
				var parentz = $(this).parent().parent().parent();
				setTimeout(function() {
					parentz.remove();
				}, 200);
			} else {
				var rem_a = rems - 1;
				$(this).siblings("i").html(rem_a);
			}
		});
	</script>
</body>
</html>
