<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品配方设置</title>
    <link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/admin.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
</head>
<body>
<span class="select-box radius" style="width: 100px;">
    <select class="select" id="raw_categories">
        <option value="-1">全部分类</option>
    </select>
</span>
<input type="text" id="raw_info" placeholder="" style="width:260px" class="input-text radius">
<button id="raw_search" class="btn btn-success size-S"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
<table class="table table-border table-bordered table-bg table-hover table-sort table-striped box-shadow mb-40">
    <thead>
    <tr class="text-c">
        <th width="30">选择</th>
        <th width="100">序号</th>
        <th width="100">原材料名称</th>
        <th width="100">条码</th>
        <th width="50">单位</th>
    </tr>
    </thead>
    <tbody id = "rawBox"></tbody>
</table>
<div class="cfpdBtnbox">
    <a class="btn btn-primary size-M f-r pl-20 pr-20" id="ensure">确认</a>
    <a class="btn btn-default size-M f-r" onclick="layer_close()">返回</a>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctxResource}/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctxResource}/js/myself.js"></script>
<script>
    $(function(){
        $("#rawBox").empty();
        //类别
        $.post("<%=request.getContextPath()%>/server/categories/categoriesList", function (data) {
            for(var n in data){
                $("#raw_categories").append("<option value = '" + data[n].id + "'>" + data[n].name + "</option>");
            }
        });

        $("#raw_search").on("click", function () {
            var cid = $("#raw_categories").val();
            var rawName = $("#raw_info").val();
            if(cid == -1 && !rawName){
                return false;
            }
            $("#rawBox").empty();
            $.post("<%=request.getContextPath()%>/server/raw/rawList", {cid : cid, rawName : rawName}, function(data){
                if(data.length > 0){
                    for(var n in data){
                        $("#rawBox").append(
                            "<tr class=\"text-c\">" +
                            "<td><input type=\"checkbox\" value=\""+data[n].id+"\" name = \"rawCb\" class=\"rawCb\"></td>" +
                            "<td class='rawId'>"+data[n].id+"</td>" +
                            "<td class='rawName'>"+data[n].name+"</td>" +
                            "<td class='rawNo'>"+data[n].rawNo+"</td>" +
                            "<td class='mainUnitName'>"+data[n].mainUnitName+"<input type='hidden' value='"+data[n].mainUnitId+"' class='mainUnitId'></td>" +
                            "</tr>"
                        );
                    }
                }else{
                    $("#rawBox").append("<span class='PS'>没有符合条件的原材料</span>");
                }
            });
        });


        //确认
        $("#ensure").on("click", function(){
            var n = $("input[type = 'checkbox']:checked").length;
            var stsids = [], ids = [];
            for(var i = 0; i < n; i++){
                stsids.push($("input[type = 'checkbox']:checked").eq(i).val());
            }
            var exIds = parent.$("#check").val();
            if(exIds){
                ids = exIds.split(",");
            }
            for(var i = 0; i < n; i++){
                var mark = stsids[i] - 1;
                if($.inArray($(".rawNo").eq(mark).text(), ids) == -1){
                    parent.$("#rawList").append("<tr class=\"text-c\">" +
                        "<td><span type=\"text\" class=\"rawName\">"+$(".rawName").eq(mark).text()+"</span></td>" +
                        "<td><span type=\"text\" class=\"rawNo\">"+$(".rawNo").eq(mark).text()+"</span><input type = 'hidden' value = '"+$(".rawId").eq(mark).text()+"' class=\"rawId\"/></td>" +
                        "<td><input type='text' class=\"input-text rawNum\" value = \"1\"/></td>" +
                        "<td><span type='text' class=\"goodsUnitName\">"+$(".mainUnitName").eq(mark).text()+"</span><input type='hidden' class='goodsUnitId' value ='"+$(".mainUnitId").eq(mark).val()+"'></td>" +
                        "<td><a class=\"btn btn-danger size-MINI radius\" onclick=\"del("+$(".rawId").eq(mark).text()+", this)\">删除</a></td>" +
                        "</tr>");
                    ids.push($(".rawNo").eq(mark).text());
                }
            }
            parent.$("#check").val(ids);
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

