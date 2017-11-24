/**
 * Created by jiaoyuxuan on 2017/8/8.
 */
$(function() {
    var baseUri = "about";

    function langChange() {
        $("#present-table").basePresent.clearField();
        $.get("/api/" + baseUri + "/list", {parent : 3, lang : $('.present-select').eq(0).val()}, function(data) {
            $("#present-id").val(data.rows[0].id);
            $("#present-table").basePresent.setCondition(data.rows[0]);
        });
    }
    function init() {
        //event_bind();
        $("#present-table").basePresent(baseUri);
        $('.present-select').change(langChange);
        $("#present-canBtn").click(langChange);
        langChange();
    }

    init();
});