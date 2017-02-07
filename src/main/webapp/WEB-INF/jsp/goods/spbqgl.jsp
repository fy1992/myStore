<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>商品标签设置</title>
	<link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/admin.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table class="table table-border table-bordered table-bg box-shadow" id="xpjgl">
	<thead>
		<tr class="text-c">
			<th>名称</th>
			<th width="150">操作</th>
		</tr>
	</thead>
	<tbody>
		<tr class="text-c">
			<td><input type="text" class="input-text" value="aaa"></td>
			<td>
				<a class="btn btn-success size-MINI radius mr-15" onclick="xpj_add(this,'10001')">保存</a>
				<a class="btn btn-danger size-MINI radius" onclick="xpj_del(this,'10001')">删除</a>
			</td>
		</tr>
		<tr class="text-c">
			<td><input type="text" class="input-text" value="bbb"></td>
			<td>
				<a class="btn btn-success size-MINI radius mr-15" onclick="xpj_add(this,'10001')">保存</a>
				<a class="btn btn-danger size-MINI radius" onclick="xpj_del(this,'10001')">删除</a>
			</td>
		</tr>
	</tbody>
</table>
<div class="border-grey pt-10 pb-10 pl-20 pr-20 mt-20 mb-40 clearfix text-c">
	<input type="text" class="input-text" id="labelName" placeholder="请输入标签名称" />
	<a class="btn btn-default size-M" id="addxpj">+ 添加标签</a>
</div>

<div class="cfpdBtnbox">
	<a class="btn btn-default size-M f-r" onclick="layer_close()">取消</a>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script>
$(function(){
	//添加标签
	$("#addxpj").click(function(){
		var labelName = $("#labelName").val();
		if(labelName){
			//添加成功之后执行以下代码
			var tr = "<tr class=\"text-c\">"+
				"<td><input type=\"text\" class=\"input-text\" value=\""+ labelName +"\"></td>"+
				"<td>"+
					"<a class=\"btn btn-success size-MINI radius mr-15\" onclick=\"xpj_add(this,'10001')\">保存</a>"+
					"<a class=\"btn btn-danger size-MINI radius\" onclick=\"xpj_del(this,'10001')\">删除</a>"+
				"</td></tr>";
			$("table tbody").append(tr);
			$("#labelName").val("");
			
		}else{
			layer.msg("请填写标签名称！");
		}
	});
	
	
	
});
//取消
function layer_close(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
//删除
function xpj_del(obj,id){
	layer.msg('确定要删除吗？', {
		time: 0 ,//不自动关闭
		btn: ['确定', '取消'],
		yes: function(index){
//				$.post(
//					"url",
//					{"id" : id},
//					function(data){
//						
//					}
//				);
			$(obj).parents("tr").remove();
			layer.msg('已删除!',{time:1000});
					
		}
	});
}
</script>

</body>
</html>
