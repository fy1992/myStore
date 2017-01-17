<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctxResource}/js/html5.js"></script>
    <script type="text/javascript" src="${ctxResource}/js/respond.min.js"></script>
    <script type="text/javascript" src="${ctxResource}/js/PIE_IE678.js"></script>
    <![endif]-->
    <link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/admin.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
    <title>添加分类</title>
</head>
<body>
<div class="pd-20">
    <div class="form form-horizontal" id="form-member-add">
        <div class="row cl">
            <label class="form-label col-5"><span class="c-red"></span>父分类：</label>
            <div class="formControls col-5">
				<span class="select-box">
					<select name="" class="select" id="categoriesParent"></select>
				</span>
            </div>
            <div class="col-2"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">* </span>名称：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="" placeholder="请输入分类名称" id="name" style = "width:307px">
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <div class="col-9 col-offset-3 mt-20">
                <input class="btn btn-primary radius" type="button" id="recommendBtn" value="&nbsp;&nbsp;确认&nbsp;&nbsp;">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript">
    $(function () {
        $.post("<%=request.getContextPath()%>/categories/tree", function(data){
            for(var i in data){
                $("#categoriesParent").append("<option value = " + data[i].id + ">" + data[i].name + "</option>");
            }
        })
    });
    $("#recommendBtn").click(function(){
        if(check()){
            $.ajax({
                url : "<%=request.getContextPath()%>/categories/add",
                data : {
                    "pid" : $("#categoriesParent").find("option:selected").val(),
                    "name" : $("#name").val()
                },
                type : "post",
                success : function(data){
                    layer.msg(data.msg, {time : 1500}, function(){
                        if(data.result == 1){
                            window.parent.table.fnDraw();
                            layer_close();
                        }
                    });
                }
            });
        }
    });

    function check(){
        var name = $("#name").val();
        if(!name){
            layer.msg("名称不能为空");
            return false
        }
        return true;
    }

    function layer_close(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
</script>
</body>
</html>