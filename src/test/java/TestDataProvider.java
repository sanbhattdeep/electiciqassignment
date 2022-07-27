import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

public class TestDataProvider {

    @DataProvider(name = "excelDataProvider")
    public static Object[][] getTestDataFromExcel() {
        return getExcelData(System.getProperty("FilePath")+"TestData.xlsx", "TestData");

    }

    public static Object[][] getExcelData(String excelPath, String sheetName) {

        Workbook book = null;
        Sheet sheet = null;

        try {
            book = WorkbookFactory.create(new File(excelPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
            }
        }
        return data;
    }
}
