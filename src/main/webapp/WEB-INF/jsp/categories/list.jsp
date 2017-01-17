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
						<th width="50">操作</th>
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
var setting = {
	view: {
		selectedMulti: false,
		showLine: true,
		dblClickExpand: true
	},
	data : {
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
		dataFilter : ajaxDataFilter,//用于对 Ajax 返回数据进行预处理的函数。[setting.async.enable = true 时生效]
		dataType : "json",//Ajax 获取的数据类型。[setting.async.enable = true 时生效]
		//otherParam:[],//Ajax 请求提交的静态参数键值对。[setting.async.enable = true 时生效]
		type : "post", //Ajax 的 http 请求模式。[setting.async.enable = true 时生效]
		url:"<%=request.getContextPath()%>/categories/tree"
	},
	callback: {
		onClick : onClick 
	}
};
	
$(document).ready(function(){
	$.fn.zTree.init($("#categoriesTree"), setting, null);
});

function onClick(event, treeId, treeNode){
	var id = treeNode.id;
	$("#categories_pid").val(id);
	table.fnDraw();
}

function ajaxDataFilter(treeId, parentNode, childNodes){
	if (!childNodes) return null;
	for (var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}

// Z-Tree 树生成结束

table = $('#categories_table').dataTable({
	"bProcessing": true,//DataTables载入数据时，是否显示‘进度’提示  
    "bPaginate": true,//是否显示（应用）分页器  
    "bLengthChange": false,
    "bScrollCollapse" : true,//是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
    "bInfo" : true,//是否显示页脚信息，DataTables插件左下角显示记录数 
    "bFilter" : false,//是否启动过滤、搜索功能
    "aoColumns" : [
      {"mData" : "id","sDefaultContent" : "", "sWidth":"2%","bSortable":false},//sDefaultContent 此列默认值为""，以防数据中没有此值，DataTables加载数据的时候报错  
      {"mData" : "name","sDefaultContent" : "", "sWidth":"8%","bSortable":false, "mRender":function(data, type, full){
          return "<div class='editDiv' tagId = '"+full.id+"'>" + data + "</div>";
      }},
      {"mData" : "","sDefaultContent" : "","mRender":function(data, type, full){
    	  return "<a style='text-decoration:none' class='ml-5 label label-primary radius' onClick='del(\""+full.id+"\")' href='javascript:void(0);'>删除</a>";
      },"sWidth":"2%","bSortable":false}],
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
    "sAjaxSource" : "<%=request.getContextPath()%>/categories/list",
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
  	  aoData.push({"name":"bNeedPaging", "value":true});
  	  var cid = $("#channel_pid").val();
  	  if(cid === "" || cid === undefined){
  		  cid = -1;
  	  }
  	  aoData.push({"name":"pid","value":cid});
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

function add(){
    var pid = $("#categories_pid").val();
    if(!pid){
        layer.msg("请选择具体父节点");
        return false;
    }

}

</script>
</body>
</html>