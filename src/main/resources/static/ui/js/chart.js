

	const _monthArray	= ['1월', '2월', '3월', '4월', '5월', '6월','7월', '8월', '9월', '10월', '11월', '12월'];
	const _colorRange	= ['#FE2371','#544FC5','#2CAFFE','#2CAFFE','#FE6A35','#6B8ABC','#1C74BD','#1C74BD','#00A6A6','#D568FB']
	
	function _getRandomColor() {
		
	    const randomIndex = Math.floor(Math.random() * colorRange.length);
	    return colorRange[randomIndex];
	}

    Highcharts.setOptions({
        colors: ['#27187F', '#F29423']
    });

    function _createChart(container, categories, series  ) {
	
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

        });
 
    }
 
function _treemapChart(target, colorAxis, treemapData, func){

	Highcharts.chart(target, {
		    colorAxis: colorAxis,
	    series: [{
	        type: 'treemap'
			,events: {
			            click: function (event) {

							if( func )
								func(event.point.options);
			            }
			}
	        ,layoutAlgorithm: 'squarified',   
	        alternateStartingDirection: true, 
	        levels: [{
	            level: 1,
	            layoutStartingDirection: 'vertical',
	            layoutAlgorithm: 'squarified',
	            dataLabels: {
	                enabled: true,
	                align: 'center',
	                verticalAlign: 'middle',
	                style: {
	                    fontSize: '15px',
	                    fontWeight: 'lighter',
	                    color:'white',
	                    textOutline: 'none'

	                },
	                formatter: function () {
	                    return '<div style="text-align: center;">' + this.point.name +'</div><br><g>' + this.point.value + '%</div>';
	                }

	            }
	        }],
	        data: treemapData
	    }],
	    title: {
	        text: ''
	    },
	    tooltip: {
	        pointFormat: '<b>{point.name}</b>:<br> {point.value}%<br>'
	    },
	    credits: { enabled: false },
	    
	})

}

function _comparisonChart (target, param){
	
	const thisMonth =	param.thisMonth;
	const lastMonth =	param.lastMonth;
	const data 		=	param.data;
	const dataPrev 	=	param.dataPrev;
	const xAxisData =	param.xAxisData;

	for (const [key, value] of Object.entries(xAxisData)) {
	    value.ucCode = key.toUpperCase();
	}
	
	const getData = data => data.map(point => ({
	    name: point[0],
	    y: point[1],
	    color: xAxisData[point[0]].color
	}));
	
	Highcharts.chart(target, {
	    chart: {
	        type: 'column'
	    },
	    xAxisData,
	    title: {
	        text: '',
	        align: 'left'
	    },
	    subtitle: {
	        text: '',
	        align: 'left'
	    },
	    plotOptions: {
	        series: {
	            grouping: false,
	            borderWidth: 0
	        }
	    },
	    legend: {
	        enabled: false
	    },
	    tooltip: {
	        shared: true,
	        headerFormat: '<span style="font-size: 15px">' +
	            '{point.key}' +
	            '</span><br/>',
	        pointFormat: '<span style="color:{point.color}">\u25CF</span> ' +
	            '{series.name}: <b>{point.y} 억</b><br/>'
	    },
	    xAxis: {
	        type: 'category',
	        accessibility: {
	            description: 'xAxisData'
	        },
	        max: xAxisData.length,
	        labels: {
	            useHTML: true,
	            animate: true,
	            format: '{value}<br>' +
	                '<span class="f32">' +
	                '<span style="display:inline-block;height:32px;vertical-align:text-top;" ' +
	                'class="flag {value}"></span></span>',
	            style: {
	                textAlign: 'center'
	            }
	        }
	    },
	    yAxis: [{
	        title: {
	            text: ''

	        },
 			format: '{value}억',
	        showFirstLabel: false
	    }],
	    series: [
		{
	        name: lastMonth,
	        color: 'rgba(158, 159, 163, 0.5)',
	        pointPlacement: -0.3,
	        linkedTo: 'main',
	        data: dataPrev["months"].slice()
	    }, 
		{
	        name: thisMonth,
	        id: 'main',
	        dataSorting: {
	            enabled: true,
	            matchByName: true
	        },
	        dataLabels: [{
	            enabled: true,
	            inside: false,
	            style: {
	                fontSize: '10px'
	            }
	        }],
	        data: getData(data["months"]).slice()
	    }],
	    exporting: {
	        allowHTML: true
	    }
	});
	
}

function _lineAndColumn_chart(target){
	
	Highcharts.chart(target, {
    chart: {
        zoomType: 'xy'
    },
    title: {
        text: 'Karasjok weather, 2021',
        align: 'left'
    },
    subtitle: {
        text: '',
        align: 'left'
    },
    xAxis: [{
        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
            'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
        crosshair: true
    }],
    yAxis: [{ // Primary yAxis
        labels: {
            format: '{value}°C',
            style: {
                color: Highcharts.getOptions().colors[1]
            }
        },
        title: {
            text: 'Temperature',
            style: {
                color: Highcharts.getOptions().colors[1]
            }
        }
    }, { // Secondary yAxis
        title: {
            text: 'Precipitation',
            style: {
                color: Highcharts.getOptions().colors[0]
            }
        },
        labels: {
            format: '{value} mm',
            style: {
                color: Highcharts.getOptions().colors[0]
            }
        },
        opposite: true
    }],
    tooltip: {
        shared: true
    },
    legend: {
        align: 'left',
        x: 80,
        verticalAlign: 'top',
        y: 60,
        floating: true,
        backgroundColor:
            Highcharts.defaultOptions.legend.backgroundColor || // theme
            'rgba(255,255,255,0.25)'
    },
    series: [{
        name: 'Precipitation',
        type: 'column',
        yAxis: 1,
        data: [27.6, 28.8, 21.7, 34.1, 29.0, 28.4, 45.6, 51.7, 39.0,
            60.0, 28.6, 32.1],
        tooltip: {
            valueSuffix: ' mm'
        }

    }, {
        name: 'Temperature',
        type: 'spline',
        data: [-13.6, -14.9, -5.8, -0.7, 3.1, 13.0, 14.5, 10.8, 5.8,
            -0.7, -11.0, -16.4],
        tooltip: {
            valueSuffix: '°C'
        }
    }]
});
}
