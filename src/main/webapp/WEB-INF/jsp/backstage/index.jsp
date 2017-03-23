<%@page language="java" pageEncoding="utf-8"%>
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
<title>易捷云收银管理系统</title>
<meta name="keywords" content="">
<meta name="description" content="">
</head>
<body>
<header class="Hui-header cl">
	<a class="Hui-logo" href="<%=request.getContextPath()%>/index">易捷云收银管理系统</a>
	<ul class="Hui-userbar">
		<li class="dropDown dropDown_hover"><a href="#" class="dropDown_A">${user.storeName} | ${user.username}<i class="Hui-iconfont">&#xe6d5;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="<%=request.getContextPath()%>/logout"><i class="Hui-iconfont">&#xe726;</i> 安全退出</a></li>
			</ul>
		</li>
	</ul>
	<a aria-hidden="false" class="Hui-nav-toggle" href="#"></a>
</header>

<aside class="Hui-aside" style="overflow: hidden;">
	<div class="menu_dropdown bk_2 f-16">
        <c:forEach items="${channel}" var="c">
            <dl>
                <dt><i class="Hui-iconfont">${c.iconType}</i> ${c.name}<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <c:forEach items="${menu}" var="m">
                            <c:if test="${c.id eq m.parentId}">
                                <li><a _href="<%=request.getContextPath()%>/${m.url}" href="javascript:void(0)">${m.name}</a></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </dd>
            </dl>
        </c:forEach>
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