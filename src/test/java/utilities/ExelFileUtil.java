package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.google.common.collect.Table.Cell;

public class ExelFileUtil {
	
	Workbook wb;
	
	public ExelFileUtil (String Excelpath ) throws EncryptedDocumentException, IOException {
		FileInputStream fi = new FileInputStream(Excelpath);
		wb = WorkbookFactory.create(fi);
		
	}
	public int rowCount (String Sheetname){
		return wb.getSheet(Sheetname).getLastRowNum();
		
		
	}
	public String getCellData (String Sheetname, int row, int col) {
		
		String data;
		if (wb.getSheet(Sheetname).getRow(row).getCell(col).getCellType()== CellType.NUMERIC){
			int celldata = (int)wb.getSheet(Sheetname).getRow(row).getCell(col).getNumericCellValue();
			data = String.valueOf(celldata);
		}
		else {
			data = wb.getSheet(Sheetname).getRow(row).getCell(col).getStringCellValue();
		}
		return data;
	}
	public void setCellData(String Sheetname,int row,int col, String status,String Excelpath) throws IOException {
		org.apache.poi.ss.usermodel.Sheet ws = wb.getSheet(Sheetname);
		
		Row rowNum =ws.getRow(row);
		org.apache.poi.ss.usermodel.Cell cell =rowNum.createCell(col);
		
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("pass")) {
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(col).setCellStyle(style);
			
			
		}
		else if(status.equalsIgnoreCase("Fail")) {
			
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(col).setCellStyle(style);
			
			
		}
		
		else if(status.equalsIgnoreCase("Blocked")) {
			
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(col).setCellStyle(style);
			
		}
		FileOutputStream fo = new FileOutputStream(Excelpath);
		wb.write(fo);
		
		
		
		
	}
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		ExelFileUtil li = new ExelFileUtil("D:/Sample.xlsx");
		int rc = li.rowCount("Emp");
		System.out.println(rc);
		
		for(int i=1;i<=rc;i++) {
			String fname = li.getCellData("Emp", i, 0);
			String ame = li.getCellData("Emp", i, 1);
			System.out.println(fname+" " + ame +" ");
			li.setCellData("Emp", i, 4, "pass", "D:/Result.xlsx");
			
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
