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
	<link href="${ctxResource}/css/jquery-weui.min.css" type="text/css" rel="stylesheet" media="all">
	<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctxResource}/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="${ctxResource}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctxResource}/js/jquery-weui.min.js"></script>
</head>
<body>
	<div class="wap">
		<div class="choose_one">
			<ul>
				<li><a href="javascript:history.go(-1);">
						<span	class="glyphicon glyphicon-menu-left"></span>返回</a></li>
				<li>购物车</li>
				<li><a class="remab" onclick="clealAll()">
						<span	class="glyphicon glyphicon-trash"></span>清空</a></li>
			</ul>
		</div>
		<div class="shopping_one">
			<c:forEach var="data" items="${goods_list}">
				<ul class="shopping_wares">
					<li>
						<span>${data.goods.goodsName }</span>
						<p>￥<span>${data.goods.price }</span></p>
					</li>
					<li>
						<div class="calculator">
							<span class="glyphicon glyphicon-minus-sign corange" onclick="delGoods(this, '${data.goods.id}')"></span> <i>${data.count }</i>
							<span class="glyphicon glyphicon-plus-sign corange" onclick="addGoods(this, '${data.goods.id}')"></span>
						</div>
					</li>
				</ul>
			</c:forEach>
		</div>
		<div class="shopping_two">
			<ul>
				<li>
					<span class="glyphicon glyphicon-shopping-cart"></span>
					<span>购物车:￥</span><span id="totalPrice">${totalPrice }</span>
					<i id="totalNum">${totalNum }</i>
				</li>
				<li><a onclick="order_order()">下单</a></li>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		// 定义购物车map
		var shopping_cart;
	
		window.onresize = function() {
			w_h();
		}
		
		$(document).ready(function() {
			w_h();
			loadCookie();
		});
		
		function loadCookie(){
			var shop_cookie = $.cookie("shopping_cart");
			if(shop_cookie){
				shopping_cart = new Map(JSON.parse(shop_cookie));
			}else{
				shopping_cart = new Map();
			}
		}
	
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
		
		// 清空购物车
		function clealAll(){
			$("#totalPrice").text(0);
			$("#totalNum").text(0);
			shopping_cart = new Map();
			$(".shopping_wares").remove();
			$.cookie("shopping_cart", JSON.stringify([...shopping_cart]), {path:"/"});
		}
		
		function addGoods(ele, goodsId){
			var adds = parseInt($(ele).parent().children().eq(1).html());
			var addb = $(ele).parent().children().eq(0);
			if (adds == 0) {
				addb.removeClass("cgrey");
				addb.addClass("corange");
			}
			var add_a = adds + 1;
			shopping_cart.set(goodsId, add_a);
			$(ele).siblings("i").html(add_a);
			$.cookie("shopping_cart", JSON.stringify([...shopping_cart]), {path:"/"});
			$("#totalNum").text(parseInt($("#totalNum").text()) + 1);
			$("#totalPrice").text(parseFloat($("#totalPrice").text()) + parseFloat($(ele).parent().parent().prev().children().eq(1).children().eq(0).text()));
		};
		
		function delGoods(ele, goodsId){
			var remb = $(ele);
			var rems = parseInt(remb.parent().children().eq(1).html());
			if (rems == 1) {
				remb.parent().parent().parent().remove();
			} 
			var rem_a = rems - 1;
			remb.siblings("i").html(rem_a);
			shopping_cart.set(goodsId, rem_a);
			$.cookie("shopping_cart", JSON.stringify([...shopping_cart]), {path:"/"});
			$("#totalNum").text(parseInt($("#totalNum").text()) - 1);
			$("#totalPrice").text(parseFloat($("#totalPrice").text()) - parseFloat(remb.parent().parent().prev().children().eq(1).children().eq(0).text()));
		}
		
		function order_order(){
			if(shopping_cart.entries().next().value){
				location.href="${ctx }/wechatdemo/order_order";
			}else{
				alert("购物车空哒，请选择商品哦~");
				location.href="${ctx }/wechatdemo/goodsList";
			}
		}
	</script>
</body>
</html>
