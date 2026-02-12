#orangeHRM

Project Structure:

OrangeHRM
	src/main/java
		base
			TestBase.java
		listeners
			TestListener.java
		main
			Keywords.java
		pages
			AddEmployeePage.java
			AdminJobTitles.java
			AdminOrganizationMenu.java
			AdminQualificationMenu.java
			DashboardMenu.java
			EmployeeListMeny.java
			LeaveApplyMenu.java
			LeaveEntitlementMenu.java
			LeaveMyLeaveMenu.java
			LoginPage.java
			PerformanceMenu.java
			PimCustomField.java
			PimMenu.java
			RecruitmentMenu.java
			ReportingMethod.java
			TerminationReasons.java
			TimeMenu.java
			UserDropdownMenu.java
			UserProfile.java
		util
			PropertiesUtil.java
	src/main/resources
		drivers
			chromedriver.exe
			geckodriver.exe
			msedgedriver.exe
		peropertiesFiles
			App.properties
		log4j.properties
	src/test/java
		hooks
			Hooks.java
		runner
			CucumberTests.java
		stepdefinitions
			StepBase.java
			StepDefAdmin.java
			StepDefDashboard.java
			StepDefEmployee.java
			StepDefLeave.java
			StepDefLoginPage.java
			StepDefPerformance.java
			StepDefPimMenu.java
			StepDefRecruitment.java
			StepDefTimeEntry.java
		testcases
			TestngAdminTests.java
			TestngDashboardTests.java
			TestngEmployeeTests.java
			TestngLeaveTests.java
			TestngLoginPageTests.java
			TestngPerformanceTests.java
			TestngPimMenuTests.java
			TestngRecruitmentTests.java
			TestngTimeEntryTests.java
	src/test/resources
		features
			cucumberAdminTests.feature
			cucumberDashboardTests.feature
			cucumberEmployeeTests.feature
			cucumberLeaveTests.feature
			cucumberLoginPageTests.feature
			cucumberPerformanceTests.feature
			cucumberPimMenuTests.feature
			cucumberRecruitmentTests.feature
			cucumberTimeEntryTests.feature
	pom.xml
	testng-regression.xml
	testng-smoke.xml
	crossBrowser.xml
	
=====================================================================================

Project follows the hybrid framework keywords driven + Page object model framework.

	TestBase - methods

		* cleanAllureResults()
			- delete the old allure reports before running build to avoid showing accumulative testcase count. 
			- Runs before every suite using TestNG annotation @BeforeSuite
			
		* setUp(String browserName, Method methods)
			- throws IOException
			- @BeforeMethod(alwaysRun = true) annotation - runs before every method always.
			- @Parameters("browser") - take browser name from xml file.
			- The Method method parameter tells you which test method just finished. and logic checks which method triggered it. 
			- TestNG uses xml files and annotations while cucumber uses feature files and testRunner.
			- check if parameter given in xml file if yes use that browser otherwise use properties file browser. 
			- initializes the keyword instance and run launchBrowser and launchURL methods. 
			
		* tearDown(Method method)
			- @AfterMethod(alwaysRun = true) annotation - runs after every method always.
			- The Method method parameter tells you which test method just finished. and logic checks which method triggered it.
			- check if driver is not null and then run driver.reset method which  remove driver, wait instance. 
			
		* Lazy factory design pattern - - 
			- Each page class has protected object returning with keyword (key) as argument in constructor. 
			- A lazy factory delays page object creation until it’s first accessed, improving performance.

=====================================================================================

	TestListener - methods

		* onTestFailure
			- @Override the ITestListener Interface method "onTestFailure"
			- get the existing instance of keywords.
			- get testname of failed test
			- capture the screenshot of failed even using captureScreenshot method which uses Ashot.
			
=====================================================================================
			
	PropertiesUtil Methods
	
		* getProperty(String key)
		    - Creates a FileInputStream for the application properties file.
			- Handles FileNotFoundException while locating the properties file.
			- Initializes a Properties object.
			- Loads key–value pairs from the properties file into the Properties object.
			- Handles IOException during properties loading.
			- Retrieves the value associated with the given key.
			- Returns the property value as a String.

=====================================================================================

	Keywords - methoeds

		* private static final Threadlocal
			- driver, wait and instance - for parallel execution.
			- each thread has its own instance of driver and wait. 
			- ThreadLocal.withInitial(Keywords::new) - checks if thread already have value if not it runs Keywords::new which is () -> new Keywords(). 
			- If again its called it simply return same instance does not create new one. 
			
		* getInstance()
			- Returns the thread-local instance of Keywords.
			- Each thread gets its own Keywords object, ensuring thread safety during parallel execution.
		
		* getDriver()
			- Fetches the RemoteWebDriver instance associated with the current thread.
			- Simple getter method for driver.
			- This prevents driver conflicts when tests run in parallel.
		
		* reset()
			- Cleans up all ThreadLocal variables (driver, wait, fluentWait, instance).
			- Used after test execution to avoid memory leaks and stale references.
		
		* getWait()
			- Returns the WebDriverWait instance tied to the current thread.
			- Simple getter method for waits.
			- Ensures explicit waits are isolated per test thread.
		
		* getFluentWait()
			- Provides a thread-safe FluentWait instance with polling and exception handling.
			- Simple getter method for fluent waits.
			- Used for flexible, retry-based waiting strategies.
		
		* launchURL(String url)
			- Navigates the browser to the specified URL using the current thread’s driver.
			- Logs the navigation step for traceability.
		
		* launchBrowser(String browserName)
			- Initializes the requested browser with predefined options and timeouts.
			- Uses WebDriverManager for version compatibility.
			- Setup options window size, start-maximized etc.
			- Assigns driver, explicit wait, and fluent wait to ThreadLocal variables for safe parallel execution.
		
		* quitBrowser()
			- Gracefully closes the browser instance for the current thread.
			- Ensures all thread-local references are removed even if an exception occurs.
		
		* waitForElementToBeVisible(WebElement element)
			- Waits until the given element becomes visible on the page. (uses webelement)
			- Uses FluentWait to handle dynamic loading and retry polling.
		
		* waitForElementToBeVisible(By locator)
			- overloaded for By locator argument. (Method overloading)
			- Waits until an element located by the given locator is visible.
			- Uses a short explicit wait suitable for simple visibility checks.
		
		* waitForElementToBeClickable(WebElement element)
			- Waits until the element is both visible and clickable.
			- Prevents ElementNotInteractableException during click actions.
		
		* waitForElementToBeClickable(By by)
			- Same as above but works with a locator instead of a WebElement. (Method overloading)
			- Useful when elements are dynamically recreated.

		* waitInvisibilityOfElement(WebElement element)
			- Waits until the specified element disappears from the UI.
			- Commonly used for loaders, spinners, or toast messages.

		* waitForAllElementAreVisible(List<WebElement> li)
			- Waits until all elements in the list are visible.
			- Ensures complete rendering before interacting with collections.

		* clickOn(WebElement element)
			- Performs a direct click action on the given element.
			- Assumes the element is already in an interactable state.

		* enterText(WebElement element, String text)
			- Inputs text into a text field using sendKeys.
			- Does not clear existing content unless explicitly done earlier.

		* normalWait(long milisec)
			- Introduces a hard wait using Thread.sleep.
			- Used only when dynamic waits are not applicable (generally discouraged).

		* presenceOfAllElement(String s)
			- Waits until all elements matching the CSS selector are present in the DOM.
			- Does not guarantee visibility, only presence.

		* visibilityOfElementLocated(WebElement e)
			- Waits until the given element is visible on the page.
			- Wrapper over ExpectedConditions.visibilityOf.

		* waitForElementToBeVisibleShort(WebElement element, int timeoutSeconds)
			- Checks element visibility with a custom short timeout.
			- Returns true if visible, otherwise false without failing the test.

		* scrollToElement(WebElement element)
			- Scrolls the page smoothly until the element is centered in the viewport.
			- Useful for lazy-loaded or off-screen elements.

		* scrollToElement(List<WebElement> elist)
			- Scrolls to the first element in the list if available.
			- Prevents IndexOutOfBoundsException for empty lists.

		* JSExecutor(String script, WebElement element)
			- Executes a JavaScript command on the provided element.
			- Used for advanced interactions not supported by standard WebDriver APIs.

		* isElementListPresent(List<WebElement> elements)
			- Checks whether a list is non-empty and the first element is displayed.
			- Safely handles null lists and runtime exceptions.

		* waitForClipBoardText(String text)
			- Continuously polls the system clipboard until expected text appears.
			- Useful for validating copy-to-clipboard functionality.
			- Uses Thread.onSpinWait() - instead of Thread.sleep()
			- The primary purpose of Thread.onSpinWait() is to allow the Java Virtual Machine (JVM) 
			- and the processor to optimize the execution of spin loops, thereby improving performance and reducing power consumption in specific, low-latency scenarios. 

		* captureScreenShot(String testname)
			- Captures a full-page screenshot using AShot with timestamped naming.
			- Creates date-wise folders and logs the saved screenshot path.
			- Using DateTimeFormatter created dataFormatter and timeFormatter
			- created dateFolder using dateFormatter and timeStamp using timeFormatter
			- setup file path using Path path and created the directory (folder for the day)
			- Took screenshot using Ashot using viewportPasting method. 
			- Using bufferedImage get the image and saved the using ImageIO.write to output file. 

		* waitForTextToBe(WebElement element, String expectedText)
			- Waits until the specified text appears inside the element.
			- Helps validate dynamic text updates.

		* waitForPresenceOfElements(By element)
			- Waits until all elements matching the locator are present in the DOM.
			- Does not ensure visibility.

		* isElementPresent(WebElement element)
			- Safely checks whether an element exists and is displayed.
			- Handles stale and missing element exceptions gracefully.

		* waitForUrlToBeChanged(String oldUrl)
			- Waits until the current URL changes from the given value.
			- Useful after navigation or form submission.

		* clearTextBox(WebElement element)
			- Clears a text field using keyboard shortcuts instead of clear().
			- Using the click click strategy.
			- More reliable for certain custom input components.

		* getCurrentURL()
			- Returns the current browser URL.
			- Useful for assertions and navigation validation.

		* waitForElementToBeInvisible(WebElement element)
			- Waits until the given element becomes invisible.
			- Commonly used for loaders or blocking overlays.

		* waitForElementToBeInvisible(By by)
			- Same as above but accepts a locator.
			- Useful when element references become stale.

		* isDisplayed(WebElement element)
			- Checks if an element is displayed without throwing exceptions.
			- Returns false safely in failure scenarios.

		* scrollToTop()
			- Scrolls the page back to the top using JavaScript.
			- Helpful after deep page interactions.

=====================================================================================

	Pages Methods - 
	
		* Constructor for every class 
			- Declares a private Keywords reference for shared utility access.
			- Accepts a Keywords object through the constructor.
			- Assigns the passed Keywords instance to the class-level variable.
			- Initializes all Page Object Model elements using PageFactory.
			- Binds WebElements to the active WebDriver instance.
			
	AddEmployeePage Methods - 
		
		* clickOnAddNewEmployeeButton()
			- Waits until the addNewEmployeeButton is clickable using explicit wait.
			- Scrolls the button into the viewport using JavaScript.
			- Performs a Selenium .click() action.
			- Logs success message for execution traceability.
		
		* uploadFileUsingRobot(String absolutePath)
			- Creates a Robot instance to simulate keyboard actions at OS level.
			- Adds a short wait to ensure file dialog is fully loaded.
			- Copies the absolute file path into system clipboard using StringSelection.
			- Pastes the path using CTRL + V.
			- Presses ENTER to confirm file selection.
			- Adds waits between steps to avoid race conditions with OS UI.
			- Why Robot class - Selenium cannot interact with native OS dialogs and Robot works outside browser DOM
		
		* selectProfilePic()
			- Waits until profile picture button is clickable.
			- Waits until profile picture container is visible.
			- Scrolls to the profile picture section.
			- Clicks on profile image area to trigger file chooser.
			- Calls uploadFileUsingRobot() with absolute image path.
			- Logs profile picture upload success.
		
		* EnterUserDetails(String fname, String mname, String lname)
			- Waits for First Name field visibility.
			- Scrolls First Name field into view.
			- Sends input to First Name, Middle Name, and Last Name fields.
			- Logs entered user details for debugging and audit purpose.
			
		* enterEmployeeID()
			- Waits until Employee ID field becomes visible.
			- Scrolls Employee ID field into view.
			- Uses JavaScript Executor to read current value of Employee ID.
			- Converts ID from String to Integer for manipulation.
			- Clears existing value using CTRL + A + DELETE.
			- Enters formatted 4-digit Employee ID.
			- Starts retry loop (max 10 attempts):
			- Waits briefly for validation message.
			- Checks if "Employee ID already exists" message is visible.
			- If error appears → increments ID and retries.
			- If error not found → breaks loop immediately.
			- Logs final unique Employee ID.
			
		* clickOnSaveButton()
			- Waits until Save button is clickable.
			- Scrolls Save button into view.
			- Clicks Save button.
			- Logs save action success.
		
		* SaveToastMessageText()
			- Waits briefly for success toast message.
			- Scrolls toast into view.
			- Checks if toast is displayed.
			- Returns true if visible.
			- Handles TimeoutException if toast does not appear.
			- Handles NoSuchElementException if toast is missing from DOM.
			- Returns false in failure scenarios.
		
		* clickOnCreateLoginDetails()
			- Waits for Login Details label to be visible.
			- Scrolls label into view.
			- Clicks label to toggle checkbox.
			- Checks checkbox state:
			- If not selected → clicks again to enable.
			- If already enabled → logs status.
			
		* enterLoginDetails(String usernm, String pass, boolean status)
			- Waits for Username field to be clickable.
			- Scrolls Username field into view.
			- Enters username, password, and confirm password.
			- Checks status flag:
			- true → selects Enabled option.
			- false → selects Disabled option.
	
	AdminJobTitles Methods - 
	
		* clickOnAdminMenu()
			- Waits until the adminMenu element is visible on the page.
			- Scrolls the Admin menu into viewport using JavaScript to ensure interactability.
			- Clicks on the Admin menu.
			- Logs the navigation action for execution traceability
			
		* clickOnJobSubMenu(String optionText)
			- Waits until the Job menu is visible.
			- Scrolls Job menu into view.
			- Waits until Job menu becomes clickable.
			- Clicks on Job menu to expand dropdown.
			- Logs successful Job menu click.
			- Waits for all submenu elements (menuList) to become visible.
			- Fetches all submenu options using fresh findElements() call.
			- Iterates through each submenu option:
			- Trims visible text and compares with provided optionText.
			- Scrolls matching option into view.
			- Waits until the option becomes clickable.
			- Clicks the matched submenu option.
			- Logs selected submenu.
			- Waits for page heading text (pageLoadStatus) to match selected option.
			- Exits method immediately after successful selection.
			- Logs error if the requested option is not found.
			
		* getTableRow(int index)
			- Fetches fresh list of table cells using findElements() to avoid stale references.
			- Validates requested index against current table size.
			- Returns the table cell if index is within bounds.
			- Throws IndexOutOfBoundsException if index exceeds available elements.
			
		* checkElementinTable(String element)
			- Waits until table body container is visible.
			- Retrieves all table rows dynamically.
			- Checks if table contains any rows:
			- Logs error and returns false if table is empty.
			- Waits until all rows are visible.
			- Iterates through each table row:
			- Scrolls row into view for reliable text extraction.
			- Reads and trims row text.
			- Logs row text for debugging.
			- Performs case-insensitive match against search text.
			- Returns true immediately upon match.
			- Logs error if no matching element is found after full iteration.
			- Returns false when search fails.
			
	AdminMenu Methods
	
		* clickOnAdminMenu()
			- Waits until the Admin menu element is visible in DOM.
			- Scrolls the Admin menu into viewport using JavaScript.
			- Clicks on the Admin menu.
			- Logs the action for execution tracking.
			
		* enterUsername(String user)
			- Waits until username textbox becomes clickable.
			- Scrolls textbox into view.
			- Sends provided username value using sendKeys.
			- Logs entered username.
			
		* getTableRow(int index)
			- Fetches table cells again using findElements() to avoid stale element issues.
			- Validates requested index against current table size.
			- Returns the WebElement at given index.
			- Throws IndexOutOfBoundsException if index is invalid.
			
		* searchResultUsername(String userNm)
			- First checks if info toast message is displayed:
			- If yes → logs message and returns true (no records scenario).
			- Fetches fresh table rows dynamically.
			- Ensures table rows are visible.
			- Retrieves second column value using getTableRow(1).
			- Scrolls to the cell.
			- Extracts visible text.
			- Compares extracted text with expected username.
			- Returns true if matched, false otherwise.
			
		* selectUserRole(String role)
			- Waits until User Role dropdown is clickable.
			- Scrolls dropdown into view.
			- Clicks dropdown to activate options.
			- Iterates using ARROW_DOWN key until desired role text appears.
			- Confirms selection using ENTER.
			- Logs selected role.
			
		* searchResultRole(String rol)
			- Checks info toast message first.
			- Fetches fresh table rows.
			- Waits for visibility.
			- Retrieves third column value using getTableRow(2).
			- Scrolls to the cell.
			- Extracts role text.
			- Compares with expected role.
			- Returns result.
			
		* infoToastMessage()
			- Waits briefly for info toast message.
			- Scrolls toast into view.
			- Checks if toast is displayed.
			- Logs “No records found” scenario.
			- Returns true if toast exists.
			- Handles:
			- TimeoutException when toast does not appear.
			- NoSuchElementException when toast is absent from DOM.
			- Returns false if toast not found.
			
		* clickOnSearchButton()
			- Waits until Search button is visible.
			- Scrolls Search button into view.
			- Waits until Search button becomes clickable.
			- Clicks Search button.
			- Logs action.
			- Adds small static wait to allow table refresh.
			
		* clickOnResetButton()
			- Waits until Reset button is visible.
			- Scrolls Reset button into view.
			- Waits until Reset button is clickable.
			- Clicks Reset button.
			- Logs reset action.
			
		* enterEmployeeName(String user)
			- Waits until Employee Name textbox is visible.
			- Scrolls textbox into view.
			- Clicks textbox to activate auto-suggest.
			- Sends partial employee name.
			- Waits for suggestions to load.
			- Waits for suggestions list visibility.
			- Navigates suggestion using ARROW_DOWN.
			- Selects first suggestion if available.
			- Presses ENTER to confirm selection.
			- Logs selection result.
			
		* searchResultsEmployeeName(String nm)
			- Checks info toast message first.
			- Fetches fresh table rows.
			- Waits until rows are visible.
			- Retrieves fourth column value using getTableRow(3).
			- Scrolls to cell.
			- Extracts employee name text.
			- Compares with expected value.
			- Returns result.
			
		* selectLoginStatus(String state)
			- Waits until status dropdown is clickable.
			- Clicks dropdown.
			- Uses ARROW_DOWN until expected state appears.
			- Confirms selection using ENTER.
			- Logs selected state.
			
		* searchResultStatus(String state)
			- Checks info toast message first.
			- Fetches fresh table rows.
			- Waits for visibility.
			- Retrieves fifth column value using getTableRow(4).
			- Scrolls to cell.
			- Extracts status text.
			- Compares with expected state.
			- Returns validation result.
			
	AdminOrganizationMenu methods
		
		* clickOnOrgMenu()
			- Waits until Organization menu element is visible in the DOM.
			- Scrolls the Organization menu into the viewport.
			- Clicks on the Organization menu.
			- Logs the navigation action.

		* clickOnOrgSubMenu(String optionText)
			- Waits for Organization menu visibility.
			- Scrolls Organization menu into view.
			- Waits until Organization menu becomes clickable.
			- Clicks Organization menu to expand dropdown.
			- Logs menu expansion.
			- Waits for submenu list elements to be visible.
			- Fetches submenu options using fresh findElements() call.
			- Iterates through submenu options:
			- Compares trimmed visible text with optionText.
			- Scrolls matching option into view.
			- Waits until option is clickable.
			- Clicks the submenu option.
			- Logs selected submenu.
			- Waits until page header text matches selected option.
			- Exits method immediately on success.
			- Logs error if submenu option is not found.

		* clickOnEditButton()
			- Waits briefly for Edit button visibility.
			- Scrolls Edit button into view.
			- Clicks Edit button.
			- Logs edit action.

		* updateRegNum(String s)
			- Waits until Registration Number field is visible.
			- Scrolls field into view.
			- Clicks input field to activate it.
			- Selects existing text using CTRL + A.
			- Clears value using BACK_SPACE.
			- Enters new registration number.
			- Logs updated value.

		* regNumberCheck(String s)
			- Waits for Registration Number field visibility.
			- Scrolls field into view.
			- Uses JavaScript Executor to fetch current input value.
			- Compares fetched value with expected value.
			- Logs match or mismatch.
			- Returns comparison result.

		* updateOrgName(String s)
			- Waits until Organization Name field is visible.
			- Scrolls field into view.
			- Clicks input field.
			- Clears existing value using CTRL + A and BACK_SPACE.
			- Enters new organization name.
			- Logs updated value.

		* orgNameTextCheck(String s)
			- Waits for Organization Name field visibility.
			- Scrolls into view.
			- Retrieves current value using JavaScript Executor.
			- Compares with expected value.
			- Logs match or mismatch.
			- Returns result.

		* updateTaxID(String s)
			- Waits for Tax ID field visibility.
			- Scrolls field into view.
			- Clicks input field.
			- Clears existing value.
			- Enters new Tax ID.
			- Logs updated Tax ID.

		* taxIDtextCheck(String s)
			- Waits for Tax ID field visibility.
			- Scrolls into view.
			- Retrieves field value via JavaScript Executor.
			- Compares with expected Tax ID.
			- Logs match or mismatch.
			- Returns result.

		* clickOnSave()
			- Waits until Save button is visible.
			- Scrolls Save button into view.
			- Clicks Save button.
			- Logs save action.

		* SaveToastMessageText()
			- Waits briefly for success toast visibility.
			- Scrolls toast into view.
			- Checks if toast is displayed.
			- Logs successful save.
			- Returns true if toast is visible.
			- Handles:
			- TimeoutException when toast does not appear.
			- NoSuchElementException if toast is absent in DOM.
			- Returns false if validation fails.

		* searchLocation(String nm)
			- Clicks on Location Name search textbox.
			- Enters location name.
			- Presses TAB to trigger field blur.
			- Clicks Search button.
			- ogs search operation.

		* errorToastMessageText()
			- Waits briefly for error toast visibility.
			- Scrolls toast into view.
			- Checks if toast is displayed.
			- Logs error toast presence.
			- Returns true if displayed.
			- Handles timeout and missing element cases.
			- Returns false if toast not found.

		* deleteSelected(String loc)
			- Waits briefly to allow search results to load.
			- Fetches fresh table rows dynamically.
			- Waits until all rows are visible.
			- Iterates through rows to find matching location text.
			- If location is found:
			- Locates delete icon within the row.
			- Clicks delete button.
			- Checks for error toast:
			- If no error → confirms deletion.
			- If error → logs location-in-use message.
			- Logs result if location not found.
			- Waits until confirmation dialog disappears.

		* clickOnAddLocationButton()
			- Waits until Add Location button is visible.
			- Scrolls button into view.
			- Clicks Add Location button.
			- Logs action.

		* enterLocation(String name)
			- Waits until Location Name field is clickable.
			- Scrolls field into view.
			- Enters location name.
			- Logs entry.

		* enterCity(String name)
			- Waits until City field is clickable.
			- Scrolls field into view.
			- Enters city name.
			- Logs entry.

		* enterState(String name)
			- Waits until State field is clickable.
			- Scrolls field into view.
			- Enters state name.
			- Logs entry.

		* enterZip(String name)
			- Waits until Zip Code field is clickable.
			- Scrolls field into view.
			- Enters zip code.
			- Logs entry.

		* selectCountry(String country)
			- Waits until country dropdown is clickable.
			- Clicks dropdown to load options.
			- Fetches list of country options dynamically.
			- Iterates through options:
			- Matches country name ignoring case.
			- Scrolls to matched option.
			- Clicks option.
			- Logs selected country.
			- Throws runtime exception if country not found.

		* clickOnSaveLocationButton()
			- Waits until Save button is clickable.
			- Scrolls button into view.
			- Clicks Save button.
			- Logs successful save.
	
	AdminQualificationMenu Methods
	
		* clickOnQualificationMenu()
			- Waits until Qualification menu becomes clickable.
			- Scrolls the Qualification menu into viewport.
			- Clicks the menu.
			- Logs navigation action.

		* clickOnSkills()
			- Waits until Skills submenu is visible.
			- Scrolls Skills menu into view.
			- Clicks Skills option.
			- Logs submenu navigation.

		* clickOnAddSkillsButton()
			- Waits until Add Skills button is visible.
			- Scrolls button into view.
			- Clicks Add Skills button.
			- Logs button click.

		* enterSkillName(String name)
			- Waits until Add Skills button is visible (page readiness indicator).
			- Scrolls Add Skills button into view.
			- Sends skill name into Skill Name textbox.
			- Logs entered skill name.

		* clickOnSaveSkillButton()
			- Waits until Save Skill button is visible.
			- Scrolls Save button into view.
			- Clicks Save button.
			- Logs save action.

		* SaveToastMessageText()
			- Waits briefly for success toast to appear.
			- Scrolls toast into view.
			- Checks toast visibility.
			- Logs successful toast display.
			- Returns true if toast is displayed.
			- Handles:
			- TimeoutException when toast does not appear.
			- NoSuchElementException if toast not present in DOM.
			- Returns false if toast validation fails.

		* deleteEnteredSkill(String name)
			- Waits until all skill table rows are visible.
			- Checks if table rows exist:
			- Logs and exits if table is empty.
			- Iterates through each row:
			- Fetches all table cells in the row.
			- Compares second column text with expected skill name (case-insensitive).
			- Clicks delete icon for matched skill.
			- Marks skill as found and exits loop.
			- Logs and exits if skill not found.
			- Waits until delete confirmation button becomes clickable.
			- Scrolls confirmation button into view.
			- Clicks confirm delete button.
			- Logs successful deletion.

	DashboardMenu Methods
		
		* clickOnMenu(String menuName)
			- Waits until the menu search field becomes clickable.
			- Scrolls the search field into the viewport.
			- Clicks on the menu search field to activate it.
			- Types the provided menu name.
			- Clicks on the first matching menu search result.
			- Logs successful menu navigation.
			
		* clickOnPunchInButton()
			- Waits until the Time Entry button is clickable.
			- Scrolls the button into view.
			- Clicks the button.
			- Logs the dashboard action.
			
		* checkUrlChangeToAttendance(String s)
			- Waits until the current URL changes from dashboard URL.
			- Fetches the current browser URL.
			- Compares it with expected attendance URL.
			- Logs success if URL matches.
			- Logs failure if page does not change.
			- Returns true on successful navigation, false otherwise.
			
		* clickOnDashboardApplyLeaveShortcutbutton()
			- Waits until Apply Leave shortcut button is clickable.
			- Scrolls shortcut icon into view.
			- Clicks Apply Leave shortcut.
			- Logs navigation action.
			
		* clickOnDashboardMyTimesheetShortcutbutton()
			- Waits until My Timesheet shortcut button is clickable.
			- Scrolls shortcut icon into view.
			- Clicks My Timesheet shortcut.
			- Logs successful navigation.
			
	EmployeeListMeny Methods
		
		* clickOnEmployeeListMenu()
			- Waits until Employee List menu becomes clickable.
			- Scrolls the menu into view.
			- Adds a short static wait to stabilize UI animation.
			- Clicks Employee List menu.
			- Logs navigation action.

		* enterEmployeeName(String name)
			- Waits for Employee Name textbox visibility.
			- Scrolls textbox into view.
			- Clicks textbox to activate auto-complete.
			- Types employee name.
			- Waits briefly for suggestions to bind.
			- Presses ENTER to confirm search value.
			- Logs input action.

		* enterEmployeeID(int id)
			- Waits until Employee ID field is visible.
			- Scrolls field into view.
			- Clicks the field.
			- Converts integer ID to string and enters it.
			- Presses ENTER to apply filter.
			- Logs search input.

		* selectEmploymentStatus(String status)
			- Waits until Employment Status dropdown is clickable.
			- Scrolls dropdown into view.
			- Clicks dropdown.
			- Uses ARROW_DOWN until expected status text appears.
			- Presses ENTER to select.
			- Logs selected status.

		* includePastCurrentEmp(String oldnew)
			- Waits until Include dropdown is clickable.
			- Scrolls dropdown into view.
			- Uses conditional logic:
			- old → navigates twice down.
			- both → navigates once.
			- new → default selection.
			- Confirms selection with ENTER.
			- Logs selected option.

		* enterSupervisorName(String name)
			- Waits for Supervisor field visibility.
			- Scrolls field into view.
			- Clicks field and types supervisor name.
			- Waits for suggestions to load.
			- Navigates suggestion using ARROW_DOWN.
			- Clicks first suggestion if available.
			- resses ENTER to confirm.
			- Logs supervisor selection.

		* selectJobTitle(String job)
			- Waits for Job Title dropdown visibility.
			- Scrolls dropdown into view.
			- Clicks dropdown.
			- Navigates options using ARROW_DOWN until match.
			- Presses ENTER to select.
			- Logs selected job title.

		* selectSubUnit(String unit)
			- Waits for Sub Unit dropdown visibility.
			- Scrolls dropdown into view.
			- Clicks dropdown.
			- Uses keyboard navigation until expected unit is visible.
			- Confirms selection using ENTER.
			- Logs selected sub-unit.


		* clickOnSearchButton()
			- Waits until Search button is visible.
			- Scrolls Search button into view.
			- Adds small wait to ensure filters are applied.
			- Clicks Search button.
			- Logs search execution.

		* clickOnResetButton()
			- Waits for Reset button visibility.
			- Scrolls Reset button into view.
			- Adds small wait.
			- Clicks Reset button.
			- Logs reset action.

		* getTableRow(int index)
			- Fetches table cells dynamically using findElements().
			- Validates index against current table size.
			- Returns the requested cell.
			- Throws IndexOutOfBoundsException if index is invalid.

		* searchResultID(String ids)
			- Checks for info toast first:
			- Returns true if no records found.
			- Fetches fresh table rows.
			- Waits for visibility.
			- Retrieves Employee ID column using index.
			- Moves mouse to element to ensure visibility.
			- Extracts text and compares with expected ID.
			- Logs validation result.

		* searchResultfirstName(String ln)
			- Checks info toast first.
			- Refreshes table rows.
			- Retrieves First Name column.
			- Scrolls to cell.
			- Compares text with expected value.
			- Logs result.

		* searchResultLastName(String nm)
			- Same pattern as first name validation.
			- Uses column index for Last Name.

		* searchResultJobTitle(String jtitle)
			- Checks info toast.
			- Retrieves Job Title column.
			- Scrolls and compares text.
			- Logs validation.

		* searchResultSubUnit(String location)
			- Checks info toast.
			- Retrieves Sub Unit column.
			- Scrolls and compares text.
			- Logs result.

		* searchResultEmploymentStatus(String status)
			- Checks info toast.
			- Retrieves Employment Status column.
			- Scrolls and compares text.
			- Logs result.

		* searchResultSupervisor(String supervisor)
			- Checks info toast.
			- Retrieves Supervisor column.
			- Scrolls to element using Actions.
			- Compares text.
			- Logs validation.

		* readTable()
			- Waits until all table cells are visible.
			- Iterates through each row.
			- Logs row text for debugging or reporting.

		* clickOnAddNewEmployeeButton()
			- Waits until Add New Employee button is clickable.
			- Scrolls button into view.
			- Adds short wait.
			- licks button.
			- Logs action.

		* clickOnSelectAll()
			- Checks info toast:
			- If no records → returns false.
			- Waits until Select All checkbox is clickable.
			- Scrolls checkbox into view.
			- Clicks checkbox.
			- Logs selection.
			- Returns true on success.

		* DeleteSelectedUsers()
			- Calls clickOnSelectAll() to ensure selection.
			- Waits until Delete button is clickable.
			- Scrolls Delete button into view.
			- Clicks Delete Selected button.
			- Waits briefly for confirmation dialog.
			- Clicks Confirm Delete button.
			- Logs deletion.
			- Waits until confirmation dialog disappears.

		* infoToastMessage()
			- Waits briefly for info toast visibility.
			- Scrolls toast into view.
			- Checks if toast is displayed.
			- Logs no-records scenario.
			- Returns true if toast appears.
			- Handles timeout and missing element cases.
			- Returns false if toast not found.
			
	LeaveApplyMenu Methods
	
		* clickOnLeaveMenu()
			- Waits until Leave menu is clickable.
			- Scrolls Leave menu into view.
			- Clicks Leave menu.
			- Logs navigation action.

		* clickOnApplyLeave()
			- Waits until Apply Leave option is clickable.
			- Scrolls Apply Leave menu into view.
			- Clicks Apply Leave.
			- Logs navigation.

		* selectLeaveType(String s)
			- Waits until page loader disappears.
			- Clicks Leave Type dropdown using locator.
			- Waits until dropdown listbox becomes visible.
			- Fetches all leave type options dynamically.
			- Iterates through options:
			- Matches visible text ignoring case.
			- Clicks matching leave type.
			- Logs selected leave type.

		* selectFromDate()
			- Waits until From Date field is clickable.
			- Scrolls field into view.
			- Clicks date input.
			- Determines date logic:
			- If today is Saturday → selects Monday.
			- If today is Sunday → selects Monday.
			- Else → selects today.
			- Enters calculated date.
			- Confirms selection using ENTER.
			- Logs action.

		* selectToDate()
			- Waits until To Date field is clickable.
			- Scrolls field into view.
			- Clicks input and clears existing value.
			- Uses same weekend logic as From Date.
			- Enters calculated date.
			- Uses TAB to shift focus and apply value.
			- Logs action.

		* clickOnApplyLeaveButton()
			- Waits until Apply button is clickable.
			- Scrolls button into view.
			- Clicks Apply button.
			- Logs submission.

		* SaveToastMessageText()
			- Waits briefly for success toast.
			- Scrolls toast into view.
			- Checks visibility.
			- Logs success.
			- Handles timeout and missing element cases.
			- Returns boolean result.

		* failedToApplyLeaveToastText()
			- Waits for warning toast visibility.
			- Scrolls toast into view.
			- Checks display status.
			- Logs failure.
			- Returns boolean.
			- Handles timeout and missing element cases.

		* checkWarningForLeaveType()
			- Waits until leave type required warning appears.
			- Scrolls warning into view.
			- Checks if warning is displayed.
			- Logs validation result.
			- Returns boolean.

		* getLastSaturday()
			- Iteratively subtracts days until target weekday is reached.
			- Returns calculated LocalDate.
		
		* getLastSunday()
			- Iteratively subtracts days until target weekday is reached.
			- Returns calculated LocalDate.

		* selectSaturdayFromDate()
			- Waits for From Date field.
			- Scrolls and clicks field.
			- Calculates last Saturday.
			- Enters date and confirms with ENTER.
			- Logs action.

		* selectSundayToDate()
			- Waits for To Date field.
			- Scrolls and clicks field.
			- Clears existing value.
			- Calculates last Sunday.
			- Enters date and confirms.
			- Logs action.

		* Error Validations
			- Waits for error toast visibility.
			- Scrolls toast into view.
			- Checks display status.
			- Logs error detection.
			- Handles timeout and missing element cases.
			- Returns boolean.

		* errorLeaveBalance()
			- Waits for error toast visibility.
			- Scrolls toast into view.
			- Checks display status.
			- Logs leave balance error.
			- Handles timeout and missing element cases.
			- Returns boolean.

		* clickOnUserLeaveMenu()
			- Waits until User Leave menu is clickable.
			- Scrolls menu into view.
			- Clicks menu.
			- Logs action.

		* clickOnApproveLeaveButton()
			- Waits until Approve button is clickable.
			- Scrolls button into view.
			- Clicks Approve.
			- Logs approval.

		* clickOnRejectLeaveButton()
			- Waits until Reject button is clickable.
			- Scrolls button into view.
			- Clicks Reject.
			- Logs rejection.

		* clickOnLeaveListMenu()
			- Waits until Leave List menu is clickable.
			- Scrolls menu into view.
			- Clicks Leave List.
			- Logs navigation.

		* selectLeaveStatusTaken()
			- Removes default pending filter.
			- Opens Leave Status dropdown.
			- Waits briefly for options.
			- Selects “Taken” option.
			- Logs selection.

		* clickOnSearchLeaveButton()
			- Waits until Search button is clickable.
			- Scrolls button into view.
			- Clicks Search.
			- Logs action.

		* hasLeaveRecords()
			- Fetches leave table rows dynamically.
			- Logs number of records found.
			- Returns true if at least one record exists.

		* clickOnMoreOptionsButton()
			- Waits for loader to disappear.
			- Waits until More Options button is visible.
			- Scrolls and clicks button.
			- Calls cancel option.

		* clickOnCancelLeaveOption()
			- Waits until Cancel Leave option is clickable.
			- Scrolls option into view.
			- Clicks option.
			- Logs action.

		* clickOnUserCancelLeaveButton()
			- Waits for loader to disappear.
			- Waits until Cancel button is clickable.
			- Scrolls button into view.
			- Clicks Cancel.
			- Logs confirmation.

		* cancelLeaveIfAlreadyTaken()
			- Waits until loader disappears.
			- Applies “Taken” leave filter.
			- Executes leave search.
			- Checks for leave records:
			- If present → performs cancel flow.
			- If absent → skips cancellation.
			- Logs decision.

		* isLeaveOverlapping()
			- Attempts to detect overlapping leave header.
			- Returns true if displayed.
			- Handles missing element gracefully.

		* isLeaveAppliedSuccessfully()
			- Delegates to SaveToastMessageText().
			- Returns final leave application status.
			
	LeaveEntitlementMenu Methods
	
		* clickOnLeaveEntitlementMenu()
			- Waits until Leave Entitlement dropdown is clickable.
			- Scrolls dropdown into viewport.
			- Clicks the dropdown.
			- Logs navigation action.

		* clickOnAddEntitlement()
			- Waits until Add Entitlement option is clickable.
			- Scrolls option into view.
			- Clicks Add Entitlement.
			- Logs action.

		* enterEmployeeName(String s)
			- Waits until Employee Name field is visible.
			- Scrolls field into view.
			- Clicks field to activate autocomplete.
			- Types employee name.
			- Waits briefly for suggestions to load.
			- Uses ARROW_DOWN to select first suggestion.
			- Presses ENTER to confirm selection.
			- Logs employee selection.

		* selectIndividualOrMultipleEmployee(String s)
			- Waits until radio buttons are visible.
			- Checks input value:
			- individual → clicks first radio button.
			- multiple → clicks second radio button.
			- Logs selected option.
			- Logs error for invalid input.

		* selectLeaveType(String s)
			- Waits until Leave Type dropdown is clickable.
			- Scrolls dropdown into view.
			- Clicks dropdown.
			- Waits briefly for options to load.
			- Fetches all leave type options dynamically.
			- Iterates options:
			- Matches text containing expected leave type.
			- Clicks matched option.
			- Logs selection.

		* enterEntitlement(String s)
			- Waits until Entitlement field is clickable.
			- Scrolls field into view.
			- Clicks input field.
			- Enters entitlement value.
			- Logs updated entitlement.

		* clickOnSaveButton()
			- Waits until Save button is clickable.
			- Scrolls button into view.
			- Clicks Save.
			- Logs save action.

		* clickOnConfirmButton()
			- Waits until Confirm button is clickable.
			- Scrolls button into view.
			- Clicks Confirm.
			- Logs confirmation.

		* SaveToastMessageText()
			- Waits briefly for success toast to appear.
			- Scrolls toast into view.
			- Checks visibility.
			- Logs success message.
			- Returns true if toast is displayed.
			- Handles timeout and missing element cases.
			- Returns false if toast not found.

		* clickEmployeeEntitlement()
			- Waits until Employee Entitlements menu is clickable.
			- Scrolls menu into view.
			- Clicks Employee Entitlements option.
			- Logs navigation.

		* enterEmployeeNameInSearchBoxEmplpyeeEntitlement(String s)
			- Waits until Employee Name search box is visible.
			- Scrolls field into view.
			- Clicks and types employee name.
			- Waits for suggestions to load.
			- Uses ARROW_DOWN to select suggestion.
			- Presses ENTER to confirm.
			- Logs search action.

		* selectLeaveTypeInEntitlement(String s)
			- Waits until Leave Type dropdown is clickable.
			- Scrolls dropdown into view.
			- Clicks dropdown.
			- Waits briefly for options.
			- Iterates through leave type options.
			- Selects matching option.
			- Logs selection.

		* clickOnSearchButton()
			- Waits until Search button is clickable.
			- Scrolls button into view.
			- Clicks Search.
			- Logs action.
			- Leave Balance Validation

		* CheckLeaveBalance()
			- Waits until Leave Balance field is visible.
			- Scrolls element into view.
			- Extracts visible text.
			- Logs leave balance value.
			- Converts text to double and returns it.
			
	LeaveMyLeaveMenu Methods
	
		* clickOnMyLeaveMenu()
			- Waits until My Leave menu is visible in the DOM.
			- Scrolls the menu into viewport to ensure interactability.
			- Clicks on the My Leave menu.
			- Logs navigation action.

		* clickOnLeaveMenu()
			- Waits until Leave menu becomes clickable.
			- Scrolls Leave menu into view.
			- Clicks Leave menu.
			- Logs navigation.

		* cancelAllLeaves()
			- Waits for a short duration to allow leave list to load completely.
			- Fetches all visible Cancel buttons dynamically using findElements().
			- Checks if any cancel buttons are present:
			- If none → logs that there are no leaves to cancel.
			- If cancel buttons exist:
			- Waits until all buttons are visible.
			- Iterates through each cancel button:
			- Scrolls button into view.
			- Verifies button is displayed and enabled.
			- Clicks cancel button.
			- Waits briefly to allow UI to process cancellation.
			- Logs total number of cancelled leave applications.
			
	LoginPage Methods
	
		* enterCredentials(String user, String pass)
			- Waits until Username field is visible.
			- Scrolls Username field into view.
			- Enters provided username.
			- Waits until Password field is visible.
			- Scrolls Password field into view.
			- Enters provided password.
			- Logs successful credential entry.

		* ClickOnLoginButton()
			- Waits until Login button is clickable.
			- Scrolls Login button into view.
			- Clicks Login button.
			- Logs login action.

		* dashboardText()
			- Waits until Dashboard link is visible.
			- Scrolls Dashboard link into view.
			- Extracts visible text from dashboard element.
			- Logs successful login.
			- Returns dashboard text for assertion.

		* errorText()
			- Waits until Invalid Credentials error message is visible.
			- Scrolls error message into view.
			- Extracts error text.
			- Logs error detection.
			- Returns error message text.

		* clickOnLogoutButton()
			- Waits until user profile dropdown is clickable.
			- Scrolls dropdown into view.
			- Clicks dropdown to expand menu.
			- Waits until Logout button is clickable.
			- Scrolls Logout button into view.
			- Clicks Logout button.
			- Logs successful logout.

		* logMeIn()
			- Waits for Username field visibility.
			- Scrolls and enters admin username.
			- Waits for Password field visibility.
			- Scrolls and enters admin password.
			- Clicks Login button.
			- Logs successful admin login.

		* logMeInAsUser()
			- Same internal flow as admin login.
			- Uses non-admin credentials.
			- Logs user login.

		* usernameRequiredError()
			- Waits until required username error is clickable/visible.
			- Scrolls error element into view.
			- Reads error text.
			- Logs validation.
			- Returns error message.

		* clickOnMenu(String menuName)
			- Checks if menu sidebar is collapsed:
			- If collapsed → waits, scrolls, and clicks expand icon.
			- Logs sidebar state.
			- Waits until menu search field is clickable.
			- Scrolls search field into view.
			- Clicks search field.
			- Types target menu name.
			- Waits until search result becomes clickable.
			- Scrolls result into view.
			- Clicks menu result.
			- Logs successful navigation.
			
	PerformanceMenu Methods
	
		* clickOnPerformanceMenu()
			- Waits until the Performance tab is clickable.
			- Scrolls the Performance tab into view.
			- Clicks the Performance tab.
			- Logs successful navigation.
			
		* clickOnPerformanceConfigure()
			- Waits until loader disappears.
			- Waits until Configure option is clickable.
			- Scrolls Configure option into view.
			- Adds a short stabilization wait.
			- Clicks on Configure under Performance.
			- Logs action.
			
		* clickOnDeleteKPIIcon()
			- Waits until loader disappears.
			- Waits until Delete KPI icon is clickable.
			- Scrolls Delete icon into view.
			- Clicks Delete icon.
			- Waits until confirmation delete button is clickable.
			- Clicks confirmation button.
			- Logs successful deletion.
		
		* clickOnKPIs()
			- Waits until KPIs submenu is clickable.
			- Scrolls KPIs option into view.
			- Clicks KPIs option.
			- Logs navigation.
		
		* clickOnAddKPIButton()
			- Waits until Add KPI button is visible.
			- Scrolls button into view.
			- Clicks Add KPI button.
			- Logs action.
		
		* enterKeyPerformanceIndicator(String s)
			- Waits until KPI name input is clickable.
			- Scrolls input field into view.
			- Clicks input field.
			- Enters KPI name.
			- Logs entered KPI value.
		
		* selecrJobTitleToAddKPI()
			- Waits until Job Title dropdown is clickable.
			- Scrolls dropdown into view.
			- Clicks Job Title dropdown.
			- Waits until dropdown list is visible.
			- Scrolls dropdown list into view.
			- Filters job title using keyboard input.
			- Confirms selection using ENTER.
			- Logs job title update.
		
		* enterMinAndMaxRatings()
			- Waits until Minimum Rating field is clickable.
			- Scrolls Minimum Rating field into view.
			- Enters minimum rating value.
			- Enters maximum rating value.
			- Logs rating update.
		
		* clickOnSaveKPIButton()
			- Waits until Save KPI button is clickable.
			- Scrolls Save button into view.
			- Clicks Save KPI button.
			- Logs KPI creation.
		
		* getTableRow(int index)
			- Fetches fresh table cell elements dynamically.
			- Checks if requested index exists.
			- Returns table cell at given index.
			- Throws exception if index is invalid.
		
		* searchResultKPI(String s)
			- Waits until loader disappears.
			- Waits until KPI table body is visible.
			- Fetches all KPI table rows.
			- Iterates through each row.
			- Reads row text and logs it.
			- Scrolls to matched row when KPI text is found.
			- Returns true if match is found.
			- Returns false if no match is found.
		
		* searchResultKPIAndDelete(String s)
			- Waits until at least one table row is present.
			- Fetches all KPI table rows dynamically.
			- Waits until all rows are visible.
			- Retrieves KPI name cell from table.
			- Scrolls to KPI name cell.
			- Reads KPI name text and logs it.
			- Verifies KPI name matches expected value.
			- Locates delete action cell.
			- Scrolls to delete cell and clicks it.
			- Waits until confirm delete button is visible and clickable.
			- Scrolls to confirmation button.
			- Clicks confirmation button.
			- Handles absence of table gracefully using exception handling.

	PimCustomFields Methods
	
		* clickOnCustomFields()
			- Creates a locator for configuration dropdown menu items.
			- Waits until the dropdown menu becomes visible.
			- Fetches configuration menu items dynamically from the DOM.
			- Validates that menu items are loaded and not empty.
			- Iterates through menu items and reads visible text.
			- Matches menu item text with Custom Fields.
			- Waits until the matched item is clickable.
			- Scrolls to the matched item.
			- Clicks on Custom Fields option.
			- Logs successful navigation.
			- Throws runtime exception if Custom Fields option is not found.

		* clickOnAddCustomFieldsButton()
			- Waits until Add Custom Fields button is clickable.
			- Scrolls button into view.
			- Clicks Add Custom Fields button.
			- Logs action.

		* enterFieldName(String field)
			- Waits until Field Name textbox is clickable.
			- Scrolls textbox into view.
			- Clicks inside textbox.
			- Enters custom field name.
			- Logs entry.

		* clickOnScreenOption()
			- Waits until Screen dropdown is clickable.
			- Scrolls dropdown into view.
			- Clicks Screen dropdown.
			- Navigates through dropdown options using keyboard.
			- Confirms selected option using ENTER.
			- Logs selection.

		* SelectTypeOfInputDropdown()
			- Waits until Input Type dropdown is clickable.
			- Scrolls dropdown into view.
			- Navigates dropdown options using keyboard.
			- Selects dropdown input type.
			- Logs selection.

		* selectTypeOfInputText()
			- Waits until Input Type dropdown is clickable.
			- Scrolls dropdown into view.
			- Navigates dropdown options using keyboard.
			- Selects text input type.
			- Logs selection.

		* enterOptions(String opt)
			- Waits until Options textbox becomes visible.
			- Scrolls textbox into view.
			- Enters dropdown options text.
			- Logs entry.

		* clickOnSaveCustomFieldButton()
			- Waits until Save Custom Field button is clickable.
			- Scrolls button into view.
			- Clicks Save button.
			- Logs save action.

		* SaveToastMessageText()
			- Waits until success toast message is visible.
			- Scrolls toast into view.
			- Checks visibility of toast message.
			- Logs successful save confirmation.
			- Returns toast visibility status.

		* SelectFieldToDelete(String fieldname)
			- Waits until all custom field table rows are visible.
			- Iterates through each table row.
			- Checks row text against provided field name.
			- Identifies matching row.
			- Locates delete icon inside matched row.
			- Scrolls to delete icon.
			- Clicks delete icon.
			- Waits briefly for confirmation dialog.
			- Waits until confirm delete button is clickable.
			- Scrolls to confirm delete button.
			- Clicks confirm delete button.
			- Logs deletion.
			- Logs message if field name is not found.
			
	PimMenu Methods
	
		* clickOnPIM()
			- Waits until PIM menu is visible.
			- Scrolls PIM menu into view.
			- Clicks on PIM menu.
			- Logs successful navigation.

		* clickOnConfiguration()
			- Waits until Configuration tab is visible.
			- Scrolls Configuration tab into view.
			- Clicks Configuration tab.
			- Logs click action.
			- Waits until configuration dropdown container becomes visible.
			- Fetches all configuration dropdown menu items.
			- Logs total number of dropdown items loaded.

		* clickConfigMenuItem(String menuName)
			- Fetches all configuration menu items dynamically.
			- Iterates through each menu item.
			- Checks if item is displayed.
			- Matches visible text with provided menu name.
			- Waits until matched item is clickable.
			- Clicks matched menu item.
			- Throws runtime exception if menu item is not found.

		* clickOnOptionalFields()
			- Waits until Optional Fields menu is clickable.
			- Scrolls Optional Fields menu into view.
			- Clicks Optional Fields option.
			- Logs selection.

		* turnONshowDeprecatedField()
			- Waits until Show Deprecated Fields label is clickable.
			- Scrolls label into view.
			- Clicks label.
			- Checks checkbox selection state.
			- Clicks label again if checkbox is not selected.
			- Logs final toggle state.

		* turnOFFshowDeprecatedField()
			- Waits until Show Deprecated Fields label is clickable.
			- Scrolls label into view.
			- Clicks label.
			- Checks checkbox selection state.
			- Clicks label again if checkbox is selected.
			- Logs final toggle state.

		* turnONSSNfield()
			- Waits until SSN field label is clickable.
			- Scrolls label into view.
			- Clicks label.
			- Checks SSN checkbox state.
			- Clicks label again if checkbox is not selected.
			- Logs toggle state.

		* turnOFFSSNfield()
			- Waits until SSN field label is clickable.
			- Scrolls label into view.
			- Clicks label.
			- Checks SSN checkbox state.
			- Clicks label again if checkbox is selected.
			- Logs toggle state.

		* turnONSINfield()
			- Waits until SIN field label is clickable.
			- Scrolls label into view.
			- Clicks label.
			- Checks SIN checkbox state.
			- Clicks label again if checkbox is not selected.
			- Logs toggle state.

		* turnOFFSINfield()
			- Waits until SIN field label is clickable.
			- Scrolls label into view.
			- Clicks label.
			- Checks SIN checkbox state.
			- Clicks label again if checkbox is selected.
			- Logs toggle state.

		* turnONUStaxExemptionMenufield()
			- Waits until US Tax Exemption label is clickable.
			- Scrolls label into view.
			- Clicks label.
			- Checks checkbox state.
			- Clicks label again if checkbox is not selected.
			- Logs toggle state.

		* turnOFFUStaxExemptionMenufield()
			- Waits until US Tax Exemption label is clickable.
			- Scrolls label into view.
			- Clicks label.
			- Checks checkbox state.
			- Clicks label again if checkbox is selected.
			- Logs toggle state.

		* clickOnSaveButtonOptionalFidls()
			- Waits until Save button is clickable.
			- Scrolls Save button into view.
			- Clicks Save button.
			- Logs save action.

		* SaveToastMessageText()
			- Waits until success toast message is visible.
			- Scrolls toast into view.
			- Checks toast visibility.
			- Logs confirmation.
			- Returns toast display status.

		* ClickOnSubMenuConfiguration(String menu)
			- Waits until all configuration menu items are visible.
			- Iterates through configuration menu list.
			- Matches menu text with provided value.
			- Clicks matched configuration menu.
			- Logs navigation.
			
	RecruitmentMenu Methoeds
	
		* clickOnRecruitmentMenu()
			- Waits until Recruitment menu is clickable.
			- Scrolls Recruitment menu into view.
			- Clicks on Recruitment menu.
			- Logs successful navigation.

		* clickOnVacanciesMenu()
			- Waits until Vacancies menu is clickable.
			- Scrolls Vacancies menu into view.
			- Clicks on Vacancies menu.
			- Logs navigation.

		* clickOnCandidatesMenu()
			- Waits until Candidates menu is clickable.
			- Scrolls Candidates menu into view.
			- Clicks on Candidates menu.
			- Logs navigation.

		* selectJobTitle(String s)
			- Waits until Job Title dropdown is clickable.
			- Scrolls Job Title dropdown into view.
			- Clicks Job Title dropdown.
			- Waits for dropdown options to load.
			- Iterates through available options.
			- Matches option text with provided value.
			- Clicks matched job title.
			- Logs selection.

		* clickOnAddVacancyButton()
			- Waits until Add Vacancy button is clickable.
			- Scrolls button into view.
			- Clicks Add Vacancy button.
			- Logs action.

		* enterVacancyName(String s)
			- Waits until Vacancy Name textbox is clickable.
			- Scrolls textbox into view.
			- Enters vacancy name.
			- Logs entered value.

		* selectHiringManager(String s)
			- Waits until Hiring Manager field is clickable.
			- Scrolls field into view.
			- Clicks Hiring Manager field.
			- Enters hiring manager name.
			- Waits for auto-suggestion list.
			- Selects suggested hiring manager.
			- Logs selection.

		* EnterNumberOfVacancy(String s)
			- Waits until Number of Vacancies field is clickable.
			- Scrolls field into view.
			- Clears existing value.
			- Enters required number of vacancies.
			- Logs update.

		* clickOnSaveVacancyButton()
			- Waits until Save Vacancy button is visible.
			- Scrolls button into view.
			- Clicks Save Vacancy button.
			- Waits briefly for save to complete.
			- Logs action.

		* clickOnJobTitleSearch(String s)
			- Waits until Job Title search dropdown is clickable.
			- Scrolls dropdown into view.
			- Clicks Job Title search dropdown.
			- Waits for dropdown options.
			- Iterates through job title options.
			- Selects matching job title.
			- Logs selection.

		* clickOnVacancySearchButton()
			- Waits until Search button is clickable.
			- Scrolls Search button into view.
			- Clicks Search button.
			- Logs search execution.

		* deleteEnteredVacancy(String s)
			- Waits until vacancy table rows are visible.
			- Iterates through table rows.
			- Reads vacancy name column.
			- Matches vacancy name with provided value.
			- Clicks delete icon for matched vacancy.
			- Waits for confirmation button.
			- Clicks confirm delete button.
			- Logs deletion.

		* checkVacancyAddedOrNot(String s)
			- Waits until vacancy table rows are visible.
			- Iterates through table rows.
			- Reads vacancy name column.
			- Matches vacancy name with expected value.
			- Logs result.
			- Returns true if vacancy is found.

		* clickOnCandidateMenu()
			- Waits until Candidate menu is clickable.
			- Scrolls menu into view.
			- Clicks Candidate menu.
			- Logs navigation.

		* clickOnAddCandidateButton()
			- Waits until Add Candidate button is clickable.
			- Scrolls button into view.
			- Clicks Add Candidate button.
			- Logs action.

		* enterCandidateFullName(String first, String middle, String last)
			- Waits until First Name field is clickable.
			- Scrolls First Name field into view.
			- Enters first name.
			- Enters middle name.
			- Enters last name.
			- Logs entry.

		* clickOnOpenVacanciesList()
			- Waits until Open Vacancies dropdown is clickable.
			- Scrolls dropdown into view.
			- Clicks dropdown.
			- Navigates options using keyboard.
			- Selects vacancy.
			- Logs selection.

		* enterCandidateEmailID(String s)
			- Waits until Email field is clickable.
			- Scrolls Email field into view.
			- Enters candidate email ID.
			- Logs entry.

		* enterCandidateContactNumber(String s)
			- Waits until Contact Number field is clickable.
			- Scrolls field into view.
			- Enters contact number.
			- Logs entry.

		* selectCandidateResume()
			- Waits until Resume upload icon is clickable.
			- Scrolls upload icon into view.
			- Clicks upload icon.
			- Triggers system file chooser.
			- Uploads resume using Robot utility.
			- Logs upload completion.

		* clickOnConcentClickBox()
			- Waits until consent checkbox is visible.
			- Scrolls checkbox into view.
			- Clicks consent checkbox.
			- Logs action.

		* clickOnSaveCandidateButton()
			- Waits until Save Candidate button is clickable.
			- Scrolls button into view.
			- Clicks Save Candidate button.
			- Logs save action.

		* SaveToastMessageText()
			- Waits for success toast message to appear.
			- Scrolls toast into view.
			- Verifies toast visibility.
			- Logs success confirmation.
			- Returns toast display status.

		* checkErrorMsg()
			- Waits until email required error message is visible.
			- Scrolls error message into view.
			- Checks visibility of error message.
			- Logs error status.
			- Returns error visibility status.
			
	ReportingMethod Methods
	
		* clickOnReportingMethod()
			- Waits until Reporting Method menu option is clickable.
			- Scrolls Reporting Method option into view.
			- Clicks on Reporting Method menu.
			- Logs successful navigation.

		* AddReportingMethodButton()
			- Waits until any delete confirmation overlay is invisible.
			- Waits until Add Reporting Method button is clickable.
			- Scrolls Add button into view.
			- Clicks Add Reporting Method button.
			- Logs action.

		* addrReportingMethod(String nm)
			- Waits until Reporting Method name textbox is clickable.
			- Scrolls textbox into view.
			- Enters reporting method name.
			- Checks if “already exists” error is present.
			- Logs error if reporting method already exists.
			- Clicks Save button if no error is detected.
			- Logs successful entry.

		* isAlreadyExistErrorPresent()
			- Fetches error message elements dynamically from the form.
			- Checks if error list is non-empty.
			- Returns true if error message is present.

		* checkAssertReportingMethod()
			- Checks if “already exists” error is present.
			- Logs error if duplicate reporting method is detected.
			- Returns true when duplicate exists.
			- Otherwise validates success toast message.

		* deleteSelectedReportingMethod(String name)
			- Waits until reporting method table rows are visible.
			- Iterates through reporting method list.
			- Matches row text with provided reporting method name.
			- Identifies matching reporting method row.
			- Locates delete icon inside matched row.
			- Scrolls to delete icon and clicks it.
			- Checks if error toast is displayed.
			- Clicks confirmation delete button if no error is shown.
			- Logs deletion or “method already in use” message.
			- Waits until confirmation dialog disappears.

		* errorToastMessageText()
			- Waits briefly for error toast to appear.
			- Scrolls error toast into view.
			- Checks visibility of error toast.
			- Logs detection of error toast.
			- Returns error toast visibility status.

		* SaveToastMessageText()
			- Waits briefly for success toast message.
			- Scrolls success toast into view.
			- Checks toast visibility.
			- Logs successful save confirmation.
			- Returns success toast visibility status.
			
	TerminationReasons Methods
	
		* clickOnTerminationReasonsMenu()
			- Waits until Termination Reasons menu is clickable.
			- Scrolls Termination Reasons menu into view.
			- Clicks on Termination Reasons menu.
			- Logs successful navigation.

		* ClickOnAddTerminationReasonButton()
			- Waits until any delete confirmation overlay is invisible.
			- Waits until Add Termination Reason button is clickable.
			- Scrolls Add button into view.
			- Clicks Add Termination Reason button.
			- Logs action.

		* AddTerminationReason(String name)
			- Waits until Termination Reason input field is clickable.
			- Scrolls input field into view.
			- Enters termination reason text.
			- Moves focus out of field to trigger validation.
			- Checks if duplicate error message is present.
			- Logs error if termination reason already exists.
			- Clicks Save button if no error is detected.
			- Logs successful addition.

		* isAlreadyExistErrorPresent()
			- Fetches validation error elements dynamically.
			- Checks if error list is non-empty.
			- Returns true if duplicate error is present.

		* checkAssertTerminationReason()
			- Checks for duplicate termination reason error.
			- Logs error if termination reason already exists.
			- Returns true when duplicate is detected.
			- Otherwise validates success toast message.

		* deleteSelectedTerminationReason(String name)
			- Waits until termination reason table rows are visible.
			- Iterates through termination reason list.
			- Matches row text with provided reason name.
			- Identifies matching termination reason row.
			- Locates delete icon within matched row.
			- Waits until delete icon is clickable and clicks it.
			- Checks if error toast is displayed.
			- Clicks confirm delete button if no error toast is shown.
			- Logs deletion or “reason already in use” message.
			- Waits until confirmation dialog disappears.

		* errorToastMessageText()
			- Waits briefly for error toast message to appear.
			- Scrolls error toast into view.
			- Checks visibility of error toast.
			- Logs detection of error toast.
			- Returns error toast visibility status.

		* SaveToastMessageText()
			- Waits briefly for success toast message.
			- Scrolls success toast into view.
			- Checks toast visibility.
			- Logs successful save confirmation.
			- Returns success toast visibility status
			
	TimeMenu Methods
	
		* clickOnMenu(String menuName)
			- Waits until menu search field is clickable.
			- Scrolls to menu search field.
			- Clicks on menu search field.
			- Enters menu name in search field.
			- Clicks on first matched menu result.
			- Logs successful menu navigation.

		* clickOnCreateTimesheetButtonOrEditTimesheetButton()
			- Checks if Create Timesheet button is present and visible.
			- Waits until Create Timesheet button is clickable and clicks it.
			- If Create button is not present, waits for Edit Timesheet button.
			- Scrolls to Edit Timesheet button and clicks it.
			- Logs which timesheet button was clicked.
			- Logs error if any exception occurs.

		* clickOnEditTimesheetButton()
			- Waits until Edit Timesheet button is clickable.
			- Scrolls to Edit Timesheet button.
			- Clicks Edit Timesheet button.
			- Logs action.

		* clickOnSubmitTimesheetButton()
			- Waits until Submit Timesheet button is clickable.
			- Scrolls to Submit Timesheet button.
			- Clicks Submit Timesheet button.
			- Logs action.

		* clickOnDeleteTimesheetEntryIcon()
			- Waits until delete timesheet entry icon is clickable.
			- Scrolls to delete icon.
			- Clicks delete icon.
			- Logs removal of previous timesheet entry.

		* enterProjectName(String projectName)
			- Waits until project name field is visible.
			- Scrolls to project name field.
			- Clears any existing project value.
			- Resets focus using TAB and ESC keys.
			- Enters project name text.
			- Waits for autocomplete dropdown to be clickable.
			- Selects first project suggestion.
			- Logs successful project selection.

		* selectActivity(String activity)
			- Waits until activity dropdown is clickable.
			- Scrolls to activity dropdown.
			- Clicks activity dropdown.
			- Enters activity text.
			- Confirms activity selection using Enter key.
			- Logs activity selection.

		* enterHoursForMondayAndTuesday()
			- Waits until Monday hours field is visible.
			- Scrolls to Monday hours field.
			- Clears existing Monday hours.
			- Enters hours for Monday.
			- Waits until Tuesday hours field is visible.
			- Scrolls to Tuesday hours field.
			- Clears existing Tuesday hours.
			- Enters hours for Tuesday.
			- Logs hours entry.

		* clickOnSaveTimesheetButton()
			- Waits until Save Timesheet button is clickable.
			- Scrolls to Save Timesheet button.
			- Clicks Save Timesheet button.
			- Logs save action.

		* getMondayHoursAfterEdit()
			- Waits until Monday hours input field is visible.
			- Scrolls to Monday hours input field.
			- Retrieves updated value from input field.
			- Logs retrieval of updated hours.
			- Returns Monday hours value.

		* getMondayHours()
			- Waits until Monday hours display field is visible.
			- Scrolls to Monday hours display field.
			- Retrieves displayed Monday hours.
			- Logs retrieval.
			- Returns hours text.

		* randomIncrementDecrementInHours(String hrString)
			- Fetches Monday hours if input value is null.
			- Extracts hour value from time string.
			- Randomly increments or decrements hour value.
			- Returns updated hour as string.

		* enterUpdatedMondayHours(String currentHours)
			- Waits until Monday hours field is visible.
			- Scrolls to Monday hours field.
			- Clears existing hours value.
			- Enters updated hour value.
			- Logs update action.

		* checkIfMondayHoursUpdated()
			- Waits until updated Monday hours are visible.
			- Scrolls to updated hours field.
			- Compares updated hours with previous value.
			- Logs success if hours are changed.
			- Logs failure if hours remain unchanged.
			- Returns update status.

		* SaveToastMessageText()
			- Waits briefly for success toast message.
			- Scrolls to toast message.
			- Checks if toast is displayed.
			- Logs success confirmation.
			- Returns toast visibility status.

		* getMondayHoursViewMode()
			- Waits until Monday hours value in view mode is visible.
			- Retrieves displayed Monday hours text.
			- Returns trimmed hours value.

		* clickOnViewTimesheetButton()
			- Waits until View Timesheet button is clickable.
			- Scrolls to View Timesheet button.
			- Clicks View Timesheet button.
			- Logs navigation to view mode.

		* clickOnRejectTimesheetButton()
			- Waits until Reject Timesheet button is clickable.
			- Scrolls to Reject Timesheet button.
			- Clicks Reject Timesheet button.
			- Logs rejection action.
			
	UserDropdownMenu Methods
	
		* clickOnUserProfile()
			- Waits until user dropdown is visible.
			- Scrolls to user dropdown.
			- Clicks on user dropdown.
			- Logs user profile click action.

		* clickOnAbout()
			- Waits until About option is clickable.
			- Scrolls to About option.
			- Clicks on About option.
			- Logs About option selection.

		* clickOnSupport()
			- Waits until Support option is visible.
			- Scrolls to Support option.
			- Clicks on Support option.
			- Logs Support option selection.

		* clickOnChangePassword()
			- Waits until Change Password option is clickable.
			- Scrolls to Change Password option.
			- Clicks on Change Password option.
			- Logs Change Password navigation.

		* logout()
			- Waits until Logout button is clickable.
			- Scrolls to Logout button.
			- Clicks on Logout button.
			- Logs logout action.

		* aboutInformation()
			- Waits until About information section is visible.
			- Scrolls to About information section.
			- Checks if About information is displayed.
			- Logs About information visibility.
			- Returns display status.

		* closeAboutInformatio()
			- Waits until Close About Info button is clickable.
			- Scrolls to Close button.
			- Clicks Close button.
			- Logs closing of About information dialog.

		* supportInformation()
			- Waits until Support information section is visible.
			- Scrolls to Support information section.
			- Checks if Support information is displayed.
			- Logs Support information visibility.
			- Returns display status.

		* checkIfChangePasswordPage()
			- Waits until Change Password page is visible.
			- Scrolls to Change Password page.
			- Checks if Change Password page is displayed.
			- Logs page visibility.
			- Returns navigation status.
			
	UserProfile Methods
	
		* clickOnEmployeeList()
			- Waits until Employee List menu is visible.
			- Scrolls to Employee List menu.
			- Waits until Employee List menu is clickable.
			- Clicks on Employee List menu.
			- Logs employee list navigation.

		* clickOnUser1()
			- Waits until first user row is clickable.
			- Scrolls to first user row.
			- Clicks on the first user in the list.
			- Logs user selection.

		* clickOnPersonalDetails()
			- Waits until Personal Details tab is clickable.
			- Scrolls to Personal Details tab.
			- Clicks on Personal Details tab.
			- Logs navigation to personal details section.

		* checkSSNvisibility()
			- Waits for SSN field to become invisible.
			- Determines whether SSN field is hidden or visible.
			- Logs SSN visibility status.
			- Returns invisibility status.

		* checkSSNdisplayed()
			- Waits until SSN field is visible.
			- Scrolls to SSN field.
			- Checks if SSN field is displayed.
			- Logs display status.
			- Returns display status.

		* checkSINvisibility()
			- Waits for SIN field to become invisible.
			- Determines whether SIN field is hidden or visible.
			- Logs SIN visibility status.
			- Returns invisibility status.

		* checkSINdisplayed()
			- Waits until SIN field is visible.
			- Scrolls to SIN field.
			- Checks if SIN field is displayed.
			- Logs display status.
			- Returns display status.

		* checknicknameVisibility()
			- Waits for Nickname field to become invisible.
			- Determines whether Nickname field is hidden or visible.
			- Logs Nickname visibility status.
			- Returns invisibility status.

		* checkNicknameDisplayed()
			- Waits until Nickname field is visible.
			- Scrolls to Nickname field.
			- Checks if Nickname field is displayed.
			- Logs display status.
			- Returns display status.

		* checkTaxExemptionvisibility()
			- Waits for Tax Exemption menu to become invisible.
			- Determines whether Tax Exemption menu is hidden or visible.
			- Logs Tax Exemption visibility status.
			- Returns invisibility status.

		* checkTaxExemptionDisplayed()
			- Waits until Tax Exemption menu is visible.
			- Scrolls to Tax Exemption menu.
			- Checks if Tax Exemption menu is displayed.
			- Logs display status.
			- Returns display status.

		* ClickOnMemebership()
			- Waits until Membership tab is visible.
			- Scrolls to Membership tab.
			- Clicks on Membership tab.
			- Logs membership navigation.

		* checkInsurandIDvisibility()
			- Waits for Insurance ID field to become invisible.
			- Determines whether Insurance ID field is hidden or visible.
			- Logs Insurance ID visibility status.
			- Returns invisibility status.

		* checkInsuranceIDDisplayed()
			- Waits until Insurance ID field is visible.
			- Scrolls to Insurance ID field.
			- Checks if Insurance ID field is displayed.
			- Logs display status.
			- Returns display status.

		* checkOfficeTransportvisibility()
			- Waits for Office Transport field to become invisible.
			- Determines whether Office Transport field is hidden or visible.
			- Logs Office Transport visibility status.
			- Returns invisibility status.

		* checkOfficeTransportDisplayed()
			- Waits until Office Transport field is visible.
			- Scrolls to Office Transport field.
			- Checks if Office Transport field is displayed.
			- Logs display status.
			- Returns display status.
			
=====================================================================================
	
	log4j.properties
	
		* log4j configuration
			- Defines a base directory for log files using the project root path.
			- Configures the root logger at DEBUG level.
			- Attaches both file and console appenders to the root logger.

		* Console Appender
			- Uses ConsoleAppender to print logs to the console.
			- Applies PatternLayout for readable log formatting.
			- Displays timestamp, thread name, log level, class name, and message.

		* DailyRollingFileAppender
			- Uses DailyRollingFileAppender to write logs to files.
			- Stores log files inside the defined Logs directory.
			- Appends logs instead of overwriting existing files.
			- Generates timestamped log files using date and time pattern.
			- Logs messages with DEBUG level and above.
			- Uses the same log pattern as the console appender.

		* Selenium WebDriver Log Suppression
			- Sets Selenium WebDriver logs to WARN level to reduce noise.
			- Disables low-level HTTP client wire logs.
			- Suppresses Apache HTTP internal logging.

		* Test Case Logging Configuration
			- Enables DEBUG-level logging for test case package.
			- Directs test logs to the rolling file appender.
			- Disables additivity to avoid duplicate log entries.
		
	App.properties
	
		* application configuration properties
			- Defines environment-specific URLs and browser configuration.
			- Centralizes test configuration to avoid hardcoding values in code.

		* local.url
			- Stores the login URL for the local OrangeHRM setup.
			- Used when tests are executed on a locally hosted application instance.
			- Helps in validating features during local development or debugging.

		* server.url
			- Stores the login URL for the hosted OrangeHRM demo server.
			- Used for executing tests against a remote or public environment.
			- Useful for smoke tests, regression tests, or CI pipeline execution.

		* browser
			- Specifies the browser to be used for test execution.
			- Allows switching browsers by changing configuration instead of code.
			- Makes the framework flexible and environment-independent
			
=====================================================================================

	Hooks (Cucumber)
		
		* @Before(order = 0) → setUp()
			- Executes before every scenario with the highest priority.
			- Retrieves a singleton instance of Keywords.
			- Launches the browser based on the value from properties file.
			- Opens the application URL from configuration.
			- Logs successful application launch.

		* @Before(order = 1) → setUpScenario(Scenario scenario)
			- Executes after browser launch and before test steps.
			- Creates LoginPage object using shared Keywords instance.
			- Reads scenario tags to determine login behavior.
			- Logs in as normal user when @userLogin tag is present.
			- Skips login completely when @noLogin tag is present.
			- Defaults to admin login when no specific tag is found.

		* @After → tearDown()
			- Executes after every scenario execution.
			- Safely checks for driver availability before quitting browser.
			- Closes the browser session cleanly.
			- Resets the Keywords singleton to avoid driver reuse issues.
			- Logs successful browser termination.
			
	CucumberTests setup 
	
		* Cucumber TestNG Runner configuration
			- Acts as the entry point to execute Cucumber feature files using TestNG.
			- Integrates Cucumber with TestNG execution lifecycle.

		* package com.runner
			- Organizes the runner class under a dedicated runner package.
			- Keeps execution logic separate from step definitions and hooks.

		* extends AbstractTestNGCucumberTests
			- Enables running Cucumber scenarios as TestNG tests.
			- Allows TestNG features like parallel execution, groups, and listeners.
			- Bridges Cucumber execution with TestNG framework.

		@CucumberOptions configuration

		* features
			- Points to the directory containing all .feature files.
			- Ensures Cucumber knows where to scan for scenarios.

		* glue
			- Specifies packages containing step definitions and hooks.
			- com.stepdefinition → step implementation classes.
			- com.hooks → Cucumber hooks like @Before and @After.

		* plugin
			- pretty → prints readable execution logs in console.
			- AllureCucumber7Jvm → generates rich Allure test reports.
			- Enables reporting integration without modifying test logic.

		* monochrome = true
			- Removes unreadable ANSI characters from console output.
			- Produces clean and readable logs.

		* tags
			- Used to filter which scenarios to execute.
			- Empty value means all scenarios will run.

		* dryRun = false
			- Executes scenarios instead of only validating step mappings.
			- When set to true, checks for missing step definitions only.

		* public class CucumberTests
			- Serves as the TestNG-compatible Cucumber runner class.
			- Does not require any test methods; execution is handled by the parent class.


	Stepdefinitions Methods
	
	StepBase
	
		* StepBase (Central Page Object Factory for Step Definitions)
			- Acts as a base/helper class for all step definition classes.
			- Centralizes creation of all Page Object classes.
			- Reduces duplication of new PageClass(Hooks.key) across step files.

		* Design Pattern Used
			- Lazy Factory Pattern
			- Page objects are created only when a method is called.
			- Improves readability and keeps step definitions clean.

		* Dependency Injection Strategy
			- Uses Hooks.key as a shared Keywords instance.
			- Ensures all page objects use the same WebDriver session.
			- Keeps browser lifecycle controlled by Hooks/TestBase.

		* pageClass() (generic explaination for each page class)
			- Returns a new page instance.
			- Used for specific page actions.
			
	StepDefPages
	
		* Cucumber execution entry point
			- Cucumber runner (CucumberTests) is triggered by TestNG.
			- Feature files are scanned from src/test/resources/features.
			- Glue packages (com.stepdefinition, com.hooks) are loaded.
			- Cucumber creates a test scenario execution context.

		* Scenario lifecycle initialization
			- Before scenario execution, Hooks class is initialized.
			- @Before hooks create the shared Keywords singleton.
			- Browser and URL are launched once per scenario.
			- Scenario tags (@userLogin, @noLogin) are evaluated.
			- Login flow is conditionally executed based on tags.

		* Step definition binding
			- Cucumber reads each step from the feature file.
			- Step text is matched against @When / @Then annotations.
			- Matching Java method in StepDefAdmin is invoked.
			- Parameter values are automatically injected from step text.

		* StepDefAdmin role in the framework
			- Acts as a thin orchestration layer.
			- Contains no Selenium or WebDriver logic.
			- Delegates all work to Page Objects via StepBase.
			- Performs assertions using TestNG Assert.

		* StepBase as object factory
			- StepBase provides lazy factory methods (e.g. admin(), jobs()).
			- Each method creates a new Page Object instance.
			- All Page Objects receive the same Keywords instance.
			- Prevents shared state between step definitions.

		* Page Object invocation flow
			- Step method calls a Page Object method.
			- Page Object encapsulates element locators and actions.
			- PageFactory.initElements() resolves WebElements lazily.
			- Page Objects remain stateless and reusable.

		* Keywords (core execution engine)
			- All browser interactions flow through Keywords.
			- Handles waits, scrolling, visibility, click safety.
			- Abstracts Selenium WebDriver completely.
			- Ensures synchronization and stability across steps.

		* Assertion handling
			- Page Objects return boolean or data results.
			- Assertions are executed only inside Step Definitions.
			- Keeps business validation separate from UI actions.
			- Assertion failures immediately fail the scenario.

		* Logging integration
			- Every major action logs through Log4j.
			- Logs capture framework behavior, not feature intent.
			- Console and daily rolling file logs are produced.
			- Selenium noise is suppressed for readability.

		* Error and exception handling
			- Explicit waits prevent flaky failures.
			- Toast messages are handled with short, safe waits.
			- Conditional flows handle already-existing data gracefully.
			- Framework continues cleanly or fails deterministically.

		* Scenario completion and teardown
			- After last step, @After hook executes.
			- Browser session is closed safely.
			- WebDriver instance is destroyed.
			- Keywords.reset() clears singleton state.
			- Next scenario starts with a fresh execution context.
			
	Feature files 
	
		* Feature: Page Module Name
			- Represents a logical test module inside the framework.
			- Groups all Page-related behaviors under one feature boundary.
			- Acts as a high-level execution container for scenarios.
			- Has no dependency on Page Objects or WebDriver.

		* Feature-level tags (@regression @admin @dashboard etc) 
			- Applied to all scenarios inside the feature implicitly.
			- Used by the runner to include or exclude this feature.
			- Can trigger conditional logic in Hooks (login, setup etc).
			- Help in grouping execution reports.

		* Background block behavior
			- Executed before every scenario in this feature (if present).
			- Used for common preconditions (navigation, setup).
			- Runs after @Before hooks but before scenario steps.
			- Ensures consistency without step duplication.

		* Scenario definition role
			- Each Scenario is treated as an independent test case.
			- Scenario name becomes the test identifier in reports.
			- Scenario boundaries control setup and teardown lifecycle.
			- Failure in one scenario does not affect others.

		* Feature file execution boundaries
			- Feature file does not control browser lifecycle.
			- Does not manage WebDriver, waits, or assertions.
			- Purely declarative and descriptive in nature.
			- Execution control is delegated to hooks and runner.

		* Reporting linkage
			- Feature name appears as top-level grouping in reports.
			- Scenario names map to individual test cases.
			- Step text appears as execution steps.
			- Tags influence report filtering and dashboards.
			
=====================================================================================

	TestngTests 
	
		* TestNG execution entry point
			- TestNG picks up the test class because it extends TestBase.
			- Group annotations decide whether the test is executed (smoke / regression / admin).
			- Allure annotations are registered for reporting metadata.

		* TestBase > framework bootstrapping
			- @Before method in TestBase runs before the test method.
			- Singleton Keywords instance is created or reused.
			- Browser type is read from app.properties.
			- WebDriver is initialized inside Keywords.
			- Application URL is launched.
			- Logging is initialized via Log4j.

		* Login handling inside test
			- Test explicitly calls login().logMeIn().
			- login() is resolved via StepBase lazy factory.
			- LoginPage receives the same shared Keywords instance.
			- All element interactions route through Keywords wrapper methods.
			- Login succeeds without creating a new WebDriver instance.

		* Page Object resolution
			- Every call like admin(), jobs(), orgMenu() creates a new Page Object.
			- Page Objects are lightweight and stateless.
			- All Page Objects share the same driver via Keywords.
			- PageFactory.initElements() binds locators at runtime.

		* Action execution flow (core engine)
			- Test calls a high-level business method (example: clickOnAdminMenu()).
			- Page Object method delegates all low-level work to Keywords.
			- Keywords performs:
			  > explicit waits
			  > scrolling
			  > visibility and click checks
			  > safe element interaction
			- Selenium WebDriver is never accessed directly from tests.

		* Synchronization and stability layer
			- All waits are centralized inside Keywords.
			- Prevents stale element, timing, and flakiness issues.
			- Table data is fetched dynamically to avoid cached WebElements.
			- Toast handling is abstracted for reuse.

		* Assertion layer
			- Page Objects return boolean or value-based results.
			- Assertions are performed only in test classes.
			- Maintains separation of concerns.
			- Failures are clearly mapped to business expectations.

		* Logging mechanism
			- Each significant action logs through Log4j.
			- Console and daily rolling file logs are generated.
			- Selenium internal logs are suppressed to reduce noise.
			- Logs reflect what the framework did, not raw Selenium output.

		* Allure reporting integration
			- Test lifecycle events are captured by Allure.
			- Severity, feature, story, and step annotations enrich reports.
			- Screenshots (if enabled in hooks) attach automatically on failure.
			- Reports stay independent of test implementation.

		* Cleanup and isolation
			- @After in TestBase runs after test completion.
			- Browser is safely closed.
			- WebDriver session is destroyed.
			- Keywords.reset() clears singleton reference.
			- Ensures zero cross-test contamination.

=====================================================================================

pom.xml

This project uses Maven to manage dependencies, build configuration, and execution modes for both TestNG and Cucumber based automation.

	Project Identity

		<groupId>thakur</groupId>
			- Logical namespace for the project.
			- Used to uniquely identify artifacts in a repository.

		<artifactId>orangeHRM</artifactId>
			- Project name used to generate build outputs.
			- Appears in JAR names and reports.

		<version>0.0.1</version>
			- Current version of the automation framework.

	Properties Section

		<allure.version>
			- Centralized Allure version used across dependencies.
			- Prevents version mismatch issues.

		<aspectj.version>
			- Required for Allure runtime weaving.
			- Enables step interception and reporting.

		Encoding properties
			- Ensures UTF-8 encoding for source files and reports.
			- Prevents character issues in logs and reports.
	
	Build Plugins
	
		Maven Surefire Plugin
			- Responsible for test execution.
			- Used by both TestNG and Cucumber.

		argLine
			- Injects AspectJ Weaver as a Java agent.
			- Mandatory for Allure annotations to work.

		testFailureIgnore=true
			- Ensures build does not stop on test failure.
			- Allows report generation even if tests fail.
		
	Maven Compiler Plugin
	
		Configuration:
			- Source & target set to Java 21.
			- Ensures consistency across developer machines and CI.
			- Enforces UTF-8 encoding.
	
	Core Dependencies
	
		selenium-java
			- Core browser automation engine.
			- Used by the Keywords layer for all UI actions.
		
		testng
			- Primary execution engine for TestNG-based tests.
			- Used in parallel with Cucumber.
			
		cucumber-java
			- Step definition binding engine.

		cucumber-testng
			- Integrates Cucumber with TestNG runner.
			- Enables unified execution and reporting.
			
		log4j 1.2.17
			- Central logging framework.
			- Used across Keywords, Pages, Hooks, and Tests.
			
		allure-cucumber7-jvm
			- Generates Allure reports for Cucumber scenarios.

		allure-testng
			- Generates Allure reports for TestNG tests.
	
		AspectJ Weaver
			- Enables runtime bytecode weaving.
			- Required for: Allure annotations, Step tracking and Test lifecycle interception
		
		commons-io
			- Used for file handling (screenshots, logs).

		ashot
			- Used for full-page screenshots.
			- Integrated with reporting and failure capture.

		webdrivermanager
			- Automatically manages browser drivers.
			- Eliminates manual driver setup.
			
		
	Maven Profiles (Execution Modes)
	
		TestNG Profile

			Profile ID: testng-tests
			Executes: testng-regression.xml
			Used for: Regression suites, Group-based execution and CI pipelines targeting TestNG
			Command : mvn test -Ptestng-tests
		
		Cucumber Profile
		
			Profile ID: cucumber-tests
			Executes: CucumberTests.class
			Used for: BDD execution, Feature-driven testing and Tag-based runs
			Command: mvn test -Pcucumber-tests
			
=====================================================================================