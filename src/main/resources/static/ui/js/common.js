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