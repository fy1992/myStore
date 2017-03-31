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

<title>原材料库存预警列表</title>
</head>
<body class="pos-r">
<div>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 库存 <span class="c-gray en">&gt;</span> 原材料库存預警 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="clearfix">
        <div class="text-r cl pl-20 pt-10 pb-10 box-shadow">
            <span class="select-box" style="width: 100px;">
                <select class="select radius" id="categoriesId">
                    <option value="-1">全部分类</option>
                </select>
            </span>
            <span class="select-box" style="width: 120px;">
                <select class="select radius" id="supplierId">
                    <option value="-1">全部供货商</option>
                </select>
            </span>
            <input type="text" id="raw_info" placeholder="原材料名称/条码" style="width:260px" class="input-text radius">
            <button id="stock_search" class="btn btn-success"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
        </div>
        <div class="pd-20 clearfix">
            <table class="table table-border table-bordered table-bg table-hover table-striped box-shadow" id="stock_table">
                <thead>
                    <tr class="text-c">
                        <th width="50">序号</th>
                        <th width="200">商品名称</th>
                        <th width="100">条码</th>
                        <th width="100">商品分类</th>
                        <th width="100">供货商</th>
                        <th width="100">现有库存</th>
                        <th width="50">单位</th>
                        <th width="50">库存上限</th>
                        <th width="50">库存下限</th>
                        <th width="50">到期日期</th>
                        <th width="50">保质情况</th>
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
<script type="text/javascript" src="${ctxResource}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
//搜索
$(function(){
	$("#stock_search").click(function(){
		table.fnDraw();
	});

    //类别
    $.post("<%=request.getContextPath()%>/server/categories/categoriesList", function (data) {
        for(var n in data){
            $("#categoriesId").append("<option value = '" + data[n].id + "'>" + data[n].name + "</option>");
        }
    });
    //供货商
    $.post("<%=request.getContextPath()%>/server/supplier/allSupplier", function (data) {
        for(var n in data){
            $("#supplierId").append("<option value = '" + data[n].id + "'>" + data[n].name + "</option>");
        }
    });
});

//table start here
table = $('#stock_table').dataTable({
	   "bProcessing": true,//DataTables载入数据时，是否显示‘进度’提示  
       "bPaginate": true,//是否显示（应用）分页器  
       "bLengthChange": false,
       "bScrollCollapse" : true,//是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
       "bDestroy" : true,
       "bInfo" : true,//是否显示页脚信息，DataTables插件左下角显示记录数 
       "bFilter" : false,//是否启动过滤、搜索功能
       "aoColumns" : [
        {"mData" : null, "sDefaultContent" : "", "bSortable":false},
        {"mData" : "name", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "rawNo", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "categoriesName", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "supplierName", "sDefaultContent" : "", "bSortable":false, "mRender":function(data, type, full){
            return  !data ? "无" : data;
        }},
        {"mData" : "stock", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "mainUnitName", "sDefaultContent" : "", "bSortable":false, "mRender":function(data, type, full){
            return  !data ? "无" : data;
        }},
        {"mData" : "stockUp", "sDefaultContent" : "", "bSortable":false, "mRender":function(data, type, full){
            return  !data ? "-" : data;
        }},
        {"mData" : "stockDown", "sDefaultContent" : "", "bSortable":false, "mRender":function(data, type, full){
            return  !data ? "-" : data;
        }},
        {"mData" : "overdueTime", "sDefaultContent" : "", "bSortable":false, "mRender":function(data, type, full){
	        return  data ? format(data).substring(0, 10) : "-";
        }},
        {"mData" : "overdueDay", "sDefaultContent" : "", "bSortable":false, "mRender":function(data, type, full){
            return  data ? "过期 " + data + " 天" : "-";
        }}
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
       "iDisplayLength" : 20, //每页显示条数
       "iDisplayStart": 0,
       "bServerSide": true,
       "fnFormatNumber": function(iIn){
       	    return iIn;//格式化数字显示方式
       },
       "sAjaxSource" : "<%=request.getContextPath()%>/server/raw/list",
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
      	var categoriesId = $("#categoriesId").val();
      	var supplierId = $("#supplierId").val();
      	var rawInfo = $("#raw_info").val();
      	aoData.push({"name":"categoriesId","value":categoriesId});
      	aoData.push({"name":"supplierId","value":supplierId});
      	aoData.push({"name":"stockPage","value":1});
      	aoData.push({"name":"goodsRawInfo","value":rawInfo});
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
</script>
</body>
</html>