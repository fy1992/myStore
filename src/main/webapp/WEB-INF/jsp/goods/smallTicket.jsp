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
    <script type="text/javascript" src="lib/html5.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <script type="text/javascript" src="lib/PIE_IE678.js"></script>
    <![endif]-->
    <link href="${ctxResource}/css/H-ui.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/admin.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctxResource}/css/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
    <title></title>
</head>
<body>
<div class="pd-20">
    <div class="form form-horizontal" id="form-goodsUnit-add">
        <div class="cl pl-20 pt-10 pb-10">
            <div class="text-r">
                <input type="text" id="goods_unit_add"  style="width:200px" class="input-text" placeholder="请输入商品单位名称"/>
                <button onclick="add()" class="btn btn-primary radius">新增</button>
            </div>
        </div>
        <div class="pd-20 clearfix">
            <table class="table table-border table-bordered table-bg table-hover table-striped box-shadow" id="goods_unit_table">
                <thead>
                <tr class="text-c">
                    <th width="50">单位</th>
                    <th width="50">操作</th>
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
<script type="text/javascript" src="${ctxResource}/js/jquery.jeditable.js"></script>
<script>
    //添加
    function add() {
        var unitName = $.trim($("#goods_unit_add").val());
        $.post("<%=request.getContextPath()%>/goods/addGoodsUnit", {"name":unitName}, function (data) {
            if(data.result == 1){
                layer.msg("单位添加成功");
                $("#goods_unit_add").val("");
                table.fnDraw();
            }else{
                layer.msg("该单位已存在")
            }
        });
    }

    //删除
    function del(id){
        layer.msg('确定要删除该单位？', {
            time: 0 ,//不自动关闭
            btn: ['确定', '取消'],
            yes: function(index){
                $.post("<%=request.getContextPath()%>/goods/delGoodsUnit", {"id" : id}, function(data){
                    layer.msg(data.msg);
                    table.fnDraw();
                });
            }
        });
    }

    //编辑
    function edit(id) {
        var name = $().val();
        $.post("<%=request.getContextPath()%>/goods/editGoodsUnit", {"id" : id, "name" : name}, function(data){
            table.fnDraw();
        });
    }

    table = $('#goods_unit_table').dataTable({
        "bProcessing": true,//DataTables载入数据时，是否显示‘进度’提示
        "bPaginate": true,//是否显示（应用）分页器
        "bLengthChange": false,
        "bAutoWidth" : true,
        "bScrollCollapse" : true,//是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
        "bDestroy" : true,
        "bInfo" : true,//是否显示页脚信息，DataTables插件左下角显示记录数
        "bFilter" : false,//是否启动过滤、搜索功能
        "aoColumns" : [
            {"mData" : "name", "sDefaultContent" : "", "bSortable":false},
            {"mData" : "", "sDefaultContent" : "", "sClass":"center", "bSortable":false, "mRender":function(data, type, full){
                var btn = "<a style='text-decoration:none' onclick='edit(\""+ full.id +"\")'>编辑</a>";
                btn += "&nbsp;<a style='text-decoration:none' onclick='del(\""+ full.id +"\")'>删除</a>";
                return btn;
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
            "sProcessing": "处理中..."
        },
        //"deferRender": true, //当处理大数据时，延迟渲染数据，有效提高Datatables处理能力
        "iDisplayLength" : 20, //每页显示条数
        //"iDisplayStart": 0,
        "bServerSide": true,
        "fnFormatNumber": function(iIn){
            return iIn;//格式化数字显示方式
        },
        "sAjaxSource" : "<%=request.getContextPath()%>/goods/goodsUnitList",
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
            var iDisplayStart = $("#news_tableStart").val();
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
</script>
</body>
</html>