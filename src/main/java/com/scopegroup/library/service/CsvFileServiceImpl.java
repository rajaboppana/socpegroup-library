/**
 * 
 */
package com.scopegroup.library.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scopegroup.library.pojo.Book;

/**
 * @author Raja
 *
 * Scope Group test
 */

@Service("csvService")
public class CsvFileServiceImpl implements IFileService {

	private static final char DEFAULT_SEPARATOR = ',';
	private static final char DOUBLE_QUOTES = '"';
	private static final char DEFAULT_QUOTE_CHAR = DOUBLE_QUOTES;

	public Set<Book> readFile(MultipartFile csvFile) throws Exception {

		int indexLine = 1;
		int skipLine = 1;
		List<String> csvLineInArray = null;
		String[] headerArray = null;
		Map<String, String> arrayObjectMap = new HashMap<String, String>();
		Set<Book> bookSet = new HashSet<Book>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), "UTF-8"))) {

			String line;
			String excludeDoublequotes = null;
			while ((line = br.readLine()) != null) {

				int textLength = line.length();
				
				if (indexLine++ <= skipLine) { //first line header array
					excludeDoublequotes = line.substring(2, textLength - 1);
					headerArray = excludeDoublequotes.split(",");
					continue;
				}

				csvLineInArray = parse(line, DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHAR);

				//map headers and parsed values
				for (int i = 0; i < headerArray.length; i++) {
					arrayObjectMap.put(headerArray[i], csvLineInArray.get(i));
				}

				Book book = new Book();

				book.setEmail(arrayObjectMap.get(new String("email")));
				book.setFirstName(arrayObjectMap.get("firstName"));
				book.setLastName(arrayObjectMap.get("lastName"));
				book.setType(arrayObjectMap.get("type").charAt(0));
				book.setIsbn(arrayObjectMap.get("isbn"));
				book.setTitle(arrayObjectMap.get("title"));
				book.setDescription(arrayObjectMap.get("description"));
				book.setDate(arrayObjectMap.get("issueDate"));

				bookSet.add(book);

			}
		}

		return bookSet;
	}

	public List<String> parse(String line, char separator, char quoteChar) throws Exception {
        
		List<String> result = new ArrayList<>();
		int inQuotesCounter = 0;
		String excludeDoublequotes = null;
		boolean inQuotes = false;
		StringBuilder field = new StringBuilder();
		
		int textLength = line.length();

		if (textLength >= 2 && line.charAt(0) == '"' && line.charAt(textLength - 1) == '"') { //remove quotes start and end of the string
			excludeDoublequotes = line.substring(1, textLength - 1);
		}
		
		//add comma at the end of the string for comfortable parsing
		String addComma = excludeDoublequotes + ",";

		for (char c : addComma.toCharArray()) {

			if (c == quoteChar) {
				inQuotes = true;
				inQuotesCounter++;
			}

			if (c == separator && !inQuotes) { // if find separator and not in quotes, add field to the list
				result.add(field.toString());
				field.setLength(0);            // empty the field and ready for the next
			} else if (c == separator && inQuotesCounter == 4) { //if description is in quotes handle count the quotes until end of the string
				result.add(field.toString());
				field.setLength(0);
				inQuotesCounter = 0;
				inQuotes = false;
			} else if (c != quoteChar) { // else append the char into a field
				field.append(c); 
			}
		}

		return result;

	}

}
