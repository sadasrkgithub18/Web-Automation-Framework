package com.nagarro.supporters;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader  
{

	private String filepath;
	private FileInputStream fip;
	private Workbook workbook;
	private Sheet sheet;
	private Row row;
	private Cell cell;
	private String cellValue;
	private FileOutputStream fout;
	
	public ExcelReader(String filepath) throws EncryptedDocumentException, IOException, InvalidFormatException
	{
	  this.filepath	= filepath;
	  fip = new FileInputStream(filepath);  
	  workbook = WorkbookFactory.create(fip);
	  Optional<Workbook> optional = Optional.ofNullable(workbook);
	  if(optional.isPresent())
	  {
		  // Don't do any thing
	  }
	  
	  else
	  {
		  System.out.println("Workbook is not available");
	  }
	  
	}
	
	
	public Sheet getSheetObj(String sheetName)
	{
		sheet = workbook.getSheet(sheetName);
		return sheet;	
	}
	
	
	public Sheet getSheetObj(int index)
	{
		sheet = workbook.getSheetAt(index);
		return sheet;	
	}
	
	
	public String getSingleCellData(String sheetName, int rowNum, int cellNum) 
	{
		
		sheet = getSheetObj(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		
		if(cell.getCellType() == CellType.STRING)
		{
			cellValue = cell.getStringCellValue();
		}
		
		else if(cell.getCellType() == CellType.NUMERIC)
		{
			cellValue = cell.getNumericCellValue()+"";
		}
		
		else if(cell.getCellType() == CellType.BOOLEAN)
		{
			cellValue = cell.getBooleanCellValue()+"";
		}
		
		return cellValue;
	
	}
	
	
	
	
	 
	
	
	public List<String> getSingleRowData(String sheetName, int rowNum)
	{
		List<String> sheetData = new ArrayList<String>(); 
		sheet = getSheetObj(sheetName);

		row = sheet.getRow(rowNum);
			
			for(int j=0; j<row.getLastCellNum();j++)
			{
			  cell = row.getCell(j);

			  if(cell.getCellType() == CellType.STRING)
			  {
				cellValue = cell.getStringCellValue();		
			  }
			
			  else if(cell.getCellType() == CellType.NUMERIC)
			  {
				cellValue = cell.getNumericCellValue()+"";	
			  }
			
			  else if(cell.getCellType() == CellType.BOOLEAN)
			  {
				cellValue = cell.getBooleanCellValue()+"";
			  }
			  sheetData.add(cellValue);	
		}
			
      	return sheetData;
		
	}
	
	
	
	public List<String> getTotalSheetData(String sheetName)
	{
		
		List<String> sheetData = new ArrayList<String>(); 
		
		sheet = getSheetObj(sheetName);
		
		for(int i=0; i<=sheet.getLastRowNum();i++)
		{
			row = sheet.getRow(i);
			
			for(int j=0; j<row.getLastCellNum();j++)
			{
				cell = row.getCell(j);
				
				if(cell.getCellType() == CellType.STRING)
				{
					cellValue = cell.getStringCellValue();
				}
				
				else if(cell.getCellType() == CellType.NUMERIC)
				{
					cellValue = cell.getNumericCellValue()+"";
				}
				
				else if(cell.getCellType() == CellType.BOOLEAN)
				{
					cellValue = cell.getBooleanCellValue()+"";
				}
				sheetData.add(cellValue);
			}
			
		}
		return sheetData;
		
	}
	
	public Set<String> getUniqueData(String sheetName)
	{
		List<String> data = getTotalSheetData(sheetName);
		Set<String> uniquedata = new LinkedHashSet<String>(data);
		return uniquedata;
		
	}
	
	
	public int findOccurancesOfaWord(String sheetName, String word)
	{
		int count = 0;
		List<String> data = getTotalSheetData(sheetName);		
		for(String ele : data)
		{
			if(ele.equalsIgnoreCase(word))
			{
			  count++;
			}
		}
		return count;
		
	}

	
	public String writeData(String sheetName, int rowNum, int cellNum, String tcName) throws IOException
	{
		sheet = getSheetObj(sheetName);
		row  = sheet.createRow(rowNum);
		cell = row.createCell(cellNum);
		cell.setCellValue(tcName);
		fout = new FileOutputStream(filepath);
		workbook.write(fout);
		
		return tcName;
		
	}
	
	
	
	
	public String writeDataTwo(String sheetName, int rowNum, int cellNum, String tcName) throws IOException
	{
		sheet = getSheetObj(sheetName);
		row  = sheet.getRow(rowNum);
		cell = row.createCell(cellNum);
		cell.setCellValue(tcName);
		fout = new FileOutputStream(filepath);
		workbook.write(fout);
		
		return tcName;
		
	}
	
	
	   public int getRowCount(String sheetName)
	   {
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else
		{
		   sheet = workbook.getSheetAt(index);
		   int number=sheet.getLastRowNum()+1;
		   return number;
		}
	
	   }
	   
	    // find whether sheets exists	
		public boolean isSheetExist(String sheetName)
		{
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
			{
				index=workbook.getSheetIndex(sheetName.toUpperCase());
				if(index==-1)
						return false;
					else
						return true;
			}
			else
				return true;
		}
	   
		
		// returns number of columns in a sheet	
			public int getColumnCount(String sheetName)
			{
					// check if sheet exists
					if(!isSheetExist(sheetName))
					 return -1;
					
					sheet = workbook.getSheet(sheetName);
					row = sheet.getRow(0);
					
					if(row==null)
						return -1;
					
					return row.getLastCellNum();
					
					
					
			}

			
			
			// returns true if sheet is created successfully else false
			public boolean addSheet(String  sheetname)
			{		
				
				//FileOutputStream fileOut;
				try {
					 workbook.createSheet(sheetname);	
					 fout = new FileOutputStream(filepath);
					 workbook.write(fout);
					 fout.close();		    
				} catch (Exception e) {			
					e.printStackTrace();
					return false;
				}
				return true;
			}
			

			
			// returns true if sheet is removed successfully else false if sheet does not exist
			public boolean removeSheet(String sheetName)
			{		
				int index = workbook.getSheetIndex(sheetName);
				if(index==-1)
					return false;
				
				try 
				{
					workbook.removeSheetAt(index);
					fout = new FileOutputStream(filepath);
					workbook.write(fout);
					fout.close();		    
				} 
				catch (Exception e) 
				{			
					e.printStackTrace();
					return false;
				}
				return true;
			}
			
			
			
			
			
			
			
			// returns true if column is created successfully
			public boolean addColumn(String sheetName,String colName)
			{
				//System.out.println("**************addColumn*********************");
				
				try{				
					fip = new FileInputStream(filepath); 
					workbook = WorkbookFactory.create(fip);
					int index = workbook.getSheetIndex(sheetName);
					if(index==-1)
						return false;
					
//				XSSFCellStyle style = workbook.createCellStyle();
//				style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
//				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//				
				sheet=workbook.getSheetAt(index);
				
				row = sheet.getRow(0);
				if (row == null)
					row = sheet.createRow(0);
				
				//cell = row.getCell();	
				//if (cell == null)
				//System.out.println(row.getLastCellNum());
				if(row.getLastCellNum() == -1)
					cell = row.createCell(0);
				else
					cell = row.createCell(row.getLastCellNum());
			        
			        cell.setCellValue(colName);
			        //cell.setCellStyle(style);
			        
			        fout = new FileOutputStream(filepath);
					workbook.write(fout);
					fout.close();		    

				}
				catch(Exception e)
				{
					e.printStackTrace();
					return false;
				}
				
				return true;
				
				
			}
			
			
			
			
			
			
			// removes a column and all the contents
			public boolean removeColumn(String sheetName, int colNum) {
				try{
				if(!isSheetExist(sheetName))
					return false;
				fip = new FileInputStream(filepath); 
				workbook =  WorkbookFactory.create(fip);
				sheet=workbook.getSheet(sheetName);
				
				/*XSSFCellStyle style = workbook.createCellStyle();
				style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
				XSSFCreationHelper createHelper = workbook.getCreationHelper();
				style.setFillPattern(HSSFCellStyle.NO_FILL);
				*/
			    
			
				for(int i =0;i<getRowCount(sheetName);i++){
					row=sheet.getRow(i);	
					if(row!=null){
						cell=row.getCell(colNum);
						if(cell!=null){
							//cell.setCellStyle(style);
							row.removeCell(cell);
						}
					}
				}
				fout = new FileOutputStream(filepath);
				workbook.write(fout);
				fout.close();
				}
				catch(Exception e){
					e.printStackTrace();
					return false;
				}
				return true;
				
			}
			
			
			
			
	public static void main(String[] args) throws EncryptedDocumentException, IOException, InvalidFormatException 
	{
		ExcelReader excelReader = new ExcelReader("D:\\testdata\\TestData.xlsx");
		
		System.out.println(excelReader.addSheet("ARKGold"));
		System.out.println("================");
        
		System.out.println(excelReader.addColumn("ARKGold", "EmpName"));
		System.out.println("================");
        
		System.out.println(excelReader.removeColumn("ARKGold",0));
		System.out.println("================");
        
		System.out.println(excelReader.removeSheet("ARKGold"));
		
		System.out.println("================");
        
        
		
		
	//	System.out.println(excelReader.getColumnCount("Java"));
		System.out.println("================");
        
		//System.out.println(excelReader.isSheetExist("Java"));
		
		/*System.out.println(excelReader.getSingleCellData("Java",1, 1));
        System.out.println("================");
        System.out.println(excelReader.getTotalSheetData("Spring"));
        System.out.println("================");
        System.out.println(excelReader.getSingleRowData("Java",0));
        System.out.println("================");
        Set<String> data = excelReader.getUniqueData("Spring");
        System.out.println(data);
        System.out.println("================");
        System.out.println(excelReader.findOccurancesOfaWord("Spring","work"));
        System.out.println("================");
        
        
		System.out.println("================");
        String name = excelReader.writeData("TCName",0,3,"Swiss");
        System.out.println(name);
*/         
	}

}
