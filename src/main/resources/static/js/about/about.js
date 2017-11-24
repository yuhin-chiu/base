/**
 * Created by yuxuanjiao on 2017/08/05.
 */
$(function() {
    var events = {
        inner_page_jump : function() {
            var cur = $(this);
            if (cur.hasClass("list")) {
                location.href = "/backend/about/list";
            } else if (cur.hasClass("dszzc")) {
                location.href = "/backend/about/dszzc";
            } else if (cur.hasClass("jcgk")) {
                location.href = "/backend/about/jcgk";
            } else if (cur.hasClass("qyys")) {
                location.href = "/backend/about/qyys";
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