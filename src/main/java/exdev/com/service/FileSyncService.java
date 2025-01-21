package exdev.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exdev.com.common.ExdevConstants;
import exdev.com.common.dao.ExdevCommonDao;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class FileSyncService {
	
    //SELECT * FROM TBL_EXP_FILEMNG WHERE FILE_PATH LIKE ExdevConstants.FILE_DIRECTORY_PATH ||'%' 안에 있는 파일 싱크
	
	@Autowired
	private ExdevCommonDao commonDao;

    /*
     * 	파일 path 가 ExdevConstants.FILE_DIRECTORY_PATH+File.separator+ExdevConstants.FILE_UPLOAD_PATH 로시작하는 폴더 
     *
     *	DB에 있는 파일명과 실제 파일 시스템에 존재하는 목록 비교 후
     * 	DB에 없는데, 파일에 있는경우 해당 파일 삭제
     *  
     *  삭제한 파일명 DB저장 ( SELECT * FROM TBL_EXP_LOG )
     */
	
    public void syncFilesWithDB(String directoryPath, List<String> ownerCodeList) throws Exception {
    	
        //	DB( TBL_EXP_FILEMNG )에서 파일목록 조회
    	Map param = new HashMap();
    	param.put("ONWER_CODE_LIST", ownerCodeList);
    	param.put("FILE_UPLOAD_PATH", directoryPath);
        List<Map> list = commonDao.getList("Filemng.getFileMng", param);
        
        //	파일목록에서 파일명 리스트 ( TBL_EXP_FILEMNG.FILE_PATH 의 파일명 부분 )
        List<String> dbFiles = list.stream()
                                   .map(map -> (String) (new File((String)map.get("FILE_PATH")).getName()))
                                   .collect(Collectors.toList());

        //	파일 시스템에서 파일 목록 가져오기
        List<File> fileSystemFiles = getFileSystemFiles(directoryPath);
        
        //	DB( TBL_EXP_FILEMNG )에 없는데, 실제 파일이 있는경우 해당 목록 조회
        List<File> filesToDelete = fileSystemFiles.stream()
                .filter(file -> 
                {
                	System.out.println("==System "+ file.getName());
                	if(!dbFiles.contains(file.getName()))
                		return true;
					return false;
                })
                .collect(Collectors.toList());

        if( filesToDelete.size() >= 1) {
        	// 파일 삭제
        	deleteFiles(directoryPath, filesToDelete);
        }
    }

    // 파일 삭제 메서드
    private void deleteFiles(String directoryPath, List<File> filesToDelete) {
        for (File file : filesToDelete) {
            
        	String fileName = file.getAbsolutePath();
			

        	if (true) 
//				if (file.delete()) 
			{ 
				System.out.println("Deleted: " + fileName); 
				Map log =new HashMap(); 
				log.put("USER_ID", "BATCH"); 
				log.put("LOG_TYPE","GARBAGE_FILE_DELETED"); 
				log.put("LOG_CONTENTS", fileName);
				log.put("LOG_DESC","GARBAGE_FILE_DELETED"); 
				log.put("CREATE_USER", "SYSTEM"); 
				try { 
					commonDao.insert("Filemng.insertDeleteLog",log); 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); 
				} 
			} else { 
				System.err.println("Failed to delete: " +fileName); 
			}
			
        }
    }
    
    private List<File> getFileSystemFiles(String directoryPath) {
        File folder = new File(directoryPath+"/"+ExdevConstants.FILE_UPLOAD_PATH + "/");
        List<File> fileList = new ArrayList<>();
        getFilesRecursively(folder, fileList);
        return fileList;
    }
    
    private void getFilesRecursively(File folder, List<File> fileList) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file);
                } else if (file.isDirectory()) {
                    getFilesRecursively(file, fileList);
                }
            }
        }
    }
    
    
}
