/**
 * Created by yuxuanjiao on 2017/10/24.
 */
$(function () {
    var baseUri = "demo";
    var $this = {};
    
    var baseModal = function(url) {
        $this = $(this);
        baseUri = url;
        
        init();
    }
    
    var events = {
        edit: function (id) {
            events.clearField();
            $('#modal-id').val(id);
            $.get("/api/" + baseUri + "/getById", {id : id}, function(data) {
                events.setCondition(data);
            });
        },
        save: function () {
            if (!check()) {
                return;
            }

            params = events.getCondition();

            $.post("/api/" + baseUri + "/insertOrUpdate", params, function(data) {
                if (data.code == 200) {
                    $("#autotable").baseTable.query();
                    $('.modal').modal('hide');
                    window.wxc.xcConfirm("操作成功！", window.wxc.xcConfirm.typeEnum.success);
                } else {
                    if(data.msg==undefined){
                        window.wxc.xcConfirm("错误！", window.wxc.xcConfirm.typeEnum.error);
                    }else{
                        window.wxc.xcConfirm(data.msg, window.wxc.xcConfirm.typeEnum.error);
                    }
                }
            });
        },
        clearField: function () {
            $('.modal input').val('');
            $('.modal textarea').val('');
            if(window.editor) {
                window.editor.html('');
            }
        },
        getCondition: function () {
            var params = {};
            params['id'] = $('#modal-id').val();
            params['author'] = $('#modal-author').val();

            var childs = $('#modal-table').children().children().children("td").children();
            for(var i = 0; i < childs.length; i++) {
                var item = $(childs[i]);
                if(item.attr('type') == 'text' || item.attr('type') == 'textarea') {
                    params[item.attr('key')] = item.val();
                } else if(item.attr('type') == 'editor') {
                    params[item.attr('key')] = window.editor.html();
                }
            }
            return params;
        },
        setCondition: function (data) {
            var childs = $('#modal-table').children().children().children("td").children();
            for(var i = 0; i < childs.length; i++) {
                var item = $(childs[i]);
                if(item.attr('type') == 'text' || item.attr('type') == 'textarea') {
                    item.val(data[item.attr('key')]);
                } else if(item.attr('type') == 'editor') {
                    window.editor.html(data[item.attr('key')]);
                }
            }
            
            $('.modal').modal('show');
        }
    };

    function event_bind() {

        $("#modal-addBtn").click(events.clearField);
        
        $("#modal-saveBtn").click(events.save);
        edit = events.edit;

    }

    function check() {
//        var childs = $('#modal-table').children().children().children("td").children();
//        for(var i = 0; i < childs.length; i++) {
//            var item = $(childs[i]);
//            if(item.attr('type') == 'text' || item.attr('type') == 'textarea') {
//                item.val(data[item.attr('key')]);
//            } else if(item.attr('type') == 'editor') {
//                window.editor.html(data[item.attr('key')]);
//            }
//        }
//        var title = $('.title').val();
//        var content = $('.content').val();
//        var count = $(".count").text();
//
//        var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
//
//        if (count == 0 || count > 500) {
//            alert('描述不能为空且不超过500字,当前字数' + count + '字！');
//            return false;
//        }
//        if (title.length == 0 || title.length > 30) {
//            alert('标题不能为空且不超过30字,当前字数' + title.length + '字！');
//            return false;
//        }

        return true;
    }
    function init() {
        $.ajaxSetup({
            async : false
        });
        event_bind();
    }
    
    $.fn.baseModal = baseModal;
});