
package utils;

import java.util.Hashtable;

import org.openqa.selenium.By;

public class ConstantsConfig {
	
	public static final String URL ="https://www.google.com/";
	
	//paths
	public static final String REPORTS_PATH = ".//Report//";
	//public static final String REPORTS_PATH = System.getProperty("user.dir")+"\\Report\\Reports";
	public static final String REPORTS_SEND = System.getProperty("user.dir")+"//Report";
//3DI	
	public static final String DATA_XLS_PATH = System.getProperty("user.dir")+"//data//Data_3DI.xlsx";
//SEC	
	//public static final String DATA_XLS_PATH = System.getProperty("user.dir")+"\\data\\Data_SEC_Dev.xlsx";
	
	public static final String Picture = System.getProperty("user.dir")+"//data//Capture.PNG";
	
	public static final String TESTDATA_SHEET = "TestData";
	public static final String TESTDATA_SHEETFull = "Incident";
	public static final Object RUNMODE_COL = "Runmode";
	public static final String TESTCASES_SHEET = "TestCases";
	public static final String TESTDATA_SCREENING_SCENARIOS_SHEET = "TestData_ScreeningScenarios";
	
			//Company Profile Page
	public static final String CPP_CompanyName="SoftTech";
	public static final String CPP_WhoWeAre="Manager who is responsible for planning, directing and overseeing the operations and fiscal health of a business unit, division, department,";
	public static final String CPP_NoOfEmp="5,001 -10,000";
	public static final String CPP_CompanyWebsite="www.softtech.com";
			//Talent Details Page
	//My Info
	public static final String TDP_FirstName="John";
	public static final String TDP_LastName="Roy";
	public static final String TDP_PhoneNo="234567891";
	public static final String TDP_IndustryExperience_Type="Computer Software";
	public static final String TDP_IndustryExperience_Year="3";
	public static final String TDP_IndustryExperience_Month="2";
	
		public static final String fromNoReplyEmail="no-reply@3disystems.com";
		public static final String TestExecutionBy="Automation Team";
		public static final String fromEmail="klassenm@3disystems.com";
		public static final String fromEmailPword="*3Diadmin#";
				
		public static final String[] toEmail={"prabhakar.panda@3disystems.com"};
		public static final String[] ccEmail={"prabhakar.panda@3disystems.com"};
		public static final String[] TestEmail={"swat_test@3disystems.com"};
		public static final String[] bccEmail={"prabhakar.panda@3disystems.com"};
		
		public static final String mailserver="smtp.emailsrvr.com";
		public static final String port="587";
		
	
public static Hashtable<String,String> table;

public static String[] listOfUserPW={"Anne Parker","Donald Field","Jay Ingram","John Spitz","Martin James","Susan Block"};

public static boolean GRID_RUN=false;
	
	public static Hashtable<String,String> getEnvDetails(){
		if(table==null)
		{
			table = new Hashtable<String,String>();		
			
				table.put("url", URL);				
			
				return table;
		 	
		}
			return table;


	}

	
	




	




	



	

}
