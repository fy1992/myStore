<%@page language="java" import="java.util.*,cn.dahe.model.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
	<!--<script type="text/javascript" src="${ctxResource}/js/html5.js"></script>
	<script type="text/javascript" src="${ctxResource}/js/respond.min.js"></script>
<![endif]-->
<link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
<link href="${ctxResource}/css/admin.css" rel="stylesheet" type="text/css" />
<link href="${ctxResource}/css/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />

<title>GoldenLeopard后台系统</title>
<meta name="keywords" content="">
<meta name="description" content="">
</head>
<body>
<header class="Hui-header cl">
	<%--<iframe class="baniframe" sandbox="allow-scripts allow-same-origin" id="mh" src="${ctxResource}/js/cavans.html" width="100%" height="50" style="position: absolute; z-index: -1; border: 0;"></iframe>--%>
	<a class="Hui-logo" href="<%=request.getContextPath()%>/index">GoldenLeopard后台系统</a>
	<ul class="Hui-userbar">
		<li class="dropDown dropDown_hover"><a href="#" class="dropDown_A">${user.storeName} | ${user.username}<i class="Hui-iconfont">&#xe6d5;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="<%=request.getContextPath()%>/logout"><i class="Hui-iconfont">&#xe726;</i> 安全退出</a></li>
			</ul>
		</li>
	</ul>
	<a aria-hidden="false" class="Hui-nav-toggle" href="#"></a>
</header>

<aside class="Hui-aside">
	<div class="menu_dropdown bk_2 f-16">
		<dl id="menu-sale">
			<dt><i class="Hui-iconfont">&#xe616;</i> 销售<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="#" href="javascript:void(0)">营业概况</a></li>
					<li><a _href="#" href="javascript:void(0)">销售单据</a></li>
					<li><a _href="#" href="javascript:void(0)">商品销售分析</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-goods">
			<dt><i class="Hui-iconfont">&#xe6c0;</i> 商品<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="<%=request.getContextPath()%>/admin/channel/channelList/view" href="javascript:;">商品分类</a></li>
					<li><a _href="<%=request.getContextPath()%>/goods/list" href="javascript:;">商品资料</a></li>
					<li><a _href="<%=request.getContextPath()%>/admin/channel/channelList/view" href="javascript:;">批量操作</a></li>
					<li><a _href="<%=request.getContextPath()%>/admin/channel/channelList/view" href="javascript:;">排序管理</a></li>
					<li><a _href="<%=request.getContextPath()%>/admin/channel/channelList/view" href="javascript:;">口味管理</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-stock">
			<dt><i class="Hui-iconfont">&#xe623;</i>库存<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="<%=request.getContextPath()%>/admin/log/logList/view" href="javascript:void(0)">日志记录</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-vip">
			<dt><i class="Hui-iconfont">&#xe623;</i>会员<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="#" href="javascript:void(0)">会员资料</a></li>
					<li><a _href="#" href="javascript:void(0)">会员等级</a></li>
					<li><a _href="#" href="javascript:void(0)">会员制度</a></li>
					<li><a _href="#" href="javascript:void(0)">精准营销</a></li>
					<li><a _href="#" href="javascript:void(0)">次卡管理</a></li>
					<li><a _href="#" href="javascript:void(0)">购物卡管理</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-salePro">
			<dt><i class="Hui-iconfont">&#xe623;</i>营销<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="#" href="javascript:void(0)">充值赠送</a></li>
					<li><a _href="#" href="javascript:void(0)">促销活动</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-member">
			<dt><i class="Hui-iconfont">&#xe623;</i>员工<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="#" href="javascript:void(0)">收银员</a></li>
					<li><a _href="#" href="javascript:void(0)">导购员</a></li>
				</ul>
			</dd>
		</dl>
        <dl id="menu-goodsFlow">
            <dt><i class="Hui-iconfont">&#xe623;</i>货流<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="#" href="javascript:void(0)">供货商</a></li>
                    <li><a _href="#" href="javascript:void(0)">门店订货</a></li>
                    <li><a _href="#" href="javascript:void(0)">货流管理</a></li>
                    <li><a _href="#" href="javascript:void(0)">供货商结算</a></li>
                    <li><a _href="#" href="javascript:void(0)">贷款汇总</a></li>
                </ul>
            </dd>
        </dl>
		<dl id="menu-store">
			<dt><i class="Hui-iconfont">&#xe623;</i>店面<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="#" href="javascript:void(0)">建立分店</a></li>
					<li><a _href="#" href="javascript:void(0)">分店信息</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-user">
			<dt><i class="Hui-iconfont">&#xe623;</i>账户<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="#" href="javascript:void(0)">账户信息</a></li>
				</ul>
			</dd>
		</dl>
	</div>
</aside>
<div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active"><span title="我的桌面" data-href="<%=request.getContextPath()%>/admin/welcome">我的桌面</span><em></em></li>
			</ul>
		</div>
	</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="<%=request.getContextPath()%>/welcome" name="iframe"></iframe>
		</div>
	</div>
</section>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script> 
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
</body>
</html>