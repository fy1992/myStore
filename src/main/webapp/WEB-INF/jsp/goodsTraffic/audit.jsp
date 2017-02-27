<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>门店订货审核</title>
	<link href="${resources}/css/H-ui.css" rel="stylesheet" type="text/css" />
	<link href="${resources}/css/admin.css" rel="stylesheet" type="text/css" />
	<link href="${resources}/css/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
	<link href="${resources}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<nav class="clearfix navbar">
	<div class="c-success f-l mr-10"><i class="Hui-iconfont mr-5 f-16">&#xe601;</i>审核</div>
	<div class="f-l">&gt;</div>
	<div class="f-l ml-10 mr-10"><i class="Hui-iconfont mr-5">&#xe619;</i>配货</div>
	<div class="f-l">&gt;</div>
	<div class="f-l ml-10"><i class="Hui-iconfont mr-5">&#xe619;</i>完成</div>
</nav>
<table class="table table-border table-bordered table-bg" id="xpjgl">
	<thead>
		<tr class="text-c">
			<th>商品名称</th>
			<th>条码</th>
			<th>请求量</th>
			<th>单位</th>
			<th>配货量</th>
			<th width="120">配货价(元/单位)</th>
			<th>小计(元)</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody>
		<tr>
           	<td>奶茶粉</td>
            <td>10001</td>
            <td>3</td>
            <td>无</td>
            <td>5</td>
            <td>100</td>
            <td>300.00</td>
            <td>-</td>
       </tr>
	</tbody>
</table>

<div class="cfpdBtnbox">
	<div class="f-l ml-20">共 <b class="c-primary">1</b> 种商品， <b class="c-primary">5</b> 件， 总计 <b class="c-primary">1000.00</b> 元。</div>
	<a class="btn btn-primary size-M f-r" id="pass">审核通过</a>
	<a class="btn btn-default size-M f-r" id="nopass">作废</a>
	<a class="btn btn-primary size-M f-r" id="recover" style="display: none;">恢复</a>
	<a class="btn btn-default size-M f-r disabled" id="obsolete" style="display: none;">已作废</a>
</div>
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/layer/layer.js"></script>
<script>
$(function(){
	//作废
	$("#nopass").click(function(){
        $.post("<%=request.getContextPath()%>/server/goodsTraffic/audit", {"id": ${goodsTrafficId}, "type" : 0}, function (data) {
			window.parent.table.fnDraw();
            layer_close();
        });
	});
	$("#recover").click(function(){
		layer.msg('确定要恢复吗？', {
			time: 0 ,//不自动关闭
			btn: ['确定', '取消'],
			yes: function(index){
				$("#pass,#nopass").css("display","block");
				$("#recover,#obsolete").css("display","none");
				layer.msg('已恢复!',{time:1000});
			}
		});
	});
	//通过
	$("#pass").click(function(){
	    $.post("<%=request.getContextPath()%>/server/goodsTraffic/audit", {"id": ${goodsTrafficId}, "type" : 1}, function (data) {
            $("body").html(data.msg);
        });
		/*layer.msg('审核通过!',{time:1000});
		setTimeout(function(){
			$.get("/goodsTraffic/audit/2",function(html){
				$("body").html(html);
			},"html");
		},500);*/
	});
});
</script>
</body>
</html>
