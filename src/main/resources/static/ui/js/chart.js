
	const _monthArray	= ['1월', '2월', '3월', '4월', '5월', '6월','7월', '8월', '9월', '10월', '11월', '12월'];
	const _colorRange	= ['#FE2371','#544FC5','#2CAFFE','#2CAFFE','#FE6A35','#6B8ABC','#1C74BD','#1C74BD','#00A6A6','#D568FB']
	
    Highcharts.setOptions({colors: ['#27187F', '#F29423']});

	function _setRemoveHighChartCredits() {
	
		setTimeout(()=>{
			$(".highcharts-credits").remove();
		},500) 
	}
	
	function _getRandomColor() {
		
	    const randomIndex = Math.floor(Math.random() * colorRange.length);
	    return colorRange[randomIndex];
	}

    function _createChart(container, categories, series  ) {
	
       const chart =  $("#"+container).highcharts({
            chart: {
                zoomType: 'xy',
                margin: [50, 0, 40, 45],
            },
            credits: { enabled: false },
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
                symbolRadius: 0,
                symbolWidth: 10,
                symbolHeight: 10,
                x:  0,
                y: -2,
            },
            plotOptions: {
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
								func ? func(event.point.options) : '' ;
				            }
				}
		        ,layoutAlgorithm: 'squarified'   
		        ,alternateStartingDirection: true 
		        ,levels: [{
		            level: 1
		            ,layoutStartingDirection: 'vertical'
		            ,layoutAlgorithm: 'squarified'
		            ,dataLabels: {
		                enabled: true
		                ,align: 'center'
		                ,verticalAlign: 'middle'
		                ,style: {
		                    fontSize: '15px'
		                    ,fontWeight: 'lighter'
		                    ,color:'white'
		                    ,textOutline: 'none'
	
		                }
		                ,formatter: function () {
		                    return '<div style="text-align: center;">' + this.point.name +'</div><br><g>' + this.point.value + '%</div>';
		                }
	
		            }
		        }]
		        ,data: treemapData
		    }]
		    ,title: {
		        text: ''
		    }
		    ,tooltip: {
		        pointFormat: '<b>{point.name}</b>:<br> {point.value}%<br>'
		    }
		    ,credits: { enabled: false },
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
		            '{series.name}: <b>{point.y} 억</b><br/>' // ◯ 문자 
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
/*
	function _comparisonChart2 (target, param){
		
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
*/
	function _lineAndColumnChart(target, param){
		
			const thisYearSales = param.thisYearSales;
			const growthRate = param.growthRate;
			
			Highcharts.chart(target, {
		    chart: {
		        zoomType: 'xy'
		    },
		    title: {
		        text: '',
		        align: 'left'
		    },
		    subtitle: {
		        text: '',
		        align: 'left'
		    },
		    xAxis: [{
		        categories: _monthArray,
		        crosshair: true
		    }],
		    yAxis: [{ // Primary yAxis
		        labels: {
		            format: '{value}%',
		            style: {
		                color: Highcharts.getOptions().colors[1]
		            }
		        },
		        title: {
		            text: '',//작년대비성장률
		            style: {
		                color: Highcharts.getOptions().colors[1]
		            }
		        }
		    }, { // Secondary yAxis
		        title: {
		            text: '',//금년매출
		            style: {
		                color: Highcharts.getOptions().colors[0]
		            }
		        },
		        labels: {
		            format: '{value}억',
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
		        align: 'center',
		        x: 50,
		        verticalAlign: 'top',
		        y: 0,
		        floating: false,
		        backgroundColor:
		            Highcharts.defaultOptions.legend.backgroundColor || // theme
		            'rgba(255,255,255,0.25)'
		    },
		    series: [{
		        name: '금년매출',
		        type: 'column',
		        yAxis: 1,
		        data: thisYearSales ,
		        tooltip: {
		            valueSuffix: ' 억원'
		        }
		
		    }, {
		        name: '작년대비성장률',
		        type: 'spline',
		        data: growthRate,
		        tooltip: {
		            valueSuffix: '%'
		        }
		    }]
		});
	}

	function _lineWithLabelsChart (target, param) {
	
		const series = param.series ;
		Highcharts.chart(target, {
		
		    title: {
		        text: '',
		        align: 'left'
		    },
		
		    subtitle: {
		        text: '',
		        align: 'left'
		    },
		
		    yAxis: {
		        title: {
		            text: ''
		        }
		    },
		
	/*	    xAxis: {
		        accessibility: {
		            rangeDescription: 'Range: 2010 to 2020'
		        }
		    },*/
			xAxis: [{
		        categories: _monthArray,
		       // crosshair: true
		    }],
	
		    legend: {
		        layout: 'vertical',
		        align: 'left',
		        verticalAlign: 'middle'
		    },
		
		    plotOptions: {
		        series: {
		            label: {
		                connectorAllowed: false
		            },
		           // pointStart: '1월'
		        }
		    },
		
		    series: series,
		
		    responsive: {
		        rules: [{
		            condition: {
		               // maxWidth: 500
		            },
		            chartOptions: {
		                legend: {
		                    layout: 'horizontal',
		                    align: 'left',
		                    verticalAlign: 'bottom'
		                }
		            }
		        }]
		    }
		
		});
	}
