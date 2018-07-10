function indexFn() {
    require(['jquery', 'echarts'], function ($, echarts) {
        console.log(echarts)
        $(function () {
            $(".content").on("click", "div", function () {
                $(".window").toggle();
                if ($(".window").is(":visible")) {
                    var weiboId = $(this).data("data");
                    console.log("weiboID:" + weiboId)
                    buildData(weiboId);
                }
            })

        });

        function buildData(weiboId) {
            $.ajax({
                type: "GET",
                url: base_path + "/weibo/predict",
                data: {weiboId: weiboId},
                dataType: "json",
                success: function (res) {
                    console.log(res);
                    var data = res.data;
                    var result = data.truth;
                    var pre = data.pre;
                    $("#first").empty();
                    //定义数据结构
                    var datas = {
                        'colors': ['#509624', '#4cabce'],
                        'xAxis': ['一天后', '两天后'],
                        'legend': ['预测'],
                        'list': [{
                            'title': '舆情预测',
                            'series': [pre],
                            'elid': 'first'
                        }]
                    };
                    for (var i = 0; i < datas['list'].length; i++) {
                        canvasEcharts(datas, i, (pre[1] + 10));
                    }
                }
            });
        }

        function canvasEcharts(json, index, ym) {
            var obj = json['list'][index];
            var myChats = echarts.init(document.getElementById(obj['elid']));
            var option = {
                title: {
                    text: obj['title'],
                    // subtext: index==0?'数据来自投票结果而时时变化':''  //只有第一个要副标题
                    //主标题文本超链接
                    //link: 'http://www.xxxxxxxxxx',
                    subtext: '数据来自新浪微博'
                },
                color: ['#006699', '#4cabce'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    left: "70%",
                    data: ['实际', '预测']
                },
                grid: {
                    left: '3%',
                    right: '3%',
                    bottom: '3%',
                    containLabel: true,
                },
                xAxis: [{
                    min: 0,
                    //坐标轴类型，横轴默认为类目型'category'
                    type: 'category',
                    data: json['xAxis']
                }],
                yAxis: [{
                    min: 0,
                    max: ym,
                    //坐标轴类型，纵轴默认为数值型'value'
                    type: 'value'
                }],
                series: (function () {
                    var arr = [];
                    for (var i = 0; i < obj['series'].length; i++) {
                        var thisobj = {
                            name: json['legend'][i],
                            type: 'bar',
                            barWidth: '15%',
                        };
                        thisobj.data = obj['series'][i];
                        arr.push(thisobj)
                    }
                    return arr
                })()
            };
            //为echarts对象加载数据
            myChats.setOption(option, true);
        }

    });
}

require(['config'], indexFn);
