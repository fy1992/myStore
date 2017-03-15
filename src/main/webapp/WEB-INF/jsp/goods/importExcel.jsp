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
    <script type="text/javascript" src="${ctxResource}/css/webuploader/0.1.5/webuploader.css"></script>
    <title></title>
</head>
<body>
<div class="pd-20 minwidth">
    <div class="form form-horizontal">
        <div class="row cl">
            1. 还没创建过导入数据文件，<a class="btn btn-primary size-S radius" href="javascript:void(0);" id="downloadTemplate">下载模板</a>
        </div>
        <div class="row cl">
            2. 已创建好导入数据文件，直接导入：
        </div>
        <div class="row cl">
            <label class="form-label col-3">所属门店：</label>
            <div class="formControls col-6">
                <span class="select-box radius">
                    <select class="select" id = "supplier" name = "supplierId">
                        <option value = "0">- 请选择供货商 -</option>
                    </select>
                </span>
            </div>
            <div class="col-3"> </div>
        </div>
        <div id="uploader" class="webuploader-container">
            <!--用来存放文件信息-->
            <div id="thelist" class="uploader-list"></div>
            <div class="btns">
                <div id="picker">选择文件</div>
                <button id="ctlBtn" class="btn btn-default">开始上传</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctxResource}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctxResource}/js/layer/layer.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctxResource}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctxResource}/js/myself.js"></script>
<script type="text/javascript" src="${ctxResource}/css/webuploader/0.1.5/webuploader.js"></script>
<script>
    var uploader = WebUploader.create({
        // swf文件路径
        swf: '${ctxResource}/css/webuploader/0.1.5/Uploader.swf',
        // 文件接收服务端。
        server: 'http://webuploader.duapp.com/server/fileupload.php',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false
    });
    uploader.on( 'uploadSuccess', function( file ) {
        $( '#'+file.id ).find('p.state').text('已上传');
    });
    uploader.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('p.state').text('上传出错');
    });
</script>
</body>
</html>