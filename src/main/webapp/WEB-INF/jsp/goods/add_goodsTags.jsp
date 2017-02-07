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
<div class="box-shadow mb-40 pd-20 clearfixs" id="ckBox">
	<!--<label><input type="checkbox" name="ck1" />aaa</label>
	<label><input type="checkbox" name="ck1" />aaa</label>
	<label><input type="checkbox" name="ck1" />aaa</label>
	<label><input type="checkbox" name="ck1" />aaa</label>
	<label><input type="checkbox" name="ck1" />aaa</label>
	<label><input type="checkbox" name="ck1" />aaa</label>-->
	<br clear="all" />
</div>
<div class="cfpdBtnbox">
	<a class="btn btn-success size-M layui-layer-close layui-layer-close1" id="xpj">管理标签</a>
	<a class="btn btn-primary size-M f-r pl-20 pr-20" id="ensure">确认</a>
</div>

<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script>
$(function(){
	//管理小票机
	$("#xpj").click(function(){
		$.get("spbqgl",function(html){
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
