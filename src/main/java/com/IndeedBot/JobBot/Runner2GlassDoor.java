package com.IndeedBot.JobBot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Runner2GlassDoor implements Runnable {

	String starturl, email, password;
	ArrayList<String> btn;
	ArrayList<ArrayList<String>> btnIfElse;
	ArrayList<String> select;
	ArrayList<ArrayList<String>> typeIfLabel;
	int amountApplied = 0;

	public Runner2GlassDoor(String starturl, String email, String password, ArrayList<String> btn,
			ArrayList<ArrayList<String>> btnIfElse, ArrayList<String> select,
			ArrayList<ArrayList<String>> typeIfLabel) {
		this.starturl = starturl;
		this.email = email;
		this.password = password;
		this.btn = btn;
		this.btnIfElse = btnIfElse;
		this.select = select;
		this.typeIfLabel = typeIfLabel;
	}

	public void run() {

		EdgeOptions obj = new EdgeOptions();
		obj.addArguments("--disable-blink-features=AutomationControlled");
		obj.addArguments(
				"user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
		obj.addArguments("−−lang=es");
		obj.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		EdgeDriver driver = new EdgeDriver(obj);

		int size = driver.manage().window().getSize().height;
		while (driver.manage().window().getSize().height == size) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

//		System.setProperty("webdriver.gecko.driver", "C:\\Users\\isaac\\Desktop\\Java_Workspace\\JobBot\\geckodriver.exe");
//		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//		capabilities.setCapability("marionette",true);
//		WebDriver  driver = new FirefoxDriver();

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		int jobsAppliedTo = 0;
		String lastPageUrl = "";
		boolean alreadyGoneBack = false;
		driver.get("https://www.glassdoor.com/index.htm");

		try {
			logIn(driver);
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.navigate().to(starturl);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e3) {
			e3.printStackTrace();
		}
		System.out.println("->" + driver.findElements(By.xpath(
				"//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'show more jobs')]"))
				.size());
		String lastItemId = "";
		while (driver.findElements(By.xpath(
				"//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'show more jobs')]"))
				.size() > 0) {
			try {
				Thread.sleep(2000);
				if (driver.findElements(By.cssSelector(".e1jbctw80.ei0fd8p1.css-1n14mz9.e1q8sty40")).size() > 0) {
					System.out.println("Close Popup");
					driver.findElement(By.cssSelector(".e1jbctw80.ei0fd8p1.css-1n14mz9.e1q8sty40")).click();
				}
				Thread.sleep(2000);
				driver.findElement(By.xpath(
						"//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'show more jobs')]"))
						.findElement(By.xpath("parent::*")).findElement(By.xpath("parent::*")).click();
				String tempLastItemId = driver.findElements(By.className("css-1nh9iuj"))
						.get(driver.findElements(By.className("css-1nh9iuj")).size() - 1).getAttribute("href");
				if (tempLastItemId.equals(lastItemId)) {
					break;
				} else {
					lastItemId = tempLastItemId;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int startJobPage = 0;
		List<WebElement> jobPages = driver.findElements(By.className("JobsList_jobListItem__JBBUV"));
		loop: for (int jobInd = startJobPage; jobInd < jobPages.size(); jobInd++) {
			try {
				Thread.sleep(7000);
				jobPages.get(jobInd).click();
				Thread.sleep(2000);

				driver.findElement(By.xpath(
						"//button//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'easy apply')]"))
						.click();

				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(tabs.size() - 1));

				Thread.sleep(7000);
				String url = driver.getCurrentUrl();
				for (int i = 0; i < 10; i++) {
					try {
						fillInInfo(driver);
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
					if (this.moveOn(driver, driver.getCurrentUrl())) {
						System.out.println("Move Break!");
						break;
					}
					Thread.sleep(5000);
				}

				Thread.sleep(5000);
				if (driver.findElements(By.xpath(
						"//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'your application was sent.')]"))
						.size() > 0) {
					jobsAppliedTo++;
					System.out.println("Jobs Applied To: " + jobsAppliedTo);
				}
				closeAdditionalTabs(driver);
			} catch (Exception e) {
				e.printStackTrace();
				closeAdditionalTabs(driver);
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
			}

			System.out.println("Number of Popups: "
					+ driver.findElements(By.cssSelector(".e1jbctw80.ei0fd8p1.css-1n14mz9.e1q8sty40")).size());

			if (driver.findElements(By.cssSelector(".e1jbctw80.ei0fd8p1.css-1n14mz9.e1q8sty40")).size() > 0) {
				startJobPage = jobPages.size();
				driver.findElement(By.cssSelector(".e1jbctw80.ei0fd8p1.css-1n14mz9.e1q8sty40")).click();
			}
		}
		driver.close();
		JOptionPane.showMessageDialog(null, jobsAppliedTo + " Jobs Applied To!", "Finish", JOptionPane.PLAIN_MESSAGE);
	}

	public boolean moveOn(WebDriver driver, String url) throws InterruptedException {
		System.out.println("Move On:");
		if (driver.findElements(By.className("ia-continueButton")).size() > 0) {
			Thread.sleep(7000);
			try {
				driver.findElement(By.className("ia-continueButton")).click();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Here I Am!!!");
			}
		} else if (driver.findElements(By.xpath(
				"//button//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'submit your application')]"))
				.size() > 0) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,100)");
			driver.findElement(By.xpath(
					"//button//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'submit your application')]"))
					.click();
			Thread.sleep(5000);
		} else if (driver.findElements(By.className("ia-HasApplied-bodyTop--text")).size() > 0) {
			closeAdditionalTabs(driver);
		} else if (driver.findElements(By.xpath(
				"//button//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'review your application')]"))
				.size() > 0) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,100)");
			driver.findElement(By.xpath(
					"//button//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'review your application')]"))
					.click();
			Thread.sleep(5000);
		}

		return false;

	}

	public int getAmountAppliedTo() {
		return this.amountApplied;
	}

	public void fillInInfo(WebDriver driver) throws InterruptedException {
		System.out.println("Fill In Info");

		if (driver.findElements(By.id("resume-display-buttonHeader")).size() > 0) {
			Thread.sleep(7000);
			driver.findElement(By.id("resume-display-buttonHeader")).click();
			Thread.sleep(7000);
		}

		if (driver.findElements(By.id("resume-upload")).size() > 0) {
			driver.findElement(By.id("resume-upload"))
					.sendKeys("C:\\Users\\isaac\\Desktop\\Java_Workspace\\JobBot\\resume.pdf");
			try {
				driver.findElement(By.id("resume-upload")).submit();
			} catch (Exception e) {
			}
			System.out.println(driver.findElements(By.id("resume-upload")).size());
		}

		for (String b : btn) {
			clickXpaths(driver,
					"//label//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
							+ b + "')]");
		}

		for (ArrayList<String> set : btnIfElse) {
			String[] setIf = new String[set.size() - 2];
			String setIfThen = set.get(set.size() - 2);
			String setElse = set.get(set.size() - 1);
			for (int i = 0; i < setIf.length; i++) {
				setIf[i] = set.get(i);
			}
			clickXpathsIfElse(driver, setIf, setIfThen, setElse);
		}

		List<WebElement> labels = driver.findElements(By.cssSelector(".css-dtssv9.es2vvo70")).size() > 0
				? driver.findElements(By.cssSelector(".css-dtssv9.es2vvo70"))
				: driver.findElements(By.cssSelector(".css-132vple.es2vvo70")).size() > 0
						? driver.findElements(By.cssSelector(".css-132vple.es2vvo70"))
						: driver.findElements(By.cssSelector(".css-1v67kj9.e1wnkr790"));
		for (WebElement label : labels) {
			for (ArrayList<String> set : this.typeIfLabel) {
				String[] setIf = new String[set.size() - 1];
				String setIfThen = set.get(set.size() - 1);
				for (int i = 0; i < setIf.length; i++) {
					setIf[i] = set.get(i);
				}
				ifLabelTypeText(driver, label, setIf, setIfThen);
			}
		}

		if (driver.findElements(By.id("downshift-0-toggle-button")).size() > 0) {
			try {
				driver.findElement(By.id("downshift-0-toggle-button")).click();
				if (driver.findElements(By.id("downshift-0-item-0")).size() > 0)
					driver.findElement(By.id("downshift-0-item-0")).click();
			} catch (Exception e) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				e.printStackTrace();
			}
		}
		clickSelect(driver, select.toArray(new String[select.size()]));
	}

	public boolean ifLabelTypeText(WebDriver driver, WebElement label, String[] ifLabel, String text) {
		for (String labelText : ifLabel) {
			if (label.getText().toLowerCase().contains(labelText)) {
				label.click();
				try {
					for (int i = 0; i < 50; i++)
						driver.switchTo().activeElement().sendKeys(Keys.BACK_SPACE);
					Thread.sleep(150);
				} catch (Exception e) {
					e.printStackTrace();
				}
//				driver.switchTo().activeElement().sendKeys(text);
				this.sendKeysSlowly(driver.switchTo().activeElement(), text);
				return true;
			}
		}
		return false;
	}

	public void clickSelect(WebDriver driver, String[] optionsToClick) {

		for (WebElement selectInput : driver.findElements(By.tagName("select"))) {
			selectInput.click();
			loop: for (WebElement optionInput : selectInput.findElements(By.tagName("option"))) {
				for (String option : optionsToClick)
					if (optionInput.getText().toLowerCase().contains(option)) {
						optionInput.click();
						break loop;
					}
			}
		}

	}

	public void clickXpathsIfElse(WebDriver driver, String[] ifArray, String ifOut, String elseOut) {
		List<WebElement> yearInputs = driver.findElements(By.xpath(
				"//label//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
						+ elseOut + "')]"));
		loop: for (int i = 0; i < yearInputs.size(); i++) {
			String legend = "";
			if (driver.findElements(By.xpath(
					"//label//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
							+ elseOut + "')]//ancestor::fieldset//legend"))
					.size() > i)
				legend = driver.findElements(By.xpath(
						"//label//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
								+ elseOut + "')]//ancestor::fieldset//legend"))
						.get(i).getText().toLowerCase();
			for (String ifTest : ifArray) {
				if (legend.toLowerCase().contains(ifTest)) {
					driver.findElements(By.xpath(
							"//label//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
									+ elseOut
									+ "')]//ancestor::fieldset//label//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
									+ ifOut + "')]"))
							.get(i).click();
					break loop;
				} else {
					yearInputs.get(i).click();
				}
			}

		}
	}

	public void clickXpathsIf(WebDriver driver, String[] ifArray, String ifOut) {
		List<WebElement> yearInputs = driver.findElements(By.xpath(
				"//label//span//span//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
						+ ifOut + "')]"));
		for (int i = 0; i < yearInputs.size(); i++) {
			String legend = "";
			if (driver.findElements(By.xpath(
					"//label//span//span//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
							+ ifOut + "')]//ancestor::fieldset//legend"))
					.size() > i)
				legend = driver.findElements(By.xpath(
						"//label//span//span//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
								+ ifOut + "')]//ancestor::fieldset//legend"))
						.get(i).getText().toLowerCase();
			for (String ifTest : ifArray) {
				if (legend.toLowerCase().contains(ifTest)) {
					driver.findElements(By.xpath(
							"//label//span//span//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
									+ ifOut
									+ "')]//ancestor::fieldset//label//span//span//span[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"
									+ ifOut + "')]"))
							.get(i).click();
					break;

				}

			}
		}
	}

	public void numberInput(WebDriver driver) {
		for (WebElement yearInput : driver.findElements(By.xpath("//input[@type='number']"))) {
			yearInput.click();
			try {
				for (int i = 0; i < 30; i++)
					yearInput.sendKeys(Keys.BACK_SPACE);
			} catch (Exception e) {
			}
//				yearInput.sendKeys("" + (5 + (int) (Math.random() * 3)));
			this.sendKeysSlowly(yearInput, "" + (5 + (int) (Math.random() * 3)));
		}
	}

	public void clickXpaths(WebDriver driver, String xpath) {
		for (WebElement yearInput : driver.findElements(By.xpath(xpath))) {
			yearInput.click();
		}
	}

	public void closeAdditionalTabs(WebDriver driver) {
		System.out.println("Clossing tabs!");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		while (tabs.size() > 1) {
			driver.switchTo().window(tabs.get(1));
			driver.close();
			driver.switchTo().window(tabs.get(0));
			tabs = new ArrayList<String>(driver.getWindowHandles());
		}
	}

	public void logIn(WebDriver driver) throws InterruptedException {
		while (true) {
			try {
				if (driver.findElements(By.xpath(
						"//form//div//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'enter email')]"))
						.size() > 0) {
					driver.findElement(By.xpath(
							"//form//div//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'enter email')]"))
							.click();
					for (int i = 0; i < 50; i++)
						driver.switchTo().activeElement().sendKeys(Keys.BACK_SPACE);
					Thread.sleep(150);
					this.sendKeysSlowly(driver.switchTo().activeElement(), this.email);
					Thread.sleep(2000);
					driver.switchTo().activeElement().sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					driver.findElement(By.xpath(
							"//div//div//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'password')]"))
							.click();
					this.sendKeysSlowly(driver.switchTo().activeElement(), this.password);
					Thread.sleep(2000);
					driver.switchTo().activeElement().sendKeys(Keys.ENTER);
					break;
				}
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
	}

	public void sendKeysSlowly(WebElement elem, String text) {
		int dely = 100;
		for (char letter : text.toCharArray()) {
			try {
				Thread.sleep(dely);
			} catch (InterruptedException e) {
			}
			elem.sendKeys(letter + "");
		}
	}

}
