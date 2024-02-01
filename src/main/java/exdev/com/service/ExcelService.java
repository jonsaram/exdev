package exdev.com.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
	
	
	public @ResponseBody Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpSession session) throws Exception {

		SessionVO sessionVo = (SessionVO) session.getAttribute(ExdevConstants.SESSION_ID);
	    Map<String, Object> resultMap = new HashMap<>();
	    resultMap.put("sessionVo", sessionVo);
	    resultMap.put("filename", file.getOriginalFilename());

	    try {
	        Workbook workbook = WorkbookFactory.create(file.getInputStream());
	        Sheet sheet = workbook.getSheetAt(0);
	        
	        List<Map<String, Object>> excelDataMapList = new ArrayList<>();
	        List<String> headerList = new ArrayList<>();
	        boolean headerRow = true;
	        
	        for (Row row : sheet) {
	            Map<String, Object> cellMap = new LinkedHashMap<>();
	            
	            for (int i = 0; i < row.getLastCellNum(); i++) {
	                Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	                if (headerRow) {
	                    headerList.add(cell.toString());
	                } else {
	                    cellMap.put(headerList.get(i), cell.toString());
	                }
	            }
	            
	            if (!headerRow) {
	                excelDataMapList.add(cellMap);
	            }
	            headerRow = false;
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
	
    public Workbook download(Map<String, Object> requestBodyMap, HttpSession session) throws Exception {
    	
        Workbook workbook = new XSSFWorkbook();
        
        Map downInfo = (Map)requestBodyMap.get("downInfo");       
        String title 	= (String) downInfo.get("title");
        String menu 	= (String) downInfo.get("menu");
        Sheet sheet = workbook.createSheet(title);
        sheet.setDefaultColumnWidth(28);

        // Header font style
        Font headerFont = workbook.createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerFont.setBold(true);

        // Header cell style
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setFont(headerFont);

        // Body cell style
        CellStyle bodyCellStyle = workbook.createCellStyle();
        bodyCellStyle.setBorderLeft(BorderStyle.THIN);
        bodyCellStyle.setBorderRight(BorderStyle.THIN);
        bodyCellStyle.setBorderTop(BorderStyle.THIN);
        bodyCellStyle.setBorderBottom(BorderStyle.THIN);

        // Retrieve data from service
        Map resultMap = commonService.requestQuery(requestBodyMap, session);
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) resultMap.get("data");
        String[] columnOrders = ((String) requestBodyMap.get("columnOrders")).split(",");

        // Add document security
        Row securityRow = sheet.createRow(0);
        Cell securityCell = securityRow.createCell(0);
        securityCell.setCellValue("Document Security");
        Font securityFont = workbook.createFont();
        securityFont.setColor(IndexedColors.RED.getIndex());
        CellStyle securityCellStyle = workbook.createCellStyle();
        securityCellStyle.setFont(securityFont);
        securityCell.setCellStyle(securityCellStyle);

        // Add table name
        Row tableNameRow = sheet.createRow(1);
        Cell tableNameCell = tableNameRow.createCell(0);
        tableNameCell.setCellValue("Menu : "+menu);
        Font tableNameFont = workbook.createFont();
        tableNameFont.setBold(true);
        CellStyle tableNameCellStyle = workbook.createCellStyle();
        tableNameCellStyle.setFont(tableNameFont);
        tableNameCell.setCellStyle(tableNameCellStyle);

        // Add Date
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        Row dateRow = sheet.createRow(2);
        Cell dateCell = dateRow.createCell(0);
        dateCell.setCellValue("Date : " + formattedDateTime);

        // Create header row
        Row headerRow = sheet.createRow(3);
        for (int i = 0; i < columnOrders.length; i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(columnOrders[i]);
            headerCell.setCellStyle(headerCellStyle);
        }

        // Fill data rows
        int rowCount = 4;
        for (Map<String, Object> rowData : dataList) {
            Row dataRow = sheet.createRow(rowCount++);
            for (int i = 0; i < columnOrders.length; i++) {
                Cell cell = dataRow.createCell(i);
                Object value = rowData.get(columnOrders[i]);
                if (value != null) {
                    cell.setCellValue(value.toString());
                }
                cell.setCellStyle(bodyCellStyle);
            }
        }

        return workbook;
    }
  }