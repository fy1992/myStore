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
<div class="box-shadow mb-40 pd-20 clearfixs" id="ckBox">
	<br clear="all" />
</div>
<div class="cfpdBtnbox">
	<a class="btn btn-success size-M layui-layer-close layui-layer-close1" id="tags">管理标签</a>
	<a class="btn btn-primary size-M f-r pl-20 pr-20" id="ensure">确认</a>
</div>

<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script>
$(function(){
    $.post("<%=request.getContextPath()%>/server/goods/findAllGoodsTags", function(data){
        for(var n in data){
            $("#ckBox").append(
                "<label><input type=\"checkbox\" name=\"ck1\" value = '"+data[n].id+"'/>"+data[n].name+"</label>"
            );
        }
        $("#ckBox").append("<br clear=\"all\"/>");
        var result = "${tagsIds}";
        if(result && result != 0){
            var tagslist = result.split(",");
            $("input[type='checkbox']").each(function() {
                if($.inArray($(this).val(), tagslist) != -1){
                    $(this).attr("checked", true);
                }
            });
        }
    });

	//管理标签
	$("#tags").click(function(){
		$.get("<%=request.getContextPath()%>/server/goods/goodsTags",function(html){
			$("body").html(html);
		},"html");
	});
	//确认
	$("#ensure").click(function(){
        var tagsIdList = [];
        var tagsNameList = [];
	    for(var i = 0, len = $("input[type='checkbox']:checked").length; i < len; i++){
            tagsIdList.push($("input[type='checkbox']:checked").eq(i).val());
            tagsNameList.push($("input[type='checkbox']:checked").parent().eq(i).text());
        }
        if(tagsIdList.length > 0 && tagsNameList.length > 0){
            parent.$("#showGoodsTags").empty();
            var tagsIds = [];
            for(var n in tagsIdList){
                parent.$("#showGoodsTags").append(
                    "<a class=\"btn btn-primary size-MINI mr-5\">"+tagsNameList[n]+"</a>"
                );
				tagsIds.push(tagsIdList[n]);
            }
            parent.$("#showGoodsTags").append(
                "<input type = 'hidden' value = '"+tagsIds+"' id = 'tagsIds' name ='goodsTagss'/>"
            );
        }else{
            parent.$("#showGoodsTags").empty();
		}
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
