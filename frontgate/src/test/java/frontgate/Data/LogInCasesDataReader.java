package frontgate.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.formula.functions.Value;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LogInCasesDataReader {
	public Object[][] getData() throws IOException {
		Object[][] myData = null;
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\frontgate\\Data\\LogInCasesData.xlsx");
		XSSFWorkbook myWoorkbook = new XSSFWorkbook(fis);
		int sheets = myWoorkbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (myWoorkbook.getSheetName(i).equalsIgnoreCase("LoginData")) {
				XSSFSheet sheet = myWoorkbook.getSheetAt(i);

				int rowsCount = sheet.getLastRowNum();
				Row firstRow = sheet.getRow(0);
				int columnCount = firstRow.getLastCellNum();
				myData = new Object[rowsCount][columnCount];
				
				for (int i1 = 0; i1 < rowsCount; i1++) {
					Row myRow = sheet.getRow(i1+1);
					for (int j = 0; j < columnCount; j++) {
						if(myRow != null) {
						Cell myCell = myRow.getCell(j);
						DataFormatter formatter = new DataFormatter();
						myData[i1][j] = formatter.formatCellValue(myCell);
						}else {
							myData[i1][j] = "";
						}
					}
				}

			}
		}
		return myData;
	}
}
