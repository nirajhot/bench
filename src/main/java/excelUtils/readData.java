package excelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class readData {

	private static HSSFWorkbook workbook;
	private static HSSFSheet sheet;
	private static HSSFRow row;
	private static HSSFCell cell;

	public void setExcelFile(String sheetName) throws IOException {
		File file =    new File(constants.excelPath);
		FileInputStream inputStream = new FileInputStream(file);
		workbook=new HSSFWorkbook(inputStream);
		sheet=workbook.getSheet(sheetName);
	}

	public String getCellData(int rowNumber,int cellNumber){
		//getting the cell value from rowNumber and cell Number
		cell =sheet.getRow(rowNumber).getCell(cellNumber);
		if(cell.getCellType() == CellType.STRING)
			return cell.getStringCellValue();
		else if(cell.getCellType() == CellType.NUMERIC)
		{
			String cellValue  = String.valueOf(cell.getNumericCellValue());
			return cellValue;
		}else if(cell.getCellType() == CellType.BLANK)
			return "";
		else
			return String.valueOf(cell.getBooleanCellValue());
	}

	public int getRowCountInSheet(){
		int rowcount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		return rowcount;
	}

	public void setCellValue(int rowNum,int cellNum,String cellValue,String excelFilePath) throws IOException {
		//creating a new cell in row and setting value to it      
		sheet.getRow(rowNum).createCell(cellNum).setCellValue(cellValue);

		FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		workbook.write(outputStream);
	}
}	