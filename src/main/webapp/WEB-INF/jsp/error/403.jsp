<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<article class="page-404 minWP text-c">
    <p class="error-title"><i class="Hui-iconfont va-m">&#xe688;</i>
        <span class="va-m"> 403</span>
    </p>
    <p class="error-description">不好意思，<shiro:principal/> 您没有权限访问该页面~</p>
    <p class="error-info">您可以：
        <a href="javascript:;" onclick="history.go(-1)" class="c-primary">&lt; 返回上一页</a>
        <span class="ml-20">|</span>
        <a href="<%=request.getContextPath()%>/admin/index" class="c-primary ml-20">去首页 &gt;</a>
    </p>
</article>