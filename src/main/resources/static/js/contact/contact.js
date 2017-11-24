/**
 * Created by yuxuanjiao on 2017/08/05.
 */
$(function() {
    var events = {
        inner_page_jump : function() {
            var cur = $(this);
            if (cur.hasClass("list")) {
                location.href = "/backend/contact/list";
            } else if (cur.hasClass("lxfs")) {
                location.href = "/backend/contact/lxfs";
            } else if (cur.hasClass("rlzy")) {
                location.href = "/backend/contact/rlzy";
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