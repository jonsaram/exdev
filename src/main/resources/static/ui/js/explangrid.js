var CLASS_GRID = function(parm) {
	
	if(isEmpty(parm)) parm = {};
	
    this.isDragging 		= false;
    this.startCell 			= null;
    this.selectedCells 		= [];
    this.pasteStartCell 	= null;
    this.gridId 			= '';
    this.undoStack			= [];
    this.redoStack 			= [];
    this.initialContent 	= "";
    this.parm 				= parm;
    this.readOnly			= parm.readOnly;

    this.undo = function() {
        if (this.undoStack.length === 0) return;
        const lastState = this.undoStack.pop();
        let undoBack = [];
        lastState.forEach(cellState => {
            const cell = $(`#${this.gridId} .explan-grid-cell[data-row=${cellState.row}][data-col=${cellState.col}]`);
            undoBack.push({
                row: cellState.row,
                col: cellState.col,
                text: cell.text(),
            });

            cell.text(cellState.text);
        });
        this.redoStack.push(undoBack);
    };

    this.redo = function() {
        if (this.redoStack.length === 0) return;
        const nextState = this.redoStack.pop();
        let redoBack = [];
        nextState.forEach(cellState => {
            const cell = $(`#${this.gridId} .explan-grid-cell[data-row=${cellState.row}][data-col=${cellState.col}]`);
            redoBack.push({
                row: cellState.row,
                col: cellState.col,
                text: cell.text(),
            });
            cell.text(cellState.text);
        });
        this.undoStack.push(redoBack);
    };

    this.setGridIndex = function(gridId, type) {
        this.gridId = gridId;
        let rowCounter = 1;
        const rowSpans = [];

        $(`#${gridId} tr`).each(function() {
            let colCounter = 1;

            while (rowSpans[colCounter] && rowSpans[colCounter] > 0) {
                rowSpans[colCounter]--;
                colCounter++;
            }
            
            // state값이 초기 'DEFAULT' 설정 (init에서 호출된 경우만)
            if(type == 'init') {
            	let uid = getUniqueId();
            	$(this).attr("__state", "default"	);
            	$(this).attr("uid"	, uid		);
            }
			
			$(this).attr("data-row", rowCounter);
			
            $(this).children('.explan-grid-cell').each(function() {
                while (rowSpans[colCounter] && rowSpans[colCounter] > 0) {
                    colCounter++;
                }

                $(this).attr('data-row', rowCounter);
                $(this).attr('data-col', colCounter);

                const rowspan = parseInt($(this).attr('rowspan')) || 1;
                const colspan = parseInt($(this).attr('colspan')) || 1;

                for (let i = 0; i < colspan; i++) {
                    rowSpans[colCounter + i] = rowspan - 1;
                }

                colCounter += colspan;
            });

            rowCounter++;
        });

        $(`#${this.gridId}`).off('click').on('click', '.explan-grid-cell', this.onClick.bind(this));
    };

    this.clearSelection = function() {
        $(`#${this.gridId} .explan-grid-cell`).removeClass("explan-selected");
        this.pasteStartCell = null;
        this.selectedCells = [];
    };

    this.selectCellsWithinRectangle = function(start, end) {
        const startRow = Math.min(parseInt(start.attr("data-row")), parseInt(end.attr("data-row")));
        const endRow = Math.max(parseInt(start.attr("data-row")), parseInt(end.attr("data-row")));
        const startCol = Math.min(parseInt(start.attr("data-col")), parseInt(end.attr("data-col")));
        const endCol = Math.max(parseInt(start.attr("data-col")), parseInt(end.attr("data-col")));

        $(`#${this.gridId} .explan-grid-cell`).each(function() {
            const $cell = $(this);
            const row = parseInt($cell.attr("data-row"));
            const col = parseInt($cell.attr("data-col"));
            const isInRectangle = row >= startRow && row <= endRow && col >= startCol && col <= endCol;
            $cell.toggleClass("explan-selected", isInRectangle);
        });
    };

    this.onMouseDown = function(event) {
        if (!$(event.target).closest(`#${this.gridId}`).length) return;
        C_GRID.clearSelection(this.gridId);
        this.isDragging = true;
        this.startCell = $(event.target);
        this.selectedCells = [];
        this.selectCellsWithinRectangle(this.startCell, this.startCell);
    };

    this.onMouseMove = function(event) {
        if (!$(event.target).closest(`#${this.gridId}`).length) return;
        if (this.isDragging) {
            const endCell = $(event.target);
            this.clearSelection();
            this.selectCellsWithinRectangle(this.startCell, endCell);
        }
    };

    this.onMouseUp = function(event) {
        if (!$(event.target).closest(`#${this.gridId}`).length) return;
        if (this.isDragging) {
            this.isDragging = false;
            this.selectedCells = $(`#${this.gridId} .explan-grid-cell.explan-selected`).toArray();
            C_GRID.setCurrentSelectedGridId(this.gridId);
            
        }
    };

    this.onClick = function(event) {
        if (!$(event.target).closest(`#${this.gridId}`).length && !$(event.target).closest('.explan-layer-popup').length) return;
        if (!this.isDragging) {
            this.clearSelection();
            $(event.target).addClass("explan-selected");
            this.selectedCells = [event.target];
            this.pasteStartCell = $(event.target);
        }
    };

    this.onDocumentMouseDown = function(event) {
        if ($(event.target).closest('.explan-grid-cell, .explan-layer-popup').length === 0) {
            this.clearSelection();
        }
    };

    this.onKeyDown = function(event) {

        let eventKey = event.key.toLowerCase();
    	
    	if(this.gridId != C_GRID.currentSelectedGridId) return;
        if (event.ctrlKey && eventKey === 'c') {
            const selectedText = this.getSelectedTextForExcel();
            if (selectedText != null) this.copyToClipboard(selectedText);
        }
        if (eventKey === 'delete' && this.selectedCells.length > 0) {
            this.backupCellsState(this.selectedCells);
            this.selectedCells.forEach(cell => {
                $(cell).text('');
            });
        }
        
        if (event.ctrlKey && eventKey === 'z') this.undo();
        if (event.ctrlKey && eventKey === 'y') this.redo();
        if (event.ctrlKey && eventKey === 'i') this.insertRow();
        if (event.ctrlKey && eventKey === 'a') {
        	event.preventDefault();
        	this.addRow();
        }
        if (event.ctrlKey && eventKey === 'd') {
        	event.preventDefault();
            this.deleteRow();
        }
    };

    this.onPaste = function(event) {
    	
    	// 읽기 전용인경우 붙여넣기 방지
    	if(this.readOnly == "Y") return;
    	
    	if(this.gridId != C_GRID.currentSelectedGridId) return;
        const clipboardData = event.originalEvent.clipboardData.getData('text');
        this.pasteFromClipboard(clipboardData);
    };

    this.getSelectedTextForExcel = function() {
        if (this.selectedCells.length < 1) return null;

        if (this.selectedCells.some(cell => $(cell).find('input').length > 0)) return null;

        this.selectedCells.sort((a, b) => {
            const rowDiff = parseInt($(a).attr("data-row")) - parseInt($(b).attr("data-row"));
            if (rowDiff !== 0) return rowDiff;
            return parseInt($(a).attr("data-col")) - parseInt($(b).attr("data-col"));
        });

        let rows = {};
        this.selectedCells.forEach(cell => {
            const row = $(cell).attr("data-row");
            const col = $(cell).attr("data-col");
            if (!rows[row]) rows[row] = [];
            debugger;
            let data = cell.innerText;

            if (data.includes('\t') || data.includes('\n')) data = `"${data}"`;
            
            rows[row][col] = data;
        });

        const rowKeys = Object.keys(rows).sort((a, b) => a - b);
        return rowKeys.map(rowKey => {
            const cols = rows[rowKey];
            const colKeys = Object.keys(cols).sort((a, b) => a - b);
            return colKeys.map(colKey => cols[colKey]).join('\t');
        }).join('\n');
    };

    this.copyToClipboard = function(text) {
    	dlog(text);
        const $temp = $("<textarea>");
        $("body").append($temp);
        $temp.val(text).select();
        document.execCommand("copy");
        $temp.remove();
    };

	this.parseExcelType = function(input) {
	    // 정규 표현식: 따옴표로 묶인 문자열과 묶이지 않은 문자열을 분리
	    const regex = /"([^"]*)"/g;
	    let modifiedInput = input;
	
	    // 따옴표로 묶인 부분을 찾고 \n을 [nl]로 바꾸기
	    modifiedInput = modifiedInput.replace(regex, (match, p1) => {
	        // p1: 따옴표 안의 내용
	        const modifiedContent = p1.replace(/\n/g, '[nl]');
	        return modifiedContent;
	    });
	
	    return modifiedInput;
	    
	}
	this.pasteFromClipboard = function(clipboardData) {

        if (!this.pasteStartCell) return;
		
        this.backupCellsState(this.getCellsToChange(clipboardData));

		clipboardData = this.parseExcelType(clipboardData);
		
        const lines = clipboardData.split("\n");

        const startRow = parseInt($(this.pasteStartCell).attr("data-row"));
        const startCol = parseInt($(this.pasteStartCell).attr("data-col"));

        lines.forEach((line, rowIndex) => {
            // row __state 설정
            const targetRow = startRow + rowIndex;
            
            let nowState = $(`#${this.gridId} tr[data-row=${targetRow}]`).attr("__state");
            
            if(nowState == "default") {
            	$(`#${this.gridId} tr[data-row=${targetRow}]`).attr("__state", "update");
            }
            
            const cells = line.split("\t");
            cells.forEach((cellText, colIndex) => {
                const targetCol = startCol + colIndex;
                const targetCell = $(`#${this.gridId} .explan-grid-cell[data-row=${targetRow}][data-col=${targetCol}]`);
                cellText = cellText.replaceAll("[nl]", "</br>");
                if (targetCell.length) {
                    targetCell.html(cellText);
                } else {
		            const tableBody = $(`#${this.gridId}`);
		            const lastRow = tableBody.find('tr').last();
		            const firstCellInLastRow = lastRow.find('.explan-grid-cell[data-col=1]');
		
		            // 선택 표시 지우기
		            this.clearSelection();
					
		            // 마지막 줄의 첫 번째 셀을 선택된 상태로 만듭니다
		            firstCellInLastRow.addClass('selected');
		            this.selectedCells.push(firstCellInLastRow[0]);
		            
		            this.addRow();
		            
	                const targetCell = $(`#${this.gridId} .explan-grid-cell[data-row=${targetRow}][data-col=${targetCol}]`);
                    targetCell.html(cellText);
                }
            });
        });
    };

    this.backupCellsState = function(cells) {
        if (cells.length === 0) return;

        const lastState = this.undoStack[this.undoStack.length - 1];
        const newState = cells.map(cell => ({
        	type	: "cell",
            row		: $(cell).attr("data-row"),
            col		: $(cell).attr("data-col"),
            text	: $(cell).text(),
        }));

        if (!lastState || JSON.stringify(lastState) !== JSON.stringify(newState)) {
            this.undoStack.push(newState);
            this.redoStack = []; // 삭제 시 redo 스택 초기화
        }
    };

    this.getCellsToChange = function(clipboardData) {
        const lines = clipboardData.split("\n");
        const startRow = parseInt(this.pasteStartCell.attr("data-row"));
        const startCol = parseInt(this.pasteStartCell.attr("data-col"));
        let cells = [];

        lines.forEach((line, rowIndex) => {
            const lineCells = line.split("\t");
            lineCells.forEach((_, colIndex) => {
                const targetRow = startRow + rowIndex;
                const targetCol = startCol + colIndex;
                const targetCell = $(`#${this.gridId} .explan-grid-cell[data-row=${targetRow}][data-col=${targetCol}]`);
                if (targetCell.length) {
                    cells.push(targetCell[0]);
                }
            });
        });

        return cells;
    };

    this.onDblClick = function(event) {

    	// 읽기 전용인경우 붙여넣기 방지
    	if(this.readOnly == "Y") return;

        if (!$(event.target).closest(`#${this.gridId}`).length) return;
        this.clearSelection();

        const cellWidth = $(event.target).width();
        const cellHeight = $(event.target).height();

        this.backupCellsState([event.target]);

        const $input = $(`<input type='text' style='width: 100%; height: ${cellHeight}px; border: none; outline: none;' value='${$(event.target).text()}'>`);
        $(event.target).html($input);
        $input.focus();
        var inputLength = $input.val().length;
        $input[0].setSelectionRange(inputLength, inputLength);

        const $cell = $(event.target);

        $input.on('paste', function(event) {
            const clipboardData = event.originalEvent.clipboardData.getData('text');
            const textBefore = $input.val();
            const cursorPosition = $input[0].selectionStart;
            const textAfter = textBefore.slice(0, cursorPosition) + clipboardData + textBefore.slice(cursorPosition);
            $input.val(textAfter);

            return false;
        });

        $input.on('copy', function(event) {
            const text = window.getSelection().toString();
            event.originalEvent.clipboardData.setData('text', text);
            return false;
        });

        $input.on('keydown', function(event) {
	        let eventKey = event.key.toLowerCase();

            if ((event.ctrlKey || event.metaKey) && eventKey === 'c') {
                event.preventDefault();
                document.execCommand('copy');
            } else if ((event.ctrlKey || event.metaKey) && eventKey === 'v') {
                return;
            } else if (eventKey === 'enter') {
                const newText = $(this).val();
                $cell.text(newText);
                
                // 상위 tr update 설정
		        let parentTr = $cell.closest('tr'); // 상위 tr 요소 찾기
		        let __state = parentTr.attr("__state"); // __state 속성 값 가져오기
		        if(__state === "default") parentTr.attr("__state", "update");
            }
        });

        $input.blur(function() {
            const newText = $(this).val();
            $cell.text(newText);

            // 상위 tr update 설정
	        let parentTr = $cell.closest('tr'); // 상위 tr 요소 찾기
	        let __state = parentTr.attr("__state"); // __state 속성 값 가져오기
	        if(__state === "default") parentTr.attr("__state", "update");
        });
    };



    this.init = function(gridId) {
        $(`#${gridId} th, #${gridId} td`).addClass("explan-grid-cell");

        this.setGridIndex(gridId, 'init');

        this.initialContent = $(`#${gridId}`).html();

        $(`#${gridId}`).on('mousedown'	, '.explan-grid-cell', this.onMouseDown.bind(this));
        $(`#${gridId}`).on('mousemove'	, '.explan-grid-cell', this.onMouseMove.bind(this));
        $(`#${gridId}`).on('mouseup'	, this.onMouseUp.bind(this));
        $(`#${gridId}`).on('click'		, '.explan-grid-cell', this.onClick.bind(this));
        $(`#${gridId}`).unbind('dblclick');
        $(`#${gridId}`).bind('dblclick'	, '.explan-grid-cell', this.onDblClick.bind(this));
    };
    this.destroy = function(gridId) {
        $(`#${gridId}`).off('mousedown');
        $(`#${gridId}`).off('mousemove');
        $(`#${gridId}`).off('mouseup');
        $(`#${gridId}`).off('click');
        $(`#${gridId}`).unbind('dblclick');
    };
    // 행추가
    this.addRow = function() {
    	return this.insertRow("A");
    }
    this.insertRow = function(type) {
    	if(isEmpty(type)) type = "I";
        const selectedCell = this.selectedCells[0];
        if (selectedCell) {
            const row 		= $(selectedCell).closest('tr');
            const newRow 	= row.clone(); // 현재 선택된 행 복사
            
            $(newRow).attr("__state"	, "insert"); // 신규 Row state값 설정
        	let uid = getUniqueId();
            $(newRow).attr("uid", uid); // 신규 Row state값 설정
            
            newRow.find('.explan-grid-cell').removeClass('explan-selected').text(''); // 새로운 행의 모든 셀 내용 지우기
            if(type == "I" ) row.before(newRow); // 새로운 행 삽입
            if(type == "A" ) row.after(newRow); // 새로운 행 추가

            this.setGridIndex(this.gridId); // 그리드 인덱스 업데이트
           
            this.undoStack = [];
            this.redoStack = [];
        } else {
            alert("선택된 셀이 없습니다.");
        }
    };
    
    
    
    this.deleteRow = function() {
	    const selectedCell = this.selectedCells[0];
	    if (selectedCell) {
	        const row = $(selectedCell).closest('tr');
	        row.remove();
	        this.setGridIndex(this.gridId); // Update the grid indices
	        
	        this.undoStack = []
	        this.redoStack = []
	
	    } else {
	        alert("선택된 셀이 없습니다.");
	    }
    };
    this.reloadGrid = function() {
    	if(confirm('초기화를 진행하면 이전 상태로 되돌릴 수 없습니다.\n\n초기화를 진행 하시겠습니까?')) {
	        const gridId = this.gridId;
	        const initialGridContent = this.initialContent;
	        if (initialGridContent) {
	            $(`#${gridId}`).html(initialGridContent);
	            this.init(gridId); // 그리드 다시 초기화
	            C_GRID.closeLayerPopup();
	        } else {
	            alert("초기 내용이 없습니다.");
	        }
    	}
    }
    
    
};

var C_GRID = {
	 gridMap : {}
	,currentSelectedGridId : ""
	,setCurrentSelectedGridId(gridId) {
		this.currentSelectedGridId = gridId;
	 }
	,getSelectedGrid() {
		if(this.currentSelectedGridId == "") return;
		return C_GRID.gridMap[this.currentSelectedGridId].gridInstance;
	 }
	,makeGrid(gridId, parm) {
		
		if(isValid(C_GRID.gridMap[gridId])) {
			if(isValid(C_GRID.gridMap[gridId].gridInstance)) {
				C_GRID.gridMap[gridId].gridInstance.destroy();
				C_GRID.gridMap[gridId].gridInstance = null;
			}
		}
		var gridInstance = new CLASS_GRID(parm);
		gridInstance.init(gridId);
		
		C_GRID.gridMap[gridId] = {
			 gridInstance: gridInstance 
		}
	 }
	,clearSelection(gridId) {
		$.each(C_GRID.gridMap, function(key, obj) {
			if(gridId == key) return true;
			obj.gridInstance.clearSelection();
		});
	 }
    ,getGridData(gridId) {
    	let baseDataList 	= C_GRID.getBaseGridData(gridId);

    	let curDataList		= C_GRID.getGridMainData(gridId);

        let gridUids 		= curDataList.map(row => row.uid);

        // Filter grid1 to find rows with uids not in grid2
        let filteredData = baseDataList.filter(row => !gridUids.includes(row.uid));

        $.each(filteredData, function(idx) {
        	filteredData[idx].__state = "delete";
        	curDataList.push(filteredData[idx]);
        });
        $.each(curDataList, function(idx) {
        	delete curDataList[idx].uid;
        	delete curDataList[idx]["data-row"];
        });
        
        return curDataList;
     }
    ,getBaseGridData(gridId) {
    	if(isEmpty(C_GRID.gridMap[gridId])) {
    		C_POP.alert('선택된 Grid가 없습니다.');
    		return;
    	}
    	let initialContent = C_GRID.gridMap[gridId].gridInstance.initialContent;
    	let gridElement = $('<div id="wrapper1"><table>' + initialContent + '</table></div>');
    	
        let headers = [];
        let rows = [];
        let invalidCheck = false;
        
        // Get column headers
        $(gridElement).find('th').each(function() {
            let column = $(this).attr('column');
            if (isEmpty(column)) invalidCheck = true;
            headers.push(column);
        });

        if (invalidCheck) {
            alert(`Table의 Column 설정이 필요합니다.`);
            return null;
        }

        // Get row data
        $(gridElement).find('tr[uid]').each(function() {
            let rowData = {};
            $(this).find('td').each(function(index) {
                rowData[headers[index]] = $(this).text().trim();
            });

            if (Object.keys(rowData).length === 0) {
                return true;
            }

            let __state = $(this).attr("__state");
            rowData["__state"] = __state;
            rowData["uid"] = $(this).attr("uid");

            rows.push(rowData);
        });
        return rows;
    	
     }
    ,getGridMainData(gridId) {
        let headers = [];
        let rows = [];
		let invalidCheck = false;        
        $(`#${gridId} th`).each(function() {
        	let column = $(this).attr('column');
        	if(isEmpty(column)) invalidCheck = true;
            headers.push(column);
        });
        if(invalidCheck) {
        	alert(`${gridId} Table의 Column 설정이 필요합니다.`);
        	return null;
        }
        $(`#${gridId} tr`).each(function() {
            let rowData = {};
            $(this).find('td').each(function(index) {
                rowData[headers[index]] = $(this).text().trim();
            });
			if (Object.keys(rowData).length === 0) {
			    return true;
			}

            let attrList = getAllAttrFromDom(this, 'big');
            
            let allRowData = Object.assign({}, rowData, attrList);

            rows.push(allRowData);
        });
        return rows
     }
	,init() {
		
        $(document).on('contextmenu', '.explan-grid-cell', function(event) {
            event.preventDefault(); // 기본 우클릭 메뉴 방지
            const x = event.pageX; // 마우스 클릭한 x 좌표
            const y = event.pageY; // 마우스 클릭한 y 좌표
			
			let selectedGridInstance = C_GRID.getSelectedGrid();
			
			
            if (!$(event.target).hasClass('explan-selected')) {
                selectedGridInstance.clearSelection();
                $(event.target).addClass('explan-selected');
                selectedGridInstance.selectedCells = [event.target];
            } else {
                event.target.classList.add('explan-selected');
            }

            C_GRID.closeLayerPopup();

            const popup = $(`<div id="explanMenuPopup" class='explan-layer-popup' type='explan-layer-popup' style='left: ${x}px; top: ${y}px; width: 200px; background-color: white; border: 1px solid black; padding: 5px;'>
                                <div class='explan-menu-item' title="Ctrl + i">행삽입</div>
                                <div class='explan-menu-item' title="Ctrl + a">행추가</div>
                                <div class='explan-menu-item' title="Ctrl + d">행삭제</div>
                                <div class='explan-menu-item'>초기화</div>
                                <div class='explan-menu-item' title="Ctrl + z">실행취소(Undo)</div>
                                <div class='explan-menu-item' title="Ctrl + y">재실행(Redo)</div>
                            </div>`);

            $('body').append(popup);

            $('#explanMenuPopup .explan-menu-item').on('mouseenter', function() {
                $(this).css('background-color', 'lightblue');
            });

            $('#explanMenuPopup .explan-menu-item').on('mouseleave', function() {
                $(this).css('background-color', 'initial');
            });

            // 메뉴 클릭 이벤트 수정
            $('#explanMenuPopup .explan-menu-item').on('click', function() {
                const menuText = $(this).text();
	            C_GRID.closeLayerPopup();
				if 		(menuText === '행삽입'			) selectedGridInstance.insertRow();
				else if (menuText === '행추가'			) selectedGridInstance.addRow();
				else if (menuText === '행삭제'			) selectedGridInstance.deleteRow();
				else if (menuText === '초기화'			) selectedGridInstance.reloadGrid();
				else if (menuText === '실행취소(Undo)'	) selectedGridInstance.undo();
				else if (menuText === '재실행(Redo)'	) selectedGridInstance.redo();
				else {
				    alert(`선택한 메뉴: ${menuText}`);
				}
	            selectedGridInstance.clearSelection(); // 선택 해제
            });

            popup.on('click', function(event) {
                event.stopPropagation();
            });

            $(document).one('click', function() {
                popup.remove();
            });

            return false;
        });
        $(document).unbind('mousedown')
        $(document).bind('mousedown'	, function(event) {
			$.each(C_GRID.gridMap, function(key, obj) {
				obj.gridInstance.onDocumentMouseDown(event);
			});
        });
        $(document).unbind('keydown')
        $(document).bind('keydown'	, function(event) {
			$.each(C_GRID.gridMap, function(key, obj) {
				obj.gridInstance.onKeyDown(event);
			});
        });
        $(document).unbind('paste')
        $(document).bind('paste'	, function(event) {
			$.each(C_GRID.gridMap, function(key, obj) {
				obj.gridInstance.onPaste(event);
			});
        });
	 }
    ,closeLayerPopup() {
        $('div[type="explan-layer-popup"]').remove();
     }
	,getAllAttrFromDom(domObj, type) {
	    var attributes = {};
	    $(domObj).each(function() {
	        $.each(this.attributes, function() {
	            if(this.specified) {
	            	let name = this.name;
	            	if(type==='big' && !in_array(name, ['__state','uid','data-row']) )  name=name.toUpperCase();
	                attributes[name] = this.value;
	            }
	        });
	    });
	    return attributes;
	 }
    ,setReadOnly(gridId, state) {  // state="Y" is Readonly , "N" or undefined is  editable 
    	if(isVaild(C_GRID.gridMap[gridId]) && isVaild(C_GRID.gridMap[gridId].gridInstance)) {
	    	C_GRID.gridMap[gridId].gridInstance.readOnly = state;
    	}
     }
    ,getReadOnly(gridId) {
    	if(isVaild(C_GRID.gridMap[gridId]) && isVaild(C_GRID.gridMap[gridId].gridInstance)) {
	    	return C_GRID.gridMap[gridId].gridInstance.readOnly;
    	} else {
    		return null;
    	}
     }
}


$(function() {
	C_GRID.init()
});
