package com.test.mahindra;

import org.testng.annotations.Test;

import com.test.pages.loginpages;

import Base.BaseTest;

public class testCase extends BaseTest{
	
	 
      
	
	@Test
	public void testValidLogin() {
		// complete testing For Login module 
       
        loginpages loginModule = new loginpages();
        loginModule.test01valid(validUsername, validPassword)
        .test02invalid(invalidUsername, invalidPassword)
        .test03RetainedFieldsAfterFailedLogin(invalidUsername, invalidPassword)
        .test04LoginButtonState()
        .test05testLoginButtonEnabled(validUsername, validPassword)
        .test06ForgotPasswordLink(emailid)
        .test01valid(validUsername, validPassword)
        .passwordstrength();
        
       
	}
	
	
	
	

}
