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
	<script type="text/javascript" src="${ctxResource}/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="${ctxResource}/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="wap">
		<div class="choose_one">
			<ul>
				<li><a id="glyfl"><span class="glyphicon glyphicon-th"></span>分类</a></li>
				<li><a><span class="glyphicon glyphicon-home"></span>壹號掌柜</a></li>
				<li><a><span class="glyphicon glyphicon-user"></span></a></li>
			</ul>
		</div>
		<div class="choose_two">
		</div>
		<div class="choose_three">
			<ul>
				<li>
					<a href="${ctx}/wechatdemo/shoppingCart">
						<span class="glyphicon glyphicon-shopping-cart"></span>
						<span>购物车:￥</span>
						<span id="totalPrice">0</span>
					</a><i id="totalNum">0</i>
				</li>
				<li><a><span class="glyphicon glyphicon-search"></span></a></li>
			</ul>
		</div>
		<ul class="glyfl">
			<li><a href="javascript:;" onclick="loadData(0)">全部</a></li>
			<li><a href="javascript:;" onclick="loadData(4)">测试</a></li>
			<c:forEach var="categ" items="${categList }">
				<li><a href="javascript:;" onclick="loadData(${categ.id })">${categ.name }</a></li>
			</c:forEach>
		</ul>
	</div>
	<script type="text/javascript" >
		// 定义购物车map
		var shopping_cart;
		
		window.onresize = function() {
			w_h();
		}
		
		$(document).ready(function() {
			w_h();
			loadCookie();
			loadData(0);
		});
		
		function loadCookie(){
			var shop_cookie = $.cookie("shopping_cart");
			if(shop_cookie){
				shopping_cart = new Map(JSON.parse(shop_cookie));
			}else{
				shopping_cart = new Map();
			}
		}
		
		function loadData(categId){
			$.ajax({
				url:'${ctx}/wechatdemo/goodsByCategId?categId='+categId,
				type:'get',
				success: function(data){
					var tempDiv = "";
					var totalNum = 0;
					var totalPrice = 0;
					for (i=0; i<data.length; i++) {
						var num = 0;
						var goods = data[i];
						if(shopping_cart.has(""+goods.id)){
							num = shopping_cart.get(""+goods.id);
							totalNum += num;
							totalPrice += num*goods.price;
						}
						tempDiv += "<div class=\"choose_wares\">";
						tempDiv += "<img src=\""+goods.imgUrl+"\">";
						tempDiv += "<ul> <li>"+goods.goodsName+"</li>";
						tempDiv += "<li> <p>￥<span>"+goods.price+"</span></p>";
						tempDiv += "<div class=\"calculator\"><span class=\"glyphicon glyphicon-minus-sign cgrey\" onclick=\"delGoods(this, '"+goods.id+"')\"></span>";
						tempDiv += "<i>"+num+"</i><span class=\"glyphicon glyphicon-plus-sign corange\" onclick=\"addGoods(this, '"+goods.id+"')\"></span>";
						tempDiv += "</div> </li> </ul> </div>";
					}
					$(".choose_two").empty();
					$(".choose_two").html(tempDiv);
					$("#totalNum").text(totalNum);
					$("#totalPrice").text(totalPrice);
					$(".calculator i").each(function() {
						var remapk = parseInt($(this).html());
						if (remapk > 0) {
							$(this).parent().children().eq(0).removeClass("cgrey");
							$(this).parent().children().eq(0).addClass("corange");
						}
					});
				}
			});
			
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
			$("#totalPrice").text(parseFloat($("#totalPrice").text()) + parseFloat($(ele).parent().prev().children().eq(0).text()));
		};
		
		function delGoods(ele, goodsId){
			var rems = parseInt($(ele).parent().children().eq(1).html());
			var remb = $(ele);
			if (rems > 0) {
				if (rems == 1) {
					remb.removeClass("corange");
					remb.addClass("cgrey");
				} 
				var rem_a = rems - 1;
				shopping_cart.set(goodsId, rem_a);
				$(ele).siblings("i").html(rem_a);
				$.cookie("shopping_cart", JSON.stringify([...shopping_cart]), {path:"/"});
				$("#totalNum").text(parseInt($("#totalNum").text()) - 1);
				$("#totalPrice").text(parseFloat($("#totalPrice").text()) - parseFloat($(ele).parent().prev().children().eq(0).text()));
			}
		};
		
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
		
		$("#glyfl").click(function() {
			$(".glyfl").css("display", "block")
		});
		
		$(".glyfl li a").click(function() {
			$(".glyfl").css("display", "none")
		});
		
	</script>
</body>
</html>
