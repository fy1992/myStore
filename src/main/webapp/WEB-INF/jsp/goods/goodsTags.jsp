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
	<tbody id = "goodsTagsList">
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
    $.post("<%=request.getContextPath()%>/server/goods/findAllGoodsTags", function(data){
        for(var n in data){
            $("#goodsTagsList").append(
                "<tr class=\"text-c\">" +
                "<td><input type=\"text\" class=\"input-text\" id = \"goodsTags_"+data[n].id+"\" value=\""+data[n].name+"\"></td>" +
                "<td>" +
                "<a class=\"btn btn-success size-MINI radius mr-15\" onclick=\"update("+data[n].id+")\">保存</a>" +
                "<a class=\"btn btn-danger size-MINI radius\" onclick=\"del("+data[n].id+")\">删除</a>" +
                "</td>" +
                "</tr>"
            );
        }
    });

	//添加标签
	$("#addxpj").click(function(){
		var labelName = $("#labelName").val();
		if(labelName){
            $.post("<%=request.getContextPath()%>/server/goods/addGoodsTags", {"name" : labelName}, function (data) {
                if(data.result == 1){
                    layer.msg("标签添加成功");
                    //添加成功之后执行以下代码
                    var tr = "<tr class=\"text-c\">"+
                        "<td><input type=\"text\" class=\"input-text\" id = \"goodsTags_" + data.object + "\" value=\""+ labelName +"\"></td>"+
                        "<td>"+
                        "<a class=\"btn btn-success size-MINI radius mr-15\" onclick=\"update(" + data.object + ")\">保存</a>"+
                        "<a class=\"btn btn-danger size-MINI radius\" onclick=\"del(" + data.object + ")\">删除</a>"+
                        "</td></tr>";
                    $("table tbody").append(tr);
                    $("#labelName").val("");
                }else{
                    layer.msg("该标签已存在")
                }
            });
		}else{
			layer.msg("请填写标签名称！");
		}
	});
});

//添加
function update(id){
    var name = $("#goodsTags_" + id).val();
    if(!name){
        layer.msg("标签名称不能为空");
        return false;
    }
    $.post("<%=request.getContextPath()%>/server/goods/editGoodsTags", {"id" : id, "name" : name}, function(data){
        layer.msg(data.msg);
        $("#goodsTags_" + id).val(name);
    });
}
//取消
function layer_close(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

//删除
function del(id){
    layer.msg('确定要删除该标签？', {
        time: 0 ,//不自动关闭
        btn: ['确定', '取消'],
        yes: function(index){
            $.post("<%=request.getContextPath()%>/server/goods/delGoodsTags", {"id" : id}, function(data){
                layer.msg(data.msg);
                layer_close();
            });
        }
    });
}
</script>
</body>
</html>
