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
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import exdev.com.common.ExdevConstants;
import exdev.com.common.service.ExdevCommonService;
import exdev.com.common.vo.SessionVO;
import exdev.com.service.ExdevSampleService;
import exdev.com.service.FileService;

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
	
	@Autowired
	private ExdevSampleService sampleService;

	@Autowired
	private FileService fileService;
	
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
            resultMap.put("msg","");
    		resultMap.put("data",json);
            resultMap.put("state","S");
    		
            return resultMap;
            
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("msg","");
            resultMap.put("state","");

            return resultMap;
        }
	}

	/**
	 * 내용 : 다중 첨부파일 업로드 샘플
	 * 작성자 : 이응규
	 * 2024.01.16_01 : 최초작성
	 */
	@PostMapping("/multiFileUpload.do")
	public @ResponseBody Map  multiFileUpload(@RequestParam("attach_file") List<MultipartFile> multiFileList,			
            HttpServletRequest request, HttpSession session)  throws Exception {
		
        SessionVO sessionVo = (SessionVO)session.getAttribute(ExdevConstants.SESSION_ID);
        
        Map returnMap = new HashMap();
        
		// 받아온것 출력 확인
		System.out.println("multiFileList : " + multiFileList);
		 String grpId = request.getParameter("groupId");
		
		// path 가져오기
		String path = request.getSession().getServletContext().getRealPath("resources");
		String root = path + "\\" + "uploadFiles";
		returnMap = fileService.fileUploadMulti( multiFileList, root , grpId);
		
		String msg = returnMap.get("msg").toString();
		if( "SUCCESS".equals(msg)) {
			returnMap.put("msg","파일 업로드에 성공하였습니다.");
		}else{
			returnMap.put("msg","파일 업로드에 실패하였습니다.");
		}
		
		return returnMap;
	}
	
	/**
	 * 내용 : 다중 첨부파일 업로드 샘플
	 * 작성자 : 이응규
	 * 2024.01.16_01 : 최초작성
	 */
	@PostMapping("/multiFileUpload1.do")
	public @ResponseBody Map  multiFileUpload1(@RequestParam("attach_file") List<MultipartFile> multiFileList,			
            HttpServletRequest request, HttpSession session)  {
		
        SessionVO sessionVo = (SessionVO)session.getAttribute(ExdevConstants.SESSION_ID);
        
        Map returnMap = new HashMap();
        
		// 받아온것 출력 확인
		System.out.println("multiFileList : " + multiFileList);
		 String grpId = request.getParameter("groupId");
		
		// path 가져오기
		String path = request.getSession().getServletContext().getRealPath("resources");
		String root = path + "\\" + "uploadFiles";
		
		File fileCheck = new File(root);
		
		if(!fileCheck.exists()) fileCheck.mkdirs();

		List<Map<String, String>> fileList = new ArrayList<>();
		
		for(int i = 0; i < multiFileList.size(); i++) {
			String originFile = multiFileList.get(i).getOriginalFilename();
			long fileSize = multiFileList.get(i).getSize();
			System.out.println("fileSize 1 ==>"+fileSize);
			String ext = originFile.substring(originFile.lastIndexOf("."));
			String changeFile = UUID.randomUUID().toString() + ext;
			
			System.out.println("originFile : " + originFile);
			System.out.println("changeFile : " + changeFile);
			System.out.println("ext : " + ext);
			Map<String, String> map = new HashMap<>();
			map.put("originFile", originFile);
			map.put("changeFile", changeFile);
			map.put("filePath", root);
			map.put("fileType", ext);
			map.put("fileSize", Long.toString(fileSize));
			fileList.add(map);
		}
		
		// 파일업로드
		try {
			for(int i = 0; i < multiFileList.size(); i++) {
				File uploadFile = new File(root + "\\" + fileList.get(i).get("changeFile"));
				multiFileList.get(i).transferTo(uploadFile);
				
				/***************************************************************************/
				/* 테이블 입력    테이블 입력    테이블 입력    테이블 입력    테이블 입력    */
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
				String strDate = dateFormat.format(Calendar.getInstance().getTime());
				
				HashMap<String,String> insertMap = new HashMap<String,String>();
				insertMap.put("uuid", fileList.get(i).get("changeFile"));
				insertMap.put("grpId", grpId);
				insertMap.put("orgFileName", fileList.get(i).get("originFile"));
				insertMap.put("storedFileName", fileList.get(i).get("changeFile"));
				insertMap.put("filePath", fileList.get(i).get("filePath"));
				insertMap.put("fileSize", fileList.get(i).get("fileSize"));
				insertMap.put("fileType", fileList.get(i).get("fileType"));
				insertMap.put("createUser", "createUser");
				insertMap.put("createDate", strDate);
				
				Map resultMap = (Map)sampleService.setFile(insertMap);
				String resultMsg = resultMap.get("insert").toString();

				System.out.println("resultMsg : " + resultMsg);

				/* 테이블 입력    테이블 입력    테이블 입력    테이블 입력    테이블 입력    */
				/***************************************************************************/
			}
			returnMap.put("msg","파일 업로드에 성공하였습니다.");
		} catch (Exception e) {
			// 만약 업로드 실패하면 파일 삭제
			for(int i = 0; i < multiFileList.size(); i++) {
				new File(root + "\\" + fileList.get(i).get("changeFile")).delete();
			}
			e.printStackTrace();
			returnMap.put("msg","파일 업로드에 실패하였습니다.");
		}
		
		return returnMap;
	}

}
