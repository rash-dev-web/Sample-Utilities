package com.excelutils;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;

public class ReadingExcel {
	
	private static final String ExcelPath = null;
	private static FileInputStream fis=null;
	private static File f = null;
	private static XSSFWorkbook wb = null;
	private static XSSFSheet sheet = null;
	private static XSSFRow row=null;
	private static XSSFCell cell=null;
	private static Object[][]  ob=null;
	public static Map<String, Object[]> testresultdata;

		//static block
	static 
	{
		f = new File(System.getProperty("user.dir")+"/DataFile/Demo5.xlsx");
		//f=new File("D:\\ns00341167\\TECHM\\Nitesh\\OPUS_ESK_OM\\Automation\\opus automation\\Test.xlsx");
		try
		{
			fis = new FileInputStream(f);
			wb = new XSSFWorkbook(fis);
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static int rowCount(String sheetname)
	{
		sheet=wb.getSheet(sheetname);
		
		int startingrownum=	sheet.getFirstRowNum();  //0
		int rows =  sheet.getLastRowNum();  //4
		int rowcount=rows+startingrownum+1;
		//System.out.println(rows);
		//System.out.println(rowcount);
		return rowcount;
		
	}
	public static int columnCount(int cid)
	{
		row=sheet.getRow(cid);
		
		int columns = row.getLastCellNum();
		 //int columncount=columns;
		//System.out.println(columns);
		return columns;
		
	}
	public  static Object[][] getData(String sheetname)
	{
		int rowcount=rowCount(sheetname);
		//System.out.println(rowcount);
		int columncount=columnCount(1);
	//	System.out.println(columncount);

	    ob = new Object[rowcount-1][columncount];
		for(int i=2;i<=rowcount;i++)
		{
			row = sheet.getRow(i-1);
			 for(int j=0;j<columncount;j++)
			 {	
				 cell=row.getCell(j);
				// System.out.println(cell);
				// cell.setCellType(Cell.CELL_TYPE_STRING);
				try
				{
				 String CellData = null;         
			     switch (cell.getCellType()){
			     case Cell.CELL_TYPE_STRING:
			          CellData = cell.getStringCellValue();
			          ob[i-2][j]=CellData;
			         // System.out.println(ob[i][j]);
			          break;
			     case Cell.CELL_TYPE_NUMERIC:
			          if (DateUtil.isCellDateFormatted(cell))
			          {
			             CellData = cell.getDateCellValue().toString();

			          }
			          else
			          {  
			             CellData = Double.toString(cell.getNumericCellValue());
			             if (CellData.contains(".0"))//removing the extra .0
			              {
			               CellData = CellData.substring(0, CellData.length()-2);
			               
			              }
							//System.out.println(CellData);

			           }
			          ob[i-2][j]=CellData;
			           break;
			     case Cell.CELL_TYPE_BLANK:
			           CellData = "";
			           ob[i-2][j]=CellData;
			           break;
			     case Cell.CELL_TYPE_BOOLEAN:
			           CellData = Boolean.toString(cell.getBooleanCellValue());
			           ob[i-2][j]=CellData;
			           break;
			     }      
			        }
				catch (Exception e){}
			 }


			 }
		return ob;
			
		}
  
	@SuppressWarnings("finally")
	public static String[] fetchData(String strTCName,String strDataSheet) {

		int rowcount=rowCount(strDataSheet);
		String data[]=null;
		try
		{
			for(int i = 1;i<rowcount;i++)
			{
				//System.out.println(i);
				XSSFRow cell1=sheet.getRow(i);
				if(cell1 != null)
				{	//System.out.println(sheet.getRow(i).getCell(0).getStringCellValue());
					if(sheet.getRow(i).getCell(0).getStringCellValue().equals(strTCName))
					{
						
						int columncounts=cell1.getLastCellNum();
						System.out.println("column count "+columncounts);
						data=getExcelData(i,columncounts);
					//System.out.println(data[i]);
					}
				}
				else
					break;
			}

		}
		catch (Exception e)
		{
		
		}
		return data;	
	}
//	
	
	public static String[] getExcelData(int rownum,int columncount){
		
		String data[]=new String[columncount-1];
		//System.out.println("row number"+ rownum);
		int k=0;
		for(int j=1;j<columncount;j++)
		{
			XSSFCell cell=sheet.getRow(rownum).getCell(j);
			System.out.println("row and column"  +sheet.getRow(rownum).getCell(j).getStringCellValue() );
			if(cell != null)
			{
				String data1=cell.getStringCellValue();
				//System.out.println(data1);
				data[k]=data1;
				//System.out.println(data[k]);
				k++;
				
			}
			/*else
				break;*/
		}
		
		//String [] s=testData(data);
	return data;

}
	
	
	
	public static LinkedList<String> testData(String strTCName,String strDataSheet){
		LinkedList<String> as = new LinkedList<String>();
		String []arr=fetchData(strTCName,strDataSheet);
	//	System.out.println(arr[1]);
		//int count=0;
		for(int i=0;i<arr.length;i++){
			if(arr[i].contains(",")){
			String[] splits=	arr[i].split(",");
			System.out.println(splits.length);
				for(int j=0 ;j<splits.length;j++){
					 
					 as.add(splits[j]);
					
				}
			}
			else
			{
				
				as.add(arr[i]);
				
			}
			
		}
		
		return as;
			
		}
		//return asd;
		
	
	
//	public static void highlightCell(String color, int rownum, int colnum)throws Exception
//	 {
//	  try{
//	  cell = sh.getRow(rownum).getCell(colnum,Row.RETURN_BLANK_AS_NULL);
//	   }catch(Exception e){System.out.println("cell is null");}
//	  if (cell == null) 
//	    {
//	   cell = row.createCell(colnum);
//	    }
//	  CellStyle cellstyle = wb.createCellStyle();
//	  color = color.toUpperCase();
//	  
//	  XSSFColor mycolor;
//	switch(color)
//	  {
//	  case "GREEN":
//	   mycolor = new XSSFColor(Color.GREEN);
//	   break;
//	  case "RED":
//	   mycolor = new XSSFColor(Color.RED);
//	  break;
//	  default:
//	   mycolor = new XSSFColor(Color.BLACK);
//	  break;
//	  }
//	  cellstyle.setFillForegroundColor(mycolor); 
//	  cellstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//	  cell.setCellStyle(cellstyle);
//	  FileOutputStream fileOut = new FileOutputStream(ExcelPath);
//	                wb.write(fileOut);
//	                fileOut.flush();
//	  fileOut.close();
//	  System.out.print("color done");  
//	 }
	
	 public static void setupBeforeSuite(ITestContext context) {
		   //  baseUrl = "http://www.seleniummaster.com";
		     //create a new work book
				f = new File(System.getProperty("user.dir")+"/DataFile/Demo4.xlsx");
				try {
					fis = new FileInputStream(f);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      try {
		    	  wb = new XSSFWorkbook(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      //create a new work sheet
		       sheet = wb.createSheet("TestResult");
		      testresultdata = new LinkedHashMap<String, Object[]>();
		      //add test result excel file column header
		      //write the header in the first row
		      testresultdata.put("1", new Object[] {"Test Step Id", "Action", "Expected Result","Actual Result"});

	}
	 
	 public static void setupAfterSuite() {
		    //write excel file and file name is TestResult.xls 
		    Set<String> keyset = testresultdata.keySet();
		    int rownum = 0;
		    for (String key : keyset) {
		        XSSFRow row = sheet.createRow(rownum++);
		        Object [] objArr = testresultdata.get(key);
		        int cellnum = 0;
		        for (Object obj : objArr) {
		            XSSFCell cell = row.createCell(cellnum++);
		            if(obj instanceof Date) 
		                cell.setCellValue((Date)obj);
		            else if(obj instanceof Boolean)
		                cell.setCellValue((Boolean)obj);
		            else if(obj instanceof String)
		                cell.setCellValue((String)obj);
		            else if(obj instanceof Double)
		                cell.setCellValue((Double)obj);
		        }
		    }
		    try {
		        FileOutputStream out =new FileOutputStream(f);
		        wb.write(out);
		        out.close();
		        System.out.println("Excel written successfully..");
		         
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    //close the browser
		 }
	
}
