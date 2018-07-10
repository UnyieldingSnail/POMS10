function indexFn() {
  require(['jquery', 'echarts'], function($, echarts) {
    console.log(echarts)
      $(function() {
          buildData();
      });

    alert("ashdfhsdaf");
      function buildData() {
          //定义数据结构
          var datas = {
              'colors': ['#006699', '#4cabce', '#e5323e'],
              'xAxis': ['一天', '两天'],
              'legend':['实际','预测'],
              'list': [{
                  'title': '舆情预测',
                  'series': [[9, 30, 45, 40, 20, 38, 50, 40],[60, 50, 48, 30, 45, 5, 55, 30]],
                  'elid': 'first'
              }]
          };
          for (var i = 0; i < datas['list'].length; i++) {
              canvasEcharts(datas, i);
          }
      }

      function canvasEcharts(json, index) {
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
              toolbox: {
                  //显示策略，可选为：true（显示） | false（隐藏），默认值为true
                  show: true,
                  //垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center' | {number}（y坐标，单位px）
                  y: 'center',
                  feature: {
                      //saveAsImage，保存图片（IE8-不支持）,图片类型默认为'png'
                      saveAsImage: { show: true }
                  }
              },
              legend: {
                  left: "70%",
                  data: ['实际', '预测']
              },
              grid: {
                  left: '3%',
                  right: '40%',
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
                  max: 60,
                  //坐标轴类型，纵轴默认为数值型'value'
                  type: 'value'
              }],
              series: (function(){
                  var arr=[];
                  for (var i = 0; i < obj['series'].length; i++) {
                      var thisobj={
                          name: json['legend'][i],
                          type: 'bar',
                          barWidth: '15%',
                      };
                      thisobj.data=obj['series'][i];
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
