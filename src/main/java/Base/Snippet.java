package Base;
import java.util.Calendar;

import org.openqa.selenium.WebDriverException;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
public class Snippet   {
//	@Test
//	public void run() {
//		 int month = Calendar.getInstance().get(Calendar.MONTH);
//		 System.out.println(month);
//	}
	public static void main(String[] args) {
		  
		LocalDate currentDate = LocalDate.now();

	       
	        LocalDate twoMonthsAgo = currentDate.minusMonths(2);

	       String CurrentMonth = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	        String twomonth = twoMonthsAgo.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	        System.out.println("//XCUIElementTypePickerWheel[@value='"+CurrentMonth+"']");
	        
	}
}

