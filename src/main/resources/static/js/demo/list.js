$(function() {

    /**
     * 只用修改baseUri，columns
     * 只要list、edit、delete接口按照规范来就行
     */
    var baseUri = 'demo';
    function getOtherCondition() { // 只要把需要提交的参数返回就行了
        return {
            timeRange : $("#timeRange").val(),
            query: $("#title").val()
        }
    }
    var columns = [ {
        title : "ID",
        field : "id",
        align : "center",
        width : 20
    }, {
        title : "标题",
        field : "title",
        align : "center",
        width : 200
    }, {
        title : "作者",
        field : "author",
        align : "center",
        width : 70
    }, {
        title : "所属栏目",
        field : "parent",
        align : "center",
        formatter : function(value) {
            switch(value) {
                case 0: return "栏目0";
                case 1: return "栏目1";
                case 2: return "栏目2";
                default : return "";
            }
        },
        width : 80
    }, {
        title : "图片",
        field : "url",
        align : "center",
        formatter : function(value) {
            if(value) {
                return "<img style='width:30px;height:30px;' src='"+ value + "' />";
            }
            return "";
        },
        width : 40
    }, {
        title : "内容",
        field : "content",
        align : "center",
        formatter : function(value) {
            if(value.length <= 30) {
                return value;
            }
            return value.substring(0, 30);
        }
    }, {
        title : "发布时间",
        field : "createTimeStr",
        align : "center",
        width : 100
    }, {
        title : "操作",
        field : "status",
        align : "center",
        formatter : function(value,row,index) {
            var html = "<a href='javascript:void(0)' onclick='edit(" + row.id + ")'>编辑</a>&nbsp;" +
                    "<a class='remove' href='javascript:void(0)' rowid=" + row.id + ">删除</a>&nbsp;";
            if(value == 1) {
                html += "<a class='no' href='javascript:void(0)' rowid=" + row.id + ">关闭</a>"
            } else {
                html += "<a class='yes' href='javascript:void(0)' rowid=" + row.id + ">开启</a>"
            }
            return html;
        },
        width : 90
    } ];

    function callback(data) {
        
    }
    $("#autotable").baseTable(baseUri, columns, getOtherCondition);

    $("#timeRange").daterangepicker({}, function(start, end, label) {
        $("#autotable").baseTable.query();
    });

    $("#modal-queryBtn").click($("#autotable").baseTable.query);
    $("#modal-table").baseModal(baseUri);
});
