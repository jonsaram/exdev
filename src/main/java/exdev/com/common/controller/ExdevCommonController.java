/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package exdev.com.common.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import exdev.com.common.ExdevConstants;
import exdev.com.common.service.ExdevCommonService;
import exdev.com.common.vo.SessionVO;

/**
 * This MovieController class is a Controller class to provide movie crud and
 * genre list functionality.
 * 
 * @author 위성열
 */
@Controller("ExdevCommonController")
public class ExdevCommonController {
	
	@Autowired
	private ExdevCommonService commonService;

	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping("requestService.do")
	public @ResponseBody Map requestService(@RequestBody Map map, HttpSession session) throws Exception {
		
		SessionVO sessionVo = (SessionVO)session.getAttribute(ExdevConstants.SESSION_ID);
		
		map.put("sessionVo", sessionVo);
		
		Map resultMap = commonService.requestService(map, session);
		
		return resultMap;

	}

	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping("requestQuery.do")
	public @ResponseBody Map requestQuery(@RequestBody Map map, HttpSession session) throws Exception {

		SessionVO sessionVo = (SessionVO)session.getAttribute(ExdevConstants.SESSION_ID);
		
		map.put("sessionVo", sessionVo);
		
		Map resultMap = commonService.requestQuery(map, session);
		
		return resultMap;
	}
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping("requestQueryGroup.do")
	public @ResponseBody Map requestQueryGroup(@RequestBody Map map, HttpSession session) throws Exception {

		SessionVO sessionVo = (SessionVO)session.getAttribute(ExdevConstants.SESSION_ID);
		
		map.put("sessionVo", sessionVo);
		
		Map resultMap = commonService.requestQueryGroup(map, session);
		
		return resultMap;
	}
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping("login.do")
	public @ResponseBody Map login(@RequestBody Map map, HttpSession session) throws Exception {
//
//		SessionVO sessionVo = new SessionVO();
//		
//		Map resultMap = commonService.getMember(map);
//		
//		String userId = null;
//		if(resultMap != null) userId = (String)resultMap.get("USER_ID");
//		
//		if(userId != null && !"".equals(userId)) {
//			sessionVo.setUserId(userId);
//			
//			session.setAttribute(ExdevConstants.SESSION_ID, sessionVo);
//			
//			resultMap.put("loginCheck", "S");
//		} else {
//			resultMap.put("loginCheck", "F");
//		}
//		
//		return resultMap;
		return null;
	}
	

	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping("/excelService.do")
	public @ResponseBody Map ExcelService(@RequestParam("file") MultipartFile file,HttpSession session) throws Exception {
		
		SessionVO sessionVo = (SessionVO)session.getAttribute(ExdevConstants.SESSION_ID);
		Map map = new HashMap();
		map.put("sessionVo", sessionVo);
		map.put("filename", file.getOriginalFilename());

		Map resultMap = new HashMap();
		resultMap.put("msg",null);
				
	  try {

            Workbook workbook = WorkbookFactory.create(file.getInputStream());

            Sheet sheet = workbook.getSheetAt(0);
            
            List<Map<String, Object>> excelDataMapList = new ArrayList<>();
            List<String> headerList = new ArrayList<>();
            boolean headerRow = true;
            
            for (Row row : sheet) {
            	Map<String,Object> cellMap = new HashMap();
            	if( headerRow) {
           
                    for (Cell cell : row) {
                        System.out.print(cell.toString() + "\t");
                        headerList.add(cell.toString()); 
                    }
            		headerRow=false;
            	}else {

            		for (Cell cell : row) {
            			System.out.print(cell.toString() + "\t");
            			cellMap.put(headerList.get(cell.getColumnIndex()), cell.toString());
            		}
            		excelDataMapList.add( cellMap);
            	}
            	System.out.println();
            }

            workbook.close();

            Gson gson = new Gson();
            String json = gson.toJson(excelDataMapList);
            resultMap.put("msg","S");
    		resultMap.put("excelJson",json);
    		
            return resultMap;
            
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("msg","E");
    		resultMap.put("err","ERROR");

            return resultMap;
        }
	}
}
