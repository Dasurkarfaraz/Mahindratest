package utils;
import java.io.IOException;
import java.util.Hashtable;


public class DataUtil {

	public static Object[][] getData(ExcelReader xls, String testCaseName) throws Exception{
		String sheetName=ConstantsConfig.TESTDATA_SHEET;
		// reads data for only testCaseName
//		test.log(LogStatus.INFO, "Opening browser");
		System.out.println("Checking for Test Case :" + testCaseName);
		int testStartRowNum=1;
//		System.out.println("celdata:" + xls.getCellData(sheetName, 0, testStartRowNum)); 
		int sheetRowCount=xls.getRowCount(sheetName);
		System.out.println("row count:" + xls.getRowCount(sheetName));
		while(!xls.getCellData(sheetName, 0, testStartRowNum).trim().equals(testCaseName.trim()) && xls.getRowCount(sheetName) >= testStartRowNum){
			//System.out.println("Test Case in Sheet : "+xls.getCellData(sheetName, 0, testStartRowNum));
			
			testStartRowNum++;
		}
		if(testStartRowNum>sheetRowCount)
		{
			System.out.println("NO TestCase Found with name :  "+testCaseName+" in Sheet :" +sheetName);
			return new Object[0][0];
		}
//		System.out.println("Test starts from row - "+ testStartRowNum);
		int colStartRowNum=testStartRowNum+1;
		int dataStartRowNum=testStartRowNum+2;
		
		// calculate rows of data
		int rows=0;
		while(!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals("")){
			rows++;
		}
//		System.out.println("Total rows are  - "+rows );
		
		//calculate total cols
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals("")){
			cols++;
		}
		System.out.println("Total cols are  - "+cols );
		Object[][] data = new Object[rows][1];
		//read the data
		int dataRow=0;
		Hashtable<String,String> table=null;
		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++){
			
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++){
				String key=xls.getCellData(sheetName,cNum,colStartRowNum);
				String value= xls.getCellData(sheetName, cNum, rNum);
				table.put(key, value);
//				System.out.println("in for");
				// 0,0 0,1 0,2
				//1,0 1,1
			}
//			System.out.println("in out for");
			data[dataRow][0] =table;
			dataRow++;
		}
//		System.out.println("in outside for");
		return data;
	}
	public static Object[][] getIncidentData(ExcelReader xls, String testCaseName) throws Exception{
		String sheetName=ConstantsConfig.TESTDATA_SHEETFull;
		// reads data for only testCaseName
//		test.log(LogStatus.INFO, "Opening browser");
		System.out.println("Checking for Test Case :" + testCaseName);
		int testStartRowNum=1;
//		System.out.println("celdata:" + xls.getCellData(sheetName, 0, testStartRowNum)); 
		int sheetRowCount=xls.getRowCount(sheetName);
		System.out.println("row count:" + xls.getRowCount(sheetName));
		while(!xls.getCellData(sheetName, 0, testStartRowNum).trim().equals(testCaseName.trim()) && xls.getRowCount(sheetName) >= testStartRowNum){
//			System.out.println("Test Case in Sheet : "+xls.getCellData(sheetName, 0, testStartRowNum));
			
			testStartRowNum++;
		}
		if(testStartRowNum>sheetRowCount)
		{
			System.out.println("NO TestCase Found with name :  "+testCaseName+" in Sheet :" +sheetName);
			return new Object[0][0];
		}
//		System.out.println("Test starts from row - "+ testStartRowNum);
		int colStartRowNum=testStartRowNum+1;
		int dataStartRowNum=testStartRowNum+2;
		
		// calculate rows of data
		int rows=0;
		while(!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals("")){
			rows++;
		}
//		System.out.println("Total rows are  - "+rows );
		
		//calculate total cols
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals("")){
			cols++;
		}
		System.out.println("Total cols are  - "+cols );
		Object[][] data = new Object[rows][1];
		//read the data
		int dataRow=0;
		Hashtable<String,String> table=null;
		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++){
			
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++){
				String key=xls.getCellData(sheetName,cNum,colStartRowNum);
				String value= xls.getCellData(sheetName, cNum, rNum);
				table.put(key, value);
//				System.out.println("in for");
				// 0,0 0,1 0,2
				//1,0 1,1
			}
//			System.out.println("in out for");
			data[dataRow][0] =table;
			dataRow++;
		}
//		System.out.println("in outside for");
		return data;
	}
	public static boolean isTestExecutable(ExcelReader xls, String testCaseName) throws Exception{
		int rows = xls.getRowCount(ConstantsConfig.TESTCASES_SHEET);
		for(int rNum=2;rNum<=rows;rNum++){
			String tcid = xls.getCellData(ConstantsConfig.TESTCASES_SHEET, "TCID", rNum);
			if(tcid.equals(testCaseName)){
				String runmode = xls.getCellData(ConstantsConfig.TESTCASES_SHEET, "Runmode", rNum);
				if(runmode.equals("Yes"))
					return true;
				else
					return false;

			}
			
		}			
		//	SendMail sendmail=new SendMail();
			//sendmail.emailsend();
		//	sendMail(FBConstants.fromEmail,FBConstants.fromEmailPword,FBConstants.mailserver,FBConstants.port,true,"false",FBConstants.toEmail,FBConstants.ccEmail,FBConstants.bccEmail,extentFilePath,fileName,subject_Extent,msgText1);

		return false;
	}
	
	
}
