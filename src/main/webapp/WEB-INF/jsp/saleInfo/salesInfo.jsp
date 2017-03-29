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
<div>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品 <span class="c-gray en">&gt;</span> 商品资料 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="clearfix">
        <div class="text-r cl pl-20 pt-10 pb-10 box-shadow">
            <%--<span class="l">
                <a href="javascript:void(0);" onclick="add();" class="btn btn-primary radius">新增</a>
                <a href="javascript:void(0);" onclick="importIn();" class="btn btn-primary radius">导入</a>
                <a href="javascript:void(0);" onclick="importOut();" class="btn btn-primary radius">导出</a>
                <a href="javascript:void(0);" onclick="unitDetail();" class="btn btn-primary radius">单位</a>
                <a href="javascript:void(0);" onclick="smallTicketDetail();" class="btn btn-primary radius">厨打</a>
                <a href="javascript:void(0);" onclick="tagsDetail();" class="btn btn-primary radius">标签</a>
            </span>--%>
                <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M-%d\'}',readOnly:true,skin:'twoer'})" id="startTime" class="input-text Wdate radius" style="width:120px;"/> 至
                <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2099-10-01',readOnly:true,skin:'twoer'})" id="endTime" class="input-text Wdate radius" style="width:120px;"/>
            <button id="news_search" class="btn btn-success"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
        </div>
        <div class="pd-20 clearfix">
            <table class="table table-border table-bordered table-bg table-hover table-striped box-shadow" id="goods_table">
                <thead>
                <tr class="text-c">
                    <th width="50"></th>
                    <th width="50">概况</th>
                    <th width="100">现金支付</th>
                    <%--<th width="50">银联支付</th>
                    <th width="50">储值卡支付</th>
                    <th width="50">次卡支付</th>
                    <th width="50">购物卡支付</th>--%>
                </tr>
                </thead>
                <tbody id="table_tr">
                    <tr role="row" class="odd">
                        <td>商品销售</td>
                        <td>销售额 <span>0.00</span>, 利润 <span>0.00</span>, 单数 <span>0</span></td>
                        <td>0.00</td>
                    </tr>
                    <tr role="row" class="even">
                        <td>现金收支</td>
                        <td>收入 <span>0</span>, 支出<span>0</span></td>
                        <td><span>0.00</span></td>
                    </tr>
                    <tr role="row" class="odd">
                        <td>总计</td>
                        <td>-</td>
                        <td><span>0.00</span></td>
                    </tr>
                    <%--<tr role="row" class="even">
                        <td></td>
                    </tr>
                    <tr role="row" class="odd">
                        <td></td>
                    </tr>
                    <tr role="row" class="even">
                        <td></td>
                    </tr>--%>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctxResource}/js/My97DatePicker/WdatePicker.js"></script>
<script>
</script>
</body>
</html>