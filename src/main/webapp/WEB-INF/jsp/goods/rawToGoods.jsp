<%@page language="java" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品原材料设置</title>
    <link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/admin.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="row cl mb-5" style="padding:5px;">
    <div class="col-4">
        <img src="${goods.imgUrl}" style="width: 145px"/>
    </div>
    <div class="col-8">
        <div class="row cl">
            <label class="form-label col-3">品名 ：</label>
            <div class="formControls col-6">
                <span>${goods.name}</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">条码 ： </label>
            <div class="formControls col-6">
                <span>${goods.goodsNo}</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">售价 ： </label>
            <div class="formControls col-6">
                <span>${goods.price} 元 <c:if test="${not empty goods.mainUnitName}">/${goods.mainUnitName}</c:if></span>
            </div>
        </div>
    </div>
</div>
<div class="row cl mb-5" style="border-top: 1px;border-top-style: dashed;border-color:#999;">
    <label class="form-label col-4">
        <input type="checkbox" id="updatePriceByRaw" style="position: absolute;left: 45px;top: 11px;"/>
    </label>
    <div class="row col-2"></div>
    <div class="formControls col-6">
        <span style="font-size: 12px;color: #999;text-align: left;">使用配方成本价更新成品进货价</span>
    </div>
</div>
<table class="table table-border table-bordered table-bg box-shadow" id="xpjgl">
    <thead>
    <tr class="text-c">
        <th width="100">原材料</th>
        <th width="100">编码</th>
        <th width="170">数量</th>
        <th width="60">单位</th>
        <th width="60">删除</th>
    </tr>
    </thead>
    <tbody id = "rawList">
    </tbody>
</table>
<input type = "hidden" id = "check" value=""/>
<p><a class="btn border-grey block mt-20 mb-40" id="addRaw">+ 添加原材料</a></p>
<div>
    <div class="row cl" style="margin: 10px;">
        <div class="row cl col-1"></div>
        <div class="row cl col-4">
            半成品
        </div>
        <div class="row cl col-3"></div>
        <div class="row cl clo-4">
            <input type="radio" name = "semifinished"  value = "1" id = "semifinished1" <c:if test="${goods.semifinished eq 1}">checked</c:if>/> <label for="semifinished1">是</label>
            <input type="radio" name = "semifinished"  value = "0" id = "semifinished2" <c:if test="${goods.semifinished eq 0}">checked</c:if>/> <label for="semifinished2">否</label>
        </div>
    </div>
    <div class="row cl" style="margin: 10px;display: none;" id="semifinishedDiv">
        <div class="row cl col-1"></div>
        <div class="row cl col-4">
            是否直接转化为成品
        </div>
        <div class="row cl col-3"></div>
        <div class="row cl clo-4">
            <input type="radio" name = "autoFinished"  value = "1" id = "autoFinished1" <c:if test="${goods.autoFinished eq 1}">checked</c:if>/> <label for="autoFinished1">是</label>
            <input type="radio" name = "autoFinished"  value = "0" id = "autoFinished2" <c:if test="${goods.autoFinished eq 0}">checked</c:if>/> <label for="autoFinished2">否</label>
        </div>
    </div>
    <div class="row cl" id="targetGoodsDiv">
        <table class="table table-border table-bordered table-bg box-shadow" >
            <thead>
                <tr class="text-c">
                    <th>制作成品</th>
                    <th>制作数量</th>
                    <th>单位</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <input style="width:160px;cursor: pointer;" class="input-text radius" id="targetGoodsName" value = "${goods.targetGoodsName}" type = "text"/>
                        <input  id="targetGoodsId" type = "hidden" value = "${goods.targetGoodsId}"/>
                    </td>
                    <td><input style="width:100px" class="input-text radius" id="targetGoodsNum" value = "${goods.targetGoodsNum}" type = "number" min="0"/></td>
                    <td><span id="unitName">${mainUnitName}</span></td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="row cl" style="height: 32px;">
    </div>
</div>
<div class="cfpdBtnbox">
    <a class="btn btn-primary size-M f-r" id="save">保存</a>
    <a class="btn btn-default size-M f-r" onclick="layer_close()">取消</a>
</div>

<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
<script>
    $(function(){
        $("#targetGoodsName").on("click", function () {
            layer.open({
                type: 2,
                area: ['490px', '460px'],
                fix: true, //不固定
                title: false,
                shadeClose: false,
                shade: false,
                closeBtn: 0,
                content: "<%=request.getContextPath()%>/server/goods/targetGoodsList"
            });
        });
        if($("input[name='semifinished']:checked").val() == 1){
            $("#semifinishedDiv").toggle(true);
            $("#targetGoodsDiv").toggle(false);
        }else{
            $("#semifinishedDiv").toggle(false);
            $("#targetGoodsDiv").toggle(false);
        }
        if($("input[name='autoFinished']:checked").val() == 1){
            $("#targetGoodsDiv").toggle(true);
        }else{
            $("#targetGoodsDiv").toggle(false);
        }
        $("input[name='semifinished']").on("click", function () {
            if($(this).val() == 1){
                $("#semifinishedDiv").toggle(true);
                $("#targetGoodsDiv").toggle(false);
            }else{
                $("#semifinishedDiv").toggle(false);
                $("#targetGoodsDiv").toggle(false);
                $("input[name='autoFinished']").each(function () {
                    if($(this).val() == 0){
                        $(this).prop("checked", true);
                    }
                })
            }
        });
        $("input[name='autoFinished']").on("click", function () {
            if($(this).val() == 1){
                $("#targetGoodsDiv").toggle(true);
            }else{
                $("#targetGoodsDiv").toggle(false);
            }
        });
        
        $.post("<%=request.getContextPath()%>/server/raw/goodsRawItemList", {id : "${id}"}, function(data){
            var initCheck = [];
            for(var n in data){
                $("#rawList").append("<tr class=\"text-c\">" +
                    "<td><span type=\"text\" class=\"rawName\">"+data[n].rawName+"</span></td>" +
                    "<td><span type=\"text\" class=\"rawNo\">"+data[n].rawNo+"</span><input type = 'hidden' value = '"+data[n].rawId+"' class=\"rawId\"/></td>" +
                    "<td><input type='text' class=\"input-text rawNum\" value = \""+data[n].rawNum+"\"/></td>" +
                    "<td><span type='text' class=\"goodsUnitName\">"+data[n].goodsUnitName+"</span><input type='hidden' class='goodsUnitId' value ='"+data[n].goodsUnitId+"'></td>" +
                    "<td><a class=\"btn btn-danger size-MINI radius\" onclick=\"del("+data[n].id+", this)\">删除</a></td>" +
                    "</tr>");
                $(".rawType").eq(n).val(data[n].type);
                initCheck.push(data[n].rawNo);
            }
            $("#check").val(initCheck);
            if("${goods.useRawPrice}" == 1){
                $("#updatePriceByRaw").prop("checked", true);
            }
        });

        //添加配方
        $("#addRaw").click(function(){
            layer.open({
                type: 2,
                area: ['490px', '460px'],
                fix: true, //不固定
                title: false,
                shadeClose: false,
                shade: false,
                closeBtn: 0,
                content: "<%=request.getContextPath()%>/server/raw/rawToGoodsList/${id}"
            });
        });

        //保存
        $("#save").click(function(){
            var len = $(".text-c").length;
            var rawItems = [];
            for(var i = 0; i < len - 2; i++){
                var rawItem = new RawItem(
                    "${id}",
                    $(".rawNum").eq(i).val(),
                    $(".rawId").eq(i).val(),
                    $(".rawName").eq(i).text(),
                    $(".rawNo").eq(i).text(),
                    $(".goodsUnitId").eq(i).val(),
                    $(".goodsUnitName").eq(i).text()
                );
                rawItems.push(rawItem);
            }
            var useRawPrice = 0;
            if($("#updatePriceByRaw").prop("checked")){
                useRawPrice = 1;
            }
            var semifinished = $("input[name='semifinished']:checked").val();
            var autoFinished = $("input[name='autoFinished']:checked").val();
            if(!autoFinished){
                autoFinished = 0;
            }
            if(rawItems.length == 0 && autoFinished == 1){
                layer.msg("请添加原材料",{time : 2000, icon : 5});
                return false;
            }
            var targetGoodsId = 0;
            var targetGoodsName = "";
            var targetGoodsNum = 0;
            if(autoFinished == 1){
                targetGoodsId = $("#targetGoodsId").val();
                targetGoodsName = $("#targetGoodsName").val();
                targetGoodsNum = $("#targetGoodsNum").val();
            }

            $.post("<%=request.getContextPath()%>/server/raw/addRawItem", {
                id : "${id}",
                rawItems:JSON.stringify(rawItems),
                useRawPrice:useRawPrice,
                semifinished : semifinished,
                autoFinished : autoFinished,
                targetGoodsId : targetGoodsId,
                targetGoodsName : targetGoodsName,
                targetGoodsNum : targetGoodsNum
            },
                function (data) {
                if(data.result == 1){
                    window.parent.table.fnDraw();
                    layer.msg(data.msg, {time : 2000, icon : 6}, function () {
                        layer_close();
                    });
                }else{
                    layer.msg(data.msg, {time : 2000, icon : 5});
                }
            });
        });
    });

    //取消
    function layer_close(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }

    //删除
    function del(id, obj){
        layer.msg('确定要删除该原材料？', {
            time: 0 ,//不自动关闭
            btn: ['确定', '取消'],
            yes: function(){
                $(obj).parents("tr").remove();
                var index = layer.alert();
                layer.close(index);
            }
        });
    }

    function RawItem(goodsId, rawNum, rawId, rawName, rawNo, goodsUnitId, goodsUnitName) {
        this.goodsId = goodsId;
        this.rawNum = rawNum;
        this.rawId = rawId;
        this.rawName = rawName;
        this.goodsUnitId = goodsUnitId;
        this.goodsUnitName = goodsUnitName;
        this.rawNo = rawNo;
    }
</script>
</body>
</html>


