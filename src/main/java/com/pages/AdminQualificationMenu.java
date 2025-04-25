package com.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class AdminQualificationMenu {
	
	private Keywords kw;
	public AdminQualificationMenu(Keywords kw) {
		PageFactory.initElements(Keywords.driver, this);
		this.kw = kw;
	}
	
	private static final Logger LOG = Logger.getLogger(AdminQualificationMenu.class);

	@FindBy(css="nav>ul>li:nth-of-type(4)")
	WebElement qualification ;
	
	public void clickOnQualificationMenu() {
		kw.waitForElementToBeClickable(qualification);
		qualification.click();
		LOG.info("Clicked on Admin > Qualification Menu");
	}
	
	@FindBy(css="nav>ul>li>ul>li:nth-of-type(1)")
	WebElement skills ;
	
	public void clickOnSkills() {
		kw.waitForElementToBeVisible(skills);
		skills.click();
		LOG.info("Clicked on Qualification> Skill menu");
	}
	
	@FindBy(css="button.oxd-button.oxd-button--medium.oxd-button--secondary")
	WebElement addSkillsButton ;
	
	public void clickOnAddSkillsButton() {
		kw.waitForElementToBeVisible(addSkillsButton);
		addSkillsButton.click();
		LOG.info("clicked on Add skills button");
	}
	
	@FindBy(css="form > div:nth-child(1) > div > div:nth-child(2) > input")
	WebElement skillNameTextbox ;
	
	public void enterSkillName(String name) {
		kw.waitForElementToBeVisible(addSkillsButton);
		skillNameTextbox.sendKeys(name);
		LOG.info("entered the skill name to add");
	}
	
	@FindBy(css="button[type=\"submit\"]")
	WebElement saveSkillButton ;
	
	public void clickOnSaveSkillButton() {
		kw.waitForElementToBeVisible(saveSkillButton);
		saveSkillButton.click();
		LOG.info("clicked on Save skill button");
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
			LOG.info("Success Toast Message Displayed");
			return isDisplayed;
		} catch (TimeoutException e) {
			LOG.warn("Toast message not found within timeout.");
	        
		}catch (NoSuchElementException e) {
	        LOG.warn("Toast element not found in DOM.");
	    }
		return false;
	}
	
	@FindBy(css=".oxd-table-row")
	List<WebElement>  rows;
	
	@FindBy(css="button.oxd-button.oxd-button--medium.oxd-button--label-danger.orangehrm-button-margin")
	WebElement confirmDeleteButton ;

	public void deleteEnteredSkill(String name) {
		kw.waitForAllElementAreVisible(rows);
		for(WebElement row: rows) {
			
			List <WebElement> cells = row.findElements(By.cssSelector(".oxd-table-cell"));
			
			if(cells.size()>1 && cells.get(1).getText().equalsIgnoreCase(name)) {
				row.findElement(By.cssSelector(".oxd-icon.bi-trash")).click();
				break;
			}
		}
		kw.waitForElementToBeClickable(confirmDeleteButton);
		confirmDeleteButton.click();
		LOG.info("Successfully deleted the skill "+name);
	}
	
}
