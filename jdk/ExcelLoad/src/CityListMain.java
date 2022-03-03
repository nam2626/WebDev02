import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CityListMain {

	public static void main(String[] args) {
		ZipSecureFile.setMinInflateRatio(0);
		try {
			FileInputStream fis = new FileInputStream("position.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			//엑셀 시트 선택
			XSSFSheet sheet = workbook.getSheetAt(0);//엑셀 시트 순번으로 읽어옴
			HashSet<String> set = new HashSet<String>();
			for(int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
				set.add(sheet.getRow(i).getCell(2).toString());
			}
			System.out.println(set);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
