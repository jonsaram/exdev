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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import exdev.com.common.ExdevConstants;
import exdev.com.common.service.ExdevCommonService;
import exdev.com.common.vo.SessionVO;
import exdev.com.service.ExcelService;
import exdev.com.service.ExdevSampleService;
import exdev.com.service.FileService;
import exdev.com.service.ApprovalService;

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
	private ExcelService excelService;

	@Autowired
	private FileService fileService;

    @Autowired
    private ApprovalService approvalService;
    
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
	@RequestMapping("/excelUpload.do")
	public @ResponseBody Map excelUpload(@RequestParam("file") MultipartFile file,HttpSession session) throws Exception {
		
		SessionVO sessionVo = (SessionVO)session.getAttribute(ExdevConstants.SESSION_ID);
		Map map = new HashMap();
		map.put("sessionVo", sessionVo);
		map.put("filename", file.getOriginalFilename());

		Map resultMap = new HashMap();
		resultMap.put("msg",null);
				
	  try {

		  	resultMap = excelService.upload(file, session);
            return resultMap;
            
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("msg","");
            resultMap.put("state","");

            return resultMap;
        }
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping("/excelDownload.do")
	public void excelDownload(@RequestParam(value = "queryId", required = true) String queryId,
            @RequestParam(value = "requestParm", required = true) String requestParm,
             HttpServletResponse res,HttpSession session) throws Exception {
	
	try {
			SessionVO sessionVo = (SessionVO)session.getAttribute(ExdevConstants.SESSION_ID);
			Map map = new HashMap();
			map.put("sessionVo", sessionVo);
		
			Map resultMap = new HashMap();
			resultMap.put("msg",null);
			resultMap.put("queryId",queryId);
			resultMap.put("requestParm",new HashMap());
					
			
			Workbook workbook = excelService.download(resultMap, session);
						  	
			String fileName = "excelDownload";
			
			res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			res.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
			ServletOutputStream servletOutputStream = res.getOutputStream();
			
			workbook.write(servletOutputStream);
			workbook.close();
			servletOutputStream.flush();
			servletOutputStream.close();
   
    } catch (Exception e) {
    	
    	e.printStackTrace();
    }
}

    /** 
     * 내용        : 결재상신
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 01. 25 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */

    @PostMapping("/approvalSave.do")
    public @ResponseBody Map  approvalSave(@RequestParam("attach_file") List<MultipartFile> multiFileList,           
            HttpServletRequest request, HttpSession session)  throws Exception {
        
        SessionVO sessionVo = (SessionVO)session.getAttribute(ExdevConstants.SESSION_ID);
        String msg = "";
        Map<String, String> returnMap = new HashMap<String, String>();
        
        // 받아온것 출력 확인
        System.out.println("multiFileList : " + multiFileList);
        String grpId = request.getParameter("groupId");
        
        String[] file_uuids = request.getParameterValues("file_uuids");

        System.out.println("grpId : " + grpId);

        
        // path 가져오기
        String path = request.getSession().getServletContext().getRealPath("resources");
        String root = path + "\\" + "uploadFiles";
        returnMap = fileService.fileUploadMulti( multiFileList, root , grpId, file_uuids);
        msg = returnMap.get("msg").toString();

        if( ExdevConstants.REQUEST_FAIL.equals(msg)) {
            returnMap.put("msg","결재상신 에 성공하였습니다.");
            return returnMap;
        }
        

        String title = request.getParameter("title");
        String content = request.getParameter("content");    
        String appr_uuid = request.getParameter("appr_uuid");


        System.out.println("title : " + title);
        System.out.println("content : " + content);    
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        String strDate = dateFormat.format(Calendar.getInstance().getTime());
        
        Map<String, String> apprMap = new HashMap<String, String>();

        apprMap.put("uuid",appr_uuid);
        apprMap.put("requestUser","requestUser");
        apprMap.put("title",title);
        apprMap.put("contents",content);
        apprMap.put("state","REQUEST");
        apprMap.put("approvalDate",strDate);
        apprMap.put("createUser","createUser");
        apprMap.put("createDate",strDate);
        

        String[] userIds       = request.getParameterValues("user_ids");
        String[] apprUserUuids = request.getParameterValues("appr_user_uuid");
        
        
        Map<String, String[]> apprUserMap = new HashMap<String, String[]>();
                  
        apprUserMap.put("apprUserUuids",apprUserUuids);
        apprUserMap.put("userIds",userIds);
        
        returnMap = approvalService.insertApproval( apprMap,apprUserMap );
        
        msg = returnMap.get("msg").toString();
        if( ExdevConstants.REQUEST_SUCCESS.equals(msg)) {
            returnMap.put("msg","결재상신 에 성공하였습니다.");
        }else{
            returnMap.put("msg","결재상신 에 실패하였습니다.");
        }
        
        return returnMap;
    }
    
    /** 
     * 내용        : 다중 첨부파일 업로드 샘플
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 01. 16 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */

    @SuppressWarnings({ "unused", "rawtypes" })
	@PostMapping("/multiFileUpload.do")
	public @ResponseBody Map  multiFileUpload(@RequestParam("attach_file") List<MultipartFile> multiFileList,			
            HttpServletRequest request, HttpSession session)  throws Exception {
		
        SessionVO sessionVo = (SessionVO)session.getAttribute(ExdevConstants.SESSION_ID);
        
        Map returnMap = new HashMap();
        
		// 받아온것 출력 확인
		System.out.println("multiFileList : " + multiFileList);
		String grpId = request.getParameter("groupId");
		String[] uuids = request.getParameterValues("uuids");
		
		// path 가져오기
		String path = request.getSession().getServletContext().getRealPath("resources");
		String root = path + "\\" + "uploadFiles";
		returnMap = fileService.fileUploadMulti( multiFileList, root , grpId, uuids);
		
		String msg = returnMap.get("msg").toString();
		if( "SUCCESS".equals(msg)) {
			returnMap.put("msg","파일 업로드에 성공하였습니다.");
		}else{
			returnMap.put("msg","파일 업로드에 실패하였습니다.");
		}
		
		return returnMap;
	}

    /** 
     * 내용        : 다중 첨부파일 삭제 샘플
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 01. 18 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */

    @SuppressWarnings({ "unused", "rawtypes" })
    @PostMapping("/fileDelete.do")
    public @ResponseBody Map  fileDelete(HttpServletRequest request, HttpSession session)  throws Exception {
        
        SessionVO sessionVo = (SessionVO)session.getAttribute(ExdevConstants.SESSION_ID);
        
        Map<String, String> returnMap = new HashMap<String, String>();
            
        String[] delete_file_list = request.getParameterValues("delete_file_list");
 
        returnMap = fileService.fileDeleteMulti( delete_file_list );
        
        String msg = returnMap.get("msg").toString();
        if( "SUCCESS".equals(msg)) {
            returnMap.put("msg","파일 삭제에 성공하였습니다.");
        }else{
            returnMap.put("msg","파일 삭제에 실패하였습니다.");
        }
        
        return returnMap;
    }

    /** 
     * 내용        : CKEditor 저장 샘플
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 01. 22 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */

    @SuppressWarnings({ "unused", "rawtypes" })
    @PostMapping("/editImageUpload1.do")
    public @ResponseBody Map  editImageUpload1( 
            MultipartRequest request,  HttpSession session,HttpServletRequest httpServletRequest)  throws Exception {
        
        Map<String, Object> returnMap = new HashMap<>();
        SessionVO sessionVo = (SessionVO)session.getAttribute(ExdevConstants.SESSION_ID);
        
        
        String localLocation = httpServletRequest.getSession().getServletContext().getRealPath("resources")+ "\\" + "editorFiles";

        MultipartFile file = request.getFile("upload");
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.indexOf("."));

        String uuidFileName = UUID.randomUUID() + ext;
        String localPath = localLocation +"\\"+ uuidFileName;
        System.out.println("localPath ===>"+localPath); 
        File localFile = new File(localPath);
        if(!localFile.exists()) {
            localFile.mkdirs();
        }
        file.transferTo(localFile);
        
        returnMap.put("uploaded", true); 
        returnMap.put("url", localPath); 
        
        //returnMap.put("uploaded", false);
        return returnMap;
    }

    /** 
     * 내용        : CKEditor 저장 샘플
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 01. 22 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자
     */

    @SuppressWarnings({ "unused", "rawtypes" })
    @PostMapping("/editImageUpload.do")
    public @ResponseBody Map  editImageUpload( 
            MultipartRequest request,  HttpSession session,HttpServletRequest httpServletRequest)  throws Exception {
        
        Map returnMap = new HashMap();
        SessionVO sessionVo = (SessionVO)session.getAttribute(ExdevConstants.SESSION_ID);
        
        
        String localLocation = httpServletRequest.getSession().getServletContext().getRealPath("resources")+ "\\" + "editorFiles";

        MultipartFile file = request.getFile("upload");
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.indexOf("."));

        String uuidFileName = UUID.randomUUID() + ext;
        String localPath = localLocation +"\\"+ uuidFileName;
        System.out.println("localPath ===>"+localPath); 
        File localFile = new File(localPath);
        if(!localFile.exists()) {
            localFile.mkdirs();
        }
        file.transferTo(localFile);
        
  
        return returnMap;
    }

}
