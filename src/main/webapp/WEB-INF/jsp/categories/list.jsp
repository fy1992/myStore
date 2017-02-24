<%@page language="java"  pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="js/html5.js"></script>
<script type="text/javascript" src="js/respond.min.js"></script>
<![endif]-->
<link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
<link href="${ctxResource}/css/admin.css" rel="stylesheet" type="text/css" />
<link href="${ctxResource}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctxResource}/css/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctxResource}/css/zTreeStyle/zTreeStyle.css" type="text/css">

<title>分类列表</title>
</head>
<body class="pos-r">
<div class="pos-a" style="width:210px;left:0;top:0; bottom:0; height:1000px; overflow: auto; border-right:1px solid #e5e5e5; background-color:#f5f5f5">
	<ul id="categoriesTree" class="ztree">
	</ul>
	<input id="categories_pid" type="hidden"/>
</div>
<div style="margin-left:200px;">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品 <span class="c-gray en">&gt;</span> 商品分类 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <input id="categories_tableStart" type="hidden"/>
    <div class="text-r cl pl-20 pt-10 pb-10 box-shadow">
        <span class="l">
            <a href="javascript:void(0);" onclick="add();" class="btn btn-primary radius">新增</a>
        </span>
    </div>
    <div class="pd-20">
		<div>
			<table class="table table-border table-bordered table-bg table-hover table-sort table-striped box-shadow" id="categories_table">
				<thead>
					<tr class="text-c">
						<th width="50">序号</th>
						<th width="120">分类名称</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script> 
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctxResource}/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script> 
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="${ctxResource}/js/myself.js"></script>
<script type="text/javascript">
var table, tree;
var setting = {
	view: {
		selectedMulti: false,
		showLine: true,
		dblClickExpand: true
	},
    edit : {
	    drag : {
	        isCopy : false,
            isMove : true
        },
        removeTitle : "删除分类",
        renameTitle : "编辑分类",
        enable : true
    },
	data : {
	    keep : {
	        leaf : true,
            parent : true
        },
		simpleData:{
			enable:true,
			idKey:"id",
			pIdKey:"pid",
			rootPId:0
		},
		key:{
			name : "name"
		}
	},
	async:{
		enable : true, //设置 zTree 是否开启异步加载模式
		//autoparam:[],//异步加载时需要自动提交父节点属性的参数。[setting.async.enable = true 时生效]
		contentType : "application/json",//Ajax 提交参数的数据类型。[setting.async.enable = true 时生效]
		dataType : "json",//Ajax 获取的数据类型。[setting.async.enable = true 时生效]
		//otherParam:[],//Ajax 请求提交的静态参数键值对。[setting.async.enable = true 时生效]
		type : "post", //Ajax 的 http 请求模式。[setting.async.enable = true 时生效]
		url:"<%=request.getContextPath()%>/server/categories/tree"
	},
	callback: {
		onClick : onClick,
        onDrop: zTreeOnDrop,//拖拽结束后
        beforeRemove : zTreeBeforeRemove, //删除时
        onRename : ztreeOnRename, //编辑后
        onRemove : zTreeOnRemove //删除后
	}
};

function onClick(event, treeId, treeNode){
	var id = treeNode.id;
	$("#categories_pid").val(id);
	table.fnDraw();
}

function zTreeOnDrop(event, treeId, treeNodes, targetNode){
    $.post("<%=request.getContextPath()%>/server/categories/editCategories",
        {"id" : treeNodes[0].id, "pid" : targetNode.id}, function(data){
            tree.reAsyncChildNodes(null, "refresh");
            table.fnDraw();
            layer.msg(data.msg);
    });
}

function ztreeOnRename(event, treeId, treeNode, isCancel){
    if(!isCancel){
        $.post("<%=request.getContextPath()%>/server/categories/editCategories",
        {"id" : treeNode[0].id, "name" : treeNode.name}, function(data){
            tree.reAsyncChildNodes(null, "refresh");
            table.fnDraw();
            layer.msg(data.msg);
        });
    }
}

function zTreeBeforeRemove(treeId, treeNode){
    if(treeNode.name == "根目录'"){
        layer.msg("根节点无法删除");
        return false;
    }
    var msg = "";
    if(treeNode.children){
        msg = "确定要删除分类 : " + treeNode.name + "以及其子分类？";
    }else{
        msg = "确定要删除分类 : " + treeNode.name + "?";
    }
    return confirm(msg);
}

function zTreeOnRemove(event, treeId, treeNode){
    del(treeNode[0].id);
}

// Z-Tree 树生成结束
$(document).ready(function(){
    tree = $.fn.zTree.init($("#categoriesTree"), setting, null);

    table = $('#categories_table').dataTable({
        "bProcessing": true,//DataTables载入数据时，是否显示‘进度’提示
        "bPaginate": true,//是否显示（应用）分页器
        "bLengthChange": false,
        "bScrollCollapse" : true,//是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
        "bInfo" : true,//是否显示页脚信息，DataTables插件左下角显示记录数
        "bFilter" : false,//是否启动过滤、搜索功能
        "aoColumns" : [
            {"mData" : "id","sDefaultContent" : "", "sWidth":"2%","bSortable":false},//sDefaultContent 此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错
            {"mData" : "name","sDefaultContent" : "", "sWidth":"8%","bSortable":false}
        ],
        "language":{
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "末页"
            },
            "sLoadingRecords": "载入中...",
            "sEmptyTable": "表中数据为空",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            "sProcessing": "处理中..."
        },
        "order" : [[0, "desc"]],
        "sPaginationType": "full_numbers",//详细分页组，可以支持直接跳转到某页
        "iDisplayLength" : 20, //每页显示条数
        "bServerSide": true,
        "fnFormatNumber": function(iIn){
            return iIn;//格式化数字显示方式
        },
        "sAjaxSource" : "<%=request.getContextPath()%>/server/categories/list",
        //服务器端，数据回调处理
        "fnServerData" : function(sSource, aDataSet, fnCallback) {
            $.ajax({
                "dataType" : 'json',
                "type" : "post",
                "url" : sSource,
                "data": {
                    aDataSet : JSON.stringify(aDataSet)
                },
                "success" : fnCallback
            });
        },
        "fnServerParams" : function(aoData){
            var cid = $("#categories_pid").val();
            if(cid === "" || cid === undefined){
                cid = 0;
            }
            aoData.push({"name":"pid", "value":cid});
            var iDisplayStart = $("#categories_tableStart").val();
            if(!iDisplayStart){
                iDisplayStart = 0;
            }
            iDisplayStart = Number(iDisplayStart);
            aoData[3].value = iDisplayStart == 0 ? this.fnSettings()._iDisplayStart : iDisplayStart;
        },
        "fnDrawCallback" : function () {
            $('#redirect').keyup(function(e){
                var redirect = 0;
                if(e.keyCode==13){
                    if($(this).val() && $(this).val()>0){
                        redirect = $(this).val()-1;
                    }
                    table.fnPageChange(redirect);
                }
            });
        }
    });
});

function add(){
    var pid = $("#categories_pid").val();
    if(!pid){
        pid = 0;
    }
    layer_show("分类添加", "<%=request.getContextPath()%>/server/categories/addCategories/"+pid, "400", "220");
}

//删除
function del(id){
    $.post("<%=request.getContextPath()%>/server/categories/delCategories", {"id" : id}, function(data){
        layer.msg(data.msg);
        table.fnDraw();
        tree.reAsyncChildNodes(null, "refresh");
    });
}
</script>
</body>
</html>