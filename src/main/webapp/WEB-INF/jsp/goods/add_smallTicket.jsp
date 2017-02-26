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
	<tbody id = "smallTicketBox">
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
    $.post("<%=request.getContextPath()%>/server/goods/findAllSmallTicket", function(data){
        for(var n in data){
            $("#smallTicketBox").append(
                "<tr class=\"text-c\">" +
                "<td><input type=\"checkbox\" value=\""+data[n].id+"\" name = \"smallTicketCb\" class=\"smallTicketCb\">" +
                "</td>" +
                "<td>"+data[n].id+"</td>" +
                "<td>"+data[n].name+"</td>" +
                "<td>"+ (data[n].type == 1  ? "一单一切" : "一品一切") +"</td>" +
                "</tr>"
            );
        }
        var result = "${stsIds}";
        if(result && result != 0){
            var resultList = result.split(",");
            $(".smallTicketCb").each(function () {
                if($.inArray($(this).val(), resultList) != -1){
                    $(this).attr("checked", true);
                }
            })
        }
    });

	//管理小票机
	$("#xpj").click(function(){
		$.get("<%=request.getContextPath()%>/server/goods/smallTicket",function(html){
			$("body").html(html);
		},"html");
	});
	//确认
	$("#ensure").click(function(){
        var n = $("input[type = 'checkbox']:checked").length;
        var stsids = [];
        for(var i = 0; i < n; i++){
            stsids.push($("input[type = 'checkbox']:checked").eq(i).val());
        }
        parent.$("#smallTicketNum").text(n);
        parent.$("#smallTicketNum").append("<input type = 'hidden' value = '"+stsids.toString()+"' id = 'stsIds' name = 'smallTickets'>");
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
