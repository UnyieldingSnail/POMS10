$(function() {
    $("#main-nav li").on("click", ".loadDashboard", "load/dashboard.html", load)
    $("#main-nav li").on("click", ".loadUpdate", "load/update.html", load)
    function load(e) {
        var url = e.data;
        $("#main-nav li").removeClass("active");
        $(this).parent().addClass("active");
        $(".span9").load(url);
    }
});