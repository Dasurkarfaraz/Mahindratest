package com.test.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.python.antlr.PythonParser.return_stmt_return;
import org.testng.Assert;

import Base.BasePage;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.VerificationUtils;

public class loginpages extends BasePage {
	
	
	public loginpages ()
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@FindBy(id = "username")
	private WebElement username;
	@FindBy(id = "password")
	private WebElement password;
	@FindBy(id = "login")
	private WebElement login;
	@FindBy(id = "dashboard")
	private WebElement dashbord;
	@FindBy(id = "errorMessage")
	private WebElement errormsg;
	
	@FindBy(id="forgetlink")
	private WebElement forgetlink;
	
	@FindBy(id = "recoveryPageHeader")
	private WebElement recoveryPageHeader;
	@FindBy(id = "emailField")
	private WebElement emailField;
	@FindBy(id = "recoverPasswordButton")
	private WebElement recoverPasswordButton;
	
	@FindBy(id = "successMessage")
	private WebElement successMessage;
	
	@FindBy(id = "loginPageHeader")
	private WebElement loginPageHeader;
	@FindBy(id = "passwordField")
	    private WebElement passwordField;
	    @FindBy(id = "validationMessage")
	    private WebElement validationMessage;
	public loginpages test01valid(String usernamedata, String validPassword) {
        sendKeys(username, usernamedata);
        sendKeys(password, validPassword);
		click(login);
		VerificationUtils.validate(true,dashbord.isDisplayed(),"Valid Data Displayed" );
		//login in applicaiton 
		return new loginpages();
		
		}
	public loginpages test02invalid(String usernamedata, String validPassword) {
        sendKeys(username, usernamedata);
        sendKeys(password, validPassword);
		click(login);
		VerificationUtils.validate(true,errormsg.isDisplayed(),"Error message displayed for invalid login" );
		Assert.assertTrue(errormsg.isDisplayed(), "Error message displayed for invalid login");
		return new loginpages();
		}
	 
	  public loginpages test03RetainedFieldsAfterFailedLogin(String invalidUsername, String invalidPassword) {
	        // Test case 3: Retain fields after failed login
	    

	        sendKeys(username, invalidPassword);
	        sendKeys(password, invalidPassword);
	       
	        click(login);
	        
	        Assert.assertEquals(username.getText(), invalidUsername, "Username field retained");
	        Assert.assertEquals(password.getText(), invalidPassword, "Password field retained");
	        return new loginpages();
	    }
	
	    public loginpages test04LoginButtonState() {
	    	 // Test case 4: Login button state when fields are empty

	    
	        Assert.assertFalse(login.isEnabled(), "Login button is disabled when fields are empty");
	        return new loginpages();
	    }
	    public loginpages test05testLoginButtonEnabled(String validUsername, String validPassword) {
	        // Test case 5: Login button enabled when fields are filled with valid input
	     
	        sendKeys(username, validUsername);
	        sendKeys(password, validPassword);
	       
	      

	        // Assertion for enabled login button
	        Assert.assertTrue(login.isEnabled(), "Login button is enabled with valid input");
	        return new loginpages();
	    }
	    public loginpages test06ForgotPasswordLink(String registeredEmail) {
	    	  // Test case 6: Clicking 'Forgot Password' link redirects to password recovery page	
	    	 click(forgetlink);

	         // Assertion for redirection
	    	 VerificationUtils.validate(true,recoveryPageHeader.isDisplayed(),"Redirected to password recovery page" );
	       /////
	    	 
	    	 sendKeys(emailField, registeredEmail);
	    	 
	    	 
	    	 click(recoverPasswordButton);
	    	 
	         // Assertion 
	    	 VerificationUtils.validate(true,successMessage.isDisplayed(),"Success message displayed for password recovery" );

	    	 VerificationUtils.validate(true,loginPageHeader.isDisplayed(),"Redirected to login page after password reset" );
	
	    	 
	    	 
	    	 
	         return new loginpages();
	    }
	    
	 
	    public loginpages  passwordstrength() {
	    	
	    	String weakPassword = "1234";
	       
	        sendKeys(passwordField, weakPassword);
	     
	        
	        VerificationUtils.validate(true,validationMessage.isDisplayed(),"Weak password validation message displayed" );
	        
	        passwordField.clear();

	       
	        String strongPassword = "StrongP@ssw0rd";
	      
	        sendKeys(passwordField, strongPassword);
	      
	      
	        VerificationUtils.validate(true,validationMessage.isDisplayed(),"Weak password validation message displayed" );
	    	 return new loginpages();
	    }
}
