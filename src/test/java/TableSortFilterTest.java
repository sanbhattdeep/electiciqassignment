import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainPage;

public class TableSortFilterTest {


    WebDriver driver;


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://mystifying-beaver-ee03b5.netlify.app/");
        driver.manage().window().maximize();
        MainPage.buildTestData(driver);
    }

    @Test(dataProvider = "excelDataProvider", dataProviderClass = TestDataProvider.class)
    public void testFilterWithDefaultSortingByName(String filterText) {
        MainPage mainPage = new MainPage(driver);
        System.out.println("Testing filtering with text " + filterText + " and default sorting by name");
        if (!filterText.equals(StringUtils.EMPTY)) {
            //clear previous filter text
            mainPage.clearFilterText();
        }
        mainPage.filterData(filterText);
        Assert.assertTrue(mainPage.verifyTableDataIsFiltered(filterText), "Table Data Is Not Filtered");
        //check if table is sorted by name
        Assert.assertTrue(Utils.isSorted(mainPage.getNameListFromTable()), "Table Data Is Not Sorted By Name");
    }

    @Test(dataProvider = "excelDataProvider", dataProviderClass = TestDataProvider.class)
    public void testFilterWithSortingByAvgImpactScore(String filterText) {
        MainPage mainPage = new MainPage(driver);
        System.out.println("Testing filtering with text " + filterText + " and sorting by Impact Score");
        if (!filterText.equals(StringUtils.EMPTY)) {
            //clear previous filter text
            mainPage.clearFilterText();
        }
        mainPage.filterData(filterText);
        Assert.assertTrue(mainPage.verifyTableDataIsFiltered(filterText), "Table Data Is Not Filtered");
        //check if table is sorted by impact score
        mainPage.sortData("Impact score");
        Assert.assertTrue(Utils.isSortedFloat(mainPage.getImpactScoreListFromTable()), "Table Data Is Not Sorted By " +
                "Name");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
