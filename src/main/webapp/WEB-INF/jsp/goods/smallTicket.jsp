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
<table class="table table-border table-bordered table-bg box-shadow" id="xpjgl">
	<thead>
		<tr class="text-c">
			<th>名称</th>
			<th width="170">类型</th>
			<th width="60">删除</th>
		</tr>
	</thead>
	<tbody id = "smallTicketList">
	</tbody>
</table>
<p><a class="btn border-grey block mt-20 mb-40" id="addxpj">+ 添加小票机</a></p>
<div class="cfpdBtnbox">
	<a class="btn btn-primary size-M f-r" id="save">保存</a>
	<a class="btn btn-default size-M f-r" onclick="layer_close()">取消</a>
</div>

<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script>
$(function(){
    $.post("<%=request.getContextPath()%>/goods/findAllSmallTicket", function(data){
        for(var n in data){
            $("#smallTicketList").append("<tr class=\"text-c\">" +
                "<td><input type=\"text\" class=\"input-text smallTicketName\" value=\""+data[n].name+"\"></td>" +
                "<td>" + "<input type = 'hidden' value = '"+data[n].id+"' class=\"smallTicketId\"/>" +
                "<span class=\"select-box\">" +
                "<select class=\"select smallTicketType\">" +
                "<option value=\"0\">一品一切</option>" +
                "<option value=\"1\">一单一切</option>" +
                "</select>" +
                "</span>" +
                "</td>" +
                "<td><a class=\"btn btn-danger size-MINI radius\" onclick=\"del("+data[n].id+")\">删除</a></td>" +
                "</tr>");

            $(".smallTicketType").eq(n).val(data[n].type);
        }
    });

	//添加小票机
	$("#addxpj").click(function(){
		var tr = "<tr class=\"text-c\">"+
				"<td><input type=\"text\" class=\"input-text smallTicketName\" value=\"\"></td>"+
				"<td>"+
                    "<input type = 'hidden' class=\"smallTicketId\" value='0'/>" +
					"<span class=\"select-box\">"+
						"<select class=\"select smallTicketType\">"+
							"<option value='0'>一品一切</option>"+
							"<option value='1'>一单一切</option>"+
						"</select>"+
					"</span>"+
				"</td>"+
				"<td><a class=\"btn btn-danger size-MINI radius\" onclick=\"del(this)\">删除</a></td>"+
			"</tr>";

		var n = $("#xpjgl tbody input[type='text']").length;

		if(n > 0){
			$("#xpjgl tbody input[type='text']").each(function(index){
				var thisVal = $.trim($(this).val());
				if(!thisVal){
					layer.msg("当前列表中有小票机名称未填写，请确认！",{time:2000});
					$(this).attr("autofocus");
					return false;
				}else if(index==(n-1)) {
					$("table tbody").append(tr);
					$("table tbody tr:last-child input").attr("autofocus","autofocus");
				}
			});
		}else{
			$("table tbody").append(tr);
			$("table tbody tr:last-child input").attr("autofocus","autofocus");
		}
	});
	
	//保存
	$("#save").click(function(){
		var n = $("#xpjgl tbody input[type='text']").length;
		if(n > 0){
			$("#xpjgl tbody input[type='text']").each(function(index){
				var thisVal = $.trim($(this).val());
				if(!thisVal){
					layer.msg("当前列表中有小票机名称未填写，请确认！",{time:2000});
					$(this).attr("autofocus");
					return false;
				}
			});
            var smallTicketArr = [];
            function smallTicket(name, type, id){
                this.name = name;
                this.type = type;
                this.id = id;
            }
            for(var i = 0; i < n; i++){
                smallTicketArr.push(new smallTicket(
                    $(".smallTicketName").eq(i).val(),
                    $(".smallTicketType").eq(i).val(),
                    $(".smallTicketId").eq(i).val()
                ));
            }
            $.post("<%=request.getContextPath()%>/goods/addSmallTicket", {"smallTicketList" : JSON.stringify(smallTicketArr)}, function (data) {
                layer.msg(data.msg);
                layer_close();
            });
		}else{
			$.get("<%=request.getContextPath()%>/goods/goodsTagsSelect", function(html){
				$("body").html(html);
			},"html");
		}
	});
});

//取消
function layer_close(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

//删除
function del(id){
    layer.msg('确定要删除该小票？', {
        time: 0 ,//不自动关闭
        btn: ['确定', '取消'],
        yes: function(index){
            $.post("<%=request.getContextPath()%>/goods/delSmallTicket", {"id" : id}, function(data){
                layer.msg(data.msg);
            });
        }
    });
}
</script>
</body>
</html>
