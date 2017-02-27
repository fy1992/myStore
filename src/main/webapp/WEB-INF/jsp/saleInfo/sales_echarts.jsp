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
<div class="pd-20 minwidth">
    <div class="form form-horizontal">
        <div class="text-r cl pl-20 pt-10 pb-10">
            <a class="btn btn-default radius l mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:history.go(-1);" title="后退" ><i class="Hui-iconfont">&#xe678;</i></a>
        </div>
        <div class="row cl text-l vipbox pt-20">
            <div class="col-12 cl">
                <div class="formControls">
                    <div id = "newsLine" style="width:1678px;height:790px;"></div>
                </div>
            </div>
            <div class="col-12 cl">
                <div class="formControls">
                    <div id = "newsWordCloud" style="width:1678px;height:790px;"></div>
                </div>
            </div>
            <div class="col-12 cl">
                <div class="formControls">
                    <div id = "newsForce" style="width:1678px;height:790px;"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctxResource}/js/echarts/build/dist/echarts-all.js"></script>
<script>
    $(function(){
        myChart1.on('click', function (params) {
            window.open(params.data.url);
        });
    });
    var myChart1 = echarts.init(document.getElementById('newsForce'));
    var option1 = {
        toolbox: {
            show : true,
            feature : {
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        series : [
            {
                type:'force',
                name : "转载分析",
                ribbonType: false,
                minRadius : 40,//顶点数据映射成圆半径后的最小半径
                maxRadius : 60,//顶点数据映射成圆半径后的最大半径
                gravity: 7,//向心力系数，系数越大则节点越往中心靠拢
                scaling: 2.5,//布局缩放系数，并不完全精确, 效果跟布局大小类似
                size:'200%',//布局大小，可以是绝对值或者相对百分比
                roam: true,//是否开启滚轮缩放和拖拽漫游，默认为false（关闭），其他有效输入为true（开启），'scale'（仅开启滚轮缩放），'move'（仅开启拖拽漫游）
                linkSymbol: 'arrow',//力导向图的边两端图形样式，可指定为'arrow'
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            textStyle: {
                                color: '#333',
                                fontSize:5
                            }
                        },
                        nodeStyle : {
                            brushType : 'both',
                            borderColor : 'rgba(255,215,0,0.4)',
                            borderWidth : 1
                        },
                        linkStyle: {
                            type: 'line'
                        }
                    }
                },
                nodes:[${fouceName}],
                links:[${fouceChart}]
            }
        ]
    };
    myChart1.setOption(option1);


    var myChart2 = echarts.init(document.getElementById('newsWordCloud'));
    var option2 = {
        toolbox: {
            show : true,
            feature : {
                saveAsImage : {show: true}
            }
        },
        series : [{
            name: 'Google Trends',
            type: 'wordCloud',
            size: ['100%', '100%'],
            textRotation : [0, 0, 0, 0],
            textPadding: 5,
            autoSize: {
                enable: true,
                minSize: 14
            },
            data:[${wordCloud}]
        }]
    };
    myChart2.setOption(option2);

    var myChart3 = echarts.init(document.getElementById('newsLine'));
    var option3 = {
        legend : {
            data : ["${legend}"]
        },
        tooltip : {
            trigger: 'axis'
        },
        toolbox: {
            show : true,
            feature : {
                magicType : {show: true, type: ['line', 'bar']},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : [${xAxis}]
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [${series}]
    };
    myChart3.setOption(option3);
</script>
</body>
</html>