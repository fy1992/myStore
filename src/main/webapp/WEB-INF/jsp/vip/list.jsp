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

<title>会员列表</title>
</head>
<body class="pos-r">
<div>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 会员 <span class="c-gray en">&gt;</span> 会员资料 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="clearfix">
        <div class="text-r cl pl-20 pt-10 pb-10 box-shadow">
            <span class="l">
                <a href="javascript:void(0);" onclick="add();" class="btn btn-primary radius">新增</a>
            </span>
            <span class="select-box" style="width: 100px;">
                <select class="select radius" id="vip_status">
                    <option value="1">启用</option>
                    <option value="0">禁用</option>
                </select>
            </span>
            <span class="select-box radius" style="width: 100px;">
                <select class="select" id="vip_level">
                    <option value="0">全部等级</option>
                </select>
            </span>
            <input type="text" id="vip_info" placeholder="卡号/姓名/电话" style="width:260px" class="input-text radius">
            <button id="vip_search" class="btn btn-success"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
        </div>
        <div class="pd-20 clearfix">
            <table class="table table-border table-bordered table-bg table-hover table-striped box-shadow" id="vip_table">
                <thead>
                    <tr class="text-c">
                        <th width="50">序号</th>
                        <th width="50">操作</th>
                        <th width="100">会员号</th>
                        <th width="50">姓名</th>
                        <th width="50">电话</th>
                        <th width="50">会员等级</th>
                        <th width="50">余额</th>
                        <th width="80">积分</th>
                        <th width="50">优惠券</th>
                        <th width="50">开卡门店</th>
                        <th width="50">开卡日期</th>
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
	$("#vip_search").click(function(){
		table.fnDraw();
	});
    //类别
	$.post("<%=request.getContextPath()%>/server/vip/allVipLevel", function (data) {
	    for(var n in data){
            $("#vip_level").append("<option value = '" + data[n].id + "'>" + data[n].name + "</option>");
        }
    });
});

//table start here
table = $('#vip_table').dataTable({
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
	       return "<a style='text-decoration:none' onclick='edit(\""+full.id+"\")'>编辑</a>";
        }},
        {"mData" : "vipNo", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "vipName", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "phone", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "vipLevelName", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "balance", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "point", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "tradePrice", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "storeName", "sDefaultContent" : "","bSortable":false,"sClass":"center"},
        {"mData" : "registerTime", "sDefaultContent" : "", "mRender":function(data, type, full){
               return format(data);
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
       "sAjaxSource" : "<%=request.getContextPath()%>/server/vip/list",
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
      	var status = $("#vip_status").val();
      	var level = $("#vip_level").val();
      	var vipInfo = $("#vip_info").val();
        aoData.push({"name":"status","value":status});
        aoData.push({"name":"level","value":level});
        aoData.push({"name":"vipInfo","value":vipInfo});
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

//新增
function add() {
    layer_show("新增会员", "<%=request.getContextPath()%>/server/vip/add", "700", "600");
}

//会员编辑
function edit(id){
    layer_show("会员编辑", "<%=request.getContextPath()%>/server/vip/edit/"+id, "700", "600");
}
</script>
</body>
</html>