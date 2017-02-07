<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>厨房票打设置</title>
	<link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/admin.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table class="table table-border table-bordered table-bg table-hover table-sort table-striped box-shadow mb-40">
	<thead>
		<tr class="text-c">
			<th width="60">选择</th>
			<th>序号</th>
			<th>小票机名称</th>
			<th>类型</th>
		</tr>
	</thead>
	<tbody>
		<tr class="text-c">
			<td><input type="checkbox" value="" name=""></td>
			<td>1</td>
			<td>厨房</td>
			<td>一品一切</td>
		</tr>
		<tr class="text-c">
			<td><input type="checkbox" value="" name=""></td>
			<td>2</td>
			<td>厨房</td>
			<td>一单一切</td>
		</tr>
	</tbody>
</table>
<div class="cfpdBtnbox">
	<a class="btn btn-success size-M layui-layer-close layui-layer-close1" id="xpj">管理小票机</a>
	<a class="btn btn-primary size-M f-r pl-20 pr-20" id="ensure">确认</a>
</div>

<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script>
$(function(){
	//管理小票机
	$("#xpj").click(function(){
		$.get("<%=request.getContextPath()%>/goods/smallTicket",function(html){
			$("body").html(html);
		},"html");
	});
	//确认
	$("#ensure").click(function(){
		layer_close();
	});
});
function layer_close(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
</script>
</body>
</html>
