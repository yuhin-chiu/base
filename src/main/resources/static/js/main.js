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
            } else if (type == "首页") {
                location.href = "/backend/homepage";
            } else if (type == "企业文化") {
                location.href = "/backend/culture";
            } else if (type == "联系我们") {
                location.href = "/backend/contact";
            } else if (type == "下载管理") {
                location.href = "/backend/downloads";
            } else if(type == "其他"){
                location.href = "/backend/demo";
            }
        },
        goto_home: function() {
            window.open("http://www.jinchichina.com/", "_blank"); 
        }
    };

    function bind_event() {
        $(".header-content .header-main div span").click(events.jump);
        $(".logo").click(events.goto_home);
    }

    function init() {
        events.init_page();
        bind_event();
    }

    init();
});