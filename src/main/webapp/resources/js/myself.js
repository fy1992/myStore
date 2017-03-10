function reflush(table, areaId) {
    var start = table.fnSettings()._iDisplayStart;
    var currentPage = (start/table.fnSettings()._iDisplayLength) + 1;
    $("#"+areaId+"").val(start);
    table.fnDraw(false);
    table.fnPageChange(currentPage - 1);
    $("#"+areaId+"").val("");
};

var existsInArr = function(item, arr){
    if(!arr || !item){
        return false;
    }
    var result = false;
    arr.forEach(function (data) {
        if(item == data){
            result = true;
        }
    });
    return result;
};

/* 时间格式转换*/
var format = function(time){
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
};

var formatDate = function(val){
    if(val >=1 && val <= 9){
        val = '0' + val;
    }
    if(val == 0){
        val = '00';
    }
    return val;
};
