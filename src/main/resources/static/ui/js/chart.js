
let MAIN_CHART = [];

    Highcharts.setOptions({
        colors: ['#27187F', '#F29423']
    });

    var chartData1 = {
        categories: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        seriesData2023: [70, 140, 190, 180, 195, 240, 160, 140, 0, 0, 0, 0],
        seriesData2022: [155, 170, 185, 170, 160, 175, 165, 190, 160, 170, 160, 150]
    };

    var chartData2 = {
        categories: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        seriesData2023: [70, 140, 160, 180, 190, 240, 160, 140, 0, 0, 0, 0],
        seriesData2022: [155, 160, 180, 170, 140, 180, 160, 180, 160, 155, 180, 150]
    };

    var chartData3 = {
        categories: ['월자문', '일반', '법인전환', 'CEO플랜', '연구소및특허', '정책자금', '대출', '기타'],
        seriesData2023: [170, 110, 58, 50, 40, 30, 20, 10],
        seriesData2022: [240, 140, 130, 140, 70, 75, 50, 25]
    };

    function createChart(container, categories, series  ) {
	
       const chart =  $("#"+container).highcharts({
            chart: {
                zoomType: 'xy',
                margin: [50, 0, 40, 45],
            },
            credits: { enabled: false }, // 하이차트 워터마크 삭제
            title: {
                text: '',
                align: 'left'
            },
            subtitle: {
                text: '',
                align: 'left'
            },
            xAxis: [{
                labels: {
                    style: {
                        color: '#8A8C92',
                        fontFamily: 'Noto Sans KR',
                        fontSize: '11px'
                    }
                },
                lineColor: '#DADADA', /* x축 라인색 */
                categories: categories,
                crosshair: true
            }],
            yAxis: [{
                labels: {
                    format: '{value}억',
                    style: {
                        color: '#8A8C92',
                        fontFamily: 'Noto Sans KR',
                        fontSize: '11px'
                    }
                },
                title: {
                    text: ''
                }
            }, { // Secondary yAxis
                title: {
                    text: '',
                    style: {}
                },
                labels: {
                    format: ' '
                },
                opposite: true
            }],
            tooltip: {
                shared: true
            },
            legend: {
                align: 'right',
                x: 0,
                verticalAlign: 'top',
                y: 0,
                itemStyle: {
                    color: '#8A8C92',
                    fontFamily: 'Noto Sans KR',
                    fontSize: '11px'
                },
                symbolRadius: 0, //범례 심볼 radius 지정
                symbolWidth: 10,
                symbolHeight: 10,
                x: 0, //가로 위치 지정.
                y: -2, //세로 위치 지정.
            },
            plotOptions: { //라인에 원표시 삭제
                line: {
                    marker: {
                        enabled: false
                    }
                }
            },
			series : series
        /*     series: [
				{
                name: '2023년',
                type: 'column',
                yAxis: 0,
                data: [10, 20, 30, 40, 50, 50, 50, 50, 50, 50, 50, 50],
                tooltip: {
                    valueSuffix: '억'
                }

            }, {
                name: '2022년',
                type: 'line',
                data: [155, 170, 185, 170, 160, 175, 165, 190, 160, 170, 160, 150],
                tooltip: {
                    valueSuffix: '억'
                }
            }]*/
        });
 
		
    }
 
/*});*/
