package exdev.com.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import exdev.com.common.ExdevConstants;
import exdev.com.common.dao.ExdevCommonDao;
import exdev.com.common.service.ExdevBaseService;


/** 
 * 파일저장 서비스
 * @생 성 자   : 이응규
 * @생 성 일자 : 2024. 01. 17
 * @수 정 자   : 
 * @수 정 일자 :
 * @수 정 자
 */
@Service("FileService")
public class FileService extends ExdevBaseService{
	
	@Autowired
	private ExdevCommonDao commonDao;


    /** 
     * 멀티 파일삭제 서비스
     * @생 성 자   : 이응규
     * @생 성 일자 : 2024. 01. 18 : 최초 생성
     * @수 정 자   : 
     * @수 정 일자 :
     * @수 정 자   :
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public Map fileDeleteMulti(String[] uuidArry) throws Exception {
	    
	    Map<String, String> returnMap = new HashMap<String, String>();
	    try {
            for(  String uuid:uuidArry ) {
                System.out.println("FileService uuid =>"+uuid); 
                Map uuidMap = new HashMap();
                uuidMap.put("uuid" , uuid  );
                List<Map> list = commonDao.getList("Sample.getFile", uuidMap);
                int result = 0;
                
                for(Map fileMap : list) {
                    new File(fileMap.get("FILEPATH") + "\\" + fileMap.get("STOREDFILENAME")).delete();
                    result += commonDao.delete("Sample.deleteFile", uuidMap);
                    
                }
            }
        
            returnMap.put("msg",ExdevConstants.REQUEST_SUCCESS);
	    } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("msg",ExdevConstants.REQUEST_FAIL);
        }
        return returnMap;
    }
    
	/** 
	 * 멀티 파일저장 서비스
	 * @생 성 자   : 이응규
	 * @생 성 일자 : 2024. 01. 17 : 최초 생성
	 * @수 정 자   : 
	 * @수 정 일자 :
	 * @수 정 자
	 */
	public Map fileUploadMulti( List<MultipartFile> multiFileList, String  path , String  grpId,String[] uuids) throws Exception {
		
		Map<String, String> returnMap = new HashMap<String, String>();
		File fileCheck = new File(path);
		if(!fileCheck.exists()) fileCheck.mkdirs();
		
        List<Map<String, String>> fileList = new ArrayList<>();
		
		for(int i = 0; i < multiFileList.size(); i++) {
			String originFile = multiFileList.get(i).getOriginalFilename();
			long fileSize = multiFileList.get(i).getSize();
			System.out.println("fileSize 1 ==>"+fileSize);
			String ext = originFile.substring(originFile.lastIndexOf("."));
			String uuid = uuids[i];
			
			String changeFile = uuid + ext;
			
			System.out.println("originFile : " + originFile);
			System.out.println("changeFile : " + changeFile);
			System.out.println("ext : " + ext);
			Map<String, String> map = new HashMap<>();
			map.put("uuid", uuid);
			map.put("originFile", originFile);
			map.put("changeFile", changeFile);
			map.put("filePath", path);
			map.put("fileType", ext);
			map.put("fileSize", Long.toString(fileSize));
			fileList.add(map);
		}
		int result = 0;
		// 파일업로드
		try {
			for(int i = 0; i < multiFileList.size(); i++) {
				File uploadFile = new File(path + "\\" + fileList.get(i).get("changeFile"));
				multiFileList.get(i).transferTo(uploadFile);
				
				/***************************************************************************/
				/* 테이블 입력    테이블 입력    테이블 입력    테이블 입력    테이블 입력    */
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
				String strDate = dateFormat.format(Calendar.getInstance().getTime());
				
				HashMap<String,String> insertMap = new HashMap<String,String>();
				insertMap.put("uuid", fileList.get(i).get("uuid"));
				insertMap.put("grpId", grpId);
				insertMap.put("orgFileName", fileList.get(i).get("originFile"));
				insertMap.put("storedFileName", fileList.get(i).get("changeFile"));
				insertMap.put("filePath", fileList.get(i).get("filePath"));
				insertMap.put("fileSize", fileList.get(i).get("fileSize"));
				insertMap.put("fileType", fileList.get(i).get("fileType"));
				insertMap.put("createUser", "createUser");
				insertMap.put("createDate", strDate);
				
				result += commonDao.insert("Sample.setFile", insertMap);
				System.out.println("insert result =>"+result);
				
				/* 테이블 입력    테이블 입력    테이블 입력    테이블 입력    테이블 입력    */
				/***************************************************************************/
			}
			System.out.println(" multiFileList.size() =>"+multiFileList.size());
			System.out.println(" result =>"+result);
			if( result == multiFileList.size()) {
				returnMap.put("msg", ExdevConstants.REQUEST_SUCCESS);	
			}else {
				for(int i = 0; i < multiFileList.size(); i++) {
					new File(path + "\\" + fileList.get(i).get("changeFile")).delete();
				}
				returnMap.put("msg", ExdevConstants.REQUEST_FAIL);
			}	
			
		} catch (Exception e) {
		    // 만약 업로드 실패하면 파일 삭제
			for(int i = 0; i < multiFileList.size(); i++) {
				new File(path + "\\" + fileList.get(i).get("changeFile")).delete();
			}
			e.printStackTrace();
			returnMap.put("msg", ExdevConstants.REQUEST_FAIL);
		}
		return returnMap;
	}
	
}