let MENUS = [];
function setMenu()
{
	var url = "/requestQuery.do";
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function() {
		
	    if (xhr.readyState == 4 && xhr.status == 200) {
	
	        var responseData = JSON.parse(xhr.responseText).data;

			G_VAL.SP_CSTM_ID =responseData[0].SP_CSTM_ID;
			
	        let gnbMenuTemplate = [];
 			MENUS = [];
	        responseData.forEach( menu => {
	        	
	        	const pageId 		= menu.PAGE_ID;
	        	const depth			= menu.MENU_DEPTH;
	        	if( Number(depth) == 0){
					let template = `<li id="HDR_${menu.MENU_ID}"><a href="javascript:lefMenuShow('${menu.MENU_ID}','${menu.MENU_NM}',${menu.PARAMETER});">${menu.MENU_NM}</a></li>`  ;
					gnbMenuTemplate.push(template);
					
	        	}else{
								        		
					let template = `<li id="LNB_${menu.MENU_ID}"><a href="javascript:getMenuPage('${menu.MENU_ID}','${pageId}',${menu.PARAMETER}		);">${menu.MENU_NM}</a></li>`	;
					MENUS.push( {	PARENT_MENU_ID 	:menu.PARENT_MENU_ID 
									, 	PARENT_MENU_NM	:menu.PARENT_MENU_NM 
									, 	MENU_ID			:menu.MENU_ID
									, 	MENU_NM 		:menu.MENU_NM
									,	PAGE_ID			:menu.PAGE_ID
									, 	HTML_TAG		: template})
									
	        	}
				
	        })
			
			$("#gnb_MenuList").html(gnbMenuTemplate.join('')) ;
			$("#gnb_MenuList li[id^='HDR_']").eq(0).addClass("active");
			
	        const lnb = makeLnbMenu( MENUS[0].PARENT_MENU_ID, MENUS[0].PARENT_MENU_NM);
			$("#lnb_MenuList").html(lnb.lnbHtml) ;
			$("#lnb_MenuList li[id^='LNB_']").eq(0).addClass("active");
			//goPage(MENUS[0].PAGE_ID);
			
	    }
	};
	
	var requestData = JSON.stringify({ "queryId": "system.getMenuList" ,"requestParm":{DEL_YN:'N', USE_YN:'Y'}});
	xhr.send(requestData);
	
}
		
function lefMenuShow(parentMenuId, parentMenuDesc, parameter){

	$("[id^='HDR_']").removeClass("active");
	$("#HDR_"+parentMenuId).addClass("active");
	
	const lnb = makeLnbMenu( parentMenuId, parentMenuDesc);

	$("#lnb_MenuList").html( lnb.lnbHtml ) ;
	
	$("#lnb_MenuList li[id^='LNB_']").eq(0).addClass("active");
	
	goPage(lnb.firstLeftMenu["PAGE_ID"], parameter);
}

function getMenuPage(menuId, pageId, parameter){

	$("#lnb_MenuList li").removeClass("active");
	$("#lnb_MenuList li[id='LNB_"+menuId+"']").addClass("active");	
	goPage(pageId , parameter);
	
}

function makeLnbMenu( parentMenuId, parentMenuDesc){
	
	let lnbHtml = `<div class="lnb_tit01">${parentMenuDesc}
					<button class="btn fold" onclick="showLeftMenuToggle()">
						<i class="icon_arrow"></i>
					</button>
					</div>
					<ul><li><ul>`;
	
	let firstLeftMenu = '';
	
	const depth1_menus = MENUS.filter( menu => menu.PARENT_MENU_ID == parentMenuId );
	depth1_menus.forEach( childMenu => {
		
		if( firstLeftMenu == '' ){firstLeftMenu = childMenu;}  

		lnbHtml += childMenu.HTML_TAG;

	})
	
	lnbHtml +=`</ul></li></ul>` ;
	
	return {lnbHtml: lnbHtml , firstLeftMenu : firstLeftMenu};
}