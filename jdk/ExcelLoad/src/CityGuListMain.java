import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CityGuListMain {

	public static void main(String[] args) {
		ZipSecureFile.setMinInflateRatio(0);
		try {
			FileInputStream fis = new FileInputStream("position.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			//엑셀 시트 선택
			XSSFSheet sheet = workbook.getSheetAt(0);//엑셀 시트 순번으로 읽어옴
			HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
			
			for(int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
				if(map.get(sheet.getRow(i).getCell(2).toString()) == null)
					map.put(sheet.getRow(i).getCell(2).toString(), new HashSet<String>());
				if(sheet.getRow(i).getCell(3).toString().equals(""))
					continue;
				map.get(sheet.getRow(i).getCell(2).toString()).add(sheet.getRow(i).getCell(3).toString());
			}
			System.out.println(map.toString());
			Set<String> city = map.keySet();
			Iterator<String> it = city.iterator();
			while(it.hasNext()) {
				String key = it.next();
				System.out.println(key);
				Iterator<String> gu = map.get(key).iterator();
					while(gu.hasNext()) {
						System.out.println("\t" + gu.next());
					}
			}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
