/**
 * 
 */
package com.scopegroup.library.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scopegroup.library.pojo.Author;
import com.scopegroup.library.pojo.Publication;
import com.scopegroup.library.utils.ParseUtil;
import com.scopegroup.library.utils.PublicationMapUtil;

/**
 * @author Raja
 *
 *         Scope Group test
 */
@Service("excelService")
public class ExcelFileServiceImpl implements IFileService{

	private static final char DEFAULT_SEPARATOR = ',';
	private static final char DOUBLE_QUOTES = '"';
	private static final char DEFAULT_QUOTE_CHAR = DOUBLE_QUOTES;

	@Override
	public Set<Publication> readFile(MultipartFile file) {

		int indexLine = 1;
		int skipLine = 1;
		String[] headerArray = null;
		List<String> csvLineInArray = null;
		Map<String, String> arrayObjectMap = new HashMap<String, String>();
		Set<Publication> publicationSet = new HashSet<>();
		
		try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();

			while (rows.hasNext()) {
				Row currentRow = rows.next();

				Cell cellsInRow = currentRow.getCell(0);
				String cellValue = cellsInRow.getStringCellValue();

				if (indexLine++ <= skipLine) { // first line header array
					headerArray = cellValue.split(",");
					continue;
				}
				
				// add comma at the end of the string for comfortable parsing
				String addComma = cellValue + ",";

				try {
					csvLineInArray = ParseUtil.parse(addComma, DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHAR);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Set<Author> authors = new HashSet<>();

				for (int i = 0; i < csvLineInArray.size(); i++) {
					arrayObjectMap.put(headerArray[i], csvLineInArray.get(i));
				}
				
				Publication publication = PublicationMapUtil.getPublicationMap(csvLineInArray, headerArray, arrayObjectMap, authors);

				publicationSet.add(publication);

			}

			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return publicationSet;
	}

}
