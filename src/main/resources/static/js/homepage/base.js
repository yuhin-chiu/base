/**
 * Created by jiaoyuxuan on 2017/8/8.
 */
$(function() {
    var baseUri = "homepage";

    function langChange() {
        $("#present-table").basePresent.clearField();
        $.get("/api/" + baseUri + "/all", {lang : $('.present-select').val()}, function(data) {
            $("#present-id").val(data.data.id);
            $("#present-table").basePresent.setCondition(data.data);
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