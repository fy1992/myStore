<%@page language="java" import="java.util.*,cn.dahe.model.*" pageEncoding="utf-8"%>
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
<%--<div class="pos-a" style="width:210px;left:0;top:0; bottom:0;height:1000px; overflow: auto; border-right:1px solid #e5e5e5; background-color:#f5f5f5;">
	<ul id="channelTree" class="ztree"></ul>
	<input id="news_cid" type="hidden"/>
	<input id="news_tableStart" type="hidden"/>
</div>--%>
<div>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品资料 <span class="c-gray en">&gt;</span> 新闻列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="clearfix">
        <div class="text-r cl pl-20 pt-10 pb-10 box-shadow">
            <span class="l">
                <a href="javascript:;" onclick="pushAll();" class="btn btn-success radius"><i class="Hui-iconfont">&#xe603;</i> 批量推送至手机客户端</a>
                <a href="javascript:;" onclick="removeCache();" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe609;</i> 清除所有缓存</a>
            </span>
            <span class="select-box" style="width: 100px;">
                <select class="select" id="goods_status">
                    <option value="0">启用</option>
                    <option value="1">禁用</option>
                </select>
            </span>
            <span class="select-box" style="width: 100px;">
                <select class="select" id="goods_categories">
                    <option value="-1">全部分类</option>
                </select>
            </span>
            <span class="select-box" style="width: 100px;">
                <select class="select" id="goods_supplier">
                    <option value="-1">全部供应商</option>
                </select>
            </span>
            <span class="select-box" style="width: 100px;">
                <select class="select" id="goods_tags">
                    <option value="-1">全部标签</option>
                </select>
            </span>
            <input type="text" id="goods_info" placeholder="条码/名称/拼音码" style="width:260px" class="input-text">
            <button id="news_search" class="btn btn-success"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
        </div>
        <div class="pd-20 clearfix">
            <table class="table table-border table-bordered table-bg table-hover table-striped box-shadow" id="goods_table">
                <thead>
                    <tr class="text-c">
                        <th width="25"><input type="checkbox" name="" value=""></th>
                        <th width="50">序号</th>
                        <th>操作</th>
                        <th width="100">商品名称</th>
                        <th width="50">条码</th>
                        <th width="50">拼音码</th>
                        <th width="50">分类</th>
                        <th width="50">库存</th>
                        <th width="80">主单位</th>
                        <th width="30">进货价</th>
                        <th width="30">销售价</th>
                        <th width="30">批发价</th>
                        <th width="30">会员价</th>
                        <th width="30">会员折扣</th>
                        <th width="30">供货商</th>
                        <th width="30">生产日期</th>
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
<script type="text/javascript" src="${ctxResource}/js/jquery.dragsort-0.5.2.min.js"></script> 
<script type="text/javascript" src="${ctxResource}/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctxResource}/js/myself.js"></script>
<script type="text/javascript">
//搜索
$(function(){
	$("#news_search").click(function(){
		/*var status = $("#goods_status").val();
         var categories = $("#goods_categories").val();
         var supplier = $("#goods_supplier").val();
         var tags = $("#goods_tags").val();
         var goodsInfo = $("#goods_info").val();
         if(!goodsInfo && status == -1 && categories == -1 && supplier == -1){
         return false;
         }*/
		table.fnDraw();
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
	  	{"mData" : "", "sDefaultContent" : "", "sClass":"center", "bSortable":false},
	  	{"mData" : "", "sDefaultContent" : "", "sClass":"center", "bSortable":false, "mRender":function(data, type, full){
            return "<a style='text-decoration:none'>编辑</a>";
        }},
        {"mData" : "name", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "goodsNo", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "pinyin", "sDefaultContent" : "", "bSortable":false},
        {"mData" : "pinyin", "sDefaultContent" : "", "bSortable":false, "mRender":function(data, type, full){
        	return format(data);
        }},
        {"mData" : "isMobile", "sDefaultContent" : "", "mRender":function(data, type, full){  
        	return data == 1?"<div class='td-status'><span class='label label-danger radius'>未推送</span>":"<span class='label label-success radius'>已推送</span></div>";
        	},"bSortable":false,"sClass":"center"
        },
        {"mData" : "isFirstPage", "sDefaultContent" : "", "mRender":function(data, type, full){  
        	return data == 1?"<div class='td-status'><span class='label label-danger radius'>不显示</span>":"<span class='label label-success radius'>显示</span></div>";
        	},"bSortable":false,"sClass":"center"
        },
        {"mData" : "isEverFirstPage", "sDefaultContent" : "", "mRender":function(data, type, full){
               return full.isMobile == 1 || data == 0 || (data == 1 && full.isFirstPage == 0) ?"<div class='td-status'><span class='label label-danger radius'>不显示</span>":"<span class='label label-success radius'>显示</span></div>";
           },"bSortable":false,"sClass":"center"
        },
	    {"mData" : "isTop", "sDefaultContent" : "", "mRender":function(data, type, full){
			   return data == 0?"<div class='td-status'><span class='label label-danger radius'>不置顶</span>":"<span class='label label-success radius'>置顶</span></div>";
		   },"bSortable":false,"sClass":"center"
	    },
        {"mData" : "isPic","sDefaultContent" : "", "mRender":function(data, type, full){
			var classType = data == 0?"label label-danger radius":"label label-success radius";
        	var url = "<%=request.getContextPath()%>/admin/news/uploadImg/" + full.id;
            var btn = "<a style='text-decoration:none' class = '"+classType+"' onClick=\"pic_add('添加焦点图', '"+url+"', '10001','800','600')\" href='javascript:;' title='添加图片'>焦点图</a></div>";
      	  	if(data == 1){
                btn += "<a class='label label-primary' onclick='imgDetail(\""+full.id+"\")'>预览</a>";
            }
            return btn;
        },"bSortable":false}
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
       "order" : [[1, "desc"]],
       "iDisplayLength" : 20, //每页显示条数
       //"iDisplayStart": 0,
       "bServerSide": true,
       "fnFormatNumber": function(iIn){
       	    return iIn;//格式化数字显示方式
       },
       "sAjaxSource" : "<%=request.getContextPath()%>/goods/List",
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
      	aoData.push({"name":"bNeedPaging", "value":true});
      	var newsId = $("#news_id").val();
      	var isTop = $("#news_isTop").val();
      	var isMobile = $("#news_isMobile").val();
      	var isFirstPage = $("#news_isFirstPage").val();
      	var cid = $("#news_cid").val();
      	if(cid == ""){
      		cid = -1;
      	}
        var iDisplayStart = $("#news_tableStart").val();
        if(!iDisplayStart){
            iDisplayStart = 0;
        }
        iDisplayStart = Number(iDisplayStart);
        aoData[3].value = iDisplayStart == 0 ? this.fnSettings()._iDisplayStart : iDisplayStart;
        aoData.push({"name":"cid","value":cid});
        aoData.push({"name":"nid","value":newsId});
        aoData.push({"name":"isTop","value":isTop});
        aoData.push({"name":"isMobile","value":isMobile});
        aoData.push({"name":"isFirstPage","value":isFirstPage});
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
	if(val >=1 && val <= 9){
		val = '0' + val;
	}
	if(val == 0){
		val = '00';
	}
	return val;
}


/**
 * 
 	新闻细缆页
 */
function detail(id){
	$.post("<%=request.getContextPath()%>/admin/news/detail", {"id":id}, function(data){
		if(data.result == 1){
			window.open(data.msg);
		}
	})
}
</script>
</body>
</html>