$(function () {
    var pageNum = 0;
    $.ajax({
        type: "GET",
        url: base_path + "/weibo/all",
        data: {pageNum: pageNum},
        dataType: "json",
        success: function (res) {
            pageNum = pageNum + 1;
            console.log(pageNum);
            console.log('dfsdfds');
            var $tbl = $(".content");
            console.log(res);
            var result = res.data;
            for (var i = 0; i < result.length; i++) {
                var weiboId = result[i].id;
                var userName = result[i].userName;
                var createdAt = result[i].createdAt;
                var attitudesCount = result[i].attitudesCount;
                var commentsCount = result[i].commentsCount;
                var repostsCount = result[i].repostsCount;
                var content = result[i].content;
                var html = '<div><h3>' + userName + '</h3>' +
                    '<span><i class="icon-time"></i>&nbsp;' + createdAt + '</span>' +
                    '<span><i class="icon-share"></i>&nbsp;' + repostsCount + '</span>' +
                    '<span><i class="icon-heart"></i>&nbsp;' + attitudesCount + '</span>' +
                    '<span><i class="icon-comment"></i>&nbsp;' + commentsCount + '</span>' +
                    '<div>' + content + '</div></div>';
                var $html = $(html);
                $html.data("data", weiboId);
                $tbl.append($html);
                console.log(weiboId);
                console.log($html.data("data"));
                console.log($(".content>div:last").data("data"));

            }
        }
    });



    var totalHeight = 0; //定义一个总高度变量
    function ata(){ //loa动态加载数据
        totalHeight =  parseFloat( $(window).height() ) +  parseFloat( $(window).scrollTop() ); //浏览器的高度加上滚动条的高度
        if ( $(document).height() <= totalHeight ) { //当文档的高度小于或者等于总的高度时，开始动态加载数据
            console.log("jxzduuju");
            $.ajax({
                type: "GET",
                url: base_path + "/weibo/all",
                data: {pageNum: pageNum},
                dataType: "json",
                success: function (res) {
                    pageNum = pageNum + 1;
                    console.log(pageNum);
                    console.log('dfsdfds');
                    var $tbl = $(".content");
                    console.log(res);
                    var result = res.data;
                    for (var i = 0; i < result.length; i++) {
                        var weiboId = result[i].id;
                        var userName = result[i].userName;
                        var createdAt = result[i].createdAt;
                        var attitudesCount = result[i].attitudesCount;
                        var commentsCount = result[i].commentsCount;
                        var repostsCount = result[i].repostsCount;
                        var content = result[i].content;
                        var html = '<div><h3>' + userName + '</h3>' +
                            '<span><i class="icon-time"></i>&nbsp;' + createdAt + '</span>' +
                            '<span><i class="icon-share"></i>&nbsp;' + repostsCount + '</span>' +
                            '<span><i class="icon-heart"></i>&nbsp;' + attitudesCount + '</span>' +
                            '<span><i class="icon-comment"></i>&nbsp;' + commentsCount + '</span>' +
                            '<div>' + content + '</div></div>';
                        var $html = $(html);
                        $html.data("data", weiboId);
                        $tbl.append($html);
                        console.log(weiboId);
                        console.log($html.data("data"));
                        console.log($(".content>div:last").data("data"));

                    }
                }
            });
        }
    }
    $(window).bind("scroll",function(){
        ata();
    })



    $.ajax({
        type: "GET",
        url: base_path + "/weibo/describe",
        success: function (res) {
            console.log(res);
            var data = res.data;
            var weiboNum = data.weiboNum;
            var repostNum = data.repostNum;
            $(".stat-holder>.weibo>p").html(weiboNum);
            $(".stat-holder>.user>p").html(repostNum);

        }
    });

});