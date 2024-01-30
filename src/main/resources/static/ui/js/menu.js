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
	        let dept1_menuTemplate = [];
	        let active 			=  "active";
	        let lnb_active		=  "active";
			let	index			= 0;
			let	parentMenu		={};	
	        responseData.forEach( menu => {
	        	
	        	const pageId 		= menu.PAGE_ID;
	        	const depth			= menu.MENU_DEPTH;
	        	let   firstPageId	= "";
	        	if( Number(depth) == 0){
	        		
					let template = `<li class=${active} id="LI_${menu.MENU_ID}"><a href="javascript:detpth2MenuShow('${menu.MENU_ID}','${menu.MENU_DESC}');">${menu.MENU_DESC}</a></li>`  ;
					dept1_menuTemplate.push(template);
					active="deactive";
					if(index == 0){parentMenu=menu;index++;}
					lnb_active	=  "active";
					
	        	}else{
								        		
					let template = `<li class=${lnb_active} ><a href="javascript:goPage('${pageId}');">${menu.MENU_DESC}</a></li>`
					if( lnb_active =="active") firstPageId = menu.PAGE_ID;					
					lnb_active="deactive";
							
					MENUS.push( {	PARENT_MENU_ID 	:menu.PARENT_MENU_ID 
									, 	PARENT_MENU_NNM	:menu.PARENT_MENU_DESC 
									, 	MENU_ID			:menu.MENU_ID
									, 	MENU_DESC 		:menu.MENU_DESC
									,	PAGE_ID			:menu.PAGE_ID
									, 	HTML_TAG		: template})
									
	        	}
				
	        })
	        
			$("#depth1_MenuList").html(dept1_menuTemplate.join('')) ;

	        const lnbHtml = make2ndLnbMenu( parentMenu.MENU_ID, parentMenu.MENU_DESC);
			$("#lnb_MenuList").html(lnbHtml) ;
			
	    }
	};
	
	var requestData = JSON.stringify({ "queryId": "system.getMenuList" ,"requestParm":{}});
	xhr.send(requestData);
	
}
		
function detpth2MenuShow(parentMenuId, parentMenuDesc){

	const retn =make2ndLnbMenu( parentMenuId, parentMenuDesc);
	const lnbHtml = retn.lnbHtml;
	const firstLeftMenu = retn.FIRST_LEFT_MENU;

	$("#lnb_MenuList").html(lnbHtml) ;
	
	$("[id^='LI_']").removeClass("active");
	$("#LI_"+parentMenuId).addClass("active");
	
	$("nav ul li ul li").click(function(){ 
		$("nav ul li ul li").removeClass("active");	
		$(this).addClass("active");
	})
	
	goPage(firstLeftMenu)

}

function make2ndLnbMenu( parentMenuId, parentMenuDesc){
	
	let lnbHtml = `<div class="lnb_tit01">${parentMenuDesc}
					<button class="btn fold" onclick="showLeftMenuToggle()">
						<i class="icon_arrow"></i>
					</button>
					</div>
					<ul><li><ul>`;
	
	const depth2_menus = MENUS.filter( menu => menu.PARENT_MENU_ID == parentMenuId );
	let firstLeftMenu = '';
	let index = 0; 
	depth2_menus.forEach( childMenu => {
		
		if( index ==0 ){firstLeftMenu = childMenu.PAGE_ID; index++; }
		lnbHtml += childMenu.HTML_TAG;
	})
	
	lnbHtml +=`</ul></li></ul>` ;
	
	return {lnbHtml: lnbHtml , FIRST_LEFT_MENU : firstLeftMenu};
}