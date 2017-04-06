<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品成品设置</title>
    <link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/admin.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
</head>
<body>
<span class="select-box radius" style="width: 100px;">
    <select class="select" id="goods_categories">
        <option value="-1">全部分类</option>
    </select>
</span>
<input type="text" id="goods_info" placeholder="" style="width:260px" class="input-text radius">
<button id="goods_search" class="btn btn-success size-S"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
<table class="table table-border table-bordered table-bg table-hover table-sort table-striped box-shadow mb-40">
    <thead>
    <tr class="text-c">
        <th width="30">选择</th>
        <th width="100">序号</th>
        <th width="100">商品名称</th>
        <th width="100">条码</th>
        <th width="50">单位</th>
    </tr>
    </thead>
    <tbody id = "goodsBox"></tbody>
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
        $("#goodsBox").empty();
        //类别
        $.post("<%=request.getContextPath()%>/server/categories/categoriesList", function (data) {
            for(var n in data){
                $("#goods_categories").append("<option value = '" + data[n].id + "'>" + data[n].name + "</option>");
            }
        });

        $("#goods_search").on("click", function () {
            var cid = $("#goods_categories").val();
            var goodsName = $("#goods_info").val();
            if(cid == -1 && !goodsName){
                return false;
            }
            $("#goodsBox").empty();
            $.post("<%=request.getContextPath()%>/server/goods/goodsList", {cid : cid, goodsName : goodsName}, function(data){
                if(data.length > 0){
                    for(var n in data){
                        $("#goodsBox").append(
                            "<tr class=\"text-c\">" +
                            "<td><input type=\"radio\" value=\""+data[n].id+"\" name = \"goodsCb\" class=\"goodsCb\"></td>" +
                            "<td class='goodsId'>"+data[n].id+"</td>" +
                            "<td class='goodsName'>"+data[n].name+"</td>" +
                            "<td class='goodsNo'>"+data[n].goodsNo+"</td>" +
                            "<td class='mainUnitName'>"+(data[n].mainUnitName?data[n].mainUnitName:"")+"</td>" +
                            "</tr>"
                        );
                    }
                }else{
                    $("#goodsBox").append("<span class='PS'>没有符合条件的商品</span>");
                }
            });
        });


        //确认
        $("#ensure").on("click", function(){
             var parentTr = $("input[type='radio']:checked").parents("tr");
             parent.$("#targetGoodsName").val(parentTr.find(".goodsName").text());
             parent.$("#targetGoodsId").val(parentTr.find(".goodsId").text());
             parent.$("#targetGoodsNum").val(1);
             parent.$("#unitName").text(parentTr.find(".mainUnitName").text());
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

