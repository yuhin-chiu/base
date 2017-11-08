/**
 * Created by yuxuanjiao on 2017/4/17.
 */
$(function () {
    var events = {
        init_page: function () {
            var type = $("body").attr("page");
            $(".header-content .header-main ." + type).addClass("selected");
        },
        jump: function () {
            console.log("jump");
            if ($(this).hasClass("selected")) {
                return;
            }
            var type = $(this).text();
            
            if (type == "新闻中心") {
                location.href = "/backend/news";
            } else if (type == "津弛产品") {
                location.href = "/backend/product";
            } else if (type == "关于津弛") {
                location.href = "/backend/about";
            } else if (type == "合作媒体") {
                location.href = "/backend/medias";
            } else if (type == "历届回顾") {
                location.href = "/backend/histories";
            } else if (type == "申请管理") {
                location.href = "/backend/applies";
            } else if (type == "下载管理") {
                location.href = "/backend/downloads";
            } else if(type == "其他"){
                location.href = "/backend/demo";
            }
        }
    };

    function bind_event() {
        $(".header-content .header-main div span").click(events.jump);
    }

    function init() {
        events.init_page();
        bind_event();
    }

    init();
});