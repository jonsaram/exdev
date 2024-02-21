function _numberWithCommas (num) {
	
            return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}


function _chkHandler(pageId, page)
{
			
    $("#"+pageId+"_ckAll").on('click',function() {

    	const isAllChk =  $(this).prop("checked");
		$("[id^='"+pageId+"_ck_']").prop("checked", isAllChk);
		
    	const ckboxes = $("[id^='"+pageId+"_ck_']");
	    pageId.checkedItems = isAllChk ? ckboxes.map(function() { return this.value; }).get() : [];

    });
    $("[id^='"+pageId+"_ck_']").on('click',function() {

	        if ($(this).prop("checked")){page.checkedItems.push($(this).val());}
    	else { page.checkedItems = page.checkedItems.filter(item => item !== $(this).val());}  
        
        const $checkboxSelector = $("[id^='"+pageId+"_ck_']")
        const allCheckboxChecked = $checkboxSelector.length === $checkboxSelector.filter(":checked").length;
        $("#"+pageId+"_ckAll").prop("checked", allCheckboxChecked);
        
    });
		    
}

function _excelUpload(pageId,queryId,searchFnc)
{
	
	const fileInput = $('#'+pageId+'_excelFileInput')[0];
    const file = fileInput.files[0];

    const formData = new FormData();
    formData.append('file', file);

    $.ajax({
        url: '/excelUpload.do',
        type: 'POST',
        data: formData,
        contentType: false,
        processData: false,
        success: function(response) {

        	const jsonData  = JSON.parse(response.data);

            const tableContainer = document.getElementById(pageId+'_uploadExcelResult');
            const table = document.createElement('table');
			
            const columns = [];
            const thead = document.createElement('thead');

            const theadRow = document.createElement('tr');
            for (const key in jsonData[0]) {
                if (Object.hasOwnProperty.call(jsonData[0], key)) {
                    const th = document.createElement('th');
                    th.textContent = key;
                    theadRow.appendChild(th);
                    columns.push( key );
                }
            }
            thead.appendChild(theadRow);
            table.appendChild(thead);

            const tbody = document.createElement('tbody');
            let index = 0;
            
            for (const item of jsonData) {
            	
                const tbodyRow = document.createElement('tr');
                tbodyRow.setAttribute("name",pageId+"_excelTr_"+index++ )
                for (const column of columns) {

                    const td = document.createElement('td');
                    td.setAttribute( "id" , column );
                    td.setAttribute( "value" , item[column]  );
                    td.textContent = item[column] || '';

                    tbodyRow.appendChild(td);
                }

                tbody.appendChild(tbodyRow);
            }
            
            table.appendChild(tbody);

            tableContainer.appendChild( table);

			const $trs = $("[name^='"+pageId+"_excelTr_']");
			let paramRows = []
			
			$.each( $trs , function(rIdx) {
				
				const $tds = $("[name='"+pageId+"_excelTr_"+rIdx+"'] td");
				let param = [];	 	
				$.each( $tds , function(){
					console.log(""+this)
					const key = $(this).attr("id");
					const val = $(this).attr("value");
					param.push( { "key": key, "val": val } );
					
				})
				
				paramRows.push( param );
				
			})

			const prlength = paramRows.length;
									
			paramRows.forEach( (row,idx)  => {
				
				let newRow = {}
				row = row.map( obj =>{
					newRow[obj.key] = obj.val;
				})
				
				var parm = {
						 queryId 		: queryId
						,requestParm	: newRow
				}
	
				C_COM.requestQuery(parm, function(resultData) {
					
					if( resultData.state == 'S' &&  idx+1 >= prlength ){
							setTimeout(()=>{
								searchFnc();
							},200)					
					}				
				});
			})			       
		}
        ,error: function(error) {
	
        	 $("#result").html ( '업로드 실패' );
        }
    });
	
}	