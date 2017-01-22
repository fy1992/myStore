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
    <div class="form form-horizontal" id="form-goods-add">
        <div class="row cl">
            <label class="form-label col-3">是否启用：</label>
            <div class="formControls col-5">
            </div>
            <div class="col-4"> </div>
        </div>
        <hr/>
        <div class="row cl">
            <div class="col-8">
                <div class="row cl">
                    <label class="form-label col-3"><span class="c-red">* </span>条码：</label>
                    <div class="formControls col-5">
                        <input type="text" class="input-text radius" value="" id="goodsNo" style = "width:307px">
                    </div>
                    <div class="col-4"> </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-3"><span class="c-red">* </span>品名：</label>
                    <div class="formControls col-5">
                        <input type="text" class="input-text radius" value=""  id="truename" style = "width:307px">
                    </div>
                    <div class="col-4"> </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-3"><span class="c-red">* </span>分类：</label>
                    <div class="formControls col-5">
                        <select id="categories"  class="select radius" style = "width:307px">
                            <option value = "-1">- 请选择商品分类 -</option>
                        </select>
                    </div>
                    <div class="col-4"> </div>
                </div>
            </div>
            <div class="col-4">
                <div class="row cl">
                    <div class="formControls col-5">
                        <h3>编辑图片</h3>
                    </div>
                    <div class="col-4"> </div>
                </div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-4">
                <label class="form-label col-3"><span class="c-red">* </span>售价：</label>
                <input type = "text" class="col-5 input-text radius" name = "price"/>
                <div class="col-4"> </div>
            </div>
            <div class="col-4">
                <label class="form-label col-3"><span class="c-red">* </span>进价：</label>
                <input type = "text" class="col-5 input-text radius"/>
                <div class="col-4"> </div>
            </div>
            <div class="col-4">
                <label class="form-label col-3"><span class="c-red">* </span>库存：</label>
                <input type = "text" class="col-5 input-text radius"/>
                <div class="col-4"> </div>
            </div>
        </div>
        <hr/>
        <div class="row cl">
            <div class="col-4">
                <label class="form-label col-3">会员折扣：</label>
                <div class="formControls col-5">
                </div>
                <div class="col-4"> </div>
            </div>
            <div class="col-4">
                <label class="form-label col-3">会员价：</label>
                <div class="formControls col-5">
                    <input type = "text"  class="input-text radius"/>
                </div>
                <div class="col-4"> </div>
            </div>
            <div class="col-4">
                <label class="form-label col-3">批发价：</label>
                <div class="formControls col-5">
                    <input type = "text"  class="input-text radius"/>
                </div>
                <div class="col-4"> </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">拼音码：</label>
            <div class="formControls col-5">
                <input type="text" name="pinyin" class="input-text radius"/>
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">供货商：</label>
            <div class="formControls col-5">
                <select class="select radius">
                    <option>- 请选择供货商 -</option>
                </select>
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <div class="col-6">
                <label class="form-label col-3">生产日期：</label>
                <div class="formControls col-5">
                    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'productionDate\')||\'%y-%M-%d\'}'})" id="productionDate" class="input-text Wdate radius" style="width:120px;"/>
                </div>
                <div class="col-4"> </div>
            </div>
            <div class="col-6">
                <label class="form-label col-3">保质期：</label>
                <div class="formControls col-5">
                    <input type="text"  class="input-text radius"/>
                </div>
                <div class="col-4"> </div>
            </div>
        </div>
        <div class="row cl">
            <div class="col-6">
                <label class="form-label col-3">库存上限：</label>
                <div class="formControls col-5">
                    <input type="text"  class="input-text radius"/>
                </div>
                <div class="col-4"> </div>
            </div>
            <div class="col-6">
                <label class="form-label col-3">库存下限：</label>
                <div class="formControls col-5">
                    <input type="text"  class="input-text"/>
                </div>
                <div class="col-4"> </div>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">厨房票打：</label>
            <div class="formControls col-5">
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">商品标签：</label>
            <div class="formControls col-5">
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">备注：</label>
            <div class="formControls col-5">
                <textarea rows="2" maxlength="200" class="edit_txt" id="edit_remarks"></textarea>
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <div class="col-9 col-offset-3 mt-20">
                <input class="btn btn-primary radius" type="button" id="userAddBtn" value="&nbsp;&nbsp;确认&nbsp;&nbsp;">
            </div>
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
</body>
</html>