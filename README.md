# electiciqassignment

This Test Suite contains tests that cover the assignment

1.testFilterWithDefaultSortingByName - tests comibination of filtering and default sorting by name
2.testFilterWithSortingByAvgImpactScore - tests comibination of filtering and sorting by Average Impact Score

Input data to these tests i.e the filter text will be fed from an excel spreadsheet 

Prequisites:-

JRE 1.8
Maven 3.8.3

Tools/Libraries used:-

1.Selenium Webdriver - UI Automation
2.Maven - Build 
3.TestNG - Test Runner
4.Apache POI - Test Data

How To Run the test suite:-

Run the below command from a powershell/bash/dos terminal

mvn clean test -DFilePath="src\test\resources\"

FilePath=Relative path of the test data spreadsheet
