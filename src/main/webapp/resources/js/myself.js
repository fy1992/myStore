function reflush(table, areaId) {
    var start = table.fnSettings()._iDisplayStart;
    var currentPage = (start/table.fnSettings()._iDisplayLength) + 1;
    $("#"+areaId+"").val(start);
    table.fnDraw(false);
    table.fnPageChange(currentPage - 1);
    $("#"+areaId+"").val("");
}

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
