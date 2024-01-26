package exdev.com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import exdev.com.common.ExdevConstants;
import exdev.com.common.service.ExdevBaseService;
import exdev.com.common.service.ExdevCommonService;
import exdev.com.common.vo.SessionVO;

import javax.servlet.http.HttpSession;

@Service("ExcelService")
public class ExcelService  extends ExdevBaseService{
	
	
	@Autowired
	private ExdevCommonService commonService;
	
	
	public @ResponseBody Map upload(@RequestParam("file") MultipartFile file,HttpSession session) throws Exception {
		
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
    
    public Workbook download(Map<String, Object>  requestBodyMap ,HttpSession session) throws Exception {
    	
        /**
         * excel sheet 생성
         */
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1"); // 엑셀 sheet 이름
        sheet.setDefaultColumnWidth(28); // 디폴트 너비 설정

        /**
         * header font style
         */
        XSSFFont headerXSSFFont = (XSSFFont) workbook.createFont();
        headerXSSFFont.setColor(new XSSFColor(new byte[]{(byte) 255, (byte) 255, (byte) 255}));

        /**
         * header cell style
         */
        XSSFCellStyle headerXssfCellStyle = (XSSFCellStyle) workbook.createCellStyle();

        // 테두리 설정
        headerXssfCellStyle.setBorderLeft(BorderStyle.THIN);
        headerXssfCellStyle.setBorderRight(BorderStyle.THIN);
        headerXssfCellStyle.setBorderTop(BorderStyle.THIN);
        headerXssfCellStyle.setBorderBottom(BorderStyle.THIN);

        // 배경 설정
        headerXssfCellStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 34, (byte) 37, (byte) 41}));
        headerXssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerXssfCellStyle.setFont(headerXSSFFont);

        /**
         * body cell style
         */
        XSSFCellStyle bodyXssfCellStyle = (XSSFCellStyle) workbook.createCellStyle();

        // 테두리 설정
        bodyXssfCellStyle.setBorderLeft(BorderStyle.THIN);
        bodyXssfCellStyle.setBorderRight(BorderStyle.THIN);
        bodyXssfCellStyle.setBorderTop(BorderStyle.THIN);
        bodyXssfCellStyle.setBorderBottom(BorderStyle.THIN);

        
		Map resultMap = commonService.requestQuery(requestBodyMap, session);
		List<Map<String, Object>> dataList =(List<Map<String, Object>>) resultMap.get("data");
        List<String> keyList = new ArrayList<>(dataList.get(0).keySet());
        String[] columnOrders = (String[])((String)requestBodyMap.get("columnOrders")).split(",");
        
        // columnOrders 배열의 순서에 따라 keyList를 재배열
        List<String> sortedKeyList = new ArrayList<>();
        for (String columnName : columnOrders) {
            if (keyList.contains(columnName)) {
                sortedKeyList.add(columnName);
            }
        }

        
        /**
         * header data
         */
        int rowCount = 0; // 데이터가 저장될 행
        // 정렬된 keyList를 배열로 변환
        String[] headerNames = sortedKeyList.toArray(new String[0]);
        //String[] headerNames = keyList.toArray(new String[0]);
        Row headerRow = null;
        Cell headerCell = null;

        headerRow = sheet.createRow(rowCount++);
        for(int i=0; i<headerNames.length; i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headerNames[i]); // 데이터 추가
            headerCell.setCellStyle(headerXssfCellStyle); // 스타일 추가
        }

        /**
         * body data
         */
		
        // 데이터 크기에 맞게 bodyDatass 초기화
        String[][] bodyDatass = new String[dataList.size()][];

        // dataList에서 각 행의 데이터 추출하여 bodyDatass에 채우기
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Object> rowData = dataList.get(i);
            List<String> rowValues = new ArrayList<>();

            // 각 열의 데이터 추출하여 리스트에 추가
            for (String key : rowData.keySet()) {
                rowValues.add(String.valueOf(rowData.get(key)));
            }

            // 리스트를 String 배열로 변환하여 bodyDatass에 채우기
            bodyDatass[i] = rowValues.toArray(new String[0]);
        }

        // bodyDatass 출력
        for (String[] row : bodyDatass) {
            for (String value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }

        Row bodyRow = null;
        Cell bodyCell = null;

        for(String[] bodyDatas : bodyDatass) {
            bodyRow = sheet.createRow(rowCount++);

            for(int i=0; i<bodyDatas.length; i++) {
                bodyCell = bodyRow.createCell(i);
                bodyCell.setCellValue(bodyDatas[i]); // 데이터 추가
                bodyCell.setCellStyle(bodyXssfCellStyle); // 스타일 추가
            }
        }

        
        return workbook;
    }
}