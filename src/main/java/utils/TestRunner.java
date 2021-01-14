package utils;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;
import il.co.topq.difido.model.Enums;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventMetaModifier;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestRunner {

    private static final String REPORTING_FILE_PATH = "/Users/orenbroshi/IdeaProjects/GmHomeTask/test-output/difido/current/index.html";
    public static ReportDispatcher report = ReportManager.getInstance();

    public static void takeScreenShot(WebDriver driver) {
        try {
            Thread.sleep(1000);
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            report.addImage(scrFile, "Screenshot: " + getCurrentTimeStampAsStrings());
        } catch (Exception e) {
            report.log(e.getMessage(), Enums.Status.error);
        }
    }

    private static String getCurrentTimeStampAsStrings() {
        LocalDateTime ldt = new LocalDateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy, MMMM dd, HH:mm:ss");
        return fmt.print(ldt);
    }

    public static void openHtmlReportFile(boolean quitDriver, WebDriver driver) {
        try {
            File htmlFile = new File(REPORTING_FILE_PATH);
            Desktop.getDesktop().browse(htmlFile.toURI());
            if (quitDriver && driver != null) {
                driver.quit();
            }
        } catch (Exception ex) {
            printToLog("BaseTest.openReportHtmlFile: " + ex.getMessage());
        }
    }

    public static void printToLog(String content) {
        report.log(content);
    }

    public static List<String> getAllConnectedDevicesUdid() {
        String line;
        List<String> connectedDevices = new ArrayList<String>();
        try {
            Runtime rt = java.lang.Runtime.getRuntime();
            // Start a new process: UNIX command ls
            Process p = rt.exec("adb devices");
            // Get process’ output: its InputStream
            InputStream is = p.getInputStream();
            BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
            // And print each line
            line = (reader.readLine());
            while ((line = reader.readLine()) != null) {
                if (line.contains("device")) {
                    connectedDevices.add((line.split(" ")[0]).split("\t")[0]);
                }
            }
            is.close();

        } catch (Exception ex) {
            printToLog("TestRunner.getAllConnectedDevices: " + ex.getMessage());
        }
        return connectedDevices;
    }

}
