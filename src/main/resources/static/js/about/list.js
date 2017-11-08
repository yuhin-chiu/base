$(function() {

    /**
     * 只用修改baseUri，columns
     * 只要list、edit、delete接口按照规范来就行
     */
    var baseUri = 'about';
    function getOtherCondition() { // 只要把需要提交的参数返回就行了
        return {
            timeRange : $("#modal-timeRange").val(),
            title: $("#modal-title").val()
        }
    }
    var columns = [ {
        title : "ID",
        field : "id",
        align : "center",
        width : 20
    }, {
        title : "正文",
        field : "content",
        align : "center",
        width : 200
    }, {
        title : "所属栏目",
        field : "parent",
        align : "center",
        formatter : function(value) {
            switch(value) {
                case 0: return "公司动态";
                case 1: return "行业新闻";
                case 2: return "栏目2";
                default : return "";
            }
        },
        width : 80
    }, {
        title : "图片",
        field : "imgUrls",
        align : "center",
        formatter : function(value) {
            if(value && value.length > 0) {
                return "<img style='width:30px;height:30px;' onclick=window.open('"+ value[0] + "','_blank_') src='"+ value + "' />";
            }
            return "";
        },
        width : 40
    }, {
        title : "发布时间",
        field : "createTimeStr",
        align : "center",
        width : 110
    }, {
        title : "操作",
        field : "status",
        align : "center",
        formatter : function(value,row,index) {
            var html = "<a href='javascript:void(0)' onclick='edit(" + row.id + ")'>中文</a>&nbsp;" +
                    "<a href='javascript:void(0)' onclick='edit(" + (row.id + 1) + ")'>英文</a>&nbsp;" +
                    "<a class='remove' href='javascript:void(0)' rowid=" + row.id + ">删除</a>&nbsp;";
//            if(value == 1) {
//                html += "<a class='no' href='javascript:void(0)' rowid=" + row.id + ">关闭</a>"
//            } else {
//                html += "<a class='yes' href='javascript:void(0)' rowid=" + row.id + ">开启</a>"
//            }
            return html;
        },
        width : 90
    } ];

    $("#autotable").baseTable(baseUri, columns, getOtherCondition);

    $("#modal-table").baseModal(baseUri, $("#autotable").baseTable);
});
