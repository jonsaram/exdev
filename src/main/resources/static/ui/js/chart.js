
	const _monthArray	= ['1월', '2월', '3월', '4월', '5월', '6월','7월', '8월', '9월', '10월', '11월', '12월'];
	const _colorRange	= ['#FE2371','#544FC5','#2CAFFE','#2CAFFE','#FE6A35','#6B8ABC','#1C74BD','#1C74BD','#00A6A6','#D568FB'];
	
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

	/* 월자문 순매출 , 1회성 순매출 , 외주자문 순매출 */
    function _createChart(container, categories, series, yAxis1, yAxis2, unit ) {
	
		let yAxis1Obj =   {
			labels: {
				format: '{value}'+unit,
				style: {
				    color: '#8A8C92',
				    fontFamily: 'Noto Sans KR',
				    fontSize: '11px'
				}
			},
			title: {
			    text: ''
			}
		};
	   	if( yAxis1)yAxis1Obj = yAxis1;
		let yAxis2Obj =   { // Secondary yAxis
			title: {
			    text: '',
			    style: {}
			},
			labels: {
			    format: ' '
			},
			opposite: true
		};
	   	if( yAxis2)yAxis2Obj = yAxis2;


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
			    lineColor: '#DADADA',
			    categories: categories,
			    crosshair: true
			}],
		    yAxis: [yAxis1Obj, yAxis2Obj],
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

	//상세별 순매출 , FA 매출순위
	function _comparisonChart (target, param , unit){
		
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

		let _dataPrev,_data ;
		if( isEmpty(getData(data["months"]).slice()) ){
			_data 		= [{name: '', y: 0, color: '#FE2371'}];
			_dataPrev 	= [];
		}else{
			_data 		= getData(data["months"]).slice();
			_dataPrev 	= dataPrev["months"].slice();
		}

		Highcharts.chart(target, {
		    chart: {
		        type: 'column',

                margin: [50, 50, 40, 50],
		    },
		    xAxisData,
		    title: {
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
		    tooltip: {
		        shared: true,
		    },
			xAxis: {
			    type: 'category',
			    accessibility: {
			        description: 'xAxisData'
			    },
			    max: isEmpty(getData(data["months"]).slice()) ? 0 : xAxisData.length,
			    labels: {
			        rotation: 0, // 라벨을 0도로 회전 설정
			        style: {
			            textOverflow: 'ellipsis',
			            whiteSpace: 'nowrap',
			            overflow: 'hidden'
			        }
			    }
			},
		    yAxis: [{
		        title: {
		            text: ''
		        },
		        showFirstLabel: false,

		        labels: {
		            format: '{value}'+unit,
		            style: {
		                color: Highcharts.getOptions().colors[0]
		            }
		        },
		    }],
			series: [
			    {
			        name: lastMonth,
			        color: 'rgba(158, 159, 163, 0.5)',
			        pointPlacement: -0.3,
			        linkedTo: 'main',
			        data: _dataPrev,
			        tooltip: {
			            valueSuffix: ' '+unit+'원'
			        },
 					showInLegend: true
			    }, 
			    {
			        name: thisMonth,
			        id: 'main',
			        dataSorting: {
			            enabled: true,
			            matchByName: true
			        },
				    dataLabels: {
				        enabled: true,
				        inside: false,
				        format: '{y} '+unit,
				        style: {
				            fontSize: '10px'
				        }
				    },
			        data: _data,
			        tooltip: {
			            valueSuffix: ' '+unit+'원'
			        }
			    }
			],
		});
		
	}

	// 순매출 성장률
	function _lineAndColumnChart(target, param , unit){
	
		const thisYearSales = param.thisYearSales;
		const growthRate = param.growthRate;
		
		Highcharts.chart(target, {
		    chart: {
		        zoomType: 'xy',
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
		            format: '{value}'+unit,
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
		        x: 120,
		        verticalAlign: 'top',
		        y: 0,
		        itemStyle: {
		            color: '#8A8C92',
		            fontFamily: 'Noto Sans KR',
		            fontSize: '11px'
		        },
		        floating: false,
		        backgroundColor:{
		            backgroundColor: Highcharts.defaultOptions.legend.backgroundColor || 'rgba(255,255,255,0.25)'
				}
		    },
		    series: [{
		        name: '금년매출',
		        type: 'column',
		        yAxis: 1,
		        data: thisYearSales ,
		        tooltip: {
		            valueSuffix: ' '+unit+'원'
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

	//지사별 순매출
	function _lineWithLabelsChart (target, param, unit) {

		let series = param.series ;
		const thisYear = param.thisYear;
		let maxVal = 0;
		
		if( series.length == 0){
			series = [{
		        name: thisYear+' 년',
		        data: [0,0,0,0,0,0,0,0,0,0,0,0]
		    	}];
		}else {
			
			series.forEach( srs =>{
				
				const tmpVal = Math.max(...srs.data);
				
				if(maxVal < tmpVal)maxVal=tmpVal;
				
			} )
	
			maxVal = maxVal * 1.2;
		}
		
		Highcharts.chart(target, {
			chart :{
				type: 'line',
			  	margin: [50, 50, 40, 60],
			},
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
		        },
		        labels: {
		            format: '{value} '+unit,
		            style: {
		                width: '60px',
		                lineHeight: '20px'
		            }
		        },
				max: maxVal,
		    },
		    xAxis: {
		        categories: _monthArray,
		    },
		    legend: {
		        x: 100,
		        y: -270,
		        itemStyle: {
		            color: '#8A8C92',
		            fontFamily: 'Noto Sans KR',
		            fontSize: '11px'
		        },
		        floating: true,
		    },
		    tooltip: {
		        shared: true
		    },
		    series:series
		});
	}
	
	function _lineRateChart(container, categories, series  ) {
	
       const chart =  $("#"+container).highcharts({
            chart: {
				type : 'line',
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
                        color: '#545454',
                        fontFamily: 'Noto Sans KR',
                        fontSize: '11px'
                    }
                },
                lineColor: '#dddddd',
                categories: categories,
                crosshair: true
            }],
            yAxis: [{
                labels: {
                    format: '{value}%',
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
                    color: '#545454',
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
                        enabled: true
                    },
		            dataLabels: {
		                enabled: true
		            },
		            enableMouseTracking: false
                }
            },
			series : series
        });
    }

	function _barLineChart(container, categories, series  ) {
	
       const chart =  $("#"+container).highcharts({
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
                labels: {
                    style: {
                        color: '#545454',
                        fontFamily: 'Noto Sans KR',
                        fontSize: '11px'
                    }
                },
                lineColor: '#dddddd', /* x축 라인색 */
                categories: categories,
                crosshair: true
            }],
		    yAxis: [{ // Primary yAxis
		        labels: {
		            format: '{value} 만원',
		            style: {
		                color: Highcharts.getOptions().colors[1]
		            }
		        },
		        title: {
		            text: '1주당 주식가액',
		            style: {
		                color: Highcharts.getOptions().colors[1]
		            }
		        }
		    }, { // Secondary yAxis
		        title: {
		            text: '기업가치',
		            style: {
		                color: Highcharts.getOptions().colors[0]
		            }
		        },
		        labels: {
		            format: '{value} 억원',
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
            plotOptions: {
                line: {
                    marker: {
                        enabled: true
                    },
		            dataLabels: {
		                enabled: true
		            },
		            enableMouseTracking: false
                },
                column: {
                    marker: {
                        enabled: true
                    },
		            dataLabels: {
		                enabled: true
		            },
		            enableMouseTracking: false
                },
            },
			series : series
        });
    }

	function _pieChart(container, series  ) {
	
		$("#"+container).highcharts({
		    chart: {
		        plotBackgroundColor: null,
		        plotBorderWidth: 0,
		        plotShadow: false,
		        margin: [0, 0, 0, 0],
		    },
		    credits: {enabled: false},// 하이차트 워터마크 삭제
		    title: {
		        text: '',

		    },
		    tooltip: {
		        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		    },
		    legend: {
		        x:30,
		        verticalAlign: 'bottom',
		        y:5,
		        itemStyle: {
		            fontWeight:'bold',
		            fontFamily: 'Noto Sans KR',
		            fontSize: '14px'
		        },
		    },
		    accessibility: {
		        point: {
		            valueSuffix: '%'
		        }
		    },
		    plotOptions: {
		        pie: {
		            dataLabels: {
		                enabled: false
		            },
		            startAngle: -140,
		            endAngle: 220,
		            center: ['50%', '50%'],
		            size: '100%',
		            borderRadius: 50,
		            showInLegend: true
		        },

		    },
		    colors: ['#3ba8c3', '#fff'],
		    series: series
		});
	}
	
	function _1bar2LineChart(container, categories, series  ) {
	
       const chart =  $("#"+container).highcharts({
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
                labels: {
                    style: {
                        color: '#545454',
                        fontFamily: 'Noto Sans KR',
                        fontSize: '11px'
                    }
                },
                lineColor: '#dddddd', /* x축 라인색 */
                categories: categories,
                crosshair: true
            }],
		    yAxis: [{ // Primary yAxis
		        labels: {
		            format: '{value} %',
		            style: {
		                color: Highcharts.getOptions().colors[1]
		            }
		        },
		        title: {
		            text: '부담률',
		            style: {
		                color: Highcharts.getOptions().colors[1]
		            }
		        }
		    }, { // Secondary yAxis
		        title: {
		            text: '10%지분가치',
		            style: {
		                color: Highcharts.getOptions().colors[0]
		            }
		        },
		        labels: {
		            format: '{value} 억원',
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
            plotOptions: {
                line: {
                    marker: {
                        enabled: true
                    },
		            dataLabels: {
		                enabled: true
		            },
		            enableMouseTracking: false
                },
                column: {
                    marker: {
                        enabled: true
                    },
		            dataLabels: {
		                enabled: true
		            },
		            enableMouseTracking: false
                },
            },
			series : series
        });
    }
	
	// 고객 체결 추이 
	function _1stacked1LineChart(container, categories, series, unit ) {
		
		Highcharts.chart(container, {
			 	    chart: {
			 	        type: 'column'
			 	    },
 					credits: {enabled: false},// 하이차트 워터마크 삭제
			 	    title: {
			 	        text: '',
			 	        align: 'left'
			 	    },
				    legend: {
				        x:200,
				        verticalAlign: 'top',
				        y:0,
		        		floating: true,
				        itemStyle: {
				            fontWeight:'bold',
				            fontFamily: 'Noto Sans KR',
				            fontSize: '12px'
				        },
				    },
			 	    xAxis: {
			 	        categories: categories
			 	    },
			 	    yAxis: [{
			 	        allowDecimals: false,
			 	        min: 0,
			 	        title: {
			 	            text: '계약금액',
				            style:{
				            	color:"#27187F",
		                        fontFamily: 'Noto Sans KR',
		                        fontSize: '12px',
		                        fontWeight: 'bold'
				            }
			 	        },
				        labels: {
				            format: '{value} '+unit,
				            style: {
				                color: Highcharts.getOptions().colors[0]
				            }
				        },

			 	    }, {
			 	        title: {
			 	            text: '계약건수',
					            style: {
					                color: Highcharts.getOptions().colors[1]
					            }
			 	        },
				        labels: {
				            format: '{value} 건',
				            style: {
				                color: Highcharts.getOptions().colors[2]
				            }
				        },
			 	        opposite: true
			 	    }],
			 	    tooltip: {
		        		shared: true
					    ,formatter: function () {

							const idx		= Number(this.x.replace("월","")) -1;
							const monthly 	= this.points[0].series.userOptions;
							const once 		= this.points[1].series.userOptions;
							const contractedCnt = this.points[2].series.userOptions;
					        let tooltip = '<b>' + this.x + '</b><br/>';
				            	tooltip += '<span style="color:' + this.points[0].color + '">\u25CF</span> ';
					            tooltip += monthly.name 		+ ': ' + monthly.data[idx] + unit +'<br>';
					            tooltip += '<span style="color:' + this.points[1].color + '">\u25CF</span> ';
					            tooltip += once.name 			+ ': ' + once.data[idx] + unit +'<br>';
					            tooltip += '<span style="color:' + this.points[2].color + '">\u25CF</span> ';
					            tooltip += contractedCnt.name 	+ ': ' + contractedCnt.data2[idx] +'건'+' <br>( 누적'+ contractedCnt.data[idx] + '건)';
					        
					        return tooltip;
					    }
						 
			 	    },
			 	    plotOptions: {
			 	        column: {
			 	            stacking: 'normal'
			 	        }
			 	    },
			 	    series: series
			 	}); 

	}	
	
	//  FA 수수료 합계 추이 차트 
	function _1Bar1Line1HorizentalLineChart(container,maxVal, yearlyTargetPayment, series, unit ) {
		
		return  Highcharts.chart(container, {
						chart: {
					        zoomType: 'xy'
					    },
					    credits: {enabled: false},
					    title: {
					        text: '',
					        align: 'left'
					    },
					    xAxis: [{
					        categories: _monthArray,
					        crosshair: true
					    }],
					    yAxis: [
					    	{
						        title: {
						            text: '월별 수수료',
						            style:{
						            	color:"#27187F",
				                        fontFamily: 'Noto Sans KR',
				                        fontSize: '12px',
				                        fontWeight: 'bold'
						            }
						        },
						        labels: {
						            format: '{value} '+unit,
						        },
						    },
					    	{
						        title: {
						            text: '수수료 합계 추이',
						            style: {
						                color: Highcharts.getOptions().colors[1]
						            }
						        },
						        labels: {
						            format: '{value}'+unit ,
						            style: {
				                        color: '#8A8C92',
				                        fontFamily: 'Noto Sans KR',
				                        fontSize: '11px'
				                    }
						        },
						        opposite: true,
						        max: maxVal,
						        plotLines: [{
									value: yearlyTargetPayment ,
						            color: "#75ba75",
						            width: 2,
						            label: {
						                text: '목표 '+yearlyTargetPayment +" 백만",
						                align: 'right'
						            },
						            id: 'plot-line-id'
											}]
					    	}
						],
					    tooltip: {
					        shared: true
					    },
					    legend: {
					        align: 'left',
					        x: 630,
					        verticalAlign: 'top',
					        y: -15,
					        floating: true,
					        backgroundColor:
					            Highcharts.defaultOptions.legend.backgroundColor ||
					            'rgba(255,255,255,0.25)'
					    },
	 				    series: series  
					});	
	}
	
	function _bubbleChart (container , series , unit) {
		
		const formatter =  function() {

					if( container == "#bubbleChart3")
		            return '<span style="color:#27187F">\u25CF</span> ' + 
							'<b>' + this.point.name + '</b><br>' +
			                '계약건수: ' + this.y + '건<br>' +
			                '매출: ' + this.x + unit+'<br>' +
			                '전체계약대비: ' + this.point.z+'%';
					else 
						return '<span style="color:#27187F">\u25CF</span> ' + 
							'<b>' + this.point.name + '</b><br>' +
			                '계약건수: ' + this.x + '건<br>' +
			                '매출: ' + this.y + unit+'<br>' +
			                '전체매출대비: ' + this.point.z+'%';
        }

		$(container).highcharts({
			chart: {
		        type: 'bubble',
		        zoomType: 'xy'
		    },
		    credits: {enabled: false},// 하이차트 워터마크 삭제
		    title: {
		        text: '',
		        align: 'left'
		    },
		    legend: {
		        enabled: false
		    },
		    colors: ['#3ba8c3', '#8A8C92'],
		    xAxis: {
		        labels: {
		          style: {
		            color: '#8A8C92',
		            fontFamily: 'Noto Sans KR',
		            fontSize: '11px'
		          }
		        },
		      lineColor: '#DADADA', /* x축 라인색 */
		    },
	
		    yAxis: {
		        labels: {
		          format: '{value}'+ (container == "#bubbleChart3" ? "건" : unit),
		          style: {
		            color: '#8A8C92',
		            fontFamily: 'Noto Sans KR',
		            fontSize: '11px'
		          }
		        },
		        title: {
		          text: ''
		        }
		    },

		    tooltip: {
        		shared: true,
		        formatter: formatter
			},
		    series: series
		});		
		
	}