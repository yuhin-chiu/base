/**
 * Created by yuxuanjiao on 2017/08/05.
 */
$(function() {
    var events = {
        inner_page_jump : function() {
            var cur = $(this);
            if (cur.hasClass("list")) {
                location.href = "/backend/culture/list";
            } else if (cur.hasClass("whln")) {
                location.href = "/backend/culture/whln";
            } else if (cur.hasClass("ygsh")) {
                location.href = "/backend/culture/ygsh";
            }
        },
        select_menu : function() {
            var menu = $("#body-content").attr("menu");
            $(".tag-list ." + menu).addClass("selected")
        }
    };

    function event_bind() {
        $(".tag-list a span").click(events.inner_page_jump);
    }

    function init() {
        events.select_menu();
        event_bind();
    }

    init();
});