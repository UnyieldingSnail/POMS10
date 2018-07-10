function bar() {
    var data = new Array();
    var ds = new Array();

    data.push([[1, 25], [2, 34], [3, 37], [4, 45], [5, 45], [6, 45], [7, 45], [8, 56]]);
    data.push([[1, 25], [2, 34], [3, 37], [4, 45], [5, 45], [6, 45], [7, 45], [8, 56]]);

    for (var i = 0, j = data.length; i < j; i++) {

        ds.push({
            data: data[i],
            grid: {
                hoverable: true
            },
            bars: {
                show: true,
                barWidth: 0.3,
                order: 1,
                lineWidth: 0.5,
                fillColor: {colors: [{opacity: 0.65}, {opacity: 1}]}
            }
        });
    }

    $.plot($("#bar-chart"), ds, {
        colors: ["#F90", "#3C4049", "#666", "#BBB"],
        xaxis: {
            ticks:
                [[1, "东北综合经济区"],
                    [2, "北部沿海经济区"],
                    [3, "东部沿海经济区"],
                    [4, "南部沿海经济区"],
                    [5, "长江中游经济区"],
                    [6, "黄河中游经济区"],
                    [7, "西南经济区"],
                    [8, "大西北经济区"]]
        }
    });
}

bar();