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
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户 <span class="c-gray en">&gt;</span> 用户列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="clearfix">
        <div class="text-r cl pl-20 pt-10 pb-10 box-shadow">
            <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}',readOnly:true,skin:'twoer'})" id="startTime" class="input-text Wdate radius" style="width:120px;"/> 至
            <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2099-10-01',readOnly:true,skin:'twoer'})" id="endTime" class="input-text Wdate radius" style="width:120px;"/>
            <button id="changeShifts_search" class="btn btn-success"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
        </div>
        <div class="pd-20 clearfix">
            <table class="table table-border table-bordered table-bg table-hover table-striped box-shadow" id="changeShifts_table">
                <thead>
                <tr class="text-c">
                    <th width="50">开始时间</th>
                    <th width="50">结束时间</th>
                    <th width="100">工号</th>
                    <th width="100">姓名</th>
                    <th width="50">收银单数</th>
                    <th width="50">收银总额</th>
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
        $("#changeShifts_search").click(function(){
            table.fnDraw();
        });
    });

    //table start here
    table = $('#changeShifts_table').dataTable({
        "bProcessing": true,//DataTables载入数据时，是否显示‘进度’提示
        "bPaginate": true,//是否显示（应用）分页器
        "bLengthChange": false,
        "bAutoWidth" : true,
        "bScrollCollapse" : true,//是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
        "bDestroy" : true,
        "bInfo" : true,//是否显示页脚信息，DataTables插件左下角显示记录数
        "bFilter" : false,//是否启动过滤、搜索功能
        "aoColumns" : [
            {"mData" : "startTime", "sDefaultContent" : "", "sClass":"center", "bSortable":false, "mRender":function(data, type, full){
                return format(data);
            }},
            {"mData" : "endTime", "sDefaultContent" : "", "sClass":"center", "bSortable":false, "mRender":function(data, type, full){
                return data ? format(data) : "非正常下线";
            }},
            {"mData" : "cashierNo", "sDefaultContent" : "", "bSortable":false},
            {"mData" : "cashierName", "sDefaultContent" : "", "bSortable":false},
            {"mData" : "num", "sDefaultContent" : "", "bSortable":false},
            {"mData" : "totalPrice", "sDefaultContent" : "", "bSortable":false, "mRender":function(data, type, full){
                return !data ? "-" : data + " 元";
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
        "order" : [[1, "desc"]],
        "iDisplayLength" : 20, //每页显示条数
        //"iDisplayStart": 0,
        "bServerSide": true,
        "fnFormatNumber": function(iIn){
            return iIn;//格式化数字显示方式
        },
        "sAjaxSource" : "<%=request.getContextPath()%>/server/employee/changeShiftsList",
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
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            var storeId = $("#storeId").val();
            if(!startTime){
                startTime = "";
            }
            if(!endTime){
                endTime = "";
            }
            if(!storeId){
                storeId = 0;
            }
            aoData.push({"name":"startTime","value":startTime});
            aoData.push({"name":"endTime","value":endTime});
            aoData.push({"name":"storeId","value":storeId});
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
</script>
</body>
</html>