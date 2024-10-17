package exdev.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exdev.com.common.dao.ExdevCommonDao;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class FileSyncService {
	
	@Autowired
	private ExdevCommonDao commonDao;
    

    // 파일 시스템에서 파일 목록 가져오기
    public List<String> getFileSystemFiles(String directoryPath) {
        File folder = new File(directoryPath);
        return Arrays.stream(folder.listFiles())
                .filter(File::isFile)
                .map(File::getName)
                .collect(Collectors.toList());
    }

    // DB와 파일 시스템 간 비교 후 삭제
    public void syncFilesWithDB(String directoryPath) throws Exception {
        // DB에서 파일 목록 가져오기
        List<Map> list = commonDao.getList("Filemng.getFileMng", new HashMap());
        // FILE_PATH 값을 가진 문자열 리스트로 변환
        List<String> dbFiles = list.stream()
                                   .map(map -> (String) map.get("FILE_PATH"))  // FILE_PATH 값을 추출
                                   .collect(Collectors.toList());

        // 파일 시스템에서 파일 목록 가져오기
        List<String> fileSystemFiles = getFileSystemFiles(directoryPath);
        
        // DB에 없는 파일 찾기
        List<String> filesToDelete = fileSystemFiles.stream()
                .filter(file -> !dbFiles.contains(file))
                .collect(Collectors.toList());

        // 파일 삭제
        deleteFiles(directoryPath, filesToDelete);
    }

    // 파일 삭제 메서드
    private void deleteFiles(String directoryPath, List<String> filesToDelete) {
        for (String fileName : filesToDelete) {
            File file = new File(directoryPath + "/" + fileName);
            //if (file.delete()) {
            //    System.out.println("Deleted: " + fileName);
            //} else {
            //    System.err.println("Failed to delete: " + fileName);
            //}
        }
    }
}
