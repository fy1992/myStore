<%@page language="java" pageEncoding="utf-8"%>
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
<script type="text/javascript" src="${ctxResource}/js/html5.js"></script>
<script type="text/javascript" src="${ctxResource}/js/respond.min.js"></script>
<![endif]-->
<link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
<link href="${ctxResource}/css/admin.css" rel="stylesheet" type="text/css" />
<link href="${ctxResource}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctxResource}/css/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />

<title>商品列表</title>
</head>
<body class="pos-r">
<div>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品 <span class="c-gray en">&gt;</span> 商品资料 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="clearfix">
        <div class="text-r cl pl-20 pt-10 pb-10 box-shadow">
            <span class="l">
                <a href="javascript:void(0);" onclick="add();" class="btn btn-primary radius">新增</a>
                <a href="javascript:void(0);" onclick="importIn();" class="btn btn-primary radius">导入</a>
                <a href="javascript:void(0);" onclick="importOut();" class="btn btn-primary radius">导出</a>
                <a href="javascript:void(0);" onclick="unitDetail();" class="btn btn-primary radius">单位</a>
                <a href="javascript:void(0);" onclick="smallTicketDetail();" class="btn btn-primary radius">厨打</a>
                <a href="javascript:void(0);" onclick="tagsDetail();" class="btn btn-primary radius">标签</a>
            </span>
            <span class="select-box" style="width: 100px;">
                <select class="select radius" id="goods_status">
                    <option value="1">启用</option>
                    <option value="0">禁用</option>
                </select>
            </span>
            <span class="select-box radius" style="width: 100px;">
                <select class="select" id="goods_categories">
                    <option value="-1">全部分类</option>
                </select>
            </span>
            <span class="select-box radius" style="width: 110px;">
                <select class="select" id="goods_supplier">
                    <option value="-1">全部供应商</option>
                </select>
            </span>
            <span class="select-box radius" style="width: 100px;">
                <select class="select" id="goods_tags">
                    <option value="-1">全部标签</option>
                </select>
            </span>
            <input type="text" id="goods_info" placeholder="条码/名称/拼音码" style="width:260px" class="input-text radius">
            <button id="news_search" class="btn btn-success"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
        </div>
        <div class="pd-20 clearfix">
            <table class="table table-border table-bordered table-bg table-hover table-striped box-shadow" id="goods_table">
                <thead>
                    <tr class="text-c">
                        <th width="50">序号</th>
                        <th width="50">操作</th>
                        <th width="100">商品名称</th>
                        <th width="50">条码</th>
                        <th width="50">拼音码</th>
                        <th width="50">分类</th>
                        <th width="50">库存</th>
                        <th width="80">主单位</th>
                        <th width="50">进货价</th>
                        <th width="50">销售价</th>
                        <th width="50">批发价</th>
                        <th width="50">会员价</th>
                        <th width="30">会员折扣</th>
                        <th width="30">供货商</th>
                        <th width="50">生产日期</th>
                        <th width="30">保质期</th>
                    </tr>
                </thead>
                <tbody id="table_tr"></tbody>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script> 
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctxResource}/js/myself.js"></script>
<script type="text/javascript">
//搜索
$(function(){
	$("#news_search").click(function(){
		table.fnDraw();
	});

	$.post("<%=request.getContextPath()%>/server/categories/categoriesList", function (data) {
	    for(var n in data){
            $("#goods_categories").append("<option value = '" + data[n].id + "'>" + data[n].name + "</option>");
        }
    });

    $.post("<%=request.getContextPath()%>/server/supplier/allSupplier", function (data) {
        for(var n in data){
            $("#goods_supplier").append("<option value = '" + data[n].id + "'>" + data[n].name + "</option>");
        }
    });

    $.post("<%=request.getContextPath()%>/server/goods/allSupplier", function (data) {
        for(var n in data){
            $("#goods_supplier").append("<option value = '" + data[n].id + "'>" + data[n].name + "</option>");
        }
    });
});

//table start here
table = $('#goods_table').dataTable({
	   "bProcessing": true,//DataTables载入数据时，是否显示‘进度’提示  
       "bPaginate": true,//是否显示（应用）分页器  
       "bLengthChange": false,
       "bAutoWidth" : true,
       "bScrollCollapse" : true,//是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
       "bDestroy" : true,
       "bInfo" : true,//是否显示页脚信息，DataTables插件左下角显示记录数 
       "bFilter" : false,//是否启动过滤、搜索功能
       "aoColumns" : [
	  	{"mData" : null, "sDefaultContent" : "", "sClass":"center", "bSortable":false},
	  	{"mData" : "", "sDefaultContent" : "", "sClass":"center", "bSortable":false, "mRender":function(data, type, full){
	       var btn ="<a style='text-decoration:none' onclick='edit(\""+full.id+"\")'>编辑</a>";
	       btn += "&nbsp;<a style='text-decoration:none' onclick='del(\""+full.id+"\")'>删除</a>";
	       return btn;
        }},
        {"mData" : "name", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "goodsNo", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "pinyin", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "categoriesName", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "goodsNum", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "mainUnit", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "bid", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "price", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "tradePrice", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "vipPrice", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "isVipSet", "sDefaultContent" : "", "mRender":function(data, type, full){
        	return data == 1?"是":"否";
        	},"bSortable":false,"sClass":"center"
        },
        {"mData" : "supplier", "sDefaultContent" : "","bSortable":false,"sClass":"center"},
        {"mData" : "productionDate", "sDefaultContent" : "", "mRender":function(data, type, full){
               return format(data);
           },"bSortable":false,"sClass":"center"
        },
	    {"mData" : "shelfLife", "sDefaultContent" : "", "mRender":function(data, type, full){
            return data + " 天";
		   },"bSortable":false,"sClass":"center"
	    }
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
        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "sProcessing": "处理中..."
   	},
   	//"deferRender": true, //当处理大数据时，延迟渲染数据，有效提高Datatables处理能力
       "order" : [[1, "desc"]],
       "iDisplayLength" : 20, //每页显示条数
       //"iDisplayStart": 0,
       "bServerSide": true,
       "fnFormatNumber": function(iIn){
       	    return iIn;//格式化数字显示方式
       },
       "sAjaxSource" : "<%=request.getContextPath()%>/server/goods/list",
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
    "fnServerParams" : function(aoData){  //那个函数是判断字符串中是否含有数字
      	var status = $("#goods_status").val();
      	var categoriesId = $("#goods_categories").val();
      	var supplierId = $("#goods_supplier").val();
      	var goodsTags = $("#goods_tags").val();
      	var goodsInfo = $("#goods_info").val();
        aoData.push({"name":"status","value":status});
        aoData.push({"name":"categoriesId","value":categoriesId});
        aoData.push({"name":"supplierId","value":supplierId});
        aoData.push({"name":"goodsTags","value":goodsTags});
        aoData.push({"name":"goodsInfo","value":goodsInfo});
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
        //序号
        var api = this.api();
        var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
        api.column(0).nodes().each(function(cell, i) {
            cell.innerHTML = startIndex + i + 1;
        });
    }
});

/* 时间格式转换*/
function format(time){
	if(time == null || time == "null" || time == undefined){
		return "";
	}
	var date = new Date(time);
	var seperator1 = '-';
    var seperator2 = ':';
	var month = formatDate(date.getMonth() + 1);
	var day = formatDate(date.getDate());
	var hours = formatDate(date.getHours());
	var minutes = formatDate(date.getMinutes()); 
    var seconds = formatDate(date.getSeconds());
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + day
        + ' ' +hours + seperator2 + minutes
        + seperator2 + seconds;
	return currentdate;
}

function formatDate(val){
	if(val >= 1 && val <= 9){
		val = '0' + val;
	}
	if(val == 0){
		val = '00';
	}
	return val;
}

//新增
function add() {
    var w = 800;
    var	h = ($(window).height() - 50);
    var index = layer.open({
        type : 2,
        title:'新增商品',
        content : "<%=request.getContextPath()%>/server/goods/addGoods",
        area : [ w+'px', h+'px' ],
        maxmin : true
    });
    layer.full(index);
}

//单位
function unitDetail() {
    layer_show("商品单位设置", "<%=request.getContextPath()%>/server/goods/goodsUnit", "600", "400");
}

//厨打
function smallTicketDetail() {
    layer_show("厨房小票机管理", "<%=request.getContextPath()%>/server/goods/smallTicket", "490", "440");
}

//标签
function tagsDetail() {
    layer_show("商品标签设置", "<%=request.getContextPath()%>/server/goods/goodsTags", "600", "400");
}

//导出
function importOut() {
    layer_show("批量导入", "<%=request.getContextPath()%>/server/goods/importOut", "480", "340");
}

//导入
function importIn() {
    layer_show("批量导出", "<%=request.getContextPath()%>/server/goods/importIn", "480", "340");
}

//商品编辑
function edit(id){
    var w = 800;
    var	h = ($(window).height() - 50);
    var index = layer.open({
        type : 2,
        title:'编辑商品',
        content : "<%=request.getContextPath()%>/server/goods/editGoods/"+id,
        area : [w+'px', h+'px'],
        maxmin : true
    });
    layer.full(index);
}

//删除
function del(id){
    layer.confirm(
        "确定删除该商品？",
        ["确定","取消"],
        function(){
            $.post("<%=request.getContextPath()%>/server/goods/delGoods", {id : id}, function (data) {
                layer.msg(data.msg);
                table.fnDraw();
            })
        }
    )
}
</script>
</body>
</html>