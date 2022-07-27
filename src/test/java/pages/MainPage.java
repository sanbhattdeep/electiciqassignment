package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MainPage {

    static WebDriver driver;
    //Locator for filter text input
    By filterInput = By.id("filter-input");
    //Locator for sort selection
    By sortSelect = By.id("sort-select");
    //list to store unfiltered table data
    static List<TableModel> unFilteredTableView = new ArrayList<>();

    //Locator for table data rows
    static By tableRows = By.cssSelector(".table-row");
    //Locator for name table data
    static String tableDataName = ".table-row:nth-child(%d)  .table-data.data-name";
    //Locator for complexity table data
    static String tableDataComplexity = ".table-row:nth-child(%d)  .table-data.data-complexity";
    //Locator for impact score table data
    static String tableDataAvgImpactScore = ".table-row:nth-child(%d)  .table-data.data-averageImpact";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTableDataName() {
        return tableDataName;
    }

    public void setTableDataName(String tableDataName) {
        this.tableDataName = tableDataName;
    }

    public String getTableDataComplexity() {
        return tableDataComplexity;
    }

    public void setTableDataComplexity(String tableDataComplexity) {
        this.tableDataComplexity = tableDataComplexity;
    }

    public void filterData(String filterText) {
        driver.findElement(filterInput).sendKeys(filterText);
    }

    public void clearFilterText() {
        driver.findElement(filterInput).clear();
    }

    public void sortData(String sortLabel) {
        Select sortSelection = new Select(driver.findElement(sortSelect));
        sortSelection.selectByVisibleText(sortLabel);
    }

    /*
     Method will return true if filter text is present
     in name or complexity values
     */
    public Boolean verifyTableDataIsFiltered(String filterText) {
        List<WebElement> tableDataRows = driver.findElements(tableRows);

        if (tableDataRows.size() == 0) {
            System.out.println("Table is cleared, So check with stored table data");
            return checkFilterMatchInUnFilteredView(filterText);
        } else {
            System.out.println("Table has contents, So check displayed table data");
            return checkFilterMatchInTable(filterText, tableDataRows);
        }
    }

    public Boolean checkFilterMatchInTable(String filterText, List<WebElement> tableDataRows) {
        for (int i = 0; i < tableDataRows.size(); i++) {
            String nameValue =
                    driver.findElement(By.cssSelector(String.format(tableDataName, i + 1))).getText().toLowerCase();
            String complexityValue =
                    driver.findElement(By.cssSelector(String.format(tableDataComplexity, i + 1))).getText().toLowerCase();
            if (!(nameValue.contains(filterText.toLowerCase()) || complexityValue.contains(filterText.toLowerCase())))
                return false;
        }
        return true;
    }

    public Boolean checkFilterMatchInUnFilteredView(String filterText) {

        Boolean isMatch = false;

        for (TableModel tm : unFilteredTableView) {
            String nameValue = tm.getName().toLowerCase();
            String complexityValue = tm.getComplexity().toLowerCase();
            if (!(nameValue.contains(filterText.toLowerCase()) || complexityValue.contains(filterText.toLowerCase()))) {
                isMatch = true;
            }
        }
        return isMatch;
    }

    public List<String> getNameListFromTable() {
        List<String> nameList = getTableDataForColumn(tableDataName);
        return nameList;
    }

    public List<Float> getImpactScoreListFromTable() {
        List<String> nameList = getTableDataForColumn(tableDataAvgImpactScore);
        return nameList.stream().map(Float::parseFloat).collect(Collectors.toList());
    }

    public List<String> getTableDataForColumn(String tableColumnLocator) {
        List<WebElement> tableDataRows = driver.findElements(tableRows);
        List<String> tableColumnValues = new ArrayList<>();

        if (tableDataRows.size() > 0) {

            for (int i = 1; i <= tableDataRows.size(); i++) {
                String colValue = driver.findElement(By.cssSelector(String.format(tableColumnLocator, i))).getText();
                tableColumnValues.add(colValue);
            }
        }
        return tableColumnValues;
    }

    public static void buildTestData(WebDriver driver) {

        List<WebElement> tableDataRows = driver.findElements(tableRows);

        for (int i = 1; i <= tableDataRows.size(); i++) {
            String nameValue = driver.findElement(By.cssSelector(String.format(tableDataName, i))).getText();
            String complexityValue =
                    driver.findElement(By.cssSelector(String.format(tableDataComplexity, i))).getText();
            unFilteredTableView.add(new TableModel(nameValue, complexityValue));
        }
    }
}
