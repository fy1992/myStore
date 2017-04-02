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
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 销售 <span class="c-gray en">&gt;</span> 销售单据 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="clearfix">
        <div class="text-r cl pl-20 pt-10 pb-10 box-shadow">
            <c:if test="${store.multiple eq 1}">
                <span class="select-box" style="width: 110px;">
                    <select class="select" id="storeId">
                        <option value="0">全部门店</option>
                    </select>
                </span>
            </c:if>
            <span class="select-box" style="width: 110px;">
                <select class="select" id="cashierId">
                    <option value="-1">全部收银员</option>
                </select>
            </span>
            <span class="select-box" style="width: 124px;">
                <select class="select" id="payType">
                    <option value="-1">全部支付方式</option>
                    <option value="0">现金</option>
                </select>
            </span>
            <span class="select-box" style="width: 105px;">
                <select class="select" id="type">
                    <option value="0">有效单据</option>
                    <option value="3">会员单据</option>
                </select>
            </span>
            <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}',readOnly:true,skin:'twoer'})" id="startTime" class="input-text Wdate radius" style="width:120px;"/> 至
            <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2099-10-01',readOnly:true,skin:'twoer'})" id="endTime" class="input-text Wdate radius" style="width:120px;"/>
            <input type="text" id="serialNum" placeholder="流水号" style="width:260px" class="input-text radius">
            <button id="salesInfo_search" class="btn btn-success"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
        </div>
        <div class="pd-20 clearfix">
            <table class="table table-border table-bordered table-bg table-hover table-striped box-shadow" id="salesInfo_table">
                <thead>
                <tr class="text-c">
                    <th width="50">操作</th>
                    <th width="100">流水号</th>
                    <th width="100">日期</th>
                    <th width="100">类型</th>
                    <th width="50">收银员</th>
                    <th width="50">会员</th>
                    <th width="50">商品数量</th>
                    <th width="50">商品原价</th>
                    <th width="50">实收金额</th>
                    <th width="50">利润</th>
                    <th width="50">导购员</th>
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
        $.post("<%=request.getContextPath()%>/server/employee/allCashierList", function (data) {
            for(var n in data){
                $("#cashierId").append("<option value = '"+data[n].id+"'>"+data[n].name+"</option>");
            }
        })

        $("#salesInfo_search").click(function(){
            table.fnDraw();
        });

        if(${store.multiple eq 1}) {
            $.post("<%=request.getContextPath()%>/server/store/allStore/0", function (data) {
                for (var n in data) {
                    $("#storeId").append("<option value = '" + data[n].id + "'>" + data[n].name + "</option>");
                }
            });
        }
        //table start here
        table = $('#salesInfo_table').dataTable({
            "bProcessing": true,//DataTables载入数据时，是否显示‘进度’提示
            "bPaginate": true,//是否显示（应用）分页器
            "bLengthChange": false,
            "bAutoWidth" : true,
            "bScrollCollapse" : true,//是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
            "bDestroy" : true,
            "bInfo" : true,//是否显示页脚信息，DataTables插件左下角显示记录数
            "bFilter" : false,//是否启动过滤、搜索功能
            "aoColumns" : [
                {"mData" : "", "sDefaultContent" : "", "sClass":"center", "bSortable":false, "mRender":function(data, type, full){
                    return "<a style='text-decoration:none' onclick='detail(\"" + full.id + "\")'>明细</a>";
                }},
                {"mData" : "serialNum", "sDefaultContent" : "", "bSortable":false},
                {"mData" : "markTime", "sDefaultContent" : "", "bSortable":false, "mRender" : function (data, type, full) {
                    return format(data).substring(0, 10);
                }},
                {"mData" : "type", "sDefaultContent" : "", "bSortable":false, "mRender" : function (data, type, full) {
                    return data;
                }},
                {"mData" : "cashierName", "sDefaultContent" : "", "bSortable":false},
                {"mData" : "vipName", "sDefaultContent" : "", "bSortable":false, "mRender" : function (data, type, full) {
                    return !data ? "-" : data;
                }},
                {"mData" : "goodsNum", "sDefaultContent" : "", "bSortable":false},
                {"mData" : "goodsPrice", "sDefaultContent" : "", "bSortable":false},
                {"mData" : "realPrice", "sDefaultContent" : "", "bSortable":false},
                {"mData" : "gain", "sDefaultContent" : "", "bSortable":false},
                {"mData" : "salesName", "sDefaultContent" : "", "bSortable":false, "mRender" : function (data, type, full) {
                    return !data ? "-" : data;
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
            "sAjaxSource" : "<%=request.getContextPath()%>/server/saleInfo/saleList",
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
                var type = $("#type").val();
                var storeId = $("#storeId").val();
                var serialNum = $("#serialNum").val();
                var cashierId = $("#cashierId").val();
                var payType = $("#payType").val();
                var startTime = $("#startTime").val();
                var endTime = $("#endTime").val();
                aoData.push({"name":"type","value":type});
                aoData.push({"name":"serialNum","value":serialNum});
                aoData.push({"name":"cashierId","value":cashierId});
                aoData.push({"name":"payType","value":payType});
                aoData.push({"name":"startTime","value":startTime});
                aoData.push({"name":"endTime","value":endTime});
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

    /**
     * 销售单据明细
     * @param id
     */
    function detail(id) {
        layer_show("销售单据明细", "<%=request.getContextPath()%>/server/saleInfo/saleInfoItem/"+id, "800", "400");
    }
</script>
</body>
</html>