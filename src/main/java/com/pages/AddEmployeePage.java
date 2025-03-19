package com.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class AddEmployeePage {
	
	public AddEmployeePage() {
		PageFactory.initElements(Keywords.driver, this);
	}

	Keywords kw = new Keywords();
	private static final Logger LOG = Logger.getLogger(AddEmployeePage.class);
	
	@FindBy(css="header > div.oxd-topbar-body > nav > ul > li:nth-child(3) > a")
	WebElement addNewEmployeeButton ;
	
	/**click on tha add button on employee list page
	 * 
	 */
	public void clickOnAddNewEmployeeButton() {
		kw.waitForElementToBeClickable(addNewEmployeeButton);
		addNewEmployeeButton.click();
		LOG.info("Successfully clicked on add new employee button");
	}
	
	@FindBy(css="Button.oxd-icon-button.employee-image-action" )
	WebElement profilePicAddButton ;
	
	@FindBy(css="input.oxd-file-input")
	WebElement filePath ;
	
	public void uploadFileUsingRobot(String filePath) throws AWTException, InterruptedException {
	    Robot robot = new Robot();
	    
	    StringSelection selection = new StringSelection(filePath);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

	    kw.waitForClipBoardText(filePath);
	    
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    
	    kw.waitForClipBoardText("");

	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    kw.normalWait(300);
	}
	
	
	/**
	 * select the profile picture from system.
	 * @throws AWTException 
	 * @throws InterruptedException 
	 */
	public void selectProfilePic() throws AWTException, InterruptedException {
		kw.waitForElementToBeClickable(profilePicAddButton);
		profilePicAddButton.click();
		uploadFileUsingRobot("C:\\Users\\piyus\\OneDrive\\Pictures\\profile.jpg");
	    LOG.info("Profile pic selected");
	}
	
	@FindBy(css="div > div > div.--name-grouped-field > div:nth-child(1) > div:nth-child(2) > input")
	WebElement firstName ;
	
	@FindBy(css="div > div > div.--name-grouped-field > div:nth-child(2) > div:nth-child(2) > input")
	WebElement middleName;
	
	@FindBy(css="div > div > div.--name-grouped-field > div:nth-child(3) > div:nth-child(2) > input")
	WebElement lastName ;
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement  SaveEmployeeButton;
	
	@FindBy(css="div:nth-child(1) > div.oxd-grid-2.orangehrm-full-width-grid > div > div > div:nth-child(2) > input")
	WebElement empID ;
	
	public void EnterUserDetails(String fname,String mname, String lname) throws InterruptedException, TimeoutException {
		kw.waitForElementToBeVisible(firstName);
		firstName.sendKeys(fname);
		middleName.sendKeys(mname);
		lastName.sendKeys(lname);
		
		LOG.info("Successfully entered the user with details "+ fname + " " + mname +" "+ lname);
	}
	
	@FindBy(css="div:nth-child(1) > div.oxd-grid-2.orangehrm-full-width-grid > div > div > span")
	WebElement empIdAlreadyExist ;
	
	@FindBy(css="div:nth-child(1) > div.oxd-grid-2.orangehrm-full-width-grid > div > div > span")
	List <WebElement> empIdAlreadyExistmultiple ;
	
	
	/**
	 * @param empid 
	 * clear the emp id field and enter new value if already exist then add 1 to value 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 */
	public void enterEmployeeID() throws InterruptedException, TimeoutException {
		
		kw.waitForElementToBeVisible(empID);
		String empid = empID.getText();
		int id = Integer.parseInt(empid);
		empID.sendKeys(Keys.CONTROL+ "a");
		empID.sendKeys(Keys.DELETE);
		kw.normalWait(500);
		LOG.info("loop start "+ String.format("%04d", id));
		empID.sendKeys(String.format("%04d", id));
		
		
		for(int attempt = 1; attempt <=10; attempt++) {
			
			try {
			    kw.waitForElementToBeVisible(empIdAlreadyExist);
			    LOG.info("Error message displayed: " + empIdAlreadyExist.getText());
			} catch (org.openqa.selenium.TimeoutException e) {
				LOG.info("Error message not found, ID is unique.");
			}
			
			if (!empIdAlreadyExistmultiple.isEmpty()) {  // Check if error message is visible
	            id++;
	            empid = String.format("%04d", id); // Format as 4-digit number
	            
	            empID.sendKeys(Keys.CONTROL + "a");
	            empID.sendKeys(Keys.DELETE);
	            empID.sendKeys(empid);
	            
	            LOG.info("Trying new ID: " + empid);
	            Thread.sleep(1000); // OR kw.normalWait(1000);
	        } else {
	            break; // Found a unique ID
	        }
        }

		LOG.info("Final Unique Employee ID: " + empid);
 
		//LOG.info("Updated the empID value to "+String.format("%04d", id));
		
	}
	
	public void clickOnSaveButton() {
		kw.waitForElementToBeClickable(SaveEmployeeButton);
		SaveEmployeeButton.click();
		LOG.info("Successfully clicked on the Save button");
	}
	
	
	@FindBy(css=".oxd-toast.oxd-toast--success.oxd-toast-container--toast")
	WebElement saveSuccessfullToast ;
	
	/**
	 * @return save successful toast message
	 * @throws TimeoutException 
	 */
	public boolean SaveToastMessageText() throws TimeoutException {
		try {
			kw.waitForElementToBeVisibleShort(saveSuccessfullToast,5);
			kw.scrollToElement(saveSuccessfullToast);
			boolean isDisplayed = saveSuccessfullToast.isDisplayed();
			LOG.info("Successfully added the employee");
			return isDisplayed;
		} catch (TimeoutException e) {
			LOG.warn("Toast message not found within timeout.");
	        
		}catch (NoSuchElementException e) {
	        LOG.warn("Toast element not found in DOM.");
	    }
		return false;
		
		
	}
	
	@FindBy(css="form > div.orangehrm-employee-container > div.orangehrm-employee-form > div.oxd-form-row.user-form-header > div > label")
	WebElement loginDetailsLabel;
	
	@FindBy(css="input[type=checkbox]")
	WebElement  loginDetailsInput;
	
	public void clickOnCreateLoginDetails() {
		kw.waitForElementToBeVisible(loginDetailsLabel);
		loginDetailsLabel.click();
		if(!loginDetailsInput.isSelected()) {
			loginDetailsLabel.click();
			LOG.info("Successfully clicked on the Create login button");
		}else {
			LOG.info("The Create login details button already turned on");
		}
	}
	
	@FindBy(css="div.orangehrm-employee-form > div:nth-child(4) > div > div:nth-child(1) > div > div:nth-child(2) > input")
	WebElement enterUsername ;
	
	@FindBy(css="div.oxd-grid-item.oxd-grid-item--gutters.user-password-cell > div > div:nth-child(2) > input")
	WebElement enterPassword ;
	
	@FindBy(css="div.oxd-form-row.user-password-row > div > div:nth-child(2) > div > div:nth-child(2) > input")
	WebElement confirmPassword ;
	
	@FindBy(css="div > div.--status-grouped-field > div:nth-child(1) > div:nth-child(2) > div > label")
	WebElement statusLoginEnabled ;
	
	@FindBy(css="div > div.--status-grouped-field > div:nth-child(2) > div:nth-child(2) > div > label")
	WebElement statusLoginDisabled;
	
	public void enterLoginDetails(String usernm, String pass, boolean status) {
		
		kw.waitForElementToBeClickable(enterUsername);
		enterUsername.sendKeys(usernm);
		enterPassword.sendKeys(pass);
		confirmPassword.sendKeys(pass);
		if(status==true) {
			statusLoginEnabled.click();
		}else {
			statusLoginDisabled.click();
		}
		
	}
	
	
	
	
	
}
